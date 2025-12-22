package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import org.apache.pdfbox.contentstream.operator.OperatorName;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/Multimap.class */
public interface Multimap<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(@CompatibleWith(OperatorName.STROKING_COLOR_CMYK) Object obj);

    boolean containsValue(@CompatibleWith("V") Object obj);

    boolean containsEntry(@CompatibleWith(OperatorName.STROKING_COLOR_CMYK) Object obj, @CompatibleWith("V") Object obj2);

    @CanIgnoreReturnValue
    boolean put(K k, V v);

    @CanIgnoreReturnValue
    boolean remove(@CompatibleWith(OperatorName.STROKING_COLOR_CMYK) Object obj, @CompatibleWith("V") Object obj2);

    @CanIgnoreReturnValue
    boolean putAll(K k, Iterable<? extends V> iterable);

    @CanIgnoreReturnValue
    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    @CanIgnoreReturnValue
    Collection<V> replaceValues(K k, Iterable<? extends V> iterable);

    @CanIgnoreReturnValue
    Collection<V> removeAll(@CompatibleWith(OperatorName.STROKING_COLOR_CMYK) Object obj);

    void clear();

    Collection<V> get(K k);

    Set<K> keySet();

    Multiset<K> keys();

    Collection<V> values();

    Collection<Map.Entry<K, V>> entries();

    Map<K, Collection<V>> asMap();

    boolean equals(Object obj);

    int hashCode();

    default void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        entries().forEach(entry -> {
            action.accept(entry.getKey(), entry.getValue());
        });
    }
}
