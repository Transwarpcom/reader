package com.mongodb.connection;

import com.mongodb.MongoCompressor;
import com.mongodb.MongoCredential;
import com.mongodb.MongoDriverInformation;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.CommandListener;
import com.mongodb.event.ConnectionListener;
import com.mongodb.event.ConnectionPoolListener;
import com.mongodb.internal.connection.ClusterableServerFactory;
import com.mongodb.internal.connection.DefaultClusterableServerFactory;
import com.mongodb.internal.connection.MultiServerCluster;
import com.mongodb.internal.connection.SingleServerCluster;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/DefaultClusterFactory.class */
public final class DefaultClusterFactory implements ClusterFactory {
    @Override // com.mongodb.connection.ClusterFactory
    public Cluster create(ClusterSettings settings, ServerSettings serverSettings, ConnectionPoolSettings connectionPoolSettings, StreamFactory streamFactory, StreamFactory heartbeatStreamFactory, List<MongoCredential> credentialList, ClusterListener clusterListener, ConnectionPoolListener connectionPoolListener, ConnectionListener connectionListener) {
        return createCluster(getClusterSettings(settings, clusterListener), serverSettings, getConnectionPoolSettings(connectionPoolSettings, connectionPoolListener), streamFactory, heartbeatStreamFactory, credentialList, null, null, null, Collections.emptyList());
    }

    @Deprecated
    public Cluster create(ClusterSettings settings, ServerSettings serverSettings, ConnectionPoolSettings connectionPoolSettings, StreamFactory streamFactory, StreamFactory heartbeatStreamFactory, List<MongoCredential> credentialList, ClusterListener clusterListener, ConnectionPoolListener connectionPoolListener, ConnectionListener connectionListener, CommandListener commandListener) {
        return createCluster(getClusterSettings(settings, clusterListener), serverSettings, getConnectionPoolSettings(connectionPoolSettings, connectionPoolListener), streamFactory, heartbeatStreamFactory, credentialList, commandListener, null, null, Collections.emptyList());
    }

    @Deprecated
    public Cluster create(ClusterSettings settings, ServerSettings serverSettings, ConnectionPoolSettings connectionPoolSettings, StreamFactory streamFactory, StreamFactory heartbeatStreamFactory, List<MongoCredential> credentialList, ClusterListener clusterListener, ConnectionPoolListener connectionPoolListener, ConnectionListener connectionListener, CommandListener commandListener, String applicationName, MongoDriverInformation mongoDriverInformation) {
        return createCluster(getClusterSettings(settings, clusterListener), serverSettings, getConnectionPoolSettings(connectionPoolSettings, connectionPoolListener), streamFactory, heartbeatStreamFactory, credentialList, commandListener, applicationName, mongoDriverInformation, Collections.emptyList());
    }

    @Deprecated
    public Cluster createCluster(ClusterSettings clusterSettings, ServerSettings serverSettings, ConnectionPoolSettings connectionPoolSettings, StreamFactory streamFactory, StreamFactory heartbeatStreamFactory, List<MongoCredential> credentialList, CommandListener commandListener, String applicationName, MongoDriverInformation mongoDriverInformation) {
        return createCluster(clusterSettings, serverSettings, connectionPoolSettings, streamFactory, heartbeatStreamFactory, credentialList, commandListener, applicationName, mongoDriverInformation, Collections.emptyList());
    }

    public Cluster createCluster(ClusterSettings clusterSettings, ServerSettings serverSettings, ConnectionPoolSettings connectionPoolSettings, StreamFactory streamFactory, StreamFactory heartbeatStreamFactory, List<MongoCredential> credentialList, CommandListener commandListener, String applicationName, MongoDriverInformation mongoDriverInformation, List<MongoCompressor> compressorList) {
        ClusterId clusterId = new ClusterId(clusterSettings.getDescription());
        ClusterableServerFactory serverFactory = new DefaultClusterableServerFactory(clusterId, clusterSettings, serverSettings, connectionPoolSettings, streamFactory, heartbeatStreamFactory, credentialList, commandListener, applicationName, mongoDriverInformation != null ? mongoDriverInformation : MongoDriverInformation.builder().build(), compressorList);
        if (clusterSettings.getMode() == ClusterConnectionMode.SINGLE) {
            return new SingleServerCluster(clusterId, clusterSettings, serverFactory);
        }
        if (clusterSettings.getMode() == ClusterConnectionMode.MULTIPLE) {
            return new MultiServerCluster(clusterId, clusterSettings, serverFactory);
        }
        throw new UnsupportedOperationException("Unsupported cluster mode: " + clusterSettings.getMode());
    }

    private ClusterSettings getClusterSettings(ClusterSettings settings, ClusterListener clusterListener) {
        return ClusterSettings.builder(settings).addClusterListener(clusterListener).build();
    }

    private ConnectionPoolSettings getConnectionPoolSettings(ConnectionPoolSettings connPoolSettings, ConnectionPoolListener connPoolListener) {
        return ConnectionPoolSettings.builder(connPoolSettings).addConnectionPoolListener(connPoolListener).build();
    }
}
