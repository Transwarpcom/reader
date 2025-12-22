package io.netty.util.collection;

import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/collection/ShortObjectMap.class */
public interface ShortObjectMap<V> extends Map<Short, V> {

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/collection/ShortObjectMap$PrimitiveEntry.class */
    public interface PrimitiveEntry<V> {
        short key();

        V value();

        void setValue(V v);
    }

    V get(short s);

    V put(short s, V v);

    V remove(short s);

    Iterable<PrimitiveEntry<V>> entries();

    boolean containsKey(short s);
}
