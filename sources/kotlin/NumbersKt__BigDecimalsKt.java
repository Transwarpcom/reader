package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BigDecimals.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010��\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\nH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000eH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000fH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u0001*\u00020\u0001H\u0087\n¨\u0006\u0011"}, d2 = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, xs = "kotlin/NumbersKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/NumbersKt__BigDecimalsKt.class */
class NumbersKt__BigDecimalsKt {
    @InlineOnly
    private static final BigDecimal plus(BigDecimal plus, BigDecimal other) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        BigDecimal bigDecimalAdd = plus.add(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
        return bigDecimalAdd;
    }

    @InlineOnly
    private static final BigDecimal minus(BigDecimal minus, BigDecimal other) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        BigDecimal bigDecimalSubtract = minus.subtract(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalSubtract, "this.subtract(other)");
        return bigDecimalSubtract;
    }

    @InlineOnly
    private static final BigDecimal times(BigDecimal times, BigDecimal other) {
        Intrinsics.checkNotNullParameter(times, "$this$times");
        BigDecimal bigDecimalMultiply = times.multiply(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalMultiply, "this.multiply(other)");
        return bigDecimalMultiply;
    }

    @InlineOnly
    private static final BigDecimal div(BigDecimal div, BigDecimal other) {
        Intrinsics.checkNotNullParameter(div, "$this$div");
        BigDecimal bigDecimalDivide = div.divide(other, RoundingMode.HALF_EVEN);
        Intrinsics.checkNotNullExpressionValue(bigDecimalDivide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimalDivide;
    }

    @InlineOnly
    private static final BigDecimal rem(BigDecimal rem, BigDecimal other) {
        Intrinsics.checkNotNullParameter(rem, "$this$rem");
        BigDecimal bigDecimalRemainder = rem.remainder(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalRemainder, "this.remainder(other)");
        return bigDecimalRemainder;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(BigDecimal unaryMinus) {
        Intrinsics.checkNotNullParameter(unaryMinus, "$this$unaryMinus");
        BigDecimal bigDecimalNegate = unaryMinus.negate();
        Intrinsics.checkNotNullExpressionValue(bigDecimalNegate, "this.negate()");
        return bigDecimalNegate;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal inc(BigDecimal inc) {
        Intrinsics.checkNotNullParameter(inc, "$this$inc");
        BigDecimal bigDecimalAdd = inc.add(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(BigDecimal.ONE)");
        return bigDecimalAdd;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal dec(BigDecimal dec) {
        Intrinsics.checkNotNullParameter(dec, "$this$dec");
        BigDecimal bigDecimalSubtract = dec.subtract(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimalSubtract, "this.subtract(BigDecimal.ONE)");
        return bigDecimalSubtract;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int $this$toBigDecimal) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf($this$toBigDecimal);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(int $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long $this$toBigDecimal) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf($this$toBigDecimal);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this)");
        return bigDecimalValueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(long $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float $this$toBigDecimal) {
        return new BigDecimal(String.valueOf($this$toBigDecimal));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(float $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal(String.valueOf($this$toBigDecimal), mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double $this$toBigDecimal) {
        return new BigDecimal(String.valueOf($this$toBigDecimal));
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(double $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal(String.valueOf($this$toBigDecimal), mathContext);
    }
}
