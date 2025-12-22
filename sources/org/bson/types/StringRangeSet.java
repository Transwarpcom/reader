package org.bson.types;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/StringRangeSet.class */
class StringRangeSet implements Set<String> {
    private static final String[] STRINGS = new String[1024];
    private final int size;

    static {
        for (int i = 0; i < STRINGS.length; i++) {
            STRINGS[i] = String.valueOf(i);
        }
    }

    StringRangeSet(int size) {
        Assertions.isTrue("size >= 0", size >= 0);
        this.size = size;
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object o) throws NumberFormatException {
        if (!(o instanceof String)) {
            return false;
        }
        try {
            int i = Integer.parseInt((String) o);
            if (i >= 0) {
                if (i < size()) {
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<String> iterator() {
        return new Iterator<String>() { // from class: org.bson.types.StringRangeSet.1
            private int cur = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.cur < StringRangeSet.this.size;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                StringRangeSet stringRangeSet = StringRangeSet.this;
                int i = this.cur;
                this.cur = i + 1;
                return stringRangeSet.intToString(i);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        Object[] retVal = new Object[size()];
        for (int i = 0; i < size(); i++) {
            retVal[i] = intToString(i);
        }
        return retVal;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        Object[] objArr;
        if (tArr.length >= size()) {
            objArr = tArr;
        } else {
            objArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.size);
        }
        T[] tArr2 = (T[]) objArr;
        for (int i = 0; i < size(); i++) {
            tArr2[i] = intToString(i);
        }
        if (tArr.length > size()) {
            tArr[this.size] = null;
        }
        return tArr2;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(String integer) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String intToString(int i) {
        if (i < STRINGS.length) {
            return STRINGS[i];
        }
        return Integer.toString(i);
    }
}
