package com.mongodb.operation;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoCredential;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.connection.ConnectionDescription;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.internal.operation.WriteConcernHelper;
import com.mongodb.operation.OperationHelper;
import org.bson.BsonDocument;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/UpdateUserOperation.class */
public class UpdateUserOperation implements AsyncWriteOperation<Void>, WriteOperation<Void> {
    private final MongoCredential credential;
    private final boolean readOnly;
    private final WriteConcern writeConcern;

    public UpdateUserOperation(MongoCredential credential, boolean readOnly) {
        this(credential, readOnly, null);
    }

    public UpdateUserOperation(MongoCredential credential, boolean readOnly, WriteConcern writeConcern) {
        this.credential = (MongoCredential) Assertions.notNull("credential", credential);
        this.readOnly = readOnly;
        this.writeConcern = writeConcern;
    }

    public MongoCredential getCredential() {
        return this.credential;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    public Void execute(final WriteBinding binding) {
        return (Void) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<Void>() { // from class: com.mongodb.operation.UpdateUserOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public Void call(Connection connection) {
                try {
                    CommandOperationHelper.executeWrappedCommandProtocol(binding, UpdateUserOperation.this.getCredential().getSource(), UpdateUserOperation.this.getCommand(connection.getDescription()), connection, CommandOperationHelper.writeConcernErrorTransformer());
                    return null;
                } catch (MongoCommandException e) {
                    UserOperationHelper.translateUserCommandException(e);
                    return null;
                }
            }
        });
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(final AsyncWriteBinding binding, final SingleResultCallback<Void> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.UpdateUserOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<Void> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    SingleResultCallback<Void> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, UpdateUserOperation.this.credential.getSource(), UpdateUserOperation.this.getCommand(connection.getDescription()), connection, CommandOperationHelper.writeConcernErrorTransformer(), UserOperationHelper.userCommandCallback(wrappedCallback));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(ConnectionDescription description) {
        BsonDocument commandDocument = UserOperationHelper.asCommandDocument(this.credential, description, this.readOnly, "updateUser");
        WriteConcernHelper.appendWriteConcernToCommand(this.writeConcern, commandDocument, description);
        return commandDocument;
    }
}
