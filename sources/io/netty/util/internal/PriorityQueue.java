package io.netty.util.internal;

import java.util.Queue;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/PriorityQueue.class */
public interface PriorityQueue<T> extends Queue<T> {
    boolean removeTyped(T t);

    boolean containsTyped(T t);

    void priorityChanged(T t);

    void clearIgnoringIndexes();
}
