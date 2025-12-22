package com.mongodb.event;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterId;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/event/ClusterDescriptionChangedEvent.class */
public final class ClusterDescriptionChangedEvent {
    private final ClusterId clusterId;
    private final ClusterDescription newDescription;
    private final ClusterDescription previousDescription;

    public ClusterDescriptionChangedEvent(ClusterId clusterId, ClusterDescription newDescription, ClusterDescription previousDescription) {
        this.clusterId = (ClusterId) Assertions.notNull("clusterId", clusterId);
        this.newDescription = (ClusterDescription) Assertions.notNull("newDescription", newDescription);
        this.previousDescription = (ClusterDescription) Assertions.notNull("previousDescription", previousDescription);
    }

    public ClusterId getClusterId() {
        return this.clusterId;
    }

    public ClusterDescription getNewDescription() {
        return this.newDescription;
    }

    public ClusterDescription getPreviousDescription() {
        return this.previousDescription;
    }

    public String toString() {
        return "ClusterDescriptionChangedEvent{clusterId=" + this.clusterId + ", newDescription=" + this.newDescription + ", previousDescription=" + this.previousDescription + '}';
    }
}
