package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;
import io.netty.util.internal.shaded.org.jctools.util.RangeUtil;
import io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess;
import java.util.Iterator;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/BaseMpscLinkedArrayQueue.class */
public abstract class BaseMpscLinkedArrayQueue<E> extends BaseMpscLinkedArrayQueueColdProducerFields<E> implements MessagePassingQueue<E>, QueueProgressIndicators {
    private static final Object JUMP = new Object();
    private static final int CONTINUE_TO_P_INDEX_CAS = 0;
    private static final int RETRY = 1;
    private static final int QUEUE_FULL = 2;
    private static final int QUEUE_RESIZE = 3;

    protected abstract long availableInQueue(long j, long j2);

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public abstract int capacity();

    protected abstract int getNextBufferSize(E[] eArr);

    protected abstract long getCurrentBufferCapacity(long j);

    public BaseMpscLinkedArrayQueue(int i) {
        RangeUtil.checkGreaterThanOrEqual(i, 2, "initialCapacity");
        int iRoundToPowerOfTwo = Pow2.roundToPowerOfTwo(i);
        long j = (iRoundToPowerOfTwo - 1) << 1;
        E[] eArr = (E[]) CircularArrayOffsetCalculator.allocate(iRoundToPowerOfTwo + 1);
        this.producerBuffer = eArr;
        this.producerMask = j;
        this.consumerBuffer = eArr;
        this.consumerMask = j;
        soProducerLimit(j);
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
                E[] buffer = this.producerBuffer;
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
                    long offset = LinkedArrayQueueUtil.modifiedCalcElementOffset(pIndex, mask);
                    UnsafeRefArrayAccess.soElement(buffer, offset, e);
                    return true;
                }
            }
        }
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E poll() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        long jModifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        Object objLvElement = UnsafeRefArrayAccess.lvElement(eArr, jModifiedCalcElementOffset);
        if (objLvElement == null) {
            if (j != lvProducerIndex()) {
                do {
                    objLvElement = UnsafeRefArrayAccess.lvElement(eArr, jModifiedCalcElementOffset);
                } while (objLvElement == null);
            } else {
                return null;
            }
        }
        if (objLvElement == JUMP) {
            return newBufferPoll(getNextBuffer(eArr, j2), j);
        }
        UnsafeRefArrayAccess.soElement(eArr, jModifiedCalcElementOffset, null);
        soConsumerIndex(j + 2);
        return (E) objLvElement;
    }

    @Override // java.util.Queue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E peek() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        long jModifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        Object objLvElement = UnsafeRefArrayAccess.lvElement(eArr, jModifiedCalcElementOffset);
        if (objLvElement == null && j != lvProducerIndex()) {
            do {
                objLvElement = UnsafeRefArrayAccess.lvElement(eArr, jModifiedCalcElementOffset);
            } while (objLvElement == null);
        }
        if (objLvElement == JUMP) {
            return newBufferPeek(getNextBuffer(eArr, j2), j);
        }
        return (E) objLvElement;
    }

    private int offerSlowPath(long mask, long pIndex, long producerLimit) {
        long cIndex = lvConsumerIndex();
        long bufferCapacity = getCurrentBufferCapacity(mask);
        if (cIndex + bufferCapacity > pIndex) {
            if (!casProducerLimit(producerLimit, cIndex + bufferCapacity)) {
                return 1;
            }
            return 0;
        }
        if (availableInQueue(pIndex, cIndex) <= 0) {
            return 2;
        }
        if (casProducerIndex(pIndex, pIndex + 1)) {
            return 3;
        }
        return 1;
    }

    private E[] getNextBuffer(E[] eArr, long j) {
        long jNextArrayOffset = nextArrayOffset(j);
        E[] eArr2 = (E[]) ((Object[]) UnsafeRefArrayAccess.lvElement(eArr, jNextArrayOffset));
        UnsafeRefArrayAccess.soElement(eArr, jNextArrayOffset, null);
        return eArr2;
    }

    private long nextArrayOffset(long mask) {
        return LinkedArrayQueueUtil.modifiedCalcElementOffset(mask + 2, Long.MAX_VALUE);
    }

    private E newBufferPoll(E[] eArr, long j) {
        long jNewBufferAndOffset = newBufferAndOffset(eArr, j);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, jNewBufferAndOffset);
        if (e == null) {
            throw new IllegalStateException("new buffer must have at least one element");
        }
        UnsafeRefArrayAccess.soElement(eArr, jNewBufferAndOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    private E newBufferPeek(E[] eArr, long j) {
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, newBufferAndOffset(eArr, j));
        if (null == e) {
            throw new IllegalStateException("new buffer must have at least one element");
        }
        return e;
    }

    private long newBufferAndOffset(E[] nextBuffer, long index) {
        this.consumerBuffer = nextBuffer;
        this.consumerMask = (LinkedArrayQueueUtil.length(nextBuffer) - 2) << 1;
        return LinkedArrayQueueUtil.modifiedCalcElementOffset(index, this.consumerMask);
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
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        long jModifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2);
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, jModifiedCalcElementOffset);
        if (e == null) {
            return null;
        }
        if (e == JUMP) {
            return newBufferPoll(getNextBuffer(eArr, j2), j);
        }
        UnsafeRefArrayAccess.soElement(eArr, jModifiedCalcElementOffset, null);
        soConsumerIndex(j + 2);
        return e;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public E relaxedPeek() {
        E[] eArr = this.consumerBuffer;
        long j = this.consumerIndex;
        long j2 = this.consumerMask;
        E e = (E) UnsafeRefArrayAccess.lvElement(eArr, LinkedArrayQueueUtil.modifiedCalcElementOffset(j, j2));
        if (e == JUMP) {
            return newBufferPeek(getNextBuffer(eArr, j2), j);
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
                E[] buffer = this.producerBuffer;
                long batchIndex = Math.min(producerLimit, pIndex + (2 * batchSize));
                if (pIndex >= producerLimit || producerLimit < batchIndex) {
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
                        long offset = LinkedArrayQueueUtil.modifiedCalcElementOffset(pIndex + (2 * i), mask);
                        UnsafeRefArrayAccess.soElement(buffer, offset, s.get());
                    }
                    return claimedSlots;
                }
            }
        }
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public void fill(MessagePassingQueue.Supplier<E> s, MessagePassingQueue.WaitStrategy w, MessagePassingQueue.ExitCondition exit) {
        while (exit.keepRunning()) {
            if (fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
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

    private void resize(long j, E[] eArr, long j2, E e) {
        int nextBufferSize = getNextBufferSize(eArr);
        E[] eArr2 = (E[]) CircularArrayOffsetCalculator.allocate(nextBufferSize);
        this.producerBuffer = eArr2;
        int i = (nextBufferSize - 2) << 1;
        this.producerMask = i;
        long jModifiedCalcElementOffset = LinkedArrayQueueUtil.modifiedCalcElementOffset(j2, j);
        UnsafeRefArrayAccess.soElement(eArr2, LinkedArrayQueueUtil.modifiedCalcElementOffset(j2, i), e);
        UnsafeRefArrayAccess.soElement(eArr, nextArrayOffset(j), eArr2);
        long jAvailableInQueue = availableInQueue(j2, lvConsumerIndex());
        RangeUtil.checkPositive(jAvailableInQueue, "availableInQueue");
        soProducerLimit(j2 + Math.min(i, jAvailableInQueue));
        soProducerIndex(j2 + 2);
        UnsafeRefArrayAccess.soElement(eArr, jModifiedCalcElementOffset, JUMP);
    }
}
