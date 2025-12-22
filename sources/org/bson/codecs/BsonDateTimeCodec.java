package org.bson.codecs;

import org.bson.BsonDateTime;
import org.bson.BsonReader;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/BsonDateTimeCodec.class */
public class BsonDateTimeCodec implements Codec<BsonDateTime> {
    @Override // org.bson.codecs.Decoder
    public BsonDateTime decode(BsonReader reader, DecoderContext decoderContext) {
        return new BsonDateTime(reader.readDateTime());
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, BsonDateTime value, EncoderContext encoderContext) {
        writer.writeDateTime(value.getValue());
    }

    @Override // org.bson.codecs.Encoder
    public Class<BsonDateTime> getEncoderClass() {
        return BsonDateTime.class;
    }
}
