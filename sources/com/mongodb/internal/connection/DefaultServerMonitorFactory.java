package com.mongodb.internal.connection;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ServerDescription;
import com.mongodb.connection.ServerId;
import com.mongodb.connection.ServerSettings;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultServerMonitorFactory.class */
class DefaultServerMonitorFactory implements ServerMonitorFactory {
    private final ServerId serverId;
    private final ServerSettings settings;
    private final ClusterClock clusterClock;
    private final InternalConnectionFactory internalConnectionFactory;
    private final ConnectionPool connectionPool;

    DefaultServerMonitorFactory(ServerId serverId, ServerSettings settings, ClusterClock clusterClock, InternalConnectionFactory internalConnectionFactory, ConnectionPool connectionPool) {
        this.serverId = (ServerId) Assertions.notNull("serverId", serverId);
        this.settings = (ServerSettings) Assertions.notNull("settings", settings);
        this.clusterClock = (ClusterClock) Assertions.notNull("clusterClock", clusterClock);
        this.internalConnectionFactory = (InternalConnectionFactory) Assertions.notNull("internalConnectionFactory", internalConnectionFactory);
        this.connectionPool = (ConnectionPool) Assertions.notNull("connectionPool", connectionPool);
    }

    @Override // com.mongodb.internal.connection.ServerMonitorFactory
    public ServerMonitor create(ChangeListener<ServerDescription> serverStateListener) {
        return new DefaultServerMonitor(this.serverId, this.settings, this.clusterClock, serverStateListener, this.internalConnectionFactory, this.connectionPool);
    }
}
