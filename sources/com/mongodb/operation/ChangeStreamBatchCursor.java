package com.mongodb.operation;

import com.mongodb.Function;
import com.mongodb.MongoChangeStreamException;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.binding.ReadBinding;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.RawBsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ChangeStreamBatchCursor.class */
final class ChangeStreamBatchCursor<T> implements BatchCursor<T> {
    private final ReadBinding binding;
    private final ChangeStreamOperation<T> changeStreamOperation;
    private BsonDocument resumeToken;
    private BatchCursor<RawBsonDocument> wrapped;

    ChangeStreamBatchCursor(ChangeStreamOperation<T> changeStreamOperation, BatchCursor<RawBsonDocument> wrapped, ReadBinding binding) {
        changeStreamOperation.startOperationTimeForResume(binding.getSessionContext().getOperationTime());
        this.changeStreamOperation = changeStreamOperation;
        this.resumeToken = changeStreamOperation.getResumeToken();
        this.wrapped = wrapped;
        this.binding = binding.retain();
    }

    BatchCursor<RawBsonDocument> getWrapped() {
        return this.wrapped;
    }

    @Override // com.mongodb.operation.BatchCursor, java.util.Iterator
    public boolean hasNext() {
        return ((Boolean) resumeableOperation(new Function<BatchCursor<RawBsonDocument>, Boolean>() { // from class: com.mongodb.operation.ChangeStreamBatchCursor.1
            @Override // com.mongodb.Function
            public Boolean apply(BatchCursor<RawBsonDocument> queryBatchCursor) {
                return Boolean.valueOf(queryBatchCursor.hasNext());
            }
        })).booleanValue();
    }

    @Override // java.util.Iterator
    public List<T> next() {
        return (List) resumeableOperation(new Function<BatchCursor<RawBsonDocument>, List<T>>() { // from class: com.mongodb.operation.ChangeStreamBatchCursor.2
            @Override // com.mongodb.Function
            public List<T> apply(BatchCursor<RawBsonDocument> queryBatchCursor) {
                return ChangeStreamBatchCursor.this.convertResults(queryBatchCursor.next());
            }
        });
    }

    @Override // com.mongodb.operation.BatchCursor
    public List<T> tryNext() {
        return (List) resumeableOperation(new Function<BatchCursor<RawBsonDocument>, List<T>>() { // from class: com.mongodb.operation.ChangeStreamBatchCursor.3
            @Override // com.mongodb.Function
            public List<T> apply(BatchCursor<RawBsonDocument> queryBatchCursor) {
                return ChangeStreamBatchCursor.this.convertResults(queryBatchCursor.tryNext());
            }
        });
    }

    @Override // com.mongodb.operation.BatchCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.wrapped.close();
        this.binding.release();
    }

    @Override // com.mongodb.operation.BatchCursor
    public void setBatchSize(int batchSize) {
        this.wrapped.setBatchSize(batchSize);
    }

    @Override // com.mongodb.operation.BatchCursor
    public int getBatchSize() {
        return this.wrapped.getBatchSize();
    }

    @Override // com.mongodb.operation.BatchCursor
    public ServerCursor getServerCursor() {
        return this.wrapped.getServerCursor();
    }

    @Override // com.mongodb.operation.BatchCursor
    public ServerAddress getServerAddress() {
        return this.wrapped.getServerAddress();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Not implemented!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<T> convertResults(List<RawBsonDocument> rawDocuments) {
        ArrayList arrayList = null;
        if (rawDocuments != null) {
            arrayList = new ArrayList();
            for (RawBsonDocument rawDocument : rawDocuments) {
                if (!rawDocument.containsKey("_id")) {
                    throw new MongoChangeStreamException("Cannot provide resume functionality when the resume token is missing.");
                }
                this.resumeToken = rawDocument.getDocument("_id");
                arrayList.add(rawDocument.decode(this.changeStreamOperation.getDecoder()));
            }
        }
        return arrayList;
    }

    <R> R resumeableOperation(Function<BatchCursor<RawBsonDocument>, R> function) {
        while (true) {
            try {
                return function.apply(this.wrapped);
            } catch (Throwable t) {
                if (!ChangeStreamBatchCursorHelper.isRetryableError(t)) {
                    throw MongoException.fromThrowableNonNull(t);
                }
                this.wrapped.close();
                if (this.resumeToken != null) {
                    this.changeStreamOperation.startOperationTimeForResume(null);
                    this.changeStreamOperation.resumeAfter(this.resumeToken);
                }
                this.wrapped = ((ChangeStreamBatchCursor) this.changeStreamOperation.execute(this.binding)).getWrapped();
                this.binding.release();
            }
        }
    }
}
