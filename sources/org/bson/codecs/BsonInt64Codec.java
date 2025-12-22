package org.bson.codecs;

import org.bson.BsonInt64;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonInt64Codec.class */
public class BsonInt64Codec implements Codec<BsonInt64> {
    @Override // org.bson.codecs.Decoder
    public BsonInt64 decode(BsonReader reader, DecoderContext decoderContext) {
        return new BsonInt64(reader.readInt64());
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonInt64 value, EncoderContext encoderContext) {
        writer.writeInt64(value.getValue());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonInt64> getEncoderClass() {
        return BsonInt64.class;
    }
}
