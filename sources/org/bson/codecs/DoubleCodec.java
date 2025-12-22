package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/DoubleCodec.class */
public class DoubleCodec implements Codec<Double> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Double value, EncoderContext encoderContext) {
        writer.writeDouble(value.doubleValue());
    }

    @Override // org.bson.codecs.Decoder
    public Double decode(BsonReader reader, DecoderContext decoderContext) {
        return Double.valueOf(NumberCodecHelper.decodeDouble(reader));
    }

    @Override // org.bson.codecs.Encoder
    public Class<Double> getEncoderClass() {
        return Double.class;
    }
}
