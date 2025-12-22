package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ServerId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerClosedEvent.class */
public final class ServerClosedEvent {
    private final ServerId serverId;

    public ServerClosedEvent(ServerId serverId) {
        this.serverId = (ServerId) Assertions.notNull("serverId", serverId);
    }

    public ServerId getServerId() {
        return this.serverId;
    }

    public String toString() {
        return "ServerClosedEvent{serverId=" + this.serverId + '}';
    }
}
