package com.mongodb.operation;

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
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import java.util.concurrent.TimeUnit;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ListDatabasesOperation.class */
public class ListDatabasesOperation<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private final Decoder<T> decoder;
    private long maxTimeMS;
    private BsonDocument filter;
    private Boolean nameOnly;

    public ListDatabasesOperation(Decoder<T> decoder) {
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public ListDatabasesOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public ListDatabasesOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public ListDatabasesOperation<T> nameOnly(Boolean nameOnly) {
        this.nameOnly = nameOnly;
        return this;
    }

    public Boolean getNameOnly() {
        return this.nameOnly;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.ListDatabasesOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource source, Connection connection) {
                return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, "admin", ListDatabasesOperation.this.getCommand(), CommandResultDocumentCodec.create(ListDatabasesOperation.this.decoder, "databases"), connection, ListDatabasesOperation.this.transformer(source, connection));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.ListDatabasesOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
                SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, "admin", ListDatabasesOperation.this.getCommand(), CommandResultDocumentCodec.create(ListDatabasesOperation.this.decoder, "databases"), connection, ListDatabasesOperation.this.asyncTransformer(source, connection), OperationHelper.releasingCallback(errHandlingCallback, source, connection));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> transformer(final ConnectionSource source, final Connection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.ListDatabasesOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return new QueryBatchCursor(ListDatabasesOperation.this.createQueryResult(result, connection.getDescription()), 0, 0, ListDatabasesOperation.this.decoder, source);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final AsyncConnectionSource source, final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.ListDatabasesOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return new AsyncQueryBatchCursor(ListDatabasesOperation.this.createQueryResult(result, connection.getDescription()), 0, 0, 0L, ListDatabasesOperation.this.decoder, source, connection);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> createQueryResult(BsonDocument result, ConnectionDescription description) {
        return new QueryResult<>(null, BsonDocumentWrapperHelper.toList(result, "databases"), 0L, description.getServerAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand() {
        BsonDocument command = new BsonDocument("listDatabases", new BsonInt32(1));
        if (this.maxTimeMS > 0) {
            command.put("maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        if (this.filter != null) {
            command.put("filter", (BsonValue) this.filter);
        }
        if (this.nameOnly != null) {
            command.put("nameOnly", (BsonValue) new BsonBoolean(this.nameOnly.booleanValue()));
        }
        return command;
    }
}
