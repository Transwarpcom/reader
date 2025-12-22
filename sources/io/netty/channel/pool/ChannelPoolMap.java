package io.netty.channel.pool;

import io.netty.channel.pool.ChannelPool;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/pool/ChannelPoolMap.class */
public interface ChannelPoolMap<K, P extends ChannelPool> {
    P get(K k);

    boolean contains(K k);
}
