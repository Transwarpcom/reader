package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: UProgressionUtil.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001��¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010��\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001��¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001��¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001��¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"differenceModulo", "Lkotlin/UInt;", "a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, OperatorName.CURVE_TO, "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/internal/UProgressionUtilKt.class */
public final class UProgressionUtilKt {
    /* renamed from: differenceModulo-WZ9TVnA, reason: not valid java name */
    private static final int m3327differenceModuloWZ9TVnA(int a, int b, int c) {
        int ac = UnsignedKt.m2457uintRemainderJ1ME1BU(a, c);
        int bc = UnsignedKt.m2457uintRemainderJ1ME1BU(b, c);
        return UnsignedKt.uintCompare(ac, bc) >= 0 ? UInt.m2244constructorimpl(ac - bc) : UInt.m2244constructorimpl(UInt.m2244constructorimpl(ac - bc) + c);
    }

    /* renamed from: differenceModulo-sambcqE, reason: not valid java name */
    private static final long m3328differenceModulosambcqE(long a, long b, long c) {
        long ac = UnsignedKt.m2459ulongRemaindereb3DHEI(a, c);
        long bc = UnsignedKt.m2459ulongRemaindereb3DHEI(b, c);
        return UnsignedKt.ulongCompare(ac, bc) >= 0 ? ULong.m2323constructorimpl(ac - bc) : ULong.m2323constructorimpl(ULong.m2323constructorimpl(ac - bc) + c);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-Nkh28Cs, reason: not valid java name */
    public static final int m3329getProgressionLastElementNkh28Cs(int start, int end, int step) {
        if (step > 0) {
            return UnsignedKt.uintCompare(start, end) >= 0 ? end : UInt.m2244constructorimpl(end - m3327differenceModuloWZ9TVnA(end, start, UInt.m2244constructorimpl(step)));
        }
        if (step < 0) {
            return UnsignedKt.uintCompare(start, end) <= 0 ? end : UInt.m2244constructorimpl(end + m3327differenceModuloWZ9TVnA(start, end, UInt.m2244constructorimpl(-step)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    /* renamed from: getProgressionLastElement-7ftBX0g, reason: not valid java name */
    public static final long m3330getProgressionLastElement7ftBX0g(long start, long end, long step) {
        if (step > 0) {
            return UnsignedKt.ulongCompare(start, end) >= 0 ? end : ULong.m2323constructorimpl(end - m3328differenceModulosambcqE(end, start, ULong.m2323constructorimpl(step)));
        }
        if (step < 0) {
            return UnsignedKt.ulongCompare(start, end) <= 0 ? end : ULong.m2323constructorimpl(end + m3328differenceModulosambcqE(start, end, ULong.m2323constructorimpl(-step)));
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
