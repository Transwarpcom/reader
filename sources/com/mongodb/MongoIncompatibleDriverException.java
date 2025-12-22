package com.mongodb;

import com.mongodb.connection.ClusterDescription;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoIncompatibleDriverException.class */
public class MongoIncompatibleDriverException extends MongoException {
    private static final long serialVersionUID = -5213381354402601890L;
    private ClusterDescription clusterDescription;

    public MongoIncompatibleDriverException(String message, ClusterDescription clusterDescription) {
        super(message);
        this.clusterDescription = clusterDescription;
    }

    public ClusterDescription getClusterDescription() {
        return this.clusterDescription;
    }
}
