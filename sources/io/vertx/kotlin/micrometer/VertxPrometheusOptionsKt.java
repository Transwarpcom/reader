package io.vertx.kotlin.micrometer;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.micrometer.VertxPrometheusOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: VertxPrometheusOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"VertxPrometheusOptions", "Lio/vertx/micrometer/VertxPrometheusOptions;", "embeddedServerEndpoint", "", "embeddedServerOptions", "Lio/vertx/core/http/HttpServerOptions;", "enabled", "", "publishQuantiles", "startEmbeddedServer", "(Ljava/lang/String;Lio/vertx/core/http/HttpServerOptions;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/micrometer/VertxPrometheusOptions;", "vertxPrometheusOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/micrometer/VertxPrometheusOptionsKt.class */
public final class VertxPrometheusOptionsKt {
    @NotNull
    public static /* synthetic */ VertxPrometheusOptions vertxPrometheusOptionsOf$default(String str, HttpServerOptions httpServerOptions, Boolean bool, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            httpServerOptions = (HttpServerOptions) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            bool3 = (Boolean) null;
        }
        return vertxPrometheusOptionsOf(str, httpServerOptions, bool, bool2, bool3);
    }

    @NotNull
    public static final VertxPrometheusOptions vertxPrometheusOptionsOf(@Nullable String embeddedServerEndpoint, @Nullable HttpServerOptions embeddedServerOptions, @Nullable Boolean enabled, @Nullable Boolean publishQuantiles, @Nullable Boolean startEmbeddedServer) {
        VertxPrometheusOptions $this$apply = new VertxPrometheusOptions();
        if (embeddedServerEndpoint != null) {
            $this$apply.setEmbeddedServerEndpoint(embeddedServerEndpoint);
        }
        if (embeddedServerOptions != null) {
            $this$apply.setEmbeddedServerOptions(embeddedServerOptions);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (publishQuantiles != null) {
            $this$apply.setPublishQuantiles(publishQuantiles.booleanValue());
        }
        if (startEmbeddedServer != null) {
            $this$apply.setStartEmbeddedServer(startEmbeddedServer.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxPrometheusOptionsOf(embeddedServerEndpoint, embeddedServerOptions, enabled, publishQuantiles, startEmbeddedServer)"))
    @NotNull
    public static /* synthetic */ VertxPrometheusOptions VertxPrometheusOptions$default(String str, HttpServerOptions httpServerOptions, Boolean bool, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            httpServerOptions = (HttpServerOptions) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            bool3 = (Boolean) null;
        }
        return VertxPrometheusOptions(str, httpServerOptions, bool, bool2, bool3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "vertxPrometheusOptionsOf(embeddedServerEndpoint, embeddedServerOptions, enabled, publishQuantiles, startEmbeddedServer)"))
    @NotNull
    public static final VertxPrometheusOptions VertxPrometheusOptions(@Nullable String embeddedServerEndpoint, @Nullable HttpServerOptions embeddedServerOptions, @Nullable Boolean enabled, @Nullable Boolean publishQuantiles, @Nullable Boolean startEmbeddedServer) {
        VertxPrometheusOptions $this$apply = new VertxPrometheusOptions();
        if (embeddedServerEndpoint != null) {
            $this$apply.setEmbeddedServerEndpoint(embeddedServerEndpoint);
        }
        if (embeddedServerOptions != null) {
            $this$apply.setEmbeddedServerOptions(embeddedServerOptions);
        }
        if (enabled != null) {
            $this$apply.setEnabled(enabled.booleanValue());
        }
        if (publishQuantiles != null) {
            $this$apply.setPublishQuantiles(publishQuantiles.booleanValue());
        }
        if (startEmbeddedServer != null) {
            $this$apply.setStartEmbeddedServer(startEmbeddedServer.booleanValue());
        }
        return $this$apply;
    }
}
