package com.mongodb.client.internal;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ReadOperation;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ListDatabasesIterableImpl.class */
public final class ListDatabasesIterableImpl<TResult> extends MongoIterableImpl<TResult> implements ListDatabasesIterable<TResult> {
    private final SyncOperations<BsonDocument> operations;
    private final Class<TResult> resultClass;
    private long maxTimeMS;
    private Bson filter;
    private Boolean nameOnly;

    public ListDatabasesIterableImpl(@Nullable ClientSession clientSession, Class<TResult> resultClass, CodecRegistry codecRegistry, ReadPreference readPreference, OperationExecutor executor) {
        super(clientSession, executor, ReadConcern.DEFAULT, readPreference);
        this.operations = new SyncOperations<>(BsonDocument.class, readPreference, codecRegistry);
        this.resultClass = (Class) Assertions.notNull("clazz", resultClass);
    }

    @Override // com.mongodb.client.ListDatabasesIterable
    public ListDatabasesIterableImpl<TResult> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public ListDatabasesIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.ListDatabasesIterable
    public ListDatabasesIterable<TResult> filter(@Nullable Bson filter) {
        this.filter = filter;
        return this;
    }

    @Override // com.mongodb.client.ListDatabasesIterable
    public ListDatabasesIterable<TResult> nameOnly(@Nullable Boolean nameOnly) {
        this.nameOnly = nameOnly;
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<TResult>> asReadOperation() {
        return this.operations.listDatabases(this.resultClass, this.filter, this.nameOnly, this.maxTimeMS);
    }
}
