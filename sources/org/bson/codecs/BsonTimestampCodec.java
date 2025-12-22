package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonTimestamp;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonTimestampCodec.class */
public class BsonTimestampCodec implements Codec<BsonTimestamp> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonTimestamp value, EncoderContext encoderContext) {
        writer.writeTimestamp(value);
    }

    @Override // org.bson.codecs.Decoder
    public BsonTimestamp decode(BsonReader reader, DecoderContext decoderContext) {
        return reader.readTimestamp();
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonTimestamp> getEncoderClass() {
        return BsonTimestamp.class;
    }
}
