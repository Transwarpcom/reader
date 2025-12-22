package io.vertx.kotlin.ext.consul;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.consul.Service;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Service.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��&\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0010\u001c\n\u0002\b\u0003\u001a\u007f\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH\u0007¢\u0006\u0002\u0010\u000e\u001a}\u0010\u000f\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r¢\u0006\u0002\u0010\u000e¨\u0006\u0010"}, d2 = {"Service", "Lio/vertx/ext/consul/Service;", "address", "", "id", "meta", "", "name", "node", "nodeAddress", RtspHeaders.Values.PORT, "", "tags", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Iterable;)Lio/vertx/ext/consul/Service;", "serviceOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/ServiceKt.class */
public final class ServiceKt {
    @NotNull
    public static /* synthetic */ Service serviceOf$default(String str, String str2, Map map, String str3, String str4, String str5, Integer num, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            map = (Map) null;
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
            num = (Integer) null;
        }
        if ((i & 128) != 0) {
            iterable = (Iterable) null;
        }
        return serviceOf(str, str2, map, str3, str4, str5, num, iterable);
    }

    @NotNull
    public static final Service serviceOf(@Nullable String address, @Nullable String id, @Nullable Map<String, String> map, @Nullable String name, @Nullable String node, @Nullable String nodeAddress, @Nullable Integer port, @Nullable Iterable<String> iterable) {
        Service $this$apply = new Service();
        if (address != null) {
            $this$apply.setAddress(address);
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
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (nodeAddress != null) {
            $this$apply.setNodeAddress(nodeAddress);
        }
        if (port != null) {
            $this$apply.setPort(port.intValue());
        }
        if (iterable != null) {
            $this$apply.setTags(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceOf(address, id, meta, name, node, nodeAddress, port, tags)"))
    @NotNull
    public static /* synthetic */ Service Service$default(String str, String str2, Map map, String str3, String str4, String str5, Integer num, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            map = (Map) null;
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
            num = (Integer) null;
        }
        if ((i & 128) != 0) {
            iterable = (Iterable) null;
        }
        return Service(str, str2, map, str3, str4, str5, num, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "serviceOf(address, id, meta, name, node, nodeAddress, port, tags)"))
    @NotNull
    public static final Service Service(@Nullable String address, @Nullable String id, @Nullable Map<String, String> map, @Nullable String name, @Nullable String node, @Nullable String nodeAddress, @Nullable Integer port, @Nullable Iterable<String> iterable) {
        Service $this$apply = new Service();
        if (address != null) {
            $this$apply.setAddress(address);
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
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (nodeAddress != null) {
            $this$apply.setNodeAddress(nodeAddress);
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
