package com.google.gson;

/* loaded from: reader.jar:BOOT-INF/lib/gson-2.8.5.jar:com/google/gson/ExclusionStrategy.class */
public interface ExclusionStrategy {
    boolean shouldSkipField(FieldAttributes fieldAttributes);

    boolean shouldSkipClass(Class<?> cls);
}
