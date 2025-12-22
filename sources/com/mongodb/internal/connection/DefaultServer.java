package com.mongodb.internal.connection;

import com.mongodb.MongoException;
import com.mongodb.MongoNodeIsRecoveringException;
import com.mongodb.MongoNotPrimaryException;
import com.mongodb.MongoSecurityException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerDescription;
import com.mongodb.connection.ServerId;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.CommandListener;
import com.mongodb.event.ServerClosedEvent;
import com.mongodb.event.ServerDescriptionChangedEvent;
import com.mongodb.event.ServerListener;
import com.mongodb.event.ServerOpeningEvent;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultServer.class */
class DefaultServer implements ClusterableServer {
    private static final Logger LOGGER = Loggers.getLogger("connection");
    private final ServerId serverId;
    private final ConnectionPool connectionPool;
    private final ClusterConnectionMode clusterConnectionMode;
    private final ConnectionFactory connectionFactory;
    private final ServerMonitor serverMonitor;
    private final ChangeListener<ServerDescription> serverStateListener;
    private final ServerListener serverListener;
    private final CommandListener commandListener;
    private final ClusterClock clusterClock;
    private volatile ServerDescription description;
    private volatile boolean isClosed;

    DefaultServer(ServerId serverId, ClusterConnectionMode clusterConnectionMode, ConnectionPool connectionPool, ConnectionFactory connectionFactory, ServerMonitorFactory serverMonitorFactory, ServerListener serverListener, CommandListener commandListener, ClusterClock clusterClock) {
        this.serverListener = (ServerListener) Assertions.notNull("serverListener", serverListener);
        this.commandListener = commandListener;
        this.clusterClock = (ClusterClock) Assertions.notNull("clusterClock", clusterClock);
        Assertions.notNull("serverAddress", serverId);
        Assertions.notNull("serverMonitorFactory", serverMonitorFactory);
        this.clusterConnectionMode = (ClusterConnectionMode) Assertions.notNull("clusterConnectionMode", clusterConnectionMode);
        this.connectionFactory = (ConnectionFactory) Assertions.notNull("connectionFactory", connectionFactory);
        this.connectionPool = (ConnectionPool) Assertions.notNull("connectionPool", connectionPool);
        this.serverStateListener = new DefaultServerStateListener();
        this.serverId = serverId;
        serverListener.serverOpening(new ServerOpeningEvent(this.serverId));
        this.description = ServerDescription.builder().state(ServerConnectionState.CONNECTING).address(serverId.getAddress()).build();
        this.serverMonitor = serverMonitorFactory.create(this.serverStateListener);
        this.serverMonitor.start();
    }

    @Override // com.mongodb.connection.Server
    public Connection getConnection() {
        Assertions.isTrue("open", !isClosed());
        try {
            return this.connectionFactory.create(this.connectionPool.get(), new DefaultServerProtocolExecutor(), this.clusterConnectionMode);
        } catch (MongoSecurityException e) {
            this.connectionPool.invalidate();
            throw e;
        } catch (MongoSocketException e2) {
            invalidate();
            throw e2;
        }
    }

    @Override // com.mongodb.connection.Server
    public void getConnectionAsync(final SingleResultCallback<AsyncConnection> callback) {
        Assertions.isTrue("open", !isClosed());
        this.connectionPool.getAsync(new SingleResultCallback<InternalConnection>() { // from class: com.mongodb.internal.connection.DefaultServer.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(InternalConnection result, Throwable t) {
                if (t instanceof MongoSecurityException) {
                    DefaultServer.this.connectionPool.invalidate();
                } else if (t instanceof MongoSocketException) {
                    DefaultServer.this.invalidate();
                }
                if (t == null) {
                    callback.onResult(DefaultServer.this.connectionFactory.createAsync(result, new DefaultServerProtocolExecutor(), DefaultServer.this.clusterConnectionMode), null);
                } else {
                    callback.onResult(null, t);
                }
            }
        });
    }

    @Override // com.mongodb.connection.Server
    public ServerDescription getDescription() {
        Assertions.isTrue("open", !isClosed());
        return this.description;
    }

    @Override // com.mongodb.internal.connection.ClusterableServer
    public void invalidate() {
        Assertions.isTrue("open", !isClosed());
        this.serverStateListener.stateChanged(new ChangeEvent<>(this.description, ServerDescription.builder().state(ServerConnectionState.CONNECTING).address(this.serverId.getAddress()).build()));
        this.connectionPool.invalidate();
        connect();
    }

    @Override // com.mongodb.internal.connection.ClusterableServer
    public void close() {
        if (!isClosed()) {
            this.connectionPool.close();
            this.serverMonitor.close();
            this.isClosed = true;
            this.serverListener.serverClosed(new ServerClosedEvent(this.serverId));
        }
    }

    @Override // com.mongodb.internal.connection.ClusterableServer
    public boolean isClosed() {
        return this.isClosed;
    }

    @Override // com.mongodb.internal.connection.ClusterableServer
    public void connect() {
        this.serverMonitor.connect();
    }

    ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleThrowable(Throwable t) {
        if (((t instanceof MongoSocketException) && !(t instanceof MongoSocketReadTimeoutException)) || (t instanceof MongoNotPrimaryException) || (t instanceof MongoNodeIsRecoveringException)) {
            invalidate();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultServer$DefaultServerProtocolExecutor.class */
    private class DefaultServerProtocolExecutor implements ProtocolExecutor {
        private DefaultServerProtocolExecutor() {
        }

        @Override // com.mongodb.internal.connection.ProtocolExecutor
        public <T> T execute(LegacyProtocol<T> protocol, InternalConnection connection) {
            try {
                protocol.setCommandListener(DefaultServer.this.commandListener);
                return protocol.execute(connection);
            } catch (MongoException e) {
                DefaultServer.this.handleThrowable(e);
                throw e;
            }
        }

        @Override // com.mongodb.internal.connection.ProtocolExecutor
        public <T> void executeAsync(LegacyProtocol<T> protocol, InternalConnection connection, final SingleResultCallback<T> callback) {
            protocol.setCommandListener(DefaultServer.this.commandListener);
            protocol.executeAsync(connection, ErrorHandlingResultCallback.errorHandlingCallback(new SingleResultCallback<T>() { // from class: com.mongodb.internal.connection.DefaultServer.DefaultServerProtocolExecutor.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(T result, Throwable t) {
                    if (t != null) {
                        DefaultServer.this.handleThrowable(t);
                    }
                    callback.onResult(result, t);
                }
            }, DefaultServer.LOGGER));
        }

        @Override // com.mongodb.internal.connection.ProtocolExecutor
        public <T> T execute(CommandProtocol<T> commandProtocol, InternalConnection internalConnection, SessionContext sessionContext) {
            try {
                commandProtocol.sessionContext(new ClusterClockAdvancingSessionContext(sessionContext, DefaultServer.this.clusterClock));
                return commandProtocol.execute(internalConnection);
            } catch (MongoWriteConcernWithResponseException e) {
                DefaultServer.this.invalidate();
                return (T) e.getResponse();
            } catch (MongoException e2) {
                DefaultServer.this.handleThrowable(e2);
                throw e2;
            }
        }

        @Override // com.mongodb.internal.connection.ProtocolExecutor
        public <T> void executeAsync(CommandProtocol<T> protocol, InternalConnection connection, SessionContext sessionContext, final SingleResultCallback<T> callback) {
            protocol.sessionContext(new ClusterClockAdvancingSessionContext(sessionContext, DefaultServer.this.clusterClock));
            protocol.executeAsync(connection, ErrorHandlingResultCallback.errorHandlingCallback(new SingleResultCallback<T>() { // from class: com.mongodb.internal.connection.DefaultServer.DefaultServerProtocolExecutor.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(T result, Throwable t) {
                    if (t != null) {
                        if (!(t instanceof MongoWriteConcernWithResponseException)) {
                            DefaultServer.this.handleThrowable(t);
                            callback.onResult(null, t);
                            return;
                        } else {
                            DefaultServer.this.invalidate();
                            callback.onResult(((MongoWriteConcernWithResponseException) t).getResponse(), null);
                            return;
                        }
                    }
                    callback.onResult(result, null);
                }
            }, DefaultServer.LOGGER));
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultServer$DefaultServerStateListener.class */
    private final class DefaultServerStateListener implements ChangeListener<ServerDescription> {
        private DefaultServerStateListener() {
        }

        @Override // com.mongodb.internal.connection.ChangeListener
        public void stateChanged(ChangeEvent<ServerDescription> event) {
            ServerDescription oldDescription = DefaultServer.this.description;
            DefaultServer.this.description = event.getNewValue();
            DefaultServer.this.serverListener.serverDescriptionChanged(new ServerDescriptionChangedEvent(DefaultServer.this.serverId, DefaultServer.this.description, oldDescription));
        }
    }
}
