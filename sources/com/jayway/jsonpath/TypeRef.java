package com.jayway.jsonpath;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/TypeRef.class */
public abstract class TypeRef<T> implements Comparable<TypeRef<T>> {
    protected final Type type;

    protected TypeRef() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new IllegalArgumentException("No type info in TypeRef");
        }
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }

    @Override // java.lang.Comparable
    public int compareTo(TypeRef<T> o) {
        return 0;
    }
}
