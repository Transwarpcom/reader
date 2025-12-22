package io.vertx.kotlin.core.net;

import io.vertx.core.net.OpenSSLEngineOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OpenSSLEngineOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0019\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"OpenSSLEngineOptions", "Lio/vertx/core/net/OpenSSLEngineOptions;", "sessionCacheEnabled", "", "(Ljava/lang/Boolean;)Lio/vertx/core/net/OpenSSLEngineOptions;", "openSSLEngineOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/OpenSSLEngineOptionsKt.class */
public final class OpenSSLEngineOptionsKt {
    @NotNull
    public static final OpenSSLEngineOptions openSSLEngineOptionsOf(@Nullable Boolean sessionCacheEnabled) {
        OpenSSLEngineOptions $this$apply = new OpenSSLEngineOptions();
        if (sessionCacheEnabled != null) {
            $this$apply.setSessionCacheEnabled(sessionCacheEnabled.booleanValue());
        }
        return $this$apply;
    }

    @NotNull
    public static /* synthetic */ OpenSSLEngineOptions openSSLEngineOptionsOf$default(Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        return openSSLEngineOptionsOf(bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "openSSLEngineOptionsOf(sessionCacheEnabled)"))
    @NotNull
    public static final OpenSSLEngineOptions OpenSSLEngineOptions(@Nullable Boolean sessionCacheEnabled) {
        OpenSSLEngineOptions $this$apply = new OpenSSLEngineOptions();
        if (sessionCacheEnabled != null) {
            $this$apply.setSessionCacheEnabled(sessionCacheEnabled.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "openSSLEngineOptionsOf(sessionCacheEnabled)"))
    @NotNull
    public static /* synthetic */ OpenSSLEngineOptions OpenSSLEngineOptions$default(Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        return OpenSSLEngineOptions(bool);
    }
}
