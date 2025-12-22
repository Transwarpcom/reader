package io.netty.channel.group;

import io.netty.channel.Channel;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/group/ChannelMatcher.class */
public interface ChannelMatcher {
    boolean matches(Channel channel);
}
