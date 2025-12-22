package com.mongodb.connection;

import com.mongodb.ConnectionString;
import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.event.ConnectionPoolListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ConnectionPoolSettings.class */
public class ConnectionPoolSettings {
    private final List<ConnectionPoolListener> connectionPoolListeners;
    private final int maxSize;
    private final int minSize;
    private final int maxWaitQueueSize;
    private final long maxWaitTimeMS;
    private final long maxConnectionLifeTimeMS;
    private final long maxConnectionIdleTimeMS;
    private final long maintenanceInitialDelayMS;
    private final long maintenanceFrequencyMS;

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ConnectionPoolSettings connectionPoolSettings) {
        return builder().applySettings(connectionPoolSettings);
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/ConnectionPoolSettings$Builder.class */
    public static final class Builder {
        private int minSize;
        private long maxConnectionLifeTimeMS;
        private long maxConnectionIdleTimeMS;
        private long maintenanceInitialDelayMS;
        private List<ConnectionPoolListener> connectionPoolListeners = new ArrayList();
        private int maxSize = 100;
        private int maxWaitQueueSize = 500;
        private long maxWaitTimeMS = 120000;
        private long maintenanceFrequencyMS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES);

        Builder() {
        }

        public Builder applySettings(ConnectionPoolSettings connectionPoolSettings) {
            Assertions.notNull("connectionPoolSettings", connectionPoolSettings);
            this.connectionPoolListeners = new ArrayList(connectionPoolSettings.connectionPoolListeners);
            this.maxSize = connectionPoolSettings.maxSize;
            this.minSize = connectionPoolSettings.minSize;
            this.maxWaitQueueSize = connectionPoolSettings.maxWaitQueueSize;
            this.maxWaitTimeMS = connectionPoolSettings.maxWaitTimeMS;
            this.maxConnectionLifeTimeMS = connectionPoolSettings.maxConnectionLifeTimeMS;
            this.maxConnectionIdleTimeMS = connectionPoolSettings.maxConnectionIdleTimeMS;
            this.maintenanceInitialDelayMS = connectionPoolSettings.maintenanceInitialDelayMS;
            this.maintenanceFrequencyMS = connectionPoolSettings.maintenanceFrequencyMS;
            return this;
        }

        public Builder maxSize(int maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public Builder minSize(int minSize) {
            this.minSize = minSize;
            return this;
        }

        public Builder maxWaitQueueSize(int maxWaitQueueSize) {
            this.maxWaitQueueSize = maxWaitQueueSize;
            return this;
        }

        public Builder maxWaitTime(long maxWaitTime, TimeUnit timeUnit) {
            this.maxWaitTimeMS = TimeUnit.MILLISECONDS.convert(maxWaitTime, timeUnit);
            return this;
        }

        public Builder maxConnectionLifeTime(long maxConnectionLifeTime, TimeUnit timeUnit) {
            this.maxConnectionLifeTimeMS = TimeUnit.MILLISECONDS.convert(maxConnectionLifeTime, timeUnit);
            return this;
        }

        public Builder maxConnectionIdleTime(long maxConnectionIdleTime, TimeUnit timeUnit) {
            this.maxConnectionIdleTimeMS = TimeUnit.MILLISECONDS.convert(maxConnectionIdleTime, timeUnit);
            return this;
        }

        public Builder maintenanceInitialDelay(long maintenanceInitialDelay, TimeUnit timeUnit) {
            this.maintenanceInitialDelayMS = TimeUnit.MILLISECONDS.convert(maintenanceInitialDelay, timeUnit);
            return this;
        }

        public Builder maintenanceFrequency(long maintenanceFrequency, TimeUnit timeUnit) {
            this.maintenanceFrequencyMS = TimeUnit.MILLISECONDS.convert(maintenanceFrequency, timeUnit);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder addConnectionPoolListener(ConnectionPoolListener connectionPoolListener) {
            this.connectionPoolListeners.add(Assertions.notNull("connectionPoolListener", connectionPoolListener));
            return this;
        }

        public ConnectionPoolSettings build() {
            return new ConnectionPoolSettings(this);
        }

        public Builder applyConnectionString(ConnectionString connectionString) {
            Integer maxConnectionPoolSize = connectionString.getMaxConnectionPoolSize();
            if (maxConnectionPoolSize != null) {
                maxSize(maxConnectionPoolSize.intValue());
            }
            Integer minConnectionPoolSize = connectionString.getMinConnectionPoolSize();
            if (minConnectionPoolSize != null) {
                minSize(minConnectionPoolSize.intValue());
            }
            Integer maxWaitTime = connectionString.getMaxWaitTime();
            if (maxWaitTime != null) {
                maxWaitTime(maxWaitTime.intValue(), TimeUnit.MILLISECONDS);
            }
            Integer maxConnectionIdleTime = connectionString.getMaxConnectionIdleTime();
            if (maxConnectionIdleTime != null) {
                maxConnectionIdleTime(maxConnectionIdleTime.intValue(), TimeUnit.MILLISECONDS);
            }
            Integer maxConnectionLifeTime = connectionString.getMaxConnectionLifeTime();
            if (maxConnectionLifeTime != null) {
                maxConnectionLifeTime(maxConnectionLifeTime.intValue(), TimeUnit.MILLISECONDS);
            }
            Integer threadsAllowedToBlockForConnectionMultiplier = connectionString.getThreadsAllowedToBlockForConnectionMultiplier();
            if (threadsAllowedToBlockForConnectionMultiplier != null) {
                maxWaitQueueSize(threadsAllowedToBlockForConnectionMultiplier.intValue() * this.maxSize);
            }
            return this;
        }
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public int getMinSize() {
        return this.minSize;
    }

    public int getMaxWaitQueueSize() {
        return this.maxWaitQueueSize;
    }

    public long getMaxWaitTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.maxWaitTimeMS, TimeUnit.MILLISECONDS);
    }

    public long getMaxConnectionLifeTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.maxConnectionLifeTimeMS, TimeUnit.MILLISECONDS);
    }

    public long getMaxConnectionIdleTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.maxConnectionIdleTimeMS, TimeUnit.MILLISECONDS);
    }

    public long getMaintenanceInitialDelay(TimeUnit timeUnit) {
        return timeUnit.convert(this.maintenanceInitialDelayMS, TimeUnit.MILLISECONDS);
    }

    public long getMaintenanceFrequency(TimeUnit timeUnit) {
        return timeUnit.convert(this.maintenanceFrequencyMS, TimeUnit.MILLISECONDS);
    }

    public List<ConnectionPoolListener> getConnectionPoolListeners() {
        return this.connectionPoolListeners;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectionPoolSettings that = (ConnectionPoolSettings) o;
        if (this.maxConnectionIdleTimeMS != that.maxConnectionIdleTimeMS || this.maxConnectionLifeTimeMS != that.maxConnectionLifeTimeMS || this.maxSize != that.maxSize || this.minSize != that.minSize || this.maintenanceInitialDelayMS != that.maintenanceInitialDelayMS || this.maintenanceFrequencyMS != that.maintenanceFrequencyMS || this.maxWaitQueueSize != that.maxWaitQueueSize || this.maxWaitTimeMS != that.maxWaitTimeMS || !this.connectionPoolListeners.equals(that.connectionPoolListeners)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.maxSize;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.minSize)) + this.maxWaitQueueSize)) + ((int) (this.maxWaitTimeMS ^ (this.maxWaitTimeMS >>> 32))))) + ((int) (this.maxConnectionLifeTimeMS ^ (this.maxConnectionLifeTimeMS >>> 32))))) + ((int) (this.maxConnectionIdleTimeMS ^ (this.maxConnectionIdleTimeMS >>> 32))))) + ((int) (this.maintenanceInitialDelayMS ^ (this.maintenanceInitialDelayMS >>> 32))))) + ((int) (this.maintenanceFrequencyMS ^ (this.maintenanceFrequencyMS >>> 32))))) + this.connectionPoolListeners.hashCode();
    }

    public String toString() {
        return "ConnectionPoolSettings{maxSize=" + this.maxSize + ", minSize=" + this.minSize + ", maxWaitQueueSize=" + this.maxWaitQueueSize + ", maxWaitTimeMS=" + this.maxWaitTimeMS + ", maxConnectionLifeTimeMS=" + this.maxConnectionLifeTimeMS + ", maxConnectionIdleTimeMS=" + this.maxConnectionIdleTimeMS + ", maintenanceInitialDelayMS=" + this.maintenanceInitialDelayMS + ", maintenanceFrequencyMS=" + this.maintenanceFrequencyMS + ", connectionPoolListeners=" + this.connectionPoolListeners + '}';
    }

    ConnectionPoolSettings(Builder builder) {
        Assertions.isTrue("maxSize > 0", builder.maxSize > 0);
        Assertions.isTrue("minSize >= 0", builder.minSize >= 0);
        Assertions.isTrue("maxWaitQueueSize >= 0", builder.maxWaitQueueSize >= 0);
        Assertions.isTrue("maintenanceInitialDelayMS >= 0", builder.maintenanceInitialDelayMS >= 0);
        Assertions.isTrue("maxConnectionLifeTime >= 0", builder.maxConnectionLifeTimeMS >= 0);
        Assertions.isTrue("maxConnectionIdleTime >= 0", builder.maxConnectionIdleTimeMS >= 0);
        Assertions.isTrue("sizeMaintenanceFrequency > 0", builder.maintenanceFrequencyMS > 0);
        Assertions.isTrue("maxSize >= minSize", builder.maxSize >= builder.minSize);
        this.maxSize = builder.maxSize;
        this.minSize = builder.minSize;
        this.maxWaitQueueSize = builder.maxWaitQueueSize;
        this.maxWaitTimeMS = builder.maxWaitTimeMS;
        this.maxConnectionLifeTimeMS = builder.maxConnectionLifeTimeMS;
        this.maxConnectionIdleTimeMS = builder.maxConnectionIdleTimeMS;
        this.maintenanceInitialDelayMS = builder.maintenanceInitialDelayMS;
        this.maintenanceFrequencyMS = builder.maintenanceFrequencyMS;
        this.connectionPoolListeners = Collections.unmodifiableList(builder.connectionPoolListeners);
    }
}
