package com.mongodb.operation;

import com.mongodb.ExplainVerbosity;
import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.client.model.Collation;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.internal.operation.WriteConcernHelper;
import com.mongodb.operation.OperationHelper;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AggregateToCollectionOperation.class */
public class AggregateToCollectionOperation implements AsyncWriteOperation<Void>, WriteOperation<Void> {
    private final MongoNamespace namespace;
    private final List<BsonDocument> pipeline;
    private final WriteConcern writeConcern;
    private Boolean allowDiskUse;
    private long maxTimeMS;
    private Boolean bypassDocumentValidation;
    private Collation collation;
    private String comment;
    private BsonDocument hint;

    @Deprecated
    public AggregateToCollectionOperation(MongoNamespace namespace, List<BsonDocument> pipeline) {
        this(namespace, pipeline, null);
    }

    public AggregateToCollectionOperation(MongoNamespace namespace, List<BsonDocument> pipeline, WriteConcern writeConcern) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.pipeline = (List) Assertions.notNull("pipeline", pipeline);
        this.writeConcern = writeConcern;
        Assertions.isTrueArgument("pipeline is not empty", !pipeline.isEmpty());
        Assertions.isTrueArgument("last stage of pipeline contains an output collection", pipeline.get(pipeline.size() - 1).get((Object) "$out") != null);
    }

    public List<BsonDocument> getPipeline() {
        return this.pipeline;
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public Boolean getAllowDiskUse() {
        return this.allowDiskUse;
    }

    public AggregateToCollectionOperation allowDiskUse(Boolean allowDiskUse) {
        this.allowDiskUse = allowDiskUse;
        return this;
    }

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public AggregateToCollectionOperation maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public AggregateToCollectionOperation bypassDocumentValidation(Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public AggregateToCollectionOperation collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public AggregateToCollectionOperation comment(String comment) {
        this.comment = comment;
        return this;
    }

    public BsonDocument getHint() {
        return this.hint;
    }

    public AggregateToCollectionOperation hint(BsonDocument hint) {
        this.hint = hint;
        return this;
    }

    public ReadOperation<BsonDocument> asExplainableOperation(ExplainVerbosity explainVerbosity) {
        return new AggregateExplainOperation(this.namespace, this.pipeline).allowDiskUse(this.allowDiskUse).maxTime(this.maxTimeMS, TimeUnit.MILLISECONDS).hint(this.hint);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    public Void execute(final WriteBinding binding) {
        return (Void) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<Void>() { // from class: com.mongodb.operation.AggregateToCollectionOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public Void call(Connection connection) {
                OperationHelper.validateCollation(connection, AggregateToCollectionOperation.this.collation);
                return (Void) CommandOperationHelper.executeWrappedCommandProtocol(binding, AggregateToCollectionOperation.this.namespace.getDatabaseName(), AggregateToCollectionOperation.this.getCommand(connection.getDescription()), connection, CommandOperationHelper.writeConcernErrorTransformer());
            }
        });
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(final AsyncWriteBinding binding, final SingleResultCallback<Void> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.AggregateToCollectionOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<Void> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    final SingleResultCallback<Void> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    OperationHelper.validateCollation(connection, AggregateToCollectionOperation.this.collation, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.AggregateToCollectionOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                        public void call(AsyncConnection connection2, Throwable t2) {
                            if (t2 != null) {
                                wrappedCallback.onResult(null, t2);
                            } else {
                                CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, AggregateToCollectionOperation.this.namespace.getDatabaseName(), AggregateToCollectionOperation.this.getCommand(connection2.getDescription()), connection2, CommandOperationHelper.writeConcernErrorTransformer(), wrappedCallback);
                            }
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(ConnectionDescription description) {
        BsonDocument commandDocument = new BsonDocument("aggregate", new BsonString(this.namespace.getCollectionName()));
        commandDocument.put("pipeline", (BsonValue) new BsonArray(this.pipeline));
        if (this.maxTimeMS > 0) {
            commandDocument.put("maxTimeMS", (BsonValue) new BsonInt64(this.maxTimeMS));
        }
        if (this.allowDiskUse != null) {
            commandDocument.put("allowDiskUse", (BsonValue) BsonBoolean.valueOf(this.allowDiskUse.booleanValue()));
        }
        if (this.bypassDocumentValidation != null && ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(description)) {
            commandDocument.put("bypassDocumentValidation", (BsonValue) BsonBoolean.valueOf(this.bypassDocumentValidation.booleanValue()));
        }
        if (ServerVersionHelper.serverIsAtLeastVersionThreeDotSix(description)) {
            commandDocument.put("cursor", (BsonValue) new BsonDocument());
        }
        WriteConcernHelper.appendWriteConcernToCommand(this.writeConcern, commandDocument, description);
        if (this.collation != null) {
            commandDocument.put("collation", (BsonValue) this.collation.asDocument());
        }
        if (this.comment != null) {
            commandDocument.put("comment", (BsonValue) new BsonString(this.comment));
        }
        if (this.hint != null) {
            commandDocument.put("hint", (BsonValue) this.hint);
        }
        return commandDocument;
    }
}
