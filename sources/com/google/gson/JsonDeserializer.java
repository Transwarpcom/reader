package com.google.gson;

import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/gson-2.8.5.jar:com/google/gson/JsonDeserializer.class */
public interface JsonDeserializer<T> {
    T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException;
}
