package io.vertx.core.json.pointer;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.pointer.impl.JsonPointerImpl;
import java.net.URI;
import java.util.List;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/pointer/JsonPointer.class */
public interface JsonPointer {
    boolean isRootPointer();

    boolean isLocalPointer();

    boolean isParent(JsonPointer jsonPointer);

    String toString();

    @GenIgnore({"permitted-type"})
    URI toURI();

    @GenIgnore({"permitted-type"})
    URI getURIWithoutFragment();

    @Fluent
    JsonPointer append(String str);

    @Fluent
    JsonPointer append(int i);

    @Fluent
    JsonPointer append(List<String> list);

    @Fluent
    JsonPointer append(JsonPointer jsonPointer);

    @Fluent
    JsonPointer parent();

    Object queryOrDefault(Object obj, JsonPointerIterator jsonPointerIterator, Object obj2);

    List<Object> tracedQuery(Object obj, JsonPointerIterator jsonPointerIterator);

    Object write(Object obj, JsonPointerIterator jsonPointerIterator, Object obj2, boolean z);

    JsonPointer copy();

    default Object query(Object objectToQuery, JsonPointerIterator iterator) {
        return queryOrDefault(objectToQuery, iterator, null);
    }

    default Object queryJson(Object jsonElement) {
        return query(jsonElement, JsonPointerIterator.JSON_ITERATOR);
    }

    default Object queryJsonOrDefault(Object jsonElement, Object defaultValue) {
        return queryOrDefault(jsonElement, JsonPointerIterator.JSON_ITERATOR, defaultValue);
    }

    default Object writeJson(Object jsonElement, Object newElement) {
        return writeJson(jsonElement, newElement, false);
    }

    default Object writeJson(Object jsonElement, Object newElement, boolean createOnMissing) {
        return write(jsonElement, JsonPointerIterator.JSON_ITERATOR, newElement, createOnMissing);
    }

    static JsonPointer create() {
        return new JsonPointerImpl();
    }

    static JsonPointer from(String pointer) {
        return new JsonPointerImpl(pointer);
    }

    @GenIgnore({"permitted-type"})
    static JsonPointer fromURI(URI uri) {
        return new JsonPointerImpl(uri);
    }
}
