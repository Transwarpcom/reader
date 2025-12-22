package kotlin;

import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;

/* compiled from: UnsignedUtils.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��0\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0001\u001a\"\u0010\f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001ø\u0001��¢\u0006\u0004\b\r\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0001ø\u0001��¢\u0006\u0004\b\u0010\u0010\u000e\u001a\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\tH\u0001\u001a\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0013H\u0001\u001a\"\u0010\u0014\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001ø\u0001��¢\u0006\u0004\b\u0015\u0010\u0016\u001a\"\u0010\u0017\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0001ø\u0001��¢\u0006\u0004\b\u0018\u0010\u0016\u001a\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0013H\u0001\u001a\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u0013H��\u001a\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\tH��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"doubleToUInt", "Lkotlin/UInt;", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "", "(D)I", "doubleToULong", "Lkotlin/ULong;", "(D)J", "uintCompare", "", "v1", "v2", "uintDivide", "uintDivide-J1ME1BU", "(II)I", "uintRemainder", "uintRemainder-J1ME1BU", "uintToDouble", "ulongCompare", "", "ulongDivide", "ulongDivide-eb3DHEI", "(JJ)J", "ulongRemainder", "ulongRemainder-eb3DHEI", "ulongToDouble", "ulongToString", "", "base", "kotlin-stdlib"})
@JvmName(name = "UnsignedKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/UnsignedKt.class */
public final class UnsignedKt {
    @PublishedApi
    public static final int uintCompare(int v1, int v2) {
        return Intrinsics.compare(v1 ^ Integer.MIN_VALUE, v2 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    public static final int ulongCompare(long v1, long v2) {
        return ((v1 ^ Long.MIN_VALUE) > (v2 ^ Long.MIN_VALUE) ? 1 : ((v1 ^ Long.MIN_VALUE) == (v2 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    @PublishedApi
    /* renamed from: uintDivide-J1ME1BU, reason: not valid java name */
    public static final int m2456uintDivideJ1ME1BU(int v1, int v2) {
        return UInt.m2244constructorimpl((int) ((v1 & 4294967295L) / (v2 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: uintRemainder-J1ME1BU, reason: not valid java name */
    public static final int m2457uintRemainderJ1ME1BU(int v1, int v2) {
        return UInt.m2244constructorimpl((int) ((v1 & 4294967295L) % (v2 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: ulongDivide-eb3DHEI, reason: not valid java name */
    public static final long m2458ulongDivideeb3DHEI(long v1, long v2) {
        if (v2 < 0) {
            return ulongCompare(v1, v2) < 0 ? ULong.m2323constructorimpl(0L) : ULong.m2323constructorimpl(1L);
        }
        if (v1 < 0) {
            long quotient = ((v1 >>> 1) / v2) << 1;
            long rem = v1 - (quotient * v2);
            return ULong.m2323constructorimpl(quotient + (ulongCompare(ULong.m2323constructorimpl(rem), ULong.m2323constructorimpl(v2)) >= 0 ? 1 : 0));
        }
        return ULong.m2323constructorimpl(v1 / v2);
    }

    @PublishedApi
    /* renamed from: ulongRemainder-eb3DHEI, reason: not valid java name */
    public static final long m2459ulongRemaindereb3DHEI(long v1, long v2) {
        if (v2 < 0) {
            if (ulongCompare(v1, v2) < 0) {
                return v1;
            }
            return ULong.m2323constructorimpl(v1 - v2);
        }
        if (v1 < 0) {
            long quotient = ((v1 >>> 1) / v2) << 1;
            long rem = v1 - (quotient * v2);
            return ULong.m2323constructorimpl(rem - (ulongCompare(ULong.m2323constructorimpl(rem), ULong.m2323constructorimpl(v2)) >= 0 ? v2 : 0L));
        }
        return ULong.m2323constructorimpl(v1 % v2);
    }

    @PublishedApi
    public static final int doubleToUInt(double v) {
        if (Double.isNaN(v) || v <= uintToDouble(0)) {
            return 0;
        }
        if (v >= uintToDouble(-1)) {
            return -1;
        }
        return v <= ((double) Integer.MAX_VALUE) ? UInt.m2244constructorimpl((int) v) : UInt.m2244constructorimpl(UInt.m2244constructorimpl((int) (v - Integer.MAX_VALUE)) + UInt.m2244constructorimpl(Integer.MAX_VALUE));
    }

    @PublishedApi
    public static final long doubleToULong(double v) {
        if (Double.isNaN(v) || v <= ulongToDouble(0L)) {
            return 0L;
        }
        if (v >= ulongToDouble(-1L)) {
            return -1L;
        }
        return v < ((double) Long.MAX_VALUE) ? ULong.m2323constructorimpl((long) v) : ULong.m2323constructorimpl(ULong.m2323constructorimpl((long) (v - 9.223372036854776E18d)) - Long.MIN_VALUE);
    }

    @PublishedApi
    public static final double uintToDouble(int v) {
        return (v & Integer.MAX_VALUE) + (((v >>> 31) << 30) * 2);
    }

    @PublishedApi
    public static final double ulongToDouble(long v) {
        return ((v >>> 11) * 2048) + (v & 2047);
    }

    @NotNull
    public static final String ulongToString(long v) {
        return ulongToString(v, 10);
    }

    @NotNull
    public static final String ulongToString(long v, int base) {
        if (v >= 0) {
            String string = Long.toString(v, CharsKt.checkRadix(base));
            Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
            return string;
        }
        long quotient = ((v >>> 1) / base) << 1;
        long rem = v - (quotient * base);
        if (rem >= base) {
            rem -= base;
            quotient++;
        }
        StringBuilder sb = new StringBuilder();
        String string2 = Long.toString(quotient, CharsKt.checkRadix(base));
        Intrinsics.checkNotNullExpressionValue(string2, "java.lang.Long.toString(this, checkRadix(radix))");
        StringBuilder sbAppend = sb.append(string2);
        String string3 = Long.toString(rem, CharsKt.checkRadix(base));
        Intrinsics.checkNotNullExpressionValue(string3, "java.lang.Long.toString(this, checkRadix(radix))");
        return sbAppend.append(string3).toString();
    }
}
