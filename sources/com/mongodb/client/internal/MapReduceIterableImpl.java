package com.mongodb.client.internal;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.binding.ReadBinding;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.FindOptions;
import com.mongodb.client.model.MapReduceAction;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.MapReduceBatchCursor;
import com.mongodb.operation.MapReduceStatistics;
import com.mongodb.operation.ReadOperation;
import com.mongodb.operation.WriteOperation;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MapReduceIterableImpl.class */
class MapReduceIterableImpl<TDocument, TResult> extends MongoIterableImpl<TResult> implements MapReduceIterable<TResult> {
    private final SyncOperations<TDocument> operations;
    private final MongoNamespace namespace;
    private final Class<TResult> resultClass;
    private final String mapFunction;
    private final String reduceFunction;
    private boolean inline;
    private String collectionName;
    private String finalizeFunction;
    private Bson scope;
    private Bson filter;
    private Bson sort;
    private int limit;
    private boolean jsMode;
    private boolean verbose;
    private long maxTimeMS;
    private MapReduceAction action;
    private String databaseName;
    private boolean sharded;
    private boolean nonAtomic;
    private Boolean bypassDocumentValidation;
    private Collation collation;

    MapReduceIterableImpl(@Nullable ClientSession clientSession, MongoNamespace namespace, Class<TDocument> documentClass, Class<TResult> resultClass, CodecRegistry codecRegistry, ReadPreference readPreference, ReadConcern readConcern, WriteConcern writeConcern, OperationExecutor executor, String mapFunction, String reduceFunction) {
        super(clientSession, executor, readConcern, readPreference);
        this.inline = true;
        this.verbose = true;
        this.action = MapReduceAction.REPLACE;
        this.operations = new SyncOperations<>(namespace, documentClass, readPreference, codecRegistry, writeConcern, false);
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.resultClass = (Class) Assertions.notNull("resultClass", resultClass);
        this.mapFunction = (String) Assertions.notNull("mapFunction", mapFunction);
        this.reduceFunction = (String) Assertions.notNull("reduceFunction", reduceFunction);
    }

    @Override // com.mongodb.client.MapReduceIterable
    public void toCollection() {
        if (this.inline) {
            throw new IllegalStateException("The options must specify a non-inline result");
        }
        getExecutor().execute(createMapReduceToCollectionOperation(), getReadConcern(), getClientSession());
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> collectionName(String collectionName) {
        this.collectionName = (String) Assertions.notNull("collectionName", collectionName);
        this.inline = false;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> finalizeFunction(@Nullable String finalizeFunction) {
        this.finalizeFunction = finalizeFunction;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> scope(@Nullable Bson scope) {
        this.scope = scope;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> sort(@Nullable Bson sort) {
        this.sort = sort;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> filter(@Nullable Bson filter) {
        this.filter = filter;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> jsMode(boolean jsMode) {
        this.jsMode = jsMode;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> action(MapReduceAction action) {
        this.action = action;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> databaseName(@Nullable String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> sharded(boolean sharded) {
        this.sharded = sharded;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> nonAtomic(boolean nonAtomic) {
        this.nonAtomic = nonAtomic;
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public MapReduceIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> bypassDocumentValidation(@Nullable Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    @Override // com.mongodb.client.MapReduceIterable
    public MapReduceIterable<TResult> collation(@Nullable Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    ReadPreference getReadPreference() {
        if (this.inline) {
            return super.getReadPreference();
        }
        return ReadPreference.primary();
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<TResult>> asReadOperation() {
        if (this.inline) {
            ReadOperation<MapReduceBatchCursor<TResult>> operation = this.operations.mapReduce(this.mapFunction, this.reduceFunction, this.finalizeFunction, this.resultClass, this.filter, this.limit, this.maxTimeMS, this.jsMode, this.scope, this.sort, this.verbose, this.collation);
            return new WrappedMapReduceReadOperation(operation);
        }
        getExecutor().execute(createMapReduceToCollectionOperation(), getReadConcern(), getClientSession());
        String dbName = this.databaseName != null ? this.databaseName : this.namespace.getDatabaseName();
        FindOptions findOptions = new FindOptions().collation(this.collation);
        Integer batchSize = getBatchSize();
        if (batchSize != null) {
            findOptions.batchSize(batchSize.intValue());
        }
        return this.operations.find(new MongoNamespace(dbName, this.collectionName), new BsonDocument(), this.resultClass, findOptions);
    }

    private WriteOperation<MapReduceStatistics> createMapReduceToCollectionOperation() {
        return this.operations.mapReduceToCollection(this.databaseName, this.collectionName, this.mapFunction, this.reduceFunction, this.finalizeFunction, this.filter, this.limit, this.maxTimeMS, this.jsMode, this.scope, this.sort, this.verbose, this.action, this.nonAtomic, this.sharded, this.bypassDocumentValidation, this.collation);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/MapReduceIterableImpl$WrappedMapReduceReadOperation.class */
    static class WrappedMapReduceReadOperation<TResult> implements ReadOperation<BatchCursor<TResult>> {
        private final ReadOperation<MapReduceBatchCursor<TResult>> operation;

        ReadOperation<MapReduceBatchCursor<TResult>> getOperation() {
            return this.operation;
        }

        WrappedMapReduceReadOperation(ReadOperation<MapReduceBatchCursor<TResult>> operation) {
            this.operation = operation;
        }

        @Override // com.mongodb.operation.ReadOperation
        public BatchCursor<TResult> execute(ReadBinding binding) {
            return this.operation.execute(binding);
        }
    }
}
