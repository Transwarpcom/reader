package com.mongodb.operation;

import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoNamespace;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import com.mongodb.client.model.Collation;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AggregateOperation.class */
public class AggregateOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private final AggregateOperationImpl<T> wrapped;

    public AggregateOperation(MongoNamespace namespace, List<BsonDocument> pipeline, Decoder<T> decoder) {
        this.wrapped = new AggregateOperationImpl<>(namespace, pipeline, decoder);
    }

    public List<BsonDocument> getPipeline() {
        return this.wrapped.getPipeline();
    }

    public Boolean getAllowDiskUse() {
        return this.wrapped.getAllowDiskUse();
    }

    public AggregateOperation<T> allowDiskUse(Boolean allowDiskUse) {
        this.wrapped.allowDiskUse(allowDiskUse);
        return this;
    }

    public Integer getBatchSize() {
        return this.wrapped.getBatchSize();
    }

    public AggregateOperation<T> batchSize(Integer batchSize) {
        this.wrapped.batchSize(batchSize);
        return this;
    }

    public long getMaxAwaitTime(TimeUnit timeUnit) {
        return this.wrapped.getMaxAwaitTime(timeUnit);
    }

    public AggregateOperation<T> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        this.wrapped.maxAwaitTime(maxAwaitTime, timeUnit);
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        return this.wrapped.getMaxTime(timeUnit);
    }

    public AggregateOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        this.wrapped.maxTime(maxTime, timeUnit);
        return this;
    }

    @Deprecated
    public Boolean getUseCursor() {
        return this.wrapped.getUseCursor();
    }

    @Deprecated
    public AggregateOperation<T> useCursor(Boolean useCursor) {
        this.wrapped.useCursor(useCursor);
        return this;
    }

    public Collation getCollation() {
        return this.wrapped.getCollation();
    }

    public AggregateOperation<T> collation(Collation collation) {
        this.wrapped.collation(collation);
        return this;
    }

    public String getComment() {
        return this.wrapped.getComment();
    }

    public AggregateOperation<T> comment(String comment) {
        this.wrapped.comment(comment);
        return this;
    }

    public BsonDocument getHint() {
        BsonValue hint = this.wrapped.getHint();
        if (hint == null) {
            return null;
        }
        if (!hint.isDocument()) {
            throw new IllegalArgumentException("Hint is not a BsonDocument please use the #getHintBsonValue() method. ");
        }
        return hint.asDocument();
    }

    public BsonValue getHintBsonValue() {
        return this.wrapped.getHint();
    }

    public AggregateOperation<T> hint(BsonValue hint) {
        this.wrapped.hint(hint);
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(ReadBinding binding) {
        return this.wrapped.execute(binding);
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(AsyncReadBinding binding, SingleResultCallback<AsyncBatchCursor<T>> callback) {
        this.wrapped.executeAsync(binding, callback);
    }

    public ReadOperation<BsonDocument> asExplainableOperation(ExplainVerbosity explainVerbosity) {
        return new AggregateExplainOperation(getNamespace(), getPipeline()).allowDiskUse(getAllowDiskUse()).maxTime(getMaxAwaitTime(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS).hint(this.wrapped.getHint());
    }

    public AsyncReadOperation<BsonDocument> asExplainableOperationAsync(ExplainVerbosity explainVerbosity) {
        return new AggregateExplainOperation(getNamespace(), getPipeline()).allowDiskUse(getAllowDiskUse()).maxTime(getMaxAwaitTime(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS).hint(this.wrapped.getHint());
    }

    MongoNamespace getNamespace() {
        return this.wrapped.getNamespace();
    }

    Decoder<T> getDecoder() {
        return this.wrapped.getDecoder();
    }

    public String toString() {
        return "AggregateOperation{namespace=" + getNamespace() + ", pipeline=" + getPipeline() + ", decoder=" + getDecoder() + ", allowDiskUse=" + getAllowDiskUse() + ", batchSize=" + getBatchSize() + ", collation=" + getCollation() + ", comment=" + getComment() + ", hint=" + getHint() + ", maxAwaitTimeMS=" + getMaxAwaitTime(TimeUnit.MILLISECONDS) + ", maxTimeMS=" + getMaxTime(TimeUnit.MILLISECONDS) + ", useCursor=" + this.wrapped.getUseCursor() + "}";
    }
}
