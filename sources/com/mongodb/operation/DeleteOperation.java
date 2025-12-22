package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.bulk.WriteRequest;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/DeleteOperation.class */
public class DeleteOperation extends BaseWriteOperation {
    private final List<DeleteRequest> deleteRequests;

    @Deprecated
    public DeleteOperation(MongoNamespace namespace, boolean ordered, WriteConcern writeConcern, List<DeleteRequest> deleteRequests) {
        this(namespace, ordered, writeConcern, false, deleteRequests);
    }

    public DeleteOperation(MongoNamespace namespace, boolean ordered, WriteConcern writeConcern, boolean retryWrites, List<DeleteRequest> deleteRequests) {
        super(namespace, ordered, writeConcern, retryWrites);
        this.deleteRequests = (List) Assertions.notNull("removes", deleteRequests);
        Assertions.isTrueArgument("deleteRequests not empty", !deleteRequests.isEmpty());
    }

    public List<DeleteRequest> getDeleteRequests() {
        return this.deleteRequests;
    }

    @Override // com.mongodb.operation.BaseWriteOperation
    protected List<? extends WriteRequest> getWriteRequests() {
        return getDeleteRequests();
    }

    @Override // com.mongodb.operation.BaseWriteOperation
    protected WriteRequest.Type getType() {
        return WriteRequest.Type.DELETE;
    }
}
