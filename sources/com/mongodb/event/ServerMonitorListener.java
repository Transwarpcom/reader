package com.mongodb.event;

import java.util.EventListener;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerMonitorListener.class */
public interface ServerMonitorListener extends EventListener {
    void serverHearbeatStarted(ServerHeartbeatStartedEvent serverHeartbeatStartedEvent);

    void serverHeartbeatSucceeded(ServerHeartbeatSucceededEvent serverHeartbeatSucceededEvent);

    void serverHeartbeatFailed(ServerHeartbeatFailedEvent serverHeartbeatFailedEvent);
}
