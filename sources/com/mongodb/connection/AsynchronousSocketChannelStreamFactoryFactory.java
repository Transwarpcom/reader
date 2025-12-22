package com.mongodb.connection;

import java.nio.channels.AsynchronousChannelGroup;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/AsynchronousSocketChannelStreamFactoryFactory.class */
public class AsynchronousSocketChannelStreamFactoryFactory implements StreamFactoryFactory {
    private final AsynchronousChannelGroup group;

    @Deprecated
    public AsynchronousSocketChannelStreamFactoryFactory() {
        this(builder());
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/AsynchronousSocketChannelStreamFactoryFactory$Builder.class */
    public static final class Builder {
        private AsynchronousChannelGroup group;

        public Builder group(AsynchronousChannelGroup group) {
            this.group = group;
            return this;
        }

        public AsynchronousSocketChannelStreamFactoryFactory build() {
            return new AsynchronousSocketChannelStreamFactoryFactory(this);
        }
    }

    @Override // com.mongodb.connection.StreamFactoryFactory
    public StreamFactory create(SocketSettings socketSettings, SslSettings sslSettings) {
        return new AsynchronousSocketChannelStreamFactory(socketSettings, sslSettings, this.group);
    }

    private AsynchronousSocketChannelStreamFactoryFactory(Builder builder) {
        this.group = builder.group;
    }
}
