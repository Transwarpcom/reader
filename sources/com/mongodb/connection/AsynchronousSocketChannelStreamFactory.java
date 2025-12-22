package com.mongodb.connection;

import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.internal.connection.AsynchronousSocketChannelStream;
import com.mongodb.internal.connection.PowerOfTwoBufferPool;
import java.nio.channels.AsynchronousChannelGroup;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/AsynchronousSocketChannelStreamFactory.class */
public class AsynchronousSocketChannelStreamFactory implements StreamFactory {
    private final BufferProvider bufferProvider;
    private final SocketSettings settings;
    private final AsynchronousChannelGroup group;

    public AsynchronousSocketChannelStreamFactory(SocketSettings settings, SslSettings sslSettings) {
        this(settings, sslSettings, null);
    }

    public AsynchronousSocketChannelStreamFactory(SocketSettings settings, SslSettings sslSettings, AsynchronousChannelGroup group) {
        this.bufferProvider = new PowerOfTwoBufferPool();
        if (sslSettings.isEnabled()) {
            throw new UnsupportedOperationException("No SSL support in java.nio.channels.AsynchronousSocketChannel. For SSL support use com.mongodb.connection.netty.NettyStreamFactoryFactory");
        }
        this.settings = (SocketSettings) Assertions.notNull("settings", settings);
        this.group = group;
    }

    @Override // com.mongodb.connection.StreamFactory
    public Stream create(ServerAddress serverAddress) {
        return new AsynchronousSocketChannelStream(serverAddress, this.settings, this.bufferProvider, this.group);
    }
}
