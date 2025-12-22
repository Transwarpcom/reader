package com.mongodb.event;

import com.mongodb.connection.ConnectionId;
import org.bson.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionClosedEvent.class */
public final class ConnectionClosedEvent {
    private final ConnectionId connectionId;

    public ConnectionClosedEvent(ConnectionId connectionId) {
        this.connectionId = (ConnectionId) Assertions.notNull("connectionId", connectionId);
    }

    public ConnectionId getConnectionId() {
        return this.connectionId;
    }

    public String toString() {
        return "ConnectionClosedEvent{connectionId=" + this.connectionId + '}';
    }
}
