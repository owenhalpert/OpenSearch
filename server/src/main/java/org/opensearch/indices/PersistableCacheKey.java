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
import java.util.UUID;

public class PersistableCacheKey implements Serializable {
    private UUID id;
    IndexReader.CacheKey cacheKey;

    public PersistableCacheKey(IndexReader.CacheKey cacheKey) {
        this.cacheKey = cacheKey;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public IndexReader.CacheKey getCacheKey() {
        return cacheKey;
    }
}
