package com.mongodb.client.internal;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.model.Collation;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ReadOperation;
import java.util.concurrent.TimeUnit;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/DistinctIterableImpl.class */
class DistinctIterableImpl<TDocument, TResult> extends MongoIterableImpl<TResult> implements DistinctIterable<TResult> {
    private final SyncOperations<TDocument> operations;
    private final Class<TResult> resultClass;
    private final String fieldName;
    private Bson filter;
    private long maxTimeMS;
    private Collation collation;

    DistinctIterableImpl(@Nullable ClientSession clientSession, MongoNamespace namespace, Class<TDocument> documentClass, Class<TResult> resultClass, CodecRegistry codecRegistry, ReadPreference readPreference, ReadConcern readConcern, OperationExecutor executor, String fieldName, Bson filter) {
        super(clientSession, executor, readConcern, readPreference);
        this.operations = new SyncOperations<>(namespace, documentClass, readPreference, codecRegistry);
        this.resultClass = (Class) Assertions.notNull("resultClass", resultClass);
        this.fieldName = (String) Assertions.notNull("mapFunction", fieldName);
        this.filter = filter;
    }

    @Override // com.mongodb.client.DistinctIterable
    public DistinctIterable<TResult> filter(@Nullable Bson filter) {
        this.filter = filter;
        return this;
    }

    @Override // com.mongodb.client.DistinctIterable
    public DistinctIterable<TResult> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public DistinctIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.DistinctIterable
    public DistinctIterable<TResult> collation(@Nullable Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<TResult>> asReadOperation() {
        return this.operations.distinct(this.fieldName, this.filter, this.resultClass, this.maxTimeMS, this.collation);
    }
}
