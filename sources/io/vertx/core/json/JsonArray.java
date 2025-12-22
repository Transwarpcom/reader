package io.vertx.core.json;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.Shareable;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import io.vertx.core.spi.json.JsonCodec;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/JsonArray.class */
public class JsonArray implements Iterable<Object>, ClusterSerializable, Shareable {
    private List<Object> list;

    public JsonArray(String json) {
        if (json == null) {
            throw new NullPointerException();
        }
        fromJson(json);
        if (this.list == null) {
            throw new DecodeException("Invalid JSON array: " + json);
        }
    }

    public JsonArray() {
        this.list = new ArrayList();
    }

    public JsonArray(List list) {
        if (list == null) {
            throw new NullPointerException();
        }
        this.list = list;
    }

    public JsonArray(Buffer buf) {
        if (buf == null) {
            throw new NullPointerException();
        }
        fromBuffer(buf);
        if (this.list == null) {
            throw new DecodeException("Invalid JSON array: " + buf);
        }
    }

    public String getString(int pos) {
        CharSequence cs = (CharSequence) this.list.get(pos);
        if (cs == null) {
            return null;
        }
        return cs.toString();
    }

    public Integer getInteger(int pos) {
        Number number = (Number) this.list.get(pos);
        if (number == null) {
            return null;
        }
        if (number instanceof Integer) {
            return (Integer) number;
        }
        return Integer.valueOf(number.intValue());
    }

    public Long getLong(int pos) {
        Number number = (Number) this.list.get(pos);
        if (number == null) {
            return null;
        }
        if (number instanceof Long) {
            return (Long) number;
        }
        return Long.valueOf(number.longValue());
    }

    public Double getDouble(int pos) {
        Number number = (Number) this.list.get(pos);
        if (number == null) {
            return null;
        }
        if (number instanceof Double) {
            return (Double) number;
        }
        return Double.valueOf(number.doubleValue());
    }

    public Float getFloat(int pos) {
        Number number = (Number) this.list.get(pos);
        if (number == null) {
            return null;
        }
        if (number instanceof Float) {
            return (Float) number;
        }
        return Float.valueOf(number.floatValue());
    }

    public Boolean getBoolean(int pos) {
        return (Boolean) this.list.get(pos);
    }

    public JsonObject getJsonObject(int pos) {
        Object val = this.list.get(pos);
        if (val instanceof Map) {
            val = new JsonObject((Map<String, Object>) val);
        }
        return (JsonObject) val;
    }

    public JsonArray getJsonArray(int pos) {
        Object val = this.list.get(pos);
        if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return (JsonArray) val;
    }

    public byte[] getBinary(int pos) {
        String val = (String) this.list.get(pos);
        if (val == null) {
            return null;
        }
        return Base64.getDecoder().decode(val);
    }

    public Instant getInstant(int pos) {
        String val = (String) this.list.get(pos);
        if (val == null) {
            return null;
        }
        return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(val));
    }

    public Object getValue(int pos) {
        Object val = this.list.get(pos);
        if (val instanceof Map) {
            val = new JsonObject((Map<String, Object>) val);
        } else if (val instanceof List) {
            val = new JsonArray((List) val);
        }
        return val;
    }

    public boolean hasNull(int pos) {
        return this.list.get(pos) == null;
    }

    public JsonArray add(Enum value) {
        this.list.add(value != null ? value.name() : null);
        return this;
    }

    public JsonArray add(CharSequence value) {
        this.list.add(value != null ? value.toString() : null);
        return this;
    }

    public JsonArray add(String value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(Integer value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(Long value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(Double value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(Float value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(Boolean value) {
        this.list.add(value);
        return this;
    }

    public JsonArray addNull() {
        this.list.add(null);
        return this;
    }

    public JsonArray add(JsonObject value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(JsonArray value) {
        this.list.add(value);
        return this;
    }

    public JsonArray add(byte[] value) {
        this.list.add(value != null ? Base64.getEncoder().encodeToString(value) : null);
        return this;
    }

    public JsonArray add(Instant value) {
        this.list.add(value != null ? DateTimeFormatter.ISO_INSTANT.format(value) : null);
        return this;
    }

    public JsonArray add(Object value) {
        this.list.add(JsonObject.checkAndCopy(value, false));
        return this;
    }

    public JsonArray addAll(JsonArray array) {
        this.list.addAll(array.list);
        return this;
    }

    public JsonArray set(int pos, Enum value) {
        this.list.set(pos, value != null ? value.name() : null);
        return this;
    }

    public JsonArray set(int pos, CharSequence value) {
        this.list.set(pos, value != null ? value.toString() : null);
        return this;
    }

    public JsonArray set(int pos, String value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, Integer value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, Long value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, Double value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, Float value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, Boolean value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray setNull(int pos) {
        this.list.set(pos, null);
        return this;
    }

    public JsonArray set(int pos, JsonObject value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, JsonArray value) {
        this.list.set(pos, value);
        return this;
    }

    public JsonArray set(int pos, byte[] value) {
        this.list.set(pos, value != null ? Base64.getEncoder().encodeToString(value) : null);
        return this;
    }

    public JsonArray set(int pos, Instant value) {
        this.list.set(pos, value != null ? DateTimeFormatter.ISO_INSTANT.format(value) : null);
        return this;
    }

    public JsonArray set(int pos, Object value) {
        this.list.set(pos, JsonObject.checkAndCopy(value, false));
        return this;
    }

    public boolean contains(Object value) {
        return this.list.contains(value);
    }

    public boolean remove(Object value) {
        return this.list.remove(value);
    }

    public Object remove(int pos) {
        Object removed = this.list.remove(pos);
        if (removed instanceof Map) {
            return new JsonObject((Map<String, Object>) removed);
        }
        if (removed instanceof ArrayList) {
            return new JsonArray((List) removed);
        }
        return removed;
    }

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public List getList() {
        return this.list;
    }

    public JsonArray clear() {
        this.list.clear();
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return new Iter(this.list.iterator());
    }

    public String encode() {
        return JsonCodec.INSTANCE.toString(this.list, false);
    }

    public Buffer toBuffer() {
        return JsonCodec.INSTANCE.toBuffer(this.list, false);
    }

    public String encodePrettily() {
        return JsonCodec.INSTANCE.toString(this.list, true);
    }

    @Override // io.vertx.core.shareddata.Shareable
    public JsonArray copy() {
        List<Object> copiedList = new ArrayList<>(this.list.size());
        for (Object val : this.list) {
            copiedList.add(JsonObject.checkAndCopy(val, true));
        }
        return new JsonArray(copiedList);
    }

    public Stream<Object> stream() {
        return JsonObject.asStream(iterator());
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
        return arrayEquals(this.list, o);
    }

    static boolean arrayEquals(List<?> l1, Object o2) {
        List<?> l2;
        if (o2 instanceof JsonArray) {
            l2 = ((JsonArray) o2).list;
        } else if (o2 instanceof List) {
            l2 = (List) o2;
        } else {
            return false;
        }
        if (l1.size() != l2.size()) {
            return false;
        }
        Iterator<?> iter = l2.iterator();
        for (Object entry : l1) {
            Object other = iter.next();
            if (entry == null) {
                if (other != null) {
                    return false;
                }
            } else if (!JsonObject.equals(entry, other)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public void writeToBuffer(Buffer buffer) {
        String encoded = encode();
        byte[] bytes = encoded.getBytes();
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
        this.list = (List) JsonCodec.INSTANCE.fromString(json, List.class);
    }

    private void fromBuffer(Buffer buf) {
        this.list = (List) JsonCodec.INSTANCE.fromBuffer(buf, List.class);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/json/JsonArray$Iter.class */
    private class Iter implements Iterator<Object> {
        final Iterator<Object> listIter;

        Iter(Iterator<Object> listIter) {
            this.listIter = listIter;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.listIter.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            Object val = this.listIter.next();
            if (val instanceof Map) {
                val = new JsonObject((Map<String, Object>) val);
            } else if (val instanceof List) {
                val = new JsonArray((List) val);
            }
            return val;
        }

        @Override // java.util.Iterator
        public void remove() {
            this.listIter.remove();
        }
    }
}
