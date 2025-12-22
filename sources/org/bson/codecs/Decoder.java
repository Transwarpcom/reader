package org.bson.codecs;

import org.bson.BsonReader;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/Decoder.class */
public interface Decoder<T> {
    T decode(BsonReader bsonReader, DecoderContext decoderContext);
}
