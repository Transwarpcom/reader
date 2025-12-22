package io.vertx.kotlin.core.http;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebsocketVersion;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WebSocketConnectOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��0\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001as\u0010��\u001a\u00020\u00012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001aq\u0010\u0010\u001a\u00020\u00012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000f¨\u0006\u0011"}, d2 = {"WebSocketConnectOptions", "Lio/vertx/core/http/WebSocketConnectOptions;", "headers", "", "", "host", RtspHeaders.Values.PORT, "", "ssl", "", "subProtocols", "", "uri", "version", "Lio/vertx/core/http/WebsocketVersion;", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Iterable;Ljava/lang/String;Lio/vertx/core/http/WebsocketVersion;)Lio/vertx/core/http/WebSocketConnectOptions;", "webSocketConnectOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/WebSocketConnectOptionsKt.class */
public final class WebSocketConnectOptionsKt {
    @NotNull
    public static /* synthetic */ WebSocketConnectOptions webSocketConnectOptionsOf$default(Map map, String str, Integer num, Boolean bool, Iterable iterable, String str2, WebsocketVersion websocketVersion, int i, Object obj) {
        if ((i & 1) != 0) {
            map = (Map) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 16) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        if ((i & 64) != 0) {
            websocketVersion = (WebsocketVersion) null;
        }
        return webSocketConnectOptionsOf(map, str, num, bool, iterable, str2, websocketVersion);
    }

    @NotNull
    public static final WebSocketConnectOptions webSocketConnectOptionsOf(@Nullable Map<String, String> map, @Nullable String host, @Nullable Integer port, @Nullable Boolean ssl, @Nullable Iterable<String> iterable, @Nullable String uri, @Nullable WebsocketVersion version) {
        WebSocketConnectOptions $this$apply = new WebSocketConnectOptions();
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl);
        }
        if (iterable != null) {
            $this$apply.setSubProtocols(CollectionsKt.toList(iterable));
        }
        if (uri != null) {
            $this$apply.setURI(uri);
        }
        if (version != null) {
            $this$apply.setVersion(version);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "webSocketConnectOptionsOf(headers, host, port, ssl, subProtocols, uri, version)"))
    @NotNull
    public static /* synthetic */ WebSocketConnectOptions WebSocketConnectOptions$default(Map map, String str, Integer num, Boolean bool, Iterable iterable, String str2, WebsocketVersion websocketVersion, int i, Object obj) {
        if ((i & 1) != 0) {
            map = (Map) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 16) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 32) != 0) {
            str2 = (String) null;
        }
        if ((i & 64) != 0) {
            websocketVersion = (WebsocketVersion) null;
        }
        return WebSocketConnectOptions(map, str, num, bool, iterable, str2, websocketVersion);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "webSocketConnectOptionsOf(headers, host, port, ssl, subProtocols, uri, version)"))
    @NotNull
    public static final WebSocketConnectOptions WebSocketConnectOptions(@Nullable Map<String, String> map, @Nullable String host, @Nullable Integer port, @Nullable Boolean ssl, @Nullable Iterable<String> iterable, @Nullable String uri, @Nullable WebsocketVersion version) {
        WebSocketConnectOptions $this$apply = new WebSocketConnectOptions();
        if (map != null) {
            for (Map.Entry item : map.entrySet()) {
                $this$apply.addHeader(item.getKey(), item.getValue());
            }
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl);
        }
        if (iterable != null) {
            $this$apply.setSubProtocols(CollectionsKt.toList(iterable));
        }
        if (uri != null) {
            $this$apply.setURI(uri);
        }
        if (version != null) {
            $this$apply.setVersion(version);
        }
        return $this$apply;
    }
}
