package com.mongodb.event;

import com.mongodb.annotations.Beta;
import java.util.EventListener;

@Beta
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionListener.class */
public interface ConnectionListener extends EventListener {
    void connectionOpened(ConnectionOpenedEvent connectionOpenedEvent);

    void connectionClosed(ConnectionClosedEvent connectionClosedEvent);

    void messagesSent(ConnectionMessagesSentEvent connectionMessagesSentEvent);

    void messageReceived(ConnectionMessageReceivedEvent connectionMessageReceivedEvent);
}
