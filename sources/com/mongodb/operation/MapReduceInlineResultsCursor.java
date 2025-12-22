package com.mongodb.operation;

import com.mongodb.binding.ConnectionSource;
import com.mongodb.connection.QueryResult;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MapReduceInlineResultsCursor.class */
class MapReduceInlineResultsCursor<T> extends QueryBatchCursor<T> implements MapReduceBatchCursor<T> {
    private final MapReduceStatistics statistics;

    MapReduceInlineResultsCursor(QueryResult<T> queryResult, Decoder<T> decoder, ConnectionSource connectionSource, MapReduceStatistics statistics) {
        super(queryResult, 0, 0, decoder, connectionSource);
        this.statistics = statistics;
    }

    @Override // com.mongodb.operation.MapReduceBatchCursor
    public MapReduceStatistics getStatistics() {
        return this.statistics;
    }
}
