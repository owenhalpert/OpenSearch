/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices;

import org.apache.lucene.index.IndexReader;

import java.io.Serializable;
import java.util.Objects;

public class SerializableCacheKey implements Serializable {
    private IndexReader.CacheKey cacheKey;
    public SerializableCacheKey(IndexReader.CacheKey cacheKey) {
        this.cacheKey = cacheKey;
    }
    public IndexReader.CacheKey getCacheKey() {
        return cacheKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableCacheKey that = (SerializableCacheKey) o;
        return Objects.equals(getCacheKey(), that.getCacheKey());
    }

}
