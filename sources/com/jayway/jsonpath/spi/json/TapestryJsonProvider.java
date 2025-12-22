package com.jayway.jsonpath.spi.json;

import com.jayway.jsonpath.InvalidJsonException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Scanner;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONCollection;
import org.apache.tapestry5.json.JSONObject;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/TapestryJsonProvider.class */
public class TapestryJsonProvider extends AbstractJsonProvider {
    public static final TapestryJsonProvider INSTANCE = new TapestryJsonProvider();

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(String json) throws InvalidJsonException {
        return new JSONObject(json);
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(InputStream jsonStream, String charset) throws InvalidJsonException {
        Scanner sc = null;
        try {
            sc = new Scanner(jsonStream, charset);
            Object obj = parse(sc.useDelimiter("\\A").next());
            if (sc != null) {
                sc.close();
            }
            return obj;
        } catch (Throwable th) {
            if (sc != null) {
                sc.close();
            }
            throw th;
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public String toJson(Object obj) {
        return ((JSONCollection) obj).toCompactString();
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
    public void setProperty(Object obj, Object key, Object value) {
        Object v = value == null ? JSONObject.NULL : value;
        if (isMap(obj)) {
            ((JSONObject) obj).put(key.toString(), v);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isMap(Object obj) {
        return obj instanceof JSONObject;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getArrayIndex(Object obj, int idx) {
        return ((JSONArray) obj).get(idx);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Collection<String> getPropertyKeys(Object obj) {
        return ((JSONObject) obj).keys();
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getMapValue(Object obj, String key) {
        JSONObject json = (JSONObject) obj;
        if (!json.has(key)) {
            return UNDEFINED;
        }
        return json.get(key);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public int length(Object obj) {
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).length();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).length();
        }
        throw new IllegalArgumentException("Cannot determine length of " + obj + ", unsupported type.");
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isArray(Object obj) {
        return obj instanceof JSONArray;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setArrayIndex(Object array, int index, Object newValue) {
        Object v = newValue == null ? JSONObject.NULL : newValue;
        JSONArray list = (JSONArray) array;
        list.put(index, v);
    }
}
