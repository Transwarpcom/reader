package com.mongodb.management;

import com.mongodb.ServerAddress;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.event.ConnectionAddedEvent;
import com.mongodb.event.ConnectionCheckedInEvent;
import com.mongodb.event.ConnectionCheckedOutEvent;
import com.mongodb.event.ConnectionPoolListenerAdapter;
import com.mongodb.event.ConnectionPoolOpenedEvent;
import com.mongodb.event.ConnectionPoolWaitQueueEnteredEvent;
import com.mongodb.event.ConnectionPoolWaitQueueExitedEvent;
import com.mongodb.event.ConnectionRemovedEvent;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/management/ConnectionPoolStatistics.class */
final class ConnectionPoolStatistics extends ConnectionPoolListenerAdapter implements ConnectionPoolStatisticsMBean {
    private final ServerAddress serverAddress;
    private final ConnectionPoolSettings settings;
    private final AtomicInteger size = new AtomicInteger();
    private final AtomicInteger checkedOutCount = new AtomicInteger();
    private final AtomicInteger waitQueueSize = new AtomicInteger();

    ConnectionPoolStatistics(ConnectionPoolOpenedEvent event) {
        this.serverAddress = event.getServerId().getAddress();
        this.settings = event.getSettings();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public String getHost() {
        return this.serverAddress.getHost();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public int getPort() {
        return this.serverAddress.getPort();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public int getMinSize() {
        return this.settings.getMinSize();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public int getMaxSize() {
        return this.settings.getMaxSize();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public int getSize() {
        return this.size.get();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public int getCheckedOutCount() {
        return this.checkedOutCount.get();
    }

    @Override // com.mongodb.management.ConnectionPoolStatisticsMBean
    public int getWaitQueueSize() {
        return this.waitQueueSize.get();
    }

    @Override // com.mongodb.event.ConnectionPoolListenerAdapter, com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedOut(ConnectionCheckedOutEvent event) {
        this.checkedOutCount.incrementAndGet();
    }

    @Override // com.mongodb.event.ConnectionPoolListenerAdapter, com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedIn(ConnectionCheckedInEvent event) {
        this.checkedOutCount.decrementAndGet();
    }

    @Override // com.mongodb.event.ConnectionPoolListenerAdapter, com.mongodb.event.ConnectionPoolListener
    public void connectionAdded(ConnectionAddedEvent event) {
        this.size.incrementAndGet();
    }

    @Override // com.mongodb.event.ConnectionPoolListenerAdapter, com.mongodb.event.ConnectionPoolListener
    public void connectionRemoved(ConnectionRemovedEvent event) {
        this.size.decrementAndGet();
    }

    @Override // com.mongodb.event.ConnectionPoolListenerAdapter, com.mongodb.event.ConnectionPoolListener
    public void waitQueueEntered(ConnectionPoolWaitQueueEnteredEvent event) {
        this.waitQueueSize.incrementAndGet();
    }

    @Override // com.mongodb.event.ConnectionPoolListenerAdapter, com.mongodb.event.ConnectionPoolListener
    public void waitQueueExited(ConnectionPoolWaitQueueExitedEvent event) {
        this.waitQueueSize.decrementAndGet();
    }
}
