package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Table.class */
public interface Table<R, C, V> {

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Table$Cell.class */
    public interface Cell<R, C, V> {
        R getRowKey();

        C getColumnKey();

        V getValue();

        boolean equals(Object obj);

        int hashCode();
    }

    boolean contains(@CompatibleWith("R") Object obj, @CompatibleWith("C") Object obj2);

    boolean containsRow(@CompatibleWith("R") Object obj);

    boolean containsColumn(@CompatibleWith("C") Object obj);

    boolean containsValue(@CompatibleWith("V") Object obj);

    V get(@CompatibleWith("R") Object obj, @CompatibleWith("C") Object obj2);

    boolean isEmpty();

    int size();

    boolean equals(Object obj);

    int hashCode();

    void clear();

    @CanIgnoreReturnValue
    V put(R r, C c, V v);

    void putAll(Table<? extends R, ? extends C, ? extends V> table);

    @CanIgnoreReturnValue
    V remove(@CompatibleWith("R") Object obj, @CompatibleWith("C") Object obj2);

    Map<C, V> row(R r);

    Map<R, V> column(C c);

    Set<Cell<R, C, V>> cellSet();

    Set<R> rowKeySet();

    Set<C> columnKeySet();

    Collection<V> values();

    Map<R, Map<C, V>> rowMap();

    Map<C, Map<R, V>> columnMap();
}
