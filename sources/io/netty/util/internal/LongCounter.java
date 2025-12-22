package io.netty.util.internal;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/internal/LongCounter.class */
public interface LongCounter {
    void add(long j);

    void increment();

    void decrement();

    long value();
}
