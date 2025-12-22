package io.vertx.kotlin.servicediscovery.types;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.servicediscovery.types.HttpLocation;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HttpLocation.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001aG\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"HttpLocation", "Lio/vertx/servicediscovery/types/HttpLocation;", "endpoint", "", "host", RtspHeaders.Values.PORT, "", "root", "ssl", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/servicediscovery/types/HttpLocation;", "httpLocationOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/servicediscovery/types/HttpLocationKt.class */
public final class HttpLocationKt {
    @NotNull
    public static /* synthetic */ HttpLocation httpLocationOf$default(String str, String str2, Integer num, String str3, Boolean bool, int i, Object obj) {
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
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        return httpLocationOf(str, str2, num, str3, bool);
    }

    @NotNull
    public static final HttpLocation httpLocationOf(@Nullable String endpoint, @Nullable String host, @Nullable Integer port, @Nullable String root, @Nullable Boolean ssl) {
        HttpLocation $this$apply = new HttpLocation();
        if (endpoint != null) {
            $this$apply.setEndpoint(endpoint);
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (root != null) {
            $this$apply.setRoot(root);
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "httpLocationOf(endpoint, host, port, root, ssl)"))
    @NotNull
    public static /* synthetic */ HttpLocation HttpLocation$default(String str, String str2, Integer num, String str3, Boolean bool, int i, Object obj) {
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
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        return HttpLocation(str, str2, num, str3, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "httpLocationOf(endpoint, host, port, root, ssl)"))
    @NotNull
    public static final HttpLocation HttpLocation(@Nullable String endpoint, @Nullable String host, @Nullable Integer port, @Nullable String root, @Nullable Boolean ssl) {
        HttpLocation $this$apply = new HttpLocation();
        if (endpoint != null) {
            $this$apply.setEndpoint(endpoint);
        }
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (root != null) {
            $this$apply.setRoot(root);
        }
        if (ssl != null) {
            $this$apply.setSsl(ssl.booleanValue());
        }
        return $this$apply;
    }
}
