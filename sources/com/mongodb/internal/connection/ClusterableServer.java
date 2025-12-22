package com.mongodb.internal.connection;

import com.mongodb.connection.Server;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ClusterableServer.class */
interface ClusterableServer extends Server {
    void invalidate();

    void close();

    boolean isClosed();

    void connect();
}
