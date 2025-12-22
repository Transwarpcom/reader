package com.mongodb.operation;

import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.Connection;
import com.mongodb.internal.async.ErrorHandlingResultCallback;
import com.mongodb.operation.CommandOperationHelper;
import com.mongodb.operation.OperationHelper;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.codecs.BsonDocumentCodec;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/UserExistsOperation.class */
public class UserExistsOperation implements AsyncReadOperation<Boolean>, ReadOperation<Boolean> {
    private final String databaseName;
    private final String userName;

    public UserExistsOperation(String databaseName, String userName) {
        this.databaseName = (String) Assertions.notNull("databaseName", databaseName);
        this.userName = (String) Assertions.notNull("userName", userName);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.operation.ReadOperation
    public Boolean execute(final ReadBinding binding) {
        return (Boolean) OperationHelper.withConnection(binding, new OperationHelper.CallableWithConnection<Boolean>() { // from class: com.mongodb.operation.UserExistsOperation.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.mongodb.operation.OperationHelper.CallableWithConnection
            public Boolean call(Connection connection) {
                return (Boolean) CommandOperationHelper.executeWrappedCommandProtocol(binding, UserExistsOperation.this.databaseName, UserExistsOperation.this.getCommand(), connection, UserExistsOperation.this.transformer());
            }
        });
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(final AsyncReadBinding binding, final SingleResultCallback<Boolean> callback) {
        OperationHelper.withConnection(binding, new OperationHelper.AsyncCallableWithConnection() { // from class: com.mongodb.operation.UserExistsOperation.2
            @Override // com.mongodb.operation.OperationHelper.AsyncCallableWithConnection
            public void call(AsyncConnection connection, Throwable t) {
                SingleResultCallback<Boolean> errHandlingCallback = ErrorHandlingResultCallback.errorHandlingCallback(callback, OperationHelper.LOGGER);
                if (t != null) {
                    errHandlingCallback.onResult(null, t);
                } else {
                    SingleResultCallback<Boolean> wrappedCallback = OperationHelper.releasingCallback(errHandlingCallback, connection);
                    CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, UserExistsOperation.this.databaseName, UserExistsOperation.this.getCommand(), new BsonDocumentCodec(), connection, UserExistsOperation.this.transformer(), wrappedCallback);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandOperationHelper.CommandTransformer<BsonDocument, Boolean> transformer() {
        return new CommandOperationHelper.CommandTransformer<BsonDocument, Boolean>() { // from class: com.mongodb.operation.UserExistsOperation.3
            @Override // com.mongodb.operation.CommandOperationHelper.CommandTransformer
            public Boolean apply(BsonDocument result, ServerAddress serverAddress) {
                return Boolean.valueOf(result.get("users").isArray() && !result.getArray("users").isEmpty());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BsonDocument getCommand() {
        return new BsonDocument("usersInfo", new BsonString(this.userName));
    }
}
