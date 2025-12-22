package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ConnectionPool.class */
interface ConnectionPool extends Closeable {
    InternalConnection get();

    InternalConnection get(long j, TimeUnit timeUnit);

    void getAsync(SingleResultCallback<InternalConnection> singleResultCallback);

    void invalidate();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
