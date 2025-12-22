package com.mongodb.connection;

import com.mongodb.MongoClientException;
import com.mongodb.ServerAddress;
import com.mongodb.UnixServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.internal.connection.PowerOfTwoBufferPool;
import com.mongodb.internal.connection.SocketChannelStream;
import com.mongodb.internal.connection.SocketStream;
import com.mongodb.internal.connection.UnixSocketChannelStream;
import java.security.NoSuchAlgorithmException;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/SocketStreamFactory.class */
public class SocketStreamFactory implements StreamFactory {
    private final SocketSettings settings;
    private final SslSettings sslSettings;
    private final SocketFactory socketFactory;
    private final BufferProvider bufferProvider;

    public SocketStreamFactory(SocketSettings settings, SslSettings sslSettings) {
        this(settings, sslSettings, null);
    }

    public SocketStreamFactory(SocketSettings settings, SslSettings sslSettings, SocketFactory socketFactory) {
        this.bufferProvider = new PowerOfTwoBufferPool();
        this.settings = (SocketSettings) Assertions.notNull("settings", settings);
        this.sslSettings = (SslSettings) Assertions.notNull("sslSettings", sslSettings);
        this.socketFactory = socketFactory;
    }

    @Override // com.mongodb.connection.StreamFactory
    public Stream create(ServerAddress serverAddress) {
        Stream stream;
        if (serverAddress instanceof UnixServerAddress) {
            if (this.sslSettings.isEnabled()) {
                throw new MongoClientException("Socket based connections do not support ssl");
            }
            stream = new UnixSocketChannelStream((UnixServerAddress) serverAddress, this.settings, this.sslSettings, this.bufferProvider);
        } else if (this.socketFactory != null) {
            stream = new SocketStream(serverAddress, this.settings, this.sslSettings, this.socketFactory, this.bufferProvider);
        } else if (this.sslSettings.isEnabled()) {
            stream = new SocketStream(serverAddress, this.settings, this.sslSettings, getSslContext().getSocketFactory(), this.bufferProvider);
        } else {
            stream = new SocketChannelStream(serverAddress, this.settings, this.sslSettings, this.bufferProvider);
        }
        return stream;
    }

    private SSLContext getSslContext() {
        try {
            return this.sslSettings.getContext() == null ? SSLContext.getDefault() : this.sslSettings.getContext();
        } catch (NoSuchAlgorithmException e) {
            throw new MongoClientException("Unable to create default SSLContext", e);
        }
    }
}
