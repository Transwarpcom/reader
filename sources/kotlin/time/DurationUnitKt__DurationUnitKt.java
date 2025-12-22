package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationUnitKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;

/* compiled from: DurationUnit.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��\u0010\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\u0010\u0010��\u001a\u00020\u0001*\u00060\u0002j\u0002`\u0003H\u0001¨\u0006\u0004"}, d2 = {"shortName", "", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "kotlin-stdlib"}, xs = "kotlin/time/DurationUnitKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/time/DurationUnitKt__DurationUnitKt.class */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalTime
    @NotNull
    public static final String shortName(@NotNull TimeUnit shortName) {
        Intrinsics.checkNotNullParameter(shortName, "$this$shortName");
        switch (DurationUnitKt.WhenMappings.$EnumSwitchMapping$0[shortName.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return "ms";
            case 4:
                return OperatorName.CLOSE_AND_STROKE;
            case 5:
                return "m";
            case 6:
                return OperatorName.CLOSE_PATH;
            case 7:
                return "d";
            default:
                throw new IllegalStateException(("Unknown unit: " + shortName).toString());
        }
    }
}
