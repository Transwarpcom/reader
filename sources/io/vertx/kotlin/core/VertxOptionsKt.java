package io.vertx.kotlin.core;

import io.vertx.core.VertxOptions;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.metrics.MetricsOptions;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;

/* compiled from: VertxOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��H\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u001aÑ\u0002\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\rH\u0007¢\u0006\u0002\u0010&\u001aÏ\u0002\u0010'\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010&¨\u0006("}, d2 = {"VertxOptions", "Lio/vertx/core/VertxOptions;", "addressResolverOptions", "Lio/vertx/core/dns/AddressResolverOptions;", "blockedThreadCheckInterval", "", "blockedThreadCheckIntervalUnit", "Ljava/util/concurrent/TimeUnit;", "clusterHost", "", "clusterPingInterval", "clusterPingReplyInterval", "clusterPort", "", "clusterPublicHost", "clusterPublicPort", "clustered", "", "eventBusOptions", "Lio/vertx/core/eventbus/EventBusOptions;", "eventLoopPoolSize", "fileResolverCachingEnabled", "fileSystemOptions", "Lio/vertx/core/file/FileSystemOptions;", "haEnabled", "haGroup", "internalBlockingPoolSize", "maxEventLoopExecuteTime", "maxEventLoopExecuteTimeUnit", "maxWorkerExecuteTime", "maxWorkerExecuteTimeUnit", "metricsOptions", "Lio/vertx/core/metrics/MetricsOptions;", "preferNativeTransport", "quorumSize", "warningExceptionTime", "warningExceptionTimeUnit", "workerPoolSize", "(Lio/vertx/core/dns/AddressResolverOptions;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/core/eventbus/EventBusOptions;Ljava/lang/Integer;Ljava/lang/Boolean;Lio/vertx/core/file/FileSystemOptions;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Lio/vertx/core/metrics/MetricsOptions;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Long;Ljava/util/concurrent/TimeUnit;Ljava/lang/Integer;)Lio/vertx/core/VertxOptions;", "vertxOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/VertxOptionsKt.class */
public final class VertxOptionsKt {
    @NotNull
    public static /* synthetic */ VertxOptions vertxOptionsOf$default(AddressResolverOptions addressResolverOptions, Long l, TimeUnit timeUnit, String str, Long l2, Long l3, Integer num, String str2, Integer num2, Boolean bool, EventBusOptions eventBusOptions, Integer num3, Boolean bool2, FileSystemOptions fileSystemOptions, Boolean bool3, String str3, Integer num4, Long l4, TimeUnit timeUnit2, Long l5, TimeUnit timeUnit3, MetricsOptions metricsOptions, Boolean bool4, Integer num5, Long l6, TimeUnit timeUnit4, Integer num6, int i, Object obj) {
        if ((i & 1) != 0) {
            addressResolverOptions = (AddressResolverOptions) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            l2 = (Long) null;
        }
        if ((i & 32) != 0) {
            l3 = (Long) null;
        }
        if ((i & 64) != 0) {
            num = (Integer) null;
        }
        if ((i & 128) != 0) {
            str2 = (String) null;
        }
        if ((i & 256) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 512) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            eventBusOptions = (EventBusOptions) null;
        }
        if ((i & 2048) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 4096) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8192) != 0) {
            fileSystemOptions = (FileSystemOptions) null;
        }
        if ((i & 16384) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 32768) != 0) {
            str3 = (String) null;
        }
        if ((i & 65536) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            l4 = (Long) null;
        }
        if ((i & 262144) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            l5 = (Long) null;
        }
        if ((i & 1048576) != 0) {
            timeUnit3 = (TimeUnit) null;
        }
        if ((i & 2097152) != 0) {
            metricsOptions = (MetricsOptions) null;
        }
        if ((i & 4194304) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 8388608) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 16777216) != 0) {
            l6 = (Long) null;
        }
        if ((i & 33554432) != 0) {
            timeUnit4 = (TimeUnit) null;
        }
        if ((i & 67108864) != 0) {
            num6 = (Integer) null;
        }
        return vertxOptionsOf(addressResolverOptions, l, timeUnit, str, l2, l3, num, str2, num2, bool, eventBusOptions, num3, bool2, fileSystemOptions, bool3, str3, num4, l4, timeUnit2, l5, timeUnit3, metricsOptions, bool4, num5, l6, timeUnit4, num6);
    }

    @NotNull
    public static final VertxOptions vertxOptionsOf(@Nullable AddressResolverOptions addressResolverOptions, @Nullable Long blockedThreadCheckInterval, @Nullable TimeUnit blockedThreadCheckIntervalUnit, @Nullable String clusterHost, @Nullable Long clusterPingInterval, @Nullable Long clusterPingReplyInterval, @Nullable Integer clusterPort, @Nullable String clusterPublicHost, @Nullable Integer clusterPublicPort, @Nullable Boolean clustered, @Nullable EventBusOptions eventBusOptions, @Nullable Integer eventLoopPoolSize, @Nullable Boolean fileResolverCachingEnabled, @Nullable FileSystemOptions fileSystemOptions, @Nullable Boolean haEnabled, @Nullable String haGroup, @Nullable Integer internalBlockingPoolSize, @Nullable Long maxEventLoopExecuteTime, @Nullable TimeUnit maxEventLoopExecuteTimeUnit, @Nullable Long maxWorkerExecuteTime, @Nullable TimeUnit maxWorkerExecuteTimeUnit, @Nullable MetricsOptions metricsOptions, @Nullable Boolean preferNativeTransport, @Nullable Integer quorumSize, @Nullable Long warningExceptionTime, @Nullable TimeUnit warningExceptionTimeUnit, @Nullable Integer workerPoolSize) {
        VertxOptions $this$apply = new VertxOptions();
        if (addressResolverOptions != null) {
            $this$apply.setAddressResolverOptions(addressResolverOptions);
        }
        if (blockedThreadCheckInterval != null) {
            $this$apply.setBlockedThreadCheckInterval(blockedThreadCheckInterval.longValue());
        }
        if (blockedThreadCheckIntervalUnit != null) {
            $this$apply.setBlockedThreadCheckIntervalUnit(blockedThreadCheckIntervalUnit);
        }
        if (clusterHost != null) {
            $this$apply.setClusterHost(clusterHost);
        }
        if (clusterPingInterval != null) {
            $this$apply.setClusterPingInterval(clusterPingInterval.longValue());
        }
        if (clusterPingReplyInterval != null) {
            $this$apply.setClusterPingReplyInterval(clusterPingReplyInterval.longValue());
        }
        if (clusterPort != null) {
            $this$apply.setClusterPort(clusterPort.intValue());
        }
        if (clusterPublicHost != null) {
            $this$apply.setClusterPublicHost(clusterPublicHost);
        }
        if (clusterPublicPort != null) {
            $this$apply.setClusterPublicPort(clusterPublicPort.intValue());
        }
        if (clustered != null) {
            $this$apply.setClustered(clustered.booleanValue());
        }
        if (eventBusOptions != null) {
            $this$apply.setEventBusOptions(eventBusOptions);
        }
        if (eventLoopPoolSize != null) {
            $this$apply.setEventLoopPoolSize(eventLoopPoolSize.intValue());
        }
        if (fileResolverCachingEnabled != null) {
            $this$apply.setFileResolverCachingEnabled(fileResolverCachingEnabled.booleanValue());
        }
        if (fileSystemOptions != null) {
            $this$apply.setFileSystemOptions(fileSystemOptions);
        }
        if (haEnabled != null) {
            $this$apply.setHAEnabled(haEnabled.booleanValue());
        }
        if (haGroup != null) {
            $this$apply.setHAGroup(haGroup);
        }
        if (internalBlockingPoolSize != null) {
            $this$apply.setInternalBlockingPoolSize(internalBlockingPoolSize.intValue());
        }
        if (maxEventLoopExecuteTime != null) {
            $this$apply.setMaxEventLoopExecuteTime(maxEventLoopExecuteTime.longValue());
        }
        if (maxEventLoopExecuteTimeUnit != null) {
            $this$apply.setMaxEventLoopExecuteTimeUnit(maxEventLoopExecuteTimeUnit);
        }
        if (maxWorkerExecuteTime != null) {
            $this$apply.setMaxWorkerExecuteTime(maxWorkerExecuteTime.longValue());
        }
        if (maxWorkerExecuteTimeUnit != null) {
            $this$apply.setMaxWorkerExecuteTimeUnit(maxWorkerExecuteTimeUnit);
        }
        if (metricsOptions != null) {
            $this$apply.setMetricsOptions(metricsOptions);
        }
        if (preferNativeTransport != null) {
            $this$apply.setPreferNativeTransport(preferNativeTransport.booleanValue());
        }
        if (quorumSize != null) {
            $this$apply.setQuorumSize(quorumSize.intValue());
        }
        if (warningExceptionTime != null) {
            $this$apply.setWarningExceptionTime(warningExceptionTime.longValue());
        }
        if (warningExceptionTimeUnit != null) {
            $this$apply.setWarningExceptionTimeUnit(warningExceptionTimeUnit);
        }
        if (workerPoolSize != null) {
            $this$apply.setWorkerPoolSize(workerPoolSize.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxOptionsOf(addressResolverOptions, blockedThreadCheckInterval, blockedThreadCheckIntervalUnit, clusterHost, clusterPingInterval, clusterPingReplyInterval, clusterPort, clusterPublicHost, clusterPublicPort, clustered, eventBusOptions, eventLoopPoolSize, fileResolverCachingEnabled, fileSystemOptions, haEnabled, haGroup, internalBlockingPoolSize, maxEventLoopExecuteTime, maxEventLoopExecuteTimeUnit, maxWorkerExecuteTime, maxWorkerExecuteTimeUnit, metricsOptions, preferNativeTransport, quorumSize, warningExceptionTime, warningExceptionTimeUnit, workerPoolSize)"))
    @NotNull
    public static /* synthetic */ VertxOptions VertxOptions$default(AddressResolverOptions addressResolverOptions, Long l, TimeUnit timeUnit, String str, Long l2, Long l3, Integer num, String str2, Integer num2, Boolean bool, EventBusOptions eventBusOptions, Integer num3, Boolean bool2, FileSystemOptions fileSystemOptions, Boolean bool3, String str3, Integer num4, Long l4, TimeUnit timeUnit2, Long l5, TimeUnit timeUnit3, MetricsOptions metricsOptions, Boolean bool4, Integer num5, Long l6, TimeUnit timeUnit4, Integer num6, int i, Object obj) {
        if ((i & 1) != 0) {
            addressResolverOptions = (AddressResolverOptions) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            timeUnit = (TimeUnit) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            l2 = (Long) null;
        }
        if ((i & 32) != 0) {
            l3 = (Long) null;
        }
        if ((i & 64) != 0) {
            num = (Integer) null;
        }
        if ((i & 128) != 0) {
            str2 = (String) null;
        }
        if ((i & 256) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 512) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            eventBusOptions = (EventBusOptions) null;
        }
        if ((i & 2048) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 4096) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8192) != 0) {
            fileSystemOptions = (FileSystemOptions) null;
        }
        if ((i & 16384) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 32768) != 0) {
            str3 = (String) null;
        }
        if ((i & 65536) != 0) {
            num4 = (Integer) null;
        }
        if ((i & 131072) != 0) {
            l4 = (Long) null;
        }
        if ((i & 262144) != 0) {
            timeUnit2 = (TimeUnit) null;
        }
        if ((i & Opcodes.ASM8) != 0) {
            l5 = (Long) null;
        }
        if ((i & 1048576) != 0) {
            timeUnit3 = (TimeUnit) null;
        }
        if ((i & 2097152) != 0) {
            metricsOptions = (MetricsOptions) null;
        }
        if ((i & 4194304) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 8388608) != 0) {
            num5 = (Integer) null;
        }
        if ((i & 16777216) != 0) {
            l6 = (Long) null;
        }
        if ((i & 33554432) != 0) {
            timeUnit4 = (TimeUnit) null;
        }
        if ((i & 67108864) != 0) {
            num6 = (Integer) null;
        }
        return VertxOptions(addressResolverOptions, l, timeUnit, str, l2, l3, num, str2, num2, bool, eventBusOptions, num3, bool2, fileSystemOptions, bool3, str3, num4, l4, timeUnit2, l5, timeUnit3, metricsOptions, bool4, num5, l6, timeUnit4, num6);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxOptionsOf(addressResolverOptions, blockedThreadCheckInterval, blockedThreadCheckIntervalUnit, clusterHost, clusterPingInterval, clusterPingReplyInterval, clusterPort, clusterPublicHost, clusterPublicPort, clustered, eventBusOptions, eventLoopPoolSize, fileResolverCachingEnabled, fileSystemOptions, haEnabled, haGroup, internalBlockingPoolSize, maxEventLoopExecuteTime, maxEventLoopExecuteTimeUnit, maxWorkerExecuteTime, maxWorkerExecuteTimeUnit, metricsOptions, preferNativeTransport, quorumSize, warningExceptionTime, warningExceptionTimeUnit, workerPoolSize)"))
    @NotNull
    public static final VertxOptions VertxOptions(@Nullable AddressResolverOptions addressResolverOptions, @Nullable Long blockedThreadCheckInterval, @Nullable TimeUnit blockedThreadCheckIntervalUnit, @Nullable String clusterHost, @Nullable Long clusterPingInterval, @Nullable Long clusterPingReplyInterval, @Nullable Integer clusterPort, @Nullable String clusterPublicHost, @Nullable Integer clusterPublicPort, @Nullable Boolean clustered, @Nullable EventBusOptions eventBusOptions, @Nullable Integer eventLoopPoolSize, @Nullable Boolean fileResolverCachingEnabled, @Nullable FileSystemOptions fileSystemOptions, @Nullable Boolean haEnabled, @Nullable String haGroup, @Nullable Integer internalBlockingPoolSize, @Nullable Long maxEventLoopExecuteTime, @Nullable TimeUnit maxEventLoopExecuteTimeUnit, @Nullable Long maxWorkerExecuteTime, @Nullable TimeUnit maxWorkerExecuteTimeUnit, @Nullable MetricsOptions metricsOptions, @Nullable Boolean preferNativeTransport, @Nullable Integer quorumSize, @Nullable Long warningExceptionTime, @Nullable TimeUnit warningExceptionTimeUnit, @Nullable Integer workerPoolSize) {
        VertxOptions $this$apply = new VertxOptions();
        if (addressResolverOptions != null) {
            $this$apply.setAddressResolverOptions(addressResolverOptions);
        }
        if (blockedThreadCheckInterval != null) {
            $this$apply.setBlockedThreadCheckInterval(blockedThreadCheckInterval.longValue());
        }
        if (blockedThreadCheckIntervalUnit != null) {
            $this$apply.setBlockedThreadCheckIntervalUnit(blockedThreadCheckIntervalUnit);
        }
        if (clusterHost != null) {
            $this$apply.setClusterHost(clusterHost);
        }
        if (clusterPingInterval != null) {
            $this$apply.setClusterPingInterval(clusterPingInterval.longValue());
        }
        if (clusterPingReplyInterval != null) {
            $this$apply.setClusterPingReplyInterval(clusterPingReplyInterval.longValue());
        }
        if (clusterPort != null) {
            $this$apply.setClusterPort(clusterPort.intValue());
        }
        if (clusterPublicHost != null) {
            $this$apply.setClusterPublicHost(clusterPublicHost);
        }
        if (clusterPublicPort != null) {
            $this$apply.setClusterPublicPort(clusterPublicPort.intValue());
        }
        if (clustered != null) {
            $this$apply.setClustered(clustered.booleanValue());
        }
        if (eventBusOptions != null) {
            $this$apply.setEventBusOptions(eventBusOptions);
        }
        if (eventLoopPoolSize != null) {
            $this$apply.setEventLoopPoolSize(eventLoopPoolSize.intValue());
        }
        if (fileResolverCachingEnabled != null) {
            $this$apply.setFileResolverCachingEnabled(fileResolverCachingEnabled.booleanValue());
        }
        if (fileSystemOptions != null) {
            $this$apply.setFileSystemOptions(fileSystemOptions);
        }
        if (haEnabled != null) {
            $this$apply.setHAEnabled(haEnabled.booleanValue());
        }
        if (haGroup != null) {
            $this$apply.setHAGroup(haGroup);
        }
        if (internalBlockingPoolSize != null) {
            $this$apply.setInternalBlockingPoolSize(internalBlockingPoolSize.intValue());
        }
        if (maxEventLoopExecuteTime != null) {
            $this$apply.setMaxEventLoopExecuteTime(maxEventLoopExecuteTime.longValue());
        }
        if (maxEventLoopExecuteTimeUnit != null) {
            $this$apply.setMaxEventLoopExecuteTimeUnit(maxEventLoopExecuteTimeUnit);
        }
        if (maxWorkerExecuteTime != null) {
            $this$apply.setMaxWorkerExecuteTime(maxWorkerExecuteTime.longValue());
        }
        if (maxWorkerExecuteTimeUnit != null) {
            $this$apply.setMaxWorkerExecuteTimeUnit(maxWorkerExecuteTimeUnit);
        }
        if (metricsOptions != null) {
            $this$apply.setMetricsOptions(metricsOptions);
        }
        if (preferNativeTransport != null) {
            $this$apply.setPreferNativeTransport(preferNativeTransport.booleanValue());
        }
        if (quorumSize != null) {
            $this$apply.setQuorumSize(quorumSize.intValue());
        }
        if (warningExceptionTime != null) {
            $this$apply.setWarningExceptionTime(warningExceptionTime.longValue());
        }
        if (warningExceptionTimeUnit != null) {
            $this$apply.setWarningExceptionTimeUnit(warningExceptionTimeUnit);
        }
        if (workerPoolSize != null) {
            $this$apply.setWorkerPoolSize(workerPoolSize.intValue());
        }
        return $this$apply;
    }
}
