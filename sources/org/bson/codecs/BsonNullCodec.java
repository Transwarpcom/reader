package org.bson.codecs;

import org.bson.BsonNull;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonNullCodec.class */
public class BsonNullCodec implements Codec<BsonNull> {
    @Override // org.bson.codecs.Decoder
    public BsonNull decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readNull();
        return BsonNull.VALUE;
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonNull value, EncoderContext encoderContext) {
        writer.writeNull();
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonNull> getEncoderClass() {
        return BsonNull.class;
    }
}
