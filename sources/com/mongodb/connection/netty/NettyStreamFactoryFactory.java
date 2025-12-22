package com.mongodb.connection.netty;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.StreamFactory;
import com.mongodb.connection.StreamFactoryFactory;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStreamFactoryFactory.class */
public class NettyStreamFactoryFactory implements StreamFactoryFactory {
    private final EventLoopGroup eventLoopGroup;
    private final Class<? extends SocketChannel> socketChannelClass;
    private final ByteBufAllocator allocator;

    @Deprecated
    public NettyStreamFactoryFactory() {
        this(builder());
    }

    @Deprecated
    public NettyStreamFactoryFactory(EventLoopGroup eventLoopGroup, ByteBufAllocator allocator) {
        this(builder().eventLoopGroup(eventLoopGroup).allocator(allocator));
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStreamFactoryFactory$Builder.class */
    public static final class Builder {
        private ByteBufAllocator allocator;
        private Class<? extends SocketChannel> socketChannelClass;
        private EventLoopGroup eventLoopGroup;

        private Builder() {
            allocator(ByteBufAllocator.DEFAULT);
            socketChannelClass(NioSocketChannel.class);
        }

        public Builder allocator(ByteBufAllocator allocator) {
            this.allocator = (ByteBufAllocator) Assertions.notNull("allocator", allocator);
            return this;
        }

        public Builder socketChannelClass(Class<? extends SocketChannel> socketChannelClass) {
            this.socketChannelClass = (Class) Assertions.notNull("socketChannelClass", socketChannelClass);
            return this;
        }

        public Builder eventLoopGroup(EventLoopGroup eventLoopGroup) {
            this.eventLoopGroup = (EventLoopGroup) Assertions.notNull("eventLoopGroup", eventLoopGroup);
            return this;
        }

        public NettyStreamFactoryFactory build() {
            return new NettyStreamFactoryFactory(this);
        }
    }

    @Override // com.mongodb.connection.StreamFactoryFactory
    public StreamFactory create(SocketSettings socketSettings, SslSettings sslSettings) {
        return new NettyStreamFactory(socketSettings, sslSettings, this.eventLoopGroup, this.socketChannelClass, this.allocator);
    }

    public String toString() {
        return "NettyStreamFactoryFactory{eventLoopGroup=" + this.eventLoopGroup + ", socketChannelClass=" + this.socketChannelClass + ", allocator=" + this.allocator + '}';
    }

    private NettyStreamFactoryFactory(Builder builder) {
        this.allocator = builder.allocator;
        this.socketChannelClass = builder.socketChannelClass;
        if (builder.eventLoopGroup != null) {
            this.eventLoopGroup = builder.eventLoopGroup;
        } else {
            this.eventLoopGroup = new NioEventLoopGroup();
        }
    }
}
