package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ConnectionDescription;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalConnectionInitializer.class */
interface InternalConnectionInitializer {
    ConnectionDescription initialize(InternalConnection internalConnection);

    void initializeAsync(InternalConnection internalConnection, SingleResultCallback<ConnectionDescription> singleResultCallback);
}
