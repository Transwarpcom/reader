package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.connection.ServerType;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.Codec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ListIndexesOperation.class */
public class ListIndexesOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private final MongoNamespace namespace;
    private final Decoder<T> decoder;
    private int batchSize;
    private long maxTimeMS;

    public ListIndexesOperation(MongoNamespace namespace, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public Integer getBatchSize() {
        return Integer.valueOf(this.batchSize);
    }

    public ListIndexesOperation<T> batchSize(int batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public ListIndexesOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.ListIndexesOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource source, Connection connection) {
                if (ServerVersionHelper.serverIsAtLeastVersionThreeDotZero(connection.getDescription())) {
                    try {
                        return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, ListIndexesOperation.this.namespace.getDatabaseName(), ListIndexesOperation.this.getCommand(), ListIndexesOperation.this.createCommandDecoder(), connection, ListIndexesOperation.this.transformer(source));
                    } catch (MongoCommandException e) {
                        return (BatchCursor) CommandOperationHelper.rethrowIfNotNamespaceError(e, OperationHelper.createEmptyBatchCursor(ListIndexesOperation.this.namespace, ListIndexesOperation.this.decoder, source.getServerDescription().getAddress(), ListIndexesOperation.this.batchSize));
                    }
                }
                return new QueryBatchCursor(connection.query(ListIndexesOperation.this.getIndexNamespace(), ListIndexesOperation.this.asQueryDocument(connection.getDescription(), binding.getReadPreference()), null, 0, 0, ListIndexesOperation.this.batchSize, binding.getReadPreference().isSlaveOk(), false, false, false, false, false, ListIndexesOperation.this.decoder), 0, ListIndexesOperation.this.batchSize, ListIndexesOperation.this.decoder, source);
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.ListIndexesOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(final AsyncConnectionSource source, final AsyncConnection connection, Throwable t) {
                SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                    return;
                }
                final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, source, connection);
                if (ServerVersionHelper.serverIsAtLeastVersionThreeDotZero(connection.getDescription())) {
                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, ListIndexesOperation.this.namespace.getDatabaseName(), ListIndexesOperation.this.getCommand(), ListIndexesOperation.this.createCommandDecoder(), connection, ListIndexesOperation.this.asyncTransformer(source, connection), new SingleResultCallback<AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.ListIndexesOperation.2.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(AsyncBatchCursor<T> result, Throwable t2) {
                            AsyncBatchCursor<T> asyncBatchCursorEmptyAsyncCursor;
                            if (t2 != null && !CommandOperationHelper.isNamespaceError(t2)) {
                                wrappedCallback.onResult(null, t2);
                                return;
                            }
                            SingleResultCallback singleResultCallback = wrappedCallback;
                            if (result == null) {
                                asyncBatchCursorEmptyAsyncCursor = ListIndexesOperation.this.emptyAsyncCursor(source);
                            } else {
                                asyncBatchCursorEmptyAsyncCursor = result;
                            }
                            singleResultCallback.onResult(asyncBatchCursorEmptyAsyncCursor, null);
                        }
                    });
                } else {
                    connection.queryAsync(ListIndexesOperation.this.getIndexNamespace(), ListIndexesOperation.this.asQueryDocument(connection.getDescription(), binding.getReadPreference()), null, 0, 0, ListIndexesOperation.this.batchSize, binding.getReadPreference().isSlaveOk(), false, false, false, false, false, ListIndexesOperation.this.decoder, new SingleResultCallback<QueryResult<T>>() { // from class: com.mongodb.operation.ListIndexesOperation.2.2
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(QueryResult<T> result, Throwable t2) {
                            if (t2 == null) {
                                wrappedCallback.onResult(new AsyncQueryBatchCursor(result, 0, ListIndexesOperation.this.batchSize, 0L, ListIndexesOperation.this.decoder, source, connection), null);
                            } else {
                                wrappedCallback.onResult(null, t2);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AsyncBatchCursor<T> emptyAsyncCursor(AsyncConnectionSource source) {
        return OperationHelper.createEmptyAsyncBatchCursor(this.namespace, source.getServerDescription().getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument asQueryDocument(ConnectionDescription connectionDescription, ReadPreference readPreference) {
        BsonDocument document = new BsonDocument("$query", new BsonDocument("ns", new BsonString(this.namespace.getFullName())));
        if (this.maxTimeMS > 0) {
            document.put("$maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        if (connectionDescription.getServerType() == ServerType.SHARD_ROUTER && !readPreference.equals(ReadPreference.primary())) {
            document.put("$readPreference", (BsonValue) readPreference.toDocument());
        }
        return document;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MongoNamespace getIndexNamespace() {
        return new MongoNamespace(this.namespace.getDatabaseName(), "system.indexes");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand() {
        BsonDocument command = new BsonDocument("listIndexes", new BsonString(this.namespace.getCollectionName())).append("cursor", CursorHelper.getCursorDocumentFromBatchSize(this.batchSize == 0 ? null : Integer.valueOf(this.batchSize)));
        if (this.maxTimeMS > 0) {
            command.put("maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        return command;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> transformer(final ConnectionSource source) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.ListIndexesOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return OperationHelper.cursorDocumentToBatchCursor(result.getDocument("cursor"), ListIndexesOperation.this.decoder, source, ListIndexesOperation.this.batchSize);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final AsyncConnectionSource source, final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.ListIndexesOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return OperationHelper.cursorDocumentToAsyncBatchCursor(result.getDocument("cursor"), ListIndexesOperation.this.decoder, source, connection, ListIndexesOperation.this.batchSize);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Codec<BsonDocument> createCommandDecoder() {
        return CommandResultDocumentCodec.create(this.decoder, "firstBatch");
    }
}
