package kotlin.sequences;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: _USequences.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��\"\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001��¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010��\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001��¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001��¢\u0006\u0004\b\u000b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, xs = "kotlin/sequences/USequencesKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/sequences/USequencesKt___USequencesKt.class */
class USequencesKt___USequencesKt {
    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUInt")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(@NotNull Sequence<UInt> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int sum2 = 0;
        Iterator<UInt> it = sum.iterator();
        while (it.hasNext()) {
            int element = it.next().m2249unboximpl();
            sum2 = UInt.m2244constructorimpl(sum2 + element);
        }
        return sum2;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfULong")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long sumOfULong(@NotNull Sequence<ULong> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        long sum2 = 0;
        Iterator<ULong> it = sum.iterator();
        while (it.hasNext()) {
            long element = it.next().m2328unboximpl();
            sum2 = ULong.m2323constructorimpl(sum2 + element);
        }
        return sum2;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUByte")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUByte(@NotNull Sequence<UByte> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int sum2 = 0;
        Iterator<UByte> it = sum.iterator();
        while (it.hasNext()) {
            byte element = it.next().m2170unboximpl();
            sum2 = UInt.m2244constructorimpl(sum2 + UInt.m2244constructorimpl(element & 255));
        }
        return sum2;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUShort")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUShort(@NotNull Sequence<UShort> sum) {
        Intrinsics.checkNotNullParameter(sum, "$this$sum");
        int sum2 = 0;
        Iterator<UShort> it = sum.iterator();
        while (it.hasNext()) {
            short element = it.next().m2434unboximpl();
            sum2 = UInt.m2244constructorimpl(sum2 + UInt.m2244constructorimpl(element & 65535));
        }
        return sum2;
    }
}
