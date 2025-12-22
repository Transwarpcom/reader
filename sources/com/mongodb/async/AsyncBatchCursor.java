package com.mongodb.async;

import java.io.Closeable;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/async/AsyncBatchCursor.class */
public interface AsyncBatchCursor<T> extends Closeable {
    void next(SingleResultCallback<List<T>> singleResultCallback);

    void tryNext(SingleResultCallback<List<T>> singleResultCallback);

    void setBatchSize(int i);

    int getBatchSize();

    boolean isClosed();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
