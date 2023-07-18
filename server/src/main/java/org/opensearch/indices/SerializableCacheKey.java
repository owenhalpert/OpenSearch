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

public class SerializableCacheKey implements Serializable {
    private IndexReader.CacheKey cacheKey;
    public SerializableCacheKey(IndexReader.CacheKey cacheKey) {
        this.cacheKey = cacheKey;
    }
    public IndexReader.CacheKey getCacheKey() {
        return cacheKey;
    }
}
