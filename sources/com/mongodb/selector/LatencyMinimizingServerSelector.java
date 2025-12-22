package com.mongodb.selector;

import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/selector/LatencyMinimizingServerSelector.class */
public class LatencyMinimizingServerSelector implements ServerSelector {
    private final long acceptableLatencyDifferenceNanos;

    public LatencyMinimizingServerSelector(long acceptableLatencyDifference, TimeUnit timeUnit) {
        this.acceptableLatencyDifferenceNanos = TimeUnit.NANOSECONDS.convert(acceptableLatencyDifference, timeUnit);
    }

    public long getAcceptableLatencyDifference(TimeUnit timeUnit) {
        return timeUnit.convert(this.acceptableLatencyDifferenceNanos, TimeUnit.NANOSECONDS);
    }

    @Override // com.mongodb.selector.ServerSelector
    public List<ServerDescription> select(ClusterDescription clusterDescription) {
        if (clusterDescription.getConnectionMode() != ClusterConnectionMode.MULTIPLE) {
            return clusterDescription.getAny();
        }
        return getServersWithAcceptableLatencyDifference(clusterDescription.getAny(), getFastestRoundTripTimeNanos(clusterDescription.getServerDescriptions()));
    }

    public String toString() {
        return "LatencyMinimizingServerSelector{acceptableLatencyDifference=" + TimeUnit.MILLISECONDS.convert(this.acceptableLatencyDifferenceNanos, TimeUnit.NANOSECONDS) + " ms}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LatencyMinimizingServerSelector that = (LatencyMinimizingServerSelector) o;
        return this.acceptableLatencyDifferenceNanos == that.acceptableLatencyDifferenceNanos;
    }

    public int hashCode() {
        return (int) (this.acceptableLatencyDifferenceNanos ^ (this.acceptableLatencyDifferenceNanos >>> 32));
    }

    private long getFastestRoundTripTimeNanos(List<ServerDescription> members) {
        long fastestRoundTripTime = Long.MAX_VALUE;
        for (ServerDescription cur : members) {
            if (cur.isOk() && cur.getRoundTripTimeNanos() < fastestRoundTripTime) {
                fastestRoundTripTime = cur.getRoundTripTimeNanos();
            }
        }
        return fastestRoundTripTime;
    }

    private List<ServerDescription> getServersWithAcceptableLatencyDifference(List<ServerDescription> servers, long bestPingTime) {
        List<ServerDescription> goodSecondaries = new ArrayList<>(servers.size());
        for (ServerDescription cur : servers) {
            if (cur.isOk() && cur.getRoundTripTimeNanos() - this.acceptableLatencyDifferenceNanos <= bestPingTime) {
                goodSecondaries.add(cur);
            }
        }
        return goodSecondaries;
    }
}
