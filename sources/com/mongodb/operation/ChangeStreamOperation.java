package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.client.model.changestream.ChangeStreamLevel;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.AggregateOperationImpl;
import com.mongodb.session.SessionContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.BsonTimestamp;
import org.bson.BsonValue;
import org.bson.RawBsonDocument;
import org.bson.codecs.Decoder;
import org.bson.codecs.RawBsonDocumentCodec;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ChangeStreamOperation.class */
public class ChangeStreamOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private static final RawBsonDocumentCodec RAW_BSON_DOCUMENT_CODEC = new RawBsonDocumentCodec();
    private final AggregateOperationImpl<RawBsonDocument> wrapped;
    private final FullDocument fullDocument;
    private final Decoder<T> decoder;
    private final ChangeStreamLevel changeStreamLevel;
    private BsonDocument resumeToken;
    private BsonTimestamp startAtOperationTime;
    private BsonTimestamp startAtOperationTimeForResume;

    public ChangeStreamOperation(MongoNamespace namespace, FullDocument fullDocument, List<BsonDocument> pipeline, Decoder<T> decoder) {
        this(namespace, fullDocument, pipeline, decoder, ChangeStreamLevel.COLLECTION);
    }

    public ChangeStreamOperation(MongoNamespace namespace, FullDocument fullDocument, List<BsonDocument> pipeline, Decoder<T> decoder, ChangeStreamLevel changeStreamLevel) {
        this.wrapped = new AggregateOperationImpl<>(namespace, pipeline, RAW_BSON_DOCUMENT_CODEC, getAggregateTarget(), getPipelineCreator());
        this.fullDocument = (FullDocument) Assertions.notNull("fullDocument", fullDocument);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
        this.changeStreamLevel = (ChangeStreamLevel) Assertions.notNull("changeStreamLevel", changeStreamLevel);
    }

    public MongoNamespace getNamespace() {
        return this.wrapped.getNamespace();
    }

    public Decoder<T> getDecoder() {
        return this.decoder;
    }

    public FullDocument getFullDocument() {
        return this.fullDocument;
    }

    public BsonDocument getResumeToken() {
        return this.resumeToken;
    }

    public ChangeStreamOperation<T> resumeAfter(BsonDocument resumeToken) {
        this.resumeToken = resumeToken;
        return this;
    }

    public List<BsonDocument> getPipeline() {
        return this.wrapped.getPipeline();
    }

    public Integer getBatchSize() {
        return this.wrapped.getBatchSize();
    }

    public ChangeStreamOperation<T> batchSize(Integer batchSize) {
        this.wrapped.batchSize(batchSize);
        return this;
    }

    public long getMaxAwaitTime(TimeUnit timeUnit) {
        return this.wrapped.getMaxAwaitTime(timeUnit);
    }

    public ChangeStreamOperation<T> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        this.wrapped.maxAwaitTime(maxAwaitTime, timeUnit);
        return this;
    }

    public Collation getCollation() {
        return this.wrapped.getCollation();
    }

    public ChangeStreamOperation<T> collation(Collation collation) {
        this.wrapped.collation(collation);
        return this;
    }

    public ChangeStreamOperation<T> startAtOperationTime(BsonTimestamp startAtOperationTime) {
        this.startAtOperationTime = startAtOperationTime;
        return this;
    }

    ChangeStreamOperation<T> startOperationTimeForResume(BsonTimestamp startAtOperationTime) {
        this.startAtOperationTimeForResume = startAtOperationTime;
        return this;
    }

    public BsonTimestamp getStartAtOperationTime() {
        return this.startAtOperationTime;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(ReadBinding binding) {
        return new ChangeStreamBatchCursor(this, this.wrapped.execute(binding), binding);
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        this.wrapped.executeAsync(binding, new SingleResultCallback<AsyncBatchCursor<RawBsonDocument>>() { // from class: com.mongodb.operation.ChangeStreamOperation.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(AsyncBatchCursor<RawBsonDocument> result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                } else {
                    callback.onResult(new AsyncChangeStreamBatchCursor(ChangeStreamOperation.this, result, binding), null);
                }
            }
        });
    }

    private AggregateOperationImpl.AggregateTarget getAggregateTarget() {
        return new AggregateOperationImpl.AggregateTarget() { // from class: com.mongodb.operation.ChangeStreamOperation.2
            @Override // com.mongodb.operation.AggregateOperationImpl.AggregateTarget
            public BsonValue create() {
                return ChangeStreamOperation.this.changeStreamLevel == ChangeStreamLevel.COLLECTION ? new BsonString(ChangeStreamOperation.this.getNamespace().getCollectionName()) : new BsonInt32(1);
            }
        };
    }

    private AggregateOperationImpl.PipelineCreator getPipelineCreator() {
        return new AggregateOperationImpl.PipelineCreator() { // from class: com.mongodb.operation.ChangeStreamOperation.3
            @Override // com.mongodb.operation.AggregateOperationImpl.PipelineCreator
            public BsonArray create(ConnectionDescription description, SessionContext sessionContext) {
                List<BsonDocument> changeStreamPipeline = new ArrayList<>();
                BsonDocument changeStream = new BsonDocument("fullDocument", new BsonString(ChangeStreamOperation.this.fullDocument.getValue()));
                if (ChangeStreamOperation.this.changeStreamLevel == ChangeStreamLevel.CLIENT) {
                    changeStream.append("allChangesForCluster", BsonBoolean.TRUE);
                }
                if (ChangeStreamOperation.this.resumeToken != null) {
                    changeStream.append("resumeAfter", ChangeStreamOperation.this.resumeToken);
                }
                if (ChangeStreamOperation.this.startAtOperationTime == null) {
                    if (ChangeStreamOperation.this.resumeToken == null && ChangeStreamOperation.this.startAtOperationTimeForResume != null && ServerVersionHelper.serverIsAtLeastVersionFourDotZero(description)) {
                        changeStream.append("startAtOperationTime", ChangeStreamOperation.this.startAtOperationTimeForResume);
                    }
                } else {
                    changeStream.append("startAtOperationTime", ChangeStreamOperation.this.startAtOperationTime);
                }
                changeStreamPipeline.add(new BsonDocument("$changeStream", changeStream));
                changeStreamPipeline.addAll(ChangeStreamOperation.this.getPipeline());
                return new BsonArray(changeStreamPipeline);
            }
        };
    }
}
