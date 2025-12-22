package com.mongodb.internal.connection;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcernResult;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.connection.ByteBufferBsonOutput;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import java.util.Collections;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DeleteProtocol.class */
class DeleteProtocol extends WriteProtocol {
    private static final Logger LOGGER = Loggers.getLogger("protocol.delete");
    private final DeleteRequest deleteRequest;

    DeleteProtocol(MongoNamespace namespace, boolean ordered, DeleteRequest deleteRequest) {
        super(namespace, ordered);
        this.deleteRequest = deleteRequest;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.mongodb.internal.connection.WriteProtocol, com.mongodb.internal.connection.LegacyProtocol
    public WriteConcernResult execute(InternalConnection connection) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Deleting documents from namespace %s on connection [%s] to server %s", getNamespace(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
        }
        WriteConcernResult writeConcernResult = super.execute(connection);
        LOGGER.debug("Delete completed");
        return writeConcernResult;
    }

    @Override // com.mongodb.internal.connection.WriteProtocol, com.mongodb.internal.connection.LegacyProtocol
    public void executeAsync(InternalConnection connection, final SingleResultCallback<WriteConcernResult> callback) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Asynchronously deleting documents in namespace %s on connection [%s] to server %s", getNamespace(), connection.getDescription().getConnectionId(), connection.getDescription().getServerAddress()));
            }
            super.executeAsync(connection, new SingleResultCallback<WriteConcernResult>() { // from class: com.mongodb.internal.connection.DeleteProtocol.1
                @Override // com.mongodb.async.SingleResultCallback
                public void onResult(WriteConcernResult result, Throwable t) {
                    if (t == null) {
                        DeleteProtocol.LOGGER.debug("Asynchronous delete completed");
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
        BsonDocument deleteDocument = new BsonDocument(OperatorName.SAVE, ByteBufBsonDocument.createOne(bsonOutput, firstDocumentPosition)).append("limit", this.deleteRequest.isMulti() ? new BsonInt32(0) : new BsonInt32(1));
        return getBaseCommandDocument("delete").append("deletes", new BsonArray(Collections.singletonList(deleteDocument)));
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected RequestMessage createRequestMessage(MessageSettings settings) {
        return new DeleteMessage(getNamespace().getFullName(), this.deleteRequest, settings);
    }

    @Override // com.mongodb.internal.connection.WriteProtocol
    protected Logger getLogger() {
        return LOGGER;
    }
}
