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
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import com.mongodb.session.SessionContext;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AggregateOperationImpl.class */
class AggregateOperationImpl<T> implements AsyncReadOperation<AsyncBatchCursor<T>>, ReadOperation<BatchCursor<T>> {
    private static final String RESULT = "result";
    private static final String CURSOR = "cursor";
    private static final String FIRST_BATCH = "firstBatch";
    private static final List<String> FIELD_NAMES_WITH_RESULT = Arrays.asList("result", FIRST_BATCH);
    private final MongoNamespace namespace;
    private final List<BsonDocument> pipeline;
    private final Decoder<T> decoder;
    private final AggregateTarget aggregateTarget;
    private final PipelineCreator pipelineCreator;
    private Boolean allowDiskUse;
    private Integer batchSize;
    private Collation collation;
    private String comment;
    private BsonValue hint;
    private long maxAwaitTimeMS;
    private long maxTimeMS;
    private Boolean useCursor;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AggregateOperationImpl$AggregateTarget.class */
    interface AggregateTarget {
        BsonValue create();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AggregateOperationImpl$PipelineCreator.class */
    interface PipelineCreator {
        BsonArray create(ConnectionDescription connectionDescription, SessionContext sessionContext);
    }

    AggregateOperationImpl(MongoNamespace namespace, List<BsonDocument> pipeline, Decoder<T> decoder) {
        this(namespace, pipeline, decoder, defaultAggregateTarget(namespace.getCollectionName()), defaultPipelineCreator(pipeline));
    }

    AggregateOperationImpl(MongoNamespace namespace, List<BsonDocument> pipeline, Decoder<T> decoder, AggregateTarget aggregateTarget, PipelineCreator pipelineCreator) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.pipeline = (List) Assertions.notNull("pipeline", pipeline);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
        this.aggregateTarget = (AggregateTarget) Assertions.notNull("aggregateTarget", aggregateTarget);
        this.pipelineCreator = (PipelineCreator) Assertions.notNull("pipelineCreator", pipelineCreator);
    }

    MongoNamespace getNamespace() {
        return this.namespace;
    }

    List<BsonDocument> getPipeline() {
        return this.pipeline;
    }

    Decoder<T> getDecoder() {
        return this.decoder;
    }

    Boolean getAllowDiskUse() {
        return this.allowDiskUse;
    }

    AggregateOperationImpl<T> allowDiskUse(Boolean allowDiskUse) {
        this.allowDiskUse = allowDiskUse;
        return this;
    }

    Integer getBatchSize() {
        return this.batchSize;
    }

    AggregateOperationImpl<T> batchSize(Integer batchSize) {
        this.batchSize = batchSize;
        return this;
    }

    long getMaxAwaitTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxAwaitTimeMS, TimeUnit.MILLISECONDS);
    }

    AggregateOperationImpl<T> maxAwaitTime(long maxAwaitTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        Assertions.isTrueArgument("maxAwaitTime >= 0", maxAwaitTime >= 0);
        this.maxAwaitTimeMS = TimeUnit.MILLISECONDS.convert(maxAwaitTime, timeUnit);
        return this;
    }

    long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    AggregateOperationImpl<T> maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        Assertions.isTrueArgument("maxTime >= 0", maxTime >= 0);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    Boolean getUseCursor() {
        return this.useCursor;
    }

    AggregateOperationImpl<T> useCursor(Boolean useCursor) {
        this.useCursor = useCursor;
        return this;
    }

    Collation getCollation() {
        return this.collation;
    }

    AggregateOperationImpl<T> collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    String getComment() {
        return this.comment;
    }

    AggregateOperationImpl<T> comment(String comment) {
        this.comment = comment;
        return this;
    }

    BsonValue getHint() {
        return this.hint;
    }

    AggregateOperationImpl<T> hint(BsonValue hint) {
        Assertions.isTrueArgument("BsonString or BsonDocument", hint == null || hint.isDocument() || hint.isString());
        this.hint = hint;
        return this;
    }

    @Override // com.mongodb.operation.ReadOperation
    public BatchCursor<T> execute(final ReadBinding binding) {
        return (BatchCursor) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BatchCursor<T>>() { // from class: com.mongodb.operation.AggregateOperationImpl.1
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BatchCursor<T> call(ConnectionSource source, Connection connection) {
                OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), AggregateOperationImpl.this.collation);
                return (BatchCursor) CommandOperationHelper.executeWrappedCommandProtocol(binding, AggregateOperationImpl.this.namespace.getDatabaseName(), AggregateOperationImpl.this.getCommand(connection.getDescription(), binding.getSessionContext()), CommandResultDocumentCodec.create(AggregateOperationImpl.this.decoder, (List<String>) AggregateOperationImpl.FIELD_NAMES_WITH_RESULT), connection, AggregateOperationImpl.this.transformer(source, connection));
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<AsyncBatchCursor<T>> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.AggregateOperationImpl.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
                SingleResultCallback<AsyncBatchCursor<T>> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<AsyncBatchCursor<T>> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, source, connection);
                    OperationHelper.validateReadConcernAndCollation(source, connection, binding.getSessionContext().getReadConcern(), AggregateOperationImpl.this.collation, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.AggregateOperationImpl.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
                        public void call(AsyncConnectionSource source2, AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, AggregateOperationImpl.this.namespace.getDatabaseName(), AggregateOperationImpl.this.getCommand(connection2.getDescription(), binding.getSessionContext()), CommandResultDocumentCodec.create(AggregateOperationImpl.this.decoder, (List<String>) AggregateOperationImpl.FIELD_NAMES_WITH_RESULT), connection2, AggregateOperationImpl.this.asyncTransformer(source2, connection2), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean isInline(ConnectionDescription description) {
        return (ServerVersionHelper.serverIsAtLeastVersionThreeDotSix(description) || this.useCursor == null || this.useCursor.booleanValue()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(ConnectionDescription description, SessionContext sessionContext) {
        BsonDocument commandDocument = new BsonDocument("aggregate", this.aggregateTarget.create());
        OperationReadConcernHelper.appendReadConcernToCommand(sessionContext, commandDocument);
        commandDocument.put("pipeline", this.pipelineCreator.create(description, sessionContext));
        if (this.maxTimeMS > 0) {
            commandDocument.put("maxTimeMS", new BsonInt64(this.maxTimeMS));
        }
        if (!isInline(description)) {
            BsonDocument cursor = new BsonDocument();
            if (this.batchSize != null) {
                cursor.put("batchSize", (BsonValue) new BsonInt32(this.batchSize.intValue()));
            }
            commandDocument.put(CURSOR, (BsonValue) cursor);
        }
        if (this.allowDiskUse != null) {
            commandDocument.put("allowDiskUse", BsonBoolean.valueOf(this.allowDiskUse.booleanValue()));
        }
        if (this.collation != null) {
            commandDocument.put("collation", this.collation.asDocument());
        }
        if (this.comment != null) {
            commandDocument.put("comment", new BsonString(this.comment));
        }
        if (this.hint != null) {
            commandDocument.put("hint", this.hint);
        }
        return commandDocument;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public QueryResult<T> createQueryResult(BsonDocument result, ConnectionDescription description) {
        if (!isInline(description) || result.containsKey(CURSOR)) {
            return OperationHelper.cursorDocumentToQueryResult(result.getDocument(CURSOR), description.getServerAddress());
        }
        return new QueryResult<>(this.namespace, BsonDocumentWrapperHelper.toList(result, "result"), 0L, description.getServerAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>> transformer(final ConnectionSource source, final Connection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, BatchCursor<T>>() { // from class: com.mongodb.operation.AggregateOperationImpl.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public BatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                QueryResult<T> queryResult = AggregateOperationImpl.this.createQueryResult(result, connection.getDescription());
                return new QueryBatchCursor(queryResult, 0, AggregateOperationImpl.this.batchSize != null ? AggregateOperationImpl.this.batchSize.intValue() : 0, AggregateOperationImpl.this.maxAwaitTimeMS, AggregateOperationImpl.this.decoder, source, connection);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>> asyncTransformer(final AsyncConnectionSource source, final AsyncConnection connection) {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, AsyncBatchCursor<T>>() { // from class: com.mongodb.operation.AggregateOperationImpl.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public AsyncBatchCursor<T> apply(BsonDocument result, ServerAddress serverAddress) {
                QueryResult<T> queryResult = AggregateOperationImpl.this.createQueryResult(result, connection.getDescription());
                return new AsyncQueryBatchCursor(queryResult, 0, AggregateOperationImpl.this.batchSize != null ? AggregateOperationImpl.this.batchSize.intValue() : 0, AggregateOperationImpl.this.maxAwaitTimeMS, AggregateOperationImpl.this.decoder, source, connection);
            }
        };
    }

    private static AggregateTarget defaultAggregateTarget(final String collectionName) {
        return new AggregateTarget() { // from class: com.mongodb.operation.AggregateOperationImpl.5
            @Override // com.mongodb.operation.AggregateOperationImpl.AggregateTarget
            public BsonValue create() {
                return new BsonString(collectionName);
            }
        };
    }

    private static PipelineCreator defaultPipelineCreator(final List<BsonDocument> pipeline) {
        return new PipelineCreator() { // from class: com.mongodb.operation.AggregateOperationImpl.6
            @Override // com.mongodb.operation.AggregateOperationImpl.PipelineCreator
            public BsonArray create(ConnectionDescription connectionDescription, SessionContext sessionContext) {
                return new BsonArray(pipeline);
            }
        };
    }
}
