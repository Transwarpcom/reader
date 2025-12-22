package com.mongodb.event;

import com.mongodb.annotations.Beta;
import com.mongodb.connection.ConnectionId;
import org.bson.assertions.Assertions;

@Beta
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionMessagesSentEvent.class */
public final class ConnectionMessagesSentEvent {
    private final ConnectionId connectionId;
    private final int requestId;
    private final int size;

    public ConnectionMessagesSentEvent(ConnectionId connectionId, int requestId, int size) {
        this.connectionId = (ConnectionId) Assertions.notNull("connectionId", connectionId);
        this.requestId = requestId;
        this.size = size;
    }

    public ConnectionId getConnectionId() {
        return this.connectionId;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        return "ConnectionMessagesSentEvent{requestId=" + this.requestId + ", size=" + this.size + ", connectionId=" + this.connectionId + '}';
    }
}
