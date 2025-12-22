package io.netty.channel;

import io.netty.util.concurrent.AbstractEventExecutorGroup;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/AbstractEventLoopGroup.class */
public abstract class AbstractEventLoopGroup extends AbstractEventExecutorGroup implements EventLoopGroup {
    @Override // io.netty.util.concurrent.EventExecutorGroup, io.netty.channel.EventLoopGroup
    public abstract EventLoop next();
}
