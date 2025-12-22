package com.mongodb.event;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ClusterListenerAdapter.class */
public abstract class ClusterListenerAdapter implements ClusterListener {
    @Override // com.mongodb.event.ClusterListener
    public void clusterOpening(ClusterOpeningEvent event) {
    }

    @Override // com.mongodb.event.ClusterListener
    public void clusterClosed(ClusterClosedEvent event) {
    }

    @Override // com.mongodb.event.ClusterListener
    public void clusterDescriptionChanged(ClusterDescriptionChangedEvent event) {
    }
}
