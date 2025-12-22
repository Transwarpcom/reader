package com.mongodb.internal.connection;

import com.mongodb.bulk.UpdateRequest;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.internal.connection.RequestMessage;
import com.mongodb.internal.validator.CollectibleDocumentFieldNameValidator;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import com.mongodb.internal.validator.UpdateFieldNameValidator;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/UpdateMessage.class */
class UpdateMessage extends LegacyMessage {
    private final UpdateRequest updateRequest;

    UpdateMessage(String collectionName, UpdateRequest updateRequest, MessageSettings settings) {
        super(collectionName, OpCode.OP_UPDATE, settings);
        this.updateRequest = updateRequest;
    }

    @Override // com.mongodb.internal.connection.LegacyMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput) {
        bsonOutput.writeInt32(0);
        bsonOutput.writeCString(getCollectionName());
        int flags = 0;
        if (this.updateRequest.isUpsert()) {
            flags = 0 | 1;
        }
        if (this.updateRequest.isMulti()) {
            flags |= 2;
        }
        bsonOutput.writeInt32(flags);
        int firstDocumentStartPosition = bsonOutput.getPosition();
        addDocument(this.updateRequest.getFilter(), bsonOutput, new NoOpFieldNameValidator());
        if (this.updateRequest.getType() == WriteRequest.Type.REPLACE) {
            addCollectibleDocument(this.updateRequest.getUpdate(), bsonOutput, new CollectibleDocumentFieldNameValidator());
        } else {
            int bufferPosition = bsonOutput.getPosition();
            addDocument(this.updateRequest.getUpdate(), bsonOutput, new UpdateFieldNameValidator());
            if (bsonOutput.getPosition() == bufferPosition + 5) {
                throw new IllegalArgumentException("Invalid BSON document for an update");
            }
        }
        return new RequestMessage.EncodingMetadata(firstDocumentStartPosition);
    }
}
