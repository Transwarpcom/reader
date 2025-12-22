package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashSet.class */
class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    private static final float LOAD_FACTOR = 1.0f;
    private static final long NEXT_MASK = 4294967295L;
    private static final long HASH_MASK = -4294967296L;

    @VisibleForTesting
    static final int DEFAULT_SIZE = 3;
    static final int UNSET = -1;
    private transient int[] table;
    private transient long[] entries;
    transient Object[] elements;
    transient int modCount;
    private transient int size;

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> set = createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    public static <E> CompactHashSet<E> create(E... elements) {
        CompactHashSet<E> set = createWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactHashSet<>(expectedSize);
    }

    CompactHashSet() {
        init(3);
    }

    CompactHashSet(int expectedSize) {
        init(expectedSize);
    }

    void init(int expectedSize) {
        Preconditions.checkArgument(expectedSize >= 0, "Initial capacity must be non-negative");
        this.modCount = Math.max(1, expectedSize);
    }

    boolean needsAllocArrays() {
        return this.table == null;
    }

    void allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int expectedSize = this.modCount;
        int buckets = Hashing.closedTableSize(expectedSize, 1.0d);
        this.table = newTable(buckets);
        this.entries = newEntries(expectedSize);
        this.elements = new Object[expectedSize];
    }

    private static int[] newTable(int size) {
        int[] array = new int[size];
        Arrays.fill(array, -1);
        return array;
    }

    private static long[] newEntries(int size) {
        long[] array = new long[size];
        Arrays.fill(array, -1L);
        return array;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getHash(long entry) {
        return (int) (entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int) entry;
    }

    private static long swapNext(long entry, int newNext) {
        return (HASH_MASK & entry) | (4294967295L & newNext);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean add(E object) {
        int last;
        long entry;
        if (needsAllocArrays()) {
            allocArrays();
        }
        long[] entries = this.entries;
        Object[] elements = this.elements;
        int hash = Hashing.smearedHash(object);
        int tableIndex = hash & hashTableMask();
        int newEntryIndex = this.size;
        int next = this.table[tableIndex];
        if (next == -1) {
            this.table[tableIndex] = newEntryIndex;
        } else {
            do {
                last = next;
                entry = entries[next];
                if (getHash(entry) == hash && Objects.equal(object, elements[next])) {
                    return false;
                }
                next = getNext(entry);
            } while (next != -1);
            entries[last] = swapNext(entry, newEntryIndex);
        }
        if (newEntryIndex == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int newSize = newEntryIndex + 1;
        resizeMeMaybe(newSize);
        insertEntry(newEntryIndex, object, hash);
        this.size = newSize;
        int oldCapacity = this.table.length;
        if (Hashing.needsResizing(newEntryIndex, oldCapacity, 1.0d)) {
            resizeTable(2 * oldCapacity);
        }
        this.modCount++;
        return true;
    }

    void insertEntry(int entryIndex, E object, int hash) {
        this.entries[entryIndex] = (hash << 32) | 4294967295L;
        this.elements[entryIndex] = object;
    }

    private void resizeMeMaybe(int newSize) {
        int entriesSize = this.entries.length;
        if (newSize > entriesSize) {
            int newCapacity = entriesSize + Math.max(1, entriesSize >>> 1);
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            if (newCapacity != entriesSize) {
                resizeEntries(newCapacity);
            }
        }
    }

    void resizeEntries(int newCapacity) {
        this.elements = Arrays.copyOf(this.elements, newCapacity);
        long[] entries = this.entries;
        int oldCapacity = entries.length;
        long[] entries2 = Arrays.copyOf(entries, newCapacity);
        if (newCapacity > oldCapacity) {
            Arrays.fill(entries2, oldCapacity, newCapacity, -1L);
        }
        this.entries = entries2;
    }

    private void resizeTable(int newCapacity) {
        int[] newTable = newTable(newCapacity);
        long[] entries = this.entries;
        int mask = newTable.length - 1;
        for (int i = 0; i < this.size; i++) {
            long oldEntry = entries[i];
            int hash = getHash(oldEntry);
            int tableIndex = hash & mask;
            int next = newTable[tableIndex];
            newTable[tableIndex] = i;
            entries[i] = (hash << 32) | (4294967295L & next);
        }
        this.table = newTable;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object object) {
        if (needsAllocArrays()) {
            return false;
        }
        int hash = Hashing.smearedHash(object);
        int next = this.table[hash & hashTableMask()];
        while (true) {
            int next2 = next;
            if (next2 != -1) {
                long entry = this.entries[next2];
                if (getHash(entry) == hash && Objects.equal(object, this.elements[next2])) {
                    return true;
                }
                next = getNext(entry);
            } else {
                return false;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(Object object) {
        if (needsAllocArrays()) {
            return false;
        }
        return remove(object, Hashing.smearedHash(object));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CanIgnoreReturnValue
    public boolean remove(Object object, int hash) {
        int tableIndex = hash & hashTableMask();
        int next = this.table[tableIndex];
        if (next == -1) {
            return false;
        }
        int last = -1;
        do {
            if (getHash(this.entries[next]) == hash && Objects.equal(object, this.elements[next])) {
                if (last == -1) {
                    this.table[tableIndex] = getNext(this.entries[next]);
                } else {
                    this.entries[last] = swapNext(this.entries[last], getNext(this.entries[next]));
                }
                moveLastEntry(next);
                this.size--;
                this.modCount++;
                return true;
            }
            last = next;
            next = getNext(this.entries[next]);
        } while (next != -1);
        return false;
    }

    void moveLastEntry(int dstIndex) {
        int previous;
        long entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            this.elements[dstIndex] = this.elements[srcIndex];
            this.elements[srcIndex] = null;
            long lastEntry = this.entries[srcIndex];
            this.entries[dstIndex] = lastEntry;
            this.entries[srcIndex] = -1;
            int tableIndex = getHash(lastEntry) & hashTableMask();
            int lastNext = this.table[tableIndex];
            if (lastNext == srcIndex) {
                this.table[tableIndex] = dstIndex;
                return;
            }
            do {
                previous = lastNext;
                entry = this.entries[lastNext];
                lastNext = getNext(entry);
            } while (lastNext != srcIndex);
            this.entries[previous] = swapNext(entry, dstIndex);
            return;
        }
        this.elements[dstIndex] = null;
        this.entries[dstIndex] = -1;
    }

    int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    int getSuccessor(int entryIndex) {
        if (entryIndex + 1 < this.size) {
            return entryIndex + 1;
        }
        return -1;
    }

    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove - 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: com.google.common.collect.CompactHashSet.1
            int expectedModCount;
            int currentIndex;
            int indexToRemove = -1;

            {
                this.expectedModCount = CompactHashSet.this.modCount;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            @Override // java.util.Iterator
            public E next() {
                checkForConcurrentModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.indexToRemove = this.currentIndex;
                E e = (E) CompactHashSet.this.elements[this.currentIndex];
                this.currentIndex = CompactHashSet.this.getSuccessor(this.currentIndex);
                return e;
            }

            @Override // java.util.Iterator
            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                this.expectedModCount++;
                CompactHashSet.this.remove(CompactHashSet.this.elements[this.indexToRemove], CompactHashSet.getHash(CompactHashSet.this.entries[this.indexToRemove]));
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public Spliterator<E> spliterator() {
        if (needsAllocArrays()) {
            return Spliterators.spliterator(new Object[0], 17);
        }
        return Spliterators.spliterator(this.elements, 0, this.size, 17);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super E> action) {
        Preconditions.checkNotNull(action);
        int iFirstEntryIndex = firstEntryIndex();
        while (true) {
            int i = iFirstEntryIndex;
            if (i >= 0) {
                action.accept(this.elements[i]);
                iFirstEntryIndex = getSuccessor(i);
            } else {
                return;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        if (needsAllocArrays()) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        return (T[]) ObjectArrays.toArrayImpl(this.elements, 0, this.size, tArr);
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        int size = this.size;
        if (size < this.entries.length) {
            resizeEntries(size);
        }
        int minimumTableSize = Hashing.closedTableSize(size, 1.0d);
        if (minimumTableSize < this.table.length) {
            resizeTable(minimumTableSize);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.modCount++;
        Arrays.fill(this.elements, 0, this.size, (Object) null);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, 0, this.size, -1L);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size);
        int iFirstEntryIndex = firstEntryIndex();
        while (true) {
            int i = iFirstEntryIndex;
            if (i >= 0) {
                stream.writeObject(this.elements[i]);
                iFirstEntryIndex = getSuccessor(i);
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException {
        stream.defaultReadObject();
        int elementCount = stream.readInt();
        if (elementCount < 0) {
            throw new InvalidObjectException("Invalid size: " + elementCount);
        }
        init(elementCount);
        for (int i = 0; i < elementCount; i++) {
            add(stream.readObject());
        }
    }
}
