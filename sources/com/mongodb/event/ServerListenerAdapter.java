package com.mongodb.event;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerListenerAdapter.class */
public abstract class ServerListenerAdapter implements ServerListener {
    @Override // com.mongodb.event.ServerListener
    public void serverOpening(ServerOpeningEvent event) {
    }

    @Override // com.mongodb.event.ServerListener
    public void serverClosed(ServerClosedEvent event) {
    }

    @Override // com.mongodb.event.ServerListener
    public void serverDescriptionChanged(ServerDescriptionChangedEvent event) {
    }
}
