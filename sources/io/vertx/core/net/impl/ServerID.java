package io.vertx.core.net.impl;

import java.io.Serializable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/ServerID.class */
public class ServerID implements Serializable {
    public int port;
    public String host;

    public ServerID(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public ServerID() {
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof ServerID)) {
            return false;
        }
        ServerID serverID = (ServerID) o;
        return this.port == serverID.port && this.host.equals(serverID.host);
    }

    public int hashCode() {
        int result = this.port;
        return (31 * result) + this.host.hashCode();
    }

    public String toString() {
        return this.host + ":" + this.port;
    }
}
