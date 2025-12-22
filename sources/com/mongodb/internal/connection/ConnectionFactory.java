package com.mongodb.internal.connection;

import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.Connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConnectionFactory.class */
interface ConnectionFactory {
    Connection create(InternalConnection internalConnection, ProtocolExecutor protocolExecutor, ClusterConnectionMode clusterConnectionMode);

    AsyncConnection createAsync(InternalConnection internalConnection, ProtocolExecutor protocolExecutor, ClusterConnectionMode clusterConnectionMode);
}
