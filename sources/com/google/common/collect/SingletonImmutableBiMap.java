package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.Map;
import java.util.function.BiConsumer;

@GwtCompatible(serializable = true, emulated = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/SingletonImmutableBiMap.class */
final class SingletonImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    final transient K singleKey;
    final transient V singleValue;

    @RetainedWith
    @LazyInit
    transient ImmutableBiMap<V, K> inverse;

    SingletonImmutableBiMap(K singleKey, V singleValue) {
        CollectPreconditions.checkEntryNotNull(singleKey, singleValue);
        this.singleKey = singleKey;
        this.singleValue = singleValue;
    }

    private SingletonImmutableBiMap(K singleKey, V singleValue, ImmutableBiMap<V, K> inverse) {
        this.singleKey = singleKey;
        this.singleValue = singleValue;
        this.inverse = inverse;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object key) {
        if (this.singleKey.equals(key)) {
            return this.singleValue;
        }
        return null;
    }

    @Override // java.util.Map
    public int size() {
        return 1;
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        ((BiConsumer) Preconditions.checkNotNull(action)).accept(this.singleKey, this.singleValue);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public boolean containsKey(Object key) {
        return this.singleKey.equals(key);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public boolean containsValue(Object value) {
        return this.singleValue.equals(value);
    }

    @Override // com.google.common.collect.ImmutableMap
    boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return ImmutableSet.of(Maps.immutableEntry(this.singleKey, this.singleValue));
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return ImmutableSet.of(this.singleKey);
    }

    @Override // com.google.common.collect.ImmutableBiMap, com.google.common.collect.BiMap
    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> result = this.inverse;
        if (result == null) {
            SingletonImmutableBiMap singletonImmutableBiMap = new SingletonImmutableBiMap(this.singleValue, this.singleKey, this);
            this.inverse = singletonImmutableBiMap;
            return singletonImmutableBiMap;
        }
        return result;
    }
}
