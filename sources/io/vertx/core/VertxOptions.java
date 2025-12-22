package io.vertx.core;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.impl.cpu.CpuCoreSensor;
import io.vertx.core.json.JsonObject;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/VertxOptions.class */
public class VertxOptions {
    public static final int DEFAULT_WORKER_POOL_SIZE = 20;
    public static final int DEFAULT_INTERNAL_BLOCKING_POOL_SIZE = 20;

    @Deprecated
    public static final boolean DEFAULT_CLUSTERED = false;

    @Deprecated
    public static final String DEFAULT_CLUSTER_HOST = "localhost";

    @Deprecated
    public static final int DEFAULT_CLUSTER_PORT = 0;

    @Deprecated
    public static final int DEFAULT_CLUSTER_PUBLIC_PORT = -1;
    public static final int DEFAULT_QUORUM_SIZE = 1;
    public static final String DEFAULT_HA_GROUP = "__DEFAULT__";
    public static final boolean DEFAULT_HA_ENABLED = false;
    public static final boolean DEFAULT_PREFER_NATIVE_TRANSPORT = false;
    private int eventLoopPoolSize;
    private int workerPoolSize;
    private int internalBlockingPoolSize;
    private long blockedThreadCheckInterval;
    private long maxEventLoopExecuteTime;
    private long maxWorkerExecuteTime;
    private ClusterManager clusterManager;
    private boolean haEnabled;
    private int quorumSize;
    private String haGroup;
    private MetricsOptions metricsOptions;
    private FileSystemOptions fileSystemOptions;
    private long warningExceptionTime;
    private EventBusOptions eventBusOptions;
    private AddressResolverOptions addressResolverOptions;
    private boolean preferNativeTransport;
    private TimeUnit maxEventLoopExecuteTimeUnit;
    private TimeUnit maxWorkerExecuteTimeUnit;
    private TimeUnit warningExceptionTimeUnit;
    private TimeUnit blockedThreadCheckIntervalUnit;
    public static final int DEFAULT_EVENT_LOOP_POOL_SIZE = 2 * CpuCoreSensor.availableProcessors();

    @Deprecated
    public static final String DEFAULT_CLUSTER_PUBLIC_HOST = null;

    @Deprecated
    public static final long DEFAULT_CLUSTER_PING_INTERVAL = TimeUnit.SECONDS.toMillis(20);

    @Deprecated
    public static final long DEFAULT_CLUSTER_PING_REPLY_INTERVAL = TimeUnit.SECONDS.toMillis(20);
    public static final long DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL = TimeUnit.SECONDS.toMillis(1);
    public static final TimeUnit DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT = TimeUnit.MILLISECONDS;
    public static final long DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME = TimeUnit.SECONDS.toNanos(2);
    public static final TimeUnit DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT = TimeUnit.NANOSECONDS;
    public static final long DEFAULT_MAX_WORKER_EXECUTE_TIME = TimeUnit.SECONDS.toNanos(60);
    public static final TimeUnit DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT = TimeUnit.NANOSECONDS;
    private static final long DEFAULT_WARNING_EXCEPTION_TIME = TimeUnit.SECONDS.toNanos(5);
    public static final TimeUnit DEFAULT_WARNING_EXCEPTION_TIME_UNIT = TimeUnit.NANOSECONDS;

    public VertxOptions() {
        this.eventLoopPoolSize = DEFAULT_EVENT_LOOP_POOL_SIZE;
        this.workerPoolSize = 20;
        this.internalBlockingPoolSize = 20;
        this.blockedThreadCheckInterval = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL;
        this.maxEventLoopExecuteTime = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
        this.maxWorkerExecuteTime = DEFAULT_MAX_WORKER_EXECUTE_TIME;
        this.haEnabled = false;
        this.quorumSize = 1;
        this.haGroup = DEFAULT_HA_GROUP;
        this.metricsOptions = new MetricsOptions();
        this.fileSystemOptions = new FileSystemOptions();
        this.warningExceptionTime = DEFAULT_WARNING_EXCEPTION_TIME;
        this.eventBusOptions = new EventBusOptions();
        this.addressResolverOptions = new AddressResolverOptions();
        this.preferNativeTransport = false;
        this.maxEventLoopExecuteTimeUnit = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;
        this.maxWorkerExecuteTimeUnit = DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
        this.warningExceptionTimeUnit = DEFAULT_WARNING_EXCEPTION_TIME_UNIT;
        this.blockedThreadCheckIntervalUnit = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT;
    }

    public VertxOptions(VertxOptions other) {
        this.eventLoopPoolSize = DEFAULT_EVENT_LOOP_POOL_SIZE;
        this.workerPoolSize = 20;
        this.internalBlockingPoolSize = 20;
        this.blockedThreadCheckInterval = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL;
        this.maxEventLoopExecuteTime = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME;
        this.maxWorkerExecuteTime = DEFAULT_MAX_WORKER_EXECUTE_TIME;
        this.haEnabled = false;
        this.quorumSize = 1;
        this.haGroup = DEFAULT_HA_GROUP;
        this.metricsOptions = new MetricsOptions();
        this.fileSystemOptions = new FileSystemOptions();
        this.warningExceptionTime = DEFAULT_WARNING_EXCEPTION_TIME;
        this.eventBusOptions = new EventBusOptions();
        this.addressResolverOptions = new AddressResolverOptions();
        this.preferNativeTransport = false;
        this.maxEventLoopExecuteTimeUnit = DEFAULT_MAX_EVENT_LOOP_EXECUTE_TIME_UNIT;
        this.maxWorkerExecuteTimeUnit = DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
        this.warningExceptionTimeUnit = DEFAULT_WARNING_EXCEPTION_TIME_UNIT;
        this.blockedThreadCheckIntervalUnit = DEFAULT_BLOCKED_THREAD_CHECK_INTERVAL_UNIT;
        this.eventLoopPoolSize = other.getEventLoopPoolSize();
        this.workerPoolSize = other.getWorkerPoolSize();
        this.blockedThreadCheckInterval = other.getBlockedThreadCheckInterval();
        this.maxEventLoopExecuteTime = other.getMaxEventLoopExecuteTime();
        this.maxWorkerExecuteTime = other.getMaxWorkerExecuteTime();
        this.internalBlockingPoolSize = other.getInternalBlockingPoolSize();
        this.clusterManager = other.getClusterManager();
        this.haEnabled = other.isHAEnabled();
        this.quorumSize = other.getQuorumSize();
        this.haGroup = other.getHAGroup();
        this.metricsOptions = other.getMetricsOptions() != null ? new MetricsOptions(other.getMetricsOptions()) : null;
        this.fileSystemOptions = other.getFileSystemOptions() != null ? new FileSystemOptions(other.getFileSystemOptions()) : null;
        this.warningExceptionTime = other.warningExceptionTime;
        this.eventBusOptions = new EventBusOptions(other.eventBusOptions);
        this.addressResolverOptions = other.addressResolverOptions != null ? new AddressResolverOptions() : null;
        this.maxEventLoopExecuteTimeUnit = other.maxEventLoopExecuteTimeUnit;
        this.maxWorkerExecuteTimeUnit = other.maxWorkerExecuteTimeUnit;
        this.warningExceptionTimeUnit = other.warningExceptionTimeUnit;
        this.blockedThreadCheckIntervalUnit = other.blockedThreadCheckIntervalUnit;
    }

    public VertxOptions(JsonObject json) {
        this();
        VertxOptionsConverter.fromJson(json, this);
    }

    public int getEventLoopPoolSize() {
        return this.eventLoopPoolSize;
    }

    public VertxOptions setEventLoopPoolSize(int eventLoopPoolSize) {
        if (eventLoopPoolSize < 1) {
            throw new IllegalArgumentException("eventLoopPoolSize must be > 0");
        }
        this.eventLoopPoolSize = eventLoopPoolSize;
        return this;
    }

    public int getWorkerPoolSize() {
        return this.workerPoolSize;
    }

    public VertxOptions setWorkerPoolSize(int workerPoolSize) {
        if (workerPoolSize < 1) {
            throw new IllegalArgumentException("workerPoolSize must be > 0");
        }
        this.workerPoolSize = workerPoolSize;
        return this;
    }

    @Deprecated
    public boolean isClustered() {
        return this.eventBusOptions.isClustered();
    }

    @Deprecated
    public VertxOptions setClustered(boolean clustered) {
        this.eventBusOptions.setClustered(clustered);
        return this;
    }

    @Deprecated
    public String getClusterHost() {
        return this.eventBusOptions.getHost();
    }

    @Deprecated
    public VertxOptions setClusterHost(String clusterHost) {
        this.eventBusOptions.setHost(clusterHost);
        return this;
    }

    @Deprecated
    public String getClusterPublicHost() {
        return getEventBusOptions().getClusterPublicHost();
    }

    @Deprecated
    public VertxOptions setClusterPublicHost(String clusterPublicHost) {
        getEventBusOptions().setClusterPublicHost(clusterPublicHost);
        return this;
    }

    @Deprecated
    public int getClusterPort() {
        return this.eventBusOptions.getPort();
    }

    @Deprecated
    public VertxOptions setClusterPort(int clusterPort) {
        this.eventBusOptions.setPort(clusterPort);
        return this;
    }

    @Deprecated
    public int getClusterPublicPort() {
        return this.eventBusOptions.getClusterPublicPort();
    }

    @Deprecated
    public VertxOptions setClusterPublicPort(int clusterPublicPort) {
        getEventBusOptions().setClusterPublicPort(clusterPublicPort);
        return this;
    }

    @Deprecated
    public long getClusterPingInterval() {
        return getEventBusOptions().getClusterPingInterval();
    }

    @Deprecated
    public VertxOptions setClusterPingInterval(long clusterPingInterval) {
        this.eventBusOptions.setClusterPingInterval(clusterPingInterval);
        return this;
    }

    @Deprecated
    public long getClusterPingReplyInterval() {
        return this.eventBusOptions.getClusterPingReplyInterval();
    }

    @Deprecated
    public VertxOptions setClusterPingReplyInterval(long clusterPingReplyInterval) {
        this.eventBusOptions.setClusterPingReplyInterval(clusterPingReplyInterval);
        return this;
    }

    public long getBlockedThreadCheckInterval() {
        return this.blockedThreadCheckInterval;
    }

    public VertxOptions setBlockedThreadCheckInterval(long blockedThreadCheckInterval) {
        if (blockedThreadCheckInterval < 1) {
            throw new IllegalArgumentException("blockedThreadCheckInterval must be > 0");
        }
        this.blockedThreadCheckInterval = blockedThreadCheckInterval;
        return this;
    }

    public long getMaxEventLoopExecuteTime() {
        return this.maxEventLoopExecuteTime;
    }

    public VertxOptions setMaxEventLoopExecuteTime(long maxEventLoopExecuteTime) {
        if (maxEventLoopExecuteTime < 1) {
            throw new IllegalArgumentException("maxEventLoopExecuteTime must be > 0");
        }
        this.maxEventLoopExecuteTime = maxEventLoopExecuteTime;
        return this;
    }

    public long getMaxWorkerExecuteTime() {
        return this.maxWorkerExecuteTime;
    }

    public VertxOptions setMaxWorkerExecuteTime(long maxWorkerExecuteTime) {
        if (maxWorkerExecuteTime < 1) {
            throw new IllegalArgumentException("maxWorkerpExecuteTime must be > 0");
        }
        this.maxWorkerExecuteTime = maxWorkerExecuteTime;
        return this;
    }

    public ClusterManager getClusterManager() {
        return this.clusterManager;
    }

    public VertxOptions setClusterManager(ClusterManager clusterManager) {
        this.clusterManager = clusterManager;
        return this;
    }

    public int getInternalBlockingPoolSize() {
        return this.internalBlockingPoolSize;
    }

    public VertxOptions setInternalBlockingPoolSize(int internalBlockingPoolSize) {
        if (internalBlockingPoolSize < 1) {
            throw new IllegalArgumentException("internalBlockingPoolSize must be > 0");
        }
        this.internalBlockingPoolSize = internalBlockingPoolSize;
        return this;
    }

    public boolean isHAEnabled() {
        return this.haEnabled;
    }

    public VertxOptions setHAEnabled(boolean haEnabled) {
        this.haEnabled = haEnabled;
        return this;
    }

    public int getQuorumSize() {
        return this.quorumSize;
    }

    public VertxOptions setQuorumSize(int quorumSize) {
        if (quorumSize < 1) {
            throw new IllegalArgumentException("quorumSize should be >= 1");
        }
        this.quorumSize = quorumSize;
        return this;
    }

    public String getHAGroup() {
        return this.haGroup;
    }

    public VertxOptions setHAGroup(String haGroup) {
        Objects.requireNonNull(haGroup, "ha group cannot be null");
        this.haGroup = haGroup;
        return this;
    }

    public MetricsOptions getMetricsOptions() {
        return this.metricsOptions;
    }

    public FileSystemOptions getFileSystemOptions() {
        return this.fileSystemOptions;
    }

    public VertxOptions setMetricsOptions(MetricsOptions metrics) {
        this.metricsOptions = metrics;
        return this;
    }

    public VertxOptions setFileSystemOptions(FileSystemOptions fileSystemOptions) {
        this.fileSystemOptions = fileSystemOptions;
        return this;
    }

    public long getWarningExceptionTime() {
        return this.warningExceptionTime;
    }

    public VertxOptions setWarningExceptionTime(long warningExceptionTime) {
        if (warningExceptionTime < 1) {
            throw new IllegalArgumentException("warningExceptionTime must be > 0");
        }
        this.warningExceptionTime = warningExceptionTime;
        return this;
    }

    public EventBusOptions getEventBusOptions() {
        return this.eventBusOptions;
    }

    public VertxOptions setEventBusOptions(EventBusOptions options) {
        Objects.requireNonNull(options);
        this.eventBusOptions = options;
        return this;
    }

    public AddressResolverOptions getAddressResolverOptions() {
        return this.addressResolverOptions;
    }

    public VertxOptions setAddressResolverOptions(AddressResolverOptions addressResolverOptions) {
        this.addressResolverOptions = addressResolverOptions;
        return this;
    }

    @Deprecated
    public boolean isFileResolverCachingEnabled() {
        if (this.fileSystemOptions == null) {
            return FileSystemOptions.DEFAULT_FILE_CACHING_ENABLED;
        }
        return this.fileSystemOptions.isFileCachingEnabled();
    }

    @Deprecated
    public VertxOptions setFileResolverCachingEnabled(boolean fileResolverCachingEnabled) {
        if (this.fileSystemOptions == null) {
            this.fileSystemOptions = new FileSystemOptions();
        }
        this.fileSystemOptions.setFileCachingEnabled(fileResolverCachingEnabled);
        return this;
    }

    public boolean getPreferNativeTransport() {
        return this.preferNativeTransport;
    }

    public VertxOptions setPreferNativeTransport(boolean preferNativeTransport) {
        this.preferNativeTransport = preferNativeTransport;
        return this;
    }

    public TimeUnit getMaxEventLoopExecuteTimeUnit() {
        return this.maxEventLoopExecuteTimeUnit;
    }

    public VertxOptions setMaxEventLoopExecuteTimeUnit(TimeUnit maxEventLoopExecuteTimeUnit) {
        this.maxEventLoopExecuteTimeUnit = maxEventLoopExecuteTimeUnit;
        return this;
    }

    public TimeUnit getMaxWorkerExecuteTimeUnit() {
        return this.maxWorkerExecuteTimeUnit;
    }

    public VertxOptions setMaxWorkerExecuteTimeUnit(TimeUnit maxWorkerExecuteTimeUnit) {
        this.maxWorkerExecuteTimeUnit = maxWorkerExecuteTimeUnit;
        return this;
    }

    public TimeUnit getWarningExceptionTimeUnit() {
        return this.warningExceptionTimeUnit;
    }

    public VertxOptions setWarningExceptionTimeUnit(TimeUnit warningExceptionTimeUnit) {
        this.warningExceptionTimeUnit = warningExceptionTimeUnit;
        return this;
    }

    public TimeUnit getBlockedThreadCheckIntervalUnit() {
        return this.blockedThreadCheckIntervalUnit;
    }

    public VertxOptions setBlockedThreadCheckIntervalUnit(TimeUnit blockedThreadCheckIntervalUnit) {
        this.blockedThreadCheckIntervalUnit = blockedThreadCheckIntervalUnit;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VertxOptions that = (VertxOptions) o;
        if (this.eventLoopPoolSize != that.eventLoopPoolSize || this.workerPoolSize != that.workerPoolSize || this.internalBlockingPoolSize != that.internalBlockingPoolSize || this.blockedThreadCheckInterval != that.blockedThreadCheckInterval || this.blockedThreadCheckIntervalUnit != that.blockedThreadCheckIntervalUnit || this.maxEventLoopExecuteTime != that.maxEventLoopExecuteTime || this.maxEventLoopExecuteTimeUnit != that.maxEventLoopExecuteTimeUnit || this.maxWorkerExecuteTime != that.maxWorkerExecuteTime || this.maxWorkerExecuteTimeUnit != that.maxWorkerExecuteTimeUnit || this.haEnabled != that.haEnabled || this.quorumSize != that.quorumSize || this.warningExceptionTime != that.warningExceptionTime || this.warningExceptionTimeUnit != that.warningExceptionTimeUnit) {
            return false;
        }
        if (this.clusterManager != null) {
            if (!this.clusterManager.equals(that.clusterManager)) {
                return false;
            }
        } else if (that.clusterManager != null) {
            return false;
        }
        if (this.haGroup != null) {
            if (!this.haGroup.equals(that.haGroup)) {
                return false;
            }
        } else if (that.haGroup != null) {
            return false;
        }
        if (this.eventBusOptions != null) {
            if (!this.eventBusOptions.equals(that.eventBusOptions)) {
                return false;
            }
        } else if (that.eventBusOptions != null) {
            return false;
        }
        if (this.addressResolverOptions != null) {
            if (!this.addressResolverOptions.equals(that.addressResolverOptions)) {
                return false;
            }
        } else if (that.addressResolverOptions != null) {
            return false;
        }
        if (this.preferNativeTransport != that.preferNativeTransport) {
            return false;
        }
        if (this.fileSystemOptions != null) {
            if (!this.fileSystemOptions.equals(that.fileSystemOptions)) {
                return false;
            }
        } else if (that.fileSystemOptions != null) {
            return false;
        }
        return this.metricsOptions == null ? that.metricsOptions == null : this.metricsOptions.equals(that.metricsOptions);
    }

    public int hashCode() {
        int result = this.eventLoopPoolSize;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.workerPoolSize)) + this.internalBlockingPoolSize)) + ((int) (this.blockedThreadCheckInterval ^ (this.blockedThreadCheckInterval >>> 32))))) + ((int) (this.maxEventLoopExecuteTime ^ (this.maxEventLoopExecuteTime >>> 32))))) + ((int) (this.maxWorkerExecuteTime ^ (this.maxWorkerExecuteTime >>> 32))))) + (this.clusterManager != null ? this.clusterManager.hashCode() : 0))) + (this.haEnabled ? 1 : 0))) + (this.preferNativeTransport ? 1 : 0))) + this.quorumSize)) + (this.haGroup != null ? this.haGroup.hashCode() : 0))) + (this.metricsOptions != null ? this.metricsOptions.hashCode() : 0))) + (this.fileSystemOptions != null ? this.fileSystemOptions.hashCode() : 0))) + (this.eventBusOptions != null ? this.eventBusOptions.hashCode() : 0))) + (this.addressResolverOptions != null ? this.addressResolverOptions.hashCode() : 0))) + ((int) (this.warningExceptionTime ^ (this.warningExceptionTime >>> 32))))) + (this.maxEventLoopExecuteTimeUnit != null ? this.maxEventLoopExecuteTimeUnit.hashCode() : 0))) + (this.maxWorkerExecuteTimeUnit != null ? this.maxWorkerExecuteTimeUnit.hashCode() : 0))) + (this.warningExceptionTimeUnit != null ? this.warningExceptionTimeUnit.hashCode() : 0))) + (this.blockedThreadCheckIntervalUnit != null ? this.blockedThreadCheckIntervalUnit.hashCode() : 0);
    }

    public String toString() {
        return "VertxOptions{eventLoopPoolSize=" + this.eventLoopPoolSize + ", workerPoolSize=" + this.workerPoolSize + ", internalBlockingPoolSize=" + this.internalBlockingPoolSize + ", blockedThreadCheckIntervalUnit=" + this.blockedThreadCheckIntervalUnit + ", blockedThreadCheckInterval=" + this.blockedThreadCheckInterval + ", maxEventLoopExecuteTimeUnit=" + this.maxEventLoopExecuteTimeUnit + ", maxEventLoopExecuteTime=" + this.maxEventLoopExecuteTime + ", maxWorkerExecuteTimeUnit=" + this.maxWorkerExecuteTimeUnit + ", maxWorkerExecuteTime=" + this.maxWorkerExecuteTime + ", clusterManager=" + this.clusterManager + ", haEnabled=" + this.haEnabled + ", preferNativeTransport=" + this.preferNativeTransport + ", quorumSize=" + this.quorumSize + ", haGroup='" + this.haGroup + "', metrics=" + this.metricsOptions + ", fileSystemOptions=" + this.fileSystemOptions + ", addressResolver=" + this.addressResolverOptions.toJson() + ", addressResolver=" + this.addressResolverOptions.toJson() + ", eventbus=" + this.eventBusOptions.toJson() + ", warningExceptionTimeUnit=" + this.warningExceptionTimeUnit + ", warningExceptionTime=" + this.warningExceptionTime + '}';
    }
}
