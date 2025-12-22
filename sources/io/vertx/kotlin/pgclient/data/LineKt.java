package io.vertx.kotlin.pgclient.data;

import io.vertx.pgclient.data.Line;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Line.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\u0005\u001a1\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"Line", "Lio/vertx/pgclient/data/Line;", "a", "", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, OperatorName.CURVE_TO, "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)Lio/vertx/pgclient/data/Line;", "lineOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/data/LineKt.class */
public final class LineKt {
    @NotNull
    public static /* synthetic */ Line lineOf$default(Double d, Double d2, Double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = (Double) null;
        }
        if ((i & 2) != 0) {
            d2 = (Double) null;
        }
        if ((i & 4) != 0) {
            d3 = (Double) null;
        }
        return lineOf(d, d2, d3);
    }

    @NotNull
    public static final Line lineOf(@Nullable Double a, @Nullable Double b, @Nullable Double c) {
        Line $this$apply = new Line();
        if (a != null) {
            $this$apply.setA(a.doubleValue());
        }
        if (b != null) {
            $this$apply.setB(b.doubleValue());
        }
        if (c != null) {
            $this$apply.setC(c.doubleValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "lineOf(a, b, c)"))
    @NotNull
    public static /* synthetic */ Line Line$default(Double d, Double d2, Double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = (Double) null;
        }
        if ((i & 2) != 0) {
            d2 = (Double) null;
        }
        if ((i & 4) != 0) {
            d3 = (Double) null;
        }
        return Line(d, d2, d3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "lineOf(a, b, c)"))
    @NotNull
    public static final Line Line(@Nullable Double a, @Nullable Double b, @Nullable Double c) {
        Line $this$apply = new Line();
        if (a != null) {
            $this$apply.setA(a.doubleValue());
        }
        if (b != null) {
            $this$apply.setB(b.doubleValue());
        }
        if (c != null) {
            $this$apply.setC(c.doubleValue());
        }
        return $this$apply;
    }
}
