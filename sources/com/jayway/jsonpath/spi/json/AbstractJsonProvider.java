package com.jayway.jsonpath.spi.json;

import com.jayway.jsonpath.JsonPathException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/AbstractJsonProvider.class */
public abstract class AbstractJsonProvider implements JsonProvider {
    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isArray(Object obj) {
        return obj instanceof List;
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object getArrayIndex(Object obj, int idx) {
        return ((List) obj).get(idx);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    @Deprecated
    public final Object getArrayIndex(Object obj, int idx, boolean unwrap) {
        return getArrayIndex(obj, idx);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public void setArrayIndex(Object array, int index, Object newValue) {
        if (!isArray(array)) {
            throw new UnsupportedOperationException();
        }
        List l = (List) array;
        if (index == l.size()) {
            l.add(newValue);
        } else {
            l.set(index, newValue);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object getMapValue(Object obj, String key) {
        Map m = (Map) obj;
        if (!m.containsKey(key)) {
            return JsonProvider.UNDEFINED;
        }
        return m.get(key);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public void setProperty(Object obj, Object key, Object value) {
        if (isMap(obj)) {
            ((Map) obj).put(key.toString(), value);
            return;
        }
        throw new JsonPathException(new StringBuilder().append("setProperty operation cannot be used with ").append(obj).toString() != null ? obj.getClass().getName() : "null");
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public void removeProperty(Object obj, Object key) {
        if (isMap(obj)) {
            ((Map) obj).remove(key.toString());
            return;
        }
        List list = (List) obj;
        int index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
        list.remove(index);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isMap(Object obj) {
        return obj instanceof Map;
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Collection<String> getPropertyKeys(Object obj) {
        if (isArray(obj)) {
            throw new UnsupportedOperationException();
        }
        return ((Map) obj).keySet();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public int length(Object obj) {
        if (isArray(obj)) {
            return ((List) obj).size();
        }
        if (isMap(obj)) {
            return getPropertyKeys(obj).size();
        }
        if (obj instanceof String) {
            return ((String) obj).length();
        }
        throw new JsonPathException("length operation cannot be applied to " + (obj != null ? obj.getClass().getName() : "null"));
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Iterable<?> toIterable(Object obj) {
        if (isArray(obj)) {
            return (Iterable) obj;
        }
        throw new JsonPathException(new StringBuilder().append("Cannot iterate over ").append(obj).toString() != null ? obj.getClass().getName() : "null");
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object unwrap(Object obj) {
        return obj;
    }
}
