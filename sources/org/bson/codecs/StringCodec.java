package org.bson.codecs;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/StringCodec.class */
public class StringCodec implements Codec<String> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, String value, EncoderContext encoderContext) {
        writer.writeString(value);
    }

    @Override // org.bson.codecs.Decoder
    public String decode(BsonReader reader, DecoderContext decoderContext) {
        if (reader.getCurrentBsonType() == BsonType.SYMBOL) {
            return reader.readSymbol();
        }
        return reader.readString();
    }

    @Override // org.bson.codecs.Encoder
    public Class<String> getEncoderClass() {
        return String.class;
    }
}
