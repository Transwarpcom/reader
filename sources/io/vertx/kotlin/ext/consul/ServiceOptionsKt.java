package io.vertx.kotlin.ext.consul;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.ServiceOptions;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ServiceOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��,\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u001c\n\u0002\b\u0003\u001as\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u000e\u001aq\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"ServiceOptions", "Lio/vertx/ext/consul/ServiceOptions;", "address", "", "checkOptions", "Lio/vertx/ext/consul/CheckOptions;", "id", "meta", "", "name", RtspHeaders.Values.PORT, "", "tags", "", "(Ljava/lang/String;Lio/vertx/ext/consul/CheckOptions;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;)Lio/vertx/ext/consul/ServiceOptions;", "serviceOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/ServiceOptionsKt.class */
public final class ServiceOptionsKt {
    @NotNull
    public static /* synthetic */ ServiceOptions serviceOptionsOf$default(String str, CheckOptions checkOptions, String str2, Map map, String str3, Integer num, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            checkOptions = (CheckOptions) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            map = (Map) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            iterable = (Iterable) null;
        }
        return serviceOptionsOf(str, checkOptions, str2, map, str3, num, iterable);
    }

    @NotNull
    public static final ServiceOptions serviceOptionsOf(@Nullable String address, @Nullable CheckOptions checkOptions, @Nullable String id, @Nullable Map<String, String> map, @Nullable String name, @Nullable Integer port, @Nullable Iterable<String> iterable) {
        ServiceOptions $this$apply = new ServiceOptions();
        if (address != null) {
            $this$apply.setAddress(address);
        }
        if (checkOptions != null) {
            $this$apply.setCheckOptions(checkOptions);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (map != null) {
            $this$apply.setMeta(map);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (iterable != null) {
            $this$apply.setTags(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceOptionsOf(address, checkOptions, id, meta, name, port, tags)"))
    @NotNull
    public static /* synthetic */ ServiceOptions ServiceOptions$default(String str, CheckOptions checkOptions, String str2, Map map, String str3, Integer num, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            checkOptions = (CheckOptions) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            map = (Map) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            iterable = (Iterable) null;
        }
        return ServiceOptions(str, checkOptions, str2, map, str3, num, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceOptionsOf(address, checkOptions, id, meta, name, port, tags)"))
    @NotNull
    public static final ServiceOptions ServiceOptions(@Nullable String address, @Nullable CheckOptions checkOptions, @Nullable String id, @Nullable Map<String, String> map, @Nullable String name, @Nullable Integer port, @Nullable Iterable<String> iterable) {
        ServiceOptions $this$apply = new ServiceOptions();
        if (address != null) {
            $this$apply.setAddress(address);
        }
        if (checkOptions != null) {
            $this$apply.setCheckOptions(checkOptions);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (map != null) {
            $this$apply.setMeta(map);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (iterable != null) {
            $this$apply.setTags(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
