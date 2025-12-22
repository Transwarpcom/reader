package com.mongodb.event;

import com.mongodb.connection.ConnectionDescription;
import org.bson.BsonDocument;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandStartedEvent.class */
public final class CommandStartedEvent extends CommandEvent {
    private final String databaseName;
    private final BsonDocument command;

    public CommandStartedEvent(int requestId, ConnectionDescription connectionDescription, String databaseName, String commandName, BsonDocument command) {
        super(requestId, connectionDescription, commandName);
        this.command = command;
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public BsonDocument getCommand() {
        return this.command;
    }
}
