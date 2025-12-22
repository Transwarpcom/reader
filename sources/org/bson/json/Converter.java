package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/Converter.class */
public interface Converter<T> {
    void convert(T t, StrictJsonWriter strictJsonWriter);
}
