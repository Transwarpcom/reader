package io.vertx.kotlin.pgclient.data;

import io.vertx.pgclient.data.Point;
import io.vertx.pgclient.data.Polygon;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Polygon.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¨\u0006\u0006"}, d2 = {"Polygon", "Lio/vertx/pgclient/data/Polygon;", "points", "", "Lio/vertx/pgclient/data/Point;", "polygonOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/PolygonKt.class */
public final class PolygonKt {
    @NotNull
    public static final Polygon polygonOf(@Nullable Iterable<? extends Point> iterable) {
        Polygon $this$apply = new Polygon();
        if (iterable != null) {
            $this$apply.setPoints(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @NotNull
    public static /* synthetic */ Polygon polygonOf$default(Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        return polygonOf(iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "polygonOf(points)"))
    @NotNull
    public static final Polygon Polygon(@Nullable Iterable<? extends Point> iterable) {
        Polygon $this$apply = new Polygon();
        if (iterable != null) {
            $this$apply.setPoints(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "polygonOf(points)"))
    @NotNull
    public static /* synthetic */ Polygon Polygon$default(Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        return Polygon(iterable);
    }
}
