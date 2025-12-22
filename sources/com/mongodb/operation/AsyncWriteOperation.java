package com.mongodb.operation;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncWriteBinding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncWriteOperation.class */
public interface AsyncWriteOperation<T> {
    void executeAsync(AsyncWriteBinding asyncWriteBinding, SingleResultCallback<T> singleResultCallback);
}
