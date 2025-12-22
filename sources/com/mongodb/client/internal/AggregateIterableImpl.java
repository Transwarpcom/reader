package com.mongodb.client.internal;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.FindOptions;
import com.mongodb.internal.operation.SyncOperations;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ReadOperation;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/AggregateIterableImpl.class */
class AggregateIterableImpl<TDocument, TResult> extends MongoIterableImpl<TResult> implements AggregateIterable<TResult> {
    private final SyncOperations<TDocument> operations;
    private final MongoNamespace namespace;
    private final Class<TDocument> documentClass;
    private final Class<TResult> resultClass;
    private final CodecRegistry codecRegistry;
    private final List<? extends Bson> pipeline;
    private Boolean allowDiskUse;
    private long maxTimeMS;
    private long maxAwaitTimeMS;
    private Boolean useCursor;
    private Boolean bypassDocumentValidation;
    private Collation collation;
    private String comment;
    private Bson hint;

    AggregateIterableImpl(@Nullable ClientSession clientSession, MongoNamespace namespace, Class<TDocument> documentClass, Class<TResult> resultClass, CodecRegistry codecRegistry, ReadPreference readPreference, ReadConcern readConcern, WriteConcern writeConcern, OperationExecutor executor, List<? extends Bson> pipeline) {
        super(clientSession, executor, readConcern, readPreference);
        this.operations = new SyncOperations<>(namespace, documentClass, readPreference, codecRegistry, writeConcern, false);
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.documentClass = (Class) Assertions.notNull("documentClass", documentClass);
        this.resultClass = (Class) Assertions.notNull("resultClass", resultClass);
        this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
        this.pipeline = (List) Assertions.notNull("pipeline", pipeline);
    }

    @Override // com.mongodb.client.AggregateIterable
    public void toCollection() {
        if (getOutCollection() == null) {
            throw new IllegalStateException("The last stage of the aggregation pipeline must be $out");
        }
        getExecutor().execute(this.operations.aggregateToCollection(this.pipeline, this.maxTimeMS, this.allowDiskUse, this.bypassDocumentValidation, this.collation, this.hint, this.comment), getReadConcern(), getClientSession());
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> allowDiskUse(@Nullable Boolean allowDiskUse) {
        this.allowDiskUse = allowDiskUse;
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public AggregateIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    @Deprecated
    public AggregateIterable<TResult> useCursor(@Nullable Boolean useCursor) {
        this.useCursor = useCursor;
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxAwaitTimeMS = TimeUnit.MILLISECONDS.convert(maxAwaitTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> bypassDocumentValidation(@Nullable Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> collation(@Nullable Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> comment(@Nullable String comment) {
        this.comment = comment;
        return this;
    }

    @Override // com.mongodb.client.AggregateIterable
    public AggregateIterable<TResult> hint(@Nullable Bson hint) {
        this.hint = hint;
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<TResult>> asReadOperation() {
        BsonValue outCollection = getOutCollection();
        if (outCollection != null) {
            getExecutor().execute(this.operations.aggregateToCollection(this.pipeline, this.maxTimeMS, this.allowDiskUse, this.bypassDocumentValidation, this.collation, this.hint, this.comment), getReadConcern(), getClientSession());
            FindOptions findOptions = new FindOptions().collation(this.collation);
            Integer batchSize = getBatchSize();
            if (batchSize != null) {
                findOptions.batchSize(batchSize.intValue());
            }
            return this.operations.find(new MongoNamespace(this.namespace.getDatabaseName(), outCollection.asString().getValue()), new BsonDocument(), this.resultClass, findOptions);
        }
        return this.operations.aggregate(this.pipeline, this.resultClass, this.maxTimeMS, this.maxAwaitTimeMS, getBatchSize(), this.collation, this.hint, this.comment, this.allowDiskUse, this.useCursor);
    }

    @Nullable
    private BsonValue getOutCollection() {
        if (this.pipeline.size() == 0) {
            return null;
        }
        Bson lastStage = (Bson) Assertions.notNull("last stage", this.pipeline.get(this.pipeline.size() - 1));
        return lastStage.toBsonDocument(this.documentClass, this.codecRegistry).get((Object) "$out");
    }
}
