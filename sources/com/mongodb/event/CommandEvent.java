package com.mongodb.event;

import com.mongodb.connection.ConnectionDescription;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandEvent.class */
public abstract class CommandEvent {
    private final int requestId;
    private final ConnectionDescription connectionDescription;
    private final String commandName;

    public CommandEvent(int requestId, ConnectionDescription connectionDescription, String commandName) {
        this.requestId = requestId;
        this.connectionDescription = connectionDescription;
        this.commandName = commandName;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public ConnectionDescription getConnectionDescription() {
        return this.connectionDescription;
    }

    public String getCommandName() {
        return this.commandName;
    }
}
