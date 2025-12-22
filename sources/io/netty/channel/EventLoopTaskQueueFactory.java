package io.netty.channel;

import java.util.Queue;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/EventLoopTaskQueueFactory.class */
public interface EventLoopTaskQueueFactory {
    Queue<Runnable> newTaskQueue(int i);
}
