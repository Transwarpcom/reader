package io.vertx.core.json;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.Shareable;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import io.vertx.core.spi.json.JsonCodec;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/JsonObject.class */
public class JsonObject implements Iterable<Map.Entry<String, Object>>, ClusterSerializable, Shareable {
    private Map<String, Object> map;

    public JsonObject(String json) {
        if (json == null) {
            throw new NullPointerException();
        }
        fromJson(json);
        if (this.map == null) {
            throw new DecodeException("Invalid JSON object: " + json);
        }
    }

    public JsonObject() {
        this.map = new LinkedHashMap();
    }

    public JsonObject(Map<String, Object> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.map = map;
    }

    public JsonObject(Buffer buf) {
        if (buf == null) {
            throw new NullPointerException();
        }
        fromBuffer(buf);
        if (this.map == null) {
            throw new DecodeException("Invalid JSON object: " + buf);
        }
    }

    public static JsonObject mapFrom(Object obj) {
        if (obj == null) {
            return null;
        }
        return new JsonObject((Map<String, Object>) JsonCodec.INSTANCE.fromValue(obj, Map.class));
    }

    public <T> T mapTo(Class<T> cls) {
        return (T) JsonCodec.INSTANCE.fromValue(this.map, cls);
    }

    public String getString(String key) {
        Objects.requireNonNull(key);
        CharSequence cs = (CharSequence) this.map.get(key);
        if (cs == null) {
            return null;
        }
        return cs.toString();
    }

    public Integer getInteger(String key) {
        Objects.requireNonNull(key);
        Number number = (Number) this.map.get(key);
        if (number == null) {
            return null;
        }
        if (number instanceof Integer) {
            return (Integer) number;
        }
        return Integer.valueOf(number.intValue());
    }

    public Long getLong(String key) {
        Objects.requireNonNull(key);
        Number number = (Number) this.map.get(key);
        if (number == null) {
            return null;
        }
        if (number instanceof Long) {
            return (Long) number;
        }
        return Long.valueOf(number.longValue());
    }

    public Double getDouble(String key) {
        Objects.requireNonNull(key);
        Number number = (Number) this.map.get(key);
        if (number == null) {
            return null;
        }
        if (number instanceof Double) {
            return (Double) number;
        }
        return Double.valueOf(number.doubleValue());
    }

    public Float getFloat(String key) {
        Objects.requireNonNull(key);
        Number number = (Number) this.map.get(key);
        if (number == null) {
            return null;
        }
        if (number instanceof Float) {
            return (Float) number;
        }
        return Float.valueOf(number.floatValue());
    }

    public Boolean getBoolean(String key) {
        Objects.requireNonNull(key);
        return (Boolean) this.map.get(key);
    }

    public JsonObject getJsonObject(String key) {
        Objects.requireNonNull(key);
        Object val = this.map.get(key);
        if (val instanceof Map) {
            val = new JsonObject((Map<String, Object>) val);
        }
        return (JsonObject) val;
    }

    public JsonArray getJsonArray(String key) {
        Objects.requireNonNull(key);
        Object val = this.map.get(key);
        if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return (JsonArray) val;
    }

    public byte[] getBinary(String key) {
        Objects.requireNonNull(key);
        String encoded = (String) this.map.get(key);
        if (encoded == null) {
            return null;
        }
        return Base64.getDecoder().decode(encoded);
    }

    public Instant getInstant(String key) {
        Objects.requireNonNull(key);
        String encoded = (String) this.map.get(key);
        if (encoded == null) {
            return null;
        }
        return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(encoded));
    }

    public Object getValue(String key) {
        Objects.requireNonNull(key);
        Object val = this.map.get(key);
        if (val instanceof Map) {
            val = new JsonObject((Map<String, Object>) val);
        } else if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return val;
    }

    public String getString(String key, String def) {
        Objects.requireNonNull(key);
        CharSequence cs = (CharSequence) this.map.get(key);
        if (cs == null && !this.map.containsKey(key)) {
            return def;
        }
        if (cs == null) {
            return null;
        }
        return cs.toString();
    }

    public Integer getInteger(String key, Integer def) {
        Objects.requireNonNull(key);
        Number val = (Number) this.map.get(key);
        if (val == null) {
            if (this.map.containsKey(key)) {
                return null;
            }
            return def;
        }
        if (val instanceof Integer) {
            return (Integer) val;
        }
        return Integer.valueOf(val.intValue());
    }

    public Long getLong(String key, Long def) {
        Objects.requireNonNull(key);
        Number val = (Number) this.map.get(key);
        if (val == null) {
            if (this.map.containsKey(key)) {
                return null;
            }
            return def;
        }
        if (val instanceof Long) {
            return (Long) val;
        }
        return Long.valueOf(val.longValue());
    }

    public Double getDouble(String key, Double def) {
        Objects.requireNonNull(key);
        Number val = (Number) this.map.get(key);
        if (val == null) {
            if (this.map.containsKey(key)) {
                return null;
            }
            return def;
        }
        if (val instanceof Double) {
            return (Double) val;
        }
        return Double.valueOf(val.doubleValue());
    }

    public Float getFloat(String key, Float def) {
        Objects.requireNonNull(key);
        Number val = (Number) this.map.get(key);
        if (val == null) {
            if (this.map.containsKey(key)) {
                return null;
            }
            return def;
        }
        if (val instanceof Float) {
            return (Float) val;
        }
        return Float.valueOf(val.floatValue());
    }

    public Boolean getBoolean(String key, Boolean def) {
        Objects.requireNonNull(key);
        Object val = this.map.get(key);
        return (val != null || this.map.containsKey(key)) ? (Boolean) val : def;
    }

    public JsonObject getJsonObject(String key, JsonObject def) {
        JsonObject val = getJsonObject(key);
        return (val != null || this.map.containsKey(key)) ? val : def;
    }

    public JsonArray getJsonArray(String key, JsonArray def) {
        JsonArray val = getJsonArray(key);
        return (val != null || this.map.containsKey(key)) ? val : def;
    }

    public byte[] getBinary(String key, byte[] def) {
        Objects.requireNonNull(key);
        Object val = this.map.get(key);
        if (val == null && !this.map.containsKey(key)) {
            return def;
        }
        if (val == null) {
            return null;
        }
        return Base64.getDecoder().decode((String) val);
    }

    public Instant getInstant(String key, Instant def) {
        Objects.requireNonNull(key);
        Object val = this.map.get(key);
        if (val == null && !this.map.containsKey(key)) {
            return def;
        }
        if (val == null) {
            return null;
        }
        return Instant.from(DateTimeFormatter.ISO_INSTANT.parse((String) val));
    }

    public Object getValue(String key, Object def) {
        Objects.requireNonNull(key);
        Object val = getValue(key);
        return (val != null || this.map.containsKey(key)) ? val : def;
    }

    public boolean containsKey(String key) {
        Objects.requireNonNull(key);
        return this.map.containsKey(key);
    }

    public Set<String> fieldNames() {
        return this.map.keySet();
    }

    public JsonObject put(String key, Enum value) {
        Objects.requireNonNull(key);
        this.map.put(key, value == null ? null : value.name());
        return this;
    }

    public JsonObject put(String key, CharSequence value) {
        Objects.requireNonNull(key);
        this.map.put(key, value == null ? null : value.toString());
        return this;
    }

    public JsonObject put(String key, String value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, Integer value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, Long value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, Double value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, Float value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, Boolean value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject putNull(String key) {
        Objects.requireNonNull(key);
        this.map.put(key, null);
        return this;
    }

    public JsonObject put(String key, JsonObject value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, JsonArray value) {
        Objects.requireNonNull(key);
        this.map.put(key, value);
        return this;
    }

    public JsonObject put(String key, byte[] value) {
        Objects.requireNonNull(key);
        this.map.put(key, value == null ? null : Base64.getEncoder().encodeToString(value));
        return this;
    }

    public JsonObject put(String key, Instant value) {
        Objects.requireNonNull(key);
        this.map.put(key, value == null ? null : DateTimeFormatter.ISO_INSTANT.format(value));
        return this;
    }

    public JsonObject put(String key, Object value) {
        Objects.requireNonNull(key);
        this.map.put(key, checkAndCopy(value, false));
        return this;
    }

    public Object remove(String key) {
        return this.map.remove(key);
    }

    public JsonObject mergeIn(JsonObject other) {
        return mergeIn(other, false);
    }

    public JsonObject mergeIn(JsonObject other, boolean deep) {
        return mergeIn(other, deep ? Integer.MAX_VALUE : 1);
    }

    public JsonObject mergeIn(JsonObject other, int depth) {
        if (depth < 1) {
            return this;
        }
        if (depth == 1) {
            this.map.putAll(other.map);
            return this;
        }
        for (Map.Entry<String, Object> e : other.map.entrySet()) {
            if (e.getValue() == null) {
                this.map.put(e.getKey(), null);
            } else {
                this.map.merge(e.getKey(), e.getValue(), (oldVal, newVal) -> {
                    if (oldVal instanceof Map) {
                        oldVal = new JsonObject((Map<String, Object>) oldVal);
                    }
                    if (newVal instanceof Map) {
                        newVal = new JsonObject((Map<String, Object>) newVal);
                    }
                    if ((oldVal instanceof JsonObject) && (newVal instanceof JsonObject)) {
                        return ((JsonObject) oldVal).mergeIn((JsonObject) newVal, depth - 1);
                    }
                    return newVal;
                });
            }
        }
        return this;
    }

    public String encode() {
        return JsonCodec.INSTANCE.toString(this.map, false);
    }

    public String encodePrettily() {
        return JsonCodec.INSTANCE.toString(this.map, true);
    }

    public Buffer toBuffer() {
        return JsonCodec.INSTANCE.toBuffer(this.map, false);
    }

    @Override // io.vertx.core.shareddata.Shareable
    public JsonObject copy() {
        Map<String, Object> copiedMap;
        if (this.map instanceof LinkedHashMap) {
            copiedMap = new LinkedHashMap<>(this.map.size());
        } else {
            copiedMap = new HashMap<>(this.map.size());
        }
        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            Object val = entry.getValue();
            copiedMap.put(entry.getKey(), checkAndCopy(val, true));
        }
        return new JsonObject(copiedMap);
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public Stream<Map.Entry<String, Object>> stream() {
        return asStream(iterator());
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<String, Object>> iterator() {
        return new Iter(this.map.entrySet().iterator());
    }

    public int size() {
        return this.map.size();
    }

    @Fluent
    public JsonObject clear() {
        this.map.clear();
        return this;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public String toString() {
        return encode();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return objectEquals(this.map, o);
    }

    private static boolean objectEquals(Map<?, ?> m1, Object o2) {
        Map<?, ?> m2;
        if (o2 instanceof JsonObject) {
            m2 = ((JsonObject) o2).map;
        } else if (o2 instanceof Map) {
            m2 = (Map) o2;
        } else {
            return false;
        }
        if (!m1.keySet().equals(m2.keySet())) {
            return false;
        }
        for (Map.Entry<?, ?> entry : m1.entrySet()) {
            Object val1 = entry.getValue();
            Object val2 = m2.get(entry.getKey());
            if (val1 == null) {
                if (val2 != null) {
                    return false;
                }
            } else if (!equals(val1, val2)) {
                return false;
            }
        }
        return true;
    }

    static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 instanceof JsonObject) {
            return objectEquals(((JsonObject) o1).map, o2);
        }
        if (o1 instanceof Map) {
            return objectEquals((Map) o1, o2);
        }
        if (o1 instanceof JsonArray) {
            return JsonArray.arrayEquals(((JsonArray) o1).getList(), o2);
        }
        if (o1 instanceof List) {
            return JsonArray.arrayEquals((List) o1, o2);
        }
        if ((o1 instanceof Number) && (o2 instanceof Number) && o1.getClass() != o2.getClass()) {
            Number n1 = (Number) o1;
            Number n2 = (Number) o2;
            return ((o1 instanceof Float) || (o1 instanceof Double) || (o2 instanceof Float) || (o2 instanceof Double)) ? n1.doubleValue() == n2.doubleValue() : n1.longValue() == n2.longValue();
        }
        return o1.equals(o2);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public void writeToBuffer(Buffer buffer) {
        String encoded = encode();
        byte[] bytes = encoded.getBytes(StandardCharsets.UTF_8);
        buffer.appendInt(bytes.length);
        buffer.appendBytes(bytes);
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public int readFromBuffer(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int start = pos + 4;
        String encoded = buffer.getString(start, start + length);
        fromJson(encoded);
        return pos + length + 4;
    }

    private void fromJson(String json) {
        this.map = (Map) JsonCodec.INSTANCE.fromString(json, Map.class);
    }

    private void fromBuffer(Buffer buf) {
        this.map = (Map) JsonCodec.INSTANCE.fromBuffer(buf, Map.class);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/JsonObject$Iter.class */
    private class Iter implements Iterator<Map.Entry<String, Object>> {
        final Iterator<Map.Entry<String, Object>> mapIter;

        Iter(Iterator<Map.Entry<String, Object>> mapIter) {
            this.mapIter = mapIter;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.mapIter.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Map.Entry<String, Object> next() {
            Map.Entry<String, Object> entry = this.mapIter.next();
            if (entry.getValue() instanceof Map) {
                return new Entry(entry.getKey(), new JsonObject((Map<String, Object>) entry.getValue()));
            }
            if (entry.getValue() instanceof List) {
                return new Entry(entry.getKey(), new JsonArray((List) entry.getValue()));
            }
            return entry;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.mapIter.remove();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/JsonObject$Entry.class */
    private static final class Entry implements Map.Entry<String, Object> {
        final String key;
        final Object value;

        public Entry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public String getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object value) {
            throw new UnsupportedOperationException();
        }
    }

    static Object checkAndCopy(Object val, boolean copy) {
        if (val != null && ((!(val instanceof Number) || (val instanceof BigDecimal)) && !(val instanceof Boolean) && !(val instanceof String) && !(val instanceof Character))) {
            if (val instanceof CharSequence) {
                val = val.toString();
            } else if (val instanceof JsonObject) {
                if (copy) {
                    val = ((JsonObject) val).copy();
                }
            } else if (val instanceof JsonArray) {
                if (copy) {
                    val = ((JsonArray) val).copy();
                }
            } else if (val instanceof Map) {
                if (copy) {
                    val = new JsonObject((Map<String, Object>) val).copy();
                } else {
                    val = new JsonObject((Map<String, Object>) val);
                }
            } else if (val instanceof List) {
                if (copy) {
                    val = new JsonArray((List) val).copy();
                } else {
                    val = new JsonArray((List) val);
                }
            } else if (val instanceof byte[]) {
                val = Base64.getEncoder().encodeToString((byte[]) val);
            } else if (val instanceof Instant) {
                val = DateTimeFormatter.ISO_INSTANT.format((Instant) val);
            } else {
                throw new IllegalStateException("Illegal type in JsonObject: " + val.getClass());
            }
        }
        return val;
    }

    static <T> Stream<T> asStream(Iterator<T> sourceIterator) {
        Iterable<T> iterable = () -> {
            return sourceIterator;
        };
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
