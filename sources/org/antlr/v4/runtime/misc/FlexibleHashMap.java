package org.antlr.v4.runtime.misc;

import cn.hutool.core.text.StrPool;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/FlexibleHashMap.class */
public class FlexibleHashMap<K, V> implements Map<K, V> {
    public static final int INITAL_CAPACITY = 16;
    public static final int INITAL_BUCKET_CAPACITY = 8;
    public static final double LOAD_FACTOR = 0.75d;
    protected final AbstractEqualityComparator<? super K> comparator;
    protected LinkedList<Entry<K, V>>[] buckets;
    protected int n;
    protected int threshold;
    protected int currentPrime;
    protected int initialBucketCapacity;

    /* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/FlexibleHashMap$Entry.class */
    public static class Entry<K, V> {
        public final K key;
        public V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return this.key.toString() + ":" + this.value.toString();
        }
    }

    public FlexibleHashMap() {
        this(null, 16, 8);
    }

    public FlexibleHashMap(AbstractEqualityComparator<? super K> comparator) {
        this(comparator, 16, 8);
    }

    public FlexibleHashMap(AbstractEqualityComparator<? super K> comparator, int initialCapacity, int initialBucketCapacity) {
        this.n = 0;
        this.threshold = 12;
        this.currentPrime = 1;
        this.initialBucketCapacity = 8;
        this.comparator = comparator == null ? ObjectEqualityComparator.INSTANCE : comparator;
        this.buckets = createEntryListArray(initialBucketCapacity);
        this.initialBucketCapacity = initialBucketCapacity;
    }

    private static <K, V> LinkedList<Entry<K, V>>[] createEntryListArray(int length) {
        LinkedList<Entry<K, V>>[] result = new LinkedList[length];
        return result;
    }

    protected int getBucket(K key) {
        int hash = this.comparator.hashCode(key);
        int b = hash & (this.buckets.length - 1);
        return b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object obj) {
        if (obj == 0) {
            return null;
        }
        LinkedList<Entry<K, V>> linkedList = this.buckets[getBucket(obj)];
        if (linkedList == null) {
            return null;
        }
        Iterator<Entry<K, V>> it = linkedList.iterator();
        while (it.hasNext()) {
            Entry<K, V> next = it.next();
            if (this.comparator.equals(next.key, obj)) {
                return next.value;
            }
        }
        return null;
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        if (k == null) {
            return null;
        }
        if (this.n > this.threshold) {
            expand();
        }
        int bucket = getBucket(k);
        LinkedList<Entry<K, V>> linkedList = this.buckets[bucket];
        if (linkedList == null) {
            LinkedList<Entry<K, V>>[] linkedListArr = this.buckets;
            LinkedList<Entry<K, V>> linkedList2 = new LinkedList<>();
            linkedListArr[bucket] = linkedList2;
            linkedList = linkedList2;
        }
        Iterator<Entry<K, V>> it = linkedList.iterator();
        while (it.hasNext()) {
            Entry<K, V> next = it.next();
            if (this.comparator.equals(next.key, k)) {
                V v2 = next.value;
                next.value = v;
                this.n++;
                return v2;
            }
        }
        linkedList.add(new Entry<>(k, v));
        this.n++;
        return null;
    }

    @Override // java.util.Map
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        List<V> a = new ArrayList<>(size());
        LinkedList<Entry<K, V>>[] arr$ = this.buckets;
        for (LinkedList<Entry<K, V>> bucket : arr$) {
            if (bucket != null) {
                Iterator i$ = bucket.iterator();
                while (i$.hasNext()) {
                    Entry<K, V> e = i$.next();
                    a.add(e.value);
                }
            }
        }
        return a;
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public int hashCode() {
        Entry<K, V> next;
        int iInitialize = MurmurHash.initialize();
        for (LinkedList<Entry<K, V>> linkedList : this.buckets) {
            if (linkedList != null) {
                Iterator<Entry<K, V>> it = linkedList.iterator();
                while (it.hasNext() && (next = it.next()) != null) {
                    iInitialize = MurmurHash.update(iInitialize, this.comparator.hashCode(next.key));
                }
            }
        }
        return MurmurHash.finish(iInitialize, size());
    }

    @Override // java.util.Map
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    protected void expand() {
        Entry<K, V> e;
        LinkedList<Entry<K, V>>[] old = this.buckets;
        this.currentPrime += 4;
        int newCapacity = this.buckets.length * 2;
        LinkedList<Entry<K, V>>[] newTable = createEntryListArray(newCapacity);
        this.buckets = newTable;
        this.threshold = (int) (newCapacity * 0.75d);
        int oldSize = size();
        for (LinkedList<Entry<K, V>> bucket : old) {
            if (bucket != null) {
                Iterator i$ = bucket.iterator();
                while (i$.hasNext() && (e = i$.next()) != null) {
                    put(e.key, e.value);
                }
            }
        }
        this.n = oldSize;
    }

    @Override // java.util.Map
    public int size() {
        return this.n;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.n == 0;
    }

    @Override // java.util.Map
    public void clear() {
        this.buckets = createEntryListArray(16);
        this.n = 0;
    }

    public String toString() {
        Entry<K, V> e;
        if (size() == 0) {
            return StrPool.EMPTY_JSON;
        }
        StringBuilder buf = new StringBuilder();
        buf.append('{');
        boolean first = true;
        LinkedList<Entry<K, V>>[] arr$ = this.buckets;
        for (LinkedList<Entry<K, V>> bucket : arr$) {
            if (bucket != null) {
                Iterator i$ = bucket.iterator();
                while (i$.hasNext() && (e = i$.next()) != null) {
                    if (first) {
                        first = false;
                    } else {
                        buf.append(", ");
                    }
                    buf.append(e.toString());
                }
            }
        }
        buf.append('}');
        return buf.toString();
    }

    public String toTableString() {
        StringBuilder buf = new StringBuilder();
        LinkedList<Entry<K, V>>[] arr$ = this.buckets;
        for (LinkedList<Entry<K, V>> bucket : arr$) {
            if (bucket == null) {
                buf.append("null\n");
            } else {
                buf.append('[');
                boolean first = true;
                Iterator i$ = bucket.iterator();
                while (i$.hasNext()) {
                    Entry<K, V> e = i$.next();
                    if (first) {
                        first = false;
                    } else {
                        buf.append(" ");
                    }
                    if (e == null) {
                        buf.append("_");
                    } else {
                        buf.append(e.toString());
                    }
                }
                buf.append("]\n");
            }
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        FlexibleHashMap<String, Integer> map = new FlexibleHashMap<>();
        map.put("hi", 1);
        map.put("mom", 2);
        map.put("foo", 3);
        map.put("ach", 4);
        map.put("cbba", 5);
        map.put("d", 6);
        map.put("edf", 7);
        map.put("mom", 8);
        map.put("hi", 9);
        System.out.println(map);
        System.out.println(map.toTableString());
    }
}
