package com.mongodb.operation;

import com.mongodb.ReadPreference;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.session.ClientSession;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncOperationExecutor.class */
public interface AsyncOperationExecutor {
    <T> void execute(AsyncReadOperation<T> asyncReadOperation, ReadPreference readPreference, SingleResultCallback<T> singleResultCallback);

    <T> void execute(AsyncReadOperation<T> asyncReadOperation, ReadPreference readPreference, ClientSession clientSession, SingleResultCallback<T> singleResultCallback);

    <T> void execute(AsyncWriteOperation<T> asyncWriteOperation, SingleResultCallback<T> singleResultCallback);

    <T> void execute(AsyncWriteOperation<T> asyncWriteOperation, ClientSession clientSession, SingleResultCallback<T> singleResultCallback);
}
