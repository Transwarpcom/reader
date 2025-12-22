package io.vertx.ext.web.common.template.impl;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/common/template/impl/ConcurrentLRUCache.class */
public class ConcurrentLRUCache<K, V> extends ConcurrentHashMap<K, V> {
    private int maxSize;
    private final Queue<K> queue;

    public ConcurrentLRUCache(int maxSize) {
        this.queue = new ArrayDeque();
        this.maxSize = maxSize;
        checkSize();
    }

    public ConcurrentLRUCache(int initialCapacity, int maxSize) {
        super(initialCapacity);
        this.queue = new ArrayDeque();
        this.maxSize = maxSize;
        checkSize();
    }

    public ConcurrentLRUCache(Map<? extends K, ? extends V> m, int maxSize) {
        super(m);
        this.queue = new ArrayDeque();
        this.maxSize = maxSize;
        checkSize();
        for (K k : m.keySet()) {
            entryAdded(k);
        }
        checkRemoveOldest();
    }

    public ConcurrentLRUCache(int initialCapacity, float loadFactor, int maxSize) {
        super(initialCapacity, loadFactor);
        this.queue = new ArrayDeque();
        this.maxSize = maxSize;
        checkSize();
    }

    public ConcurrentLRUCache(int initialCapacity, float loadFactor, int concurrencyLevel, int maxSize) {
        super(initialCapacity, loadFactor, concurrencyLevel);
        this.queue = new ArrayDeque();
        this.maxSize = maxSize;
        checkSize();
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        checkRemoveOldest();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public V put(K k, V v) {
        V v2 = (V) super.put(k, v);
        if (v2 == null) {
            entryAdded(k);
        }
        checkRemoveOldest();
        return v2;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K k : m.keySet()) {
            if (!super.containsKey(k)) {
                entryAdded(k);
            }
        }
        super.putAll(m);
        checkRemoveOldest();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        V v = (V) super.remove(obj);
        if (v != null) {
            entryRemoved(obj);
        }
        return v;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        super.clear();
        this.queue.clear();
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.Map, java.util.concurrent.ConcurrentMap
    public V putIfAbsent(K k, V v) {
        V v2 = (V) super.putIfAbsent(k, v);
        if (v2 == null) {
            entryAdded(k);
        }
        checkRemoveOldest();
        return v2;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.Map, java.util.concurrent.ConcurrentMap
    public boolean remove(Object key, Object value) {
        boolean removed = super.remove(key, value);
        if (removed) {
            entryRemoved(value);
        }
        return removed;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.Map, java.util.concurrent.ConcurrentMap
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    private void entryAdded(K k) {
        this.queue.add(k);
    }

    private void entryRemoved(Object o) {
        if (!this.queue.remove(o)) {
            throw new IllegalStateException("Failed to remove");
        }
    }

    private void checkRemoveOldest() {
        while (this.queue.size() > this.maxSize) {
            K k = this.queue.poll();
            if (k != null) {
                super.remove(k);
            }
        }
    }

    private void checkSize() {
        if (this.maxSize < 1) {
            throw new IllegalArgumentException("maxSize must be >= 1");
        }
    }

    public int queueSize() {
        return this.queue.size();
    }
}
