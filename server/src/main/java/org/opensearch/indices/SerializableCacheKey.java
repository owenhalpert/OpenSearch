/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableCacheKey implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final SerializableCacheKey INSTANCE = new SerializableCacheKey();

    private SerializableCacheKey() {
        // Private constructor to prevent instantiation from outside
    }

    public static SerializableCacheKey getInstance() {
        return INSTANCE;
    }

    // Custom readObject method for deserialization
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Ensure the same instance is used during deserialization
    }

    // Custom writeObject method for serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        // Do nothing (no fields to serialize)
    }

    // Resolve method to ensure the same instance is used during deserialization
    private Object readResolve() {
        return INSTANCE;
    }
}
