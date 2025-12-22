package com.jayway.jsonpath.spi.cache;

import com.jayway.jsonpath.JsonPath;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/spi/cache/LRUCache.class */
public class LRUCache implements Cache {
    private final ReentrantLock lock = new ReentrantLock();
    private final Map<String, JsonPath> map = new ConcurrentHashMap();
    private final Deque<String> queue = new LinkedList();
    private final int limit;

    public LRUCache(int limit) {
        this.limit = limit;
    }

    @Override // com.jayway.jsonpath.spi.cache.Cache
    public void put(String key, JsonPath value) {
        JsonPath oldValue = this.map.put(key, value);
        if (oldValue != null) {
            removeThenAddKey(key);
        } else {
            addKey(key);
        }
        if (this.map.size() > this.limit) {
            this.map.remove(removeLast());
        }
    }

    @Override // com.jayway.jsonpath.spi.cache.Cache
    public JsonPath get(String key) {
        JsonPath jsonPath = this.map.get(key);
        if (jsonPath != null) {
            removeThenAddKey(key);
        }
        return jsonPath;
    }

    private void addKey(String key) {
        this.lock.lock();
        try {
            this.queue.addFirst(key);
        } finally {
            this.lock.unlock();
        }
    }

    private String removeLast() {
        this.lock.lock();
        try {
            String removedKey = this.queue.removeLast();
            return removedKey;
        } finally {
            this.lock.unlock();
        }
    }

    private void removeThenAddKey(String key) {
        this.lock.lock();
        try {
            this.queue.removeFirstOccurrence(key);
            this.queue.addFirst(key);
        } finally {
            this.lock.unlock();
        }
    }

    private void removeFirstOccurrence(String key) {
        this.lock.lock();
        try {
            this.queue.removeFirstOccurrence(key);
        } finally {
            this.lock.unlock();
        }
    }

    public JsonPath getSilent(String key) {
        return this.map.get(key);
    }

    public void remove(String key) {
        removeFirstOccurrence(key);
        this.map.remove(key);
    }

    public int size() {
        return this.map.size();
    }

    public String toString() {
        return this.map.toString();
    }
}
