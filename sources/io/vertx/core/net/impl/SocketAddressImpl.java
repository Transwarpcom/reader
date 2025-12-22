package io.vertx.core.net.impl;

import io.vertx.core.impl.Arguments;
import io.vertx.core.net.SocketAddress;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/SocketAddressImpl.class */
public class SocketAddressImpl implements SocketAddress {
    private final String hostAddress;
    private final int port;
    private final String path;

    public SocketAddressImpl(int port, String host) {
        Objects.requireNonNull(host, "no null host accepted");
        Arguments.require(!host.isEmpty(), "no empty host accepted");
        Arguments.requireInRange(port, 0, 65535, "port p must be in range 0 <= p <= 65535");
        this.port = port;
        this.hostAddress = host;
        this.path = null;
    }

    public SocketAddressImpl(String path) {
        this.port = -1;
        this.hostAddress = null;
        this.path = path;
    }

    @Override // io.vertx.core.net.SocketAddress
    public String path() {
        return this.path;
    }

    @Override // io.vertx.core.net.SocketAddress
    public String host() {
        return this.hostAddress;
    }

    @Override // io.vertx.core.net.SocketAddress
    public int port() {
        return this.port;
    }

    public String toString() {
        if (this.path == null) {
            return this.hostAddress + ":" + this.port;
        }
        return this.path;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SocketAddressImpl that = (SocketAddressImpl) o;
        if (this.port != that.port) {
            return false;
        }
        if (this.hostAddress != null) {
            if (!this.hostAddress.equals(that.hostAddress)) {
                return false;
            }
        } else if (that.hostAddress != null) {
            return false;
        }
        return this.path != null ? this.path.equals(that.path) : that.path == null;
    }

    public int hashCode() {
        int result = this.hostAddress != null ? this.hostAddress.hashCode() : 0;
        return (31 * ((31 * result) + (this.path != null ? this.path.hashCode() : 0))) + this.port;
    }
}
