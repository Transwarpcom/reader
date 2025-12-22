package com.mongodb.event;

import java.util.EventListener;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ClusterListener.class */
public interface ClusterListener extends EventListener {
    void clusterOpening(ClusterOpeningEvent clusterOpeningEvent);

    void clusterClosed(ClusterClosedEvent clusterClosedEvent);

    void clusterDescriptionChanged(ClusterDescriptionChangedEvent clusterDescriptionChangedEvent);
}
