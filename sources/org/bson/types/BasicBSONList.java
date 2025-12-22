package org.bson.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bson.BSONObject;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/BasicBSONList.class */
public class BasicBSONList extends ArrayList<Object> implements BSONObject {
    private static final long serialVersionUID = -4415279469780082174L;

    @Override // org.bson.BSONObject
    public Object put(String key, Object v) {
        return put(_getInt(key), v);
    }

    public Object put(int key, Object value) {
        while (key >= size()) {
            add(null);
        }
        set(key, value);
        return value;
    }

    @Override // org.bson.BSONObject, java.util.AbstractMap, java.util.Map
    public void putAll(Map m) {
        for (Map.Entry entry : m.entrySet()) {
            put(entry.getKey().toString(), entry.getValue());
        }
    }

    @Override // org.bson.BSONObject
    public void putAll(BSONObject o) {
        for (String k : o.keySet()) {
            put(k, o.get(k));
        }
    }

    @Override // org.bson.BSONObject
    public Object get(String key) {
        int i = _getInt(key);
        if (i < 0 || i >= size()) {
            return null;
        }
        return get(i);
    }

    @Override // org.bson.BSONObject
    public Object removeField(String key) {
        int i = _getInt(key);
        if (i < 0 || i >= size()) {
            return null;
        }
        return remove(i);
    }

    @Override // org.bson.BSONObject
    @Deprecated
    public boolean containsKey(String key) {
        return containsField(key);
    }

    @Override // org.bson.BSONObject
    public boolean containsField(String key) {
        int i = _getInt(key, false);
        return i >= 0 && i >= 0 && i < size();
    }

    @Override // org.bson.BSONObject
    public Set<String> keySet() {
        return new StringRangeSet(size());
    }

    @Override // org.bson.BSONObject
    public Map toMap() {
        Map m = new HashMap();
        for (String str : keySet()) {
            m.put(str, get(String.valueOf(str)));
        }
        return m;
    }

    int _getInt(String s) {
        return _getInt(s, true);
    }

    int _getInt(String s, boolean err) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            if (err) {
                throw new IllegalArgumentException("BasicBSONList can only work with numeric keys, not: [" + s + "]");
            }
            return -1;
        }
    }
}
