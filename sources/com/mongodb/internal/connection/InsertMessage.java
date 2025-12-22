package com.mongodb.internal.connection;

import com.mongodb.bulk.InsertRequest;
import com.mongodb.internal.connection.RequestMessage;
import com.mongodb.internal.validator.CollectibleDocumentFieldNameValidator;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InsertMessage.class */
class InsertMessage extends LegacyMessage {
    private final InsertRequest insertRequest;

    InsertMessage(String collectionName, InsertRequest insertRequest, MessageSettings settings) {
        super(collectionName, OpCode.OP_INSERT, settings);
        this.insertRequest = insertRequest;
    }

    @Override // com.mongodb.internal.connection.LegacyMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput outputStream) {
        writeInsertPrologue(outputStream);
        int firstDocumentPosition = outputStream.getPosition();
        addCollectibleDocument(this.insertRequest.getDocument(), outputStream, new CollectibleDocumentFieldNameValidator());
        return new RequestMessage.EncodingMetadata(firstDocumentPosition);
    }

    private void writeInsertPrologue(BsonOutput outputStream) {
        outputStream.writeInt32(0);
        outputStream.writeCString(getCollectionName());
    }
}
