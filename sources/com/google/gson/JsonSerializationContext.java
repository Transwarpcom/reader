package com.google.gson;

import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/gson-2.8.5.jar:com/google/gson/JsonSerializationContext.class */
public interface JsonSerializationContext {
    JsonElement serialize(Object obj);

    JsonElement serialize(Object obj, Type type);
}
