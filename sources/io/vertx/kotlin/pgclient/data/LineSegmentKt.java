package io.vertx.kotlin.pgclient.data;

import io.vertx.pgclient.data.LineSegment;
import io.vertx.pgclient.data.Point;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: LineSegment.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007\u001a\u001e\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¨\u0006\u0006"}, d2 = {"LineSegment", "Lio/vertx/pgclient/data/LineSegment;", "p1", "Lio/vertx/pgclient/data/Point;", "p2", "lineSegmentOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/LineSegmentKt.class */
public final class LineSegmentKt {
    @NotNull
    public static /* synthetic */ LineSegment lineSegmentOf$default(Point point, Point point2, int i, Object obj) {
        if ((i & 1) != 0) {
            point = (Point) null;
        }
        if ((i & 2) != 0) {
            point2 = (Point) null;
        }
        return lineSegmentOf(point, point2);
    }

    @NotNull
    public static final LineSegment lineSegmentOf(@Nullable Point p1, @Nullable Point p2) {
        LineSegment $this$apply = new LineSegment();
        if (p1 != null) {
            $this$apply.setP1(p1);
        }
        if (p2 != null) {
            $this$apply.setP2(p2);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "lineSegmentOf(p1, p2)"))
    @NotNull
    public static /* synthetic */ LineSegment LineSegment$default(Point point, Point point2, int i, Object obj) {
        if ((i & 1) != 0) {
            point = (Point) null;
        }
        if ((i & 2) != 0) {
            point2 = (Point) null;
        }
        return LineSegment(point, point2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "lineSegmentOf(p1, p2)"))
    @NotNull
    public static final LineSegment LineSegment(@Nullable Point p1, @Nullable Point p2) {
        LineSegment $this$apply = new LineSegment();
        if (p1 != null) {
            $this$apply.setP1(p1);
        }
        if (p2 != null) {
            $this$apply.setP2(p2);
        }
        return $this$apply;
    }
}
