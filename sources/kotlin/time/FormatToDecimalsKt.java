package kotlin.time;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: formatToDecimals.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��.\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0006\n\u0002\b\u0003\u001a\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH��\u001a\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH��\u001a\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH��\"\u001c\u0010��\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0012"}, d2 = {"precisionFormats", "", "Ljava/lang/ThreadLocal;", "Ljava/text/DecimalFormat;", "[Ljava/lang/ThreadLocal;", "rootNegativeExpFormatSymbols", "Ljava/text/DecimalFormatSymbols;", "rootPositiveExpFormatSymbols", "scientificFormat", "createFormatForDecimals", "decimals", "", "formatScientific", "", "value", "", "formatToExactDecimals", "formatUpToDecimals", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/time/FormatToDecimalsKt.class */
public final class FormatToDecimalsKt {
    private static final DecimalFormatSymbols rootNegativeExpFormatSymbols;
    private static final DecimalFormatSymbols rootPositiveExpFormatSymbols;
    private static final ThreadLocal<DecimalFormat>[] precisionFormats;
    private static final ThreadLocal<DecimalFormat> scientificFormat;

    static {
        DecimalFormatSymbols $this$apply = new DecimalFormatSymbols(Locale.ROOT);
        $this$apply.setExponentSeparator("e");
        rootNegativeExpFormatSymbols = $this$apply;
        DecimalFormatSymbols $this$apply2 = new DecimalFormatSymbols(Locale.ROOT);
        $this$apply2.setExponentSeparator("e+");
        rootPositiveExpFormatSymbols = $this$apply2;
        ThreadLocal<DecimalFormat>[] threadLocalArr = new ThreadLocal[4];
        for (int i = 0; i < 4; i++) {
            threadLocalArr[i] = new ThreadLocal<>();
        }
        precisionFormats = threadLocalArr;
        scientificFormat = new ThreadLocal<>();
    }

    private static final DecimalFormat createFormatForDecimals(int decimals) {
        DecimalFormat $this$apply = new DecimalFormat("0", rootNegativeExpFormatSymbols);
        if (decimals > 0) {
            $this$apply.setMinimumFractionDigits(decimals);
        }
        $this$apply.setRoundingMode(RoundingMode.HALF_UP);
        return $this$apply;
    }

    @NotNull
    public static final String formatToExactDecimals(double value, int decimals) {
        DecimalFormat decimalFormatCreateFormatForDecimals;
        if (decimals < precisionFormats.length) {
            ThreadLocal<DecimalFormat> threadLocal = precisionFormats[decimals];
            DecimalFormat decimalFormat = threadLocal.get();
            if (decimalFormat == null) {
                DecimalFormat decimalFormatCreateFormatForDecimals2 = createFormatForDecimals(decimals);
                threadLocal.set(decimalFormatCreateFormatForDecimals2);
                decimalFormat = decimalFormatCreateFormatForDecimals2;
            }
            decimalFormatCreateFormatForDecimals = decimalFormat;
        } else {
            decimalFormatCreateFormatForDecimals = createFormatForDecimals(decimals);
        }
        DecimalFormat format = decimalFormatCreateFormatForDecimals;
        String str = format.format(value);
        Intrinsics.checkNotNullExpressionValue(str, "format.format(value)");
        return str;
    }

    @NotNull
    public static final String formatUpToDecimals(double value, int decimals) {
        DecimalFormat $this$apply = createFormatForDecimals(0);
        $this$apply.setMaximumFractionDigits(decimals);
        String str = $this$apply.format(value);
        Intrinsics.checkNotNullExpressionValue(str, "createFormatForDecimals(… }\n        .format(value)");
        return str;
    }

    @NotNull
    public static final String formatScientific(double value) {
        ThreadLocal<DecimalFormat> threadLocal = scientificFormat;
        DecimalFormat decimalFormat = threadLocal.get();
        if (decimalFormat == null) {
            DecimalFormat $this$apply = new DecimalFormat("0E0", rootNegativeExpFormatSymbols);
            $this$apply.setMinimumFractionDigits(2);
            threadLocal.set($this$apply);
            decimalFormat = $this$apply;
        }
        DecimalFormat $this$apply2 = decimalFormat;
        $this$apply2.setDecimalFormatSymbols((value >= ((double) 1) || value <= ((double) (-1))) ? rootPositiveExpFormatSymbols : rootNegativeExpFormatSymbols);
        String str = $this$apply2.format(value);
        Intrinsics.checkNotNullExpressionValue(str, "scientificFormat.getOrSe… }\n        .format(value)");
        return str;
    }
}
