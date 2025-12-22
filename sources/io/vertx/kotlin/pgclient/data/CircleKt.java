package io.vertx.kotlin.pgclient.data;

import io.vertx.pgclient.data.Circle;
import io.vertx.pgclient.data.Point;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Circle.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Circle", "Lio/vertx/pgclient/data/Circle;", "centerPoint", "Lio/vertx/pgclient/data/Point;", "radius", "", "(Lio/vertx/pgclient/data/Point;Ljava/lang/Double;)Lio/vertx/pgclient/data/Circle;", "circleOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/CircleKt.class */
public final class CircleKt {
    @NotNull
    public static /* synthetic */ Circle circleOf$default(Point point, Double d, int i, Object obj) {
        if ((i & 1) != 0) {
            point = (Point) null;
        }
        if ((i & 2) != 0) {
            d = (Double) null;
        }
        return circleOf(point, d);
    }

    @NotNull
    public static final Circle circleOf(@Nullable Point centerPoint, @Nullable Double radius) {
        Circle $this$apply = new Circle();
        if (centerPoint != null) {
            $this$apply.setCenterPoint(centerPoint);
        }
        if (radius != null) {
            $this$apply.setRadius(radius.doubleValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "circleOf(centerPoint, radius)"))
    @NotNull
    public static /* synthetic */ Circle Circle$default(Point point, Double d, int i, Object obj) {
        if ((i & 1) != 0) {
            point = (Point) null;
        }
        if ((i & 2) != 0) {
            d = (Double) null;
        }
        return Circle(point, d);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "circleOf(centerPoint, radius)"))
    @NotNull
    public static final Circle Circle(@Nullable Point centerPoint, @Nullable Double radius) {
        Circle $this$apply = new Circle();
        if (centerPoint != null) {
            $this$apply.setCenterPoint(centerPoint);
        }
        if (radius != null) {
            $this$apply.setRadius(radius.doubleValue());
        }
        return $this$apply;
    }
}
