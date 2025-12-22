package org.bson.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.bson.assertions.Assertions;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap.class */
abstract class AbstractCopyOnWriteMap<K, V, M extends Map<K, V>> implements ConcurrentMap<K, V> {
    private volatile M delegate;
    private final transient Lock lock = new ReentrantLock();
    private final View<K, V> view;

    abstract <N extends Map<? extends K, ? extends V>> M copy(N n);

    protected <N extends Map<? extends K, ? extends V>> AbstractCopyOnWriteMap(N map, View.Type viewType) {
        this.delegate = (M) Assertions.notNull("delegate", copy((Map) Assertions.notNull(BeanDefinitionParserDelegate.MAP_ELEMENT, map)));
        this.view = ((View.Type) Assertions.notNull("viewType", viewType)).get(this);
    }

    @Override // java.util.Map
    public final void clear() {
        this.lock.lock();
        try {
            set(copy(Collections.emptyMap()));
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.util.Map
    public final V remove(Object obj) {
        this.lock.lock();
        try {
            if (!this.delegate.containsKey(obj)) {
                return null;
            }
            Map mapCopy = copy();
            try {
                V v = (V) mapCopy.remove(obj);
                set(mapCopy);
                this.lock.unlock();
                return v;
            } catch (Throwable th) {
                set(mapCopy);
                throw th;
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean remove(Object key, Object value) {
        this.lock.lock();
        try {
            if (this.delegate.containsKey(key) && equals(value, this.delegate.get(key))) {
                Map mapCopy = copy();
                mapCopy.remove(key);
                set(mapCopy);
                this.lock.unlock();
                return true;
            }
            return false;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public boolean replace(K key, V oldValue, V newValue) {
        this.lock.lock();
        try {
            if (!this.delegate.containsKey(key) || !equals(oldValue, this.delegate.get(key))) {
                return false;
            }
            Map mapCopy = copy();
            mapCopy.put(key, newValue);
            set(mapCopy);
            this.lock.unlock();
            return true;
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V replace(K k, V v) {
        this.lock.lock();
        try {
            if (!this.delegate.containsKey(k)) {
                return null;
            }
            Map mapCopy = copy();
            try {
                V v2 = (V) mapCopy.put(k, v);
                set(mapCopy);
                this.lock.unlock();
                return v2;
            } catch (Throwable th) {
                set(mapCopy);
                throw th;
            }
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.util.Map
    public final V put(K k, V v) {
        this.lock.lock();
        try {
            Map mapCopy = copy();
            try {
                V v2 = (V) mapCopy.put(k, v);
                set(mapCopy);
                this.lock.unlock();
                return v2;
            } catch (Throwable th) {
                set(mapCopy);
                throw th;
            }
        } catch (Throwable th2) {
            this.lock.unlock();
            throw th2;
        }
    }

    @Override // java.util.concurrent.ConcurrentMap, java.util.Map
    public V putIfAbsent(K k, V v) {
        this.lock.lock();
        try {
            if (!this.delegate.containsKey(k)) {
                Map mapCopy = copy();
                try {
                    V v2 = (V) mapCopy.put(k, v);
                    set(mapCopy);
                    this.lock.unlock();
                    return v2;
                } catch (Throwable th) {
                    set(mapCopy);
                    throw th;
                }
            }
            V v3 = (V) this.delegate.get(k);
            this.lock.unlock();
            return v3;
        } catch (Throwable th2) {
            this.lock.unlock();
            throw th2;
        }
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> t) {
        this.lock.lock();
        try {
            Map mapCopy = copy();
            mapCopy.putAll(t);
            set(mapCopy);
        } finally {
            this.lock.unlock();
        }
    }

    protected M copy() {
        this.lock.lock();
        try {
            return (M) copy(this.delegate);
        } finally {
            this.lock.unlock();
        }
    }

    protected void set(M map) {
        this.delegate = map;
    }

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return this.view.entrySet();
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        return this.view.keySet();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        return this.view.values();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object key) {
        return this.delegate.containsKey(key);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object value) {
        return this.delegate.containsValue(value);
    }

    @Override // java.util.Map
    public final V get(Object obj) {
        return (V) this.delegate.get(obj);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    @Override // java.util.Map
    public final int size() {
        return this.delegate.size();
    }

    @Override // java.util.Map
    public final boolean equals(Object o) {
        return this.delegate.equals(o);
    }

    @Override // java.util.Map
    public final int hashCode() {
        return this.delegate.hashCode();
    }

    protected final M getDelegate() {
        return this.delegate;
    }

    public String toString() {
        return this.delegate.toString();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$KeySet.class */
    private class KeySet extends CollectionView<K> implements Set<K> {
        private KeySet() {
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.CollectionView
        Collection<K> getDelegate() {
            return AbstractCopyOnWriteMap.this.delegate.keySet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public void clear() {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                mapCopy.keySet().clear();
                AbstractCopyOnWriteMap.this.set(mapCopy);
            } finally {
                AbstractCopyOnWriteMap.this.lock.unlock();
            }
        }

        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            return AbstractCopyOnWriteMap.this.remove(o) != null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRemoveAll = mapCopy.keySet().removeAll(c);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRemoveAll;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } catch (Throwable th2) {
                AbstractCopyOnWriteMap.this.lock.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRetainAll = mapCopy.keySet().retainAll(c);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRetainAll;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } catch (Throwable th2) {
                AbstractCopyOnWriteMap.this.lock.unlock();
                throw th2;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$Values.class */
    private final class Values extends CollectionView<V> {
        private Values() {
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.CollectionView
        Collection<V> getDelegate() {
            return AbstractCopyOnWriteMap.this.delegate.values();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public void clear() {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                mapCopy.values().clear();
                AbstractCopyOnWriteMap.this.set(mapCopy);
            } finally {
                AbstractCopyOnWriteMap.this.lock.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public boolean remove(Object o) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                if (!contains(o)) {
                    return false;
                }
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRemove = mapCopy.values().remove(o);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRemove;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } finally {
                AbstractCopyOnWriteMap.this.lock.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public boolean removeAll(Collection<?> c) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRemoveAll = mapCopy.values().removeAll(c);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRemoveAll;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } catch (Throwable th2) {
                AbstractCopyOnWriteMap.this.lock.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection
        public boolean retainAll(Collection<?> c) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRetainAll = mapCopy.values().retainAll(c);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRetainAll;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } catch (Throwable th2) {
                AbstractCopyOnWriteMap.this.lock.unlock();
                throw th2;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$EntrySet.class */
    private class EntrySet extends CollectionView<Map.Entry<K, V>> implements Set<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.CollectionView
        Collection<Map.Entry<K, V>> getDelegate() {
            return AbstractCopyOnWriteMap.this.delegate.entrySet();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public void clear() {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                mapCopy.entrySet().clear();
                AbstractCopyOnWriteMap.this.set(mapCopy);
            } finally {
                AbstractCopyOnWriteMap.this.lock.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                if (!contains(o)) {
                    return false;
                }
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRemove = mapCopy.entrySet().remove(o);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRemove;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } finally {
                AbstractCopyOnWriteMap.this.lock.unlock();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> c) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRemoveAll = mapCopy.entrySet().removeAll(c);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRemoveAll;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } catch (Throwable th2) {
                AbstractCopyOnWriteMap.this.lock.unlock();
                throw th2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> c) {
            AbstractCopyOnWriteMap.this.lock.lock();
            try {
                Map mapCopy = AbstractCopyOnWriteMap.this.copy();
                try {
                    boolean zRetainAll = mapCopy.entrySet().retainAll(c);
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    AbstractCopyOnWriteMap.this.lock.unlock();
                    return zRetainAll;
                } catch (Throwable th) {
                    AbstractCopyOnWriteMap.this.set(mapCopy);
                    throw th;
                }
            } catch (Throwable th2) {
                AbstractCopyOnWriteMap.this.lock.unlock();
                throw th2;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$UnmodifiableIterator.class */
    private static class UnmodifiableIterator<T> implements Iterator<T> {
        private final Iterator<T> delegate;

        UnmodifiableIterator(Iterator<T> delegate) {
            this.delegate = delegate;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.delegate.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return this.delegate.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$CollectionView.class */
    protected static abstract class CollectionView<E> implements Collection<E> {
        abstract Collection<E> getDelegate();

        protected CollectionView() {
        }

        @Override // java.util.Collection
        public final boolean contains(Object o) {
            return getDelegate().contains(o);
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection<?> c) {
            return getDelegate().containsAll(c);
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator<E> iterator() {
            return new UnmodifiableIterator(getDelegate().iterator());
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return getDelegate().isEmpty();
        }

        @Override // java.util.Collection
        public final int size() {
            return getDelegate().size();
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            return getDelegate().toArray();
        }

        @Override // java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            return (T[]) getDelegate().toArray(tArr);
        }

        @Override // java.util.Collection
        public int hashCode() {
            return getDelegate().hashCode();
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            return getDelegate().equals(obj);
        }

        public String toString() {
            return getDelegate().toString();
        }

        @Override // java.util.Collection
        public final boolean add(E o) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }
    }

    private boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$View.class */
    public static abstract class View<K, V> {

        /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$View$Type.class */
        public enum Type {
            STABLE { // from class: org.bson.util.AbstractCopyOnWriteMap.View.Type.1
                @Override // org.bson.util.AbstractCopyOnWriteMap.View.Type
                <K, V, M extends Map<K, V>> View<K, V> get(AbstractCopyOnWriteMap<K, V, M> host) {
                    host.getClass();
                    return host.new Immutable();
                }
            },
            LIVE { // from class: org.bson.util.AbstractCopyOnWriteMap.View.Type.2
                @Override // org.bson.util.AbstractCopyOnWriteMap.View.Type
                <K, V, M extends Map<K, V>> View<K, V> get(AbstractCopyOnWriteMap<K, V, M> host) {
                    host.getClass();
                    return host.new Mutable();
                }
            };

            abstract <K, V, M extends Map<K, V>> View<K, V> get(AbstractCopyOnWriteMap<K, V, M> abstractCopyOnWriteMap);
        }

        abstract Set<K> keySet();

        abstract Set<Map.Entry<K, V>> entrySet();

        abstract Collection<V> values();

        View() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$Immutable.class */
    final class Immutable extends View<K, V> {
        Immutable() {
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.View
        public Set<K> keySet() {
            return Collections.unmodifiableSet(AbstractCopyOnWriteMap.this.delegate.keySet());
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.View
        public Set<Map.Entry<K, V>> entrySet() {
            return Collections.unmodifiableSet(AbstractCopyOnWriteMap.this.delegate.entrySet());
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.View
        public Collection<V> values() {
            return Collections.unmodifiableCollection(AbstractCopyOnWriteMap.this.delegate.values());
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/util/AbstractCopyOnWriteMap$Mutable.class */
    final class Mutable extends View<K, V> {
        private final transient AbstractCopyOnWriteMap<K, V, M>.KeySet keySet;
        private final transient AbstractCopyOnWriteMap<K, V, M>.EntrySet entrySet;
        private final transient AbstractCopyOnWriteMap<K, V, M>.Values values;

        Mutable() {
            this.keySet = new KeySet();
            this.entrySet = new EntrySet();
            this.values = new Values();
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.View
        public Set<K> keySet() {
            return this.keySet;
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.View
        public Set<Map.Entry<K, V>> entrySet() {
            return this.entrySet;
        }

        @Override // org.bson.util.AbstractCopyOnWriteMap.View
        public Collection<V> values() {
            return this.values;
        }
    }
}
