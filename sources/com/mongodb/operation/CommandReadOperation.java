package com.mongodb.operation;

import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;
import com.mongodb.binding.ReadBinding;
import org.bson.BsonDocument;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandReadOperation.class */
public class CommandReadOperation<T> implements AsyncReadOperation<T>, ReadOperation<T> {
    private final String databaseName;
    private final BsonDocument command;
    private final Decoder<T> decoder;

    public CommandReadOperation(String databaseName, BsonDocument command, Decoder<T> decoder) {
        this.databaseName = (String) Assertions.notNull("databaseName", databaseName);
        this.command = (BsonDocument) Assertions.notNull("command", command);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    @Override // com.mongodb.operation.ReadOperation
    public T execute(ReadBinding readBinding) {
        return (T) CommandOperationHelper.executeWrappedCommandProtocol(readBinding, this.databaseName, this.command, this.decoder);
    }

    @Override // com.mongodb.operation.AsyncReadOperation
    public void executeAsync(AsyncReadBinding binding, SingleResultCallback<T> callback) {
        CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, this.databaseName, this.command, this.decoder, callback);
    }
}
