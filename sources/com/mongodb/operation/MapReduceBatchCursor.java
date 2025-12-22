package com.mongodb.operation;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MapReduceBatchCursor.class */
public interface MapReduceBatchCursor<T> extends BatchCursor<T> {
    MapReduceStatistics getStatistics();
}
