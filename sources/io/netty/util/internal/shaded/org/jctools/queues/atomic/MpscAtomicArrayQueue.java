package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/atomic/MpscAtomicArrayQueue.class */
public class MpscAtomicArrayQueue<E> extends MpscAtomicArrayQueueL3Pad<E> {
    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue, java.util.AbstractCollection
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public MpscAtomicArrayQueue(int capacity) {
        super(capacity);
    }

    public boolean offerIfBelowThreshold(E e, int threshold) {
        long pIndex;
        if (null == e) {
            throw new NullPointerException();
        }
        int mask = this.mask;
        long capacity = mask + 1;
        long producerLimit = lvProducerLimit();
        do {
            pIndex = lvProducerIndex();
            long available = producerLimit - pIndex;
            long size = capacity - available;
            if (size >= threshold) {
                long cIndex = lvConsumerIndex();
                long size2 = pIndex - cIndex;
                if (size2 >= threshold) {
                    return false;
                }
                producerLimit = cIndex + capacity;
                soProducerLimit(producerLimit);
            }
        } while (!casProducerIndex(pIndex, pIndex + 1));
        int offset = calcElementOffset(pIndex, mask);
        soElement(this.buffer, offset, e);
        return true;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        long pIndex;
        if (null == e) {
            throw new NullPointerException();
        }
        int mask = this.mask;
        long producerLimit = lvProducerLimit();
        do {
            pIndex = lvProducerIndex();
            if (pIndex >= producerLimit) {
                long cIndex = lvConsumerIndex();
                producerLimit = cIndex + mask + 1;
                if (pIndex >= producerLimit) {
                    return false;
                }
                soProducerLimit(producerLimit);
            }
        } while (!casProducerIndex(pIndex, pIndex + 1));
        int offset = calcElementOffset(pIndex, mask);
        soElement(this.buffer, offset, e);
        return true;
    }

    public final int failFastOffer(E e) {
        if (null == e) {
            throw new NullPointerException();
        }
        int mask = this.mask;
        long capacity = mask + 1;
        long pIndex = lvProducerIndex();
        if (pIndex >= lvProducerLimit()) {
            long cIndex = lvConsumerIndex();
            long producerLimit = cIndex + capacity;
            if (pIndex >= producerLimit) {
                return 1;
            }
            soProducerLimit(producerLimit);
        }
        if (!casProducerIndex(pIndex, pIndex + 1)) {
            return -1;
        }
        int offset = calcElementOffset(pIndex, mask);
        soElement(this.buffer, offset, e);
        return 0;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        long jLpConsumerIndex = lpConsumerIndex();
        int iCalcElementOffset = calcElementOffset(jLpConsumerIndex);
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        Object objLvElement = lvElement(atomicReferenceArray, iCalcElementOffset);
        if (null == objLvElement) {
            if (jLpConsumerIndex != lvProducerIndex()) {
                do {
                    objLvElement = lvElement(atomicReferenceArray, iCalcElementOffset);
                } while (objLvElement == null);
            } else {
                return null;
            }
        }
        spElement(atomicReferenceArray, iCalcElementOffset, null);
        soConsumerIndex(jLpConsumerIndex + 1);
        return (E) objLvElement;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        long jLpConsumerIndex = lpConsumerIndex();
        int iCalcElementOffset = calcElementOffset(jLpConsumerIndex);
        Object objLvElement = lvElement(atomicReferenceArray, iCalcElementOffset);
        if (null == objLvElement) {
            if (jLpConsumerIndex != lvProducerIndex()) {
                do {
                    objLvElement = lvElement(atomicReferenceArray, iCalcElementOffset);
                } while (objLvElement == null);
            } else {
                return null;
            }
        }
        return (E) objLvElement;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        long jLpConsumerIndex = lpConsumerIndex();
        int iCalcElementOffset = calcElementOffset(jLpConsumerIndex);
        E e = (E) lvElement(atomicReferenceArray, iCalcElementOffset);
        if (null == e) {
            return null;
        }
        spElement(atomicReferenceArray, iCalcElementOffset, null);
        soConsumerIndex(jLpConsumerIndex + 1);
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        return (E) lvElement(this.buffer, calcElementOffset(lpConsumerIndex(), this.mask));
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> c) {
        return drain(c, capacity());
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> s) {
        long result = 0;
        int capacity = capacity();
        do {
            int filled = fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH);
            if (filled == 0) {
                return (int) result;
            }
            result += filled;
        } while (result <= capacity);
        return (int) result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> consumer, int limit) {
        AtomicReferenceArray<E> buffer = this.buffer;
        int mask = this.mask;
        long cIndex = lpConsumerIndex();
        for (int i = 0; i < limit; i++) {
            long index = cIndex + i;
            int offset = calcElementOffset(index, mask);
            Object objLvElement = lvElement(buffer, offset);
            if (null == objLvElement) {
                return i;
            }
            spElement(buffer, offset, null);
            soConsumerIndex(index + 1);
            consumer.accept(objLvElement);
        }
        return limit;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> s, int limit) {
        long pIndex;
        int actualLimit;
        int mask = this.mask;
        long capacity = mask + 1;
        long producerLimit = lvProducerLimit();
        do {
            pIndex = lvProducerIndex();
            long available = producerLimit - pIndex;
            if (available <= 0) {
                long cIndex = lvConsumerIndex();
                producerLimit = cIndex + capacity;
                available = producerLimit - pIndex;
                if (available <= 0) {
                    return 0;
                }
                soProducerLimit(producerLimit);
            }
            actualLimit = Math.min((int) available, limit);
        } while (!casProducerIndex(pIndex, pIndex + actualLimit));
        AtomicReferenceArray<E> buffer = this.buffer;
        for (int i = 0; i < actualLimit; i++) {
            int offset = calcElementOffset(pIndex + i, mask);
            soElement(buffer, offset, s.get());
        }
        return actualLimit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy w, MessagePassingQueue.ExitCondition exit) {
        AtomicReferenceArray<E> buffer = this.buffer;
        int mask = this.mask;
        long cIndex = lpConsumerIndex();
        int counter = 0;
        while (exit.keepRunning()) {
            for (int i = 0; i < 4096; i++) {
                int offset = calcElementOffset(cIndex, mask);
                Object objLvElement = lvElement(buffer, offset);
                if (null == objLvElement) {
                    counter = w.idle(counter);
                } else {
                    cIndex++;
                    counter = 0;
                    spElement(buffer, offset, null);
                    soConsumerIndex(cIndex);
                    consumer.accept(objLvElement);
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> s, MessagePassingQueue.WaitStrategy w, MessagePassingQueue.ExitCondition exit) {
        int iIdle = 0;
        while (true) {
            int idleCounter = iIdle;
            if (exit.keepRunning()) {
                if (fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
                    iIdle = w.idle(idleCounter);
                } else {
                    iIdle = 0;
                }
            } else {
                return;
            }
        }
    }

    @Deprecated
    public int weakOffer(E e) {
        return failFastOffer(e);
    }
}
