package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.UpdateRequest;
import com.mongodb.bulk.WriteRequest;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/UpdateOperation.class */
public class UpdateOperation extends BaseWriteOperation {
    private final List<UpdateRequest> updates;

    @Deprecated
    public UpdateOperation(MongoNamespace namespace, boolean ordered, WriteConcern writeConcern, List<UpdateRequest> updates) {
        this(namespace, ordered, writeConcern, false, updates);
    }

    public UpdateOperation(MongoNamespace namespace, boolean ordered, WriteConcern writeConcern, boolean retryWrites, List<UpdateRequest> updates) {
        super(namespace, ordered, writeConcern, retryWrites);
        this.updates = (List) Assertions.notNull("update", updates);
        Assertions.isTrueArgument("updateRequests not empty", !updates.isEmpty());
    }

    public List<UpdateRequest> getUpdateRequests() {
        return this.updates;
    }

    @Override // com.mongodb.operation.BaseWriteOperation
    protected List<? extends WriteRequest> getWriteRequests() {
        return getUpdateRequests();
    }

    @Override // com.mongodb.operation.BaseWriteOperation
    protected WriteRequest.Type getType() {
        return WriteRequest.Type.UPDATE;
    }
}
