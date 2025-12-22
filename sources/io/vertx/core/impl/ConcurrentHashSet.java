package io.vertx.core.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/ConcurrentHashSet.class */
public class ConcurrentHashSet<E> implements Set<E> {
    private final Map<E, Object> map;
    private static final Object OBJ = new Object();

    public ConcurrentHashSet(int size) {
        this.map = new ConcurrentHashMap(size);
    }

    public ConcurrentHashSet() {
        this.map = new ConcurrentHashMap();
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return this.map.keySet().toArray();
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.map.keySet().toArray(tArr);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E e) {
        return this.map.put(e, OBJ) == null;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object o) {
        return this.map.remove(o) != null;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        return this.map.keySet().containsAll(c);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            if (this.map.put(e, OBJ) == null) {
                changed = true;
            }
        }
        return changed;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.map.clear();
    }
}
