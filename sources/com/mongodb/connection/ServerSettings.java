package com.mongodb.connection;

import com.mongodb.ConnectionString;
import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.event.ServerListener;
import com.mongodb.event.ServerMonitorListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ServerSettings.class */
public class ServerSettings {
    private final long heartbeatFrequencyMS;
    private final long minHeartbeatFrequencyMS;
    private final List<ServerListener> serverListeners;
    private final List<ServerMonitorListener> serverMonitorListeners;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ServerSettings serverSettings) {
        return builder().applySettings(serverSettings);
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ServerSettings$Builder.class */
    public static final class Builder {
        private long heartbeatFrequencyMS;
        private long minHeartbeatFrequencyMS;
        private List<ServerListener> serverListeners;
        private List<ServerMonitorListener> serverMonitorListeners;

        private Builder() {
            this.heartbeatFrequencyMS = 10000L;
            this.minHeartbeatFrequencyMS = 500L;
            this.serverListeners = new ArrayList();
            this.serverMonitorListeners = new ArrayList();
        }

        public Builder applySettings(ServerSettings serverSettings) {
            Assertions.notNull("serverSettings", serverSettings);
            this.heartbeatFrequencyMS = serverSettings.heartbeatFrequencyMS;
            this.minHeartbeatFrequencyMS = serverSettings.minHeartbeatFrequencyMS;
            this.serverListeners = new ArrayList(serverSettings.serverListeners);
            this.serverMonitorListeners = new ArrayList(serverSettings.serverMonitorListeners);
            return this;
        }

        public Builder heartbeatFrequency(long heartbeatFrequency, TimeUnit timeUnit) {
            this.heartbeatFrequencyMS = TimeUnit.MILLISECONDS.convert(heartbeatFrequency, timeUnit);
            return this;
        }

        public Builder minHeartbeatFrequency(long minHeartbeatFrequency, TimeUnit timeUnit) {
            this.minHeartbeatFrequencyMS = TimeUnit.MILLISECONDS.convert(minHeartbeatFrequency, timeUnit);
            return this;
        }

        public Builder addServerListener(ServerListener serverListener) {
            Assertions.notNull("serverListener", serverListener);
            this.serverListeners.add(serverListener);
            return this;
        }

        public Builder addServerMonitorListener(ServerMonitorListener serverMonitorListener) {
            Assertions.notNull("serverMonitorListener", serverMonitorListener);
            this.serverMonitorListeners.add(serverMonitorListener);
            return this;
        }

        public Builder applyConnectionString(ConnectionString connectionString) {
            Integer heartbeatFrequency = connectionString.getHeartbeatFrequency();
            if (heartbeatFrequency != null) {
                this.heartbeatFrequencyMS = heartbeatFrequency.intValue();
            }
            return this;
        }

        public ServerSettings build() {
            return new ServerSettings(this);
        }
    }

    public long getHeartbeatFrequency(TimeUnit timeUnit) {
        return timeUnit.convert(this.heartbeatFrequencyMS, TimeUnit.MILLISECONDS);
    }

    public long getMinHeartbeatFrequency(TimeUnit timeUnit) {
        return timeUnit.convert(this.minHeartbeatFrequencyMS, TimeUnit.MILLISECONDS);
    }

    public List<ServerListener> getServerListeners() {
        return this.serverListeners;
    }

    public List<ServerMonitorListener> getServerMonitorListeners() {
        return this.serverMonitorListeners;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServerSettings that = (ServerSettings) o;
        if (this.heartbeatFrequencyMS != that.heartbeatFrequencyMS || this.minHeartbeatFrequencyMS != that.minHeartbeatFrequencyMS || !this.serverListeners.equals(that.serverListeners) || !this.serverMonitorListeners.equals(that.serverMonitorListeners)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = (int) (this.heartbeatFrequencyMS ^ (this.heartbeatFrequencyMS >>> 32));
        return (31 * ((31 * ((31 * result) + ((int) (this.minHeartbeatFrequencyMS ^ (this.minHeartbeatFrequencyMS >>> 32))))) + this.serverListeners.hashCode())) + this.serverMonitorListeners.hashCode();
    }

    public String toString() {
        return "ServerSettings{heartbeatFrequencyMS=" + this.heartbeatFrequencyMS + ", minHeartbeatFrequencyMS=" + this.minHeartbeatFrequencyMS + ", serverListeners='" + this.serverListeners + "', serverMonitorListeners='" + this.serverMonitorListeners + "'}";
    }

    ServerSettings(Builder builder) {
        this.heartbeatFrequencyMS = builder.heartbeatFrequencyMS;
        this.minHeartbeatFrequencyMS = builder.minHeartbeatFrequencyMS;
        this.serverListeners = Collections.unmodifiableList(builder.serverListeners);
        this.serverMonitorListeners = Collections.unmodifiableList(builder.serverMonitorListeners);
    }
}
