/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

package org.opensearch.indices;

import com.carrotsearch.hppc.ObjectHashSet;
import com.carrotsearch.hppc.ObjectSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.util.Accountable;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.RamUsageEstimator;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.internal.statistics.DefaultStatisticsService;
import org.ehcache.core.spi.service.StatisticsService;
import org.ehcache.core.statistics.CacheStatistics;
import org.ehcache.expiry.Expirations;
import org.ehcache.expiry.ExpiryPolicy;
import org.opensearch.common.CheckedSupplier;
import org.opensearch.common.bytes.BytesReference;
//import org.opensearch.common.cache.Cache;
//import org.opensearch.common.cache.CacheBuilder;
//import org.opensearch.common.cache.CacheLoader;
import org.ehcache.*;
import org.opensearch.common.cache.CacheLoader;
import org.opensearch.common.cache.RemovalListener;
import org.opensearch.common.cache.RemovalNotification;
import org.opensearch.common.lucene.index.OpenSearchDirectoryReader;
import org.opensearch.common.settings.Setting;
import org.opensearch.common.settings.Setting.Property;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.unit.ByteSizeValue;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.common.util.concurrent.ConcurrentCollections;
import org.opensearch.common.util.concurrent.ReleasableLock;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.concurrent.TimeUnit;

/**
 * The indices request cache allows to cache a shard level request stage responses, helping with improving
 * similar requests that are potentially expensive (because of aggs for example). The cache is fully coherent
 * with the semantics of NRT (the index reader cache key is part of the cache key), and relies on size based
 * eviction to evict old reader associated cache entries as well as scheduler reaper to clean readers that
 * are no longer used or closed shards.
 * <p>
 * Currently, the cache is only enabled for count requests, and can only be opted in on an index
 * level setting that can be dynamically changed and defaults to false.
 * <p>
 * There are still several TODOs left in this class, some easily addressable, some more complex, but the support
 * is functional.
 *
 * @opensearch.internal
 */
public final class IndicesRequestCache implements RemovalListener<IndicesRequestCache.Key, BytesReference>, Closeable {

    private static final Logger logger = LogManager.getLogger(IndicesRequestCache.class);

    /**
     * A setting to enable or disable request caching on an index level. Its dynamic by default
     * since we are checking on the cluster state IndexMetadata always.
     */
    public static final Setting<Boolean> INDEX_CACHE_REQUEST_ENABLED_SETTING = Setting.boolSetting(
        "index.requests.cache.enable",
        true,
        Property.Dynamic,
        Property.IndexScope
    );
    public static final Setting<ByteSizeValue> INDICES_CACHE_QUERY_SIZE = Setting.memorySizeSetting(
        "indices.requests.cache.size",
        "1%",
        Property.NodeScope
    );
    public static final Setting<TimeValue> INDICES_CACHE_QUERY_EXPIRE = Setting.positiveTimeSetting(
        "indices.requests.cache.expire",
        new TimeValue(0),
        Property.NodeScope
    );

    private final ConcurrentMap<CleanupKey, Boolean> registeredClosedListeners = ConcurrentCollections.newConcurrentMap();
    private final Set<CleanupKey> keysToClean = ConcurrentCollections.newConcurrentSet();
    private final ByteSizeValue size;
    private final TimeValue expire;
    private final org.ehcache.Cache<Key, BytesReference> cache;
    private final DefaultStatisticsService statisticsService;

    IndicesRequestCache(Settings settings) {
        this.size = INDICES_CACHE_QUERY_SIZE.get(settings);
        this.expire = INDICES_CACHE_QUERY_EXPIRE.exists(settings) ? INDICES_CACHE_QUERY_EXPIRE.get(settings) : null;
        long sizeInBytes = size.getBytes();
        this.statisticsService = new DefaultStatisticsService();
        PersistentCacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .with(CacheManagerBuilder.persistence(new File("/Users/ohalpert/Desktop/Data", "myData")))
            .withCache("twoTieredCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Key.class, BytesReference.class,
                    ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(sizeInBytes, MemoryUnit.MB)
                        .disk(sizeInBytes + 1, MemoryUnit.MB, true)
//                ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMillis(expire.millis()))) TODO: Add TTL only if expire != null
                )
            )
            .using(statisticsService)
            .build(true);
        cache = cacheManager.getCache("twoTieredCache", Key.class, BytesReference.class);
    }

    @Override
    public void close() {
        cache.clear();
    }

    void clear(CacheEntity entity) {
        keysToClean.add(new CleanupKey(entity, null));
        cleanCache();
    }

    @Override
    public void onRemoval(RemovalNotification<Key, BytesReference> notification) {
        notification.getKey().entity.onRemoval(notification);
    }

    BytesReference getOrCompute(
        CacheEntity cacheEntity,
        CheckedSupplier<BytesReference, IOException> loader,
        DirectoryReader reader,
        BytesReference cacheKey
    ) throws Exception {
        assert reader.getReaderCacheHelper() != null;
        final Key key = new Key(cacheEntity, reader.getReaderCacheHelper().getKey(), cacheKey);
        Loader cacheLoader = new Loader(cacheEntity, loader);
        BytesReference value = cache.get(key);
        if (value == null) {
            key.entity.onMiss();
            // see if it's the first time we see this reader, and make sure to register a cleanup key
            value = compute(key, cacheLoader);
            CleanupKey cleanupKey = new CleanupKey(cacheEntity, reader.getReaderCacheHelper().getKey());
            if (!registeredClosedListeners.containsKey(cleanupKey)) {
                Boolean previous = registeredClosedListeners.putIfAbsent(cleanupKey, Boolean.TRUE);
                if (previous == null) {
                    OpenSearchDirectoryReader.addReaderCloseListener(reader, cleanupKey);
                }
            }
        } else {
            key.entity.onHit();
        }
        return value;
    }

    public BytesReference compute(Key key, Loader cacheLoader) throws ExecutionException {
        //TODO: concurrency in this method is usually handled by completableFutures, overlooked for PoC
        BytesReference value;
        try {
            value = cacheLoader.load(key);
        } catch (Exception e) {
            throw new ExecutionException(e);
        }
        if (value == null) {
            NullPointerException npe = new NullPointerException("loader returned a null value");
            throw new ExecutionException(npe);
        } cache.put(key, value);
        return value;
    }

    /**
     * Invalidates the given the cache entry for the given key and it's context
     *
     * @param cacheEntity the cache entity to invalidate for
     * @param reader      the reader to invalidate the cache entry for
     * @param cacheKey    the cache key to invalidate
     */
    void invalidate(CacheEntity cacheEntity, DirectoryReader reader, BytesReference cacheKey) {
        assert reader.getReaderCacheHelper() != null;
        cache.remove(new Key(cacheEntity, reader.getReaderCacheHelper().getKey(), cacheKey));
    }

    /**
     * Loader for the request cache
     *
     * @opensearch.internal
     */
    private static class Loader implements CacheLoader<Key, BytesReference> {

        private final CacheEntity entity;
        private final CheckedSupplier<BytesReference, IOException> loader;
        private boolean loaded;

        Loader(CacheEntity entity, CheckedSupplier<BytesReference, IOException> loader) {
            this.entity = entity;
            this.loader = loader;
        }

        public boolean isLoaded() {
            return this.loaded;
        }

        @Override
        public BytesReference load(Key key) throws Exception {
            BytesReference value = loader.get();
            entity.onCached(key, value);
            loaded = true;
            return value;
        }
    }

    /**
     * Basic interface to make this cache testable.
     */
    interface CacheEntity extends Accountable {

        /**
         * Called after the value was loaded.
         */
        void onCached(Key key, BytesReference value);

        /**
         * Returns <code>true</code> iff the resource behind this entity is still open i.e.
         * entities associated with it can remain in the cache. i.e. IndexShard is still open.
         */
        boolean isOpen();

        /**
         * Returns the cache identity. this is, similar to {@link #isOpen()} the resource identity behind this cache entity.
         * For instance IndexShard is the identity while a CacheEntity is per DirectoryReader. Yet, we group by IndexShard instance.
         */
        Object getCacheIdentity();

        /**
         * Called each time this entity has a cache hit.
         */
        void onHit();

        /**
         * Called each time this entity has a cache miss.
         */
        void onMiss();

        /**
         * Called when this entity instance is removed
         */
        void onRemoval(RemovalNotification<Key, BytesReference> notification);
    }

    /**
     * Unique key for the cache
     *
     * @opensearch.internal
     */
    static class Key implements Accountable, Serializable {
        private static final long BASE_RAM_BYTES_USED = RamUsageEstimator.shallowSizeOfInstance(Key.class);

        public final CacheEntity entity; // use as identity equality
        public final SerializableCacheKey readerCacheKey;
        public final BytesReference value;

        Key(CacheEntity entity, IndexReader.CacheKey readerCacheKey, BytesReference value) {
            this.entity = entity;
            this.readerCacheKey = new SerializableCacheKey(Objects.requireNonNull(readerCacheKey));
            this.value = value;
        }

        @Override
        public long ramBytesUsed() {
            return BASE_RAM_BYTES_USED + entity.ramBytesUsed() + value.length();
        }

        @Override
        public Collection<Accountable> getChildResources() {
            // TODO: more detailed ram usage?
            return Collections.emptyList();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            if (Objects.equals(readerCacheKey, key.readerCacheKey) == false) return false;
            if (!entity.getCacheIdentity().equals(key.entity.getCacheIdentity())) return false;
            if (!value.equals(key.value)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = entity.getCacheIdentity().hashCode();
            result = 31 * result + readerCacheKey.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }
    }

    private class CleanupKey implements IndexReader.ClosedListener {
        final CacheEntity entity;
        final IndexReader.CacheKey readerCacheKey;

        private CleanupKey(CacheEntity entity, IndexReader.CacheKey readerCacheKey) {
            this.entity = entity;
            this.readerCacheKey = readerCacheKey;
        }

        @Override
        public void onClose(IndexReader.CacheKey cacheKey) {
            Boolean remove = registeredClosedListeners.remove(this);
            if (remove != null) {
                keysToClean.add(this);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CleanupKey that = (CleanupKey) o;
            if (Objects.equals(readerCacheKey, that.readerCacheKey) == false) return false;
            if (!entity.getCacheIdentity().equals(that.entity.getCacheIdentity())) return false;
            return true;
        }

        @Override
        public int hashCode() {
            int result = entity.getCacheIdentity().hashCode();
            result = 31 * result + Objects.hashCode(readerCacheKey);
            return result;
        }
    }

    synchronized void cleanCache() {
        final ObjectSet<CleanupKey> currentKeysToClean = new ObjectHashSet<>();
        final ObjectSet<Object> currentFullClean = new ObjectHashSet<>();
        currentKeysToClean.clear();
        currentFullClean.clear();
        for (Iterator<CleanupKey> iterator = keysToClean.iterator(); iterator.hasNext(); ) {
            CleanupKey cleanupKey = iterator.next();
            iterator.remove();
            if (cleanupKey.readerCacheKey == null || cleanupKey.entity.isOpen() == false) {
                // null indicates full cleanup, as does a closed shard
                currentFullClean.add(cleanupKey.entity.getCacheIdentity());
            } else {
                currentKeysToClean.add(cleanupKey);
            }
        }
        if (!currentKeysToClean.isEmpty() || !currentFullClean.isEmpty()) {
            for (Iterator<Cache.Entry<Key, BytesReference>> iterator = cache.iterator(); iterator.hasNext(); ) { //changed to iterator over keys and values
                Key key = iterator.next().getKey(); // changed to .getKey()
                if (currentFullClean.contains(key.entity.getCacheIdentity())) {
                    iterator.remove(); //
                } else {
                    if (currentKeysToClean.contains(new CleanupKey(key.entity, key.readerCacheKey.getCacheKey()))) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    /**
     * Returns the current size of the cache
     */
    int count() {
        CacheStatistics CacheStat = this.statisticsService.getCacheStatistics("twoTieredCache");
        return Math.toIntExact(CacheStat.getTierStatistics().get("Disk").getMappings()); //TODO: this method may be computationally expensive — unfortunately Ehcache doesn't dynamically update count like OS
    }

    int numRegisteredCloseListeners() { // for testing
        return registeredClosedListeners.size();
    }
}
