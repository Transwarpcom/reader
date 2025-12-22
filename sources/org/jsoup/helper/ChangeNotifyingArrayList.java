package org.jsoup.helper;

import java.util.ArrayList;
import java.util.Collection;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/helper/ChangeNotifyingArrayList.class */
public abstract class ChangeNotifyingArrayList<E> extends ArrayList<E> {
    public abstract void onContentsChanged();

    public ChangeNotifyingArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        onContentsChanged();
        return (E) super.set(i, e);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        onContentsChanged();
        return super.add(e);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int index, E element) {
        onContentsChanged();
        super.add(index, element);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public E remove(int i) {
        onContentsChanged();
        return (E) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object o) {
        onContentsChanged();
        return super.remove(o);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        onContentsChanged();
        super.clear();
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> c) {
        onContentsChanged();
        return super.addAll(c);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends E> c) {
        onContentsChanged();
        return super.addAll(index, c);
    }

    @Override // java.util.ArrayList, java.util.AbstractList
    protected void removeRange(int fromIndex, int toIndex) {
        onContentsChanged();
        super.removeRange(fromIndex, toIndex);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> c) {
        onContentsChanged();
        return super.removeAll(c);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> c) {
        onContentsChanged();
        return super.retainAll(c);
    }
}
