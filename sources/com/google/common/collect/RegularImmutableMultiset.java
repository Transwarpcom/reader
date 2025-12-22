package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Arrays;
import java.util.Collection;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/RegularImmutableMultiset.class */
class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final ImmutableMultiset<Object> EMPTY = create(ImmutableList.of());

    @VisibleForTesting
    static final double MAX_LOAD_FACTOR = 1.0d;

    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001d;

    @VisibleForTesting
    static final int MAX_HASH_BUCKET_LENGTH = 9;
    private final transient Multisets.ImmutableEntry<E>[] entries;
    private final transient Multisets.ImmutableEntry<E>[] hashTable;
    private final transient int size;
    private final transient int hashCode;

    @LazyInit
    private transient ImmutableSet<E> elementSet;

    static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> entries) {
        Multisets.ImmutableEntry<E> nonTerminalEntry;
        int distinct = entries.size();
        Multisets.ImmutableEntry<E>[] entryArray = new Multisets.ImmutableEntry[distinct];
        if (distinct == 0) {
            return new RegularImmutableMultiset(entryArray, null, 0, 0, ImmutableSet.of());
        }
        int tableSize = Hashing.closedTableSize(distinct, 1.0d);
        int mask = tableSize - 1;
        Multisets.ImmutableEntry<E>[] hashTable = new Multisets.ImmutableEntry[tableSize];
        int index = 0;
        int hashCode = 0;
        long size = 0;
        for (Multiset.Entry<? extends E> entry : entries) {
            Object objCheckNotNull = Preconditions.checkNotNull(entry.getElement());
            int count = entry.getCount();
            int hash = objCheckNotNull.hashCode();
            int bucket = Hashing.smear(hash) & mask;
            Multisets.ImmutableEntry<E> bucketHead = hashTable[bucket];
            if (bucketHead == null) {
                boolean canReuseEntry = (entry instanceof Multisets.ImmutableEntry) && !(entry instanceof NonTerminalEntry);
                nonTerminalEntry = canReuseEntry ? (Multisets.ImmutableEntry) entry : new Multisets.ImmutableEntry<>(objCheckNotNull, count);
            } else {
                nonTerminalEntry = new NonTerminalEntry<>(objCheckNotNull, count, bucketHead);
            }
            Multisets.ImmutableEntry<E> newEntry = nonTerminalEntry;
            hashCode += hash ^ count;
            int i = index;
            index++;
            entryArray[i] = newEntry;
            hashTable[bucket] = newEntry;
            size += count;
        }
        if (hashFloodingDetected(hashTable)) {
            return JdkBackedImmutableMultiset.create(ImmutableList.asImmutableList(entryArray));
        }
        return new RegularImmutableMultiset(entryArray, hashTable, Ints.saturatedCast(size), hashCode, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0025, code lost:
    
        r4 = r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean hashFloodingDetected(com.google.common.collect.Multisets.ImmutableEntry<?>[] r3) {
        /*
            r0 = 0
            r4 = r0
        L2:
            r0 = r4
            r1 = r3
            int r1 = r1.length
            if (r0 >= r1) goto L2b
            r0 = 0
            r5 = r0
            r0 = r3
            r1 = r4
            r0 = r0[r1]
            r6 = r0
        Le:
            r0 = r6
            if (r0 == 0) goto L25
            int r5 = r5 + 1
            r0 = r5
            r1 = 9
            if (r0 <= r1) goto L1d
            r0 = 1
            return r0
        L1d:
            r0 = r6
            com.google.common.collect.Multisets$ImmutableEntry r0 = r0.nextInBucket()
            r6 = r0
            goto Le
        L25:
            int r4 = r4 + 1
            goto L2
        L2b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMultiset.hashFloodingDetected(com.google.common.collect.Multisets$ImmutableEntry[]):boolean");
    }

    private RegularImmutableMultiset(Multisets.ImmutableEntry<E>[] entries, Multisets.ImmutableEntry<E>[] hashTable, int size, int hashCode, ImmutableSet<E> elementSet) {
        this.entries = entries;
        this.hashTable = hashTable;
        this.size = size;
        this.hashCode = hashCode;
        this.elementSet = elementSet;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/RegularImmutableMultiset$NonTerminalEntry.class */
    private static final class NonTerminalEntry<E> extends Multisets.ImmutableEntry<E> {
        private final Multisets.ImmutableEntry<E> nextInBucket;

        NonTerminalEntry(E element, int count, Multisets.ImmutableEntry<E> nextInBucket) {
            super(element, count);
            this.nextInBucket = nextInBucket;
        }

        @Override // com.google.common.collect.Multisets.ImmutableEntry
        public Multisets.ImmutableEntry<E> nextInBucket() {
            return this.nextInBucket;
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        Multisets.ImmutableEntry<E>[] hashTable = this.hashTable;
        if (element == null || hashTable == null) {
            return 0;
        }
        int hash = Hashing.smearedHash(element);
        int mask = hashTable.length - 1;
        Multisets.ImmutableEntry<E> immutableEntryNextInBucket = hashTable[hash & mask];
        while (true) {
            Multisets.ImmutableEntry<E> entry = immutableEntryNextInBucket;
            if (entry != null) {
                if (!Objects.equal(element, entry.getElement())) {
                    immutableEntryNextInBucket = entry.nextInBucket();
                } else {
                    return entry.getCount();
                }
            } else {
                return 0;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> result = this.elementSet;
        if (result != null) {
            return result;
        }
        ImmutableMultiset.ElementSet elementSet = new ImmutableMultiset.ElementSet(Arrays.asList(this.entries), this);
        this.elementSet = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry<E> getEntry(int index) {
        return this.entries[index];
    }

    @Override // com.google.common.collect.ImmutableMultiset, java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return this.hashCode;
    }
}
