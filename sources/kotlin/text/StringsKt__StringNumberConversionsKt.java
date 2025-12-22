package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringNumberConversions.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��.\n��\n\u0002\u0010\u0001\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0010\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H��\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, xs = "kotlin/text/StringsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/StringsKt__StringNumberConversionsKt.class */
public class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String toByteOrNull) {
        Intrinsics.checkNotNullParameter(toByteOrNull, "$this$toByteOrNull");
        return StringsKt.toByteOrNull(toByteOrNull, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String toByteOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toByteOrNull, "$this$toByteOrNull");
        Integer intOrNull = StringsKt.toIntOrNull(toByteOrNull, radix);
        if (intOrNull == null) {
            return null;
        }
        int iIntValue = intOrNull.intValue();
        if (iIntValue < -128 || iIntValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) iIntValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String toShortOrNull) {
        Intrinsics.checkNotNullParameter(toShortOrNull, "$this$toShortOrNull");
        return StringsKt.toShortOrNull(toShortOrNull, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String toShortOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toShortOrNull, "$this$toShortOrNull");
        Integer intOrNull = StringsKt.toIntOrNull(toShortOrNull, radix);
        if (intOrNull == null) {
            return null;
        }
        int iIntValue = intOrNull.intValue();
        if (iIntValue < -32768 || iIntValue > 32767) {
            return null;
        }
        return Short.valueOf((short) iIntValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String toIntOrNull) {
        Intrinsics.checkNotNullParameter(toIntOrNull, "$this$toIntOrNull");
        return StringsKt.toIntOrNull(toIntOrNull, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String toIntOrNull, int radix) {
        int start;
        boolean isNegative;
        int limit;
        Intrinsics.checkNotNullParameter(toIntOrNull, "$this$toIntOrNull");
        CharsKt.checkRadix(radix);
        int length = toIntOrNull.length();
        if (length == 0) {
            return null;
        }
        char firstChar = toIntOrNull.charAt(0);
        if (Intrinsics.compare((int) firstChar, 48) < 0) {
            if (length == 1) {
                return null;
            }
            start = 1;
            if (firstChar == '-') {
                isNegative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar == '+') {
                isNegative = false;
                limit = -2147483647;
            } else {
                return null;
            }
        } else {
            start = 0;
            isNegative = false;
            limit = -2147483647;
        }
        int limitBeforeMul = -59652323;
        int result = 0;
        for (int i = start; i < length; i++) {
            int digit = CharsKt.digitOf(toIntOrNull.charAt(i), radix);
            if (digit < 0) {
                return null;
            }
            if (result < limitBeforeMul) {
                if (limitBeforeMul == -59652323) {
                    limitBeforeMul = limit / radix;
                    if (result < limitBeforeMul) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            int result2 = result * radix;
            if (result2 < limit + digit) {
                return null;
            }
            result = result2 - digit;
        }
        return isNegative ? Integer.valueOf(result) : Integer.valueOf(-result);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String toLongOrNull) {
        Intrinsics.checkNotNullParameter(toLongOrNull, "$this$toLongOrNull");
        return StringsKt.toLongOrNull(toLongOrNull, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String toLongOrNull, int radix) {
        int start;
        boolean isNegative;
        long limit;
        Intrinsics.checkNotNullParameter(toLongOrNull, "$this$toLongOrNull");
        CharsKt.checkRadix(radix);
        int length = toLongOrNull.length();
        if (length == 0) {
            return null;
        }
        char firstChar = toLongOrNull.charAt(0);
        if (Intrinsics.compare((int) firstChar, 48) < 0) {
            if (length == 1) {
                return null;
            }
            start = 1;
            if (firstChar == '-') {
                isNegative = true;
                limit = Long.MIN_VALUE;
            } else if (firstChar == '+') {
                isNegative = false;
                limit = -9223372036854775807L;
            } else {
                return null;
            }
        } else {
            start = 0;
            isNegative = false;
            limit = -9223372036854775807L;
        }
        long limitBeforeMul = -256204778801521550L;
        long result = 0;
        for (int i = start; i < length; i++) {
            int digit = CharsKt.digitOf(toLongOrNull.charAt(i), radix);
            if (digit < 0) {
                return null;
            }
            if (result < limitBeforeMul) {
                if (limitBeforeMul == -256204778801521550L) {
                    limitBeforeMul = limit / radix;
                    if (result < limitBeforeMul) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            long result2 = result * radix;
            if (result2 < limit + digit) {
                return null;
            }
            result = result2 - digit;
        }
        return isNegative ? Long.valueOf(result) : Long.valueOf(-result);
    }

    @NotNull
    public static final Void numberFormatError(@NotNull String input) {
        Intrinsics.checkNotNullParameter(input, "input");
        throw new NumberFormatException("Invalid number format: '" + input + '\'');
    }
}
