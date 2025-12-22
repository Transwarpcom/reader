package com.mongodb.event;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionPoolListenerAdapter.class */
public abstract class ConnectionPoolListenerAdapter implements ConnectionPoolListener {
    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionPoolOpened(ConnectionPoolOpenedEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionPoolClosed(ConnectionPoolClosedEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedOut(ConnectionCheckedOutEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedIn(ConnectionCheckedInEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void waitQueueEntered(ConnectionPoolWaitQueueEnteredEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void waitQueueExited(ConnectionPoolWaitQueueExitedEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionAdded(ConnectionAddedEvent event) {
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionRemoved(ConnectionRemovedEvent event) {
    }
}
