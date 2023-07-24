/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices;

import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import org.apache.lucene.index.IndexReader;
import org.ehcache.spi.serialization.SerializerException;
import com.esotericsoftware.kryo.*;
import org.ehcache.spi.serialization.Serializer;
import org.opensearch.common.bytes.BytesReference;
import org.opensearch.common.metrics.CounterMetric;
import org.opensearch.indices.IndicesRequestCache.Key;
import org.opensearch.indices.IndicesRequestCache.TestEntity;
import org.opensearch.common.bytes.BytesArray;


import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class KeySerializer implements Serializer<Key> {
    private Kryo kryo;
    public KeySerializer(ClassLoader classLoader) {
        kryo = new Kryo();
        kryo.setRegistrationRequired(false);
    }
    @Override
    public ByteBuffer serialize(IndicesRequestCache.Key key) throws SerializerException {
        Output output = new Output(new ByteArrayOutputStream());
        kryo.writeObject(output, key.readerCacheKey);
        kryo.writeObject(output, key.entity);
        kryo.writeObject(output, key.value);
        output.close();
        return ByteBuffer.wrap(output.getBuffer());
    }

    @Override
    public IndicesRequestCache.Key read(ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        Input input = new Input(binary.array());
        IndexReader.CacheKey readerCacheKey = kryo.readObject(input, IndexReader.CacheKey.class);
        IndicesRequestCache.TestEntity entity = kryo.readObject(input, IndicesRequestCache.TestEntity.class);
        BytesArray value = kryo.readObject(input, BytesArray.class);
        IndicesRequestCache.Key key = new Key(entity, readerCacheKey, value);
        return key;
    }

    @Override
    public boolean equals(IndicesRequestCache.Key object, ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        IndicesRequestCache.Key key = read(binary);
        return object.equals(key);
    }
}
