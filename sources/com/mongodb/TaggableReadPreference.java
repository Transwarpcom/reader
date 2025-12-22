package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ClusterType;
import com.mongodb.connection.ServerDescription;
import com.mongodb.lang.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.BsonValue;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TaggableReadPreference.class */
public abstract class TaggableReadPreference extends ReadPreference {
    private static final int SMALLEST_MAX_STALENESS_MS = 90000;
    private static final int IDLE_WRITE_PERIOD_MS = 10000;
    private final List<TagSet> tagSetList;
    private final Long maxStalenessMS;

    TaggableReadPreference() {
        this.tagSetList = new ArrayList();
        this.maxStalenessMS = null;
    }

    TaggableReadPreference(List<TagSet> tagSetList, @Nullable Long maxStaleness, TimeUnit timeUnit) {
        this.tagSetList = new ArrayList();
        Assertions.notNull("tagSetList", tagSetList);
        Assertions.isTrueArgument("maxStaleness is null or >= 0", maxStaleness == null || maxStaleness.longValue() >= 0);
        this.maxStalenessMS = maxStaleness == null ? null : Long.valueOf(TimeUnit.MILLISECONDS.convert(maxStaleness.longValue(), timeUnit));
        this.tagSetList.addAll(tagSetList);
    }

    @Override // com.mongodb.ReadPreference
    public boolean isSlaveOk() {
        return true;
    }

    @Override // com.mongodb.ReadPreference
    public BsonDocument toDocument() {
        BsonDocument readPrefObject = new BsonDocument("mode", new BsonString(getName()));
        if (!this.tagSetList.isEmpty()) {
            readPrefObject.put("tags", (BsonValue) tagsListToBsonArray());
        }
        if (this.maxStalenessMS != null) {
            readPrefObject.put("maxStalenessSeconds", (BsonValue) new BsonInt64(TimeUnit.MILLISECONDS.toSeconds(this.maxStalenessMS.longValue())));
        }
        return readPrefObject;
    }

    public List<TagSet> getTagSetList() {
        return Collections.unmodifiableList(this.tagSetList);
    }

    @Nullable
    public Long getMaxStaleness(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        if (this.maxStalenessMS == null) {
            return null;
        }
        return Long.valueOf(timeUnit.convert(this.maxStalenessMS.longValue(), TimeUnit.MILLISECONDS));
    }

    public String toString() {
        return "ReadPreference{name=" + getName() + (this.tagSetList.isEmpty() ? "" : ", tagSetList=" + this.tagSetList) + (this.maxStalenessMS == null ? "" : ", maxStalenessMS=" + this.maxStalenessMS) + '}';
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaggableReadPreference that = (TaggableReadPreference) o;
        if (this.maxStalenessMS != null) {
            if (!this.maxStalenessMS.equals(that.maxStalenessMS)) {
                return false;
            }
        } else if (that.maxStalenessMS != null) {
            return false;
        }
        if (!this.tagSetList.equals(that.tagSetList)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.tagSetList.hashCode();
        return (31 * ((31 * result) + getName().hashCode())) + (this.maxStalenessMS != null ? this.maxStalenessMS.hashCode() : 0);
    }

    @Override // com.mongodb.ReadPreference
    protected List<ServerDescription> chooseForNonReplicaSet(ClusterDescription clusterDescription) {
        return selectFreshServers(clusterDescription, clusterDescription.getAny());
    }

    protected static ClusterDescription copyClusterDescription(ClusterDescription clusterDescription, List<ServerDescription> selectedServers) {
        return new ClusterDescription(clusterDescription.getConnectionMode(), clusterDescription.getType(), selectedServers, clusterDescription.getClusterSettings(), clusterDescription.getServerSettings());
    }

    protected List<ServerDescription> selectFreshServers(ClusterDescription clusterDescription, List<ServerDescription> servers) {
        Long maxStaleness = getMaxStaleness(TimeUnit.MILLISECONDS);
        if (maxStaleness == null) {
            return servers;
        }
        if (clusterDescription.getServerSettings() == null) {
            throw new MongoConfigurationException("heartbeat frequency must be provided in cluster description");
        }
        if (!serversAreAllThreeDotFour(clusterDescription)) {
            throw new MongoConfigurationException("Servers must all be at least version 3.4 when max staleness is configured");
        }
        if (clusterDescription.getType() != ClusterType.REPLICA_SET) {
            return servers;
        }
        long heartbeatFrequencyMS = clusterDescription.getServerSettings().getHeartbeatFrequency(TimeUnit.MILLISECONDS);
        if (maxStaleness.longValue() < Math.max(90000L, heartbeatFrequencyMS + 10000)) {
            if (90000 > heartbeatFrequencyMS + 10000) {
                throw new MongoConfigurationException(String.format("Max staleness (%d sec) must be at least 90 seconds", getMaxStaleness(TimeUnit.SECONDS)));
            }
            throw new MongoConfigurationException(String.format("Max staleness (%d ms) must be at least the heartbeat period (%d ms) plus the idle write period (%d ms)", maxStaleness, Long.valueOf(heartbeatFrequencyMS), 10000));
        }
        List<ServerDescription> freshServers = new ArrayList<>(servers.size());
        ServerDescription primary = findPrimary(clusterDescription);
        if (primary != null) {
            for (ServerDescription cur : servers) {
                if (cur.isPrimary()) {
                    freshServers.add(cur);
                } else if (getStalenessOfSecondaryRelativeToPrimary(primary, cur, heartbeatFrequencyMS) <= maxStaleness.longValue()) {
                    freshServers.add(cur);
                }
            }
        } else {
            ServerDescription mostUpToDateSecondary = findMostUpToDateSecondary(clusterDescription);
            for (ServerDescription cur2 : servers) {
                if ((getLastWriteDateNonNull(mostUpToDateSecondary).getTime() - getLastWriteDateNonNull(cur2).getTime()) + heartbeatFrequencyMS <= maxStaleness.longValue()) {
                    freshServers.add(cur2);
                }
            }
        }
        return freshServers;
    }

    private long getStalenessOfSecondaryRelativeToPrimary(ServerDescription primary, ServerDescription serverDescription, long heartbeatFrequencyMS) {
        return ((getLastWriteDateNonNull(primary).getTime() + (serverDescription.getLastUpdateTime(TimeUnit.MILLISECONDS) - primary.getLastUpdateTime(TimeUnit.MILLISECONDS))) - getLastWriteDateNonNull(serverDescription).getTime()) + heartbeatFrequencyMS;
    }

    @Nullable
    private ServerDescription findPrimary(ClusterDescription clusterDescription) {
        for (ServerDescription cur : clusterDescription.getServerDescriptions()) {
            if (cur.isPrimary()) {
                return cur;
            }
        }
        return null;
    }

    private ServerDescription findMostUpToDateSecondary(ClusterDescription clusterDescription) {
        ServerDescription mostUpdateToDateSecondary = null;
        for (ServerDescription cur : clusterDescription.getServerDescriptions()) {
            if (cur.isSecondary() && (mostUpdateToDateSecondary == null || getLastWriteDateNonNull(cur).getTime() > getLastWriteDateNonNull(mostUpdateToDateSecondary).getTime())) {
                mostUpdateToDateSecondary = cur;
            }
        }
        if (mostUpdateToDateSecondary == null) {
            throw new MongoInternalException("Expected at least one secondary in cluster description: " + clusterDescription);
        }
        return mostUpdateToDateSecondary;
    }

    private Date getLastWriteDateNonNull(ServerDescription serverDescription) {
        Date lastWriteDate = serverDescription.getLastWriteDate();
        if (lastWriteDate == null) {
            throw new MongoClientException("lastWriteDate should not be null in " + serverDescription);
        }
        return lastWriteDate;
    }

    private boolean serversAreAllThreeDotFour(ClusterDescription clusterDescription) {
        for (ServerDescription cur : clusterDescription.getServerDescriptions()) {
            if (cur.isOk() && cur.getMaxWireVersion() < 5) {
                return false;
            }
        }
        return true;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TaggableReadPreference$SecondaryReadPreference.class */
    static class SecondaryReadPreference extends TaggableReadPreference {
        SecondaryReadPreference() {
        }

        SecondaryReadPreference(List<TagSet> tagSetList, @Nullable Long maxStaleness, TimeUnit timeUnit) {
            super(tagSetList, maxStaleness, timeUnit);
        }

        @Override // com.mongodb.ReadPreference
        public String getName() {
            return "secondary";
        }

        @Override // com.mongodb.ReadPreference
        protected List<ServerDescription> chooseForReplicaSet(ClusterDescription clusterDescription) {
            List<ServerDescription> selectedServers = selectFreshServers(clusterDescription, clusterDescription.getSecondaries());
            if (!getTagSetList().isEmpty()) {
                ClusterDescription nonStaleClusterDescription = copyClusterDescription(clusterDescription, selectedServers);
                selectedServers = Collections.emptyList();
                Iterator<TagSet> it = getTagSetList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TagSet tagSet = it.next();
                    List<ServerDescription> servers = nonStaleClusterDescription.getSecondaries(tagSet);
                    if (!servers.isEmpty()) {
                        selectedServers = servers;
                        break;
                    }
                }
            }
            return selectedServers;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TaggableReadPreference$SecondaryPreferredReadPreference.class */
    static class SecondaryPreferredReadPreference extends SecondaryReadPreference {
        SecondaryPreferredReadPreference() {
        }

        SecondaryPreferredReadPreference(List<TagSet> tagSetList, @Nullable Long maxStaleness, TimeUnit timeUnit) {
            super(tagSetList, maxStaleness, timeUnit);
        }

        @Override // com.mongodb.TaggableReadPreference.SecondaryReadPreference, com.mongodb.ReadPreference
        public String getName() {
            return "secondaryPreferred";
        }

        @Override // com.mongodb.TaggableReadPreference.SecondaryReadPreference, com.mongodb.ReadPreference
        protected List<ServerDescription> chooseForReplicaSet(ClusterDescription clusterDescription) {
            List<ServerDescription> selectedServers = super.chooseForReplicaSet(clusterDescription);
            if (selectedServers.isEmpty()) {
                selectedServers = clusterDescription.getPrimaries();
            }
            return selectedServers;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TaggableReadPreference$NearestReadPreference.class */
    static class NearestReadPreference extends TaggableReadPreference {
        NearestReadPreference() {
        }

        NearestReadPreference(List<TagSet> tagSetList, @Nullable Long maxStaleness, TimeUnit timeUnit) {
            super(tagSetList, maxStaleness, timeUnit);
        }

        @Override // com.mongodb.ReadPreference
        public String getName() {
            return "nearest";
        }

        @Override // com.mongodb.ReadPreference
        public List<ServerDescription> chooseForReplicaSet(ClusterDescription clusterDescription) {
            List<ServerDescription> selectedServers = selectFreshServers(clusterDescription, clusterDescription.getAnyPrimaryOrSecondary());
            if (!getTagSetList().isEmpty()) {
                ClusterDescription nonStaleClusterDescription = copyClusterDescription(clusterDescription, selectedServers);
                selectedServers = Collections.emptyList();
                Iterator<TagSet> it = getTagSetList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TagSet tagSet = it.next();
                    List<ServerDescription> servers = nonStaleClusterDescription.getAnyPrimaryOrSecondary(tagSet);
                    if (!servers.isEmpty()) {
                        selectedServers = servers;
                        break;
                    }
                }
            }
            return selectedServers;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/TaggableReadPreference$PrimaryPreferredReadPreference.class */
    static class PrimaryPreferredReadPreference extends SecondaryReadPreference {
        PrimaryPreferredReadPreference() {
        }

        PrimaryPreferredReadPreference(List<TagSet> tagSetList, @Nullable Long maxStaleness, TimeUnit timeUnit) {
            super(tagSetList, maxStaleness, timeUnit);
        }

        @Override // com.mongodb.TaggableReadPreference.SecondaryReadPreference, com.mongodb.ReadPreference
        public String getName() {
            return "primaryPreferred";
        }

        @Override // com.mongodb.TaggableReadPreference.SecondaryReadPreference, com.mongodb.ReadPreference
        protected List<ServerDescription> chooseForReplicaSet(ClusterDescription clusterDescription) {
            List<ServerDescription> selectedServers = selectFreshServers(clusterDescription, clusterDescription.getPrimaries());
            if (selectedServers.isEmpty()) {
                selectedServers = super.chooseForReplicaSet(clusterDescription);
            }
            return selectedServers;
        }
    }

    private BsonArray tagsListToBsonArray() {
        BsonArray bsonArray = new BsonArray();
        for (TagSet tagSet : this.tagSetList) {
            bsonArray.add((BsonValue) toDocument(tagSet));
        }
        return bsonArray;
    }

    private BsonDocument toDocument(TagSet tagSet) {
        BsonDocument document = new BsonDocument();
        Iterator<Tag> it = tagSet.iterator();
        while (it.hasNext()) {
            Tag tag = it.next();
            document.put(tag.getName(), (BsonValue) new BsonString(tag.getValue()));
        }
        return document;
    }
}
