package com.mongodb.event;

import com.mongodb.annotations.Beta;
import com.mongodb.connection.ConnectionId;
import org.bson.assertions.Assertions;

@Beta
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionMessageReceivedEvent.class */
public final class ConnectionMessageReceivedEvent {
    private final int responseTo;
    private final int size;
    private final ConnectionId connectionId;

    public ConnectionMessageReceivedEvent(ConnectionId connectionId, int responseTo, int size) {
        this.connectionId = (ConnectionId) Assertions.notNull("connectionId", connectionId);
        this.responseTo = responseTo;
        this.size = size;
    }

    public int getResponseTo() {
        return this.responseTo;
    }

    public int getSize() {
        return this.size;
    }

    public ConnectionId getConnectionId() {
        return this.connectionId;
    }

    public String toString() {
        return "ConnectionMessageReceivedEvent{responseTo=" + this.responseTo + ", size=" + this.size + ", connectionId=" + this.connectionId + '}';
    }
}
