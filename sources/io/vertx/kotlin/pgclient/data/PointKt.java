package io.vertx.kotlin.pgclient.data;

import io.vertx.pgclient.data.Point;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Point.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\u0004\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"Point", "Lio/vertx/pgclient/data/Point;", "x", "", OperatorName.CURVE_TO_REPLICATE_FINAL_POINT, "(Ljava/lang/Double;Ljava/lang/Double;)Lio/vertx/pgclient/data/Point;", "pointOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/PointKt.class */
public final class PointKt {
    @NotNull
    public static /* synthetic */ Point pointOf$default(Double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = (Double) null;
        }
        if ((i & 2) != 0) {
            d2 = (Double) null;
        }
        return pointOf(d, d2);
    }

    @NotNull
    public static final Point pointOf(@Nullable Double x, @Nullable Double y) {
        Point $this$apply = new Point();
        if (x != null) {
            $this$apply.setX(x.doubleValue());
        }
        if (y != null) {
            $this$apply.setY(y.doubleValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pointOf(x, y)"))
    @NotNull
    public static /* synthetic */ Point Point$default(Double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = (Double) null;
        }
        if ((i & 2) != 0) {
            d2 = (Double) null;
        }
        return Point(d, d2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "pointOf(x, y)"))
    @NotNull
    public static final Point Point(@Nullable Double x, @Nullable Double y) {
        Point $this$apply = new Point();
        if (x != null) {
            $this$apply.setX(x.doubleValue());
        }
        if (y != null) {
            $this$apply.setY(y.doubleValue());
        }
        return $this$apply;
    }
}
