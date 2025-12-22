package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import java.lang.Comparable;
import java.util.Map;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/RangeMap.class */
public interface RangeMap<K extends Comparable, V> {
    V get(K k);

    Map.Entry<Range<K>, V> getEntry(K k);

    Range<K> span();

    void put(Range<K> range, V v);

    void putCoalescing(Range<K> range, V v);

    void putAll(RangeMap<K, V> rangeMap);

    void clear();

    void remove(Range<K> range);

    Map<Range<K>, V> asMapOfRanges();

    Map<Range<K>, V> asDescendingMapOfRanges();

    RangeMap<K, V> subRangeMap(Range<K> range);

    boolean equals(Object obj);

    int hashCode();

    String toString();
}
