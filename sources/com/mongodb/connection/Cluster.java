package com.mongodb.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.lang.Nullable;
import com.mongodb.selector.ServerSelector;
import java.io.Closeable;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/Cluster.class */
public interface Cluster extends Closeable {
    ClusterSettings getSettings();

    ClusterDescription getDescription();

    ClusterDescription getCurrentDescription();

    @Nullable
    BsonTimestamp getClusterTime();

    Server selectServer(ServerSelector serverSelector);

    void selectServerAsync(ServerSelector serverSelector, SingleResultCallback<Server> singleResultCallback);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    boolean isClosed();
}
