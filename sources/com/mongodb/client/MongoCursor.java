package com.mongodb.client;

import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.lang.Nullable;
import java.io.Closeable;
import java.util.Iterator;

@NotThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/MongoCursor.class */
public interface MongoCursor<TResult> extends Iterator<TResult>, Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    @Override // java.util.Iterator
    boolean hasNext();

    @Override // java.util.Iterator
    TResult next();

    @Nullable
    TResult tryNext();

    @Nullable
    ServerCursor getServerCursor();

    ServerAddress getServerAddress();
}
