package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/atomic/BaseMpscLinkedAtomicArrayQueue.class */
public abstract class BaseMpscLinkedAtomicArrayQueue<E> extends BaseMpscLinkedAtomicArrayQueueColdProducerFields<E> implements MessagePassingQueue<E>, QueueProgressIndicators {
    private static final Object JUMP = new Object();

    protected abstract long availableInQueue(long j, long j2);

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public abstract int capacity();

    protected abstract int getNextBufferSize(AtomicReferenceArray<E> atomicReferenceArray);

    protected abstract long getCurrentBufferCapacity(long j);

    public BaseMpscLinkedAtomicArrayQueue(int initialCapacity) {
        RangeUtil.checkGreaterThanOrEqual(initialCapacity, 2, "initialCapacity");
        int p2capacity = Pow2.roundToPowerOfTwo(initialCapacity);
        long mask = (p2capacity - 1) << 1;
        AtomicReferenceArray<E> buffer = LinkedAtomicArrayQueueUtil.allocate(p2capacity + 1);
        this.producerBuffer = buffer;
        this.producerMask = mask;
        this.consumerBuffer = buffer;
        this.consumerMask = mask;
        soProducerLimit(mask);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public final int size() {
        long before;
        long currentProducerIndex;
        long after = lvConsumerIndex();
        do {
            before = after;
            currentProducerIndex = lvProducerIndex();
            after = lvConsumerIndex();
        } while (before != after);
        long size = (currentProducerIndex - after) >> 1;
        if (size > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public final boolean isEmpty() {
        return lvConsumerIndex() == lvProducerIndex();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return getClass().getName();
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean offer(E e) {
        if (null == e) {
            throw new NullPointerException();
        }
        while (true) {
            long producerLimit = lvProducerLimit();
            long pIndex = lvProducerIndex();
            if ((pIndex & 1) != 1) {
                long mask = this.producerMask;
                AtomicReferenceArray<E> buffer = this.producerBuffer;
                if (producerLimit <= pIndex) {
                    int result = offerSlowPath(mask, pIndex, producerLimit);
                    switch (result) {
                        case 2:
                            return false;
                        case 3:
                            resize(mask, buffer, pIndex, e);
                            return true;
                    }
                }
                if (casProducerIndex(pIndex, pIndex + 2)) {
                    int offset = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(pIndex, mask);
                    LinkedAtomicArrayQueueUtil.soElement(buffer, offset, e);
                    return true;
                }
            }
        }
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        int iModifiedCalcElementOffset = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        Object objLvElement = LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, iModifiedCalcElementOffset);
        if (objLvElement == null) {
            if (j != lvProducerIndex()) {
                do {
                    objLvElement = LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, iModifiedCalcElementOffset);
                } while (objLvElement == null);
            } else {
                return null;
            }
        }
        if (objLvElement == JUMP) {
            return newBufferPoll(getNextBuffer(atomicReferenceArray, j2), j);
        }
        LinkedAtomicArrayQueueUtil.soElement(atomicReferenceArray, iModifiedCalcElementOffset, null);
        soConsumerIndex(j + 2);
        return (E) objLvElement;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        Object objLvElement;
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        int iModifiedCalcElementOffset = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        Object objLvElement2 = LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, iModifiedCalcElementOffset);
        if (objLvElement2 == null && j != lvProducerIndex()) {
            do {
                objLvElement = LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, iModifiedCalcElementOffset);
                objLvElement2 = objLvElement;
            } while (objLvElement == null);
        }
        if (objLvElement2 == JUMP) {
            return newBufferPeek(getNextBuffer(atomicReferenceArray, j2), j);
        }
        return (E) objLvElement2;
    }

    private int offerSlowPath(long mask, long pIndex, long producerLimit) {
        long cIndex = lvConsumerIndex();
        long bufferCapacity = getCurrentBufferCapacity(mask);
        int result = 0;
        if (cIndex + bufferCapacity > pIndex) {
            if (!casProducerLimit(producerLimit, cIndex + bufferCapacity)) {
                result = 1;
            }
        } else if (availableInQueue(pIndex, cIndex) <= 0) {
            result = 2;
        } else if (casProducerIndex(pIndex, pIndex + 1)) {
            result = 3;
        } else {
            result = 1;
        }
        return result;
    }

    private AtomicReferenceArray<E> getNextBuffer(AtomicReferenceArray<E> buffer, long mask) {
        int offset = nextArrayOffset(mask);
        AtomicReferenceArray<E> nextBuffer = (AtomicReferenceArray) LinkedAtomicArrayQueueUtil.lvElement(buffer, offset);
        LinkedAtomicArrayQueueUtil.soElement(buffer, offset, null);
        return nextBuffer;
    }

    private int nextArrayOffset(long mask) {
        return LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(mask + 2, Long.MAX_VALUE);
    }

    private E newBufferPoll(AtomicReferenceArray<E> atomicReferenceArray, long j) {
        int iNewBufferAndOffset = newBufferAndOffset(atomicReferenceArray, j);
        E e = (E) LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, iNewBufferAndOffset);
        if (e == null) {
            throw new IllegalStateException("new buffer must have at least one element");
        }
        LinkedAtomicArrayQueueUtil.soElement(atomicReferenceArray, iNewBufferAndOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    private E newBufferPeek(AtomicReferenceArray<E> atomicReferenceArray, long j) {
        E e = (E) LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, newBufferAndOffset(atomicReferenceArray, j));
        if (null == e) {
            throw new IllegalStateException("new buffer must have at least one element");
        }
        return e;
    }

    private int newBufferAndOffset(AtomicReferenceArray<E> nextBuffer, long index) {
        this.consumerBuffer = nextBuffer;
        this.consumerMask = (LinkedAtomicArrayQueueUtil.length(nextBuffer) - 2) << 1;
        int offsetInNew = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(index, this.consumerMask);
        return offsetInNew;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public long currentProducerIndex() {
        return lvProducerIndex() / 2;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.QueueProgressIndicators
    public long currentConsumerIndex() {
        return lvConsumerIndex() / 2;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public boolean relaxedOffer(E e) {
        return offer(e);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPoll() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        int iModifiedCalcElementOffset = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        E e = (E) LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, iModifiedCalcElementOffset);
        if (e == null) {
            return null;
        }
        if (e == JUMP) {
            return newBufferPoll(getNextBuffer(atomicReferenceArray, j2), j);
        }
        LinkedAtomicArrayQueueUtil.soElement(atomicReferenceArray, iModifiedCalcElementOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        AtomicReferenceArray<E> atomicReferenceArray = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        E e = (E) LinkedAtomicArrayQueueUtil.lvElement(atomicReferenceArray, LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(j, j2));
        if (e == JUMP) {
            return newBufferPeek(getNextBuffer(atomicReferenceArray, j2), j);
        }
        return e;
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

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> s, int batchSize) {
        while (true) {
            long producerLimit = lvProducerLimit();
            long pIndex = lvProducerIndex();
            if ((pIndex & 1) != 1) {
                long mask = this.producerMask;
                AtomicReferenceArray<E> buffer = this.producerBuffer;
                long batchIndex = Math.min(producerLimit, pIndex + (2 * batchSize));
                if (pIndex == producerLimit || producerLimit < batchIndex) {
                    int result = offerSlowPath(mask, pIndex, producerLimit);
                    switch (result) {
                        case 2:
                            return 0;
                        case 3:
                            resize(mask, buffer, pIndex, s.get());
                            return 1;
                    }
                }
                if (casProducerIndex(pIndex, batchIndex)) {
                    int claimedSlots = (int) ((batchIndex - pIndex) / 2);
                    for (int i = 0; i < claimedSlots; i++) {
                        int offset = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(pIndex + (2 * i), mask);
                        LinkedAtomicArrayQueueUtil.soElement(buffer, offset, s.get());
                    }
                    return claimedSlots;
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> s, MessagePassingQueue.WaitStrategy w, MessagePassingQueue.ExitCondition exit) {
        while (exit.keepRunning()) {
            while (fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH) != 0 && exit.keepRunning()) {
            }
            int iIdle = 0;
            while (true) {
                int idleCounter = iIdle;
                if (!exit.keepRunning() || fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH) != 0) {
                    break;
                } else {
                    iIdle = w.idle(idleCounter);
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> c) {
        return drain(c, capacity());
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> c, int limit) {
        E m;
        int i = 0;
        while (i < limit && (m = relaxedPoll()) != null) {
            c.accept(m);
            i++;
        }
        return i;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void drain(MessagePassingQueue.Consumer<E> c, MessagePassingQueue.WaitStrategy w, MessagePassingQueue.ExitCondition exit) {
        int idleCounter = 0;
        while (exit.keepRunning()) {
            E e = relaxedPoll();
            if (e == null) {
                idleCounter = w.idle(idleCounter);
            } else {
                idleCounter = 0;
                c.accept(e);
            }
        }
    }

    private void resize(long oldMask, AtomicReferenceArray<E> oldBuffer, long pIndex, E e) {
        int newBufferLength = getNextBufferSize(oldBuffer);
        AtomicReferenceArray<E> newBuffer = LinkedAtomicArrayQueueUtil.allocate(newBufferLength);
        this.producerBuffer = newBuffer;
        int newMask = (newBufferLength - 2) << 1;
        this.producerMask = newMask;
        int offsetInOld = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(pIndex, oldMask);
        int offsetInNew = LinkedAtomicArrayQueueUtil.modifiedCalcElementOffset(pIndex, newMask);
        LinkedAtomicArrayQueueUtil.soElement(newBuffer, offsetInNew, e);
        LinkedAtomicArrayQueueUtil.soElement(oldBuffer, nextArrayOffset(oldMask), newBuffer);
        long cIndex = lvConsumerIndex();
        long availableInQueue = availableInQueue(pIndex, cIndex);
        RangeUtil.checkPositive(availableInQueue, "availableInQueue");
        soProducerLimit(pIndex + Math.min(newMask, availableInQueue));
        soProducerIndex(pIndex + 2);
        LinkedAtomicArrayQueueUtil.soElement(oldBuffer, offsetInOld, JUMP);
    }
}
