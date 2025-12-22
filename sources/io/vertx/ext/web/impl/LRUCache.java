package io.vertx.ext.web.impl;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/LRUCache.class */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;

    public LRUCache(int initialCapacity, float loadFactor, int maxSize) {
        super(initialCapacity, loadFactor);
        this.maxSize = maxSize;
        checkSize();
    }

    public LRUCache(int initialCapacity, int maxSize) {
        super(initialCapacity);
        this.maxSize = maxSize;
        checkSize();
    }

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        checkSize();
    }

    public LRUCache(Map<? extends K, ? extends V> m, int maxSize) {
        super(m);
        this.maxSize = maxSize;
        checkSize();
    }

    public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder, int maxSize) {
        super(initialCapacity, loadFactor, accessOrder);
        this.maxSize = maxSize;
        checkSize();
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.maxSize;
    }

    private void checkSize() {
        if (this.maxSize < 1) {
            throw new IllegalArgumentException("maxSize must be >= 1");
        }
    }
}
