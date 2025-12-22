package io.netty.channel.pool;

import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import java.io.Closeable;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/pool/ChannelPool.class */
public interface ChannelPool extends Closeable {
    Future<Channel> acquire();

    Future<Channel> acquire(Promise<Channel> promise);

    Future<Void> release(Channel channel);

    Future<Void> release(Channel channel, Promise<Void> promise);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
