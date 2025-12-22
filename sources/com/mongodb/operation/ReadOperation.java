package com.mongodb.operation;

import com.mongodb.binding.ReadBinding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/operation/ReadOperation.class */
public interface ReadOperation<T> {
    T execute(ReadBinding readBinding);
}
