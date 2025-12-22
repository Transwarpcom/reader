package com.mongodb.internal.connection;

import com.mongodb.MongoClientException;
import com.mongodb.MongoIncompatibleDriverException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.MongoWaitQueueFullException;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.Cluster;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterId;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterType;
import com.mongodb.connection.Server;
import com.mongodb.connection.ServerDescription;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.ClusterClosedEvent;
import com.mongodb.event.ClusterDescriptionChangedEvent;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.ClusterOpeningEvent;
import com.mongodb.event.ServerListener;
import com.mongodb.internal.event.EventListenerHelper;
import com.mongodb.selector.CompositeServerSelector;
import com.mongodb.selector.ServerSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/BaseCluster.class */
abstract class BaseCluster implements Cluster {
    private static final Logger LOGGER = Loggers.getLogger("cluster");
    private final ClusterableServerFactory serverFactory;
    private final ClusterId clusterId;
    private final ClusterSettings settings;
    private final ClusterListener clusterListener;
    private Thread waitQueueHandler;
    private volatile boolean isClosed;
    private volatile ClusterDescription description;
    private final AtomicReference<CountDownLatch> phase = new AtomicReference<>(new CountDownLatch(1));
    private final ThreadLocal<Random> random = new ThreadLocal<>();
    private final Deque<ServerSelectionRequest> waitQueue = new ConcurrentLinkedDeque();
    private final AtomicInteger waitQueueSize = new AtomicInteger(0);
    private final ClusterClock clusterClock = new ClusterClock();

    protected abstract void connect();

    protected abstract ClusterableServer getServer(ServerAddress serverAddress);

    BaseCluster(ClusterId clusterId, ClusterSettings settings, ClusterableServerFactory serverFactory) {
        this.clusterId = (ClusterId) Assertions.notNull("clusterId", clusterId);
        this.settings = (ClusterSettings) Assertions.notNull("settings", settings);
        this.serverFactory = (ClusterableServerFactory) Assertions.notNull("serverFactory", serverFactory);
        this.clusterListener = EventListenerHelper.getClusterListener(settings);
        this.clusterListener.clusterOpening(new ClusterOpeningEvent(clusterId));
    }

    @Override // com.mongodb.connection.Cluster
    public BsonTimestamp getClusterTime() {
        return this.clusterClock.getClusterTime();
    }

    @Override // com.mongodb.connection.Cluster
    public Server selectServer(ServerSelector serverSelector) throws InterruptedException {
        Assertions.isTrue("open", !isClosed());
        try {
            CountDownLatch currentPhase = this.phase.get();
            ClusterDescription curDescription = this.description;
            ServerSelector compositeServerSelector = getCompositeServerSelector(serverSelector);
            Server server = selectRandomServer(compositeServerSelector, curDescription);
            boolean selectionFailureLogged = false;
            long startTimeNanos = System.nanoTime();
            long curTimeNanos = startTimeNanos;
            long maxWaitTimeNanos = getMaxWaitTimeNanos();
            while (true) {
                throwIfIncompatible(curDescription);
                if (server != null) {
                    return server;
                }
                if (curTimeNanos - startTimeNanos > maxWaitTimeNanos) {
                    throw createTimeoutException(serverSelector, curDescription);
                }
                if (!selectionFailureLogged) {
                    logServerSelectionFailure(serverSelector, curDescription);
                    selectionFailureLogged = true;
                }
                connect();
                currentPhase.await(Math.min(maxWaitTimeNanos - (curTimeNanos - startTimeNanos), getMinWaitTimeNanos()), TimeUnit.NANOSECONDS);
                curTimeNanos = System.nanoTime();
                currentPhase = this.phase.get();
                curDescription = this.description;
                server = selectRandomServer(compositeServerSelector, curDescription);
            }
        } catch (InterruptedException e) {
            throw new MongoInterruptedException(String.format("Interrupted while waiting for a server that matches %s", serverSelector), e);
        }
    }

    @Override // com.mongodb.connection.Cluster
    public void selectServerAsync(ServerSelector serverSelector, SingleResultCallback<Server> callback) {
        Assertions.isTrue("open", !isClosed());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Asynchronously selecting server with selector %s", serverSelector));
        }
        ServerSelectionRequest request = new ServerSelectionRequest(serverSelector, getCompositeServerSelector(serverSelector), getMaxWaitTimeNanos(), callback);
        CountDownLatch currentPhase = this.phase.get();
        ClusterDescription currentDescription = this.description;
        if (!handleServerSelectionRequest(request, currentPhase, currentDescription)) {
            notifyWaitQueueHandler(request);
        }
    }

    @Override // com.mongodb.connection.Cluster
    public ClusterDescription getDescription() throws InterruptedException {
        Assertions.isTrue("open", !isClosed());
        try {
            CountDownLatch currentPhase = this.phase.get();
            ClusterDescription curDescription = this.description;
            boolean selectionFailureLogged = false;
            long startTimeNanos = System.nanoTime();
            long curTimeNanos = startTimeNanos;
            long maxWaitTimeNanos = getMaxWaitTimeNanos();
            while (curDescription.getType() == ClusterType.UNKNOWN) {
                if (curTimeNanos - startTimeNanos > maxWaitTimeNanos) {
                    throw new MongoTimeoutException(String.format("Timed out after %d ms while waiting to connect. Client view of cluster state is %s", Long.valueOf(this.settings.getServerSelectionTimeout(TimeUnit.MILLISECONDS)), curDescription.getShortDescription()));
                }
                if (!selectionFailureLogged) {
                    if (LOGGER.isInfoEnabled()) {
                        if (this.settings.getServerSelectionTimeout(TimeUnit.MILLISECONDS) < 0) {
                            LOGGER.info("Cluster description not yet available. Waiting indefinitely.");
                        } else {
                            LOGGER.info(String.format("Cluster description not yet available. Waiting for %d ms before timing out", Long.valueOf(this.settings.getServerSelectionTimeout(TimeUnit.MILLISECONDS))));
                        }
                    }
                    selectionFailureLogged = true;
                }
                connect();
                currentPhase.await(Math.min(maxWaitTimeNanos - (curTimeNanos - startTimeNanos), getMinWaitTimeNanos()), TimeUnit.NANOSECONDS);
                curTimeNanos = System.nanoTime();
                currentPhase = this.phase.get();
                curDescription = this.description;
            }
            return curDescription;
        } catch (InterruptedException e) {
            throw new MongoInterruptedException("Interrupted while waiting to connect", e);
        }
    }

    protected ClusterId getClusterId() {
        return this.clusterId;
    }

    @Override // com.mongodb.connection.Cluster
    public ClusterSettings getSettings() {
        return this.settings;
    }

    public ClusterableServerFactory getServerFactory() {
        return this.serverFactory;
    }

    @Override // com.mongodb.connection.Cluster, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!isClosed()) {
            this.isClosed = true;
            this.phase.get().countDown();
            this.clusterListener.clusterClosed(new ClusterClosedEvent(this.clusterId));
            stopWaitQueueHandler();
        }
    }

    @Override // com.mongodb.connection.Cluster
    public boolean isClosed() {
        return this.isClosed;
    }

    protected synchronized void updateDescription(ClusterDescription newDescription) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Updating cluster description to  %s", newDescription.getShortDescription()));
        }
        this.description = newDescription;
        this.phase.getAndSet(new CountDownLatch(1)).countDown();
    }

    protected void fireChangeEvent(ClusterDescriptionChangedEvent event) {
        this.clusterListener.clusterDescriptionChanged(event);
    }

    @Override // com.mongodb.connection.Cluster
    public ClusterDescription getCurrentDescription() {
        return this.description;
    }

    private long getMaxWaitTimeNanos() {
        if (this.settings.getServerSelectionTimeout(TimeUnit.NANOSECONDS) < 0) {
            return Long.MAX_VALUE;
        }
        return this.settings.getServerSelectionTimeout(TimeUnit.NANOSECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getMinWaitTimeNanos() {
        return this.serverFactory.getSettings().getMinHeartbeatFrequency(TimeUnit.NANOSECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleServerSelectionRequest(ServerSelectionRequest request, CountDownLatch currentPhase, ClusterDescription description) {
        try {
            if (currentPhase != request.phase) {
                CountDownLatch prevPhase = request.phase;
                request.phase = currentPhase;
                if (!description.isCompatibleWithDriver()) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace("Asynchronously failed server selection due to driver incompatibility with server");
                    }
                    request.onResult(null, createIncompatibleException(description));
                    return true;
                }
                Server server = selectRandomServer(request.compositeSelector, description);
                if (server != null) {
                    if (LOGGER.isTraceEnabled()) {
                        LOGGER.trace(String.format("Asynchronously selected server %s", server.getDescription().getAddress()));
                    }
                    request.onResult(server, null);
                    return true;
                }
                if (prevPhase == null) {
                    logServerSelectionFailure(request.originalSelector, description);
                }
            }
            if (request.timedOut()) {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("Asynchronously failed server selection after timeout");
                }
                request.onResult(null, createTimeoutException(request.originalSelector, description));
                return true;
            }
            return false;
        } catch (Exception e) {
            request.onResult(null, e);
            return true;
        }
    }

    private void logServerSelectionFailure(ServerSelector serverSelector, ClusterDescription curDescription) {
        if (LOGGER.isInfoEnabled()) {
            if (this.settings.getServerSelectionTimeout(TimeUnit.MILLISECONDS) < 0) {
                LOGGER.info(String.format("No server chosen by %s from cluster description %s. Waiting indefinitely.", serverSelector, curDescription));
            } else {
                LOGGER.info(String.format("No server chosen by %s from cluster description %s. Waiting for %d ms before timing out", serverSelector, curDescription, Long.valueOf(this.settings.getServerSelectionTimeout(TimeUnit.MILLISECONDS))));
            }
        }
    }

    private Server selectRandomServer(ServerSelector serverSelector, ClusterDescription clusterDescription) {
        List<ServerDescription> serverDescriptions = serverSelector.select(clusterDescription);
        if (!serverDescriptions.isEmpty()) {
            return getRandomServer(new ArrayList(serverDescriptions));
        }
        return null;
    }

    private ServerSelector getCompositeServerSelector(ServerSelector serverSelector) {
        if (this.settings.getServerSelector() == null) {
            return serverSelector;
        }
        return new CompositeServerSelector(Arrays.asList(serverSelector, this.settings.getServerSelector()));
    }

    private ClusterableServer getRandomServer(List<ServerDescription> serverDescriptions) {
        while (!serverDescriptions.isEmpty()) {
            int serverPos = getRandom().nextInt(serverDescriptions.size());
            ClusterableServer server = getServer(serverDescriptions.get(serverPos).getAddress());
            if (server != null) {
                return server;
            }
            serverDescriptions.remove(serverPos);
        }
        return null;
    }

    private Random getRandom() {
        Random result = this.random.get();
        if (result == null) {
            result = new Random();
            this.random.set(result);
        }
        return result;
    }

    protected ClusterableServer createServer(ServerAddress serverAddress, ServerListener serverListener) {
        return this.serverFactory.create(serverAddress, EventListenerHelper.createServerListener(this.serverFactory.getSettings(), serverListener), this.clusterClock);
    }

    private void throwIfIncompatible(ClusterDescription curDescription) {
        if (!curDescription.isCompatibleWithDriver()) {
            throw createIncompatibleException(curDescription);
        }
    }

    private MongoIncompatibleDriverException createIncompatibleException(ClusterDescription curDescription) {
        String message;
        ServerDescription incompatibleServer = curDescription.findServerIncompatiblyOlderThanDriver();
        if (incompatibleServer != null) {
            message = String.format("Server at %s reports wire version %d, but this version of the driver requires at least %d (MongoDB %s).", incompatibleServer.getAddress(), Integer.valueOf(incompatibleServer.getMaxWireVersion()), 1, ServerDescription.MIN_DRIVER_SERVER_VERSION);
        } else {
            ServerDescription incompatibleServer2 = curDescription.findServerIncompatiblyNewerThanDriver();
            message = String.format("Server at %s requires wire version %d, but this version of the driver only supports up to %d.", incompatibleServer2.getAddress(), Integer.valueOf(incompatibleServer2.getMinWireVersion()), 6);
        }
        return new MongoIncompatibleDriverException(message, curDescription);
    }

    private MongoTimeoutException createTimeoutException(ServerSelector serverSelector, ClusterDescription curDescription) {
        return new MongoTimeoutException(String.format("Timed out after %d ms while waiting for a server that matches %s. Client view of cluster state is %s", Long.valueOf(this.settings.getServerSelectionTimeout(TimeUnit.MILLISECONDS)), serverSelector, curDescription.getShortDescription()));
    }

    private MongoWaitQueueFullException createWaitQueueFullException() {
        return new MongoWaitQueueFullException(String.format("Too many operations are already waiting for a server. Max number of operations (maxWaitQueueSize) of %d has been exceeded.", Integer.valueOf(this.settings.getMaxWaitQueueSize())));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/BaseCluster$ServerSelectionRequest.class */
    private static final class ServerSelectionRequest {
        private final ServerSelector originalSelector;
        private final ServerSelector compositeSelector;
        private final long maxWaitTimeNanos;
        private final SingleResultCallback<Server> callback;
        private final long startTimeNanos = System.nanoTime();
        private CountDownLatch phase;

        ServerSelectionRequest(ServerSelector serverSelector, ServerSelector compositeSelector, long maxWaitTimeNanos, SingleResultCallback<Server> callback) {
            this.originalSelector = serverSelector;
            this.compositeSelector = compositeSelector;
            this.maxWaitTimeNanos = maxWaitTimeNanos;
            this.callback = callback;
        }

        void onResult(Server server, Throwable t) {
            try {
                this.callback.onResult(server, t);
            } catch (Throwable th) {
            }
        }

        boolean timedOut() {
            return System.nanoTime() - this.startTimeNanos > this.maxWaitTimeNanos;
        }

        long getRemainingTime() {
            return (this.startTimeNanos + this.maxWaitTimeNanos) - System.nanoTime();
        }
    }

    private synchronized void notifyWaitQueueHandler(ServerSelectionRequest request) {
        if (this.isClosed) {
            return;
        }
        if (this.waitQueueSize.incrementAndGet() > this.settings.getMaxWaitQueueSize()) {
            this.waitQueueSize.decrementAndGet();
            request.onResult(null, createWaitQueueFullException());
            return;
        }
        this.waitQueue.add(request);
        if (this.waitQueueHandler == null) {
            this.waitQueueHandler = new Thread(new WaitQueueHandler(), "cluster-" + this.clusterId.getValue());
            this.waitQueueHandler.setDaemon(true);
            this.waitQueueHandler.start();
        }
    }

    private synchronized void stopWaitQueueHandler() {
        if (this.waitQueueHandler != null) {
            this.waitQueueHandler.interrupt();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/BaseCluster$WaitQueueHandler.class */
    private final class WaitQueueHandler implements Runnable {
        private WaitQueueHandler() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (!BaseCluster.this.isClosed) {
                CountDownLatch currentPhase = (CountDownLatch) BaseCluster.this.phase.get();
                ClusterDescription curDescription = BaseCluster.this.description;
                long waitTimeNanos = Long.MAX_VALUE;
                Iterator<ServerSelectionRequest> iter = BaseCluster.this.waitQueue.iterator();
                while (iter.hasNext()) {
                    ServerSelectionRequest nextRequest = iter.next();
                    if (!BaseCluster.this.handleServerSelectionRequest(nextRequest, currentPhase, curDescription)) {
                        waitTimeNanos = Math.min(nextRequest.getRemainingTime(), Math.min(BaseCluster.this.getMinWaitTimeNanos(), waitTimeNanos));
                    } else {
                        iter.remove();
                        BaseCluster.this.waitQueueSize.decrementAndGet();
                    }
                }
                if (waitTimeNanos < Long.MAX_VALUE) {
                    BaseCluster.this.connect();
                }
                try {
                    currentPhase.await(waitTimeNanos, TimeUnit.NANOSECONDS);
                } catch (InterruptedException e) {
                }
            }
            Iterator<ServerSelectionRequest> iter2 = BaseCluster.this.waitQueue.iterator();
            while (iter2.hasNext()) {
                iter2.next().onResult(null, new MongoClientException("Shutdown in progress"));
                iter2.remove();
            }
        }
    }
}
