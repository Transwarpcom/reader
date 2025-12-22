package org.bson.codecs;

import org.bson.BsonInt32;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonInt32Codec.class */
public class BsonInt32Codec implements Codec<BsonInt32> {
    @Override // org.bson.codecs.Decoder
    public BsonInt32 decode(BsonReader reader, DecoderContext decoderContext) {
        return new BsonInt32(reader.readInt32());
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonInt32 value, EncoderContext encoderContext) {
        writer.writeInt32(value.getValue());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonInt32> getEncoderClass() {
        return BsonInt32.class;
    }
}
