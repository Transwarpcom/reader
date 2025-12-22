package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import jnr.unixsocket.UnixSocketAddress;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/UnixServerAddress.class */
public final class UnixServerAddress extends ServerAddress {
    private static final long serialVersionUID = 154466643544866543L;

    public UnixServerAddress(String path) {
        super((String) Assertions.notNull("The path cannot be null", path));
        Assertions.isTrueArgument("The path must end in .sock", path.endsWith(".sock"));
    }

    @Override // com.mongodb.ServerAddress
    public InetSocketAddress getSocketAddress() {
        throw new UnsupportedOperationException("Cannot return a InetSocketAddress from a UnixServerAddress");
    }

    public SocketAddress getUnixSocketAddress() {
        return new UnixSocketAddress(getHost());
    }

    @Override // com.mongodb.ServerAddress
    public String toString() {
        return getHost();
    }
}
