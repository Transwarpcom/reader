package io.vertx.core.eventbus.impl.clustered;

import io.vertx.core.net.impl.ServerID;
import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/impl/clustered/ClusterNodeInfo.class */
public class ClusterNodeInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public String nodeId;
    public ServerID serverID;

    public ClusterNodeInfo() {
    }

    public ClusterNodeInfo(String nodeId, ServerID serverID) {
        this.nodeId = nodeId;
        this.serverID = serverID;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClusterNodeInfo that = (ClusterNodeInfo) o;
        if (this.nodeId != null) {
            if (!this.nodeId.equals(that.nodeId)) {
                return false;
            }
        } else if (that.nodeId != null) {
            return false;
        }
        return this.serverID != null ? this.serverID.equals(that.serverID) : that.serverID == null;
    }

    public int hashCode() {
        int result = this.nodeId != null ? this.nodeId.hashCode() : 0;
        return (31 * result) + (this.serverID != null ? this.serverID.hashCode() : 0);
    }

    public String toString() {
        return this.nodeId + ":" + this.serverID.toString();
    }
}
