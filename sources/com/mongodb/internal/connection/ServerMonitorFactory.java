package com.mongodb.internal.connection;

import com.mongodb.connection.ServerDescription;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ServerMonitorFactory.class */
interface ServerMonitorFactory {
    ServerMonitor create(ChangeListener<ServerDescription> changeListener);
}
