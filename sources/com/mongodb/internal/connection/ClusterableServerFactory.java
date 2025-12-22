package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import com.mongodb.connection.ServerSettings;
import com.mongodb.event.ServerListener;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ClusterableServerFactory.class */
public interface ClusterableServerFactory {
    ClusterableServer create(ServerAddress serverAddress, ServerListener serverListener, ClusterClock clusterClock);

    ServerSettings getSettings();
}
