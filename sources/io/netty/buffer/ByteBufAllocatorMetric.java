package io.netty.buffer;

/* loaded from: reader.jar:BOOT-INF/lib/netty-buffer-4.1.42.Final.jar:io/netty/buffer/ByteBufAllocatorMetric.class */
public interface ByteBufAllocatorMetric {
    long usedHeapMemory();

    long usedDirectMemory();
}
