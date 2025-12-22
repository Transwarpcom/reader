package com.google.gson;

import java.lang.reflect.Field;

/* loaded from: reader.jar:BOOT-INF/lib/gson-2.8.5.jar:com/google/gson/FieldNamingStrategy.class */
public interface FieldNamingStrategy {
    String translateName(Field field);
}
