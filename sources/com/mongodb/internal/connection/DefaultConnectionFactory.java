package com.mongodb.internal.connection;

import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.Connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultConnectionFactory.class */
class DefaultConnectionFactory implements ConnectionFactory {
    DefaultConnectionFactory() {
    }

    @Override // com.mongodb.internal.connection.ConnectionFactory
    public Connection create(InternalConnection internalConnection, ProtocolExecutor executor, ClusterConnectionMode clusterConnectionMode) {
        return new DefaultServerConnection(internalConnection, executor, clusterConnectionMode);
    }

    @Override // com.mongodb.internal.connection.ConnectionFactory
    public AsyncConnection createAsync(InternalConnection internalConnection, ProtocolExecutor executor, ClusterConnectionMode clusterConnectionMode) {
        return new DefaultServerConnection(internalConnection, executor, clusterConnectionMode);
    }
}
