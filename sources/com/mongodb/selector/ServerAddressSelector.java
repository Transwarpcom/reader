package com.mongodb.selector;

import com.mongodb.ServerAddress;
import com.mongodb.assertions.Assertions;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/selector/ServerAddressSelector.class */
public class ServerAddressSelector implements ServerSelector {
    private final ServerAddress serverAddress;

    public ServerAddressSelector(ServerAddress serverAddress) {
        this.serverAddress = (ServerAddress) Assertions.notNull("serverAddress", serverAddress);
    }

    public ServerAddress getServerAddress() {
        return this.serverAddress;
    }

    @Override // com.mongodb.selector.ServerSelector
    public List<ServerDescription> select(ClusterDescription clusterDescription) {
        return clusterDescription.getByServerAddress(this.serverAddress) != null ? Arrays.asList(clusterDescription.getByServerAddress(this.serverAddress)) : Collections.emptyList();
    }

    public String toString() {
        return "ServerAddressSelector{serverAddress=" + this.serverAddress + '}';
    }
}
