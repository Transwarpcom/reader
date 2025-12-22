package org.bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.bson.types.BasicBSONList;
import org.bson.types.ObjectId;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/BasicBSONObject.class */
public class BasicBSONObject extends LinkedHashMap<String, Object> implements BSONObject {
    private static final long serialVersionUID = -4415279469780082174L;

    @Override // org.bson.BSONObject
    public /* bridge */ /* synthetic */ Object put(String x0, Object x1) {
        return super.put((BasicBSONObject) x0, (String) x1);
    }

    public BasicBSONObject() {
    }

    public BasicBSONObject(int size) {
        super(size);
    }

    public BasicBSONObject(String key, Object value) {
        put((Object) key, value);
    }

    public BasicBSONObject(Map map) {
        super(map);
    }

    @Override // org.bson.BSONObject
    public Map toMap() {
        return new LinkedHashMap(this);
    }

    @Override // org.bson.BSONObject
    public Object removeField(String key) {
        return remove(key);
    }

    @Override // org.bson.BSONObject
    public boolean containsField(String field) {
        return super.containsKey((Object) field);
    }

    @Override // org.bson.BSONObject
    @Deprecated
    public boolean containsKey(String key) {
        return containsField(key);
    }

    @Override // org.bson.BSONObject
    public Object get(String key) {
        return super.get((Object) key);
    }

    public int getInt(String key) {
        Object o = get(key);
        if (o == null) {
            throw new NullPointerException("no value for: " + key);
        }
        return toInt(o);
    }

    public int getInt(String key, int def) {
        Object foo = get(key);
        if (foo == null) {
            return def;
        }
        return toInt(foo);
    }

    public long getLong(String key) {
        Object foo = get(key);
        return ((Number) foo).longValue();
    }

    public long getLong(String key, long def) {
        Object foo = get(key);
        if (foo == null) {
            return def;
        }
        return ((Number) foo).longValue();
    }

    public double getDouble(String key) {
        Object foo = get(key);
        return ((Number) foo).doubleValue();
    }

    public double getDouble(String key, double def) {
        Object foo = get(key);
        if (foo == null) {
            return def;
        }
        return ((Number) foo).doubleValue();
    }

    public String getString(String key) {
        Object foo = get(key);
        if (foo == null) {
            return null;
        }
        return foo.toString();
    }

    public String getString(String key, String def) {
        Object foo = get(key);
        if (foo == null) {
            return def;
        }
        return foo.toString();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        Object foo = get(key);
        if (foo == null) {
            return def;
        }
        if (foo instanceof Number) {
            return ((Number) foo).intValue() > 0;
        }
        if (foo instanceof Boolean) {
            return ((Boolean) foo).booleanValue();
        }
        throw new IllegalArgumentException("can't coerce to bool:" + foo.getClass());
    }

    public ObjectId getObjectId(String field) {
        return (ObjectId) get(field);
    }

    public ObjectId getObjectId(String field, ObjectId def) {
        Object foo = get(field);
        return foo != null ? (ObjectId) foo : def;
    }

    public Date getDate(String field) {
        return (Date) get(field);
    }

    public Date getDate(String field, Date def) {
        Object foo = get(field);
        return foo != null ? (Date) foo : def;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map, org.bson.BSONObject
    public void putAll(Map m) {
        for (Map.Entry entry : m.entrySet()) {
            put((Object) entry.getKey().toString(), entry.getValue());
        }
    }

    @Override // org.bson.BSONObject
    public void putAll(BSONObject o) {
        for (String k : o.keySet()) {
            put((Object) k, o.get(k));
        }
    }

    public BasicBSONObject append(String key, Object val) {
        put((Object) key, val);
        return this;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BSONObject)) {
            return false;
        }
        BSONObject other = (BSONObject) o;
        if (!keySet().equals(other.keySet())) {
            return false;
        }
        return Arrays.equals(getEncoder().encode(canonicalizeBSONObject(this)), getEncoder().encode(canonicalizeBSONObject(other)));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        return Arrays.hashCode(canonicalizeBSONObject(this).encode());
    }

    private byte[] encode() {
        return getEncoder().encode(this);
    }

    private BSONEncoder getEncoder() {
        return new BasicBSONEncoder();
    }

    private static Object canonicalize(Object from) {
        if ((from instanceof BSONObject) && !(from instanceof BasicBSONList)) {
            return canonicalizeBSONObject((BSONObject) from);
        }
        if (from instanceof List) {
            return canonicalizeList((List) from);
        }
        if (from instanceof Map) {
            return canonicalizeMap((Map) from);
        }
        return from;
    }

    private static Map<String, Object> canonicalizeMap(Map<String, Object> from) {
        Map<String, Object> canonicalized = new LinkedHashMap<>(from.size());
        TreeSet<String> keysInOrder = new TreeSet<>(from.keySet());
        Iterator<String> it = keysInOrder.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object val = from.get(key);
            canonicalized.put(key, canonicalize(val));
        }
        return canonicalized;
    }

    private static BasicBSONObject canonicalizeBSONObject(BSONObject from) {
        BasicBSONObject canonicalized = new BasicBSONObject();
        TreeSet<String> keysInOrder = new TreeSet<>(from.keySet());
        Iterator<String> it = keysInOrder.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object val = from.get(key);
            canonicalized.put((Object) key, canonicalize(val));
        }
        return canonicalized;
    }

    private static List canonicalizeList(List<Object> list) {
        List<Object> canonicalized = new ArrayList<>(list.size());
        for (Object cur : list) {
            canonicalized.add(canonicalize(cur));
        }
        return canonicalized;
    }

    private int toInt(Object o) {
        if (o instanceof Number) {
            return ((Number) o).intValue();
        }
        if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue() ? 1 : 0;
        }
        throw new IllegalArgumentException("can't convert: " + o.getClass().getName() + " to int");
    }
}
