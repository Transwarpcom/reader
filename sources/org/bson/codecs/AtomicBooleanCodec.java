package org.bson.codecs;

import java.util.concurrent.atomic.AtomicBoolean;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/AtomicBooleanCodec.class */
public class AtomicBooleanCodec implements Codec<AtomicBoolean> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, AtomicBoolean value, EncoderContext encoderContext) {
        writer.writeBoolean(value.get());
    }

    @Override // org.bson.codecs.Decoder
    public AtomicBoolean decode(BsonReader reader, DecoderContext decoderContext) {
        return new AtomicBoolean(reader.readBoolean());
    }

    @Override // org.bson.codecs.Encoder
    public Class<AtomicBoolean> getEncoderClass() {
        return AtomicBoolean.class;
    }
}
