package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/IntegerCodec.class */
public class IntegerCodec implements Codec<Integer> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Integer value, EncoderContext encoderContext) {
        writer.writeInt32(value.intValue());
    }

    @Override // org.bson.codecs.Decoder
    public Integer decode(BsonReader reader, DecoderContext decoderContext) {
        return Integer.valueOf(NumberCodecHelper.decodeInt(reader));
    }

    @Override // org.bson.codecs.Encoder
    public Class<Integer> getEncoderClass() {
        return Integer.class;
    }
}
