package com.mongodb.internal.connection;

import com.mongodb.internal.connection.RequestMessage;
import com.mongodb.internal.validator.NoOpFieldNameValidator;
import org.bson.BsonDocument;
import org.bson.io.BsonOutput;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/QueryMessage.class */
class QueryMessage extends BaseQueryMessage {
    private final BsonDocument queryDocument;
    private final BsonDocument fields;

    QueryMessage(String collectionName, int skip, int numberToReturn, BsonDocument queryDocument, BsonDocument fields, MessageSettings settings) {
        super(collectionName, skip, numberToReturn, settings);
        this.queryDocument = queryDocument;
        this.fields = fields;
    }

    @Override // com.mongodb.internal.connection.LegacyMessage
    protected RequestMessage.EncodingMetadata encodeMessageBodyWithMetadata(BsonOutput bsonOutput) {
        writeQueryPrologue(bsonOutput);
        int firstDocumentStartPosition = bsonOutput.getPosition();
        addDocument(this.queryDocument, bsonOutput, new NoOpFieldNameValidator());
        if (this.fields != null) {
            addDocument(this.fields, bsonOutput, new NoOpFieldNameValidator());
        }
        return new RequestMessage.EncodingMetadata(firstDocumentStartPosition);
    }
}
