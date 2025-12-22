package com.mongodb.operation;

import com.mongodb.binding.WriteBinding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/WriteOperation.class */
public interface WriteOperation<T> {
    T execute(WriteBinding writeBinding);
}
