package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
import io.vertx.core.http.Http2Settings;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/shaded/org/jctools/queues/MpscUnboundedArrayQueue.class */
public class MpscUnboundedArrayQueue<E> extends BaseMpscLinkedArrayQueue<E> {
    long p0;
    long p1;
    long p2;
    long p3;
    long p4;
    long p5;
    long p6;
    long p7;
    long p10;
    long p11;
    long p12;
    long p13;
    long p14;
    long p15;
    long p16;
    long p17;

    public MpscUnboundedArrayQueue(int chunkSize) {
        super(chunkSize);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue
    protected long availableInQueue(long pIndex, long cIndex) {
        return 2147483647L;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int capacity() {
        return -1;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int drain(MessagePassingQueue.Consumer<E> c) {
        return drain(c, 4096);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue, io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue
    public int fill(MessagePassingQueue.Supplier<E> s) {
        long result = 0;
        do {
            int filled = fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH);
            if (filled == 0) {
                return (int) result;
            }
            result += filled;
        } while (result <= Http2Settings.DEFAULT_HEADER_TABLE_SIZE);
        return (int) result;
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue
    protected int getNextBufferSize(E[] buffer) {
        return LinkedArrayQueueUtil.length(buffer);
    }

    @Override // io.netty.util.internal.shaded.org.jctools.queues.BaseMpscLinkedArrayQueue
    protected long getCurrentBufferCapacity(long mask) {
        return mask;
    }
}
