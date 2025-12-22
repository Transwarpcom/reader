package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/shareddata/LocalMap.class */
public interface LocalMap<K, V> extends Map<K, V> {
    @Override // java.util.Map
    V get(Object obj);

    @Override // java.util.Map
    V put(K k, V v);

    @Override // java.util.Map
    V remove(Object obj);

    @Override // java.util.Map
    void clear();

    @Override // java.util.Map
    int size();

    @Override // java.util.Map
    boolean isEmpty();

    @Override // java.util.Map
    V putIfAbsent(K k, V v);

    boolean removeIfPresent(K k, V v);

    boolean replaceIfPresent(K k, V v, V v2);

    @Override // java.util.Map
    V replace(K k, V v);

    void close();

    @Override // java.util.Map
    @GenIgnore
    Set<K> keySet();

    @Override // java.util.Map
    @GenIgnore
    Collection<V> values();

    @Override // java.util.Map
    @GenIgnore
    V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override // java.util.Map
    @GenIgnore
    V computeIfAbsent(K k, Function<? super K, ? extends V> function);

    @Override // java.util.Map
    @GenIgnore
    V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction);

    @Override // java.util.Map
    boolean containsKey(Object obj);

    @Override // java.util.Map
    boolean containsValue(Object obj);

    @Override // java.util.Map
    @GenIgnore
    Set<Map.Entry<K, V>> entrySet();

    @Override // java.util.Map
    @GenIgnore
    void forEach(BiConsumer<? super K, ? super V> biConsumer);

    @Override // java.util.Map
    V getOrDefault(Object obj, V v);

    @Override // java.util.Map
    @GenIgnore
    V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction);

    @Override // java.util.Map
    @GenIgnore
    void putAll(Map<? extends K, ? extends V> map);

    @Override // java.util.Map
    @GenIgnore
    boolean remove(Object obj, Object obj2);

    @Override // java.util.Map
    @GenIgnore
    boolean replace(K k, V v, V v2);

    @Override // java.util.Map
    @GenIgnore
    void replaceAll(BiFunction<? super K, ? super V, ? extends V> biFunction);
}
