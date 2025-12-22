package org.bson.codecs;

import org.bson.BsonBoolean;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonBooleanCodec.class */
public class BsonBooleanCodec implements Codec<BsonBoolean> {
    @Override // org.bson.codecs.Decoder
    public BsonBoolean decode(BsonReader reader, DecoderContext decoderContext) {
        boolean value = reader.readBoolean();
        return BsonBoolean.valueOf(value);
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonBoolean value, EncoderContext encoderContext) {
        writer.writeBoolean(value.getValue());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonBoolean> getEncoderClass() {
        return BsonBoolean.class;
    }
}
