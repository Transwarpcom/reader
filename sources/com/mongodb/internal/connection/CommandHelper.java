package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.MongoServerException;
import com.mongodb.ReadPreference;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.session.SessionContext;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.codecs.BsonDocumentCodec;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandHelper.class */
public final class CommandHelper {
    static BsonDocument executeCommand(String database, BsonDocument command, InternalConnection internalConnection) {
        return sendAndReceive(database, command, null, internalConnection);
    }

    public static BsonDocument executeCommand(String database, BsonDocument command, ClusterClock clusterClock, InternalConnection internalConnection) {
        return sendAndReceive(database, command, clusterClock, internalConnection);
    }

    static BsonDocument executeCommandWithoutCheckingForFailure(String database, BsonDocument command, InternalConnection internalConnection) {
        try {
            return sendAndReceive(database, command, null, internalConnection);
        } catch (MongoServerException e) {
            return new BsonDocument();
        }
    }

    static void executeCommandAsync(String database, BsonDocument command, InternalConnection internalConnection, final SingleResultCallback<BsonDocument> callback) {
        internalConnection.sendAndReceiveAsync(getCommandMessage(database, command, internalConnection), new BsonDocumentCodec(), NoOpSessionContext.INSTANCE, new SingleResultCallback<BsonDocument>() { // from class: com.mongodb.internal.connection.CommandHelper.1
            @Override // com.mongodb.async.SingleResultCallback
            public void onResult(BsonDocument result, Throwable t) {
                if (t != null) {
                    callback.onResult(null, t);
                } else {
                    callback.onResult(result, null);
                }
            }
        });
    }

    static boolean isCommandOk(BsonDocument response) {
        if (!response.containsKey("ok")) {
            return false;
        }
        BsonValue okValue = response.get("ok");
        if (okValue.isBoolean()) {
            return okValue.asBoolean().getValue();
        }
        return okValue.isNumber() && okValue.asNumber().intValue() == 1;
    }

    private static BsonDocument sendAndReceive(String database, BsonDocument command, ClusterClock clusterClock, InternalConnection internalConnection) {
        SessionContext sessionContext = clusterClock == null ? NoOpSessionContext.INSTANCE : new ClusterClockAdvancingSessionContext(NoOpSessionContext.INSTANCE, clusterClock);
        return (BsonDocument) internalConnection.sendAndReceive(getCommandMessage(database, command, internalConnection), new BsonDocumentCodec(), sessionContext);
    }

    private static CommandMessage getCommandMessage(String database, BsonDocument command, InternalConnection internalConnection) {
        return new CommandMessage(new MongoNamespace(database, MongoNamespace.COMMAND_COLLECTION_NAME), command, new NoOpFieldNameValidator(), ReadPreference.primary(), MessageSettings.builder().serverVersion(internalConnection.getDescription().getServerVersion()).build());
    }

    private CommandHelper() {
    }
}
