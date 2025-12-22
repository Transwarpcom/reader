package com.mongodb.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ServerType.class */
public enum ServerType {
    STANDALONE { // from class: com.mongodb.connection.ServerType.1
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.STANDALONE;
        }
    },
    REPLICA_SET_PRIMARY { // from class: com.mongodb.connection.ServerType.2
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.REPLICA_SET;
        }
    },
    REPLICA_SET_SECONDARY { // from class: com.mongodb.connection.ServerType.3
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.REPLICA_SET;
        }
    },
    REPLICA_SET_ARBITER { // from class: com.mongodb.connection.ServerType.4
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.REPLICA_SET;
        }
    },
    REPLICA_SET_OTHER { // from class: com.mongodb.connection.ServerType.5
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.REPLICA_SET;
        }
    },
    REPLICA_SET_GHOST { // from class: com.mongodb.connection.ServerType.6
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.REPLICA_SET;
        }
    },
    SHARD_ROUTER { // from class: com.mongodb.connection.ServerType.7
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.SHARDED;
        }
    },
    UNKNOWN { // from class: com.mongodb.connection.ServerType.8
        @Override // com.mongodb.connection.ServerType
        public ClusterType getClusterType() {
            return ClusterType.UNKNOWN;
        }
    };

    public abstract ClusterType getClusterType();
}
