package io.vertx.core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/InstantDeserializer.class */
class InstantDeserializer extends JsonDeserializer<Instant> {
    InstantDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        try {
            return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(text));
        } catch (DateTimeException e) {
            throw new InvalidFormatException(p, "Expected an ISO 8601 formatted date time", text, (Class<?>) Instant.class);
        }
    }
}
