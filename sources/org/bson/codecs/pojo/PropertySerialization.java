package org.bson.codecs.pojo;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PropertySerialization.class */
public interface PropertySerialization<T> {
    boolean shouldSerialize(T t);
}
