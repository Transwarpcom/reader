package io.netty.util.collection;

import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/collection/IntObjectMap.class */
public interface IntObjectMap<V> extends Map<Integer, V> {

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/collection/IntObjectMap$PrimitiveEntry.class */
    public interface PrimitiveEntry<V> {
        int key();

        V value();

        void setValue(V v);
    }

    V get(int i);

    V put(int i, V v);

    V remove(int i);

    Iterable<PrimitiveEntry<V>> entries();

    boolean containsKey(int i);
}
