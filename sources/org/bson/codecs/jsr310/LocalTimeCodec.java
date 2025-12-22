package org.bson.codecs.jsr310;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/jsr310/LocalTimeCodec.class */
public class LocalTimeCodec extends DateTimeBasedCodec<LocalTime> {
    @Override // org.bson.codecs.Decoder
    public LocalTime decode(BsonReader reader, DecoderContext decoderContext) {
        return Instant.ofEpochMilli(validateAndReadDateTime(reader)).atOffset(ZoneOffset.UTC).toLocalTime();
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter writer, LocalTime value, EncoderContext encoderContext) {
        writer.writeDateTime(value.atDate(LocalDate.ofEpochDay(0L)).toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    @Override // org.bson.codecs.Encoder
    public Class<LocalTime> getEncoderClass() {
        return LocalTime.class;
    }
}
