package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AggregateExplainOperation.class */
class AggregateExplainOperation implements AsyncReadOperation<BsonDocument>, ReadOperation<BsonDocument> {
    private final MongoNamespace namespace;
    private final List<BsonDocument> pipeline;
    private Boolean allowDiskUse;
    private long maxTimeMS;
    private Collation collation;
    private BsonValue hint;

    AggregateExplainOperation(MongoNamespace namespace, List<BsonDocument> pipeline) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.pipeline = (List) Assertions.notNull("pipeline", pipeline);
    }

    public AggregateExplainOperation allowDiskUse(Boolean allowDiskUse) {
        this.allowDiskUse = allowDiskUse;
        return this;
    }

    public AggregateExplainOperation maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public AggregateExplainOperation collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    public BsonDocument getHint() {
        if (this.hint == null) {
            return null;
        }
        if (!this.hint.isDocument()) {
            throw new IllegalArgumentException("Hint is not a BsonDocument please use the #getHintBsonValue() method. ");
        }
        return this.hint.asDocument();
    }

    public BsonValue getHintBsonValue() {
        return this.hint;
    }

    public AggregateExplainOperation hint(BsonValue hint) {
        Assertions.isTrueArgument("BsonString or BsonDocument", hint == null || hint.isDocument() || hint.isString());
        this.hint = hint;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.ReadOperation
    public BsonDocument execute(final ReadBinding binding) {
        return (BsonDocument) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<BsonDocument>() { // from class: com.mongodb.operation.AggregateExplainOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public BsonDocument call(Connection connection) {
                OperationHelper.validateCollation(connection, AggregateExplainOperation.this.collation);
                return CommandOperationHelper.executeWrappedCommandProtocol(binding, AggregateExplainOperation.this.namespace.getDatabaseName(), AggregateExplainOperation.this.getCommand(), connection);
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<BsonDocument> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.AggregateExplainOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<BsonDocument> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<BsonDocument> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    OperationHelper.validateCollation(connection, AggregateExplainOperation.this.collation, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.AggregateExplainOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                        public void call(AsyncConnection connection2, Throwable t2) {
                            if (t2 == null) {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, AggregateExplainOperation.this.namespace.getDatabaseName(), AggregateExplainOperation.this.getCommand(), connection2, new CommandOperationHelper.IdentityTransformer(), wrappedCallback);
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
    public BsonDocument getCommand() {
        BsonDocument commandDocument = new BsonDocument("aggregate", new BsonString(this.namespace.getCollectionName()));
        commandDocument.put("pipeline", (BsonValue) new BsonArray(this.pipeline));
        commandDocument.put("explain", (BsonValue) BsonBoolean.TRUE);
        if (this.maxTimeMS > 0) {
            commandDocument.put("maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        if (this.allowDiskUse != null) {
            commandDocument.put("allowDiskUse", (BsonValue) BsonBoolean.valueOf(this.allowDiskUse.booleanValue()));
        }
        if (this.collation != null) {
            commandDocument.put("collation", (BsonValue) this.collation.asDocument());
        }
        if (this.hint != null) {
            commandDocument.put("hint", this.hint);
        }
        return commandDocument;
    }
}
