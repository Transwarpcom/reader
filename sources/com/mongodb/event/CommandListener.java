package com.mongodb.event;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/CommandListener.class */
public interface CommandListener {
    void commandStarted(CommandStartedEvent commandStartedEvent);

    void commandSucceeded(CommandSucceededEvent commandSucceededEvent);

    void commandFailed(CommandFailedEvent commandFailedEvent);
}
