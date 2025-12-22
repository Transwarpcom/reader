package io.vertx.kotlin.core.dns;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.dns.DnsClientOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DnsClientOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0004\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u000b\u001aG\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"DnsClientOptions", "Lio/vertx/core/dns/DnsClientOptions;", "host", "", "logActivity", "", RtspHeaders.Values.PORT, "", "queryTimeout", "", "recursionDesired", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Boolean;)Lio/vertx/core/dns/DnsClientOptions;", "dnsClientOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/dns/DnsClientOptionsKt.class */
public final class DnsClientOptionsKt {
    @NotNull
    public static /* synthetic */ DnsClientOptions dnsClientOptionsOf$default(String str, Boolean bool, Integer num, Long l, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            l = (Long) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        return dnsClientOptionsOf(str, bool, num, l, bool2);
    }

    @NotNull
    public static final DnsClientOptions dnsClientOptionsOf(@Nullable String host, @Nullable Boolean logActivity, @Nullable Integer port, @Nullable Long queryTimeout, @Nullable Boolean recursionDesired) {
        DnsClientOptions $this$apply = new DnsClientOptions();
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (queryTimeout != null) {
            $this$apply.setQueryTimeout(queryTimeout.longValue());
        }
        if (recursionDesired != null) {
            $this$apply.setRecursionDesired(recursionDesired.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "dnsClientOptionsOf(host, logActivity, port, queryTimeout, recursionDesired)"))
    @NotNull
    public static /* synthetic */ DnsClientOptions DnsClientOptions$default(String str, Boolean bool, Integer num, Long l, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 4) != 0) {
            num = (Integer) null;
        }
        if ((i & 8) != 0) {
            l = (Long) null;
        }
        if ((i & 16) != 0) {
            bool2 = (Boolean) null;
        }
        return DnsClientOptions(str, bool, num, l, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "dnsClientOptionsOf(host, logActivity, port, queryTimeout, recursionDesired)"))
    @NotNull
    public static final DnsClientOptions DnsClientOptions(@Nullable String host, @Nullable Boolean logActivity, @Nullable Integer port, @Nullable Long queryTimeout, @Nullable Boolean recursionDesired) {
        DnsClientOptions $this$apply = new DnsClientOptions();
        if (host != null) {
            $this$apply.setHost(host);
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (queryTimeout != null) {
            $this$apply.setQueryTimeout(queryTimeout.longValue());
        }
        if (recursionDesired != null) {
            $this$apply.setRecursionDesired(recursionDesired.booleanValue());
        }
        return $this$apply;
    }
}
