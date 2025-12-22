package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.SplittablePayload;
import com.mongodb.session.SessionContext;
import org.bson.BsonDocument;
import org.bson.FieldNameValidator;
import org.bson.codecs.Decoder;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandProtocolImpl.class */
class CommandProtocolImpl<T> implements CommandProtocol<T> {
    private final MongoNamespace namespace;
    private final BsonDocument command;
    private final SplittablePayload payload;
    private final ReadPreference readPreference;
    private final FieldNameValidator commandFieldNameValidator;
    private final FieldNameValidator payloadFieldNameValidator;
    private final Decoder<T> commandResultDecoder;
    private final boolean responseExpected;
    private final ClusterConnectionMode clusterConnectionMode;
    private SessionContext sessionContext;

    CommandProtocolImpl(String database, BsonDocument command, FieldNameValidator commandFieldNameValidator, ReadPreference readPreference, Decoder<T> commandResultDecoder) {
        this(database, command, commandFieldNameValidator, readPreference, commandResultDecoder, true, null, null, ClusterConnectionMode.MULTIPLE);
    }

    CommandProtocolImpl(String database, BsonDocument command, FieldNameValidator commandFieldNameValidator, ReadPreference readPreference, Decoder<T> commandResultDecoder, boolean responseExpected, SplittablePayload payload, FieldNameValidator payloadFieldNameValidator, ClusterConnectionMode clusterConnectionMode) {
        Assertions.notNull("database", database);
        this.namespace = new MongoNamespace((String) Assertions.notNull("database", database), MongoNamespace.COMMAND_COLLECTION_NAME);
        this.command = (BsonDocument) Assertions.notNull("command", command);
        this.commandFieldNameValidator = (FieldNameValidator) Assertions.notNull("commandFieldNameValidator", commandFieldNameValidator);
        this.readPreference = readPreference;
        this.commandResultDecoder = (Decoder) Assertions.notNull("commandResultDecoder", commandResultDecoder);
        this.responseExpected = responseExpected;
        this.payload = payload;
        this.payloadFieldNameValidator = payloadFieldNameValidator;
        this.clusterConnectionMode = (ClusterConnectionMode) Assertions.notNull("clusterConnectionMode", clusterConnectionMode);
        Assertions.isTrueArgument("payloadFieldNameValidator cannot be null if there is a payload.", payload == null || payloadFieldNameValidator != null);
    }

    @Override // com.mongodb.internal.connection.CommandProtocol
    public T execute(InternalConnection internalConnection) {
        return (T) internalConnection.sendAndReceive(getCommandMessage(internalConnection), this.commandResultDecoder, this.sessionContext);
    }

    @Override // com.mongodb.internal.connection.CommandProtocol
    public void executeAsync(InternalConnection connection, final SingleResultCallback<T> callback) {
        try {
            connection.sendAndReceiveAsync(getCommandMessage(connection), this.commandResultDecoder, this.sessionContext, new SingleResultCallback<T>() { // from class: com.mongodb.internal.connection.CommandProtocolImpl.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(T result, Throwable t) {
                    if (t != null) {
                        callback.onResult(null, t);
                    } else {
                        callback.onResult(result, null);
                    }
                }
            });
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    @Override // com.mongodb.internal.connection.CommandProtocol
    public CommandProtocolImpl<T> sessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
        return this;
    }

    private CommandMessage getCommandMessage(InternalConnection connection) {
        return new CommandMessage(this.namespace, this.command, this.commandFieldNameValidator, this.readPreference, ProtocolHelper.getMessageSettings(connection.getDescription()), this.responseExpected, this.payload, this.payloadFieldNameValidator, this.clusterConnectionMode);
    }
}
