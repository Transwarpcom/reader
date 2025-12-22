package com.mongodb.operation;

import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoNamespace;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
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
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import com.mongodb.session.SessionContext;
import java.util.concurrent.TimeUnit;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonJavaScript;
import org.bson.BsonNull;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MapReduceWithInlineResultsOperation.class */
public class MapReduceWithInlineResultsOperation<T> implements AsyncReadOperation<MapReduceAsyncBatchCursor<T>>, ReadOperation<MapReduceBatchCursor<T>> {
    private final MongoNamespace namespace;
    private final BsonJavaScript mapFunction;
    private final BsonJavaScript reduceFunction;
    private final Decoder<T> decoder;
    private BsonJavaScript finalizeFunction;
    private BsonDocument scope;
    private BsonDocument filter;
    private BsonDocument sort;
    private int limit;
    private boolean jsMode;
    private boolean verbose;
    private long maxTimeMS;
    private Collation collation;

    public MapReduceWithInlineResultsOperation(MongoNamespace namespace, BsonJavaScript mapFunction, BsonJavaScript reduceFunction, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.mapFunction = (BsonJavaScript) Assertions.notNull("mapFunction", mapFunction);
        this.reduceFunction = (BsonJavaScript) Assertions.notNull("reduceFunction", reduceFunction);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    public MongoNamespace getNamespace() {
        return this.namespace;
    }

    public Decoder<T> getDecoder() {
        return this.decoder;
    }

    public BsonJavaScript getMapFunction() {
        return this.mapFunction;
    }

    public BsonJavaScript getReduceFunction() {
        return this.reduceFunction;
    }

    public BsonJavaScript getFinalizeFunction() {
        return this.finalizeFunction;
    }

    public MapReduceWithInlineResultsOperation<T> finalizeFunction(BsonJavaScript finalizeFunction) {
        this.finalizeFunction = finalizeFunction;
        return this;
    }

    public BsonDocument getScope() {
        return this.scope;
    }

    public MapReduceWithInlineResultsOperation<T> scope(BsonDocument scope) {
        this.scope = scope;
        return this;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public MapReduceWithInlineResultsOperation<T> filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonDocument getSort() {
        return this.sort;
    }

    public MapReduceWithInlineResultsOperation<T> sort(BsonDocument sort) {
        this.sort = sort;
        return this;
    }

    public int getLimit() {
        return this.limit;
    }

    public MapReduceWithInlineResultsOperation<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public boolean isJsMode() {
        return this.jsMode;
    }

    public MapReduceWithInlineResultsOperation<T> jsMode(boolean jsMode) {
        this.jsMode = jsMode;
        return this;
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public MapReduceWithInlineResultsOperation<T> verbose(boolean verbose) {
        this.verbose = verbose;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public MapReduceWithInlineResultsOperation<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public MapReduceWithInlineResultsOperation<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public MapReduceBatchCursor<T> execute(final ReadBinding binding) {
        return (MapReduceBatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<MapReduceBatchCursor<T>>() { // from class: com.mongodb.operation.MapReduceWithInlineResultsOperation.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public MapReduceBatchCursor<T> call(ConnectionSource source, Connection connection) {
                OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), MapReduceWithInlineResultsOperation.this.collation);
                return (MapReduceBatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, MapReduceWithInlineResultsOperation.this.namespace.getDatabaseName(), MapReduceWithInlineResultsOperation.this.getCommand(binding.getSessionContext()), CommandResultDocumentCodec.create(MapReduceWithInlineResultsOperation.this.decoder, "results"), connection, MapReduceWithInlineResultsOperation.this.transformer(source, connection));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<MapReduceAsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.MapReduceWithInlineResultsOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<MapReduceAsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<MapReduceAsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), MapReduceWithInlineResultsOperation.this.collation, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.MapReduceWithInlineResultsOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                        public void call(AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, MapReduceWithInlineResultsOperation.this.namespace.getDatabaseName(), MapReduceWithInlineResultsOperation.this.getCommand(binding.getSessionContext()), CommandResultDocumentCodec.create(MapReduceWithInlineResultsOperation.this.decoder, "results"), connection2, MapReduceWithInlineResultsOperation.this.asyncTransformer(connection2), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    public ReadOperation<BsonDocument> asExplainableOperation(ExplainVerbosity explainVerbosity) {
        return createExplainableOperation(explainVerbosity);
    }

    public AsyncReadOperation<BsonDocument> asExplainableOperationAsync(ExplainVerbosity explainVerbosity) {
        return createExplainableOperation(explainVerbosity);
    }

    private CommandReadOperation<BsonDocument> createExplainableOperation(ExplainVerbosity explainVerbosity) {
        return new CommandReadOperation<>(this.namespace.getDatabaseName(), ExplainHelper.asExplainCommand(getCommand(NoOpSessionContext.INSTANCE), explainVerbosity), new BsonDocumentCodec());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, MapReduceBatchCursor<T>> transformer(final ConnectionSource source, final Connection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, MapReduceBatchCursor<T>>() { // from class: com.mongodb.operation.MapReduceWithInlineResultsOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public MapReduceBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return new MapReduceInlineResultsCursor(MapReduceWithInlineResultsOperation.this.createQueryResult(result, connection.getDescription()), MapReduceWithInlineResultsOperation.this.decoder, source, MapReduceHelper.createStatistics(result));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, MapReduceAsyncBatchCursor<T>> asyncTransformer(final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, MapReduceAsyncBatchCursor<T>>() { // from class: com.mongodb.operation.MapReduceWithInlineResultsOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public MapReduceAsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                return new MapReduceInlineResultsAsyncCursor(MapReduceWithInlineResultsOperation.this.createQueryResult(result, connection.getDescription()), MapReduceHelper.createStatistics(result));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(SessionContext sessionContext) {
        BsonDocument commandDocument = new BsonDocument("mapreduce", new BsonString(this.namespace.getCollectionName())).append(BeanDefinitionParserDelegate.MAP_ELEMENT, getMapFunction()).append("reduce", getReduceFunction()).append("out", new BsonDocument("inline", new BsonInt32(1))).append("query", asValueOrNull(getFilter())).append("sort", asValueOrNull(getSort())).append("finalize", asValueOrNull(getFinalizeFunction())).append("scope", asValueOrNull(getScope())).append("verbose", BsonBoolean.valueOf(isVerbose()));
        OperationReadConcernHelper.appendReadConcernToCommand(sessionContext, commandDocument);
        DocumentHelper.putIfNotZero(commandDocument, "limit", getLimit());
        DocumentHelper.putIfNotZero(commandDocument, "maxTimeMS", getMaxTime(TimeUnit.MILLISECONDS));
        DocumentHelper.putIfTrue(commandDocument, "jsMode", isJsMode());
        if (this.collation != null) {
            commandDocument.put("collation", (BsonValue) this.collation.asDocument());
        }
        return commandDocument;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> createQueryResult(BsonDocument result, ConnectionDescription description) {
        return new QueryResult<>(this.namespace, BsonDocumentWrapperHelper.toList(result, "results"), 0L, description.getServerAddress());
    }

    private static BsonValue asValueOrNull(BsonValue value) {
        return value == null ? BsonNull.VALUE : value;
    }
}
