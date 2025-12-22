package com.mongodb.client.internal;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.ChangeStreamLevel;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.ChangeStreamOperation;
import com.mongodb.operation.ReadOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.BsonTimestamp;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/ChangeStreamIterableImpl.class */
public final class ChangeStreamIterableImpl<TResult> extends MongoIterableImpl<ChangeStreamDocument<TResult>> implements ChangeStreamIterable<TResult> {
    private final MongoNamespace namespace;
    private final CodecRegistry codecRegistry;
    private final List<? extends Bson> pipeline;
    private final Codec<ChangeStreamDocument<TResult>> codec;
    private final ChangeStreamLevel changeStreamLevel;
    private FullDocument fullDocument;
    private BsonDocument resumeToken;
    private long maxAwaitTimeMS;
    private Collation collation;
    private BsonTimestamp startAtOperationTime;

    public ChangeStreamIterableImpl(@Nullable ClientSession clientSession, String databaseName, CodecRegistry codecRegistry, ReadPreference readPreference, ReadConcern readConcern, OperationExecutor executor, List<? extends Bson> pipeline, Class<TResult> resultClass, ChangeStreamLevel changeStreamLevel) {
        this(clientSession, new MongoNamespace(databaseName, "ignored"), codecRegistry, readPreference, readConcern, executor, pipeline, resultClass, changeStreamLevel);
    }

    public ChangeStreamIterableImpl(@Nullable ClientSession clientSession, MongoNamespace namespace, CodecRegistry codecRegistry, ReadPreference readPreference, ReadConcern readConcern, OperationExecutor executor, List<? extends Bson> pipeline, Class<TResult> resultClass, ChangeStreamLevel changeStreamLevel) {
        super(clientSession, executor, readConcern, readPreference);
        this.fullDocument = FullDocument.DEFAULT;
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
        this.pipeline = (List) Assertions.notNull("pipeline", pipeline);
        this.codec = ChangeStreamDocument.createCodec((Class) Assertions.notNull("resultClass", resultClass), codecRegistry);
        this.changeStreamLevel = (ChangeStreamLevel) Assertions.notNull("changeStreamLevel", changeStreamLevel);
    }

    @Override // com.mongodb.client.ChangeStreamIterable
    public ChangeStreamIterable<TResult> fullDocument(FullDocument fullDocument) {
        this.fullDocument = (FullDocument) Assertions.notNull("fullDocument", fullDocument);
        return this;
    }

    @Override // com.mongodb.client.ChangeStreamIterable
    public ChangeStreamIterable<TResult> resumeAfter(BsonDocument resumeAfter) {
        this.resumeToken = (BsonDocument) Assertions.notNull("resumeAfter", resumeAfter);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl, com.mongodb.client.MongoIterable
    /* renamed from: batchSize */
    public ChangeStreamIterable<TResult> batchSize2(int batchSize) {
        super.batchSize2(batchSize);
        return this;
    }

    @Override // com.mongodb.client.ChangeStreamIterable
    public ChangeStreamIterable<TResult> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxAwaitTimeMS = TimeUnit.MILLISECONDS.convert(maxAwaitTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.client.ChangeStreamIterable
    public ChangeStreamIterable<TResult> collation(@Nullable Collation collation) {
        this.collation = (Collation) Assertions.notNull("collation", collation);
        return this;
    }

    @Override // com.mongodb.client.ChangeStreamIterable
    public <TDocument> MongoIterable<TDocument> withDocumentClass(final Class<TDocument> clazz) {
        return new MongoIterableImpl<TDocument>(getClientSession(), getExecutor(), getReadConcern(), getReadPreference()) { // from class: com.mongodb.client.internal.ChangeStreamIterableImpl.1
            private ReadOperation<BatchCursor<TDocument>> operation;

            {
                this.operation = ChangeStreamIterableImpl.this.createChangeStreamOperation(ChangeStreamIterableImpl.this.codecRegistry.get(clazz));
            }

            @Override // com.mongodb.client.internal.MongoIterableImpl
            public ReadOperation<BatchCursor<TDocument>> asReadOperation() {
                return this.operation;
            }
        };
    }

    @Override // com.mongodb.client.ChangeStreamIterable
    public ChangeStreamIterable<TResult> startAtOperationTime(BsonTimestamp startAtOperationTime) {
        this.startAtOperationTime = (BsonTimestamp) Assertions.notNull("startAtOperationTime", startAtOperationTime);
        return this;
    }

    @Override // com.mongodb.client.internal.MongoIterableImpl
    public ReadOperation<BatchCursor<ChangeStreamDocument<TResult>>> asReadOperation() {
        return (ReadOperation<BatchCursor<ChangeStreamDocument<TResult>>>) createChangeStreamOperation(this.codec);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <S> ReadOperation<BatchCursor<S>> createChangeStreamOperation(Codec<S> codec) {
        return new ChangeStreamOperation(this.namespace, this.fullDocument, createBsonDocumentList(this.pipeline), codec, this.changeStreamLevel).batchSize(getBatchSize()).collation(this.collation).maxAwaitTime(this.maxAwaitTimeMS, TimeUnit.MILLISECONDS).resumeAfter(this.resumeToken).startAtOperationTime(this.startAtOperationTime);
    }

    private List<BsonDocument> createBsonDocumentList(List<? extends Bson> pipeline) {
        List<BsonDocument> aggregateList = new ArrayList<>(pipeline.size());
        for (Bson obj : pipeline) {
            if (obj == null) {
                throw new IllegalArgumentException("pipeline cannot contain a null value");
            }
            aggregateList.add(obj.toBsonDocument(BsonDocument.class, this.codecRegistry));
        }
        return aggregateList;
    }
}
