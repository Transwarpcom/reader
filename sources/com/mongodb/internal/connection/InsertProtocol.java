package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcernResult;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.bulk.InsertRequest;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import org.bson.BsonArray;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InsertProtocol.class */
class InsertProtocol extends WriteProtocol {
    private static final Logger LOGGER = Loggers.getLogger("protocol.insert");
    private final InsertRequest insertRequest;

    InsertProtocol(MongoNamespace namespace, boolean ordered, InsertRequest insertRequest) {
        super(namespace, ordered);
        this.insertRequest = insertRequest;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.internal.connection.WriteProtocol, com.mongodb.internal.connection.LegacyProtocol
    public WriteConcernResult execute(InternalConnection connection) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Inserting 1 document into namespace %s on connection [%s] to server %s", getNamespace(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
        }
        WriteConcernResult writeConcernResult = super.execute(connection);
        LOGGER.debug("Insert completed");
        return writeConcernResult;
    }

    @Override // com.mongodb.internal.connection.WriteProtocol, com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(InternalConnection connection, final SingleResultCallback<WriteConcernResult> callback) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Asynchronously inserting 1 document into namespace %s on connection [%s] to server %s", getNamespace(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
            }
            super.executeAsync(connection, new SingleResultCallback<WriteConcernResult>() { // from class: com.mongodb.internal.connection.InsertProtocol.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(WriteConcernResult result, Throwable t) {
                    if (t == null) {
                        InsertProtocol.LOGGER.debug("Asynchronous insert completed");
                        callback.onResult(result, null);
                    } else {
                        callback.onResult(null, t);
                    }
                }
            });
        } catch (Throwable t) {
            callback.onResult(null, t);
        }
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected BsonDocument getAsWriteCommand(ByteBufferBsonOutput bsonOutput, int firstDocumentPosition) {
        return getBaseCommandDocument("insert").append("documents", new BsonArray(ByteBufBsonDocument.createList(bsonOutput, firstDocumentPosition)));
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected RequestMessage createRequestMessage(MessageSettings settings) {
        return new InsertMessage(getNamespace().getFullName(), this.insertRequest, settings);
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected Logger getLogger() {
        return LOGGER;
    }
}
