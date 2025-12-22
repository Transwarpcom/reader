package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.ReadBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.QueryResult;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import org.bson.BsonDocument;
import org.bson.BsonJavaScript;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/GroupOperation.class */
public class GroupOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private final MongoNamespace namespace;
    private final Decoder<T> decoder;
    private final BsonJavaScript reduceFunction;
    private final BsonDocument initial;
    private BsonDocument key;
    private BsonJavaScript keyFunction;
    private BsonDocument filter;
    private BsonJavaScript finalizeFunction;
    private Collation collation;

    public GroupOperation(MongoNamespace namespace, BsonJavaScript reduceFunction, BsonDocument initial, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.reduceFunction = (BsonJavaScript) Assertions.notNull("reduceFunction", reduceFunction);
        this.initial = (BsonDocument) Assertions.notNull("initial", initial);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public MongoNamespace getNamespace() {
        return this.namespace;
    }

    public Decoder<T> getDecoder() {
        return this.decoder;
    }

    public BsonDocument getKey() {
        return this.key;
    }

    public GroupOperation<T> key(BsonDocument key) {
        this.key = key;
        return this;
    }

    public BsonJavaScript getKeyFunction() {
        return this.keyFunction;
    }

    public GroupOperation<T> keyFunction(BsonJavaScript keyFunction) {
        this.keyFunction = keyFunction;
        return this;
    }

    public BsonDocument getInitial() {
        return this.initial;
    }

    public BsonJavaScript getReduceFunction() {
        return this.reduceFunction;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public GroupOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonJavaScript getFinalizeFunction() {
        return this.finalizeFunction;
    }

    public GroupOperation<T> finalizeFunction(BsonJavaScript finalizeFunction) {
        this.finalizeFunction = finalizeFunction;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public GroupOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.GroupOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource connectionSource, Connection connection) {
                OperationHelper.validateCollation(connection, GroupOperation.this.collation);
                return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, GroupOperation.this.namespace.getDatabaseName(), GroupOperation.this.getCommand(), CommandResultDocumentCodec.create(GroupOperation.this.decoder, "retval"), connection, GroupOperation.this.transformer(connectionSource, connection));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.GroupOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    OperationHelper.validateCollation(connection, GroupOperation.this.collation, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.GroupOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                        public void call(AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, GroupOperation.this.namespace.getDatabaseName(), GroupOperation.this.getCommand(), CommandResultDocumentCodec.create(GroupOperation.this.decoder, "retval"), connection2, GroupOperation.this.asyncTransformer(connection2), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand() {
        BsonDocument commandDocument = new BsonDocument("ns", new BsonString(this.namespace.getCollectionName()));
        if (getKey() != null) {
            commandDocument.put("key", (BsonValue) getKey());
        } else if (getKeyFunction() != null) {
            commandDocument.put("$keyf", (BsonValue) getKeyFunction());
        }
        commandDocument.put("initial", (BsonValue) getInitial());
        commandDocument.put("$reduce", (BsonValue) getReduceFunction());
        if (getFinalizeFunction() != null) {
            commandDocument.put("finalize", (BsonValue) getFinalizeFunction());
        }
        if (getFilter() != null) {
            commandDocument.put("cond", (BsonValue) getFilter());
        }
        if (getCollation() != null) {
            commandDocument.put("collation", (BsonValue) this.collation.asDocument());
        }
        return new BsonDocument("group", commandDocument);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> transformer(final ConnectionSource source, final Connection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.GroupOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return new QueryBatchCursor(GroupOperation.this.createQueryResult(result, connection.getDescription()), 0, 0, GroupOperation.this.decoder, source);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.GroupOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return new AsyncSingleBatchQueryCursor(GroupOperation.this.createQueryResult(result, connection.getDescription()));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> createQueryResult(BsonDocument result, ConnectionDescription description) {
        return new QueryResult<>(this.namespace, BsonDocumentWrapperHelper.toList(result, "retval"), 0L, description.getServerAddress());
    }
}
