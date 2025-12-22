package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.event.CommandListener;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/LegacyProtocol.class */
public interface LegacyProtocol<T> {
    T execute(InternalConnection internalConnection);

    void executeAsync(InternalConnection internalConnection, SingleResultCallback<T> singleResultCallback);

    void setCommandListener(CommandListener commandListener);
}
