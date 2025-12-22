package kotlin.text;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UStrings.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��,\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010��\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010��\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010��\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001��¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001��\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001��¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001��\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001��¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001��\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001��¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001��\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"})
@JvmName(name = "UStringsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/UStringsKt.class */
public final class UStringsKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @NotNull
    /* renamed from: toString-LxnNnR4, reason: not valid java name */
    public static final String m4044toStringLxnNnR4(byte $this$toString, int radix) {
        String string = Integer.toString($this$toString & 255, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @NotNull
    /* renamed from: toString-olVBNx4, reason: not valid java name */
    public static final String m4045toStringolVBNx4(short $this$toString, int radix) {
        String string = Integer.toString($this$toString & 65535, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @NotNull
    /* renamed from: toString-V7xB4Y4, reason: not valid java name */
    public static final String m4046toStringV7xB4Y4(int $this$toString, int radix) {
        String string = Long.toString($this$toString & 4294967295L, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @NotNull
    /* renamed from: toString-JSWoG40, reason: not valid java name */
    public static final String m4047toStringJSWoG40(long $this$toString, int radix) {
        return UnsignedKt.ulongToString($this$toString, CharsKt.checkRadix(radix));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final byte toUByte(@NotNull String toUByte) {
        Intrinsics.checkNotNullParameter(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte);
        if (uByteOrNull != null) {
            return uByteOrNull.m2170unboximpl();
        }
        StringsKt.numberFormatError(toUByte);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final byte toUByte(@NotNull String toUByte, int radix) {
        Intrinsics.checkNotNullParameter(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte, radix);
        if (uByteOrNull != null) {
            return uByteOrNull.m2170unboximpl();
        }
        StringsKt.numberFormatError(toUByte);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final short toUShort(@NotNull String toUShort) {
        Intrinsics.checkNotNullParameter(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort);
        if (uShortOrNull != null) {
            return uShortOrNull.m2434unboximpl();
        }
        StringsKt.numberFormatError(toUShort);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final short toUShort(@NotNull String toUShort, int radix) {
        Intrinsics.checkNotNullParameter(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort, radix);
        if (uShortOrNull != null) {
            return uShortOrNull.m2434unboximpl();
        }
        StringsKt.numberFormatError(toUShort);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int toUInt(@NotNull String toUInt) {
        Intrinsics.checkNotNullParameter(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt);
        if (uIntOrNull != null) {
            return uIntOrNull.m2249unboximpl();
        }
        StringsKt.numberFormatError(toUInt);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final int toUInt(@NotNull String toUInt, int radix) {
        Intrinsics.checkNotNullParameter(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt, radix);
        if (uIntOrNull != null) {
            return uIntOrNull.m2249unboximpl();
        }
        StringsKt.numberFormatError(toUInt);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long toULong(@NotNull String toULong) {
        Intrinsics.checkNotNullParameter(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong);
        if (uLongOrNull != null) {
            return uLongOrNull.m2328unboximpl();
        }
        StringsKt.numberFormatError(toULong);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    public static final long toULong(@NotNull String toULong, int radix) {
        Intrinsics.checkNotNullParameter(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong, radix);
        if (uLongOrNull != null) {
            return uLongOrNull.m2328unboximpl();
        }
        StringsKt.numberFormatError(toULong);
        throw new KotlinNothingValueException();
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String toUByteOrNull) {
        Intrinsics.checkNotNullParameter(toUByteOrNull, "$this$toUByteOrNull");
        return toUByteOrNull(toUByteOrNull, 10);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String toUByteOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toUByteOrNull, "$this$toUByteOrNull");
        UInt uIntOrNull = toUIntOrNull(toUByteOrNull, radix);
        if (uIntOrNull == null) {
            return null;
        }
        int iM2249unboximpl = uIntOrNull.m2249unboximpl();
        if (UnsignedKt.uintCompare(iM2249unboximpl, UInt.m2244constructorimpl((-1) & 255)) > 0) {
            return null;
        }
        return UByte.m2166boximpl(UByte.m2165constructorimpl((byte) iM2249unboximpl));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String toUShortOrNull) {
        Intrinsics.checkNotNullParameter(toUShortOrNull, "$this$toUShortOrNull");
        return toUShortOrNull(toUShortOrNull, 10);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String toUShortOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toUShortOrNull, "$this$toUShortOrNull");
        UInt uIntOrNull = toUIntOrNull(toUShortOrNull, radix);
        if (uIntOrNull == null) {
            return null;
        }
        int iM2249unboximpl = uIntOrNull.m2249unboximpl();
        if (UnsignedKt.uintCompare(iM2249unboximpl, UInt.m2244constructorimpl((-1) & 65535)) > 0) {
            return null;
        }
        return UShort.m2430boximpl(UShort.m2429constructorimpl((short) iM2249unboximpl));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String toUIntOrNull) {
        Intrinsics.checkNotNullParameter(toUIntOrNull, "$this$toUIntOrNull");
        return toUIntOrNull(toUIntOrNull, 10);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String toUIntOrNull, int radix) {
        int start;
        Intrinsics.checkNotNullParameter(toUIntOrNull, "$this$toUIntOrNull");
        CharsKt.checkRadix(radix);
        int length = toUIntOrNull.length();
        if (length == 0) {
            return null;
        }
        char firstChar = toUIntOrNull.charAt(0);
        if (Intrinsics.compare((int) firstChar, 48) < 0) {
            if (length == 1 || firstChar != '+') {
                return null;
            }
            start = 1;
        } else {
            start = 0;
        }
        int limitBeforeMul = 119304647;
        int uradix = UInt.m2244constructorimpl(radix);
        int result = 0;
        for (int i = start; i < length; i++) {
            int digit = CharsKt.digitOf(toUIntOrNull.charAt(i), radix);
            if (digit < 0) {
                return null;
            }
            if (UnsignedKt.uintCompare(result, limitBeforeMul) > 0) {
                if (limitBeforeMul == 119304647) {
                    limitBeforeMul = UnsignedKt.m2456uintDivideJ1ME1BU(-1, uradix);
                    if (UnsignedKt.uintCompare(result, limitBeforeMul) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            int result2 = UInt.m2244constructorimpl(result * uradix);
            result = UInt.m2244constructorimpl(result2 + UInt.m2244constructorimpl(digit));
            if (UnsignedKt.uintCompare(result, result2) < 0) {
                return null;
            }
        }
        return UInt.m2245boximpl(result);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final ULong toULongOrNull(@NotNull String toULongOrNull) {
        Intrinsics.checkNotNullParameter(toULongOrNull, "$this$toULongOrNull");
        return toULongOrNull(toULongOrNull, 10);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    @Nullable
    public static final ULong toULongOrNull(@NotNull String toULongOrNull, int radix) {
        int start;
        Intrinsics.checkNotNullParameter(toULongOrNull, "$this$toULongOrNull");
        CharsKt.checkRadix(radix);
        int length = toULongOrNull.length();
        if (length == 0) {
            return null;
        }
        char firstChar = toULongOrNull.charAt(0);
        if (Intrinsics.compare((int) firstChar, 48) < 0) {
            if (length == 1 || firstChar != '+') {
                return null;
            }
            start = 1;
        } else {
            start = 0;
        }
        long limitBeforeMul = 512409557603043100L;
        long uradix = ULong.m2323constructorimpl(radix);
        long result = 0;
        for (int i = start; i < length; i++) {
            int digit = CharsKt.digitOf(toULongOrNull.charAt(i), radix);
            if (digit < 0) {
                return null;
            }
            if (UnsignedKt.ulongCompare(result, limitBeforeMul) > 0) {
                if (limitBeforeMul == 512409557603043100L) {
                    limitBeforeMul = UnsignedKt.m2458ulongDivideeb3DHEI(-1L, uradix);
                    if (UnsignedKt.ulongCompare(result, limitBeforeMul) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            long result2 = ULong.m2323constructorimpl(result * uradix);
            result = ULong.m2323constructorimpl(result2 + ULong.m2323constructorimpl(UInt.m2244constructorimpl(digit) & 4294967295L));
            if (UnsignedKt.ulongCompare(result, result2) < 0) {
                return null;
            }
        }
        return ULong.m2324boximpl(result);
    }
}
