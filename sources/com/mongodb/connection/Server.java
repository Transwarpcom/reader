package com.mongodb.connection;

import com.mongodb.annotations.ThreadSafe;
import com.mongodb.async.SingleResultCallback;

@ThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/Server.class */
public interface Server {
    ServerDescription getDescription();

    Connection getConnection();

    void getConnectionAsync(SingleResultCallback<AsyncConnection> singleResultCallback);
}
