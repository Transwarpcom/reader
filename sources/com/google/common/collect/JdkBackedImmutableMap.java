package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMapEntrySet;
import java.util.Map;
import java.util.function.BiConsumer;

@GwtCompatible(emulated = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/JdkBackedImmutableMap.class */
final class JdkBackedImmutableMap<K, V> extends ImmutableMap<K, V> {
    private final transient Map<K, V> delegateMap;
    private final transient ImmutableList<Map.Entry<K, V>> entries;

    static <K, V> ImmutableMap<K, V> create(int n, Map.Entry<K, V>[] entryArray) {
        Map<K, V> delegateMap = Maps.newHashMapWithExpectedSize(n);
        for (int i = 0; i < n; i++) {
            entryArray[i] = RegularImmutableMap.makeImmutable(entryArray[i]);
            V oldValue = delegateMap.putIfAbsent(entryArray[i].getKey(), entryArray[i].getValue());
            if (oldValue != null) {
                throw conflictException("key", entryArray[i], entryArray[i].getKey() + "=" + oldValue);
            }
        }
        return new JdkBackedImmutableMap(delegateMap, ImmutableList.asImmutableList(entryArray, n));
    }

    JdkBackedImmutableMap(Map<K, V> delegateMap, ImmutableList<Map.Entry<K, V>> entries) {
        this.delegateMap = delegateMap;
        this.entries = entries;
    }

    @Override // java.util.Map
    public int size() {
        return this.entries.size();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object key) {
        return this.delegateMap.get(key);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        this.entries.forEach(e -> {
            action.accept(e.getKey(), e.getValue());
        });
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection<V> createValues() {
        return new ImmutableMapValues(this);
    }

    @Override // com.google.common.collect.ImmutableMap
    boolean isPartialView() {
        return false;
    }
}
