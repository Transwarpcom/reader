package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcernResult;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import java.util.Collections;
import java.util.List;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonArray;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/UpdateProtocol.class */
class UpdateProtocol extends WriteProtocol {
    private static final Logger LOGGER = Loggers.getLogger("protocol.update");
    private final UpdateRequest updateRequest;

    UpdateProtocol(MongoNamespace namespace, boolean ordered, UpdateRequest updateRequest) {
        super(namespace, ordered);
        this.updateRequest = updateRequest;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.internal.connection.WriteProtocol, com.mongodb.internal.connection.LegacyProtocol
    public WriteConcernResult execute(InternalConnection connection) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Updating documents in namespace %s on connection [%s] to server %s", getNamespace(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
        }
        WriteConcernResult writeConcernResult = super.execute(connection);
        LOGGER.debug("Update completed");
        return writeConcernResult;
    }

    @Override // com.mongodb.internal.connection.WriteProtocol, com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(InternalConnection connection, final SingleResultCallback<WriteConcernResult> callback) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Asynchronously updating documents in namespace %s on connection [%s] to server %s", getNamespace(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
            }
            super.executeAsync(connection, new SingleResultCallback<WriteConcernResult>() { // from class: com.mongodb.internal.connection.UpdateProtocol.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(WriteConcernResult result, Throwable t) {
                    if (t == null) {
                        UpdateProtocol.LOGGER.debug("Asynchronous update completed");
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
        List<ByteBufBsonDocument> documents = ByteBufBsonDocument.createList(bsonOutput, firstDocumentPosition);
        BsonDocument updateDocument = new BsonDocument(OperatorName.SAVE, documents.get(0)).append("u", documents.get(1));
        if (this.updateRequest.isMulti()) {
            updateDocument.append("multi", BsonBoolean.TRUE);
        }
        if (this.updateRequest.isUpsert()) {
            updateDocument.append("upsert", BsonBoolean.TRUE);
        }
        return getBaseCommandDocument("update").append("updates", new BsonArray(Collections.singletonList(updateDocument)));
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected RequestMessage createRequestMessage(MessageSettings settings) {
        return new UpdateMessage(getNamespace().getFullName(), this.updateRequest, settings);
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected Logger getLogger() {
        return LOGGER;
    }
}
