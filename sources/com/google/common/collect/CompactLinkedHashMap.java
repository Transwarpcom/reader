package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactLinkedHashMap.class */
class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;

    @VisibleForTesting
    transient long[] links;
    private transient int firstEntry;
    private transient int lastEntry;
    private final boolean accessOrder;

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashMap<>(expectedSize);
    }

    CompactLinkedHashMap() {
        this(3);
    }

    CompactLinkedHashMap(int expectedSize) {
        this(expectedSize, false);
    }

    CompactLinkedHashMap(int expectedSize, boolean accessOrder) {
        super(expectedSize);
        this.accessOrder = accessOrder;
    }

    @Override // com.google.common.collect.CompactHashMap
    void init(int expectedSize) {
        super.init(expectedSize);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.CompactHashMap
    void allocArrays() {
        super.allocArrays();
        int expectedSize = this.keys.length;
        this.links = new long[expectedSize];
        Arrays.fill(this.links, -1L);
    }

    private int getPredecessor(int entry) {
        return (int) (this.links[entry] >>> 32);
    }

    @Override // com.google.common.collect.CompactHashMap
    int getSuccessor(int entry) {
        return (int) this.links[entry];
    }

    private void setSuccessor(int entry, int succ) {
        this.links[entry] = (this.links[entry] & (4294967295L ^ (-1))) | (succ & 4294967295L);
    }

    private void setPredecessor(int entry, int pred) {
        this.links[entry] = (this.links[entry] & ((-4294967296L) ^ (-1))) | (pred << 32);
    }

    private void setSucceeds(int pred, int succ) {
        if (pred == -2) {
            this.firstEntry = succ;
        } else {
            setSuccessor(pred, succ);
        }
        if (succ == -2) {
            this.lastEntry = pred;
        } else {
            setPredecessor(succ, pred);
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    void insertEntry(int entryIndex, K key, V value, int hash) {
        super.insertEntry(entryIndex, key, value, hash);
        setSucceeds(this.lastEntry, entryIndex);
        setSucceeds(entryIndex, -2);
    }

    @Override // com.google.common.collect.CompactHashMap
    void accessEntry(int index) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(index), getSuccessor(index));
            setSucceeds(this.lastEntry, index);
            setSucceeds(index, -2);
            this.modCount++;
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    void moveLastEntry(int dstIndex) {
        int srcIndex = size() - 1;
        super.moveLastEntry(dstIndex);
        setSucceeds(getPredecessor(dstIndex), getSuccessor(dstIndex));
        if (dstIndex < srcIndex) {
            setSucceeds(getPredecessor(srcIndex), dstIndex);
            setSucceeds(dstIndex, getSuccessor(srcIndex));
        }
        this.links[srcIndex] = -1;
    }

    @Override // com.google.common.collect.CompactHashMap
    void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        int oldCapacity = this.links.length;
        this.links = Arrays.copyOf(this.links, newCapacity);
        if (oldCapacity < newCapacity) {
            Arrays.fill(this.links, oldCapacity, newCapacity, -1L);
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashMap
    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= size() ? indexRemoved : indexBeforeRemove;
    }

    @Override // com.google.common.collect.CompactHashMap
    Set<Map.Entry<K, V>> createEntrySet() {
        return new CompactHashMap<K, V>.EntrySetView() { // from class: com.google.common.collect.CompactLinkedHashMap.1EntrySetImpl
            @Override // com.google.common.collect.CompactHashMap.EntrySetView, java.util.Collection, java.lang.Iterable, java.util.Set
            public Spliterator<Map.Entry<K, V>> spliterator() {
                return Spliterators.spliterator(this, 17);
            }
        };
    }

    @Override // com.google.common.collect.CompactHashMap
    Set<K> createKeySet() {
        return new CompactHashMap<K, V>.KeySetView() { // from class: com.google.common.collect.CompactLinkedHashMap.1KeySetImpl
            @Override // com.google.common.collect.CompactHashMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return ObjectArrays.toArrayImpl(this);
            }

            @Override // com.google.common.collect.CompactHashMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) ObjectArrays.toArrayImpl(this, tArr);
            }

            @Override // com.google.common.collect.CompactHashMap.KeySetView, java.util.Collection, java.lang.Iterable, java.util.Set
            public Spliterator<K> spliterator() {
                return Spliterators.spliterator(this, 17);
            }
        };
    }

    @Override // com.google.common.collect.CompactHashMap
    Collection<V> createValues() {
        return new CompactHashMap<K, V>.ValuesView() { // from class: com.google.common.collect.CompactLinkedHashMap.1ValuesImpl
            @Override // com.google.common.collect.CompactHashMap.ValuesView, java.util.AbstractCollection, java.util.Collection
            public Object[] toArray() {
                return ObjectArrays.toArrayImpl(this);
            }

            @Override // com.google.common.collect.CompactHashMap.ValuesView, java.util.AbstractCollection, java.util.Collection
            public <T> T[] toArray(T[] tArr) {
                return (T[]) ObjectArrays.toArrayImpl(this, tArr);
            }

            @Override // com.google.common.collect.CompactHashMap.ValuesView, java.util.Collection, java.lang.Iterable
            public Spliterator<V> spliterator() {
                return Spliterators.spliterator(this, 16);
            }
        };
    }

    @Override // com.google.common.collect.CompactHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        Arrays.fill(this.links, 0, size(), -1L);
        super.clear();
    }
}
