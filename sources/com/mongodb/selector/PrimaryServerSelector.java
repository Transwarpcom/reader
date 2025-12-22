package com.mongodb.selector;

import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;
import java.util.List;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/selector/PrimaryServerSelector.class */
public final class PrimaryServerSelector implements ServerSelector {
    @Override // com.mongodb.selector.ServerSelector
    public List<ServerDescription> select(ClusterDescription clusterDescription) {
        return clusterDescription.getPrimaries();
    }

    public String toString() {
        return "PrimaryServerSelector";
    }
}
