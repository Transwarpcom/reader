package com.mongodb.internal.connection;

import com.mongodb.connection.ServerId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/InternalConnectionFactory.class */
interface InternalConnectionFactory {
    InternalConnection create(ServerId serverId);
}
