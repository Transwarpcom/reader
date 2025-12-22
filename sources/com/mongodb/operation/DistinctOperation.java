package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
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
import com.mongodb.session.SessionContext;
import java.util.concurrent.TimeUnit;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.Codec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/DistinctOperation.class */
public class DistinctOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private static final String VALUES = "values";
    private final MongoNamespace namespace;
    private final String fieldName;
    private final Decoder<T> decoder;
    private BsonDocument filter;
    private long maxTimeMS;
    private Collation collation;

    public DistinctOperation(MongoNamespace namespace, String fieldName, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.fieldName = (String) Assertions.notNull("fieldName", fieldName);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public DistinctOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public DistinctOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public DistinctOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.DistinctOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource source, Connection connection) {
                OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), DistinctOperation.this.collation);
                return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, DistinctOperation.this.namespace.getDatabaseName(), DistinctOperation.this.getCommand(binding.getSessionContext()), DistinctOperation.this.createCommandDecoder(), connection, DistinctOperation.this.transformer(source, connection));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.DistinctOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
                SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, source, connection);
                    OperationHelper.validateReadConcernAndCollation(source, connection, binding.getSessionContext().getReadConcern(), DistinctOperation.this.collation, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.DistinctOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                        public void call(AsyncConnectionSource source2, AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, DistinctOperation.this.namespace.getDatabaseName(), DistinctOperation.this.getCommand(binding.getSessionContext()), DistinctOperation.this.createCommandDecoder(), connection2, DistinctOperation.this.asyncTransformer(connection2.getDescription()), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Codec<BsonDocument> createCommandDecoder() {
        return CommandResultDocumentCodec.create(this.decoder, VALUES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> createQueryResult(BsonDocument result, ConnectionDescription description) {
        return new QueryResult<>(this.namespace, BsonDocumentWrapperHelper.toList(result, VALUES), 0L, description.getServerAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> transformer(final ConnectionSource source, final Connection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.DistinctOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                QueryResult<T> queryResult = DistinctOperation.this.createQueryResult(result, connection.getDescription());
                return new QueryBatchCursor(queryResult, 0, 0, DistinctOperation.this.decoder, source);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final ConnectionDescription connectionDescription) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.DistinctOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                QueryResult<T> queryResult = DistinctOperation.this.createQueryResult(result, connectionDescription);
                return new AsyncSingleBatchQueryCursor(queryResult);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(SessionContext sessionContext) {
        BsonDocument commandDocument = new BsonDocument("distinct", new BsonString(this.namespace.getCollectionName()));
        OperationReadConcernHelper.appendReadConcernToCommand(sessionContext, commandDocument);
        commandDocument.put("key", (BsonValue) new BsonString(this.fieldName));
        DocumentHelper.putIfNotNullOrEmpty(commandDocument, "query", this.filter);
        DocumentHelper.putIfNotZero(commandDocument, "maxTimeMS", this.maxTimeMS);
        if (this.collation != null) {
            commandDocument.put("collation", (BsonValue) this.collation.asDocument());
        }
        return commandDocument;
    }
}
