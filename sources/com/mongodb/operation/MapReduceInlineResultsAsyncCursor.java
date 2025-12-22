package com.mongodb.operation;

import com.mongodb.connection.QueryResult;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MapReduceInlineResultsAsyncCursor.class */
class MapReduceInlineResultsAsyncCursor<T> extends AsyncSingleBatchQueryCursor<T> implements MapReduceAsyncBatchCursor<T> {
    private final MapReduceStatistics statistics;

    MapReduceInlineResultsAsyncCursor(QueryResult<T> queryResult, MapReduceStatistics statistics) {
        super(queryResult);
        this.statistics = statistics;
    }

    @Override // com.mongodb.operation.MapReduceAsyncBatchCursor
    public MapReduceStatistics getStatistics() {
        return this.statistics;
    }
}
