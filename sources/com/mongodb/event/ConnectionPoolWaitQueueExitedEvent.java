package com.mongodb.event;

import com.mongodb.connection.ServerId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionPoolWaitQueueExitedEvent.class */
public final class ConnectionPoolWaitQueueExitedEvent {
    private final ServerId serverId;

    public ConnectionPoolWaitQueueExitedEvent(ServerId serverId) {
        this.serverId = serverId;
    }

    public ServerId getServerId() {
        return this.serverId;
    }

    public String toString() {
        return "ConnectionPoolWaitQueueExitedEvent{serverId=" + this.serverId + '}';
    }
}
