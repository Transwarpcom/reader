package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/LongCodec.class */
public class LongCodec implements Codec<Long> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Long value, EncoderContext encoderContext) {
        writer.writeInt64(value.longValue());
    }

    @Override // org.bson.codecs.Decoder
    public Long decode(BsonReader reader, DecoderContext decoderContext) {
        return Long.valueOf(NumberCodecHelper.decodeLong(reader));
    }

    @Override // org.bson.codecs.Encoder
    public Class<Long> getEncoderClass() {
        return Long.class;
    }
}
