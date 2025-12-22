package com.mongodb.internal.connection;

import com.mongodb.bulk.DeleteRequest;
import com.mongodb.internal.connection.RequestMessage;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/DeleteMessage.class */
class DeleteMessage extends LegacyMessage {
    private final DeleteRequest deleteRequest;

    DeleteMessage(String collectionName, DeleteRequest deleteRequest, MessageSettings settings) {
        super(collectionName, OpCode.OP_DELETE, settings);
        this.deleteRequest = deleteRequest;
    }

    @Override // com.mongodb.internal.connection.LegacyMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput) {
        bsonOutput.writeInt32(0);
        bsonOutput.writeCString(getCollectionName());
        if (this.deleteRequest.isMulti()) {
            bsonOutput.writeInt32(0);
        } else {
            bsonOutput.writeInt32(1);
        }
        int firstDocumentStartPosition = bsonOutput.getPosition();
        addDocument(this.deleteRequest.getFilter(), bsonOutput, new NoOpFieldNameValidator());
        return new RequestMessage.EncodingMetadata(firstDocumentStartPosition);
    }
}
