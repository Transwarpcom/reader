package com.mongodb.event;

import com.mongodb.annotations.Beta;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Beta
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ConnectionPoolEventMulticaster.class */
public final class ConnectionPoolEventMulticaster implements ConnectionPoolListener {
    private final Set<ConnectionPoolListener> connectionPoolListeners = Collections.newSetFromMap(new ConcurrentHashMap());

    public void add(ConnectionPoolListener connectionPoolListener) {
        this.connectionPoolListeners.add(connectionPoolListener);
    }

    public void remove(ConnectionPoolListener connectionPoolListener) {
        this.connectionPoolListeners.remove(connectionPoolListener);
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionPoolOpened(ConnectionPoolOpenedEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.connectionPoolOpened(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionPoolClosed(ConnectionPoolClosedEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.connectionPoolClosed(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedOut(ConnectionCheckedOutEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.connectionCheckedOut(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedIn(ConnectionCheckedInEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.connectionCheckedIn(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void waitQueueEntered(ConnectionPoolWaitQueueEnteredEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.waitQueueEntered(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void waitQueueExited(ConnectionPoolWaitQueueExitedEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.waitQueueExited(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionAdded(ConnectionAddedEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.connectionAdded(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionRemoved(ConnectionRemovedEvent event) {
        for (ConnectionPoolListener cur : this.connectionPoolListeners) {
            cur.connectionRemoved(event);
        }
    }
}
