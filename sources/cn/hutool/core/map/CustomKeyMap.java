package cn.hutool.core.map;

import java.util.Map;
import java.util.function.BiFunction;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/CustomKeyMap.class */
public abstract class CustomKeyMap<K, V> extends MapWrapper<K, V> {
    private static final long serialVersionUID = 4043263744224569870L;

    protected abstract Object customKey(Object obj);

    public CustomKeyMap(Map<K, V> emptyMap) {
        super(emptyMap);
    }

    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V get(Object obj) {
        return (V) super.get(customKey(obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V put(K k, V v) {
        return (V) super.put(customKey(k), v);
    }

    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public boolean containsKey(Object key) {
        return super.containsKey(customKey(key));
    }

    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V remove(Object obj) {
        return (V) super.remove(customKey(obj));
    }

    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public boolean remove(Object key, Object value) {
        return super.remove(customKey(key), value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public boolean replace(K key, V oldValue, V newValue) {
        return super.replace(customKey(key), oldValue, newValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V replace(K k, V v) {
        return (V) super.replace(customKey(k), v);
    }

    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V getOrDefault(Object obj, V v) {
        return (V) super.getOrDefault(customKey(obj), v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V computeIfPresent(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        return (V) super.computeIfPresent(customKey(k), biFunction);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V compute(K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        return (V) super.compute(customKey(k), biFunction);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.hutool.core.map.MapWrapper, java.util.Map
    public V merge(K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
        return (V) super.merge(customKey(k), v, biFunction);
    }
}
