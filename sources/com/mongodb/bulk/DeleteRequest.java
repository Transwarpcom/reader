package com.mongodb.bulk;

import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.client.model.Collation;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/DeleteRequest.class */
public final class DeleteRequest extends WriteRequest {
    private final BsonDocument filter;
    private boolean isMulti = true;
    private Collation collation;

    public DeleteRequest(BsonDocument filter) {
        this.filter = (BsonDocument) Assertions.notNull("filter", filter);
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public DeleteRequest multi(boolean isMulti) {
        this.isMulti = isMulti;
        return this;
    }

    public boolean isMulti() {
        return this.isMulti;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public DeleteRequest collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    @Override // com.mongodb.bulk.WriteRequest
    public WriteRequest.Type getType() {
        return WriteRequest.Type.DELETE;
    }
}
