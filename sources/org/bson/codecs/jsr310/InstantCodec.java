package org.bson.codecs.jsr310;

import java.time.Instant;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/jsr310/InstantCodec.class */
public class InstantCodec extends DateTimeBasedCodec<Instant> {
    @Override // org.bson.codecs.Decoder
    public Instant decode(BsonReader reader, DecoderContext decoderContext) {
        return Instant.ofEpochMilli(validateAndReadDateTime(reader));
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, Instant value, EncoderContext encoderContext) {
        try {
            writer.writeDateTime(value.toEpochMilli());
        } catch (ArithmeticException e) {
            throw new CodecConfigurationException(String.format("Unsupported Instant value '%s' could not be converted to milliseconds: %s", value, e.getMessage()), e);
        }
    }

    @Override // org.bson.codecs.Encoder
    public Class<Instant> getEncoderClass() {
        return Instant.class;
    }
}
