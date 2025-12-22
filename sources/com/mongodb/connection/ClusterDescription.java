package com.mongodb.connection;

import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.TagSet;
import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.selector.ReadPreferenceServerSelector;
import com.mongodb.selector.WritableServerSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ClusterDescription.class */
public class ClusterDescription {
    private final ClusterConnectionMode connectionMode;
    private final ClusterType type;
    private final List<ServerDescription> serverDescriptions;
    private final ClusterSettings clusterSettings;
    private final ServerSettings serverSettings;

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ClusterDescription$Predicate.class */
    private interface Predicate {
        boolean apply(ServerDescription serverDescription);
    }

    public ClusterDescription(ClusterConnectionMode connectionMode, ClusterType type, List<ServerDescription> serverDescriptions) {
        this(connectionMode, type, serverDescriptions, null, null);
    }

    public ClusterDescription(ClusterConnectionMode connectionMode, ClusterType type, List<ServerDescription> serverDescriptions, ClusterSettings clusterSettings, ServerSettings serverSettings) {
        Assertions.notNull("all", serverDescriptions);
        this.connectionMode = (ClusterConnectionMode) Assertions.notNull("connectionMode", connectionMode);
        this.type = (ClusterType) Assertions.notNull("type", type);
        this.serverDescriptions = new ArrayList(serverDescriptions);
        this.clusterSettings = clusterSettings;
        this.serverSettings = serverSettings;
    }

    public ClusterSettings getClusterSettings() {
        return this.clusterSettings;
    }

    public ServerSettings getServerSettings() {
        return this.serverSettings;
    }

    public boolean isCompatibleWithDriver() {
        for (ServerDescription cur : this.serverDescriptions) {
            if (!cur.isCompatibleWithDriver()) {
                return false;
            }
        }
        return true;
    }

    public ServerDescription findServerIncompatiblyOlderThanDriver() {
        for (ServerDescription cur : this.serverDescriptions) {
            if (cur.isIncompatiblyOlderThanDriver()) {
                return cur;
            }
        }
        return null;
    }

    public ServerDescription findServerIncompatiblyNewerThanDriver() {
        for (ServerDescription cur : this.serverDescriptions) {
            if (cur.isIncompatiblyNewerThanDriver()) {
                return cur;
            }
        }
        return null;
    }

    public boolean hasReadableServer(ReadPreference readPreference) {
        Assertions.notNull("readPreference", readPreference);
        return !new ReadPreferenceServerSelector(readPreference).select(this).isEmpty();
    }

    public boolean hasWritableServer() {
        return !new WritableServerSelector().select(this).isEmpty();
    }

    public ClusterConnectionMode getConnectionMode() {
        return this.connectionMode;
    }

    public ClusterType getType() {
        return this.type;
    }

    public List<ServerDescription> getServerDescriptions() {
        return Collections.unmodifiableList(this.serverDescriptions);
    }

    public Integer getLogicalSessionTimeoutMinutes() {
        Integer retVal = null;
        for (ServerDescription cur : getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.1
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return serverDescription.isPrimary() || serverDescription.isSecondary();
            }
        })) {
            if (cur.getLogicalSessionTimeoutMinutes() == null) {
                return null;
            }
            if (retVal == null) {
                retVal = cur.getLogicalSessionTimeoutMinutes();
            } else {
                retVal = Integer.valueOf(Math.min(retVal.intValue(), cur.getLogicalSessionTimeoutMinutes().intValue()));
            }
        }
        return retVal;
    }

    @Deprecated
    public Set<ServerDescription> getAll() {
        Set<ServerDescription> serverDescriptionSet = new TreeSet<>(new Comparator<ServerDescription>() { // from class: com.mongodb.connection.ClusterDescription.2
            @Override // java.util.Comparator
            public int compare(ServerDescription o1, ServerDescription o2) {
                int val = o1.getAddress().getHost().compareTo(o2.getAddress().getHost());
                if (val != 0) {
                    return val;
                }
                return integerCompare(o1.getAddress().getPort(), o2.getAddress().getPort());
            }

            private int integerCompare(int p1, int p2) {
                if (p1 < p2) {
                    return -1;
                }
                return p1 == p2 ? 0 : 1;
            }
        });
        serverDescriptionSet.addAll(this.serverDescriptions);
        return Collections.unmodifiableSet(serverDescriptionSet);
    }

    @Deprecated
    public ServerDescription getByServerAddress(ServerAddress serverAddress) {
        for (ServerDescription cur : this.serverDescriptions) {
            if (cur.isOk() && cur.getAddress().equals(serverAddress)) {
                return cur;
            }
        }
        return null;
    }

    @Deprecated
    public List<ServerDescription> getPrimaries() {
        return getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.3
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return serverDescription.isPrimary();
            }
        });
    }

    @Deprecated
    public List<ServerDescription> getSecondaries() {
        return getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.4
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return serverDescription.isSecondary();
            }
        });
    }

    @Deprecated
    public List<ServerDescription> getSecondaries(final TagSet tagSet) {
        return getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.5
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return serverDescription.isSecondary() && serverDescription.hasTags(tagSet);
            }
        });
    }

    @Deprecated
    public List<ServerDescription> getAny() {
        return getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.6
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return serverDescription.isOk();
            }
        });
    }

    @Deprecated
    public List<ServerDescription> getAnyPrimaryOrSecondary() {
        return getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.7
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return serverDescription.isPrimary() || serverDescription.isSecondary();
            }
        });
    }

    @Deprecated
    public List<ServerDescription> getAnyPrimaryOrSecondary(final TagSet tagSet) {
        return getServersByPredicate(new Predicate() { // from class: com.mongodb.connection.ClusterDescription.8
            @Override // com.mongodb.connection.ClusterDescription.Predicate
            public boolean apply(ServerDescription serverDescription) {
                return (serverDescription.isPrimary() || serverDescription.isSecondary()) && serverDescription.hasTags(tagSet);
            }
        });
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClusterDescription that = (ClusterDescription) o;
        if (this.connectionMode != that.connectionMode || this.type != that.type || this.serverDescriptions.size() != that.serverDescriptions.size() || !this.serverDescriptions.containsAll(that.serverDescriptions)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.connectionMode.hashCode();
        return (31 * ((31 * result) + this.type.hashCode())) + this.serverDescriptions.hashCode();
    }

    public String toString() {
        return "ClusterDescription{type=" + getType() + ", connectionMode=" + this.connectionMode + ", serverDescriptions=" + this.serverDescriptions + '}';
    }

    public String getShortDescription() {
        StringBuilder serverDescriptions = new StringBuilder();
        String delimiter = "";
        for (ServerDescription cur : this.serverDescriptions) {
            serverDescriptions.append(delimiter).append(cur.getShortDescription());
            delimiter = ", ";
        }
        return String.format("{type=%s, servers=[%s]", this.type, serverDescriptions);
    }

    private List<ServerDescription> getServersByPredicate(Predicate predicate) {
        List<ServerDescription> membersByTag = new ArrayList<>();
        for (ServerDescription cur : this.serverDescriptions) {
            if (predicate.apply(cur)) {
                membersByTag.add(cur);
            }
        }
        return membersByTag;
    }
}
