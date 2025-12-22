package io.vertx.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/ByteArraySerializer.class */
class ByteArraySerializer extends JsonSerializer<byte[]> {
    ByteArraySerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(byte[] value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(Base64.getEncoder().encodeToString(value));
    }
}
