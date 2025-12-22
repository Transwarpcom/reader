package org.bson;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/LazyBSONList.class */
public class LazyBSONList extends LazyBSONObject implements List {
    public LazyBSONList(byte[] bytes, LazyBSONCallback callback) {
        super(bytes, callback);
    }

    public LazyBSONList(byte[] bytes, int offset, LazyBSONCallback callback) {
        super(bytes, offset, callback);
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return keySet().size();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return new LazyBSONListIterator();
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection collection) {
        Set<Object> values = new HashSet<>();
        Iterator it = iterator();
        while (it.hasNext()) {
            Object o = it.next();
            values.add(o);
        }
        return values.containsAll(collection);
    }

    @Override // java.util.List
    public Object get(int index) {
        return get(String.valueOf(index));
    }

    @Override // java.util.List
    public int indexOf(Object o) {
        Iterator it = iterator();
        int pos = 0;
        while (it.hasNext()) {
            if (!o.equals(it.next())) {
                pos++;
            } else {
                return pos;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(Object o) {
        int lastFound = -1;
        Iterator it = iterator();
        int pos = 0;
        while (it.hasNext()) {
            if (o.equals(it.next())) {
                lastFound = pos;
            }
            pos++;
        }
        return lastFound;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/LazyBSONList$LazyBSONListIterator.class */
    public class LazyBSONListIterator implements Iterator {
        private final BsonBinaryReader reader;
        private BsonType cachedBsonType;

        public LazyBSONListIterator() {
            this.reader = LazyBSONList.this.getBsonReader();
            this.reader.readStartDocument();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.cachedBsonType == null) {
                this.cachedBsonType = this.reader.readBsonType();
            }
            return this.cachedBsonType != BsonType.END_OF_DOCUMENT;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            this.cachedBsonType = null;
            this.reader.readName();
            return LazyBSONList.this.readValue(this.reader);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported");
        }
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Operation is not supported instance of this type");
    }

    @Override // java.util.List
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Operation is not supported instance of this type");
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object o) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List
    public Object set(int index, Object element) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List
    public void add(int index, Object element) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List
    public Object remove(int index) {
        throw new UnsupportedOperationException("Object is read only");
    }

    @Override // java.util.List
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        throw new UnsupportedOperationException("Operation is not supported");
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Operation is not supported");
    }
}
