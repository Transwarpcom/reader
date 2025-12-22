package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: BigIntegers.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��(\n��\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0087\b\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\u0016H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f¨\u0006\u0019"}, d2 = {"and", "Ljava/math/BigInteger;", "other", "dec", "div", "inc", "inv", "minus", "or", "plus", "rem", "shl", OperatorName.ENDPATH, "", "shr", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "toBigInteger", "", "unaryMinus", "xor", "kotlin-stdlib"}, xs = "kotlin/NumbersKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/NumbersKt__BigIntegersKt.class */
class NumbersKt__BigIntegersKt extends NumbersKt__BigDecimalsKt {
    @InlineOnly
    private static final BigInteger plus(BigInteger plus, BigInteger other) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        BigInteger bigIntegerAdd = plus.add(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
        return bigIntegerAdd;
    }

    @InlineOnly
    private static final BigInteger minus(BigInteger minus, BigInteger other) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        BigInteger bigIntegerSubtract = minus.subtract(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerSubtract, "this.subtract(other)");
        return bigIntegerSubtract;
    }

    @InlineOnly
    private static final BigInteger times(BigInteger times, BigInteger other) {
        Intrinsics.checkNotNullParameter(times, "$this$times");
        BigInteger bigIntegerMultiply = times.multiply(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerMultiply, "this.multiply(other)");
        return bigIntegerMultiply;
    }

    @InlineOnly
    private static final BigInteger div(BigInteger div, BigInteger other) {
        Intrinsics.checkNotNullParameter(div, "$this$div");
        BigInteger bigIntegerDivide = div.divide(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerDivide, "this.divide(other)");
        return bigIntegerDivide;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(BigInteger rem, BigInteger other) {
        Intrinsics.checkNotNullParameter(rem, "$this$rem");
        BigInteger bigIntegerRemainder = rem.remainder(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerRemainder, "this.remainder(other)");
        return bigIntegerRemainder;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(BigInteger unaryMinus) {
        Intrinsics.checkNotNullParameter(unaryMinus, "$this$unaryMinus");
        BigInteger bigIntegerNegate = unaryMinus.negate();
        Intrinsics.checkNotNullExpressionValue(bigIntegerNegate, "this.negate()");
        return bigIntegerNegate;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inc(BigInteger inc) {
        Intrinsics.checkNotNullParameter(inc, "$this$inc");
        BigInteger bigIntegerAdd = inc.add(BigInteger.ONE);
        Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(BigInteger.ONE)");
        return bigIntegerAdd;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger dec(BigInteger dec) {
        Intrinsics.checkNotNullParameter(dec, "$this$dec");
        BigInteger bigIntegerSubtract = dec.subtract(BigInteger.ONE);
        Intrinsics.checkNotNullExpressionValue(bigIntegerSubtract, "this.subtract(BigInteger.ONE)");
        return bigIntegerSubtract;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inv(BigInteger $this$inv) {
        BigInteger bigIntegerNot = $this$inv.not();
        Intrinsics.checkNotNullExpressionValue(bigIntegerNot, "this.not()");
        return bigIntegerNot;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger and(BigInteger $this$and, BigInteger other) {
        BigInteger bigIntegerAnd = $this$and.and(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerAnd, "this.and(other)");
        return bigIntegerAnd;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger or(BigInteger $this$or, BigInteger other) {
        BigInteger bigIntegerOr = $this$or.or(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerOr, "this.or(other)");
        return bigIntegerOr;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger xor(BigInteger $this$xor, BigInteger other) {
        BigInteger bigIntegerXor = $this$xor.xor(other);
        Intrinsics.checkNotNullExpressionValue(bigIntegerXor, "this.xor(other)");
        return bigIntegerXor;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shl(BigInteger $this$shl, int n) {
        BigInteger bigIntegerShiftLeft = $this$shl.shiftLeft(n);
        Intrinsics.checkNotNullExpressionValue(bigIntegerShiftLeft, "this.shiftLeft(n)");
        return bigIntegerShiftLeft;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shr(BigInteger $this$shr, int n) {
        BigInteger bigIntegerShiftRight = $this$shr.shiftRight(n);
        Intrinsics.checkNotNullExpressionValue(bigIntegerShiftRight, "this.shiftRight(n)");
        return bigIntegerShiftRight;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(int $this$toBigInteger) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf($this$toBigInteger);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        return bigIntegerValueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(long $this$toBigInteger) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf($this$toBigInteger);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this)");
        return bigIntegerValueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(BigInteger $this$toBigDecimal) {
        return new BigDecimal($this$toBigDecimal);
    }

    static /* synthetic */ BigDecimal toBigDecimal$default(BigInteger $this$toBigDecimal, int scale, MathContext mathContext, int i, Object obj) {
        if ((i & 1) != 0) {
            scale = 0;
        }
        if ((i & 2) != 0) {
            MathContext mathContext2 = MathContext.UNLIMITED;
            Intrinsics.checkNotNullExpressionValue(mathContext2, "MathContext.UNLIMITED");
            mathContext = mathContext2;
        }
        return new BigDecimal($this$toBigDecimal, scale, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(BigInteger $this$toBigDecimal, int scale, MathContext mathContext) {
        return new BigDecimal($this$toBigDecimal, scale, mathContext);
    }
}
