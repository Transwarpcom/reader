package io.netty.buffer;

/* loaded from: reader.jar:BOOT-INF/lib/netty-buffer-4.1.42.Final.jar:io/netty/buffer/PoolChunkListMetric.class */
public interface PoolChunkListMetric extends Iterable<PoolChunkMetric> {
    int minUsage();

    int maxUsage();
}
