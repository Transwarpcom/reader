package org.antlr.v4.runtime.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/antlr4-runtime-4.7.2.jar:org/antlr/v4/runtime/misc/OrderedHashSet.class */
public class OrderedHashSet<T> extends LinkedHashSet<T> {
    protected ArrayList<T> elements = new ArrayList<>();

    public T get(int i) {
        return this.elements.get(i);
    }

    public T set(int i, T value) {
        T oldElement = this.elements.get(i);
        this.elements.set(i, value);
        super.remove(oldElement);
        super.add(value);
        return oldElement;
    }

    public boolean remove(int i) {
        T o = this.elements.remove(i);
        return super.remove(o);
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(T value) {
        boolean result = super.add(value);
        if (result) {
            this.elements.add(value);
        }
        return result;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.elements.clear();
        super.clear();
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public boolean equals(Object o) {
        if (!(o instanceof OrderedHashSet)) {
            return false;
        }
        boolean same = this.elements != null && this.elements.equals(((OrderedHashSet) o).elements);
        return same;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<T> iterator() {
        return this.elements.iterator();
    }

    public List<T> elements() {
        return this.elements;
    }

    @Override // java.util.HashSet
    public Object clone() {
        OrderedHashSet<T> dup = (OrderedHashSet) super.clone();
        dup.elements = new ArrayList<>(this.elements);
        return dup;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return this.elements.toArray();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return this.elements.toString();
    }
}
