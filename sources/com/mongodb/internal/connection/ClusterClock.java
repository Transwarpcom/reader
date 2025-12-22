package com.mongodb.internal.connection;

import org.bson.BsonDocument;
import org.bson.BsonTimestamp;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ClusterClock.class */
public class ClusterClock {
    private static final String CLUSTER_TIME_KEY = "clusterTime";
    private BsonDocument clusterTime;

    public synchronized BsonDocument getCurrent() {
        return this.clusterTime;
    }

    public synchronized BsonTimestamp getClusterTime() {
        if (this.clusterTime != null) {
            return this.clusterTime.getTimestamp(CLUSTER_TIME_KEY);
        }
        return null;
    }

    public synchronized void advance(BsonDocument other) {
        this.clusterTime = greaterOf(other);
    }

    public synchronized BsonDocument greaterOf(BsonDocument other) {
        if (other == null) {
            return this.clusterTime;
        }
        if (this.clusterTime == null) {
            return other;
        }
        return other.getTimestamp(CLUSTER_TIME_KEY).compareTo(this.clusterTime.getTimestamp(CLUSTER_TIME_KEY)) > 0 ? other : this.clusterTime;
    }
}
