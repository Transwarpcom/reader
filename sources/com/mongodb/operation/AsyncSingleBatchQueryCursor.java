package com.mongodb.operation;

import com.mongodb.MongoException;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.QueryResult;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncSingleBatchQueryCursor.class */
class AsyncSingleBatchQueryCursor<T> implements AsyncBatchCursor<T> {
    private volatile QueryResult<T> firstBatch;
    private volatile boolean closed;

    AsyncSingleBatchQueryCursor(QueryResult<T> firstBatch) {
        this.firstBatch = firstBatch;
        Assertions.isTrue("Empty Cursor", firstBatch.getCursor() == null);
    }

    @Override // com.mongodb.async.AsyncBatchCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.closed = true;
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void next(SingleResultCallback<List<T>> callback) {
        if (this.closed) {
            callback.onResult(null, new MongoException("next() called after the cursor was closed."));
            return;
        }
        if (this.firstBatch != null && !this.firstBatch.getResults().isEmpty()) {
            List<T> results = this.firstBatch.getResults();
            this.firstBatch = null;
            callback.onResult(results, null);
        } else {
            this.closed = true;
            callback.onResult(null, null);
        }
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void tryNext(SingleResultCallback<List<T>> callback) {
        next(callback);
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void setBatchSize(int batchSize) {
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public int getBatchSize() {
        return 0;
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public boolean isClosed() {
        return this.closed;
    }
}
