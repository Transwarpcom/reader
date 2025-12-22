package io.vertx.core.json.pointer.impl;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointerIterator;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/pointer/impl/JsonPointerIteratorImpl.class */
public class JsonPointerIteratorImpl implements JsonPointerIterator {
    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean isObject(Object value) {
        return value instanceof JsonObject;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean isArray(Object value) {
        return value instanceof JsonArray;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean isNull(Object value) {
        return value == null;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean objectContainsKey(Object value, String key) {
        return isObject(value) && ((JsonObject) value).containsKey(key);
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public Object getObjectParameter(Object value, String key, boolean createOnMissing) {
        if (isObject(value)) {
            if (!objectContainsKey(value, key)) {
                if (createOnMissing) {
                    writeObjectParameter(value, key, new JsonObject());
                } else {
                    return null;
                }
            }
            return jsonifyValue(((JsonObject) value).getValue(key));
        }
        return null;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public Object getArrayElement(Object value, int i) {
        if (isArray(value)) {
            try {
                return jsonifyValue(((JsonArray) value).getValue(i));
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return null;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean writeObjectParameter(Object value, String key, Object el) {
        if (isObject(value)) {
            ((JsonObject) value).put(key, el);
            return true;
        }
        return false;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean writeArrayElement(Object value, int i, Object el) {
        if (isArray(value)) {
            try {
                ((JsonArray) value).getList().add(i, el);
                return true;
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        }
        return false;
    }

    @Override // io.vertx.core.json.pointer.JsonPointerIterator
    public boolean appendArrayElement(Object value, Object el) {
        if (isArray(value)) {
            ((JsonArray) value).add(el);
            return true;
        }
        return false;
    }

    private Object jsonifyValue(Object v) {
        return v instanceof Map ? new JsonObject((Map<String, Object>) v) : v instanceof List ? new JsonArray((List) v) : v;
    }
}
