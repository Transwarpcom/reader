package com.mongodb.connection;

import cn.hutool.core.text.StrPool;
import com.mongodb.ServerAddress;
import com.mongodb.TagSet;
import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.internal.connection.DecimalFormatHelper;
import com.mongodb.internal.connection.Time;
import com.mongodb.lang.Nullable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.bson.types.ObjectId;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ServerDescription.class */
public class ServerDescription {
    public static final String MIN_DRIVER_SERVER_VERSION = "2.6";
    public static final int MIN_DRIVER_WIRE_VERSION = 1;
    public static final int MAX_DRIVER_WIRE_VERSION = 6;
    private static final int DEFAULT_MAX_DOCUMENT_SIZE = 16777216;
    private final ServerAddress address;
    private final ServerType type;
    private final String canonicalAddress;
    private final Set<String> hosts;
    private final Set<String> passives;
    private final Set<String> arbiters;
    private final String primary;
    private final int maxDocumentSize;
    private final TagSet tagSet;
    private final String setName;
    private final long roundTripTimeNanos;
    private final boolean ok;
    private final ServerConnectionState state;
    private final ServerVersion version;
    private final int minWireVersion;
    private final int maxWireVersion;
    private final ObjectId electionId;
    private final Integer setVersion;
    private final Date lastWriteDate;
    private final long lastUpdateTimeNanos;
    private final Integer logicalSessionTimeoutMinutes;
    private final Throwable exception;

    public static Builder builder() {
        return new Builder();
    }

    public String getCanonicalAddress() {
        return this.canonicalAddress;
    }

    public Integer getLogicalSessionTimeoutMinutes() {
        return this.logicalSessionTimeoutMinutes;
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ServerDescription$Builder.class */
    public static class Builder {
        private ServerAddress address;
        private String canonicalAddress;
        private String primary;
        private String setName;
        private long roundTripTimeNanos;
        private boolean ok;
        private ServerConnectionState state;
        private ObjectId electionId;
        private Integer setVersion;
        private Date lastWriteDate;
        private Integer logicalSessionTimeoutMinutes;
        private Throwable exception;
        private ServerType type = ServerType.UNKNOWN;
        private Set<String> hosts = Collections.emptySet();
        private Set<String> passives = Collections.emptySet();
        private Set<String> arbiters = Collections.emptySet();
        private int maxDocumentSize = 16777216;
        private TagSet tagSet = new TagSet();
        private ServerVersion version = new ServerVersion();
        private int minWireVersion = 0;
        private int maxWireVersion = 0;
        private long lastUpdateTimeNanos = Time.nanoTime();

        public Builder address(ServerAddress address) {
            this.address = address;
            return this;
        }

        public Builder canonicalAddress(String canonicalAddress) {
            this.canonicalAddress = canonicalAddress;
            return this;
        }

        public Builder type(ServerType type) {
            this.type = (ServerType) Assertions.notNull("type", type);
            return this;
        }

        public Builder hosts(Set<String> hosts) {
            this.hosts = hosts == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(hosts));
            return this;
        }

        public Builder passives(Set<String> passives) {
            this.passives = passives == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(passives));
            return this;
        }

        public Builder arbiters(Set<String> arbiters) {
            this.arbiters = arbiters == null ? Collections.emptySet() : Collections.unmodifiableSet(new HashSet(arbiters));
            return this;
        }

        public Builder primary(String primary) {
            this.primary = primary;
            return this;
        }

        public Builder maxDocumentSize(int maxDocumentSize) {
            this.maxDocumentSize = maxDocumentSize;
            return this;
        }

        public Builder tagSet(TagSet tagSet) {
            this.tagSet = tagSet == null ? new TagSet() : tagSet;
            return this;
        }

        public Builder roundTripTime(long roundTripTime, TimeUnit timeUnit) {
            this.roundTripTimeNanos = timeUnit.toNanos(roundTripTime);
            return this;
        }

        public Builder setName(String setName) {
            this.setName = setName;
            return this;
        }

        public Builder ok(boolean ok) {
            this.ok = ok;
            return this;
        }

        public Builder state(ServerConnectionState state) {
            this.state = state;
            return this;
        }

        public Builder version(ServerVersion version) {
            Assertions.notNull("version", version);
            this.version = version;
            return this;
        }

        public Builder minWireVersion(int minWireVersion) {
            this.minWireVersion = minWireVersion;
            return this;
        }

        public Builder maxWireVersion(int maxWireVersion) {
            this.maxWireVersion = maxWireVersion;
            return this;
        }

        public Builder electionId(ObjectId electionId) {
            this.electionId = electionId;
            return this;
        }

        public Builder setVersion(Integer setVersion) {
            this.setVersion = setVersion;
            return this;
        }

        public Builder lastWriteDate(Date lastWriteDate) {
            this.lastWriteDate = lastWriteDate;
            return this;
        }

        public Builder lastUpdateTimeNanos(long lastUpdateTimeNanos) {
            this.lastUpdateTimeNanos = lastUpdateTimeNanos;
            return this;
        }

        public Builder logicalSessionTimeoutMinutes(Integer logicalSessionTimeoutMinutes) {
            this.logicalSessionTimeoutMinutes = logicalSessionTimeoutMinutes;
            return this;
        }

        public Builder exception(Throwable exception) {
            this.exception = exception;
            return this;
        }

        public ServerDescription build() {
            return new ServerDescription(this);
        }
    }

    public boolean isCompatibleWithDriver() {
        if (isIncompatiblyOlderThanDriver() || isIncompatiblyNewerThanDriver()) {
            return false;
        }
        return true;
    }

    public boolean isIncompatiblyNewerThanDriver() {
        return this.ok && this.minWireVersion > 6;
    }

    public boolean isIncompatiblyOlderThanDriver() {
        return this.ok && this.maxWireVersion < 1;
    }

    public static int getDefaultMaxDocumentSize() {
        return 16777216;
    }

    public static int getDefaultMinWireVersion() {
        return 0;
    }

    public static int getDefaultMaxWireVersion() {
        return 0;
    }

    public ServerAddress getAddress() {
        return this.address;
    }

    public boolean isReplicaSetMember() {
        return this.type.getClusterType() == ClusterType.REPLICA_SET;
    }

    public boolean isShardRouter() {
        return this.type == ServerType.SHARD_ROUTER;
    }

    public boolean isStandAlone() {
        return this.type == ServerType.STANDALONE;
    }

    public boolean isPrimary() {
        return this.ok && (this.type == ServerType.REPLICA_SET_PRIMARY || this.type == ServerType.SHARD_ROUTER || this.type == ServerType.STANDALONE);
    }

    public boolean isSecondary() {
        return this.ok && (this.type == ServerType.REPLICA_SET_SECONDARY || this.type == ServerType.SHARD_ROUTER || this.type == ServerType.STANDALONE);
    }

    public Set<String> getHosts() {
        return this.hosts;
    }

    public Set<String> getPassives() {
        return this.passives;
    }

    public Set<String> getArbiters() {
        return this.arbiters;
    }

    public String getPrimary() {
        return this.primary;
    }

    public int getMaxDocumentSize() {
        return this.maxDocumentSize;
    }

    public TagSet getTagSet() {
        return this.tagSet;
    }

    public int getMinWireVersion() {
        return this.minWireVersion;
    }

    public int getMaxWireVersion() {
        return this.maxWireVersion;
    }

    public ObjectId getElectionId() {
        return this.electionId;
    }

    public Integer getSetVersion() {
        return this.setVersion;
    }

    @Nullable
    public Date getLastWriteDate() {
        return this.lastWriteDate;
    }

    public long getLastUpdateTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.lastUpdateTimeNanos, TimeUnit.NANOSECONDS);
    }

    public boolean hasTags(TagSet desiredTags) {
        if (!this.ok) {
            return false;
        }
        if (this.type == ServerType.STANDALONE || this.type == ServerType.SHARD_ROUTER) {
            return true;
        }
        return this.tagSet.containsAll(desiredTags);
    }

    public String getSetName() {
        return this.setName;
    }

    public boolean isOk() {
        return this.ok;
    }

    public ServerConnectionState getState() {
        return this.state;
    }

    public ServerType getType() {
        return this.type;
    }

    public ClusterType getClusterType() {
        return this.type.getClusterType();
    }

    public ServerVersion getVersion() {
        return this.version;
    }

    public long getRoundTripTimeNanos() {
        return this.roundTripTimeNanos;
    }

    public Throwable getException() {
        return this.exception;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerDescription that = (ServerDescription) o;
        if (this.maxDocumentSize != that.maxDocumentSize || this.ok != that.ok || !this.address.equals(that.address) || !this.arbiters.equals(that.arbiters)) {
            return false;
        }
        if (this.canonicalAddress != null) {
            if (!this.canonicalAddress.equals(that.canonicalAddress)) {
                return false;
            }
        } else if (that.canonicalAddress != null) {
            return false;
        }
        if (!this.hosts.equals(that.hosts) || !this.passives.equals(that.passives)) {
            return false;
        }
        if (this.primary != null) {
            if (!this.primary.equals(that.primary)) {
                return false;
            }
        } else if (that.primary != null) {
            return false;
        }
        if (this.setName != null) {
            if (!this.setName.equals(that.setName)) {
                return false;
            }
        } else if (that.setName != null) {
            return false;
        }
        if (this.state != that.state || !this.tagSet.equals(that.tagSet) || this.type != that.type || !this.version.equals(that.version) || this.minWireVersion != that.minWireVersion || this.maxWireVersion != that.maxWireVersion) {
            return false;
        }
        if (this.electionId != null) {
            if (!this.electionId.equals(that.electionId)) {
                return false;
            }
        } else if (that.electionId != null) {
            return false;
        }
        if (this.setVersion != null) {
            if (!this.setVersion.equals(that.setVersion)) {
                return false;
            }
        } else if (that.setVersion != null) {
            return false;
        }
        if (this.lastWriteDate != null) {
            if (!this.lastWriteDate.equals(that.lastWriteDate)) {
                return false;
            }
        } else if (that.lastWriteDate != null) {
            return false;
        }
        if (this.lastUpdateTimeNanos != that.lastUpdateTimeNanos) {
            return false;
        }
        if (this.logicalSessionTimeoutMinutes != null) {
            if (!this.logicalSessionTimeoutMinutes.equals(that.logicalSessionTimeoutMinutes)) {
                return false;
            }
        } else if (that.logicalSessionTimeoutMinutes != null) {
            return false;
        }
        Class<?> thisExceptionClass = this.exception != null ? this.exception.getClass() : null;
        Class<?> thatExceptionClass = that.exception != null ? that.exception.getClass() : null;
        if (thisExceptionClass != null) {
            if (!thisExceptionClass.equals(thatExceptionClass)) {
                return false;
            }
        } else if (thatExceptionClass != null) {
            return false;
        }
        String thisExceptionMessage = this.exception != null ? this.exception.getMessage() : null;
        String thatExceptionMessage = that.exception != null ? that.exception.getMessage() : null;
        if (thisExceptionMessage != null) {
            if (!thisExceptionMessage.equals(thatExceptionMessage)) {
                return false;
            }
            return true;
        }
        if (thatExceptionMessage != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.address.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.type.hashCode())) + (this.canonicalAddress != null ? this.canonicalAddress.hashCode() : 0))) + this.hosts.hashCode())) + this.passives.hashCode())) + this.arbiters.hashCode())) + (this.primary != null ? this.primary.hashCode() : 0))) + this.maxDocumentSize)) + this.tagSet.hashCode())) + (this.setName != null ? this.setName.hashCode() : 0))) + (this.electionId != null ? this.electionId.hashCode() : 0))) + (this.setVersion != null ? this.setVersion.hashCode() : 0))) + (this.lastWriteDate != null ? this.lastWriteDate.hashCode() : 0))) + ((int) (this.lastUpdateTimeNanos ^ (this.lastUpdateTimeNanos >>> 32))))) + (this.ok ? 1 : 0))) + this.state.hashCode())) + this.version.hashCode())) + this.minWireVersion)) + this.maxWireVersion)) + (this.logicalSessionTimeoutMinutes != null ? this.logicalSessionTimeoutMinutes.hashCode() : 0))) + (this.exception == null ? 0 : this.exception.getClass().hashCode()))) + (this.exception == null ? 0 : this.exception.getMessage().hashCode());
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sbAppend = new StringBuilder().append("ServerDescription{address=").append(this.address).append(", type=").append(this.type).append(", state=").append(this.state);
        if (this.state == ServerConnectionState.CONNECTED) {
            str = ", ok=" + this.ok + ", version=" + this.version + ", minWireVersion=" + this.minWireVersion + ", maxWireVersion=" + this.maxWireVersion + ", maxDocumentSize=" + this.maxDocumentSize + ", logicalSessionTimeoutMinutes=" + this.logicalSessionTimeoutMinutes + ", roundTripTimeNanos=" + this.roundTripTimeNanos;
        } else {
            str = "";
        }
        StringBuilder sbAppend2 = sbAppend.append(str);
        if (isReplicaSetMember()) {
            str2 = ", setName='" + this.setName + "', canonicalAddress=" + this.canonicalAddress + ", hosts=" + this.hosts + ", passives=" + this.passives + ", arbiters=" + this.arbiters + ", primary='" + this.primary + "', tagSet=" + this.tagSet + ", electionId=" + this.electionId + ", setVersion=" + this.setVersion + ", lastWriteDate=" + this.lastWriteDate + ", lastUpdateTimeNanos=" + this.lastUpdateTimeNanos;
        } else {
            str2 = "";
        }
        return sbAppend2.append(str2).append(this.exception == null ? "" : ", exception=" + translateExceptionToString()).append('}').toString();
    }

    public String getShortDescription() {
        return "{address=" + this.address + ", type=" + this.type + (!this.tagSet.iterator().hasNext() ? "" : ", " + this.tagSet) + (this.state == ServerConnectionState.CONNECTED ? ", roundTripTime=" + getRoundTripFormattedInMilliseconds() + " ms" : "") + ", state=" + this.state + (this.exception == null ? "" : ", exception=" + translateExceptionToString()) + '}';
    }

    private String translateExceptionToString() {
        StringBuilder builder = new StringBuilder();
        builder.append(StrPool.DELIM_START);
        builder.append(this.exception);
        builder.append("}");
        Throwable cause = this.exception.getCause();
        while (true) {
            Throwable cur = cause;
            if (cur != null) {
                builder.append(", caused by ");
                builder.append(StrPool.DELIM_START);
                builder.append(cur);
                builder.append("}");
                cause = cur.getCause();
            } else {
                return builder.toString();
            }
        }
    }

    private String getRoundTripFormattedInMilliseconds() {
        return DecimalFormatHelper.format("#0.0", (this.roundTripTimeNanos / 1000.0d) / 1000.0d);
    }

    ServerDescription(Builder builder) {
        this.address = (ServerAddress) Assertions.notNull("address", builder.address);
        this.type = (ServerType) Assertions.notNull("type", builder.type);
        this.state = (ServerConnectionState) Assertions.notNull("state", builder.state);
        this.version = (ServerVersion) Assertions.notNull("version", builder.version);
        this.canonicalAddress = builder.canonicalAddress;
        this.hosts = builder.hosts;
        this.passives = builder.passives;
        this.arbiters = builder.arbiters;
        this.primary = builder.primary;
        this.maxDocumentSize = builder.maxDocumentSize;
        this.tagSet = builder.tagSet;
        this.setName = builder.setName;
        this.roundTripTimeNanos = builder.roundTripTimeNanos;
        this.ok = builder.ok;
        this.minWireVersion = builder.minWireVersion;
        this.maxWireVersion = builder.maxWireVersion;
        this.electionId = builder.electionId;
        this.setVersion = builder.setVersion;
        this.lastWriteDate = builder.lastWriteDate;
        this.lastUpdateTimeNanos = builder.lastUpdateTimeNanos;
        this.logicalSessionTimeoutMinutes = builder.logicalSessionTimeoutMinutes;
        this.exception = builder.exception;
    }
}
