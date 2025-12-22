package cn.hutool.core.collection;

import java.io.Serializable;
import java.util.Enumeration;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/collection/EnumerationIter.class */
public class EnumerationIter<E> implements IterableIter<E>, Serializable {
    private static final long serialVersionUID = 1;
    private final Enumeration<E> e;

    public EnumerationIter(Enumeration<E> enumeration) {
        this.e = enumeration;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.e.hasMoreElements();
    }

    @Override // java.util.Iterator
    public E next() {
        return this.e.nextElement();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
