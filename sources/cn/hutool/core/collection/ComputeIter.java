package cn.hutool.core.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/collection/ComputeIter.class */
public abstract class ComputeIter<T> implements Iterator<T> {
    private T next;
    private boolean finished;

    protected abstract T computeNext();

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (null != this.next) {
            return true;
        }
        if (this.finished) {
            return false;
        }
        T result = computeNext();
        if (null == result) {
            this.finished = true;
            return false;
        }
        this.next = result;
        return true;
    }

    @Override // java.util.Iterator
    public T next() {
        if (false == hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        T result = this.next;
        this.next = null;
        return result;
    }

    public void finish() {
        this.finished = true;
        this.next = null;
    }
}
