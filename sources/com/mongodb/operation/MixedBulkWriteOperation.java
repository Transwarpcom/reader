package com.mongodb.operation;

import com.mongodb.MongoException;
import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.WriteConcernResult;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncConnectionSource;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.ConnectionSource;
import com.mongodb.binding.WriteBinding;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.bulk.InsertRequest;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.connection.MongoWriteConcernWithResponseException;
import com.mongodb.internal.connection.ProtocolHelper;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.operation.OperationHelper;
import java.util.List;
import org.bson.BsonDocument;
import org.bson.FieldNameValidator;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/MixedBulkWriteOperation.class */
public class MixedBulkWriteOperation implements AsyncWriteOperation<BulkWriteResult>, WriteOperation<BulkWriteResult> {
    private static final FieldNameValidator NO_OP_FIELD_NAME_VALIDATOR = new NoOpFieldNameValidator();
    private final MongoNamespace namespace;
    private final List<? extends WriteRequest> writeRequests;
    private final boolean ordered;
    private final boolean retryWrites;
    private final WriteConcern writeConcern;
    private Boolean bypassDocumentValidation;

    @Deprecated
    public MixedBulkWriteOperation(MongoNamespace namespace, List<? extends WriteRequest> writeRequests, boolean ordered, WriteConcern writeConcern) {
        this(namespace, writeRequests, ordered, writeConcern, false);
    }

    public MixedBulkWriteOperation(MongoNamespace namespace, List<? extends WriteRequest> writeRequests, boolean ordered, WriteConcern writeConcern, boolean retryWrites) {
        this.ordered = ordered;
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.writeRequests = (List) Assertions.notNull("writes", writeRequests);
        this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
        this.retryWrites = retryWrites;
        Assertions.isTrueArgument("writes is not an empty list", !writeRequests.isEmpty());
    }

    public MongoNamespace getNamespace() {
        return this.namespace;
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public boolean isOrdered() {
        return this.ordered;
    }

    public List<? extends WriteRequest> getWriteRequests() {
        return this.writeRequests;
    }

    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public MixedBulkWriteOperation bypassDocumentValidation(Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    public Boolean getRetryWrites() {
        return Boolean.valueOf(this.retryWrites);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    public BulkWriteResult execute(final WriteBinding binding) {
        return (BulkWriteResult) OperationHelper.withReleasableConnection(binding, new OperationHelper.CallableWithConnectionAndSource<BulkWriteResult>() { // from class: com.mongodb.operation.MixedBulkWriteOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BulkWriteResult call(ConnectionSource connectionSource, Connection connection) {
                MixedBulkWriteOperation.this.validateWriteRequestsAndReleaseConnectionIfError(connection);
                if (!MixedBulkWriteOperation.this.getWriteConcern().isAcknowledged() && !ServerVersionHelper.serverIsAtLeastVersionThreeDotSix(connection.getDescription())) {
                    return MixedBulkWriteOperation.this.executeLegacyBatches(connection);
                }
                BulkWriteBatch bulkWriteBatch = BulkWriteBatch.createBulkWriteBatch(MixedBulkWriteOperation.this.namespace, connectionSource.getServerDescription(), connection.getDescription(), MixedBulkWriteOperation.this.ordered, MixedBulkWriteOperation.this.writeConcern, MixedBulkWriteOperation.this.bypassDocumentValidation, MixedBulkWriteOperation.this.retryWrites, MixedBulkWriteOperation.this.writeRequests, binding.getSessionContext());
                return MixedBulkWriteOperation.this.executeBulkWriteBatch(binding, connection, bulkWriteBatch);
            }
        });
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(final AsyncWriteBinding binding, SingleResultCallback<BulkWriteResult> callback) {
        final SingleResultCallback<BulkWriteResult> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.MixedBulkWriteOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(final AsyncConnectionSource source, AsyncConnection connection, Throwable t) {
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    OperationHelper.validateWriteRequests(connection, MixedBulkWriteOperation.this.bypassDocumentValidation, MixedBulkWriteOperation.this.writeRequests, MixedBulkWriteOperation.this.writeConcern, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.MixedBulkWriteOperation.2.1
                        @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
                        public void call(AsyncConnection connection2, Throwable t1) {
                            OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> releasingCallback = new OperationHelper.ConnectionReleasingWrappedCallback<>(errHandlingCallback, source, connection2);
                            if (t1 == null) {
                                if (!MixedBulkWriteOperation.this.writeConcern.isAcknowledged() && !ServerVersionHelper.serverIsAtLeastVersionThreeDotSix(connection2.getDescription())) {
                                    MixedBulkWriteOperation.this.executeLegacyBatchesAsync(connection2, MixedBulkWriteOperation.this.getWriteRequests(), 1, releasingCallback);
                                    return;
                                }
                                try {
                                    BulkWriteBatch batch = BulkWriteBatch.createBulkWriteBatch(MixedBulkWriteOperation.this.namespace, source.getServerDescription(), connection2.getDescription(), MixedBulkWriteOperation.this.ordered, MixedBulkWriteOperation.this.writeConcern, MixedBulkWriteOperation.this.bypassDocumentValidation, MixedBulkWriteOperation.this.retryWrites, MixedBulkWriteOperation.this.writeRequests, binding.getSessionContext());
                                    MixedBulkWriteOperation.this.executeBatchesAsync(binding, connection2, batch, MixedBulkWriteOperation.this.retryWrites, releasingCallback);
                                    return;
                                } catch (Throwable t2) {
                                    releasingCallback.onResult(null, t2);
                                    return;
                                }
                            }
                            releasingCallback.onResult(null, t1);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BulkWriteResult executeBulkWriteBatch(WriteBinding binding, Connection connection, BulkWriteBatch originalBatch) {
        MongoException writeConcernBasedError;
        BulkWriteBatch currentBatch = originalBatch;
        MongoException exception = null;
        while (currentBatch.shouldProcessBatch()) {
            try {
                BsonDocument result = executeCommand(connection, currentBatch, binding);
                if (this.retryWrites && !binding.getSessionContext().hasActiveTransaction() && (writeConcernBasedError = ProtocolHelper.createSpecialException(result, connection.getDescription().getServerAddress(), "errMsg")) != null && CommandOperationHelper.shouldAttemptToRetry(true, (Throwable) writeConcernBasedError)) {
                    throw new MongoWriteConcernWithResponseException(writeConcernBasedError, result);
                }
                currentBatch.addResult(result);
                currentBatch = currentBatch.getNextBatch();
            } catch (MongoException e) {
                exception = e;
                connection.release();
            } catch (Throwable th) {
                connection.release();
                throw th;
            }
        }
        connection.release();
        if (exception == null) {
            return currentBatch.getResult();
        }
        if (!(exception instanceof MongoWriteConcernWithResponseException) && !CommandOperationHelper.shouldAttemptToRetry(originalBatch.getRetryWrites(), exception)) {
            throw exception;
        }
        return retryExecuteBatches(binding, currentBatch, exception);
    }

    private BulkWriteResult retryExecuteBatches(final WriteBinding binding, final BulkWriteBatch retryBatch, final MongoException originalError) {
        return (BulkWriteResult) OperationHelper.withReleasableConnection(binding, originalError, new OperationHelper.CallableWithConnectionAndSource<BulkWriteResult>() { // from class: com.mongodb.operation.MixedBulkWriteOperation.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnectionAndSource
            public BulkWriteResult call(ConnectionSource source, Connection connection) {
                if (OperationHelper.isRetryableWrite(MixedBulkWriteOperation.this.retryWrites, MixedBulkWriteOperation.this.writeConcern, source.getServerDescription(), connection.getDescription(), binding.getSessionContext())) {
                    try {
                        retryBatch.addResult(MixedBulkWriteOperation.this.executeCommand(connection, retryBatch, binding));
                        return MixedBulkWriteOperation.this.executeBulkWriteBatch(binding, connection, retryBatch.getNextBatch());
                    } catch (Throwable th) {
                        return checkMongoWriteConcernWithResponseException(connection);
                    }
                }
                return checkMongoWriteConcernWithResponseException(connection);
            }

            private BulkWriteResult checkMongoWriteConcernWithResponseException(Connection connection) {
                if (originalError instanceof MongoWriteConcernWithResponseException) {
                    retryBatch.addResult((BsonDocument) ((MongoWriteConcernWithResponseException) originalError).getResponse());
                    return MixedBulkWriteOperation.this.executeBulkWriteBatch(binding, connection, retryBatch.getNextBatch());
                }
                connection.release();
                throw originalError;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BulkWriteResult executeLegacyBatches(Connection connection) {
        try {
            for (WriteRequest writeRequest : getWriteRequests()) {
                if (writeRequest.getType() == WriteRequest.Type.INSERT) {
                    connection.insert(getNamespace(), isOrdered(), (InsertRequest) writeRequest);
                } else if (writeRequest.getType() == WriteRequest.Type.UPDATE || writeRequest.getType() == WriteRequest.Type.REPLACE) {
                    connection.update(getNamespace(), isOrdered(), (UpdateRequest) writeRequest);
                } else {
                    connection.delete(getNamespace(), isOrdered(), (DeleteRequest) writeRequest);
                }
            }
            BulkWriteResult bulkWriteResultUnacknowledged = BulkWriteResult.unacknowledged();
            connection.release();
            return bulkWriteResultUnacknowledged;
        } catch (Throwable th) {
            connection.release();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeBatchesAsync(AsyncWriteBinding binding, AsyncConnection connection, BulkWriteBatch batch, boolean retryWrites, OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> callback) {
        executeCommandAsync(binding, connection, batch, callback, getCommandCallback(binding, connection, batch, retryWrites, false, callback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryExecuteBatchesAsync(final AsyncWriteBinding binding, final BulkWriteBatch retryBatch, final Throwable originalError, final SingleResultCallback<BulkWriteResult> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnectionAndSource() { // from class: com.mongodb.operation.MixedBulkWriteOperation.4
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnectionAndSource
            public void call(AsyncConnectionSource source, final AsyncConnection connection, Throwable t) {
                if (t != null) {
                    callback.onResult(null, originalError);
                    return;
                }
                final OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> releasingCallback = new OperationHelper.ConnectionReleasingWrappedCallback<>(callback, source, connection);
                if (OperationHelper.isRetryableWrite(MixedBulkWriteOperation.this.retryWrites, MixedBulkWriteOperation.this.writeConcern, source.getServerDescription(), connection.getDescription(), binding.getSessionContext())) {
                    MixedBulkWriteOperation.this.executeCommandAsync(binding, connection, retryBatch, releasingCallback, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.operation.MixedBulkWriteOperation.4.1
                        @Override // com.mongodb.async.SingleResultCallback
                        public void onResult(BsonDocument result, Throwable t2) {
                            if (t2 != null) {
                                checkMongoWriteConcernWithResponseException(connection, releasingCallback);
                            } else {
                                MixedBulkWriteOperation.this.getCommandCallback(binding, connection, retryBatch, true, true, releasingCallback).onResult(result, null);
                            }
                        }
                    });
                } else {
                    checkMongoWriteConcernWithResponseException(connection, releasingCallback);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void checkMongoWriteConcernWithResponseException(AsyncConnection connection, OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> releasingCallback) {
                if (originalError instanceof MongoWriteConcernWithResponseException) {
                    retryBatch.addResult((BsonDocument) ((MongoWriteConcernWithResponseException) originalError).getResponse());
                    BulkWriteBatch nextBatch = retryBatch.getNextBatch();
                    MixedBulkWriteOperation.this.executeCommandAsync(binding, connection, nextBatch, releasingCallback, MixedBulkWriteOperation.this.getCommandCallback(binding, connection, nextBatch, true, true, releasingCallback));
                    return;
                }
                releasingCallback.onResult(null, originalError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeLegacyBatchesAsync(final AsyncConnection connection, List<? extends WriteRequest> writeRequests, final int batchNum, final SingleResultCallback<BulkWriteResult> callback) {
        try {
            if (!writeRequests.isEmpty()) {
                WriteRequest writeRequest = writeRequests.get(0);
                final List<? extends WriteRequest> remaining = writeRequests.subList(1, writeRequests.size());
                SingleResultCallback<WriteConcernResult> writeCallback = new SingleResultCallback<WriteConcernResult>() { // from class: com.mongodb.operation.MixedBulkWriteOperation.5
                    @Override // com.mongodb.async.SingleResultCallback
                    public void onResult(WriteConcernResult result, Throwable t) {
                        if (t == null) {
                            MixedBulkWriteOperation.this.executeLegacyBatchesAsync(connection, remaining, batchNum + 1, callback);
                        } else {
                            callback.onResult(null, t);
                        }
                    }
                };
                if (writeRequest.getType() == WriteRequest.Type.INSERT) {
                    connection.insertAsync(getNamespace(), isOrdered(), (InsertRequest) writeRequest, writeCallback);
                } else if (writeRequest.getType() == WriteRequest.Type.UPDATE || writeRequest.getType() == WriteRequest.Type.REPLACE) {
                    connection.updateAsync(getNamespace(), isOrdered(), (UpdateRequest) writeRequest, writeCallback);
                } else {
                    connection.deleteAsync(getNamespace(), isOrdered(), (DeleteRequest) writeRequest, writeCallback);
                }
            } else {
                callback.onResult(BulkWriteResult.unacknowledged(), null);
            }
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument executeCommand(Connection connection, BulkWriteBatch batch, WriteBinding binding) {
        return (BsonDocument) connection.command(this.namespace.getDatabaseName(), batch.getCommand(), NO_OP_FIELD_NAME_VALIDATOR, null, batch.getDecoder(), binding.getSessionContext(), shouldAcknowledge(batch, this.writeConcern), batch.getPayload(), batch.getFieldNameValidator());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeCommandAsync(AsyncWriteBinding binding, AsyncConnection connection, BulkWriteBatch batch, OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> callback, SingleResultCallback<BsonDocument> commandCallback) {
        try {
            connection.commandAsync(this.namespace.getDatabaseName(), batch.getCommand(), NO_OP_FIELD_NAME_VALIDATOR, null, batch.getDecoder(), binding.getSessionContext(), shouldAcknowledge(batch, this.writeConcern), batch.getPayload(), batch.getFieldNameValidator(), commandCallback);
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    private boolean shouldAcknowledge(BulkWriteBatch batch, WriteConcern writeConcern) {
        return this.ordered ? batch.hasAnotherBatch() || writeConcern.isAcknowledged() : writeConcern.isAcknowledged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SingleResultCallback<BsonDocument> getCommandCallback(final AsyncWriteBinding binding, final AsyncConnection connection, final BulkWriteBatch batch, final boolean retryWrites, final boolean isSecondAttempt, final OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> callback) {
        return new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.operation.MixedBulkWriteOperation.6
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(BsonDocument result, Throwable t) {
                MongoException writeConcernBasedError;
                if (t != null) {
                    if (!isSecondAttempt && CommandOperationHelper.shouldAttemptToRetry(retryWrites, t)) {
                        MixedBulkWriteOperation.this.retryExecuteBatchesAsync(binding, batch, t, callback.releaseConnectionAndGetWrapped());
                        return;
                    } else if (t instanceof MongoWriteConcernWithResponseException) {
                        MixedBulkWriteOperation.this.addBatchResult((BsonDocument) ((MongoWriteConcernWithResponseException) t).getResponse(), binding, connection, batch, retryWrites, callback);
                        return;
                    } else {
                        callback.onResult(null, t);
                        return;
                    }
                }
                if (!retryWrites || isSecondAttempt || (writeConcernBasedError = ProtocolHelper.createSpecialException(result, connection.getDescription().getServerAddress(), "errMsg")) == null || !CommandOperationHelper.shouldAttemptToRetry(true, (Throwable) writeConcernBasedError)) {
                    MixedBulkWriteOperation.this.addBatchResult(result, binding, connection, batch, retryWrites, callback);
                } else {
                    MixedBulkWriteOperation.this.retryExecuteBatchesAsync(binding, batch, new MongoWriteConcernWithResponseException(writeConcernBasedError, result), callback.releaseConnectionAndGetWrapped());
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBatchResult(BsonDocument result, AsyncWriteBinding binding, AsyncConnection connection, BulkWriteBatch batch, boolean retryWrites, OperationHelper.ConnectionReleasingWrappedCallback<BulkWriteResult> callback) {
        batch.addResult(result);
        BulkWriteBatch nextBatch = batch.getNextBatch();
        if (nextBatch.shouldProcessBatch()) {
            executeBatchesAsync(binding, connection, nextBatch, retryWrites, callback);
        } else if (batch.hasErrors()) {
            callback.onResult(null, batch.getError());
        } else {
            callback.onResult(batch.getResult(), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateWriteRequestsAndReleaseConnectionIfError(Connection connection) {
        try {
            OperationHelper.validateWriteRequests(connection.getDescription(), this.bypassDocumentValidation, this.writeRequests, this.writeConcern);
        } catch (MongoException e) {
            connection.release();
            throw e;
        } catch (IllegalArgumentException e2) {
            connection.release();
            throw e2;
        } catch (Throwable t) {
            connection.release();
            throw MongoException.fromThrowableNonNull(t);
        }
    }
}
