package com.mongodb.operation;

import com.mongodb.MongoChangeStreamException;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import java.util.ArrayList;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.RawBsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncChangeStreamBatchCursor.class */
final class AsyncChangeStreamBatchCursor<T> implements AsyncBatchCursor<T> {
    private final AsyncReadBinding binding;
    private final ChangeStreamOperation<T> changeStreamOperation;
    private volatile BsonDocument resumeToken;
    private volatile AsyncBatchCursor<RawBsonDocument> wrapped;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncChangeStreamBatchCursor$AsyncBlock.class */
    private interface AsyncBlock {
        void apply(AsyncBatchCursor<RawBsonDocument> asyncBatchCursor, SingleResultCallback<List<RawBsonDocument>> singleResultCallback);
    }

    AsyncChangeStreamBatchCursor(ChangeStreamOperation<T> changeStreamOperation, AsyncBatchCursor<RawBsonDocument> wrapped, AsyncReadBinding binding) {
        changeStreamOperation.startOperationTimeForResume(binding.getSessionContext().getOperationTime());
        this.changeStreamOperation = changeStreamOperation;
        this.resumeToken = changeStreamOperation.getResumeToken();
        this.wrapped = wrapped;
        this.binding = binding;
        binding.retain();
    }

    AsyncBatchCursor<RawBsonDocument> getWrapped() {
        return this.wrapped;
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void next(SingleResultCallback<List<T>> callback) {
        resumeableOperation(new AsyncBlock() { // from class: com.mongodb.operation.AsyncChangeStreamBatchCursor.1
            @Override // com.mongodb.operation.AsyncChangeStreamBatchCursor.AsyncBlock
            public void apply(AsyncBatchCursor<RawBsonDocument> cursor, SingleResultCallback<List<RawBsonDocument>> callback2) {
                cursor.next(callback2);
            }
        }, convertResultsCallback(callback));
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void tryNext(SingleResultCallback<List<T>> callback) {
        resumeableOperation(new AsyncBlock() { // from class: com.mongodb.operation.AsyncChangeStreamBatchCursor.2
            @Override // com.mongodb.operation.AsyncChangeStreamBatchCursor.AsyncBlock
            public void apply(AsyncBatchCursor<RawBsonDocument> cursor, SingleResultCallback<List<RawBsonDocument>> callback2) {
                cursor.tryNext(callback2);
            }
        }, convertResultsCallback(callback));
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public void setBatchSize(int batchSize) {
        this.wrapped.setBatchSize(batchSize);
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public int getBatchSize() {
        return this.wrapped.getBatchSize();
    }

    @Override // com.mongodb.async.AsyncBatchCursor
    public boolean isClosed() {
        return this.wrapped.isClosed();
    }

    @Override // com.mongodb.async.AsyncBatchCursor, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.wrapped.close();
        this.binding.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeableOperation(final AsyncBlock asyncBlock, final SingleResultCallback<List<RawBsonDocument>> callback) {
        asyncBlock.apply(this.wrapped, new SingleResultCallback<List<RawBsonDocument>>() { // from class: com.mongodb.operation.AsyncChangeStreamBatchCursor.3
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(List<RawBsonDocument> result, Throwable t) {
                if (t == null) {
                    callback.onResult(result, null);
                } else if (ChangeStreamBatchCursorHelper.isRetryableError(t)) {
                    AsyncChangeStreamBatchCursor.this.wrapped.close();
                    AsyncChangeStreamBatchCursor.this.retryOperation(asyncBlock, callback);
                } else {
                    callback.onResult(null, t);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryOperation(final AsyncBlock asyncBlock, final SingleResultCallback<List<RawBsonDocument>> callback) {
        if (this.resumeToken != null) {
            this.changeStreamOperation.startOperationTimeForResume(null);
            this.changeStreamOperation.resumeAfter(this.resumeToken);
        }
        this.changeStreamOperation.executeAsync(this.binding, new SingleResultCallback<AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.AsyncChangeStreamBatchCursor.4
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(AsyncBatchCursor<T> result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                    return;
                }
                AsyncChangeStreamBatchCursor.this.wrapped = ((AsyncChangeStreamBatchCursor) result).getWrapped();
                AsyncChangeStreamBatchCursor.this.binding.release();
                AsyncChangeStreamBatchCursor.this.resumeableOperation(asyncBlock, callback);
            }
        });
    }

    private SingleResultCallback<List<RawBsonDocument>> convertResultsCallback(final SingleResultCallback<List<T>> callback) {
        return ErrorHandlingResultCallback.errorHandlingCallback(new SingleResultCallback<List<RawBsonDocument>>() { // from class: com.mongodb.operation.AsyncChangeStreamBatchCursor.5
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(List<RawBsonDocument> rawDocuments, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                    return;
                }
                if (rawDocuments != null) {
                    ArrayList arrayList = new ArrayList();
                    for (RawBsonDocument rawDocument : rawDocuments) {
                        if (rawDocument.containsKey("_id")) {
                            AsyncChangeStreamBatchCursor.this.resumeToken = rawDocument.getDocument("_id");
                            arrayList.add(rawDocument.decode(AsyncChangeStreamBatchCursor.this.changeStreamOperation.getDecoder()));
                        } else {
                            callback.onResult(null, new MongoChangeStreamException("Cannot provide resume functionality when the resume token is missing."));
                            return;
                        }
                    }
                    callback.onResult(arrayList, null);
                    return;
                }
                callback.onResult(null, null);
            }
        }, OperationHelper.LOGGER);
    }
}
