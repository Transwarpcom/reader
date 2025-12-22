package com.mongodb.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/AsyncCompletionHandler.class */
public interface AsyncCompletionHandler<T> {
    void completed(T t);

    void failed(Throwable th);
}
