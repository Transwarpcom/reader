package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/MapDifference.class */
public interface MapDifference<K, V> {

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/MapDifference$ValueDifference.class */
    public interface ValueDifference<V> {
        V leftValue();

        V rightValue();

        boolean equals(Object obj);

        int hashCode();
    }

    boolean areEqual();

    Map<K, V> entriesOnlyOnLeft();

    Map<K, V> entriesOnlyOnRight();

    Map<K, V> entriesInCommon();

    Map<K, ValueDifference<V>> entriesDiffering();

    boolean equals(Object obj);

    int hashCode();
}
