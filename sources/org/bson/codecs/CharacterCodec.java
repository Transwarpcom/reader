package org.bson.codecs;

import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/CharacterCodec.class */
public class CharacterCodec implements Codec<Character> {
    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Character value, EncoderContext encoderContext) {
        Assertions.notNull("value", value);
        writer.writeString(value.toString());
    }

    @Override // org.bson.codecs.Decoder
    public Character decode(BsonReader reader, DecoderContext decoderContext) {
        String string = reader.readString();
        if (string.length() != 1) {
            throw new BsonInvalidOperationException(String.format("Attempting to decode the string '%s' to a character, but its length is not equal to one", string));
        }
        return Character.valueOf(string.charAt(0));
    }

    @Override // org.bson.codecs.Encoder
    public Class<Character> getEncoderClass() {
        return Character.class;
    }
}
