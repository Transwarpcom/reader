package com.mongodb.operation;

import com.mongodb.async.AsyncBatchCursor;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MapReduceAsyncBatchCursor.class */
public interface MapReduceAsyncBatchCursor<T> extends AsyncBatchCursor<T> {
    MapReduceStatistics getStatistics();
}
