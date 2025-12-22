package com.mongodb.internal.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.event.ServerHeartbeatFailedEvent;
import com.mongodb.event.ServerHeartbeatStartedEvent;
import com.mongodb.event.ServerHeartbeatSucceededEvent;
import com.mongodb.event.ServerMonitorListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/event/ServerMonitorListenerMulticaster.class */
final class ServerMonitorListenerMulticaster implements ServerMonitorListener {
    private static final Logger LOGGER = Loggers.getLogger("cluster.event");
    private final List<ServerMonitorListener> serverMonitorListeners;

    ServerMonitorListenerMulticaster(List<ServerMonitorListener> serverMonitorListeners) {
        Assertions.isTrue("All ServerMonitorListener instances are non-null", !serverMonitorListeners.contains(null));
        this.serverMonitorListeners = new ArrayList(serverMonitorListeners);
    }

    @Override // com.mongodb.event.ServerMonitorListener
    public void serverHearbeatStarted(ServerHeartbeatStartedEvent event) {
        for (ServerMonitorListener cur : this.serverMonitorListeners) {
            try {
                cur.serverHearbeatStarted(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising server heartbeat started event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.ServerMonitorListener
    public void serverHeartbeatSucceeded(ServerHeartbeatSucceededEvent event) {
        for (ServerMonitorListener cur : this.serverMonitorListeners) {
            try {
                cur.serverHeartbeatSucceeded(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising server heartbeat succeeded event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.ServerMonitorListener
    public void serverHeartbeatFailed(ServerHeartbeatFailedEvent event) {
        for (ServerMonitorListener cur : this.serverMonitorListeners) {
            try {
                cur.serverHeartbeatFailed(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising server heartbeat failed event to listener %s", cur), e);
                }
            }
        }
    }
}
