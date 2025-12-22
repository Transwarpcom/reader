package com.mongodb.selector;

import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/selector/CompositeServerSelector.class */
public final class CompositeServerSelector implements ServerSelector {
    private final List<ServerSelector> serverSelectors;

    public CompositeServerSelector(List<? extends ServerSelector> serverSelectors) {
        Assertions.notNull("serverSelectors", serverSelectors);
        if (serverSelectors.isEmpty()) {
            throw new IllegalArgumentException("Server selectors can not be an empty list");
        }
        ArrayList<ServerSelector> mergedServerSelectors = new ArrayList<>();
        for (ServerSelector cur : serverSelectors) {
            if (cur == null) {
                throw new IllegalArgumentException("Can not have a null server selector in the list of composed selectors");
            }
            if (cur instanceof CompositeServerSelector) {
                mergedServerSelectors.addAll(((CompositeServerSelector) cur).serverSelectors);
            } else {
                mergedServerSelectors.add(cur);
            }
        }
        this.serverSelectors = Collections.unmodifiableList(mergedServerSelectors);
    }

    public List<ServerSelector> getServerSelectors() {
        return this.serverSelectors;
    }

    @Override // com.mongodb.selector.ServerSelector
    public List<ServerDescription> select(ClusterDescription clusterDescription) {
        ClusterDescription curClusterDescription = clusterDescription;
        List<ServerDescription> choices = null;
        for (ServerSelector cur : this.serverSelectors) {
            choices = cur.select(curClusterDescription);
            curClusterDescription = new ClusterDescription(clusterDescription.getConnectionMode(), clusterDescription.getType(), choices, clusterDescription.getClusterSettings(), clusterDescription.getServerSettings());
        }
        return choices;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompositeServerSelector that = (CompositeServerSelector) o;
        if (this.serverSelectors.size() != that.serverSelectors.size()) {
            return false;
        }
        return this.serverSelectors.equals(that.serverSelectors);
    }

    public int hashCode() {
        if (this.serverSelectors != null) {
            return this.serverSelectors.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "{serverSelectors=" + this.serverSelectors + '}';
    }
}
