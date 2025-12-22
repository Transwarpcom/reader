package com.script;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/SimpleBindings.class */
public class SimpleBindings implements Bindings {
    private Map<String, Object> map;

    public SimpleBindings(Map<String, Object> m) {
        if (m == null) {
            throw new NullPointerException();
        }
        this.map = m;
    }

    public SimpleBindings() {
        this(new HashMap());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.script.Bindings, java.util.Map
    public Object put(String name, Object value) {
        checkKey(name);
        return this.map.put(name, value);
    }

    @Override // com.script.Bindings, java.util.Map
    public void putAll(Map<? extends String, ? extends Object> toMerge) {
        if (toMerge == null) {
            throw new NullPointerException("toMerge map is null");
        }
        for (Map.Entry<? extends String, ? extends Object> entry : toMerge.entrySet()) {
            String key = entry.getKey();
            checkKey(key);
            put(key, entry.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // com.script.Bindings, java.util.Map
    public boolean containsKey(Object key) {
        checkKey(key);
        return this.map.containsKey(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    @Override // com.script.Bindings, java.util.Map
    public Object get(Object key) {
        checkKey(key);
        return this.map.get(key);
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // com.script.Bindings, java.util.Map
    public Object remove(Object key) {
        checkKey(key);
        return this.map.remove(key);
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.map.values();
    }

    private void checkKey(Object key) {
        if (key == null) {
            throw new NullPointerException("key can not be null");
        }
        if (!(key instanceof String)) {
            throw new ClassCastException("key should be a String");
        }
        if (key.equals("")) {
            throw new IllegalArgumentException("key can not be empty");
        }
    }
}
