package com.google.gson;

import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/gson-2.8.5.jar:com/google/gson/JsonSerializer.class */
public interface JsonSerializer<T> {
    JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext);
}
