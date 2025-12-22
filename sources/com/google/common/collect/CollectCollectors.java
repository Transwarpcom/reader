package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedSet;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collector;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CollectCollectors.class */
final class CollectCollectors {
    private static final Collector<Object, ?, ImmutableList<Object>> TO_IMMUTABLE_LIST = Collector.of(ImmutableList::builder, (v0, v1) -> {
        v0.add(v1);
    }, (v0, v1) -> {
        return v0.combine(v1);
    }, (v0) -> {
        return v0.build();
    }, new Collector.Characteristics[0]);
    private static final Collector<Object, ?, ImmutableSet<Object>> TO_IMMUTABLE_SET = Collector.of(ImmutableSet::builder, (v0, v1) -> {
        v0.add(v1);
    }, (v0, v1) -> {
        return v0.combine(v1);
    }, (v0) -> {
        return v0.build();
    }, new Collector.Characteristics[0]);

    @GwtIncompatible
    private static final Collector<Range<Comparable>, ?, ImmutableRangeSet<Comparable>> TO_IMMUTABLE_RANGE_SET = Collector.of(ImmutableRangeSet::builder, (v0, v1) -> {
        v0.add(v1);
    }, (v0, v1) -> {
        return v0.combine(v1);
    }, (v0) -> {
        return v0.build();
    }, new Collector.Characteristics[0]);

    CollectCollectors() {
    }

    static <T, K, V> Collector<T, ?, ImmutableBiMap<K, V>> toImmutableBiMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valueFunction);
        return Collector.of(ImmutableBiMap.Builder::new, (builder, input) -> {
            builder.put((ImmutableBiMap.Builder) keyFunction.apply(input), valueFunction.apply(input));
        }, (v0, v1) -> {
            return v0.combine(v1);
        }, (v0) -> {
            return v0.build();
        }, new Collector.Characteristics[0]);
    }

    static <E> Collector<E, ?, ImmutableList<E>> toImmutableList() {
        return (Collector<E, ?, ImmutableList<E>>) TO_IMMUTABLE_LIST;
    }

    static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valueFunction);
        return Collector.of(ImmutableMap.Builder::new, (builder, input) -> {
            builder.put(keyFunction.apply(input), valueFunction.apply(input));
        }, (v0, v1) -> {
            return v0.combine(v1);
        }, (v0) -> {
            return v0.build();
        }, new Collector.Characteristics[0]);
    }

    static <E> Collector<E, ?, ImmutableSet<E>> toImmutableSet() {
        return (Collector<E, ?, ImmutableSet<E>>) TO_IMMUTABLE_SET;
    }

    static <T, K, V> Collector<T, ?, ImmutableSortedMap<K, V>> toImmutableSortedMap(Comparator<? super K> comparator, Function<? super T, ? extends K> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valueFunction);
        return Collector.of(() -> {
            return new ImmutableSortedMap.Builder(comparator);
        }, (builder, input) -> {
            builder.put((ImmutableSortedMap.Builder) keyFunction.apply(input), valueFunction.apply(input));
        }, (v0, v1) -> {
            return v0.combine(v1);
        }, (v0) -> {
            return v0.build();
        }, Collector.Characteristics.UNORDERED);
    }

    static <E> Collector<E, ?, ImmutableSortedSet<E>> toImmutableSortedSet(Comparator<? super E> comparator) {
        Preconditions.checkNotNull(comparator);
        return Collector.of(() -> {
            return new ImmutableSortedSet.Builder(comparator);
        }, (v0, v1) -> {
            v0.add(v1);
        }, (v0, v1) -> {
            return v0.combine(v1);
        }, (v0) -> {
            return v0.build();
        }, new Collector.Characteristics[0]);
    }

    @GwtIncompatible
    static <E extends Comparable<? super E>> Collector<Range<E>, ?, ImmutableRangeSet<E>> toImmutableRangeSet() {
        return (Collector<Range<E>, ?, ImmutableRangeSet<E>>) TO_IMMUTABLE_RANGE_SET;
    }

    @GwtIncompatible
    static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(Function<? super T, Range<K>> keyFunction, Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(keyFunction);
        Preconditions.checkNotNull(valueFunction);
        return Collector.of(ImmutableRangeMap::builder, (builder, input) -> {
            builder.put((Range) keyFunction.apply(input), valueFunction.apply(input));
        }, (v0, v1) -> {
            return v0.combine(v1);
        }, (v0) -> {
            return v0.build();
        }, new Collector.Characteristics[0]);
    }
}
