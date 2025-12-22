package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringNumberConversionsJVM.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��Z\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n��\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n��\n\u0002\u0010\n\n\u0002\b\u0002\u001a4\u0010��\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\u0082\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\u0087\b\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u000e\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u0003H\u0007\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\t*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0007\u001a\r\u0010\r\u001a\u00020\u000e*\u00020\u0003H\u0087\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007\u001a\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0003H\u0087\b\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\u0003H\u0087\b¢\u0006\u0002\b\u0014\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u0015\u001a\u00020\u0016*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0018*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001a\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0003H\u0087\b\u001a\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001c*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001e\u001a\r\u0010\u001f\u001a\u00020\u0010*\u00020\u0003H\u0087\b\u001a\u0015\u0010\u001f\u001a\u00020\u0010*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010 \u001a\u00020!*\u00020\u0003H\u0087\b\u001a\u0015\u0010 \u001a\u00020!*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\r\u0010\"\u001a\u00020#*\u00020\u0003H\u0087\b\u001a\u0015\u0010\"\u001a\u00020#*\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b\u001a\u0015\u0010$\u001a\u00020\u0003*\u00020#2\u0006\u0010\u000f\u001a\u00020\u0010H\u0087\b¨\u0006%"}, d2 = {"screenFloatValue", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "toBigInteger", "Ljava/math/BigInteger;", "radix", "", "toBigIntegerOrNull", "toBoolean", "", "toBooleanNullable", "toByte", "", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toLong", "", "toShort", "", "toString", "kotlin-stdlib"}, xs = "kotlin/text/StringsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/StringsKt__StringNumberConversionsJVMKt.class */
public class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(byte $this$toString, int radix) {
        String string = Integer.toString($this$toString, CharsKt.checkRadix(CharsKt.checkRadix(radix)));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(short $this$toString, int radix) {
        String string = Integer.toString($this$toString, CharsKt.checkRadix(CharsKt.checkRadix(radix)));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(int $this$toString, int radix) {
        String string = Integer.toString($this$toString, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(long $this$toString, int radix) {
        String string = Long.toString($this$toString, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "toBooleanNullable")
    @InlineOnly
    private static final boolean toBooleanNullable(String $this$toBoolean) {
        return Boolean.parseBoolean($this$toBoolean);
    }

    @InlineOnly
    private static final byte toByte(String $this$toByte) {
        return Byte.parseByte($this$toByte);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte toByte(String $this$toByte, int radix) {
        return Byte.parseByte($this$toByte, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final short toShort(String $this$toShort) {
        return Short.parseShort($this$toShort);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short toShort(String $this$toShort, int radix) {
        return Short.parseShort($this$toShort, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final int toInt(String $this$toInt) {
        return Integer.parseInt($this$toInt);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int toInt(String $this$toInt, int radix) {
        return Integer.parseInt($this$toInt, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final long toLong(String $this$toLong) {
        return Long.parseLong($this$toLong);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long toLong(String $this$toLong, int radix) {
        return Long.parseLong($this$toLong, CharsKt.checkRadix(radix));
    }

    @InlineOnly
    private static final float toFloat(String $this$toFloat) {
        return Float.parseFloat($this$toFloat);
    }

    @InlineOnly
    private static final double toDouble(String $this$toDouble) {
        return Double.parseDouble($this$toDouble);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Float toFloatOrNull(@NotNull String toFloatOrNull) {
        Float f;
        Float fValueOf;
        Intrinsics.checkNotNullParameter(toFloatOrNull, "$this$toFloatOrNull");
        try {
            if (ScreenFloatValueRegEx.value.matches(toFloatOrNull)) {
                fValueOf = Float.valueOf(Float.parseFloat(toFloatOrNull));
            } else {
                fValueOf = null;
            }
            f = fValueOf;
        } catch (NumberFormatException e) {
            f = null;
        }
        return f;
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Double toDoubleOrNull(@NotNull String toDoubleOrNull) {
        Double d;
        Double dValueOf;
        Intrinsics.checkNotNullParameter(toDoubleOrNull, "$this$toDoubleOrNull");
        try {
            if (ScreenFloatValueRegEx.value.matches(toDoubleOrNull)) {
                dValueOf = Double.valueOf(Double.parseDouble(toDoubleOrNull));
            } else {
                dValueOf = null;
            }
            d = dValueOf;
        } catch (NumberFormatException e) {
            d = null;
        }
        return d;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String $this$toBigInteger) {
        return new BigInteger($this$toBigInteger);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String $this$toBigInteger, int radix) {
        return new BigInteger($this$toBigInteger, CharsKt.checkRadix(radix));
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String toBigIntegerOrNull) {
        Intrinsics.checkNotNullParameter(toBigIntegerOrNull, "$this$toBigIntegerOrNull");
        return StringsKt.toBigIntegerOrNull(toBigIntegerOrNull, 10);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String toBigIntegerOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toBigIntegerOrNull, "$this$toBigIntegerOrNull");
        CharsKt.checkRadix(radix);
        int length = toBigIntegerOrNull.length();
        switch (length) {
            case 0:
                return null;
            case 1:
                if (CharsKt.digitOf(toBigIntegerOrNull.charAt(0), radix) < 0) {
                    return null;
                }
                break;
            default:
                int start = toBigIntegerOrNull.charAt(0) == '-' ? 1 : 0;
                for (int index = start; index < length; index++) {
                    if (CharsKt.digitOf(toBigIntegerOrNull.charAt(index), radix) < 0) {
                        return null;
                    }
                }
                break;
        }
        return new BigInteger(toBigIntegerOrNull, CharsKt.checkRadix(radix));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String $this$toBigDecimal) {
        return new BigDecimal($this$toBigDecimal);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String toBigDecimalOrNull) {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        Intrinsics.checkNotNullParameter(toBigDecimalOrNull, "$this$toBigDecimalOrNull");
        try {
            if (ScreenFloatValueRegEx.value.matches(toBigDecimalOrNull)) {
                bigDecimal2 = new BigDecimal(toBigDecimalOrNull);
            } else {
                bigDecimal2 = null;
            }
            bigDecimal = bigDecimal2;
        } catch (NumberFormatException e) {
            bigDecimal = null;
        }
        return bigDecimal;
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String toBigDecimalOrNull, @NotNull MathContext mathContext) {
        BigDecimal bigDecimal;
        BigDecimal bigDecimal2;
        Intrinsics.checkNotNullParameter(toBigDecimalOrNull, "$this$toBigDecimalOrNull");
        Intrinsics.checkNotNullParameter(mathContext, "mathContext");
        try {
            if (ScreenFloatValueRegEx.value.matches(toBigDecimalOrNull)) {
                bigDecimal2 = new BigDecimal(toBigDecimalOrNull, mathContext);
            } else {
                bigDecimal2 = null;
            }
            bigDecimal = bigDecimal2;
        } catch (NumberFormatException e) {
            bigDecimal = null;
        }
        return bigDecimal;
    }

    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String str, Function1<? super String, ? extends T> function1) {
        T t;
        T tInvoke;
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                tInvoke = function1.invoke(str);
            } else {
                tInvoke = null;
            }
            t = tInvoke;
        } catch (NumberFormatException e) {
            t = null;
        }
        return t;
    }
}
