package io.netty.buffer;

/* loaded from: reader.jar:BOOT-INF/lib/netty-buffer-4.1.42.Final.jar:io/netty/buffer/PoolSubpageMetric.class */
public interface PoolSubpageMetric {
    int maxNumElements();

    int numAvailable();

    int elementSize();

    int pageSize();
}
