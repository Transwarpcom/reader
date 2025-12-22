package com.mongodb.event;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerMonitorListenerAdapter.class */
public abstract class ServerMonitorListenerAdapter implements ServerMonitorListener {
    @Override // com.mongodb.event.ServerMonitorListener
    public void serverHearbeatStarted(ServerHeartbeatStartedEvent event) {
    }

    @Override // com.mongodb.event.ServerMonitorListener
    public void serverHeartbeatSucceeded(ServerHeartbeatSucceededEvent event) {
    }

    @Override // com.mongodb.event.ServerMonitorListener
    public void serverHeartbeatFailed(ServerHeartbeatFailedEvent event) {
    }
}
