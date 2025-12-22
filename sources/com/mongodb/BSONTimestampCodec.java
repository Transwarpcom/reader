package com.mongodb;

import org.bson.BsonReader;
import org.bson.BsonTimestamp;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.BSONTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/BSONTimestampCodec.class */
public class BSONTimestampCodec implements Codec<BSONTimestamp> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BSONTimestamp value, EncoderContext encoderContext) {
        writer.writeTimestamp(new BsonTimestamp(value.getTime(), value.getInc()));
    }

    @Override // org.bson.codecs.Decoder
    public BSONTimestamp decode(BsonReader reader, DecoderContext decoderContext) {
        BsonTimestamp timestamp = reader.readTimestamp();
        return new BSONTimestamp(timestamp.getTime(), timestamp.getInc());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BSONTimestamp> getEncoderClass() {
        return BSONTimestamp.class;
    }
}
