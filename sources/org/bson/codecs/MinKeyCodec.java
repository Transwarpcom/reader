package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.types.MinKey;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/MinKeyCodec.class */
public class MinKeyCodec implements Codec<MinKey> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, MinKey value, EncoderContext encoderContext) {
        writer.writeMinKey();
    }

    @Override // org.bson.codecs.Decoder
    public MinKey decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readMinKey();
        return new MinKey();
    }

    @Override // org.bson.codecs.Encoder
    public Class<MinKey> getEncoderClass() {
        return MinKey.class;
    }
}
