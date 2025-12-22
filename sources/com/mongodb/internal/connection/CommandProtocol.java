package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandProtocol.class */
public interface CommandProtocol<T> {
    T execute(InternalConnection internalConnection);

    void executeAsync(InternalConnection internalConnection, SingleResultCallback<T> singleResultCallback);

    CommandProtocol<T> sessionContext(SessionContext sessionContext);
}
