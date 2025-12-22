package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/ObjectIdCodec.class */
public class ObjectIdCodec implements Codec<ObjectId> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, ObjectId value, EncoderContext encoderContext) {
        writer.writeObjectId(value);
    }

    @Override // org.bson.codecs.Decoder
    public ObjectId decode(BsonReader reader, DecoderContext decoderContext) {
        return reader.readObjectId();
    }

    @Override // org.bson.codecs.Encoder
    public Class<ObjectId> getEncoderClass() {
        return ObjectId.class;
    }
}
