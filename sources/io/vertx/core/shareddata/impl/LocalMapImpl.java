package io.vertx.core.shareddata.impl;

import io.vertx.core.shareddata.LocalMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/impl/LocalMapImpl.class */
class LocalMapImpl<K, V> implements LocalMap<K, V> {
    private final ConcurrentMap<String, LocalMap<?, ?>> maps;
    private final String name;
    private final ConcurrentMap<K, V> map = new ConcurrentHashMap();

    LocalMapImpl(String name, ConcurrentMap<String, LocalMap<?, ?>> maps) {
        this.name = name;
        this.maps = maps;
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V get(Object obj) {
        return (V) Checker.copyIfRequired(this.map.get(obj));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V put(K key, V value) {
        Checker.checkType(key);
        Checker.checkType(value);
        return this.map.put(key, value);
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V remove(Object obj) {
        return (V) Checker.copyIfRequired(this.map.remove(obj));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public void clear() {
        this.map.clear();
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V putIfAbsent(K k, V v) {
        Checker.checkType(k);
        Checker.checkType(v);
        return (V) Checker.copyIfRequired(this.map.putIfAbsent(k, v));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public boolean remove(Object key, Object value) {
        return this.map.remove(key, value);
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public boolean replace(K key, V oldValue, V newValue) {
        return this.map.replace(key, oldValue, newValue);
    }

    @Override // io.vertx.core.shareddata.LocalMap
    public boolean removeIfPresent(K key, V value) {
        return this.map.remove(key, value);
    }

    @Override // io.vertx.core.shareddata.LocalMap
    public boolean replaceIfPresent(K key, V oldValue, V newValue) {
        Checker.checkType(key);
        Checker.checkType(oldValue);
        Checker.checkType(newValue);
        return this.map.replace(key, oldValue, newValue);
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V replace(K k, V v) {
        Checker.checkType(k);
        Checker.checkType(v);
        return (V) Checker.copyIfRequired(this.map.replace(k, v));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        this.map.replaceAll((k, v) -> {
            Checker.checkType(k);
            Checker.checkType(v);
            Object objApply = function.apply(k, v);
            if (objApply != null) {
                Checker.checkType(objApply);
            }
            return objApply;
        });
    }

    @Override // io.vertx.core.shareddata.LocalMap
    public void close() {
        this.maps.remove(this.name);
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public Set<K> keySet() {
        HashSet hashSet = new HashSet(this.map.size());
        for (K k : this.map.keySet()) {
            hashSet.add(Checker.copyIfRequired(k));
        }
        return hashSet;
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public Collection<V> values() {
        ArrayList arrayList = new ArrayList(this.map.size());
        for (V v : this.map.values()) {
            arrayList.add(Checker.copyIfRequired(v));
        }
        return arrayList;
    }

    private BiFunction<? super K, ? super V, ? extends V> typeChecked(BiFunction<? super K, ? super V, ? extends V> function) {
        return (k, v) -> {
            Checker.checkType(k);
            Object objApply = function.apply(k, v);
            if (objApply != null) {
                Checker.checkType(objApply);
            }
            return objApply;
        };
    }

    private Function<? super K, ? extends V> typeChecked(Function<? super K, ? extends V> function) {
        return k -> {
            Checker.checkType(k);
            Object objApply = function.apply(k);
            if (objApply != null) {
                Checker.checkType(objApply);
            }
            return objApply;
        };
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.map.compute(key, typeChecked(remappingFunction));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return this.map.computeIfAbsent(key, typeChecked(mappingFunction));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return this.map.computeIfPresent(key, typeChecked(remappingFunction));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>(this.map.size());
        for (final Map.Entry<K, V> entry : this.map.entrySet()) {
            entries.add(new Map.Entry<K, V>() { // from class: io.vertx.core.shareddata.impl.LocalMapImpl.1
                @Override // java.util.Map.Entry
                public K getKey() {
                    return (K) Checker.copyIfRequired(entry.getKey());
                }

                @Override // java.util.Map.Entry
                public V getValue() {
                    return (V) Checker.copyIfRequired(entry.getValue());
                }

                @Override // java.util.Map.Entry
                public V setValue(V value) {
                    throw new UnsupportedOperationException();
                }
            });
        }
        return entries;
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (Map.Entry<K, V> entry : entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V getOrDefault(Object obj, V v) {
        return (V) Checker.copyIfRequired(this.map.getOrDefault(obj, v));
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Checker.checkType(key);
        Checker.checkType(value);
        return this.map.merge(key, value, (k, v) -> {
            Object objApply = remappingFunction.apply(k, v);
            if (objApply != null) {
                Checker.checkType(objApply);
            }
            return objApply;
        });
    }

    @Override // io.vertx.core.shareddata.LocalMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public String toString() {
        return this.map.toString();
    }
}
