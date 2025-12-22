package io.netty.bootstrap;

import io.netty.channel.Channel;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/bootstrap/ChannelFactory.class */
public interface ChannelFactory<T extends Channel> {
    T newChannel();
}
