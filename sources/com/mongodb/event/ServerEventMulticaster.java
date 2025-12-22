package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ServerEventMulticaster.class */
public final class ServerEventMulticaster implements ServerListener {
    private static final Logger LOGGER = Loggers.getLogger("cluster.event");
    private final List<ServerListener> serverListeners;

    public ServerEventMulticaster(List<ServerListener> serverListeners) {
        Assertions.notNull("serverListeners", serverListeners);
        Assertions.isTrue("All ServerListener instances are non-null", !serverListeners.contains(null));
        this.serverListeners = new ArrayList(serverListeners);
    }

    public List<ServerListener> getServerListeners() {
        return Collections.unmodifiableList(this.serverListeners);
    }

    @Override // com.mongodb.event.ServerListener
    public void serverOpening(ServerOpeningEvent event) {
        for (ServerListener cur : this.serverListeners) {
            try {
                cur.serverOpening(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising server opening event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.ServerListener
    public void serverClosed(ServerClosedEvent event) {
        for (ServerListener cur : this.serverListeners) {
            try {
                cur.serverClosed(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising server opening event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.ServerListener
    public void serverDescriptionChanged(ServerDescriptionChangedEvent event) {
        for (ServerListener cur : this.serverListeners) {
            try {
                cur.serverDescriptionChanged(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising server description changed event to listener %s", cur), e);
                }
            }
        }
    }
}
