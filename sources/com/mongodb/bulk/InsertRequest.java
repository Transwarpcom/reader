package com.mongodb.bulk;

import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.WriteRequest;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/InsertRequest.class */
public final class InsertRequest extends WriteRequest {
    private final BsonDocument document;

    public InsertRequest(BsonDocument document) {
        this.document = (BsonDocument) Assertions.notNull("document", document);
    }

    public BsonDocument getDocument() {
        return this.document;
    }

    @Override // com.mongodb.bulk.WriteRequest
    public WriteRequest.Type getType() {
        return WriteRequest.Type.INSERT;
    }
}
