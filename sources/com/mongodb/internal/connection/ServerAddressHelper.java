package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import com.mongodb.UnixServerAddress;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ServerAddressHelper.class */
public final class ServerAddressHelper {
    public static ServerAddress createServerAddress(String host) {
        return createServerAddress(host, ServerAddress.defaultPort());
    }

    public static ServerAddress createServerAddress(String host, int port) {
        if (host != null && host.endsWith(".sock")) {
            return new UnixServerAddress(host);
        }
        return new ServerAddress(host, port);
    }

    private ServerAddressHelper() {
    }
}
