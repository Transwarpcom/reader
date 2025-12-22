package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.PreparedQueryExecuteResponse;
import io.vertx.ext.consul.ServiceEntry;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreparedQueryExecuteResponse.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aO\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001aM\u0010\f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"PreparedQueryExecuteResponse", "Lio/vertx/ext/consul/PreparedQueryExecuteResponse;", PackageDocumentBase.PREFIX_DUBLIN_CORE, "", "dnsTtl", "failovers", "", "nodes", "", "Lio/vertx/ext/consul/ServiceEntry;", "service", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;Ljava/lang/String;)Lio/vertx/ext/consul/PreparedQueryExecuteResponse;", "preparedQueryExecuteResponseOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/PreparedQueryExecuteResponseKt.class */
public final class PreparedQueryExecuteResponseKt {
    @NotNull
    public static /* synthetic */ PreparedQueryExecuteResponse preparedQueryExecuteResponseOf$default(String str, String str2, Integer num, Iterable iterable, String str3, int i, Object obj) {
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
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return preparedQueryExecuteResponseOf(str, str2, num, iterable, str3);
    }

    @NotNull
    public static final PreparedQueryExecuteResponse preparedQueryExecuteResponseOf(@Nullable String dc, @Nullable String dnsTtl, @Nullable Integer failovers, @Nullable Iterable<? extends ServiceEntry> iterable, @Nullable String service) {
        PreparedQueryExecuteResponse $this$apply = new PreparedQueryExecuteResponse();
        if (dc != null) {
            $this$apply.setDc(dc);
        }
        if (dnsTtl != null) {
            $this$apply.setDnsTtl(dnsTtl);
        }
        if (failovers != null) {
            $this$apply.setFailovers(failovers.intValue());
        }
        if (iterable != null) {
            $this$apply.setNodes(CollectionsKt.toList(iterable));
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "preparedQueryExecuteResponseOf(dc, dnsTtl, failovers, nodes, service)"))
    @NotNull
    public static /* synthetic */ PreparedQueryExecuteResponse PreparedQueryExecuteResponse$default(String str, String str2, Integer num, Iterable iterable, String str3, int i, Object obj) {
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
            iterable = (Iterable) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return PreparedQueryExecuteResponse(str, str2, num, iterable, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "preparedQueryExecuteResponseOf(dc, dnsTtl, failovers, nodes, service)"))
    @NotNull
    public static final PreparedQueryExecuteResponse PreparedQueryExecuteResponse(@Nullable String dc, @Nullable String dnsTtl, @Nullable Integer failovers, @Nullable Iterable<? extends ServiceEntry> iterable, @Nullable String service) {
        PreparedQueryExecuteResponse $this$apply = new PreparedQueryExecuteResponse();
        if (dc != null) {
            $this$apply.setDc(dc);
        }
        if (dnsTtl != null) {
            $this$apply.setDnsTtl(dnsTtl);
        }
        if (failovers != null) {
            $this$apply.setFailovers(failovers.intValue());
        }
        if (iterable != null) {
            $this$apply.setNodes(CollectionsKt.toList(iterable));
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        return $this$apply;
    }
}
