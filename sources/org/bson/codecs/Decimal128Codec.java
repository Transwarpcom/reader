package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.types.Decimal128;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/Decimal128Codec.class */
public final class Decimal128Codec implements Codec<Decimal128> {
    @Override // org.bson.codecs.Decoder
    public Decimal128 decode(BsonReader reader, DecoderContext decoderContext) {
        return reader.readDecimal128();
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Decimal128 value, EncoderContext encoderContext) {
        writer.writeDecimal128(value);
    }

    @Override // org.bson.codecs.Encoder
    public Class<Decimal128> getEncoderClass() {
        return Decimal128.class;
    }
}
