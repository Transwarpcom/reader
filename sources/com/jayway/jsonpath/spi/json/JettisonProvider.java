package com.jayway.jsonpath.spi.json;

import com.jayway.jsonpath.InvalidJsonException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JettisonProvider.class */
public class JettisonProvider extends AbstractJsonProvider {
    /* JADX INFO: Access modifiers changed from: private */
    public static Object jettisonUnwrap(Object obj) {
        if (obj != null && obj.equals(JSONObject.NULL)) {
            return null;
        }
        return obj;
    }

    private static Object jettisonWrap(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        return obj;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JettisonProvider$JettisonTokener.class */
    private static class JettisonTokener extends JSONTokener {
        public JettisonTokener(String s) {
            super(s);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newJSONObject, reason: merged with bridge method [inline-methods] */
        public JettisonObject m941newJSONObject() throws JSONException {
            return new JettisonObject(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: newJSONArray, reason: merged with bridge method [inline-methods] */
        public JettisonArray m940newJSONArray() throws JSONException {
            return new JettisonArray(this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JettisonProvider$JettisonObject.class */
    private static class JettisonObject extends JSONObject implements Iterable<Object> {
        private static final long serialVersionUID = 1;

        private JettisonObject(JettisonTokener tokener) throws JSONException {
            super(tokener);
        }

        private JettisonObject() {
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return new JettisonObjectIterator(this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JettisonProvider$JettisonArray.class */
    private static class JettisonArray extends JSONArray implements Iterable<Object> {
        private static final long serialVersionUID = 2;

        private JettisonArray(JettisonTokener tokener) throws JSONException {
            super(tokener);
        }

        private JettisonArray() {
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return new JettisonArrayIterator(this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JettisonProvider$JettisonArrayIterator.class */
    private static class JettisonArrayIterator implements Iterator<Object> {
        private final JSONArray jsonArray;
        private int index;

        private JettisonArrayIterator(JSONArray jsonArray) {
            this.index = 0;
            this.jsonArray = jsonArray;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.jsonArray.length();
        }

        @Override // java.util.Iterator
        public Object next() {
            try {
                JSONArray jSONArray = this.jsonArray;
                int i = this.index;
                this.index = i + 1;
                return JettisonProvider.jettisonUnwrap(jSONArray.get(i));
            } catch (JSONException jsonException) {
                throw new NoSuchElementException(jsonException.toString());
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/json/JettisonProvider$JettisonObjectIterator.class */
    private static class JettisonObjectIterator implements Iterator<Object> {
        private final JSONObject jsonObject;
        private final Iterator<?> jsonKeysIt;

        private JettisonObjectIterator(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
            this.jsonKeysIt = jsonObject.keys();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.jsonKeysIt.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            try {
                return JettisonProvider.jettisonUnwrap(this.jsonObject.get(String.valueOf(this.jsonKeysIt.next())));
            } catch (JSONException jsonException) {
                throw new NoSuchElementException(jsonException.toString());
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            this.jsonKeysIt.remove();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.codehaus.jettison.json.JSONException */
    private Object parse(JettisonTokener JsonTokener) throws JSONException {
        try {
            char nextChar = JsonTokener.nextClean();
            JsonTokener.back();
            if (nextChar == '{') {
                return new JettisonObject(JsonTokener);
            }
            if (nextChar == '[') {
                return new JettisonArray(JsonTokener);
            }
            throw new JSONException("Invalid JSON");
        } catch (JSONException jsonException) {
            throw new IllegalStateException((Throwable) jsonException);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(String json) throws InvalidJsonException {
        return parse(new JettisonTokener(json));
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object parse(InputStream jsonStream, String charset) throws IOException, InvalidJsonException {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int size = jsonStream.read(buffer);
                if (size > 0) {
                    stream.write(buffer, 0, size);
                } else {
                    return parse(new JettisonTokener(new String(stream.toByteArray(), charset)));
                }
            }
        } catch (IOException ioe) {
            throw new InvalidJsonException(ioe);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public String toJson(Object obj) {
        try {
            if (obj instanceof JSONArray) {
                return ((JSONArray) obj).toString(2);
            }
            if (obj instanceof JSONObject) {
                return ((JSONObject) obj).toString(2);
            }
            return String.valueOf(obj);
        } catch (JSONException jsonException) {
            throw new IllegalStateException((Throwable) jsonException);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Object createMap() {
        return new JettisonObject();
    }

    @Override // com.jayway.jsonpath.spi.json.JsonProvider
    public Iterable<?> createArray() {
        return new JettisonArray();
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object unwrap(Object obj) {
        return jettisonUnwrap(obj);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isArray(Object obj) {
        return obj instanceof JSONArray;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public boolean isMap(Object obj) {
        return obj instanceof JSONObject;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public int length(Object obj) {
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).length();
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).length();
        }
        if (obj instanceof String) {
            return ((String) obj).length();
        }
        return 0;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Iterable<?> toIterable(final Object obj) {
        return new Iterable<Object>() { // from class: com.jayway.jsonpath.spi.json.JettisonProvider.1
            @Override // java.lang.Iterable
            public Iterator<Object> iterator() {
                if (obj instanceof JSONArray) {
                    return new JettisonArrayIterator((JSONArray) obj);
                }
                if (obj instanceof JSONObject) {
                    return new JettisonObjectIterator((JSONObject) obj);
                }
                return Collections.emptyList().iterator();
            }
        };
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Collection<String> getPropertyKeys(Object obj) {
        List<String> keys = new ArrayList<>(length(obj));
        if (obj instanceof JSONArray) {
            for (int i = 0; i < length(obj); i++) {
                keys.add(String.valueOf(i));
            }
        }
        if (obj instanceof JSONObject) {
            Iterator<?> keysIt = ((JSONObject) obj).keys();
            while (keysIt.hasNext()) {
                keys.add(String.valueOf(keysIt.next()));
            }
        }
        return keys;
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getArrayIndex(Object obj, int index) {
        return jettisonUnwrap(((JSONArray) obj).opt(index));
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setArrayIndex(Object array, int index, Object value) {
        if (!isArray(array)) {
            throw new UnsupportedOperationException();
        }
        try {
            ((JSONArray) array).put(index, jettisonWrap(value));
        } catch (JSONException jsonException) {
            throw new IllegalArgumentException((Throwable) jsonException);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public Object getMapValue(Object obj, String key) {
        Object value = ((JSONObject) obj).opt(key);
        if (value == null) {
            return JsonProvider.UNDEFINED;
        }
        return jettisonUnwrap(value);
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void setProperty(Object obj, Object key, Object value) {
        try {
            if (obj instanceof JSONArray) {
                int index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
                ((JSONArray) obj).put(index, jettisonWrap(value));
            }
            if (obj instanceof JSONObject) {
                ((JSONObject) obj).put(String.valueOf(key), jettisonWrap(value));
            }
        } catch (JSONException jsonException) {
            throw new IllegalStateException((Throwable) jsonException);
        }
    }

    @Override // com.jayway.jsonpath.spi.json.AbstractJsonProvider, com.jayway.jsonpath.spi.json.JsonProvider
    public void removeProperty(Object obj, Object key) {
        try {
            if (obj instanceof JSONArray) {
                int index = key instanceof Integer ? ((Integer) key).intValue() : Integer.parseInt(key.toString());
                if (index < length(obj)) {
                    Object temp = new Object();
                    ((JSONArray) obj).put(index, temp);
                    ((JSONArray) obj).remove(temp);
                }
            }
            if (obj instanceof JSONObject) {
                ((JSONObject) obj).remove(String.valueOf(key));
            }
        } catch (JSONException jsonException) {
            throw new IllegalStateException((Throwable) jsonException);
        }
    }
}
