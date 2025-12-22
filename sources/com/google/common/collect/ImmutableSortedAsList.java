package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Comparator;
import java.util.Spliterator;

@GwtCompatible(emulated = true)
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/collect/ImmutableSortedAsList.class */
final class ImmutableSortedAsList<E> extends RegularImmutableAsList<E> implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> backingSet, ImmutableList<E> backingList) {
        super(backingSet, backingList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.RegularImmutableAsList, com.google.common.collect.ImmutableAsList
    public ImmutableSortedSet<E> delegateCollection() {
        return (ImmutableSortedSet) super.delegateCollection();
    }

    @Override // com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return delegateCollection().comparator();
    }

    @Override // com.google.common.collect.ImmutableList, java.util.List
    @GwtIncompatible
    public int indexOf(Object target) {
        int index = delegateCollection().indexOf(target);
        if (index < 0 || !get(index).equals(target)) {
            return -1;
        }
        return index;
    }

    @Override // com.google.common.collect.ImmutableList, java.util.List
    @GwtIncompatible
    public int lastIndexOf(Object target) {
        return indexOf(target);
    }

    @Override // com.google.common.collect.ImmutableAsList, com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object target) {
        return indexOf(target) >= 0;
    }

    @Override // com.google.common.collect.ImmutableList
    @GwtIncompatible
    ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        ImmutableList<E> parentSubList = super.subListUnchecked(fromIndex, toIndex);
        return new RegularImmutableSortedSet(parentSubList, comparator()).asList();
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.Collection, java.lang.Iterable
    public Spliterator<E> spliterator() {
        int size = size();
        ImmutableList<? extends E> immutableListDelegateList = delegateList();
        immutableListDelegateList.getClass();
        return CollectSpliterators.indexed(size, 1301, immutableListDelegateList::get, comparator());
    }
}
