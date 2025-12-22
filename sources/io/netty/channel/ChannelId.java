package io.netty.channel;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/ChannelId.class */
public interface ChannelId extends Serializable, Comparable<ChannelId> {
    String asShortText();

    String asLongText();
}
