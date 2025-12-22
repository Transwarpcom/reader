package io.vertx.kotlin.core.net;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import io.vertx.ext.web.handler.FormLoginHandler;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProxyOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"ProxyOptions", "Lio/vertx/core/net/ProxyOptions;", "host", "", FormLoginHandler.DEFAULT_PASSWORD_PARAM, RtspHeaders.Values.PORT, "", "type", "Lio/vertx/core/net/ProxyType;", FormLoginHandler.DEFAULT_USERNAME_PARAM, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lio/vertx/core/net/ProxyType;Ljava/lang/String;)Lio/vertx/core/net/ProxyOptions;", "proxyOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/ProxyOptionsKt.class */
public final class ProxyOptionsKt {
    @NotNull
    public static /* synthetic */ ProxyOptions proxyOptionsOf$default(String str, String str2, Integer num, ProxyType proxyType, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            proxyType = (ProxyType) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return proxyOptionsOf(str, str2, num, proxyType, str3);
    }

    @NotNull
    public static final ProxyOptions proxyOptionsOf(@Nullable String host, @Nullable String password, @Nullable Integer port, @Nullable ProxyType type, @Nullable String username) {
        ProxyOptions $this$apply = new ProxyOptions();
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (username != null) {
            $this$apply.setUsername(username);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "proxyOptionsOf(host, password, port, type, username)"))
    @NotNull
    public static /* synthetic */ ProxyOptions ProxyOptions$default(String str, String str2, Integer num, ProxyType proxyType, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            proxyType = (ProxyType) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return ProxyOptions(str, str2, num, proxyType, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "proxyOptionsOf(host, password, port, type, username)"))
    @NotNull
    public static final ProxyOptions ProxyOptions(@Nullable String host, @Nullable String password, @Nullable Integer port, @Nullable ProxyType type, @Nullable String username) {
        ProxyOptions $this$apply = new ProxyOptions();
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (password != null) {
            $this$apply.setPassword(password);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (username != null) {
            $this$apply.setUsername(username);
        }
        return $this$apply;
    }
}
