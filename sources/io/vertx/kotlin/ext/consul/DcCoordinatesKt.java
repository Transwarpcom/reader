package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Coordinate;
import io.vertx.ext.consul.DcCoordinates;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DcCoordinates.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0007\u001a$\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¨\u0006\b"}, d2 = {"DcCoordinates", "Lio/vertx/ext/consul/DcCoordinates;", "datacenter", "", "servers", "", "Lio/vertx/ext/consul/Coordinate;", "dcCoordinatesOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/DcCoordinatesKt.class */
public final class DcCoordinatesKt {
    @NotNull
    public static /* synthetic */ DcCoordinates dcCoordinatesOf$default(String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return dcCoordinatesOf(str, iterable);
    }

    @NotNull
    public static final DcCoordinates dcCoordinatesOf(@Nullable String datacenter, @Nullable Iterable<? extends Coordinate> iterable) {
        DcCoordinates $this$apply = new DcCoordinates();
        if (datacenter != null) {
            $this$apply.setDatacenter(datacenter);
        }
        if (iterable != null) {
            $this$apply.setServers(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "dcCoordinatesOf(datacenter, servers)"))
    @NotNull
    public static /* synthetic */ DcCoordinates DcCoordinates$default(String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        return DcCoordinates(str, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "dcCoordinatesOf(datacenter, servers)"))
    @NotNull
    public static final DcCoordinates DcCoordinates(@Nullable String datacenter, @Nullable Iterable<? extends Coordinate> iterable) {
        DcCoordinates $this$apply = new DcCoordinates();
        if (datacenter != null) {
            $this$apply.setDatacenter(datacenter);
        }
        if (iterable != null) {
            $this$apply.setServers(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
