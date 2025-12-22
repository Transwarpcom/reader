package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashMap.class */
class CompactHashMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final float LOAD_FACTOR = 1.0f;
    private static final long NEXT_MASK = 4294967295L;
    private static final long HASH_MASK = -4294967296L;
    static final int DEFAULT_SIZE = 3;
    static final int UNSET = -1;
    private transient int[] table;

    @VisibleForTesting
    transient long[] entries;

    @VisibleForTesting
    transient Object[] keys;

    @VisibleForTesting
    transient Object[] values;
    transient int modCount;
    private transient int size;
    private transient Set<K> keySetView;
    private transient Set<Map.Entry<K, V>> entrySetView;
    private transient Collection<V> valuesView;

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactHashMap<>(expectedSize);
    }

    CompactHashMap() {
        init(3);
    }

    CompactHashMap(int expectedSize) {
        init(expectedSize);
    }

    void init(int expectedSize) {
        Preconditions.checkArgument(expectedSize >= 0, "Expected size must be non-negative");
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
        this.keys = new Object[expectedSize];
        this.values = new Object[expectedSize];
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

    private static int getHash(long entry) {
        return (int) (entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int) entry;
    }

    private static long swapNext(long entry, int newNext) {
        return (HASH_MASK & entry) | (4294967295L & newNext);
    }

    void accessEntry(int index) {
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V put(K k, V v) {
        int i;
        long j;
        if (needsAllocArrays()) {
            allocArrays();
        }
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        Object[] objArr2 = this.values;
        int iSmearedHash = Hashing.smearedHash(k);
        int iHashTableMask = iSmearedHash & hashTableMask();
        int i2 = this.size;
        int next = this.table[iHashTableMask];
        if (next == -1) {
            this.table[iHashTableMask] = i2;
        } else {
            do {
                i = next;
                j = jArr[next];
                if (getHash(j) == iSmearedHash && Objects.equal(k, objArr[next])) {
                    V v2 = (V) objArr2[next];
                    objArr2[next] = v;
                    accessEntry(next);
                    return v2;
                }
                next = getNext(j);
            } while (next != -1);
            jArr[i] = swapNext(j, i2);
        }
        if (i2 == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int i3 = i2 + 1;
        resizeMeMaybe(i3);
        insertEntry(i2, k, v, iSmearedHash);
        this.size = i3;
        int length = this.table.length;
        if (Hashing.needsResizing(i2, length, 1.0d)) {
            resizeTable(2 * length);
        }
        this.modCount++;
        return null;
    }

    void insertEntry(int entryIndex, K key, V value, int hash) {
        this.entries[entryIndex] = (hash << 32) | 4294967295L;
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
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
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
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

    /* JADX INFO: Access modifiers changed from: private */
    public int indexOf(Object key) {
        if (needsAllocArrays()) {
            return -1;
        }
        int hash = Hashing.smearedHash(key);
        int next = this.table[hash & hashTableMask()];
        while (true) {
            int next2 = next;
            if (next2 != -1) {
                long entry = this.entries[next2];
                if (getHash(entry) == hash && Objects.equal(key, this.keys[next2])) {
                    return next2;
                }
                next = getNext(entry);
            } else {
                return -1;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object key) {
        return indexOf(key) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        int iIndexOf = indexOf(obj);
        accessEntry(iIndexOf);
        if (iIndexOf == -1) {
            return null;
        }
        return (V) this.values[iIndexOf];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public V remove(Object key) {
        if (needsAllocArrays()) {
            return null;
        }
        return remove(key, Hashing.smearedHash(key));
    }

    private V remove(Object obj, int i) {
        int iHashTableMask = i & hashTableMask();
        int next = this.table[iHashTableMask];
        if (next == -1) {
            return null;
        }
        int i2 = -1;
        do {
            if (getHash(this.entries[next]) == i && Objects.equal(obj, this.keys[next])) {
                V v = (V) this.values[next];
                if (i2 == -1) {
                    this.table[iHashTableMask] = getNext(this.entries[next]);
                } else {
                    this.entries[i2] = swapNext(this.entries[i2], getNext(this.entries[next]));
                }
                moveLastEntry(next);
                this.size--;
                this.modCount++;
                return v;
            }
            i2 = next;
            next = getNext(this.entries[next]);
        } while (next != -1);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CanIgnoreReturnValue
    public V removeEntry(int entryIndex) {
        return remove(this.keys[entryIndex], getHash(this.entries[entryIndex]));
    }

    void moveLastEntry(int dstIndex) {
        int previous;
        long entry;
        int srcIndex = size() - 1;
        if (dstIndex < srcIndex) {
            this.keys[dstIndex] = this.keys[srcIndex];
            this.values[dstIndex] = this.values[srcIndex];
            this.keys[srcIndex] = null;
            this.values[srcIndex] = null;
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
        this.keys[dstIndex] = null;
        this.values[dstIndex] = null;
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

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashMap$Itr.class */
    private abstract class Itr<T> implements Iterator<T> {
        int expectedModCount;
        int currentIndex;
        int indexToRemove;

        abstract T getOutput(int i);

        private Itr() {
            this.expectedModCount = CompactHashMap.this.modCount;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.indexToRemove = this.currentIndex;
            T result = getOutput(this.currentIndex);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return result;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            this.expectedModCount++;
            CompactHashMap.this.removeEntry(this.indexToRemove);
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    @Override // java.util.Map
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(function);
        for (int i = 0; i < this.size; i++) {
            this.values[i] = function.apply(this.keys[i], this.values[i]);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        if (this.keySetView != null) {
            return this.keySetView;
        }
        Set<K> setCreateKeySet = createKeySet();
        this.keySetView = setCreateKeySet;
        return setCreateKeySet;
    }

    Set<K> createKeySet() {
        return new KeySetView();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashMap$KeySetView.class */
    class KeySetView extends Maps.KeySet<K, V> {
        KeySetView() {
            super(CompactHashMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            if (!CompactHashMap.this.needsAllocArrays()) {
                return ObjectArrays.copyAsObjectArray(CompactHashMap.this.keys, 0, CompactHashMap.this.size);
            }
            return new Object[0];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            if (!CompactHashMap.this.needsAllocArrays()) {
                return (T[]) ObjectArrays.toArrayImpl(CompactHashMap.this.keys, 0, CompactHashMap.this.size, tArr);
            }
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            int index = CompactHashMap.this.indexOf(o);
            if (index != -1) {
                CompactHashMap.this.removeEntry(index);
                return true;
            }
            return false;
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public Spliterator<K> spliterator() {
            if (!CompactHashMap.this.needsAllocArrays()) {
                return Spliterators.spliterator(CompactHashMap.this.keys, 0, CompactHashMap.this.size, 17);
            }
            return Spliterators.spliterator(new Object[0], 17);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.lang.Iterable
        public void forEach(Consumer<? super K> action) {
            Preconditions.checkNotNull(action);
            int iFirstEntryIndex = CompactHashMap.this.firstEntryIndex();
            while (true) {
                int i = iFirstEntryIndex;
                if (i >= 0) {
                    action.accept(CompactHashMap.this.keys[i]);
                    iFirstEntryIndex = CompactHashMap.this.getSuccessor(i);
                } else {
                    return;
                }
            }
        }
    }

    Iterator<K> keySetIterator() {
        return new CompactHashMap<K, V>.Itr<K>() { // from class: com.google.common.collect.CompactHashMap.1
            @Override // com.google.common.collect.CompactHashMap.Itr
            K getOutput(int i) {
                return (K) CompactHashMap.this.keys[i];
            }
        };
    }

    @Override // java.util.Map
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        int iFirstEntryIndex = firstEntryIndex();
        while (true) {
            int i = iFirstEntryIndex;
            if (i >= 0) {
                action.accept(this.keys[i], this.values[i]);
                iFirstEntryIndex = getSuccessor(i);
            } else {
                return;
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySetView != null) {
            return this.entrySetView;
        }
        Set<Map.Entry<K, V>> setCreateEntrySet = createEntrySet();
        this.entrySetView = setCreateEntrySet;
        return setCreateEntrySet;
    }

    Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashMap$EntrySetView.class */
    class EntrySetView extends Maps.EntrySet<K, V> {
        EntrySetView() {
        }

        @Override // com.google.common.collect.Maps.EntrySet
        Map<K, V> map() {
            return CompactHashMap.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return CollectSpliterators.indexed(CompactHashMap.this.size, 17, x$0 -> {
                return new MapEntry(x$0);
            });
        }

        @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> entry = (Map.Entry) o;
                int index = CompactHashMap.this.indexOf(entry.getKey());
                return index != -1 && Objects.equal(CompactHashMap.this.values[index], entry.getValue());
            }
            return false;
        }

        @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> entry = (Map.Entry) o;
                int index = CompactHashMap.this.indexOf(entry.getKey());
                if (index != -1 && Objects.equal(CompactHashMap.this.values[index], entry.getValue())) {
                    CompactHashMap.this.removeEntry(index);
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    Iterator<Map.Entry<K, V>> entrySetIterator() {
        return new CompactHashMap<K, V>.Itr<Map.Entry<K, V>>() { // from class: com.google.common.collect.CompactHashMap.2
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.CompactHashMap.Itr
            public Map.Entry<K, V> getOutput(int entry) {
                return new MapEntry(entry);
            }
        };
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashMap$MapEntry.class */
    final class MapEntry extends AbstractMapEntry<K, V> {
        private final K key;
        private int lastKnownIndex;

        MapEntry(int i) {
            this.key = (K) CompactHashMap.this.keys[i];
            this.lastKnownIndex = i;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            if (this.lastKnownIndex == -1 || this.lastKnownIndex >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return null;
            }
            return (V) CompactHashMap.this.values[this.lastKnownIndex];
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V setValue(V v) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                CompactHashMap.this.put(this.key, v);
                return null;
            }
            V v2 = (V) CompactHashMap.this.values[this.lastKnownIndex];
            CompactHashMap.this.values[this.lastKnownIndex] = v;
            return v2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(Object value) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equal(value, this.values[i])) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Collection<V> values() {
        if (this.valuesView != null) {
            return this.valuesView;
        }
        Collection<V> collectionCreateValues = createValues();
        this.valuesView = collectionCreateValues;
        return collectionCreateValues;
    }

    Collection<V> createValues() {
        return new ValuesView();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/CompactHashMap$ValuesView.class */
    class ValuesView extends Maps.Values<K, V> {
        ValuesView() {
            super(CompactHashMap.this);
        }

        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }

        @Override // com.google.common.collect.Maps.Values, java.lang.Iterable
        public void forEach(Consumer<? super V> action) {
            Preconditions.checkNotNull(action);
            int iFirstEntryIndex = CompactHashMap.this.firstEntryIndex();
            while (true) {
                int i = iFirstEntryIndex;
                if (i >= 0) {
                    action.accept(CompactHashMap.this.values[i]);
                    iFirstEntryIndex = CompactHashMap.this.getSuccessor(i);
                } else {
                    return;
                }
            }
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            if (!CompactHashMap.this.needsAllocArrays()) {
                return Spliterators.spliterator(CompactHashMap.this.values, 0, CompactHashMap.this.size, 16);
            }
            return Spliterators.spliterator(new Object[0], 16);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            if (!CompactHashMap.this.needsAllocArrays()) {
                return ObjectArrays.copyAsObjectArray(CompactHashMap.this.values, 0, CompactHashMap.this.size);
            }
            return new Object[0];
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            if (!CompactHashMap.this.needsAllocArrays()) {
                return (T[]) ObjectArrays.toArrayImpl(CompactHashMap.this.values, 0, CompactHashMap.this.size, tArr);
            }
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
    }

    Iterator<V> valuesIterator() {
        return new CompactHashMap<K, V>.Itr<V>() { // from class: com.google.common.collect.CompactHashMap.3
            @Override // com.google.common.collect.CompactHashMap.Itr
            V getOutput(int i) {
                return (V) CompactHashMap.this.values[i];
            }
        };
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

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
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
                stream.writeObject(this.keys[i]);
                stream.writeObject(this.values[i]);
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
            put(stream.readObject(), stream.readObject());
        }
    }
}
