package io.vertx.kotlin.core.http;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.http.RequestOptions;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RequestOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\u001aU\u0010��\u001a\u00020\u00012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u000b\u001aS\u0010\f\u001a\u00020\u00012\u0016\b\u0002\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"RequestOptions", "Lio/vertx/core/http/RequestOptions;", "headers", "", "", "host", RtspHeaders.Values.PORT, "", "ssl", "", "uri", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/core/http/RequestOptions;", "requestOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/RequestOptionsKt.class */
public final class RequestOptionsKt {
    @NotNull
    public static /* synthetic */ RequestOptions requestOptionsOf$default(Map map, String str, Integer num, Boolean bool, String str2, int i, Object obj) {
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
            str2 = (String) null;
        }
        return requestOptionsOf(map, str, num, bool, str2);
    }

    @NotNull
    public static final RequestOptions requestOptionsOf(@Nullable Map<String, String> map, @Nullable String host, @Nullable Integer port, @Nullable Boolean ssl, @Nullable String uri) {
        RequestOptions $this$apply = new RequestOptions();
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
        if (uri != null) {
            $this$apply.setURI(uri);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "requestOptionsOf(headers, host, port, ssl, uri)"))
    @NotNull
    public static /* synthetic */ RequestOptions RequestOptions$default(Map map, String str, Integer num, Boolean bool, String str2, int i, Object obj) {
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
            str2 = (String) null;
        }
        return RequestOptions(map, str, num, bool, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "requestOptionsOf(headers, host, port, ssl, uri)"))
    @NotNull
    public static final RequestOptions RequestOptions(@Nullable Map<String, String> map, @Nullable String host, @Nullable Integer port, @Nullable Boolean ssl, @Nullable String uri) {
        RequestOptions $this$apply = new RequestOptions();
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
        if (uri != null) {
            $this$apply.setURI(uri);
        }
        return $this$apply;
    }
}
