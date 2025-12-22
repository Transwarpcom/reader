package com.jayway.jsonpath.spi.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPathException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/GsonJsonProvider.class */
public class GsonJsonProvider extends AbstractJsonProvider {
    private static final JsonParser PARSER = new JsonParser();
    private final Gson gson;

    public GsonJsonProvider() {
        this(new Gson());
    }

    public GsonJsonProvider(Gson gson) {
        this.gson = gson;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object unwrap(Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof JsonElement)) {
            return o;
        }
        JsonElement e = (JsonElement) o;
        if (e.isJsonNull()) {
            return null;
        }
        if (e.isJsonPrimitive()) {
            JsonPrimitive p = e.getAsJsonPrimitive();
            if (p.isString()) {
                return p.getAsString();
            }
            if (p.isBoolean()) {
                return Boolean.valueOf(p.getAsBoolean());
            }
            if (p.isNumber()) {
                return unwrapNumber(p.getAsNumber());
            }
        }
        return o;
    }

    private static boolean isPrimitiveNumber(Number n) {
        return (n instanceof Integer) || (n instanceof Float) || (n instanceof Double) || (n instanceof Long) || (n instanceof BigDecimal) || (n instanceof BigInteger);
    }

    private static Number unwrapNumber(Number n) {
        Number unwrapped;
        if (!isPrimitiveNumber(n)) {
            BigDecimal bigDecimal = new BigDecimal(n.toString());
            if (bigDecimal.scale() <= 0) {
                if (bigDecimal.abs().compareTo(new BigDecimal(Integer.MAX_VALUE)) <= 0) {
                    unwrapped = Integer.valueOf(bigDecimal.intValue());
                } else if (bigDecimal.abs().compareTo(new BigDecimal(Long.MAX_VALUE)) <= 0) {
                    unwrapped = Long.valueOf(bigDecimal.longValue());
                } else {
                    unwrapped = bigDecimal;
                }
            } else {
                double doubleValue = bigDecimal.doubleValue();
                if (BigDecimal.valueOf(doubleValue).compareTo(bigDecimal) != 0) {
                    unwrapped = bigDecimal;
                } else {
                    unwrapped = Double.valueOf(doubleValue);
                }
            }
        } else {
            unwrapped = n;
        }
        return unwrapped;
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(String json) throws InvalidJsonException {
        return PARSER.parse(json);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(InputStream jsonStream, String charset) throws InvalidJsonException {
        try {
            return PARSER.parse(new InputStreamReader(jsonStream, charset));
        } catch (UnsupportedEncodingException e) {
            throw new JsonPathException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public String toJson(Object obj) {
        return this.gson.toJson(obj);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createArray() {
        return new JsonArray();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createMap() {
        return new JsonObject();
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isArray(Object obj) {
        return (obj instanceof JsonArray) || (obj instanceof List);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getArrayIndex(Object obj, int idx) {
        return toJsonArray(obj).get(idx);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setArrayIndex(Object array, int index, Object newValue) {
        if (!isArray(array)) {
            throw new UnsupportedOperationException();
        }
        JsonArray arr = toJsonArray(array);
        if (index == arr.size()) {
            arr.add(createJsonElement(newValue));
        } else {
            arr.set(index, createJsonElement(newValue));
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getMapValue(Object obj, String key) {
        JsonObject jsonObject = toJsonObject(obj);
        Object o = jsonObject.get(key);
        if (!jsonObject.has(key)) {
            return UNDEFINED;
        }
        return unwrap(o);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setProperty(Object obj, Object key, Object value) {
        int index;
        if (isMap(obj)) {
            toJsonObject(obj).add(key.toString(), createJsonElement(value));
            return;
        }
        JsonArray array = toJsonArray(obj);
        if (key != null) {
            index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
        } else {
            index = array.size();
        }
        if (index == array.size()) {
            array.add(createJsonElement(value));
        } else {
            array.set(index, createJsonElement(value));
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void removeProperty(Object obj, Object key) {
        if (isMap(obj)) {
            toJsonObject(obj).remove(key.toString());
            return;
        }
        JsonArray array = toJsonArray(obj);
        int index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
        array.remove(index);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isMap(Object obj) {
        return obj instanceof JsonObject;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Collection<String> getPropertyKeys(Object obj) {
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : toJsonObject(obj).entrySet()) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public int length(Object obj) {
        if (isArray(obj)) {
            return toJsonArray(obj).size();
        }
        if (isMap(obj)) {
            return toJsonObject(obj).entrySet().size();
        }
        if (obj instanceof JsonElement) {
            JsonElement element = toJsonElement(obj);
            if (element.isJsonPrimitive()) {
                return element.toString().length();
            }
        }
        throw new JsonPathException("length operation can not applied to " + (obj != null ? obj.getClass().getName() : "null"));
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Iterable<?> toIterable(Object obj) {
        JsonArray arr = toJsonArray(obj);
        List<Object> values = new ArrayList<>(arr.size());
        Iterator<JsonElement> it = arr.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            values.add(unwrap(o));
        }
        return values;
    }

    private JsonElement createJsonElement(Object o) {
        return this.gson.toJsonTree(o);
    }

    private JsonArray toJsonArray(Object o) {
        return (JsonArray) o;
    }

    private JsonObject toJsonObject(Object o) {
        return (JsonObject) o;
    }

    private JsonElement toJsonElement(Object o) {
        return (JsonElement) o;
    }
}
