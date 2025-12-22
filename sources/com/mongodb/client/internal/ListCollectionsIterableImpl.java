package com.mongodb.client.internal;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ReadOperation;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ListCollectionsIterableImpl.class */
final class ListCollectionsIterableImpl<TResult> extends MongoIterableImpl<TResult> implements ListCollectionsIterable<TResult> {
    private final SyncOperations<BsonDocument> operations;
    private final String databaseName;
    private final Class<TResult> resultClass;
    private Bson filter;
    private final boolean collectionNamesOnly;
    private long maxTimeMS;

    ListCollectionsIterableImpl(@Nullable ClientSession clientSession, String databaseName, boolean collectionNamesOnly, Class<TResult> resultClass, CodecRegistry codecRegistry, ReadPreference readPreference, OperationExecutor executor) {
        super(clientSession, executor, ReadConcern.DEFAULT, readPreference);
        this.collectionNamesOnly = collectionNamesOnly;
        this.operations = new SyncOperations<>(BsonDocument.class, readPreference, codecRegistry);
        this.databaseName = (String) Assertions.notNull("databaseName", databaseName);
        this.resultClass = (Class) Assertions.notNull("resultClass", resultClass);
    }

    @Override // com.mongodb.client.ListCollectionsIterable
    public ListCollectionsIterable<TResult> filter(@Nullable Bson filter) {
        Assertions.notNull("filter", filter);
        this.filter = filter;
        return this;
    }

    @Override // com.mongodb.client.ListCollectionsIterable
    public ListCollectionsIterable<TResult> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public ListCollectionsIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<TResult>> asReadOperation() {
        return this.operations.listCollections(this.databaseName, this.resultClass, this.filter, this.collectionNamesOnly, getBatchSize(), this.maxTimeMS);
    }
}
