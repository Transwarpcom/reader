package org.bson.codecs;

import org.bson.BsonDecimal128;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonDecimal128Codec.class */
public class BsonDecimal128Codec implements Codec<BsonDecimal128> {
    @Override // org.bson.codecs.Decoder
    public BsonDecimal128 decode(BsonReader reader, DecoderContext decoderContext) {
        return new BsonDecimal128(reader.readDecimal128());
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonDecimal128 value, EncoderContext encoderContext) {
        writer.writeDecimal128(value.getValue());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonDecimal128> getEncoderClass() {
        return BsonDecimal128.class;
    }
}
