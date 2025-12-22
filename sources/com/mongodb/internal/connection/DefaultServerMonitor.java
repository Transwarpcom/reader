package com.mongodb.internal.connection;

import com.mongodb.MongoSocketException;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ServerConnectionState;
import com.mongodb.connection.ServerDescription;
import com.mongodb.connection.ServerId;
import com.mongodb.connection.ServerSettings;
import com.mongodb.connection.ServerType;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.ServerHeartbeatFailedEvent;
import com.mongodb.event.ServerHeartbeatStartedEvent;
import com.mongodb.event.ServerHeartbeatSucceededEvent;
import com.mongodb.event.ServerMonitorListener;
import com.mongodb.internal.event.EventListenerHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.bson.BsonDocument;
import org.bson.BsonInt32;

@ThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultServerMonitor.class */
class DefaultServerMonitor implements ServerMonitor {
    private static final Logger LOGGER = Loggers.getLogger("cluster");
    private final ServerId serverId;
    private final ServerMonitorListener serverMonitorListener;
    private final ClusterClock clusterClock;
    private final ChangeListener<ServerDescription> serverStateListener;
    private final InternalConnectionFactory internalConnectionFactory;
    private final ConnectionPool connectionPool;
    private final ServerSettings serverSettings;
    private final Thread monitorThread;
    private volatile boolean isClosed;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = this.lock.newCondition();
    private final ServerMonitorRunnable monitor = new ServerMonitorRunnable();

    DefaultServerMonitor(ServerId serverId, ServerSettings serverSettings, ClusterClock clusterClock, ChangeListener<ServerDescription> serverStateListener, InternalConnectionFactory internalConnectionFactory, ConnectionPool connectionPool) {
        this.serverSettings = (ServerSettings) Assertions.notNull("serverSettings", serverSettings);
        this.serverId = (ServerId) Assertions.notNull("serverId", serverId);
        this.serverMonitorListener = EventListenerHelper.getServerMonitorListener(serverSettings);
        this.clusterClock = (ClusterClock) Assertions.notNull("clusterClock", clusterClock);
        this.serverStateListener = serverStateListener;
        this.internalConnectionFactory = (InternalConnectionFactory) Assertions.notNull("internalConnectionFactory", internalConnectionFactory);
        this.connectionPool = connectionPool;
        this.monitorThread = new Thread(this.monitor, "cluster-" + this.serverId.getClusterId() + "-" + this.serverId.getAddress());
        this.monitorThread.setDaemon(true);
        this.isClosed = false;
    }

    @Override // com.mongodb.internal.connection.ServerMonitor
    public void start() {
        this.monitorThread.start();
    }

    @Override // com.mongodb.internal.connection.ServerMonitor
    public void connect() {
        this.lock.lock();
        try {
            this.condition.signal();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // com.mongodb.internal.connection.ServerMonitor
    public void close() {
        this.isClosed = true;
        this.monitorThread.interrupt();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DefaultServerMonitor$ServerMonitorRunnable.class */
    class ServerMonitorRunnable implements Runnable {
        private final ExponentiallyWeightedMovingAverage averageRoundTripTime = new ExponentiallyWeightedMovingAverage(0.2d);

        ServerMonitorRunnable() {
        }

        @Override // java.lang.Runnable
        public synchronized void run() {
            InternalConnection connection = null;
            try {
                ServerDescription currentServerDescription = getConnectingServerDescription(null);
                while (!DefaultServerMonitor.this.isClosed) {
                    ServerDescription previousServerDescription = currentServerDescription;
                    if (connection == null) {
                        try {
                            connection = DefaultServerMonitor.this.internalConnectionFactory.create(DefaultServerMonitor.this.serverId);
                            try {
                                connection.open();
                                try {
                                    currentServerDescription = lookupServerDescription(connection);
                                } catch (MongoSocketException e) {
                                    DefaultServerMonitor.this.connectionPool.invalidate();
                                    connection.close();
                                    connection = DefaultServerMonitor.this.internalConnectionFactory.create(DefaultServerMonitor.this.serverId);
                                    try {
                                        connection.open();
                                        try {
                                            currentServerDescription = lookupServerDescription(connection);
                                        } catch (MongoSocketException e1) {
                                            connection.close();
                                            throw e1;
                                        }
                                    } finally {
                                    }
                                }
                            } finally {
                            }
                        } catch (Throwable t) {
                            this.averageRoundTripTime.reset();
                            currentServerDescription = getConnectingServerDescription(t);
                        }
                    } else {
                        currentServerDescription = lookupServerDescription(connection);
                    }
                    if (!DefaultServerMonitor.this.isClosed) {
                        try {
                            logStateChange(previousServerDescription, currentServerDescription);
                            DefaultServerMonitor.this.serverStateListener.stateChanged(new ChangeEvent(previousServerDescription, currentServerDescription));
                        } catch (Throwable t2) {
                            DefaultServerMonitor.LOGGER.warn("Exception in monitor thread during notification of server description state change", t2);
                        }
                        waitForNext();
                    }
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }

        private ServerDescription getConnectingServerDescription(Throwable exception) {
            return ServerDescription.builder().type(ServerType.UNKNOWN).state(ServerConnectionState.CONNECTING).address(DefaultServerMonitor.this.serverId.getAddress()).exception(exception).build();
        }

        private ServerDescription lookupServerDescription(InternalConnection connection) {
            if (DefaultServerMonitor.LOGGER.isDebugEnabled()) {
                DefaultServerMonitor.LOGGER.debug(String.format("Checking status of %s", DefaultServerMonitor.this.serverId.getAddress()));
            }
            DefaultServerMonitor.this.serverMonitorListener.serverHearbeatStarted(new ServerHeartbeatStartedEvent(connection.getDescription().getConnectionId()));
            long start = System.nanoTime();
            try {
                BsonDocument isMasterResult = CommandHelper.executeCommand("admin", new BsonDocument("ismaster", new BsonInt32(1)), DefaultServerMonitor.this.clusterClock, connection);
                long elapsedTimeNanos = System.nanoTime() - start;
                this.averageRoundTripTime.addSample(elapsedTimeNanos);
                DefaultServerMonitor.this.serverMonitorListener.serverHeartbeatSucceeded(new ServerHeartbeatSucceededEvent(connection.getDescription().getConnectionId(), isMasterResult, elapsedTimeNanos));
                return DescriptionHelper.createServerDescription(DefaultServerMonitor.this.serverId.getAddress(), isMasterResult, connection.getDescription().getServerVersion(), this.averageRoundTripTime.getAverage());
            } catch (RuntimeException e) {
                DefaultServerMonitor.this.serverMonitorListener.serverHeartbeatFailed(new ServerHeartbeatFailedEvent(connection.getDescription().getConnectionId(), System.nanoTime() - start, e));
                throw e;
            }
        }

        private void logStateChange(ServerDescription previousServerDescription, ServerDescription currentServerDescription) {
            if (DefaultServerMonitor.shouldLogStageChange(previousServerDescription, currentServerDescription)) {
                if (currentServerDescription.getException() == null) {
                    DefaultServerMonitor.LOGGER.info(String.format("Monitor thread successfully connected to server with description %s", currentServerDescription));
                } else {
                    DefaultServerMonitor.LOGGER.info(String.format("Exception in monitor thread while connecting to server %s", DefaultServerMonitor.this.serverId.getAddress()), currentServerDescription.getException());
                }
            }
        }

        private void waitForNext() throws InterruptedException {
            try {
                long timeRemaining = waitForSignalOrTimeout();
                if (timeRemaining > 0) {
                    long timeWaiting = DefaultServerMonitor.this.serverSettings.getHeartbeatFrequency(TimeUnit.NANOSECONDS) - timeRemaining;
                    long minimumNanosToWait = DefaultServerMonitor.this.serverSettings.getMinHeartbeatFrequency(TimeUnit.NANOSECONDS);
                    if (timeWaiting < minimumNanosToWait) {
                        long millisToSleep = TimeUnit.MILLISECONDS.convert(minimumNanosToWait - timeWaiting, TimeUnit.NANOSECONDS);
                        if (millisToSleep > 0) {
                            Thread.sleep(millisToSleep);
                        }
                    }
                }
            } catch (InterruptedException e) {
            }
        }

        private long waitForSignalOrTimeout() throws InterruptedException {
            DefaultServerMonitor.this.lock.lock();
            try {
                return DefaultServerMonitor.this.condition.awaitNanos(DefaultServerMonitor.this.serverSettings.getHeartbeatFrequency(TimeUnit.NANOSECONDS));
            } finally {
                DefaultServerMonitor.this.lock.unlock();
            }
        }
    }

    static boolean shouldLogStageChange(ServerDescription previous, ServerDescription current) {
        if (previous.isOk() != current.isOk() || !previous.getAddress().equals(current.getAddress())) {
            return true;
        }
        if (previous.getCanonicalAddress() != null) {
            if (!previous.getCanonicalAddress().equals(current.getCanonicalAddress())) {
                return true;
            }
        } else if (current.getCanonicalAddress() != null) {
            return true;
        }
        if (!previous.getHosts().equals(current.getHosts()) || !previous.getArbiters().equals(current.getArbiters()) || !previous.getPassives().equals(current.getPassives())) {
            return true;
        }
        if (previous.getPrimary() != null) {
            if (!previous.getPrimary().equals(current.getPrimary())) {
                return true;
            }
        } else if (current.getPrimary() != null) {
            return true;
        }
        if (previous.getSetName() != null) {
            if (!previous.getSetName().equals(current.getSetName())) {
                return true;
            }
        } else if (current.getSetName() != null) {
            return true;
        }
        if (previous.getState() != current.getState() || !previous.getTagSet().equals(current.getTagSet()) || previous.getType() != current.getType() || !previous.getVersion().equals(current.getVersion())) {
            return true;
        }
        if (previous.getElectionId() != null) {
            if (!previous.getElectionId().equals(current.getElectionId())) {
                return true;
            }
        } else if (current.getElectionId() != null) {
            return true;
        }
        if (previous.getSetVersion() != null) {
            if (!previous.getSetVersion().equals(current.getSetVersion())) {
                return true;
            }
        } else if (current.getSetVersion() != null) {
            return true;
        }
        Class<?> thisExceptionClass = previous.getException() != null ? previous.getException().getClass() : null;
        Class<?> thatExceptionClass = current.getException() != null ? current.getException().getClass() : null;
        if (thisExceptionClass != null) {
            if (!thisExceptionClass.equals(thatExceptionClass)) {
                return true;
            }
        } else if (thatExceptionClass != null) {
            return true;
        }
        String thisExceptionMessage = previous.getException() != null ? previous.getException().getMessage() : null;
        String thatExceptionMessage = current.getException() != null ? current.getException().getMessage() : null;
        if (thisExceptionMessage != null) {
            if (!thisExceptionMessage.equals(thatExceptionMessage)) {
                return true;
            }
            return false;
        }
        if (thatExceptionMessage != null) {
            return true;
        }
        return false;
    }
}
