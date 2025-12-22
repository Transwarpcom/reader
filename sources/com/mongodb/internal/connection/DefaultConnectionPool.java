package com.mongodb.internal.connection;

import com.mongodb.MongoException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.MongoWaitQueueFullException;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ConnectionId;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ServerId;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.ConnectionAddedEvent;
import com.mongodb.event.ConnectionCheckedInEvent;
import com.mongodb.event.ConnectionCheckedOutEvent;
import com.mongodb.event.ConnectionPoolClosedEvent;
import com.mongodb.event.ConnectionPoolListener;
import com.mongodb.event.ConnectionPoolOpenedEvent;
import com.mongodb.event.ConnectionPoolWaitQueueEnteredEvent;
import com.mongodb.event.ConnectionPoolWaitQueueExitedEvent;
import com.mongodb.event.ConnectionRemovedEvent;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.connection.ConcurrentPool;
import com.mongodb.internal.event.EventListenerHelper;
import com.mongodb.internal.thread.DaemonThreadFactory;
import com.mongodb.session.SessionContext;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.bson.ByteBuf;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultConnectionPool.class */
class DefaultConnectionPool implements ConnectionPool {
    private static final Logger LOGGER = Loggers.getLogger("connection");
    private final ConcurrentPool<UsageTrackingInternalConnection> pool;
    private final ConnectionPoolSettings settings;
    private final AtomicInteger waitQueueSize = new AtomicInteger(0);
    private final AtomicInteger generation = new AtomicInteger(0);
    private final ExecutorService sizeMaintenanceTimer;
    private ExecutorService asyncGetter;
    private final Runnable maintenanceTask;
    private final ConnectionPoolListener connectionPoolListener;
    private final ServerId serverId;
    private volatile boolean closed;

    DefaultConnectionPool(ServerId serverId, InternalConnectionFactory internalConnectionFactory, ConnectionPoolSettings settings) {
        this.serverId = (ServerId) Assertions.notNull("serverId", serverId);
        this.settings = (ConnectionPoolSettings) Assertions.notNull("settings", settings);
        UsageTrackingInternalConnectionItemFactory connectionItemFactory = new UsageTrackingInternalConnectionItemFactory(internalConnectionFactory);
        this.pool = new ConcurrentPool<>(settings.getMaxSize(), connectionItemFactory);
        this.maintenanceTask = createMaintenanceTask();
        this.sizeMaintenanceTimer = createMaintenanceTimer();
        this.connectionPoolListener = EventListenerHelper.getConnectionPoolListener(settings);
        this.connectionPoolListener.connectionPoolOpened(new ConnectionPoolOpenedEvent(serverId, settings));
    }

    @Override // com.mongodb.internal.connection.ConnectionPool
    public InternalConnection get() {
        return get(this.settings.getMaxWaitTime(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS);
    }

    @Override // com.mongodb.internal.connection.ConnectionPool
    public InternalConnection get(long timeout, TimeUnit timeUnit) {
        try {
            if (this.waitQueueSize.incrementAndGet() > this.settings.getMaxWaitQueueSize()) {
                throw createWaitQueueFullException();
            }
            try {
                this.connectionPoolListener.waitQueueEntered(new ConnectionPoolWaitQueueEnteredEvent(this.serverId));
                PooledConnection pooledConnection = getPooledConnection(timeout, timeUnit);
                if (!pooledConnection.opened()) {
                    try {
                        pooledConnection.open();
                    } catch (Throwable t) {
                        this.pool.release(pooledConnection.wrapped, true);
                        if (t instanceof MongoException) {
                            throw ((MongoException) t);
                        }
                        throw new MongoInternalException(t.toString(), t);
                    }
                }
                this.waitQueueSize.decrementAndGet();
                return pooledConnection;
            } finally {
                this.connectionPoolListener.waitQueueExited(new ConnectionPoolWaitQueueExitedEvent(this.serverId));
            }
        } catch (Throwable th) {
            this.waitQueueSize.decrementAndGet();
            throw th;
        }
    }

    @Override // com.mongodb.internal.connection.ConnectionPool
    public void getAsync(SingleResultCallback<InternalConnection> callback) {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Asynchronously getting a connection from the pool for server %s", this.serverId));
        }
        final SingleResultCallback<InternalConnection> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, LOGGER);
        PooledConnection connection = null;
        try {
            connection = getPooledConnection(0L, TimeUnit.MILLISECONDS);
        } catch (MongoTimeoutException e) {
        } catch (Throwable t) {
            callback.onResult(null, t);
            return;
        }
        if (connection != null) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(String.format("Asynchronously opening pooled connection %s to server %s", connection.getDescription().getConnectionId(), this.serverId));
            }
            openAsync(connection, errHandlingCallback);
        } else {
            if (this.waitQueueSize.incrementAndGet() > this.settings.getMaxWaitQueueSize()) {
                this.waitQueueSize.decrementAndGet();
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace(String.format("Asynchronously failing to get a pooled connection to %s because the wait queue is full", this.serverId));
                }
                callback.onResult(null, createWaitQueueFullException());
                return;
            }
            final long startTimeMillis = System.currentTimeMillis();
            this.connectionPoolListener.waitQueueEntered(new ConnectionPoolWaitQueueEnteredEvent(this.serverId));
            getAsyncGetter().submit(new Runnable() { // from class: com.mongodb.internal.connection.DefaultConnectionPool.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (getRemainingWaitTime() <= 0) {
                            errHandlingCallback.onResult(null, DefaultConnectionPool.this.createTimeoutException());
                        } else {
                            PooledConnection connection2 = DefaultConnectionPool.this.getPooledConnection(getRemainingWaitTime(), TimeUnit.MILLISECONDS);
                            DefaultConnectionPool.this.openAsync(connection2, errHandlingCallback);
                        }
                    } catch (Throwable t2) {
                        errHandlingCallback.onResult(null, t2);
                    } finally {
                        DefaultConnectionPool.this.waitQueueSize.decrementAndGet();
                        DefaultConnectionPool.this.connectionPoolListener.waitQueueExited(new ConnectionPoolWaitQueueExitedEvent(DefaultConnectionPool.this.serverId));
                    }
                }

                private long getRemainingWaitTime() {
                    return (startTimeMillis + DefaultConnectionPool.this.settings.getMaxWaitTime(TimeUnit.MILLISECONDS)) - System.currentTimeMillis();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openAsync(final PooledConnection pooledConnection, final SingleResultCallback<InternalConnection> callback) {
        if (pooledConnection.opened()) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(String.format("Pooled connection %s to server %s is already open", pooledConnection.getDescription().getConnectionId(), this.serverId));
            }
            callback.onResult(pooledConnection, null);
        } else {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(String.format("Pooled connection %s to server %s is not yet open", pooledConnection.getDescription().getConnectionId(), this.serverId));
            }
            pooledConnection.openAsync(new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.DefaultConnectionPool.2
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(Void result, Throwable t) {
                    if (t != null) {
                        if (DefaultConnectionPool.LOGGER.isTraceEnabled()) {
                            DefaultConnectionPool.LOGGER.trace(String.format("Pooled connection %s to server %s failed to open", pooledConnection.getDescription().getConnectionId(), DefaultConnectionPool.this.serverId));
                        }
                        callback.onResult(null, t);
                        DefaultConnectionPool.this.pool.release(pooledConnection.wrapped, true);
                        return;
                    }
                    if (DefaultConnectionPool.LOGGER.isTraceEnabled()) {
                        DefaultConnectionPool.LOGGER.trace(String.format("Pooled connection %s to server %s is now open", pooledConnection.getDescription().getConnectionId(), DefaultConnectionPool.this.serverId));
                    }
                    callback.onResult(pooledConnection, null);
                }
            });
        }
    }

    private synchronized ExecutorService getAsyncGetter() {
        if (this.asyncGetter == null) {
            this.asyncGetter = Executors.newSingleThreadExecutor(new DaemonThreadFactory("AsyncGetter"));
        }
        return this.asyncGetter;
    }

    private synchronized void shutdownAsyncGetter() {
        if (this.asyncGetter != null) {
            this.asyncGetter.shutdownNow();
        }
    }

    @Override // com.mongodb.internal.connection.ConnectionPool
    public void invalidate() {
        LOGGER.debug("Invalidating the connection pool");
        this.generation.incrementAndGet();
    }

    @Override // com.mongodb.internal.connection.ConnectionPool, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!this.closed) {
            this.pool.close();
            if (this.sizeMaintenanceTimer != null) {
                this.sizeMaintenanceTimer.shutdownNow();
            }
            shutdownAsyncGetter();
            this.closed = true;
            this.connectionPoolListener.connectionPoolClosed(new ConnectionPoolClosedEvent(this.serverId));
        }
    }

    public void doMaintenance() {
        if (this.maintenanceTask != null) {
            this.maintenanceTask.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PooledConnection getPooledConnection(long timeout, TimeUnit timeUnit) {
        UsageTrackingInternalConnection internalConnection;
        UsageTrackingInternalConnection usageTrackingInternalConnection = this.pool.get(timeout, timeUnit);
        while (true) {
            internalConnection = usageTrackingInternalConnection;
            if (!shouldPrune(internalConnection)) {
                break;
            }
            this.pool.release(internalConnection, true);
            usageTrackingInternalConnection = this.pool.get(timeout, timeUnit);
        }
        this.connectionPoolListener.connectionCheckedOut(new ConnectionCheckedOutEvent(internalConnection.getDescription().getConnectionId()));
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Checked out connection [%s] to server %s", getId(internalConnection), this.serverId.getAddress()));
        }
        return new PooledConnection(internalConnection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoTimeoutException createTimeoutException() {
        return new MongoTimeoutException(String.format("Timed out after %d ms while waiting for a connection to server %s.", Long.valueOf(this.settings.getMaxWaitTime(TimeUnit.MILLISECONDS)), this.serverId.getAddress()));
    }

    private MongoWaitQueueFullException createWaitQueueFullException() {
        return new MongoWaitQueueFullException(String.format("Too many threads are already waiting for a connection. Max number of threads (maxWaitQueueSize) of %d has been exceeded.", Integer.valueOf(this.settings.getMaxWaitQueueSize())));
    }

    ConcurrentPool<UsageTrackingInternalConnection> getPool() {
        return this.pool;
    }

    private Runnable createMaintenanceTask() {
        Runnable newMaintenanceTask = null;
        if (shouldPrune() || shouldEnsureMinSize()) {
            newMaintenanceTask = new Runnable() { // from class: com.mongodb.internal.connection.DefaultConnectionPool.3
                @Override // java.lang.Runnable
                public synchronized void run() {
                    try {
                        if (DefaultConnectionPool.this.shouldPrune()) {
                            if (DefaultConnectionPool.LOGGER.isDebugEnabled()) {
                                DefaultConnectionPool.LOGGER.debug(String.format("Pruning pooled connections to %s", DefaultConnectionPool.this.serverId.getAddress()));
                            }
                            DefaultConnectionPool.this.pool.prune();
                        }
                        if (DefaultConnectionPool.this.shouldEnsureMinSize()) {
                            if (DefaultConnectionPool.LOGGER.isDebugEnabled()) {
                                DefaultConnectionPool.LOGGER.debug(String.format("Ensuring minimum pooled connections to %s", DefaultConnectionPool.this.serverId.getAddress()));
                            }
                            DefaultConnectionPool.this.pool.ensureMinSize(DefaultConnectionPool.this.settings.getMinSize(), true);
                        }
                    } catch (MongoInterruptedException e) {
                    } catch (Exception e2) {
                        DefaultConnectionPool.LOGGER.warn("Exception thrown during connection pool background maintenance task", e2);
                    }
                }
            };
        }
        return newMaintenanceTask;
    }

    private ExecutorService createMaintenanceTimer() {
        if (this.maintenanceTask == null) {
            return null;
        }
        ScheduledExecutorService newTimer = Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory("MaintenanceTimer"));
        newTimer.scheduleAtFixedRate(this.maintenanceTask, this.settings.getMaintenanceInitialDelay(TimeUnit.MILLISECONDS), this.settings.getMaintenanceFrequency(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS);
        return newTimer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldEnsureMinSize() {
        return this.settings.getMinSize() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldPrune() {
        return this.settings.getMaxConnectionIdleTime(TimeUnit.MILLISECONDS) > 0 || this.settings.getMaxConnectionLifeTime(TimeUnit.MILLISECONDS) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldPrune(UsageTrackingInternalConnection connection) {
        return fromPreviousGeneration(connection) || pastMaxLifeTime(connection) || pastMaxIdleTime(connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pastMaxIdleTime(UsageTrackingInternalConnection connection) {
        return expired(connection.getLastUsedAt(), System.currentTimeMillis(), this.settings.getMaxConnectionIdleTime(TimeUnit.MILLISECONDS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pastMaxLifeTime(UsageTrackingInternalConnection connection) {
        return expired(connection.getOpenedAt(), System.currentTimeMillis(), this.settings.getMaxConnectionLifeTime(TimeUnit.MILLISECONDS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean fromPreviousGeneration(UsageTrackingInternalConnection connection) {
        return this.generation.get() > connection.getGeneration();
    }

    private boolean expired(long startTime, long curTime, long maxTime) {
        return maxTime != 0 && curTime - startTime > maxTime;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementGenerationOnSocketException(InternalConnection connection, Throwable t) {
        if ((t instanceof MongoSocketException) && !(t instanceof MongoSocketReadTimeoutException)) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(String.format("Got socket exception on connection [%s] to %s. All connections to %s will be closed.", getId(connection), this.serverId.getAddress(), this.serverId.getAddress()));
            }
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ConnectionId getId(InternalConnection internalConnection) {
        return internalConnection.getDescription().getConnectionId();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultConnectionPool$PooledConnection.class */
    private class PooledConnection implements InternalConnection {
        private final UsageTrackingInternalConnection wrapped;
        private final AtomicBoolean isClosed = new AtomicBoolean();

        PooledConnection(UsageTrackingInternalConnection wrapped) {
            this.wrapped = (UsageTrackingInternalConnection) Assertions.notNull("wrapped", wrapped);
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public void open() {
            Assertions.isTrue("open", !this.isClosed.get());
            this.wrapped.open();
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public void openAsync(SingleResultCallback<Void> callback) {
            Assertions.isTrue("open", !this.isClosed.get());
            this.wrapped.openAsync(callback);
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public void close() {
            if (!this.isClosed.getAndSet(true)) {
                if (!DefaultConnectionPool.this.closed) {
                    DefaultConnectionPool.this.connectionPoolListener.connectionCheckedIn(new ConnectionCheckedInEvent(DefaultConnectionPool.this.getId(this.wrapped)));
                    if (DefaultConnectionPool.LOGGER.isTraceEnabled()) {
                        DefaultConnectionPool.LOGGER.trace(String.format("Checked in connection [%s] to server %s", DefaultConnectionPool.this.getId(this.wrapped), DefaultConnectionPool.this.serverId.getAddress()));
                    }
                }
                DefaultConnectionPool.this.pool.release(this.wrapped, this.wrapped.isClosed() || DefaultConnectionPool.this.shouldPrune(this.wrapped));
            }
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public boolean opened() {
            Assertions.isTrue("open", !this.isClosed.get());
            return this.wrapped.opened();
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public boolean isClosed() {
            return this.isClosed.get() || this.wrapped.isClosed();
        }

        @Override // com.mongodb.connection.BufferProvider
        public ByteBuf getBuffer(int capacity) {
            return this.wrapped.getBuffer(capacity);
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public void sendMessage(List<ByteBuf> byteBuffers, int lastRequestId) {
            Assertions.isTrue("open", !this.isClosed.get());
            try {
                this.wrapped.sendMessage(byteBuffers, lastRequestId);
            } catch (MongoException e) {
                DefaultConnectionPool.this.incrementGenerationOnSocketException(this, e);
                throw e;
            }
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public <T> T sendAndReceive(CommandMessage commandMessage, Decoder<T> decoder, SessionContext sessionContext) {
            Assertions.isTrue("open", !this.isClosed.get());
            try {
                return (T) this.wrapped.sendAndReceive(commandMessage, decoder, sessionContext);
            } catch (MongoException e) {
                DefaultConnectionPool.this.incrementGenerationOnSocketException(this, e);
                throw e;
            }
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public <T> void sendAndReceiveAsync(CommandMessage message, Decoder<T> decoder, SessionContext sessionContext, final SingleResultCallback<T> callback) {
            Assertions.isTrue("open", !this.isClosed.get());
            this.wrapped.sendAndReceiveAsync(message, decoder, sessionContext, new SingleResultCallback<T>() { // from class: com.mongodb.internal.connection.DefaultConnectionPool.PooledConnection.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(T result, Throwable t) {
                    if (t != null) {
                        DefaultConnectionPool.this.incrementGenerationOnSocketException(PooledConnection.this, t);
                    }
                    callback.onResult(result, t);
                }
            });
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public ResponseBuffers receiveMessage(int responseTo) {
            Assertions.isTrue("open", !this.isClosed.get());
            try {
                return this.wrapped.receiveMessage(responseTo);
            } catch (MongoException e) {
                DefaultConnectionPool.this.incrementGenerationOnSocketException(this, e);
                throw e;
            }
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public void sendMessageAsync(List<ByteBuf> byteBuffers, int lastRequestId, final SingleResultCallback<Void> callback) {
            Assertions.isTrue("open", !this.isClosed.get());
            this.wrapped.sendMessageAsync(byteBuffers, lastRequestId, new SingleResultCallback<Void>() { // from class: com.mongodb.internal.connection.DefaultConnectionPool.PooledConnection.2
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(Void result, Throwable t) {
                    if (t != null) {
                        DefaultConnectionPool.this.incrementGenerationOnSocketException(PooledConnection.this, t);
                    }
                    callback.onResult(null, t);
                }
            });
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public void receiveMessageAsync(int responseTo, final SingleResultCallback<ResponseBuffers> callback) {
            Assertions.isTrue("open", !this.isClosed.get());
            this.wrapped.receiveMessageAsync(responseTo, new SingleResultCallback<ResponseBuffers>() { // from class: com.mongodb.internal.connection.DefaultConnectionPool.PooledConnection.3
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(ResponseBuffers result, Throwable t) {
                    if (t != null) {
                        DefaultConnectionPool.this.incrementGenerationOnSocketException(PooledConnection.this, t);
                    }
                    callback.onResult(result, t);
                }
            });
        }

        @Override // com.mongodb.internal.connection.InternalConnection
        public ConnectionDescription getDescription() {
            Assertions.isTrue("open", !this.isClosed.get());
            return this.wrapped.getDescription();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultConnectionPool$UsageTrackingInternalConnectionItemFactory.class */
    private class UsageTrackingInternalConnectionItemFactory implements ConcurrentPool.ItemFactory<UsageTrackingInternalConnection> {
        private final InternalConnectionFactory internalConnectionFactory;

        UsageTrackingInternalConnectionItemFactory(InternalConnectionFactory internalConnectionFactory) {
            this.internalConnectionFactory = internalConnectionFactory;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
        public UsageTrackingInternalConnection create(boolean initialize) {
            UsageTrackingInternalConnection internalConnection = new UsageTrackingInternalConnection(this.internalConnectionFactory.create(DefaultConnectionPool.this.serverId), DefaultConnectionPool.this.generation.get());
            if (initialize) {
                internalConnection.open();
            }
            DefaultConnectionPool.this.connectionPoolListener.connectionAdded(new ConnectionAddedEvent(DefaultConnectionPool.this.getId(internalConnection)));
            return internalConnection;
        }

        @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
        public void close(UsageTrackingInternalConnection connection) {
            if (!DefaultConnectionPool.this.closed) {
                DefaultConnectionPool.this.connectionPoolListener.connectionRemoved(new ConnectionRemovedEvent(DefaultConnectionPool.this.getId(connection)));
            }
            if (DefaultConnectionPool.LOGGER.isInfoEnabled()) {
                DefaultConnectionPool.LOGGER.info(String.format("Closed connection [%s] to %s because %s.", DefaultConnectionPool.this.getId(connection), DefaultConnectionPool.this.serverId.getAddress(), getReasonForClosing(connection)));
            }
            connection.close();
        }

        private String getReasonForClosing(UsageTrackingInternalConnection connection) {
            String reason;
            if (!connection.isClosed()) {
                if (!DefaultConnectionPool.this.fromPreviousGeneration(connection)) {
                    if (!DefaultConnectionPool.this.pastMaxLifeTime(connection)) {
                        if (DefaultConnectionPool.this.pastMaxIdleTime(connection)) {
                            reason = "it is past its maximum allowed idle time";
                        } else {
                            reason = "the pool has been closed";
                        }
                    } else {
                        reason = "it is past its maximum allowed life time";
                    }
                } else {
                    reason = "there was a socket exception raised on another connection from this pool";
                }
            } else {
                reason = "there was a socket exception raised by this connection";
            }
            return reason;
        }

        @Override // com.mongodb.internal.connection.ConcurrentPool.ItemFactory
        public ConcurrentPool.Prune shouldPrune(UsageTrackingInternalConnection usageTrackingConnection) {
            return DefaultConnectionPool.this.shouldPrune(usageTrackingConnection) ? ConcurrentPool.Prune.YES : ConcurrentPool.Prune.NO;
        }
    }
}
