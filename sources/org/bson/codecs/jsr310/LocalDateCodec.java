package org.bson.codecs.jsr310;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/jsr310/LocalDateCodec.class */
public class LocalDateCodec extends DateTimeBasedCodec<LocalDate> {
    @Override // org.bson.codecs.Decoder
    public LocalDate decode(BsonReader reader, DecoderContext decoderContext) {
        return Instant.ofEpochMilli(validateAndReadDateTime(reader)).atZone(ZoneOffset.UTC).toLocalDate();
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, LocalDate value, EncoderContext encoderContext) {
        try {
            writer.writeDateTime(value.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
        } catch (ArithmeticException e) {
            throw new CodecConfigurationException(String.format("Unsupported LocalDate '%s' could not be converted to milliseconds: %s", value, e.getMessage()), e);
        }
    }

    @Override // org.bson.codecs.Encoder
    public Class<LocalDate> getEncoderClass() {
        return LocalDate.class;
    }
}
