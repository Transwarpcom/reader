package com.jayway.jsonpath.spi.json;

import com.jayway.jsonpath.InvalidJsonException;
import com.jayway.jsonpath.JsonPathException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JsonOrgJsonProvider.class */
public class JsonOrgJsonProvider extends AbstractJsonProvider {
    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(String json) throws InvalidJsonException {
        try {
            return new JSONTokener(json).nextValue();
        } catch (JSONException e) {
            throw new InvalidJsonException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(InputStream jsonStream, String charset) throws InvalidJsonException {
        try {
            return new JSONTokener(new InputStreamReader(jsonStream, charset)).nextValue();
        } catch (UnsupportedEncodingException e) {
            throw new JsonPathException(e);
        } catch (JSONException e2) {
            throw new InvalidJsonException(e2);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object unwrap(Object obj) {
        if (obj == JSONObject.NULL) {
            return null;
        }
        return obj;
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public String toJson(Object obj) {
        return obj.toString();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createArray() {
        return new JSONArray();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createMap() {
        return new JSONObject();
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isArray(Object obj) {
        return (obj instanceof JSONArray) || (obj instanceof List);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getArrayIndex(Object obj, int idx) {
        try {
            return toJsonArray(obj).get(idx);
        } catch (JSONException e) {
            throw new JsonPathException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setArrayIndex(Object array, int index, Object newValue) throws JSONException {
        try {
            if (!isArray(array)) {
                throw new UnsupportedOperationException();
            }
            toJsonArray(array).put(index, createJsonElement(newValue));
        } catch (JSONException e) {
            throw new JsonPathException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getMapValue(Object obj, String key) {
        try {
            JSONObject jsonObject = toJsonObject(obj);
            Object o = jsonObject.opt(key);
            if (o == null) {
                return UNDEFINED;
            }
            return unwrap(o);
        } catch (JSONException e) {
            throw new JsonPathException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setProperty(Object obj, Object key, Object value) throws JSONException {
        int index;
        try {
            if (isMap(obj)) {
                toJsonObject(obj).put(key.toString(), createJsonElement(value));
            } else {
                JSONArray array = toJsonArray(obj);
                if (key != null) {
                    index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
                } else {
                    index = array.length();
                }
                if (index == array.length()) {
                    array.put(createJsonElement(value));
                } else {
                    array.put(index, createJsonElement(value));
                }
            }
        } catch (JSONException e) {
            throw new JsonPathException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void removeProperty(Object obj, Object key) {
        if (isMap(obj)) {
            toJsonObject(obj).remove(key.toString());
            return;
        }
        JSONArray array = toJsonArray(obj);
        int index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
        array.remove(index);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isMap(Object obj) {
        return obj instanceof JSONObject;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Collection<String> getPropertyKeys(Object obj) {
        JSONObject jsonObject = toJsonObject(obj);
        try {
            if (Objects.isNull(jsonObject.names())) {
                return new ArrayList();
            }
            return jsonObject.keySet();
        } catch (JSONException e) {
            throw new JsonPathException(e);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public int length(Object obj) {
        if (isArray(obj)) {
            return toJsonArray(obj).length();
        }
        if (isMap(obj)) {
            return toJsonObject(obj).length();
        }
        if (obj instanceof String) {
            return ((String) obj).length();
        }
        throw new JsonPathException("length operation can not applied to " + (obj != null ? obj.getClass().getName() : "null"));
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Iterable<?> toIterable(Object obj) throws JSONException {
        try {
            if (isArray(obj)) {
                JSONArray arr = toJsonArray(obj);
                List<Object> values = new ArrayList<>(arr.length());
                for (int i = 0; i < arr.length(); i++) {
                    values.add(unwrap(arr.get(i)));
                }
                return values;
            }
            JSONObject jsonObject = toJsonObject(obj);
            List<Object> values2 = new ArrayList<>();
            for (int i2 = 0; i2 < jsonObject.names().length(); i2++) {
                String key = (String) jsonObject.names().get(i2);
                Object val = jsonObject.get(key);
                values2.add(unwrap(val));
            }
            return values2;
        } catch (JSONException e) {
            throw new JsonPathException(e);
        }
    }

    private Object createJsonElement(Object o) {
        return o;
    }

    private JSONArray toJsonArray(Object o) {
        return (JSONArray) o;
    }

    private JSONObject toJsonObject(Object o) {
        return (JSONObject) o;
    }
}
