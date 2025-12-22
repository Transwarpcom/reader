package com.mongodb.connection.netty;

import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.Stream;
import com.mongodb.connection.StreamFactory;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStreamFactory.class */
public class NettyStreamFactory implements StreamFactory {
    private final SocketSettings settings;
    private final SslSettings sslSettings;
    private final EventLoopGroup eventLoopGroup;
    private final Class<? extends SocketChannel> socketChannelClass;
    private final ByteBufAllocator allocator;

    public NettyStreamFactory(SocketSettings settings, SslSettings sslSettings, EventLoopGroup eventLoopGroup, Class<? extends SocketChannel> socketChannelClass, ByteBufAllocator allocator) {
        this.settings = (SocketSettings) Assertions.notNull("settings", settings);
        this.sslSettings = (SslSettings) Assertions.notNull("sslSettings", sslSettings);
        this.eventLoopGroup = (EventLoopGroup) Assertions.notNull("eventLoopGroup", eventLoopGroup);
        this.socketChannelClass = (Class) Assertions.notNull("socketChannelClass", socketChannelClass);
        this.allocator = (ByteBufAllocator) Assertions.notNull("allocator", allocator);
    }

    public NettyStreamFactory(SocketSettings settings, SslSettings sslSettings, EventLoopGroup eventLoopGroup, ByteBufAllocator allocator) {
        this(settings, sslSettings, eventLoopGroup, NioSocketChannel.class, allocator);
    }

    public NettyStreamFactory(SocketSettings settings, SslSettings sslSettings, EventLoopGroup eventLoopGroup) {
        this(settings, sslSettings, eventLoopGroup, PooledByteBufAllocator.DEFAULT);
    }

    public NettyStreamFactory(SocketSettings settings, SslSettings sslSettings) {
        this(settings, sslSettings, new NioEventLoopGroup());
    }

    @Override // com.mongodb.connection.StreamFactory
    public Stream create(ServerAddress serverAddress) {
        return new NettyStream(serverAddress, this.settings, this.sslSettings, this.eventLoopGroup, this.socketChannelClass, this.allocator);
    }
}
