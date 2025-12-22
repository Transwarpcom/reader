package io.vertx.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/JsonObjectSerializer.class */
class JsonObjectSerializer extends JsonSerializer<JsonObject> {
    JsonObjectSerializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(JsonObject value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObject(value.getMap());
    }
}
