package com.mongodb.operation;

import com.mongodb.MongoNamespace;
import com.mongodb.WriteConcern;
import com.mongodb.assertions.Assertions;
import com.mongodb.bulk.InsertRequest;
import com.mongodb.bulk.WriteRequest;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/InsertOperation.class */
public class InsertOperation extends BaseWriteOperation {
    private final List<InsertRequest> insertRequests;

    @Deprecated
    public InsertOperation(MongoNamespace namespace, boolean ordered, WriteConcern writeConcern, List<InsertRequest> insertRequests) {
        this(namespace, ordered, writeConcern, false, insertRequests);
    }

    public InsertOperation(MongoNamespace namespace, boolean ordered, WriteConcern writeConcern, boolean retryWrites, List<InsertRequest> insertRequests) {
        super(namespace, ordered, writeConcern, retryWrites);
        this.insertRequests = (List) Assertions.notNull("insertRequests", insertRequests);
        Assertions.isTrueArgument("insertRequests not empty", !insertRequests.isEmpty());
    }

    public List<InsertRequest> getInsertRequests() {
        return this.insertRequests;
    }

    @Override // com.mongodb.operation.BaseWriteOperation
    protected WriteRequest.Type getType() {
        return WriteRequest.Type.INSERT;
    }

    @Override // com.mongodb.operation.BaseWriteOperation
    protected List<? extends WriteRequest> getWriteRequests() {
        return getInsertRequests();
    }
}
