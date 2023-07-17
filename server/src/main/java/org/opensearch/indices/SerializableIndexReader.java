/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

package org.opensearch.indices;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermVectors;
import org.opensearch.index.Index;

import java.io.IOException;
import java.io.Serializable;

public class SerializableIndexReader extends IndexReader implements Serializable {

    @Override
    public TermVectors termVectors() throws IOException {
    }
}
