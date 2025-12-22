package org.bson.codecs;

import java.util.concurrent.atomic.AtomicInteger;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/AtomicIntegerCodec.class */
public class AtomicIntegerCodec implements Codec<AtomicInteger> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, AtomicInteger value, EncoderContext encoderContext) {
        writer.writeInt32(value.intValue());
    }

    @Override // org.bson.codecs.Decoder
    public AtomicInteger decode(BsonReader reader, DecoderContext decoderContext) {
        return new AtomicInteger(NumberCodecHelper.decodeInt(reader));
    }

    @Override // org.bson.codecs.Encoder
    public Class<AtomicInteger> getEncoderClass() {
        return AtomicInteger.class;
    }
}
