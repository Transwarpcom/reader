package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: _UCollections.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��F\n��\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001��¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010��\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001��¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001��¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001��¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001��¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001��¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"}, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/UCollectionsKt___UCollectionsKt.class */
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull Collection<UByte> toUByteArray) {
        Intrinsics.checkNotNullParameter(toUByteArray, "$this$toUByteArray");
        byte[] result = UByteArray.m2181constructorimpl(toUByteArray.size());
        int index = 0;
        Iterator<UByte> it = toUByteArray.iterator();
        while (it.hasNext()) {
            byte element = it.next().m2170unboximpl();
            int i = index;
            index++;
            UByteArray.m2174setVurrAj0(result, i, element);
        }
        return result;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull Collection<UInt> toUIntArray) {
        Intrinsics.checkNotNullParameter(toUIntArray, "$this$toUIntArray");
        int[] result = UIntArray.m2260constructorimpl(toUIntArray.size());
        int index = 0;
        Iterator<UInt> it = toUIntArray.iterator();
        while (it.hasNext()) {
            int element = it.next().m2249unboximpl();
            int i = index;
            index++;
            UIntArray.m2253setVXSXFK8(result, i, element);
        }
        return result;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull Collection<ULong> toULongArray) {
        Intrinsics.checkNotNullParameter(toULongArray, "$this$toULongArray");
        long[] result = ULongArray.m2339constructorimpl(toULongArray.size());
        int index = 0;
        Iterator<ULong> it = toULongArray.iterator();
        while (it.hasNext()) {
            long element = it.next().m2328unboximpl();
            int i = index;
            index++;
            ULongArray.m2332setk8EXiF4(result, i, element);
        }
        return result;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull Collection<UShort> toUShortArray) {
        Intrinsics.checkNotNullParameter(toUShortArray, "$this$toUShortArray");
        short[] result = UShortArray.m2445constructorimpl(toUShortArray.size());
        int index = 0;
        Iterator<UShort> it = toUShortArray.iterator();
        while (it.hasNext()) {
            short element = it.next().m2434unboximpl();
            int i = index;
            index++;
            UShortArray.m2438set01HTLdE(result, i, element);
        }
        return result;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "sumOfUInt")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int sumOfUInt(@NotNull Iterable<UInt> sum) {
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
    public static final long sumOfULong(@NotNull Iterable<ULong> sum) {
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
    public static final int sumOfUByte(@NotNull Iterable<UByte> sum) {
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
    public static final int sumOfUShort(@NotNull Iterable<UShort> sum) {
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
