package org.antlr.v4.runtime.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/MultiMap.class */
public class MultiMap<K, V> extends LinkedHashMap<K, List<V>> {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.ArrayList] */
    public void map(K key, V value) {
        V arrayList = (List) get(key);
        if (arrayList == null) {
            arrayList = new ArrayList();
            super.put(key, arrayList);
        }
        arrayList.add(value);
    }

    public List<Pair<K, V>> getPairs() {
        List<Pair<K, V>> pairs = new ArrayList<>();
        for (K key : keySet()) {
            Iterator i$ = ((List) get(key)).iterator();
            while (i$.hasNext()) {
                pairs.add(new Pair<>(key, i$.next()));
            }
        }
        return pairs;
    }
}
