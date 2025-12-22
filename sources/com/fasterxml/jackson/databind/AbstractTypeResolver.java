package com.fasterxml.jackson.databind;

/* loaded from: reader.jar:BOOT-INF/lib/jackson-databind-2.9.9.jar:com/fasterxml/jackson/databind/AbstractTypeResolver.class */
public abstract class AbstractTypeResolver {
    public JavaType findTypeMapping(DeserializationConfig config, JavaType type) {
        return null;
    }

    @Deprecated
    public JavaType resolveAbstractType(DeserializationConfig config, JavaType type) {
        return null;
    }

    public JavaType resolveAbstractType(DeserializationConfig config, BeanDescription typeDesc) {
        return null;
    }
}
