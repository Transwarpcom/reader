package io.vertx.core.json.pointer;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.pointer.impl.JsonPointerIteratorImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/pointer/JsonPointerIterator.class */
public interface JsonPointerIterator {
    public static final JsonPointerIterator JSON_ITERATOR = new JsonPointerIteratorImpl();

    boolean isObject(Object obj);

    boolean isArray(Object obj);

    boolean isNull(Object obj);

    boolean objectContainsKey(Object obj, String str);

    Object getObjectParameter(Object obj, String str, boolean z);

    Object getArrayElement(Object obj, int i);

    boolean writeObjectParameter(Object obj, String str, Object obj2);

    boolean writeArrayElement(Object obj, int i, Object obj2);

    boolean appendArrayElement(Object obj, Object obj2);
}
