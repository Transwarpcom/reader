package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ServerId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionPoolOpenedEvent.class */
public final class ConnectionPoolOpenedEvent {
    private final ServerId serverId;
    private final ConnectionPoolSettings settings;

    public ConnectionPoolOpenedEvent(ServerId serverId, ConnectionPoolSettings settings) {
        this.serverId = (ServerId) Assertions.notNull("serverId", serverId);
        this.settings = (ConnectionPoolSettings) Assertions.notNull("settings", settings);
    }

    public ServerId getServerId() {
        return this.serverId;
    }

    public ConnectionPoolSettings getSettings() {
        return this.settings;
    }

    public String toString() {
        return "ConnectionPoolOpenedEvent{serverId=" + this.serverId + "settings=" + this.settings + '}';
    }
}
