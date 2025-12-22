package com.mongodb.operation;

import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoNamespace;
import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.AsyncBatchCursor;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.client.model.CountStrategy;
import com.mongodb.internal.connection.NoOpSessionContext;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import com.mongodb.session.SessionContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonNull;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CountOperation.class */
public class CountOperation implements AsyncReadOperation<Long>, ReadOperation<Long> {
    private static final Decoder<BsonDocument> DECODER = new BsonDocumentCodec();
    private final MongoNamespace namespace;
    private final CountStrategy countStrategy;
    private BsonDocument filter;
    private BsonValue hint;
    private long skip;
    private long limit;
    private long maxTimeMS;
    private Collation collation;

    public CountOperation(MongoNamespace namespace) {
        this(namespace, CountStrategy.COMMAND);
    }

    public CountOperation(MongoNamespace namespace, CountStrategy countStrategy) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.countStrategy = (CountStrategy) Assertions.notNull("countStrategy", countStrategy);
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public CountOperation filter(BsonDocument filter) {
        this.filter = filter;
        return this;
    }

    public BsonValue getHint() {
        return this.hint;
    }

    public CountOperation hint(BsonValue hint) {
        this.hint = hint;
        return this;
    }

    public long getLimit() {
        return this.limit;
    }

    public CountOperation limit(long limit) {
        this.limit = limit;
        return this;
    }

    public long getSkip() {
        return this.skip;
    }

    public CountOperation skip(long skip) {
        this.skip = skip;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public CountOperation maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public CountOperation collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.ReadOperation
    public Long execute(final ReadBinding binding) {
        if (this.countStrategy.equals(CountStrategy.COMMAND)) {
            return (Long) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<Long>() { // from class: com.mongodb.operation.CountOperation.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
                public Long call(Connection connection) {
                    OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), CountOperation.this.collation);
                    return (Long) CommandOperationHelper.executeWrappedCommandProtocol(binding, CountOperation.this.namespace.getDatabaseName(), CountOperation.this.getCommand(binding.getSessionContext()), (Decoder<BsonDocument>) CountOperation.DECODER, connection, CountOperation.this.transformer());
                }
            });
        }
        BatchCursor<BsonDocument> cursor = getAggregateOperation().execute(binding);
        return Long.valueOf(cursor.hasNext() ? getCountFromAggregateResults(cursor.next()).longValue() : 0L);
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<Long> callback) {
        if (this.countStrategy.equals(CountStrategy.COMMAND)) {
            OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.CountOperation.2
                @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                public void call(AsyncConnection connection, Throwable t) {
                    SingleResultCallback<Long> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                    if (t != null) {
                        errHandlingCallback.onResult(null, t);
                    } else {
                        final SingleResultCallback<Long> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                        OperationHelper.validateReadConcernAndCollation(connection, binding.getSessionContext().getReadConcern(), CountOperation.this.collation, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.CountOperation.2.1
                            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                            public void call(AsyncConnection connection2, Throwable t2) {
                                if (t2 != null) {
                                    wrappedCallback.onResult(null, t2);
                                } else {
                                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, CountOperation.this.namespace.getDatabaseName(), CountOperation.this.getCommand(binding.getSessionContext()), (Decoder<BsonDocument>) CountOperation.DECODER, connection2, CountOperation.this.transformer(), wrappedCallback);
                                }
                            }
                        });
                    }
                }
            });
        } else {
            getAggregateOperation().executeAsync(binding, new SingleResultCallback<AsyncBatchCursor<BsonDocument>>() { // from class: com.mongodb.operation.CountOperation.3
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(AsyncBatchCursor<BsonDocument> result, Throwable t) {
                    if (t != null) {
                        callback.onResult(null, t);
                    } else {
                        result.next(new SingleResultCallback<List<BsonDocument>>() { // from class: com.mongodb.operation.CountOperation.3.1
                            @Override // com.mongodb.async.SingleResultCallback
                            public void onResult(List<BsonDocument> result2, Throwable t2) {
                                if (t2 == null) {
                                    callback.onResult(CountOperation.this.getCountFromAggregateResults(result2), null);
                                } else {
                                    callback.onResult(null, t2);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public ReadOperation<BsonDocument> asExplainableOperation(ExplainVerbosity explainVerbosity) {
        if (this.countStrategy.equals(CountStrategy.COMMAND)) {
            return createExplainableOperation(explainVerbosity);
        }
        return getAggregateOperation().asExplainableOperation(explainVerbosity);
    }

    public AsyncReadOperation<BsonDocument> asExplainableOperationAsync(ExplainVerbosity explainVerbosity) {
        if (this.countStrategy.equals(CountStrategy.COMMAND)) {
            return createExplainableOperation(explainVerbosity);
        }
        return getAggregateOperation().asExplainableOperationAsync(explainVerbosity);
    }

    private CommandReadOperation<BsonDocument> createExplainableOperation(ExplainVerbosity explainVerbosity) {
        return new CommandReadOperation<>(this.namespace.getDatabaseName(), ExplainHelper.asExplainCommand(getCommand(NoOpSessionContext.INSTANCE), explainVerbosity), new BsonDocumentCodec());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, Long> transformer() {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, Long>() { // from class: com.mongodb.operation.CountOperation.4
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public Long apply(BsonDocument result, ServerAddress serverAddress) {
                return Long.valueOf(result.getNumber(OperatorName.ENDPATH).longValue());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(SessionContext sessionContext) {
        BsonDocument document = new BsonDocument("count", new BsonString(this.namespace.getCollectionName()));
        OperationReadConcernHelper.appendReadConcernToCommand(sessionContext, document);
        DocumentHelper.putIfNotNull(document, "query", this.filter);
        DocumentHelper.putIfNotZero(document, "limit", this.limit);
        DocumentHelper.putIfNotZero(document, "skip", this.skip);
        DocumentHelper.putIfNotNull(document, "hint", this.hint);
        DocumentHelper.putIfNotZero(document, "maxTimeMS", this.maxTimeMS);
        if (this.collation != null) {
            document.put("collation", (BsonValue) this.collation.asDocument());
        }
        return document;
    }

    private AggregateOperation<BsonDocument> getAggregateOperation() {
        return new AggregateOperation(this.namespace, getPipeline(), DECODER).collation(this.collation).hint(this.hint).maxTime(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    private List<BsonDocument> getPipeline() {
        ArrayList<BsonDocument> pipeline = new ArrayList<>();
        pipeline.add(new BsonDocument("$match", this.filter != null ? this.filter : new BsonDocument()));
        if (this.skip > 0) {
            pipeline.add(new BsonDocument("$skip", new BsonInt64(this.skip)));
        }
        if (this.limit > 0) {
            pipeline.add(new BsonDocument("$limit", new BsonInt64(this.limit)));
        }
        pipeline.add(new BsonDocument("$group", new BsonDocument("_id", new BsonNull()).append(OperatorName.ENDPATH, new BsonDocument("$sum", new BsonInt32(1)))));
        return pipeline;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Long getCountFromAggregateResults(List<BsonDocument> results) {
        if (results == null || results.isEmpty()) {
            return 0L;
        }
        return Long.valueOf(results.get(0).getNumber(OperatorName.ENDPATH).longValue());
    }
}
