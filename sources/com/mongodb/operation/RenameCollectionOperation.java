package com.mongodb.operation;

import com.mongodb.MongoNamespace;
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
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonString;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/RenameCollectionOperation.class */
public class RenameCollectionOperation implements AsyncWriteOperation<Void>, WriteOperation<Void> {
    private final MongoNamespace originalNamespace;
    private final MongoNamespace newNamespace;
    private final WriteConcern writeConcern;
    private boolean dropTarget;

    @Deprecated
    public RenameCollectionOperation(MongoNamespace originalNamespace, MongoNamespace newNamespace) {
        this(originalNamespace, newNamespace, null);
    }

    public RenameCollectionOperation(MongoNamespace originalNamespace, MongoNamespace newNamespace, WriteConcern writeConcern) {
        this.originalNamespace = (MongoNamespace) Assertions.notNull("originalNamespace", originalNamespace);
        this.newNamespace = (MongoNamespace) Assertions.notNull("newNamespace", newNamespace);
        this.writeConcern = writeConcern;
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public boolean isDropTarget() {
        return this.dropTarget;
    }

    public RenameCollectionOperation dropTarget(boolean dropTarget) {
        this.dropTarget = dropTarget;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.WriteOperation
    public Void execute(final WriteBinding binding) {
        return (Void) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<Void>() { // from class: com.mongodb.operation.RenameCollectionOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public Void call(Connection connection) {
                return (Void) CommandOperationHelper.executeWrappedCommandProtocol(binding, "admin", RenameCollectionOperation.this.getCommand(connection.getDescription()), connection, CommandOperationHelper.writeConcernErrorTransformer());
            }
        });
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(final AsyncWriteBinding binding, final SingleResultCallback<Void> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.RenameCollectionOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<Void> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, "admin", RenameCollectionOperation.this.getCommand(connection.getDescription()), connection, CommandOperationHelper.writeConcernErrorTransformer(), OperationHelper.releasingCallback(errHandlingCallback, connection));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand(ConnectionDescription description) {
        BsonDocument commandDocument = new BsonDocument("renameCollection", new BsonString(this.originalNamespace.getFullName())).append("to", new BsonString(this.newNamespace.getFullName())).append("dropTarget", BsonBoolean.valueOf(this.dropTarget));
        WriteConcernHelper.appendWriteConcernToCommand(this.writeConcern, commandDocument, description);
        return commandDocument;
    }
}
