package io.netty.channel;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/ChannelInboundInvoker.class */
public interface ChannelInboundInvoker {
    ChannelInboundInvoker fireChannelRegistered();

    ChannelInboundInvoker fireChannelUnregistered();

    ChannelInboundInvoker fireChannelActive();

    ChannelInboundInvoker fireChannelInactive();

    ChannelInboundInvoker fireExceptionCaught(Throwable th);

    ChannelInboundInvoker fireUserEventTriggered(Object obj);

    ChannelInboundInvoker fireChannelRead(Object obj);

    ChannelInboundInvoker fireChannelReadComplete();

    ChannelInboundInvoker fireChannelWritabilityChanged();
}
