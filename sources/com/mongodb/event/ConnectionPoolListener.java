package com.mongodb.event;

import java.util.EventListener;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionPoolListener.class */
public interface ConnectionPoolListener extends EventListener {
    void connectionPoolOpened(ConnectionPoolOpenedEvent connectionPoolOpenedEvent);

    void connectionPoolClosed(ConnectionPoolClosedEvent connectionPoolClosedEvent);

    void connectionCheckedOut(ConnectionCheckedOutEvent connectionCheckedOutEvent);

    void connectionCheckedIn(ConnectionCheckedInEvent connectionCheckedInEvent);

    void waitQueueEntered(ConnectionPoolWaitQueueEnteredEvent connectionPoolWaitQueueEnteredEvent);

    void waitQueueExited(ConnectionPoolWaitQueueExitedEvent connectionPoolWaitQueueExitedEvent);

    void connectionAdded(ConnectionAddedEvent connectionAddedEvent);

    void connectionRemoved(ConnectionRemovedEvent connectionRemovedEvent);
}
