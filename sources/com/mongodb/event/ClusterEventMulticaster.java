package com.mongodb.event;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Immutable
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ClusterEventMulticaster.class */
public final class ClusterEventMulticaster implements ClusterListener {
    private static final Logger LOGGER = Loggers.getLogger("cluster.event");
    private final List<ClusterListener> clusterListeners;

    public ClusterEventMulticaster(List<ClusterListener> clusterListeners) {
        Assertions.notNull("clusterListeners", clusterListeners);
        Assertions.isTrue("All ClusterListener instances are non-null", !clusterListeners.contains(null));
        this.clusterListeners = new ArrayList(clusterListeners);
    }

    public List<ClusterListener> getClusterListeners() {
        return Collections.unmodifiableList(this.clusterListeners);
    }

    @Override // com.mongodb.event.ClusterListener
    public void clusterOpening(ClusterOpeningEvent event) {
        for (ClusterListener cur : this.clusterListeners) {
            try {
                cur.clusterOpening(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising cluster opening event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.ClusterListener
    public void clusterClosed(ClusterClosedEvent event) {
        for (ClusterListener cur : this.clusterListeners) {
            try {
                cur.clusterClosed(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising cluster closed event to listener %s", cur), e);
                }
            }
        }
    }

    @Override // com.mongodb.event.ClusterListener
    public void clusterDescriptionChanged(ClusterDescriptionChangedEvent event) {
        for (ClusterListener cur : this.clusterListeners) {
            try {
                cur.clusterDescriptionChanged(event);
            } catch (Exception e) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn(String.format("Exception thrown raising cluster description changed event to listener %s", cur), e);
                }
            }
        }
    }
}
