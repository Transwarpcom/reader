package com.mongodb.internal.connection;

import com.mongodb.MongoSocketOpenException;
import com.mongodb.UnixServerAddress;
import com.mongodb.connection.BufferProvider;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import java.io.IOException;
import jnr.unixsocket.UnixSocketChannel;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/UnixSocketChannelStream.class */
public class UnixSocketChannelStream extends SocketChannelStream {
    private final UnixServerAddress address;

    public UnixSocketChannelStream(UnixServerAddress address, SocketSettings settings, SslSettings sslSettings, BufferProvider bufferProvider) {
        super(address, settings, sslSettings, bufferProvider);
        this.address = address;
    }

    @Override // com.mongodb.internal.connection.SocketChannelStream, com.mongodb.connection.Stream
    public void open() {
        try {
            setSocketChannel(UnixSocketChannel.open(this.address.getUnixSocketAddress()));
        } catch (IOException e) {
            close();
            throw new MongoSocketOpenException("Exception opening socket", getAddress(), e);
        }
    }
}
