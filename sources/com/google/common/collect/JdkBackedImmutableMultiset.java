package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/JdkBackedImmutableMultiset.class */
final class JdkBackedImmutableMultiset<E> extends ImmutableMultiset<E> {
    private final Map<E, Integer> delegateMap;
    private final ImmutableList<Multiset.Entry<E>> entries;
    private final long size;
    private transient ImmutableSet<E> elementSet;

    static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> entries) {
        Multiset.Entry<E>[] entriesArray = (Multiset.Entry[]) entries.toArray(new Multiset.Entry[0]);
        HashMap mapNewHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(entriesArray.length);
        long size = 0;
        for (int i = 0; i < entriesArray.length; i++) {
            Multiset.Entry<E> entry = entriesArray[i];
            int count = entry.getCount();
            size += count;
            Object objCheckNotNull = Preconditions.checkNotNull(entry.getElement());
            mapNewHashMapWithExpectedSize.put(objCheckNotNull, Integer.valueOf(count));
            if (!(entry instanceof Multisets.ImmutableEntry)) {
                entriesArray[i] = Multisets.immutableEntry(objCheckNotNull, count);
            }
        }
        return new JdkBackedImmutableMultiset(mapNewHashMapWithExpectedSize, ImmutableList.asImmutableList(entriesArray), size);
    }

    private JdkBackedImmutableMultiset(Map<E, Integer> delegateMap, ImmutableList<Multiset.Entry<E>> entries, long size) {
        this.delegateMap = delegateMap;
        this.entries = entries;
        this.size = size;
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object element) {
        return this.delegateMap.getOrDefault(element, 0).intValue();
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> result = this.elementSet;
        if (result != null) {
            return result;
        }
        ImmutableMultiset.ElementSet elementSet = new ImmutableMultiset.ElementSet(this.entries, this);
        this.elementSet = elementSet;
        return elementSet;
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry<E> getEntry(int index) {
        return this.entries.get(index);
    }

    @Override // com.google.common.collect.ImmutableCollection
    boolean isPartialView() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }
}
