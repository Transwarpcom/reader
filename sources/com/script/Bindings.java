package com.script;

import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:com/script/Bindings.class */
public interface Bindings extends Map<String, Object> {
    @Override // java.util.Map
    boolean containsKey(Object obj);

    @Override // java.util.Map
    Object get(Object obj);

    @Override // java.util.Map
    Object put(String str, Object obj);

    @Override // java.util.Map
    void putAll(Map<? extends String, ? extends Object> map);

    @Override // java.util.Map
    Object remove(Object obj);
}
