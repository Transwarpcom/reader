package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.operation.ServerVersionHelper;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.session.SessionContext;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonValue;
import org.bson.FieldNameValidator;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/BaseFindAndModifyOperation.class */
public abstract class BaseFindAndModifyOperation<T> implements AsyncWriteOperation<T>, WriteOperation<T> {
    private final MongoNamespace namespace;
    private final WriteConcern writeConcern;
    private final boolean retryWrites;
    private final Decoder<T> decoder;

    protected abstract String getDatabaseName();

    protected abstract CommandOperationHelper.CommandCreator getCommandCreator(SessionContext sessionContext);

    protected abstract FieldNameValidator getFieldNameValidator();

    protected BaseFindAndModifyOperation(MongoNamespace namespace, WriteConcern writeConcern, boolean retryWrites, Decoder<T> decoder) {
        this.namespace = (MongoNamespace) Assertions.notNull("namespace", namespace);
        this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
        this.retryWrites = retryWrites;
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    @Override // com.mongodb.operation.WriteOperation
    public T execute(WriteBinding writeBinding) {
        return (T) CommandOperationHelper.executeRetryableCommand(writeBinding, getDatabaseName(), null, getFieldNameValidator(), CommandResultDocumentCodec.create(getDecoder(), "value"), getCommandCreator(writeBinding.getSessionContext()), FindAndModifyHelper.transformer());
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(AsyncWriteBinding binding, SingleResultCallback<T> callback) {
        CommandOperationHelper.executeRetryableCommand(binding, getDatabaseName(), null, getFieldNameValidator(), CommandResultDocumentCodec.create(getDecoder(), "value"), getCommandCreator(binding.getSessionContext()), FindAndModifyHelper.transformer(), callback);
    }

    public MongoNamespace getNamespace() {
        return this.namespace;
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public Decoder<T> getDecoder() {
        return this.decoder;
    }

    public boolean isRetryWrites() {
        return this.retryWrites;
    }

    protected void addTxnNumberToCommand(ServerDescription serverDescription, ConnectionDescription connectionDescription, BsonDocument commandDocument, SessionContext sessionContext) {
        if (OperationHelper.isRetryableWrite(isRetryWrites(), getWriteConcern(), serverDescription, connectionDescription, sessionContext)) {
            commandDocument.put("txnNumber", (BsonValue) new BsonInt64(sessionContext.advanceTransactionNumber()));
        }
    }

    protected void addWriteConcernToCommand(ConnectionDescription connectionDescription, BsonDocument commandDocument, SessionContext sessionContext) {
        if (getWriteConcern().isAcknowledged() && !getWriteConcern().isServerDefault() && ServerVersionHelper.serverIsAtLeastVersionThreeDotTwo(connectionDescription) && !sessionContext.hasActiveTransaction()) {
            commandDocument.put("writeConcern", (BsonValue) getWriteConcern().asDocument());
        }
    }
}
