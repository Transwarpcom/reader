package com.mongodb.operation;

import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.connection.ServerDescription;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.operation.CommandOperationHelper;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/TransactionOperation.class */
public abstract class TransactionOperation implements WriteOperation<Void>, AsyncWriteOperation<Void> {
    private final WriteConcern writeConcern;

    protected abstract String getCommandName();

    protected TransactionOperation(WriteConcern writeConcern) {
        this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    public Void execute(WriteBinding binding) {
        Assertions.isTrue("in transaction", binding.getSessionContext().hasActiveTransaction());
        return (Void) CommandOperationHelper.executeRetryableCommand(binding, "admin", null, new NoOpFieldNameValidator(), new BsonDocumentCodec(), getCommandCreator(), CommandOperationHelper.writeConcernErrorTransformer());
    }

    public void executeAsync(AsyncWriteBinding binding, SingleResultCallback<Void> callback) {
        Assertions.isTrue("in transaction", binding.getSessionContext().hasActiveTransaction());
        CommandOperationHelper.executeRetryableCommand(binding, "admin", null, new NoOpFieldNameValidator(), new BsonDocumentCodec(), getCommandCreator(), CommandOperationHelper.writeConcernErrorTransformer(), ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER));
    }

    private CommandOperationHelper.CommandCreator getCommandCreator() {
        return new CommandOperationHelper.CommandCreator() { // from class: com.mongodb.operation.TransactionOperation.1
            @Override // com.mongodb.operation.CommandOperationHelper.CommandCreator
            public BsonDocument create(ServerDescription serverDescription, ConnectionDescription connectionDescription) {
                return TransactionOperation.this.getCommand();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand() {
        BsonDocument command = new BsonDocument(getCommandName(), new BsonInt32(1));
        if (!getWriteConcern().isServerDefault()) {
            command.put("writeConcern", (BsonValue) getWriteConcern().asDocument());
        }
        return command;
    }
}
