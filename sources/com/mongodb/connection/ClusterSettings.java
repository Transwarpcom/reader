package com.mongodb.connection;

import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.event.ClusterListener;
import com.mongodb.internal.connection.ServerAddressHelper;
import com.mongodb.selector.CompositeServerSelector;
import com.mongodb.selector.LatencyMinimizingServerSelector;
import com.mongodb.selector.ServerSelector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ClusterSettings.class */
public final class ClusterSettings {
    private final List<ServerAddress> hosts;
    private final ClusterConnectionMode mode;
    private final ClusterType requiredClusterType;
    private final String requiredReplicaSetName;
    private final ServerSelector serverSelector;
    private final String description;
    private final long localThresholdMS;
    private final long serverSelectionTimeoutMS;
    private final int maxWaitQueueSize;
    private final List<ClusterListener> clusterListeners;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ClusterSettings clusterSettings) {
        return builder().applySettings(clusterSettings);
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ClusterSettings$Builder.class */
    public static final class Builder {
        private List<ServerAddress> hosts;
        private ClusterConnectionMode mode;
        private ClusterType requiredClusterType;
        private String requiredReplicaSetName;
        private ServerSelector serverSelector;
        private String description;
        private long serverSelectionTimeoutMS;
        private long localThresholdMS;
        private int maxWaitQueueSize;
        private List<ClusterListener> clusterListeners;

        private Builder() {
            this.hosts = Collections.singletonList(new ServerAddress());
            this.requiredClusterType = ClusterType.UNKNOWN;
            this.serverSelectionTimeoutMS = TimeUnit.MILLISECONDS.convert(30L, TimeUnit.SECONDS);
            this.localThresholdMS = TimeUnit.MILLISECONDS.convert(15L, TimeUnit.MILLISECONDS);
            this.maxWaitQueueSize = 500;
            this.clusterListeners = new ArrayList();
        }

        public Builder applySettings(ClusterSettings clusterSettings) {
            Assertions.notNull("clusterSettings", clusterSettings);
            this.description = clusterSettings.description;
            this.hosts = clusterSettings.hosts;
            this.mode = clusterSettings.mode;
            this.requiredReplicaSetName = clusterSettings.requiredReplicaSetName;
            this.requiredClusterType = clusterSettings.requiredClusterType;
            this.localThresholdMS = clusterSettings.localThresholdMS;
            this.serverSelectionTimeoutMS = clusterSettings.serverSelectionTimeoutMS;
            this.maxWaitQueueSize = clusterSettings.maxWaitQueueSize;
            this.clusterListeners = new ArrayList(clusterSettings.clusterListeners);
            this.serverSelector = unpackServerSelector(clusterSettings.serverSelector);
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder hosts(List<ServerAddress> hosts) {
            Assertions.notNull("hosts", hosts);
            if (hosts.isEmpty()) {
                throw new IllegalArgumentException("hosts list may not be empty");
            }
            Set<ServerAddress> hostsSet = new LinkedHashSet<>(hosts.size());
            for (ServerAddress serverAddress : hosts) {
                Assertions.notNull("serverAddress", serverAddress);
                hostsSet.add(ServerAddressHelper.createServerAddress(serverAddress.getHost(), serverAddress.getPort()));
            }
            this.hosts = Collections.unmodifiableList(new ArrayList(hostsSet));
            return this;
        }

        public Builder mode(ClusterConnectionMode mode) {
            this.mode = (ClusterConnectionMode) Assertions.notNull("mode", mode);
            return this;
        }

        public Builder requiredReplicaSetName(String requiredReplicaSetName) {
            this.requiredReplicaSetName = requiredReplicaSetName;
            return this;
        }

        public Builder requiredClusterType(ClusterType requiredClusterType) {
            this.requiredClusterType = (ClusterType) Assertions.notNull("requiredClusterType", requiredClusterType);
            return this;
        }

        public Builder localThreshold(long localThreshold, TimeUnit timeUnit) {
            Assertions.isTrueArgument("localThreshold must be >= 0", localThreshold >= 0);
            this.localThresholdMS = TimeUnit.MILLISECONDS.convert(localThreshold, timeUnit);
            return this;
        }

        public Builder serverSelector(ServerSelector serverSelector) {
            this.serverSelector = serverSelector;
            return this;
        }

        public Builder serverSelectionTimeout(long serverSelectionTimeout, TimeUnit timeUnit) {
            this.serverSelectionTimeoutMS = TimeUnit.MILLISECONDS.convert(serverSelectionTimeout, timeUnit);
            return this;
        }

        public Builder maxWaitQueueSize(int maxWaitQueueSize) {
            this.maxWaitQueueSize = maxWaitQueueSize;
            return this;
        }

        public Builder addClusterListener(ClusterListener clusterListener) {
            Assertions.notNull("clusterListener", clusterListener);
            this.clusterListeners.add(clusterListener);
            return this;
        }

        public Builder applyConnectionString(ConnectionString connectionString) {
            if (connectionString.getHosts().size() == 1 && connectionString.getRequiredReplicaSetName() == null) {
                mode(ClusterConnectionMode.SINGLE).hosts(Collections.singletonList(ServerAddressHelper.createServerAddress(connectionString.getHosts().get(0))));
            } else {
                List<ServerAddress> seedList = new ArrayList<>();
                for (String cur : connectionString.getHosts()) {
                    seedList.add(ServerAddressHelper.createServerAddress(cur));
                }
                mode(ClusterConnectionMode.MULTIPLE).hosts(seedList);
            }
            requiredReplicaSetName(connectionString.getRequiredReplicaSetName());
            Integer maxConnectionPoolSize = connectionString.getMaxConnectionPoolSize();
            int maxSize = maxConnectionPoolSize != null ? maxConnectionPoolSize.intValue() : 100;
            Integer threadsAllowedToBlockForConnectionMultiplier = connectionString.getThreadsAllowedToBlockForConnectionMultiplier();
            int waitQueueMultiple = threadsAllowedToBlockForConnectionMultiplier != null ? threadsAllowedToBlockForConnectionMultiplier.intValue() : 5;
            maxWaitQueueSize(waitQueueMultiple * maxSize);
            Integer serverSelectionTimeout = connectionString.getServerSelectionTimeout();
            if (serverSelectionTimeout != null) {
                serverSelectionTimeout(serverSelectionTimeout.intValue(), TimeUnit.MILLISECONDS);
            }
            Integer localThreshold = connectionString.getLocalThreshold();
            if (localThreshold != null) {
                localThreshold(localThreshold.intValue(), TimeUnit.MILLISECONDS);
            }
            return this;
        }

        private ServerSelector unpackServerSelector(ServerSelector serverSelector) {
            if (serverSelector instanceof CompositeServerSelector) {
                return ((CompositeServerSelector) serverSelector).getServerSelectors().get(0);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ServerSelector packServerSelector() {
            ServerSelector latencyMinimizingServerSelector = new LatencyMinimizingServerSelector(this.localThresholdMS, TimeUnit.MILLISECONDS);
            if (this.serverSelector == null) {
                return latencyMinimizingServerSelector;
            }
            return new CompositeServerSelector(Arrays.asList(this.serverSelector, latencyMinimizingServerSelector));
        }

        public ClusterSettings build() {
            return new ClusterSettings(this);
        }
    }

    public String getDescription() {
        return this.description;
    }

    public List<ServerAddress> getHosts() {
        return this.hosts;
    }

    public ClusterConnectionMode getMode() {
        return this.mode;
    }

    public ClusterType getRequiredClusterType() {
        return this.requiredClusterType;
    }

    public String getRequiredReplicaSetName() {
        return this.requiredReplicaSetName;
    }

    public ServerSelector getServerSelector() {
        return this.serverSelector;
    }

    public long getServerSelectionTimeout(TimeUnit timeUnit) {
        return timeUnit.convert(this.serverSelectionTimeoutMS, TimeUnit.MILLISECONDS);
    }

    public long getLocalThreshold(TimeUnit timeUnit) {
        return timeUnit.convert(this.localThresholdMS, TimeUnit.MILLISECONDS);
    }

    public int getMaxWaitQueueSize() {
        return this.maxWaitQueueSize;
    }

    public List<ClusterListener> getClusterListeners() {
        return this.clusterListeners;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClusterSettings that = (ClusterSettings) o;
        if (this.maxWaitQueueSize != that.maxWaitQueueSize || this.serverSelectionTimeoutMS != that.serverSelectionTimeoutMS || this.localThresholdMS != that.localThresholdMS) {
            return false;
        }
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
            return false;
        }
        if (!this.hosts.equals(that.hosts) || this.mode != that.mode || this.requiredClusterType != that.requiredClusterType) {
            return false;
        }
        if (this.requiredReplicaSetName != null) {
            if (!this.requiredReplicaSetName.equals(that.requiredReplicaSetName)) {
                return false;
            }
        } else if (that.requiredReplicaSetName != null) {
            return false;
        }
        if (this.serverSelector != null) {
            if (!this.serverSelector.equals(that.serverSelector)) {
                return false;
            }
        } else if (that.serverSelector != null) {
            return false;
        }
        if (!this.clusterListeners.equals(that.clusterListeners)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.hosts.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.mode.hashCode())) + this.requiredClusterType.hashCode())) + (this.requiredReplicaSetName != null ? this.requiredReplicaSetName.hashCode() : 0))) + (this.serverSelector != null ? this.serverSelector.hashCode() : 0))) + (this.description != null ? this.description.hashCode() : 0))) + ((int) (this.serverSelectionTimeoutMS ^ (this.serverSelectionTimeoutMS >>> 32))))) + ((int) (this.localThresholdMS ^ (this.localThresholdMS >>> 32))))) + this.maxWaitQueueSize)) + this.clusterListeners.hashCode();
    }

    public String toString() {
        return "{hosts=" + this.hosts + ", mode=" + this.mode + ", requiredClusterType=" + this.requiredClusterType + ", requiredReplicaSetName='" + this.requiredReplicaSetName + "', serverSelector='" + this.serverSelector + "', clusterListeners='" + this.clusterListeners + "', serverSelectionTimeout='" + this.serverSelectionTimeoutMS + " ms', localThreshold='" + this.serverSelectionTimeoutMS + " ms', maxWaitQueueSize=" + this.maxWaitQueueSize + ", description='" + this.description + "'}";
    }

    public String getShortDescription() {
        return "{hosts=" + this.hosts + ", mode=" + this.mode + ", requiredClusterType=" + this.requiredClusterType + ", serverSelectionTimeout='" + this.serverSelectionTimeoutMS + " ms', maxWaitQueueSize=" + this.maxWaitQueueSize + (this.requiredReplicaSetName == null ? "" : ", requiredReplicaSetName='" + this.requiredReplicaSetName + '\'') + (this.description == null ? "" : ", description='" + this.description + '\'') + '}';
    }

    private ClusterSettings(Builder builder) {
        ClusterConnectionMode clusterConnectionMode;
        if (builder.hosts.size() <= 1 || builder.requiredClusterType != ClusterType.STANDALONE) {
            if (builder.mode == null || builder.mode != ClusterConnectionMode.SINGLE || builder.hosts.size() <= 1) {
                if (builder.requiredReplicaSetName != null) {
                    if (builder.requiredClusterType == ClusterType.UNKNOWN) {
                        builder.requiredClusterType = ClusterType.REPLICA_SET;
                    } else if (builder.requiredClusterType != ClusterType.REPLICA_SET) {
                        throw new IllegalArgumentException("When specifying a replica set name, only ClusterType.UNKNOWN and ClusterType.REPLICA_SET are valid.");
                    }
                }
                this.description = builder.description;
                this.hosts = builder.hosts;
                if (builder.mode != null) {
                    clusterConnectionMode = builder.mode;
                } else {
                    clusterConnectionMode = this.hosts.size() == 1 ? ClusterConnectionMode.SINGLE : ClusterConnectionMode.MULTIPLE;
                }
                this.mode = clusterConnectionMode;
                this.requiredReplicaSetName = builder.requiredReplicaSetName;
                this.requiredClusterType = builder.requiredClusterType;
                this.localThresholdMS = builder.localThresholdMS;
                this.serverSelector = builder.packServerSelector();
                this.serverSelectionTimeoutMS = builder.serverSelectionTimeoutMS;
                this.maxWaitQueueSize = builder.maxWaitQueueSize;
                this.clusterListeners = Collections.unmodifiableList(builder.clusterListeners);
                return;
            }
            throw new IllegalArgumentException("Can not directly connect to more than one server");
        }
        throw new IllegalArgumentException("Multiple hosts cannot be specified when using ClusterType.STANDALONE.");
    }
}
