package io.netty.util.concurrent;

import java.lang.Thread;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/ThreadProperties.class */
public interface ThreadProperties {
    Thread.State state();

    int priority();

    boolean isInterrupted();

    boolean isDaemon();

    String name();

    long id();

    StackTraceElement[] stackTrace();

    boolean isAlive();
}
