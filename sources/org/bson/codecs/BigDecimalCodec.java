package org.bson.codecs;

import java.math.BigDecimal;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.types.Decimal128;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BigDecimalCodec.class */
public final class BigDecimalCodec implements Codec<BigDecimal> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BigDecimal value, EncoderContext encoderContext) {
        writer.writeDecimal128(new Decimal128(value));
    }

    @Override // org.bson.codecs.Decoder
    public BigDecimal decode(BsonReader reader, DecoderContext decoderContext) {
        return reader.readDecimal128().bigDecimalValue();
    }

    @Override // org.bson.codecs.Encoder
    public Class<BigDecimal> getEncoderClass() {
        return BigDecimal.class;
    }
}
