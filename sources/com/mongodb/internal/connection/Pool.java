package com.mongodb.internal.connection;

import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/Pool.class */
interface Pool<T> {
    T get();

    T get(long j, TimeUnit timeUnit);

    void release(T t);

    void close();

    void release(T t, boolean z);
}
