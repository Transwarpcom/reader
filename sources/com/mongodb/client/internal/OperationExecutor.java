package com.mongodb.client.internal;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.client.ClientSession;
import com.mongodb.lang.Nullable;
import com.mongodb.operation.ReadOperation;
import com.mongodb.operation.WriteOperation;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-sync-3.8.2.jar:com/mongodb/client/internal/OperationExecutor.class */
public interface OperationExecutor {
    <T> T execute(ReadOperation<T> readOperation, ReadPreference readPreference, ReadConcern readConcern);

    <T> T execute(WriteOperation<T> writeOperation, ReadConcern readConcern);

    <T> T execute(ReadOperation<T> readOperation, ReadPreference readPreference, ReadConcern readConcern, @Nullable ClientSession clientSession);

    <T> T execute(WriteOperation<T> writeOperation, ReadConcern readConcern, @Nullable ClientSession clientSession);
}
