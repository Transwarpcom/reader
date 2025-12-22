package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerMonitorEventMulticaster.class */
public final class ServerMonitorEventMulticaster implements ServerMonitorListener {
    private static final Logger LOGGER = Loggers.getLogger("cluster.event");
    private final List<ServerMonitorListener> serverMonitorListeners;

    public ServerMonitorEventMulticaster(List<ServerMonitorListener> serverMonitorListeners) {
        Assertions.notNull("serverMonitorListeners", serverMonitorListeners);
        Assertions.isTrue("All ServerMonitorListener instances are non-null", !serverMonitorListeners.contains(null));
        this.serverMonitorListeners = new ArrayList(serverMonitorListeners);
    }

    public List<ServerMonitorListener> getServerMonitorListeners() {
        return Collections.unmodifiableList(this.serverMonitorListeners);
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
