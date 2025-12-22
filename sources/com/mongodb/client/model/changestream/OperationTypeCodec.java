package com.mongodb.client.model.changestream;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/changestream/OperationTypeCodec.class */
final class OperationTypeCodec implements Codec<OperationType> {
    OperationTypeCodec() {
    }

    @Override // org.bson.codecs.Decoder
    public OperationType decode(BsonReader reader, DecoderContext decoderContext) {
        return OperationType.fromString(reader.readString());
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, OperationType value, EncoderContext encoderContext) {
        writer.writeString(value.getValue());
    }

    @Override // org.bson.codecs.Encoder
    public Class<OperationType> getEncoderClass() {
        return OperationType.class;
    }
}
