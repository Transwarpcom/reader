package com.mongodb.operation;

import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;
import com.mongodb.binding.WriteBinding;
import org.bson.BsonDocument;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/CommandWriteOperation.class */
public class CommandWriteOperation<T> implements AsyncWriteOperation<T>, WriteOperation<T> {
    private final String databaseName;
    private final BsonDocument command;
    private final Decoder<T> decoder;

    public CommandWriteOperation(String databaseName, BsonDocument command, Decoder<T> decoder) {
        this.databaseName = (String) Assertions.notNull("databaseName", databaseName);
        this.command = (BsonDocument) Assertions.notNull("command", command);
        this.decoder = (Decoder) Assertions.notNull("decoder", decoder);
    }

    @Override // com.mongodb.operation.WriteOperation
    public T execute(WriteBinding writeBinding) {
        return (T) CommandOperationHelper.executeWrappedCommandProtocol(writeBinding, this.databaseName, this.command, this.decoder);
    }

    @Override // com.mongodb.operation.AsyncWriteOperation
    public void executeAsync(AsyncWriteBinding binding, SingleResultCallback<T> callback) {
        CommandOperationHelper.executeWrappedCommandProtocolAsync(binding, this.databaseName, this.command, this.decoder, callback);
    }
}
