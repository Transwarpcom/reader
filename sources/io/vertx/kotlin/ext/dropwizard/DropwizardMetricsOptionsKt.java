package io.vertx.kotlin.ext.dropwizard;

import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import io.vertx.ext.dropwizard.Match;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DropwizardMetricsOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u009d\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0010\u001a\u009b\u0001\u0010\u0011\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"DropwizardMetricsOptions", "Lio/vertx/ext/dropwizard/DropwizardMetricsOptions;", "baseName", "", "configPath", "enabled", "", "jmxDomain", "jmxEnabled", "monitoredEventBusHandlers", "", "Lio/vertx/ext/dropwizard/Match;", "monitoredHttpClientEndpoints", "monitoredHttpClientUris", "monitoredHttpServerUris", "registryName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/Iterable;Ljava/lang/String;)Lio/vertx/ext/dropwizard/DropwizardMetricsOptions;", "dropwizardMetricsOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/dropwizard/DropwizardMetricsOptionsKt.class */
public final class DropwizardMetricsOptionsKt {
    @NotNull
    public static /* synthetic */ DropwizardMetricsOptions dropwizardMetricsOptionsOf$default(String str, String str2, Boolean bool, String str3, Boolean bool2, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            str4 = (String) null;
        }
        return dropwizardMetricsOptionsOf(str, str2, bool, str3, bool2, iterable, iterable2, iterable3, iterable4, str4);
    }

    @NotNull
    public static final DropwizardMetricsOptions dropwizardMetricsOptionsOf(@Nullable String baseName, @Nullable String configPath, @Nullable Boolean enabled, @Nullable String jmxDomain, @Nullable Boolean jmxEnabled, @Nullable Iterable<? extends Match> iterable, @Nullable Iterable<? extends Match> iterable2, @Nullable Iterable<? extends Match> iterable3, @Nullable Iterable<? extends Match> iterable4, @Nullable String registryName) {
        DropwizardMetricsOptions $this$apply = new DropwizardMetricsOptions();
        if (baseName != null) {
            $this$apply.setBaseName(baseName);
        }
        if (configPath != null) {
            $this$apply.setConfigPath(configPath);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (jmxDomain != null) {
            $this$apply.setJmxDomain(jmxDomain);
        }
        if (jmxEnabled != null) {
            $this$apply.setJmxEnabled(jmxEnabled.booleanValue());
        }
        if (iterable != null) {
            for (Match item : iterable) {
                $this$apply.addMonitoredEventBusHandler(item);
            }
        }
        if (iterable2 != null) {
            for (Match item2 : iterable2) {
                $this$apply.addMonitoredHttpClientEndpoint(item2);
            }
        }
        if (iterable3 != null) {
            for (Match item3 : iterable3) {
                $this$apply.addMonitoredHttpClientUri(item3);
            }
        }
        if (iterable4 != null) {
            for (Match item4 : iterable4) {
                $this$apply.addMonitoredHttpServerUri(item4);
            }
        }
        if (registryName != null) {
            $this$apply.setRegistryName(registryName);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "dropwizardMetricsOptionsOf(baseName, configPath, enabled, jmxDomain, jmxEnabled, monitoredEventBusHandlers, monitoredHttpClientEndpoints, monitoredHttpClientUris, monitoredHttpServerUris, registryName)"))
    @NotNull
    public static /* synthetic */ DropwizardMetricsOptions DropwizardMetricsOptions$default(String str, String str2, Boolean bool, String str3, Boolean bool2, Iterable iterable, Iterable iterable2, Iterable iterable3, Iterable iterable4, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 32) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 64) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 128) != 0) {
            iterable3 = (Iterable) null;
        }
        if ((i & 256) != 0) {
            iterable4 = (Iterable) null;
        }
        if ((i & 512) != 0) {
            str4 = (String) null;
        }
        return DropwizardMetricsOptions(str, str2, bool, str3, bool2, iterable, iterable2, iterable3, iterable4, str4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "dropwizardMetricsOptionsOf(baseName, configPath, enabled, jmxDomain, jmxEnabled, monitoredEventBusHandlers, monitoredHttpClientEndpoints, monitoredHttpClientUris, monitoredHttpServerUris, registryName)"))
    @NotNull
    public static final DropwizardMetricsOptions DropwizardMetricsOptions(@Nullable String baseName, @Nullable String configPath, @Nullable Boolean enabled, @Nullable String jmxDomain, @Nullable Boolean jmxEnabled, @Nullable Iterable<? extends Match> iterable, @Nullable Iterable<? extends Match> iterable2, @Nullable Iterable<? extends Match> iterable3, @Nullable Iterable<? extends Match> iterable4, @Nullable String registryName) {
        DropwizardMetricsOptions $this$apply = new DropwizardMetricsOptions();
        if (baseName != null) {
            $this$apply.setBaseName(baseName);
        }
        if (configPath != null) {
            $this$apply.setConfigPath(configPath);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (jmxDomain != null) {
            $this$apply.setJmxDomain(jmxDomain);
        }
        if (jmxEnabled != null) {
            $this$apply.setJmxEnabled(jmxEnabled.booleanValue());
        }
        if (iterable != null) {
            for (Match item : iterable) {
                $this$apply.addMonitoredEventBusHandler(item);
            }
        }
        if (iterable2 != null) {
            for (Match item2 : iterable2) {
                $this$apply.addMonitoredHttpClientEndpoint(item2);
            }
        }
        if (iterable3 != null) {
            for (Match item3 : iterable3) {
                $this$apply.addMonitoredHttpClientUri(item3);
            }
        }
        if (iterable4 != null) {
            for (Match item4 : iterable4) {
                $this$apply.addMonitoredHttpServerUri(item4);
            }
        }
        if (registryName != null) {
            $this$apply.setRegistryName(registryName);
        }
        return $this$apply;
    }
}
