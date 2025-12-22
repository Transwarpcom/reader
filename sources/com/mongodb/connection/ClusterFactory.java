package com.mongodb.connection;

import com.mongodb.MongoCredential;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.ConnectionListener;
import com.mongodb.event.ConnectionPoolListener;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ClusterFactory.class */
public interface ClusterFactory {
    Cluster create(ClusterSettings clusterSettings, ServerSettings serverSettings, ConnectionPoolSettings connectionPoolSettings, StreamFactory streamFactory, StreamFactory streamFactory2, List<MongoCredential> list, ClusterListener clusterListener, ConnectionPoolListener connectionPoolListener, ConnectionListener connectionListener);
}
