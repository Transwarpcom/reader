package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/BiMap.class */
public interface BiMap<K, V> extends Map<K, V> {
    @CanIgnoreReturnValue
    V put(K k, V v);

    @CanIgnoreReturnValue
    V forcePut(K k, V v);

    void putAll(Map<? extends K, ? extends V> map);

    @Override // java.util.Map
    Set<V> values();

    BiMap<V, K> inverse();
}
