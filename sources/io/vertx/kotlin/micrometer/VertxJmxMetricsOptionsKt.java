package io.vertx.kotlin.micrometer;

import io.vertx.micrometer.VertxJmxMetricsOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VertxJmxMetricsOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n\u0002\b\u0003\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a/\u0010\t\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"VertxJmxMetricsOptions", "Lio/vertx/micrometer/VertxJmxMetricsOptions;", "domain", "", "enabled", "", "step", "", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)Lio/vertx/micrometer/VertxJmxMetricsOptions;", "vertxJmxMetricsOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/micrometer/VertxJmxMetricsOptionsKt.class */
public final class VertxJmxMetricsOptionsKt {
    @NotNull
    public static /* synthetic */ VertxJmxMetricsOptions vertxJmxMetricsOptionsOf$default(String str, Boolean bool, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return vertxJmxMetricsOptionsOf(str, bool, num);
    }

    @NotNull
    public static final VertxJmxMetricsOptions vertxJmxMetricsOptionsOf(@Nullable String domain, @Nullable Boolean enabled, @Nullable Integer step) {
        VertxJmxMetricsOptions $this$apply = new VertxJmxMetricsOptions();
        if (domain != null) {
            $this$apply.setDomain(domain);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (step != null) {
            $this$apply.setStep(step.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxJmxMetricsOptionsOf(domain, enabled, step)"))
    @NotNull
    public static /* synthetic */ VertxJmxMetricsOptions VertxJmxMetricsOptions$default(String str, Boolean bool, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        return VertxJmxMetricsOptions(str, bool, num);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxJmxMetricsOptionsOf(domain, enabled, step)"))
    @NotNull
    public static final VertxJmxMetricsOptions VertxJmxMetricsOptions(@Nullable String domain, @Nullable Boolean enabled, @Nullable Integer step) {
        VertxJmxMetricsOptions $this$apply = new VertxJmxMetricsOptions();
        if (domain != null) {
            $this$apply.setDomain(domain);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (step != null) {
            $this$apply.setStep(step.intValue());
        }
        return $this$apply;
    }
}
