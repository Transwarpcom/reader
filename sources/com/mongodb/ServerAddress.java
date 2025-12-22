package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.lang.Nullable;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ServerAddress.class */
public class ServerAddress implements Serializable {
    private static final long serialVersionUID = 4027873363095395504L;
    private final String host;
    private final int port;

    public ServerAddress() {
        this(defaultHost(), defaultPort());
    }

    public ServerAddress(@Nullable String host) {
        this(host, defaultPort());
    }

    public ServerAddress(InetAddress inetAddress) {
        this(inetAddress.getHostName(), defaultPort());
    }

    public ServerAddress(InetAddress inetAddress, int port) {
        this(inetAddress.getHostName(), port);
    }

    public ServerAddress(InetSocketAddress inetSocketAddress) {
        this(inetSocketAddress.getAddress(), inetSocketAddress.getPort());
    }

    public ServerAddress(@Nullable String host, int port) throws NumberFormatException {
        String hostToUse = host;
        String hostToUse2 = (hostToUse == null ? defaultHost() : hostToUse).trim();
        hostToUse2 = hostToUse2.length() == 0 ? defaultHost() : hostToUse2;
        int portToUse = port;
        if (hostToUse2.startsWith("[")) {
            int idx = host.indexOf("]");
            if (idx == -1) {
                throw new IllegalArgumentException("an IPV6 address must be encosed with '[' and ']' according to RFC 2732.");
            }
            int portIdx = host.indexOf("]:");
            if (portIdx != -1) {
                if (port != defaultPort()) {
                    throw new IllegalArgumentException("can't specify port in construct and via host");
                }
                portToUse = Integer.parseInt(host.substring(portIdx + 2));
            }
            hostToUse2 = host.substring(1, idx);
        } else {
            int idx2 = hostToUse2.indexOf(":");
            int lastIdx = hostToUse2.lastIndexOf(":");
            if (idx2 == lastIdx && idx2 > 0) {
                if (port != defaultPort()) {
                    throw new IllegalArgumentException("can't specify port in construct and via host");
                }
                try {
                    portToUse = Integer.parseInt(hostToUse2.substring(idx2 + 1));
                    hostToUse2 = hostToUse2.substring(0, idx2).trim();
                } catch (NumberFormatException e) {
                    throw new MongoException("host and port should be specified in host:port format");
                }
            }
        }
        this.host = hostToUse2.toLowerCase();
        this.port = portToUse;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerAddress that = (ServerAddress) o;
        if (this.port != that.port || !this.host.equals(that.host)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.host.hashCode();
        return (31 * result) + this.port;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public InetSocketAddress getSocketAddress() {
        try {
            return new InetSocketAddress(InetAddress.getByName(this.host), this.port);
        } catch (UnknownHostException e) {
            throw new MongoSocketException(e.getMessage(), this, e);
        }
    }

    public String toString() {
        return this.host + ":" + this.port;
    }

    public static String defaultHost() {
        return "127.0.0.1";
    }

    public static int defaultPort() {
        return MongoProperties.DEFAULT_PORT;
    }

    @Deprecated
    public boolean sameHost(String hostName) {
        return equals(new ServerAddress(hostName));
    }
}
