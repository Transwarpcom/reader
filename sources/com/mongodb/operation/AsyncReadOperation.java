package com.mongodb.operation;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.binding.AsyncReadBinding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/AsyncReadOperation.class */
public interface AsyncReadOperation<T> {
    void executeAsync(AsyncReadBinding asyncReadBinding, SingleResultCallback<T> singleResultCallback);
}
