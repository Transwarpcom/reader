package com.mongodb.operation;

import com.mongodb.ReadPreference;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/OperationExecutor.class */
public interface OperationExecutor {
    <T> T execute(ReadOperation<T> readOperation, ReadPreference readPreference);

    <T> T execute(WriteOperation<T> writeOperation);
}
