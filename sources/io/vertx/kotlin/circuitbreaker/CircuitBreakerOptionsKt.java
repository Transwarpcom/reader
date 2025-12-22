package io.vertx.kotlin.circuitbreaker;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.circuitbreaker.CircuitBreakerOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CircuitBreakerOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\u001a\u0085\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0010\u001a\u0083\u0001\u0010\u0011\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"CircuitBreakerOptions", "Lio/vertx/circuitbreaker/CircuitBreakerOptions;", "failuresRollingWindow", "", "fallbackOnFailure", "", "maxFailures", "", "maxRetries", "metricsRollingBuckets", "metricsRollingWindow", "notificationAddress", "", "notificationPeriod", "resetTimeout", RtspHeaders.Values.TIMEOUT, "(Ljava/lang/Long;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)Lio/vertx/circuitbreaker/CircuitBreakerOptions;", "circuitBreakerOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/circuitbreaker/CircuitBreakerOptionsKt.class */
public final class CircuitBreakerOptionsKt {
    @NotNull
    public static /* synthetic */ CircuitBreakerOptions circuitBreakerOptionsOf$default(Long l, Boolean bool, Integer num, Integer num2, Integer num3, Long l2, String str, Long l3, Long l4, Long l5, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 32) != 0) {
            l2 = (Long) null;
        }
        if ((i & 64) != 0) {
            str = (String) null;
        }
        if ((i & 128) != 0) {
            l3 = (Long) null;
        }
        if ((i & 256) != 0) {
            l4 = (Long) null;
        }
        if ((i & 512) != 0) {
            l5 = (Long) null;
        }
        return circuitBreakerOptionsOf(l, bool, num, num2, num3, l2, str, l3, l4, l5);
    }

    @NotNull
    public static final CircuitBreakerOptions circuitBreakerOptionsOf(@Nullable Long failuresRollingWindow, @Nullable Boolean fallbackOnFailure, @Nullable Integer maxFailures, @Nullable Integer maxRetries, @Nullable Integer metricsRollingBuckets, @Nullable Long metricsRollingWindow, @Nullable String notificationAddress, @Nullable Long notificationPeriod, @Nullable Long resetTimeout, @Nullable Long timeout) {
        CircuitBreakerOptions $this$apply = new CircuitBreakerOptions();
        if (failuresRollingWindow != null) {
            $this$apply.setFailuresRollingWindow(failuresRollingWindow.longValue());
        }
        if (fallbackOnFailure != null) {
            $this$apply.setFallbackOnFailure(fallbackOnFailure.booleanValue());
        }
        if (maxFailures != null) {
            $this$apply.setMaxFailures(maxFailures.intValue());
        }
        if (maxRetries != null) {
            $this$apply.setMaxRetries(maxRetries.intValue());
        }
        if (metricsRollingBuckets != null) {
            $this$apply.setMetricsRollingBuckets(metricsRollingBuckets.intValue());
        }
        if (metricsRollingWindow != null) {
            $this$apply.setMetricsRollingWindow(metricsRollingWindow.longValue());
        }
        if (notificationAddress != null) {
            $this$apply.setNotificationAddress(notificationAddress);
        }
        if (notificationPeriod != null) {
            $this$apply.setNotificationPeriod(notificationPeriod.longValue());
        }
        if (resetTimeout != null) {
            $this$apply.setResetTimeout(resetTimeout.longValue());
        }
        if (timeout != null) {
            $this$apply.setTimeout(timeout.longValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "circuitBreakerOptionsOf(failuresRollingWindow, fallbackOnFailure, maxFailures, maxRetries, metricsRollingBuckets, metricsRollingWindow, notificationAddress, notificationPeriod, resetTimeout, timeout)"))
    @NotNull
    public static /* synthetic */ CircuitBreakerOptions CircuitBreakerOptions$default(Long l, Boolean bool, Integer num, Integer num2, Integer num3, Long l2, String str, Long l3, Long l4, Long l5, int i, Object obj) {
        if ((i & 1) != 0) {
            l = (Long) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 16) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 32) != 0) {
            l2 = (Long) null;
        }
        if ((i & 64) != 0) {
            str = (String) null;
        }
        if ((i & 128) != 0) {
            l3 = (Long) null;
        }
        if ((i & 256) != 0) {
            l4 = (Long) null;
        }
        if ((i & 512) != 0) {
            l5 = (Long) null;
        }
        return CircuitBreakerOptions(l, bool, num, num2, num3, l2, str, l3, l4, l5);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "circuitBreakerOptionsOf(failuresRollingWindow, fallbackOnFailure, maxFailures, maxRetries, metricsRollingBuckets, metricsRollingWindow, notificationAddress, notificationPeriod, resetTimeout, timeout)"))
    @NotNull
    public static final CircuitBreakerOptions CircuitBreakerOptions(@Nullable Long failuresRollingWindow, @Nullable Boolean fallbackOnFailure, @Nullable Integer maxFailures, @Nullable Integer maxRetries, @Nullable Integer metricsRollingBuckets, @Nullable Long metricsRollingWindow, @Nullable String notificationAddress, @Nullable Long notificationPeriod, @Nullable Long resetTimeout, @Nullable Long timeout) {
        CircuitBreakerOptions $this$apply = new CircuitBreakerOptions();
        if (failuresRollingWindow != null) {
            $this$apply.setFailuresRollingWindow(failuresRollingWindow.longValue());
        }
        if (fallbackOnFailure != null) {
            $this$apply.setFallbackOnFailure(fallbackOnFailure.booleanValue());
        }
        if (maxFailures != null) {
            $this$apply.setMaxFailures(maxFailures.intValue());
        }
        if (maxRetries != null) {
            $this$apply.setMaxRetries(maxRetries.intValue());
        }
        if (metricsRollingBuckets != null) {
            $this$apply.setMetricsRollingBuckets(metricsRollingBuckets.intValue());
        }
        if (metricsRollingWindow != null) {
            $this$apply.setMetricsRollingWindow(metricsRollingWindow.longValue());
        }
        if (notificationAddress != null) {
            $this$apply.setNotificationAddress(notificationAddress);
        }
        if (notificationPeriod != null) {
            $this$apply.setNotificationPeriod(notificationPeriod.longValue());
        }
        if (resetTimeout != null) {
            $this$apply.setResetTimeout(resetTimeout.longValue());
        }
        if (timeout != null) {
            $this$apply.setTimeout(timeout.longValue());
        }
        return $this$apply;
    }
}
