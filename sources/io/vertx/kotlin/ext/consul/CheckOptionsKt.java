package io.vertx.kotlin.ext.consul;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.CheckStatus;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��(\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a»\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0014\u001a¹\u0001\u0010\u0015\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0014¨\u0006\u0016"}, d2 = {"CheckOptions", "Lio/vertx/ext/consul/CheckOptions;", "deregisterAfter", "", "grpc", "grpcTls", "", "http", "id", "interval", "name", "notes", "scriptArgs", "", "serviceId", "status", "Lio/vertx/ext/consul/CheckStatus;", "tcp", "tlsSkipVerify", RtspHeaders.Values.TTL, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/String;Lio/vertx/ext/consul/CheckStatus;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lio/vertx/ext/consul/CheckOptions;", "checkOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/CheckOptionsKt.class */
public final class CheckOptionsKt {
    @NotNull
    public static /* synthetic */ CheckOptions checkOptionsOf$default(String str, String str2, Boolean bool, String str3, String str4, String str5, String str6, String str7, Iterable iterable, String str8, CheckStatus checkStatus, String str9, Boolean bool2, String str10, int i, Object obj) {
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
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            str6 = (String) null;
        }
        if ((i & 128) != 0) {
            str7 = (String) null;
        }
        if ((i & 256) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 512) != 0) {
            str8 = (String) null;
        }
        if ((i & 1024) != 0) {
            checkStatus = (CheckStatus) null;
        }
        if ((i & 2048) != 0) {
            str9 = (String) null;
        }
        if ((i & 4096) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8192) != 0) {
            str10 = (String) null;
        }
        return checkOptionsOf(str, str2, bool, str3, str4, str5, str6, str7, iterable, str8, checkStatus, str9, bool2, str10);
    }

    @NotNull
    public static final CheckOptions checkOptionsOf(@Nullable String deregisterAfter, @Nullable String grpc, @Nullable Boolean grpcTls, @Nullable String http, @Nullable String id, @Nullable String interval, @Nullable String name, @Nullable String notes, @Nullable Iterable<String> iterable, @Nullable String serviceId, @Nullable CheckStatus status, @Nullable String tcp, @Nullable Boolean tlsSkipVerify, @Nullable String ttl) {
        CheckOptions $this$apply = new CheckOptions();
        if (deregisterAfter != null) {
            $this$apply.setDeregisterAfter(deregisterAfter);
        }
        if (grpc != null) {
            $this$apply.setGrpc(grpc);
        }
        if (grpcTls != null) {
            $this$apply.setGrpcTls(grpcTls.booleanValue());
        }
        if (http != null) {
            $this$apply.setHttp(http);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (interval != null) {
            $this$apply.setInterval(interval);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (notes != null) {
            $this$apply.setNotes(notes);
        }
        if (iterable != null) {
            $this$apply.setScriptArgs(CollectionsKt.toList(iterable));
        }
        if (serviceId != null) {
            $this$apply.setServiceId(serviceId);
        }
        if (status != null) {
            $this$apply.setStatus(status);
        }
        if (tcp != null) {
            $this$apply.setTcp(tcp);
        }
        if (tlsSkipVerify != null) {
            $this$apply.setTlsSkipVerify(tlsSkipVerify.booleanValue());
        }
        if (ttl != null) {
            $this$apply.setTtl(ttl);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "checkOptionsOf(deregisterAfter, grpc, grpcTls, http, id, interval, name, notes, scriptArgs, serviceId, status, tcp, tlsSkipVerify, ttl)"))
    @NotNull
    public static /* synthetic */ CheckOptions CheckOptions$default(String str, String str2, Boolean bool, String str3, String str4, String str5, String str6, String str7, Iterable iterable, String str8, CheckStatus checkStatus, String str9, Boolean bool2, String str10, int i, Object obj) {
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
            str4 = (String) null;
        }
        if ((i & 32) != 0) {
            str5 = (String) null;
        }
        if ((i & 64) != 0) {
            str6 = (String) null;
        }
        if ((i & 128) != 0) {
            str7 = (String) null;
        }
        if ((i & 256) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 512) != 0) {
            str8 = (String) null;
        }
        if ((i & 1024) != 0) {
            checkStatus = (CheckStatus) null;
        }
        if ((i & 2048) != 0) {
            str9 = (String) null;
        }
        if ((i & 4096) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 8192) != 0) {
            str10 = (String) null;
        }
        return CheckOptions(str, str2, bool, str3, str4, str5, str6, str7, iterable, str8, checkStatus, str9, bool2, str10);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "checkOptionsOf(deregisterAfter, grpc, grpcTls, http, id, interval, name, notes, scriptArgs, serviceId, status, tcp, tlsSkipVerify, ttl)"))
    @NotNull
    public static final CheckOptions CheckOptions(@Nullable String deregisterAfter, @Nullable String grpc, @Nullable Boolean grpcTls, @Nullable String http, @Nullable String id, @Nullable String interval, @Nullable String name, @Nullable String notes, @Nullable Iterable<String> iterable, @Nullable String serviceId, @Nullable CheckStatus status, @Nullable String tcp, @Nullable Boolean tlsSkipVerify, @Nullable String ttl) {
        CheckOptions $this$apply = new CheckOptions();
        if (deregisterAfter != null) {
            $this$apply.setDeregisterAfter(deregisterAfter);
        }
        if (grpc != null) {
            $this$apply.setGrpc(grpc);
        }
        if (grpcTls != null) {
            $this$apply.setGrpcTls(grpcTls.booleanValue());
        }
        if (http != null) {
            $this$apply.setHttp(http);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (interval != null) {
            $this$apply.setInterval(interval);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (notes != null) {
            $this$apply.setNotes(notes);
        }
        if (iterable != null) {
            $this$apply.setScriptArgs(CollectionsKt.toList(iterable));
        }
        if (serviceId != null) {
            $this$apply.setServiceId(serviceId);
        }
        if (status != null) {
            $this$apply.setStatus(status);
        }
        if (tcp != null) {
            $this$apply.setTcp(tcp);
        }
        if (tlsSkipVerify != null) {
            $this$apply.setTlsSkipVerify(tlsSkipVerify.booleanValue());
        }
        if (ttl != null) {
            $this$apply.setTtl(ttl);
        }
        return $this$apply;
    }
}
