package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ConnectionId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerHeartbeatStartedEvent.class */
public final class ServerHeartbeatStartedEvent {
    private final ConnectionId connectionId;

    public ServerHeartbeatStartedEvent(ConnectionId connectionId) {
        this.connectionId = (ConnectionId) Assertions.notNull("connectionId", connectionId);
    }

    public ConnectionId getConnectionId() {
        return this.connectionId;
    }

    public String toString() {
        return "ServerHeartbeatStartedEvent{connectionId=" + this.connectionId + "} " + super.toString();
    }
}
