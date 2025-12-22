package io.netty.channel;

import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/MaxBytesRecvByteBufAllocator.class */
public interface MaxBytesRecvByteBufAllocator extends RecvByteBufAllocator {
    int maxBytesPerRead();

    MaxBytesRecvByteBufAllocator maxBytesPerRead(int i);

    int maxBytesPerIndividualRead();

    MaxBytesRecvByteBufAllocator maxBytesPerIndividualRead(int i);

    Map.Entry<Integer, Integer> maxBytesPerReadPair();

    MaxBytesRecvByteBufAllocator maxBytesPerReadPair(int i, int i2);
}
