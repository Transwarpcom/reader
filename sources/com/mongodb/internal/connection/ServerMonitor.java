package com.mongodb.internal.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ServerMonitor.class */
interface ServerMonitor {
    void start();

    void connect();

    void close();
}
