package org.bson.codecs;

import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/Encoder.class */
public interface Encoder<T> {
    void encode(BsonWriter bsonWriter, T t, EncoderContext encoderContext);

    Class<T> getEncoderClass();
}
