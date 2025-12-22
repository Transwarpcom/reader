package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterId;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ClusterType;
import com.mongodb.connection.Server;
import com.mongodb.connection.ServerDescription;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.ClusterDescriptionChangedEvent;
import com.mongodb.event.ServerDescriptionChangedEvent;
import com.mongodb.event.ServerListenerAdapter;
import com.mongodb.selector.ServerSelector;
import java.util.Collections;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SingleServerCluster.class */
public final class SingleServerCluster extends BaseCluster {
    private static final Logger LOGGER = Loggers.getLogger("cluster");
    private final ClusterableServer server;

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ ClusterDescription getCurrentDescription() {
        return super.getCurrentDescription();
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ boolean isClosed() {
        return super.isClosed();
    }

    @Override // com.mongodb.internal.connection.BaseCluster
    public /* bridge */ /* synthetic */ ClusterableServerFactory getServerFactory() {
        return super.getServerFactory();
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ ClusterSettings getSettings() {
        return super.getSettings();
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ ClusterDescription getDescription() {
        return super.getDescription();
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ void selectServerAsync(ServerSelector serverSelector, SingleResultCallback singleResultCallback) {
        super.selectServerAsync(serverSelector, singleResultCallback);
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ Server selectServer(ServerSelector serverSelector) {
        return super.selectServer(serverSelector);
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster
    public /* bridge */ /* synthetic */ BsonTimestamp getClusterTime() {
        return super.getClusterTime();
    }

    public SingleServerCluster(ClusterId clusterId, ClusterSettings settings, ClusterableServerFactory serverFactory) {
        super(clusterId, settings, serverFactory);
        Assertions.isTrue("one server in a direct cluster", settings.getHosts().size() == 1);
        Assertions.isTrue("connection mode is single", settings.getMode() == ClusterConnectionMode.SINGLE);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(String.format("Cluster created with settings %s", settings.getShortDescription()));
        }
        synchronized (this) {
            this.server = createServer(settings.getHosts().get(0), new DefaultServerStateListener());
            publishDescription(this.server.getDescription());
        }
    }

    @Override // com.mongodb.internal.connection.BaseCluster
    protected void connect() {
        this.server.connect();
    }

    @Override // com.mongodb.internal.connection.BaseCluster
    protected ClusterableServer getServer(ServerAddress serverAddress) {
        Assertions.isTrue("open", !isClosed());
        return this.server;
    }

    @Override // com.mongodb.internal.connection.BaseCluster, com.mongodb.connection.Cluster, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (!isClosed()) {
            this.server.close();
            super.close();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SingleServerCluster$DefaultServerStateListener.class */
    private class DefaultServerStateListener extends ServerListenerAdapter {
        private DefaultServerStateListener() {
        }

        @Override // com.mongodb.event.ServerListenerAdapter, com.mongodb.event.ServerListener
        public void serverDescriptionChanged(ServerDescriptionChangedEvent event) {
            ServerDescription descriptionToPublish = event.getNewDescription();
            if (event.getNewDescription().isOk()) {
                if (SingleServerCluster.this.getSettings().getRequiredClusterType() != ClusterType.UNKNOWN && SingleServerCluster.this.getSettings().getRequiredClusterType() != event.getNewDescription().getClusterType()) {
                    descriptionToPublish = null;
                } else if (SingleServerCluster.this.getSettings().getRequiredClusterType() == ClusterType.REPLICA_SET && SingleServerCluster.this.getSettings().getRequiredReplicaSetName() != null && !SingleServerCluster.this.getSettings().getRequiredReplicaSetName().equals(event.getNewDescription().getSetName())) {
                    descriptionToPublish = null;
                }
            }
            SingleServerCluster.this.publishDescription(descriptionToPublish);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishDescription(ServerDescription serverDescription) {
        ClusterType clusterType = getSettings().getRequiredClusterType();
        if (clusterType == ClusterType.UNKNOWN && serverDescription != null) {
            clusterType = serverDescription.getClusterType();
        }
        ClusterDescription oldDescription = getCurrentDescription();
        ClusterDescription description = new ClusterDescription(ClusterConnectionMode.SINGLE, clusterType, serverDescription == null ? Collections.emptyList() : Collections.singletonList(serverDescription), getSettings(), getServerFactory().getSettings());
        updateDescription(description);
        fireChangeEvent(new ClusterDescriptionChangedEvent(getClusterId(), description, oldDescription == null ? getInitialDescription() : oldDescription));
    }

    private ClusterDescription getInitialDescription() {
        return new ClusterDescription(getSettings().getMode(), getSettings().getRequiredClusterType(), Collections.emptyList(), getSettings(), getServerFactory().getSettings());
    }
}
