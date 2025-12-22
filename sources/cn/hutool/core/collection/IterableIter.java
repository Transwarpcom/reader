package cn.hutool.core.collection;

import java.util.Iterator;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/collection/IterableIter.class */
public interface IterableIter<T> extends Iterable<T>, Iterator<T> {
    @Override // java.lang.Iterable
    default Iterator<T> iterator() {
        return this;
    }
}
