package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.Coordinate;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Coordinate.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\b\u0003\u001aO\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001aM\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\u0002\u0010\n¨\u0006\f"}, d2 = {"Coordinate", "Lio/vertx/ext/consul/Coordinate;", "adj", "", "err", "height", "node", "", "vec", "", "(Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/Iterable;)Lio/vertx/ext/consul/Coordinate;", "coordinateOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/CoordinateKt.class */
public final class CoordinateKt {
    @NotNull
    public static /* synthetic */ Coordinate coordinateOf$default(Float f, Float f2, Float f3, String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            f = (Float) null;
        }
        if ((i & 2) != 0) {
            f2 = (Float) null;
        }
        if ((i & 4) != 0) {
            f3 = (Float) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            iterable = (Iterable) null;
        }
        return coordinateOf(f, f2, f3, str, iterable);
    }

    @NotNull
    public static final Coordinate coordinateOf(@Nullable Float adj, @Nullable Float err, @Nullable Float height, @Nullable String node, @Nullable Iterable<Float> iterable) {
        Coordinate $this$apply = new Coordinate();
        if (adj != null) {
            $this$apply.setAdj(adj.floatValue());
        }
        if (err != null) {
            $this$apply.setErr(err.floatValue());
        }
        if (height != null) {
            $this$apply.setHeight(height.floatValue());
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (iterable != null) {
            $this$apply.setVec(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "coordinateOf(adj, err, height, node, vec)"))
    @NotNull
    public static /* synthetic */ Coordinate Coordinate$default(Float f, Float f2, Float f3, String str, Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            f = (Float) null;
        }
        if ((i & 2) != 0) {
            f2 = (Float) null;
        }
        if ((i & 4) != 0) {
            f3 = (Float) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            iterable = (Iterable) null;
        }
        return Coordinate(f, f2, f3, str, iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "coordinateOf(adj, err, height, node, vec)"))
    @NotNull
    public static final Coordinate Coordinate(@Nullable Float adj, @Nullable Float err, @Nullable Float height, @Nullable String node, @Nullable Iterable<Float> iterable) {
        Coordinate $this$apply = new Coordinate();
        if (adj != null) {
            $this$apply.setAdj(adj.floatValue());
        }
        if (err != null) {
            $this$apply.setErr(err.floatValue());
        }
        if (height != null) {
            $this$apply.setHeight(height.floatValue());
        }
        if (node != null) {
            $this$apply.setNode(node);
        }
        if (iterable != null) {
            $this$apply.setVec(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }
}
