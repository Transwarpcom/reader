package com.mongodb.event;

import java.util.EventListener;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerListener.class */
public interface ServerListener extends EventListener {
    void serverOpening(ServerOpeningEvent serverOpeningEvent);

    void serverClosed(ServerClosedEvent serverClosedEvent);

    void serverDescriptionChanged(ServerDescriptionChangedEvent serverDescriptionChangedEvent);
}
