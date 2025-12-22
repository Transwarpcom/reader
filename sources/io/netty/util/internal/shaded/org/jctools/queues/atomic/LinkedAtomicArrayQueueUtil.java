package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/atomic/LinkedAtomicArrayQueueUtil.class */
final class LinkedAtomicArrayQueueUtil {
    private LinkedAtomicArrayQueueUtil() {
    }

    public static <E> E lvElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return (E) AtomicReferenceArrayQueue.lvElement(atomicReferenceArray, i);
    }

    public static <E> E lpElement(AtomicReferenceArray<E> atomicReferenceArray, int i) {
        return (E) AtomicReferenceArrayQueue.lpElement(atomicReferenceArray, i);
    }

    public static <E> void spElement(AtomicReferenceArray<E> buffer, int offset, E value) {
        AtomicReferenceArrayQueue.spElement(buffer, offset, value);
    }

    public static <E> void svElement(AtomicReferenceArray<E> buffer, int offset, E value) {
        AtomicReferenceArrayQueue.svElement(buffer, offset, value);
    }

    static <E> void soElement(AtomicReferenceArray buffer, int offset, Object value) {
        buffer.lazySet(offset, value);
    }

    static int calcElementOffset(long index, long mask) {
        return (int) (index & mask);
    }

    static <E> AtomicReferenceArray<E> allocate(int capacity) {
        return new AtomicReferenceArray<>(capacity);
    }

    static int length(AtomicReferenceArray<?> buf) {
        return buf.length();
    }

    static int modifiedCalcElementOffset(long index, long mask) {
        return ((int) (index & mask)) >> 1;
    }

    static int nextArrayOffset(AtomicReferenceArray<?> curr) {
        return length(curr) - 1;
    }
}
