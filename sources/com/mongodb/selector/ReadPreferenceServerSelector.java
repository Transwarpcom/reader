package com.mongodb.selector;

import com.mongodb.ReadPreference;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ClusterConnectionMode;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/selector/ReadPreferenceServerSelector.class */
public class ReadPreferenceServerSelector implements ServerSelector {
    private final ReadPreference readPreference;

    public ReadPreferenceServerSelector(ReadPreference readPreference) {
        this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
    }

    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Override // com.mongodb.selector.ServerSelector
    public List<ServerDescription> select(ClusterDescription clusterDescription) {
        if (clusterDescription.getConnectionMode() == ClusterConnectionMode.SINGLE) {
            return clusterDescription.getAny();
        }
        return this.readPreference.choose(clusterDescription);
    }

    public String toString() {
        return "ReadPreferenceServerSelector{readPreference=" + this.readPreference + '}';
    }
}
