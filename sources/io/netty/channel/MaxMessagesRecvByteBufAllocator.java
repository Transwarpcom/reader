package io.netty.channel;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/MaxMessagesRecvByteBufAllocator.class */
public interface MaxMessagesRecvByteBufAllocator extends RecvByteBufAllocator {
    int maxMessagesPerRead();

    MaxMessagesRecvByteBufAllocator maxMessagesPerRead(int i);
}
