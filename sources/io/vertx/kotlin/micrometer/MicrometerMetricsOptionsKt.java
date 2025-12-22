package io.vertx.kotlin.micrometer;

import io.vertx.micrometer.Label;
import io.vertx.micrometer.Match;
import io.vertx.micrometer.MetricsDomain;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxInfluxDbOptions;
import io.vertx.micrometer.VertxJmxMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MicrometerMetricsOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��B\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u009d\u0001\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00032\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007¢\u0006\u0002\u0010\u0015\u001a\u009b\u0001\u0010\u0016\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00032\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015¨\u0006\u0017"}, d2 = {"MicrometerMetricsOptions", "Lio/vertx/micrometer/MicrometerMetricsOptions;", "disabledMetricsCategories", "", "Lio/vertx/micrometer/MetricsDomain;", "enabled", "", "influxDbOptions", "Lio/vertx/micrometer/VertxInfluxDbOptions;", "jmxMetricsOptions", "Lio/vertx/micrometer/VertxJmxMetricsOptions;", "jvmMetricsEnabled", "labelMatches", "Lio/vertx/micrometer/Match;", "labelMatchs", "labels", "Lio/vertx/micrometer/Label;", "prometheusOptions", "Lio/vertx/micrometer/VertxPrometheusOptions;", "registryName", "", "(Ljava/lang/Iterable;Ljava/lang/Boolean;Lio/vertx/micrometer/VertxInfluxDbOptions;Lio/vertx/micrometer/VertxJmxMetricsOptions;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Lio/vertx/micrometer/VertxPrometheusOptions;Ljava/lang/String;)Lio/vertx/micrometer/MicrometerMetricsOptions;", "micrometerMetricsOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/micrometer/MicrometerMetricsOptionsKt.class */
public final class MicrometerMetricsOptionsKt {
    @NotNull
    public static /* synthetic */ MicrometerMetricsOptions micrometerMetricsOptionsOf$default(Iterable iterable, Boolean bool, VertxInfluxDbOptions vertxInfluxDbOptions, VertxJmxMetricsOptions vertxJmxMetricsOptions, Boolean bool2, Iterable iterable2, Iterable iterable3, Iterable iterable4, VertxPrometheusOptions vertxPrometheusOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            vertxInfluxDbOptions = (VertxInfluxDbOptions) null;
        }
        if ((i & 8) != 0) {
            vertxJmxMetricsOptions = (VertxJmxMetricsOptions) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            vertxPrometheusOptions = (VertxPrometheusOptions) null;
        }
        if ((i & 512) != 0) {
            str = (String) null;
        }
        return micrometerMetricsOptionsOf(iterable, bool, vertxInfluxDbOptions, vertxJmxMetricsOptions, bool2, iterable2, iterable3, iterable4, vertxPrometheusOptions, str);
    }

    @NotNull
    public static final MicrometerMetricsOptions micrometerMetricsOptionsOf(@Nullable Iterable<? extends MetricsDomain> iterable, @Nullable Boolean enabled, @Nullable VertxInfluxDbOptions influxDbOptions, @Nullable VertxJmxMetricsOptions jmxMetricsOptions, @Nullable Boolean jvmMetricsEnabled, @Nullable Iterable<? extends Match> iterable2, @Nullable Iterable<? extends Match> iterable3, @Nullable Iterable<? extends Label> iterable4, @Nullable VertxPrometheusOptions prometheusOptions, @Nullable String registryName) {
        MicrometerMetricsOptions $this$apply = new MicrometerMetricsOptions();
        if (iterable != null) {
            $this$apply.setDisabledMetricsCategories(CollectionsKt.toSet(iterable));
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (influxDbOptions != null) {
            $this$apply.setInfluxDbOptions(influxDbOptions);
        }
        if (jmxMetricsOptions != null) {
            $this$apply.setJmxMetricsOptions(jmxMetricsOptions);
        }
        if (jvmMetricsEnabled != null) {
            $this$apply.setJvmMetricsEnabled(jvmMetricsEnabled.booleanValue());
        }
        if (iterable2 != null) {
            $this$apply.setLabelMatches(CollectionsKt.toList(iterable2));
        }
        if (iterable3 != null) {
            for (Match item : iterable3) {
                $this$apply.addLabelMatch(item);
            }
        }
        if (iterable4 != null) {
            $this$apply.setLabels(CollectionsKt.toSet(iterable4));
        }
        if (prometheusOptions != null) {
            $this$apply.setPrometheusOptions(prometheusOptions);
        }
        if (registryName != null) {
            $this$apply.setRegistryName(registryName);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "micrometerMetricsOptionsOf(disabledMetricsCategories, enabled, influxDbOptions, jmxMetricsOptions, jvmMetricsEnabled, labelMatches, labelMatchs, labels, prometheusOptions, registryName)"))
    @NotNull
    public static /* synthetic */ MicrometerMetricsOptions MicrometerMetricsOptions$default(Iterable iterable, Boolean bool, VertxInfluxDbOptions vertxInfluxDbOptions, VertxJmxMetricsOptions vertxJmxMetricsOptions, Boolean bool2, Iterable iterable2, Iterable iterable3, Iterable iterable4, VertxPrometheusOptions vertxPrometheusOptions, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            vertxInfluxDbOptions = (VertxInfluxDbOptions) null;
        }
        if ((i & 8) != 0) {
            vertxJmxMetricsOptions = (VertxJmxMetricsOptions) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            vertxPrometheusOptions = (VertxPrometheusOptions) null;
        }
        if ((i & 512) != 0) {
            str = (String) null;
        }
        return MicrometerMetricsOptions(iterable, bool, vertxInfluxDbOptions, vertxJmxMetricsOptions, bool2, iterable2, iterable3, iterable4, vertxPrometheusOptions, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "micrometerMetricsOptionsOf(disabledMetricsCategories, enabled, influxDbOptions, jmxMetricsOptions, jvmMetricsEnabled, labelMatches, labelMatchs, labels, prometheusOptions, registryName)"))
    @NotNull
    public static final MicrometerMetricsOptions MicrometerMetricsOptions(@Nullable Iterable<? extends MetricsDomain> iterable, @Nullable Boolean enabled, @Nullable VertxInfluxDbOptions influxDbOptions, @Nullable VertxJmxMetricsOptions jmxMetricsOptions, @Nullable Boolean jvmMetricsEnabled, @Nullable Iterable<? extends Match> iterable2, @Nullable Iterable<? extends Match> iterable3, @Nullable Iterable<? extends Label> iterable4, @Nullable VertxPrometheusOptions prometheusOptions, @Nullable String registryName) {
        MicrometerMetricsOptions $this$apply = new MicrometerMetricsOptions();
        if (iterable != null) {
            $this$apply.setDisabledMetricsCategories(CollectionsKt.toSet(iterable));
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (influxDbOptions != null) {
            $this$apply.setInfluxDbOptions(influxDbOptions);
        }
        if (jmxMetricsOptions != null) {
            $this$apply.setJmxMetricsOptions(jmxMetricsOptions);
        }
        if (jvmMetricsEnabled != null) {
            $this$apply.setJvmMetricsEnabled(jvmMetricsEnabled.booleanValue());
        }
        if (iterable2 != null) {
            $this$apply.setLabelMatches(CollectionsKt.toList(iterable2));
        }
        if (iterable3 != null) {
            for (Match item : iterable3) {
                $this$apply.addLabelMatch(item);
            }
        }
        if (iterable4 != null) {
            $this$apply.setLabels(CollectionsKt.toSet(iterable4));
        }
        if (prometheusOptions != null) {
            $this$apply.setPrometheusOptions(prometheusOptions);
        }
        if (registryName != null) {
            $this$apply.setRegistryName(registryName);
        }
        return $this$apply;
    }
}
