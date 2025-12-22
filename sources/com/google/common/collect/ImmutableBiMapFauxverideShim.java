package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/ImmutableBiMapFauxverideShim.class */
abstract class ImmutableBiMapFauxverideShim<K, V> extends ImmutableMap<K, V> {
    ImmutableBiMapFauxverideShim() {
    }

    @Deprecated
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        throw new UnsupportedOperationException();
    }
}
