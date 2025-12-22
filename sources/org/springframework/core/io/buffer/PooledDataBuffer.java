package org.springframework.core.io.buffer;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/io/buffer/PooledDataBuffer.class */
public interface PooledDataBuffer extends DataBuffer {
    boolean isAllocated();

    PooledDataBuffer retain();

    boolean release();
}
