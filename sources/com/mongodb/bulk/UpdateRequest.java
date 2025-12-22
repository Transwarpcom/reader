package com.mongodb.bulk;

import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.WriteRequest;
import com.mongodb.client.model.Collation;
import java.util.List;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/bulk/UpdateRequest.class */
public final class UpdateRequest extends WriteRequest {
    private final BsonDocument update;
    private final WriteRequest.Type updateType;
    private final BsonDocument filter;
    private boolean isMulti;
    private boolean isUpsert = false;
    private Collation collation;
    private List<BsonDocument> arrayFilters;

    public UpdateRequest(BsonDocument filter, BsonDocument update, WriteRequest.Type updateType) {
        this.isMulti = true;
        if (updateType != WriteRequest.Type.UPDATE && updateType != WriteRequest.Type.REPLACE) {
            throw new IllegalArgumentException("Update type must be UPDATE or REPLACE");
        }
        this.filter = (BsonDocument) Assertions.notNull("filter", filter);
        this.update = (BsonDocument) Assertions.notNull("update", update);
        this.updateType = updateType;
        this.isMulti = updateType == WriteRequest.Type.UPDATE;
    }

    @Override // com.mongodb.bulk.WriteRequest
    public WriteRequest.Type getType() {
        return this.updateType;
    }

    public BsonDocument getFilter() {
        return this.filter;
    }

    public BsonDocument getUpdate() {
        return this.update;
    }

    public boolean isMulti() {
        return this.isMulti;
    }

    public UpdateRequest multi(boolean isMulti) {
        if (isMulti && this.updateType == WriteRequest.Type.REPLACE) {
            throw new IllegalArgumentException("Replacements can not be multi");
        }
        this.isMulti = isMulti;
        return this;
    }

    public boolean isUpsert() {
        return this.isUpsert;
    }

    public UpdateRequest upsert(boolean isUpsert) {
        this.isUpsert = isUpsert;
        return this;
    }

    public Collation getCollation() {
        return this.collation;
    }

    public UpdateRequest collation(Collation collation) {
        this.collation = collation;
        return this;
    }

    public UpdateRequest arrayFilters(List<BsonDocument> arrayFilters) {
        this.arrayFilters = arrayFilters;
        return this;
    }

    public List<BsonDocument> getArrayFilters() {
        return this.arrayFilters;
    }
}
