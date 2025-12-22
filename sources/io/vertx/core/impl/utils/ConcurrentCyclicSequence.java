package io.vertx.core.impl.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/utils/ConcurrentCyclicSequence.class */
public class ConcurrentCyclicSequence<T> implements Iterable<T>, Iterator<T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final AtomicInteger pos;
    private final Object[] elements;

    public ConcurrentCyclicSequence() {
        this(0, EMPTY_ARRAY);
    }

    @SafeVarargs
    public ConcurrentCyclicSequence(T... elements) {
        this(0, Arrays.copyOf(elements, elements.length, Object[].class));
    }

    private ConcurrentCyclicSequence(int pos, Object[] elements) {
        this.pos = new AtomicInteger(pos);
        this.elements = elements;
    }

    public int index() {
        if (this.elements.length > 0) {
            return this.pos.get() % this.elements.length;
        }
        return 0;
    }

    public T first() {
        if (this.elements.length > 0) {
            return (T) this.elements[0];
        }
        return null;
    }

    public ConcurrentCyclicSequence<T> add(T element) {
        int len = this.elements.length;
        Object[] copy = Arrays.copyOf(this.elements, len + 1);
        copy[len] = element;
        return new ConcurrentCyclicSequence<>(this.pos.get(), copy);
    }

    public ConcurrentCyclicSequence<T> remove(T element) {
        int len = this.elements.length;
        for (int i = 0; i < len; i++) {
            if (Objects.equals(element, this.elements[i])) {
                if (len > 1) {
                    Object[] copy = new Object[len - 1];
                    System.arraycopy(this.elements, 0, copy, 0, i);
                    System.arraycopy(this.elements, i + 1, copy, i, (len - i) - 1);
                    return new ConcurrentCyclicSequence<>(this.pos.get() % copy.length, copy);
                }
                return new ConcurrentCyclicSequence<>();
            }
        }
        return this;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return true;
    }

    @Override // java.util.Iterator
    public T next() {
        int length = this.elements.length;
        switch (length) {
            case 0:
                return null;
            case 1:
                return (T) this.elements[0];
            default:
                return (T) this.elements[Math.abs(this.pos.getAndIncrement() % length)];
        }
    }

    public int size() {
        return this.elements.length;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return Arrays.asList(this.elements).iterator();
    }
}
