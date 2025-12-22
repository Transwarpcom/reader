package com.mongodb.async;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/async/SingleResultCallback.class */
public interface SingleResultCallback<T> {
    void onResult(T t, Throwable th);
}
