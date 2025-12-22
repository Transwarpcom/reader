package org.bson.codecs;

import org.bson.BsonObjectId;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonObjectIdCodec.class */
public class BsonObjectIdCodec implements Codec<BsonObjectId> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonObjectId value, EncoderContext encoderContext) {
        writer.writeObjectId(value.getValue());
    }

    @Override // org.bson.codecs.Decoder
    public BsonObjectId decode(BsonReader reader, DecoderContext decoderContext) {
        return new BsonObjectId(reader.readObjectId());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonObjectId> getEncoderClass() {
        return BsonObjectId.class;
    }
}
