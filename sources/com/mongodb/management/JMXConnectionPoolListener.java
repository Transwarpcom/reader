package com.mongodb.management;

import ch.qos.logback.classic.spi.CallerData;
import cn.hutool.core.text.StrPool;
import com.mongodb.connection.ConnectionId;
import com.mongodb.connection.ServerId;
import com.mongodb.event.ConnectionAddedEvent;
import com.mongodb.event.ConnectionCheckedInEvent;
import com.mongodb.event.ConnectionCheckedOutEvent;
import com.mongodb.event.ConnectionPoolClosedEvent;
import com.mongodb.event.ConnectionPoolListener;
import com.mongodb.event.ConnectionPoolOpenedEvent;
import com.mongodb.event.ConnectionPoolWaitQueueEnteredEvent;
import com.mongodb.event.ConnectionPoolWaitQueueExitedEvent;
import com.mongodb.event.ConnectionRemovedEvent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.management.ObjectName;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/management/JMXConnectionPoolListener.class */
public class JMXConnectionPoolListener implements ConnectionPoolListener {
    private final ConcurrentMap<ServerId, ConnectionPoolStatistics> map = new ConcurrentHashMap();

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionPoolOpened(ConnectionPoolOpenedEvent event) {
        ConnectionPoolStatistics statistics = new ConnectionPoolStatistics(event);
        this.map.put(event.getServerId(), statistics);
        MBeanServerFactory.getMBeanServer().registerMBean(statistics, getMBeanObjectName(event.getServerId()));
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionPoolClosed(ConnectionPoolClosedEvent event) {
        this.map.remove(event.getServerId());
        MBeanServerFactory.getMBeanServer().unregisterMBean(getMBeanObjectName(event.getServerId()));
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedOut(ConnectionCheckedOutEvent event) {
        ConnectionPoolStatistics statistics = getStatistics(event.getConnectionId());
        if (statistics != null) {
            statistics.connectionCheckedOut(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionCheckedIn(ConnectionCheckedInEvent event) {
        ConnectionPoolStatistics statistics = getStatistics(event.getConnectionId());
        if (statistics != null) {
            statistics.connectionCheckedIn(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void waitQueueEntered(ConnectionPoolWaitQueueEnteredEvent event) {
        ConnectionPoolListener statistics = getStatistics(event.getServerId());
        if (statistics != null) {
            statistics.waitQueueEntered(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void waitQueueExited(ConnectionPoolWaitQueueExitedEvent event) {
        ConnectionPoolListener statistics = getStatistics(event.getServerId());
        if (statistics != null) {
            statistics.waitQueueExited(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionAdded(ConnectionAddedEvent event) {
        ConnectionPoolStatistics statistics = getStatistics(event.getConnectionId());
        if (statistics != null) {
            statistics.connectionAdded(event);
        }
    }

    @Override // com.mongodb.event.ConnectionPoolListener
    public void connectionRemoved(ConnectionRemovedEvent event) {
        ConnectionPoolStatistics statistics = getStatistics(event.getConnectionId());
        if (statistics != null) {
            statistics.connectionRemoved(event);
        }
    }

    String getMBeanObjectName(ServerId serverId) {
        String name = String.format("org.mongodb.driver:type=ConnectionPool,clusterId=%s,host=%s,port=%s", ensureValidValue(serverId.getClusterId().getValue()), ensureValidValue(serverId.getAddress().getHost()), Integer.valueOf(serverId.getAddress().getPort()));
        if (serverId.getClusterId().getDescription() != null) {
            name = String.format("%s,description=%s", name, ensureValidValue(serverId.getClusterId().getDescription()));
        }
        return name;
    }

    ConnectionPoolStatisticsMBean getMBean(ServerId serverId) {
        return getStatistics(serverId);
    }

    private ConnectionPoolStatistics getStatistics(ConnectionId connectionId) {
        return getStatistics(connectionId.getServerId());
    }

    private ConnectionPoolStatistics getStatistics(ServerId serverId) {
        return this.map.get(serverId);
    }

    private String ensureValidValue(String value) {
        if (containsQuotableCharacter(value)) {
            return ObjectName.quote(value);
        }
        return value;
    }

    private boolean containsQuotableCharacter(String value) {
        if (value == null || value.length() == 0) {
            return false;
        }
        List<String> quoteableCharacters = Arrays.asList(",", ":", CallerData.NA, "*", "=", OperatorName.SHOW_TEXT_LINE_AND_SPACE, StrPool.BACKSLASH, "\n");
        for (String quotable : quoteableCharacters) {
            if (value.contains(quotable)) {
                return true;
            }
        }
        return false;
    }
}
