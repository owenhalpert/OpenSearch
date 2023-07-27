/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.lucene.index.IndexReader;
import org.ehcache.spi.serialization.SerializerException;
import com.esotericsoftware.kryo.*;
import org.ehcache.spi.serialization.Serializer;
import org.opensearch.indices.IndicesRequestCache.Key;
import org.opensearch.indices.IndicesRequestCache.TestEntity;
import org.opensearch.common.bytes.BytesArray;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class KeySerializer implements Serializer<Integer> {
    private Kryo kryo;
    public KeySerializer(ClassLoader classLoader) {
        kryo = new Kryo();
        kryo.register(Integer.class);
    }
    @Override
    public ByteBuffer serialize(Integer ehcacheKey) throws SerializerException {
        Output output = new Output(new ByteArrayOutputStream());
        kryo.writeObject(output, ehcacheKey);
        output.close();
        return ByteBuffer.wrap(output.getBuffer());
    }

    @Override
    public Integer read(ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        Input input = new Input(binary.array());
        Integer ehcacheKey = kryo.readObject(input, Integer.class);
        return ehcacheKey;
    }

    @Override
    public boolean equals(Integer object, ByteBuffer binary) throws ClassNotFoundException, SerializerException {
        Integer ehcacheKey = read(binary);
        return object.equals(ehcacheKey);
    }
}
