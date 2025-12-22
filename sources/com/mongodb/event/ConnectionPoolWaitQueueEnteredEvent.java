package com.mongodb.event;

import com.mongodb.connection.ServerId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionPoolWaitQueueEnteredEvent.class */
public final class ConnectionPoolWaitQueueEnteredEvent {
    private final ServerId serverId;

    public ConnectionPoolWaitQueueEnteredEvent(ServerId serverId) {
        this.serverId = serverId;
    }

    public ServerId getServerId() {
        return this.serverId;
    }

    public String toString() {
        return "ConnectionPoolWaitQueueEnteredEvent{serverId=" + this.serverId + '}';
    }
}
