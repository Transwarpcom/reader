package com.mongodb.internal.connection;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ProtocolExecutor.class */
public interface ProtocolExecutor {
    <T> T execute(LegacyProtocol<T> legacyProtocol, InternalConnection internalConnection);

    <T> void executeAsync(LegacyProtocol<T> legacyProtocol, InternalConnection internalConnection, SingleResultCallback<T> singleResultCallback);

    <T> T execute(CommandProtocol<T> commandProtocol, InternalConnection internalConnection, SessionContext sessionContext);

    <T> void executeAsync(CommandProtocol<T> commandProtocol, InternalConnection internalConnection, SessionContext sessionContext, SingleResultCallback<T> singleResultCallback);
}
