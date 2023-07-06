/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.util.concurrent.atomic.AtomicBoolean;

public class CacheEntitySerializer extends Serializer<IndicesRequestCache.Key> {

    @Override
    public void write(Kryo kryo, Output output, IndicesRequestCache.Key object) {
        IndicesRequestCache.TestEntity entity = (IndicesRequestCache.TestEntity) object.entity;
    }

    @Override
    public IndicesRequestCache.Key read(Kryo kryo, Input input, Class<? extends IndicesRequestCache.Key> type) {
        return null;
    }
}
