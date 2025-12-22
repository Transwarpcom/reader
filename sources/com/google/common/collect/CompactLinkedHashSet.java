package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Spliterator;
import java.util.Spliterators;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactLinkedHashSet.class */
class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int[] predecessor;
    private transient int[] successor;
    private transient int firstEntry;
    private transient int lastEntry;

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> set = createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    public static <E> CompactLinkedHashSet<E> create(E... elements) {
        CompactLinkedHashSet<E> set = createWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashSet<>(expectedSize);
    }

    CompactLinkedHashSet() {
    }

    CompactLinkedHashSet(int expectedSize) {
        super(expectedSize);
    }

    @Override // com.google.common.collect.CompactHashSet
    void init(int expectedSize) {
        super.init(expectedSize);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.CompactHashSet
    void allocArrays() {
        super.allocArrays();
        int expectedSize = this.elements.length;
        this.predecessor = new int[expectedSize];
        this.successor = new int[expectedSize];
        Arrays.fill(this.predecessor, -1);
        Arrays.fill(this.successor, -1);
    }

    private int getPredecessor(int entry) {
        return this.predecessor[entry];
    }

    @Override // com.google.common.collect.CompactHashSet
    int getSuccessor(int entry) {
        return this.successor[entry];
    }

    private void setSuccessor(int entry, int succ) {
        this.successor[entry] = succ;
    }

    private void setPredecessor(int entry, int pred) {
        this.predecessor[entry] = pred;
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

    @Override // com.google.common.collect.CompactHashSet
    void insertEntry(int entryIndex, E object, int hash) {
        super.insertEntry(entryIndex, object, hash);
        setSucceeds(this.lastEntry, entryIndex);
        setSucceeds(entryIndex, -2);
    }

    @Override // com.google.common.collect.CompactHashSet
    void moveLastEntry(int dstIndex) {
        int srcIndex = size() - 1;
        super.moveLastEntry(dstIndex);
        setSucceeds(getPredecessor(dstIndex), getSuccessor(dstIndex));
        if (dstIndex < srcIndex) {
            setSucceeds(getPredecessor(srcIndex), dstIndex);
            setSucceeds(dstIndex, getSuccessor(srcIndex));
        }
        this.predecessor[srcIndex] = -1;
        this.successor[srcIndex] = -1;
    }

    @Override // com.google.common.collect.CompactHashSet
    void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        int oldCapacity = this.predecessor.length;
        this.predecessor = Arrays.copyOf(this.predecessor, newCapacity);
        this.successor = Arrays.copyOf(this.successor, newCapacity);
        if (oldCapacity < newCapacity) {
            Arrays.fill(this.predecessor, oldCapacity, newCapacity, -1);
            Arrays.fill(this.successor, oldCapacity, newCapacity, -1);
        }
    }

    @Override // com.google.common.collect.CompactHashSet
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashSet
    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= size() ? indexRemoved : indexBeforeRemove;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.Collection, java.lang.Iterable, java.util.Set
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 17);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        Arrays.fill(this.predecessor, 0, size(), -1);
        Arrays.fill(this.successor, 0, size(), -1);
        super.clear();
    }
}
