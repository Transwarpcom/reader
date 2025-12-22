package org.bson.types;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/types/Decimal128.class */
public final class Decimal128 implements Serializable {
    private static final long serialVersionUID = 4570973266503637887L;
    private static final long SIGN_BIT_MASK = Long.MIN_VALUE;
    private static final int MIN_EXPONENT = -6176;
    private static final int MAX_EXPONENT = 6111;
    private static final int EXPONENT_OFFSET = 6176;
    private static final int MAX_BIT_LENGTH = 113;
    private final long high;
    private final long low;
    private static final BigInteger BIG_INT_TEN = new BigInteger("10");
    private static final BigInteger BIG_INT_ONE = new BigInteger(CustomBooleanEditor.VALUE_1);
    private static final BigInteger BIG_INT_ZERO = new BigInteger("0");
    private static final Set<String> NaN_STRINGS = new HashSet(Arrays.asList("nan"));
    private static final Set<String> NEGATIVE_NaN_STRINGS = new HashSet(Arrays.asList("-nan"));
    private static final Set<String> POSITIVE_INFINITY_STRINGS = new HashSet(Arrays.asList("inf", "+inf", "infinity", "+infinity"));
    private static final Set<String> NEGATIVE_INFINITY_STRINGS = new HashSet(Arrays.asList("-inf", "-infinity"));
    private static final long INFINITY_MASK = 8646911284551352320L;
    public static final Decimal128 POSITIVE_INFINITY = fromIEEE754BIDEncoding(INFINITY_MASK, 0);
    public static final Decimal128 NEGATIVE_INFINITY = fromIEEE754BIDEncoding(-576460752303423488L, 0);
    public static final Decimal128 NEGATIVE_NaN = fromIEEE754BIDEncoding(-288230376151711744L, 0);
    private static final long NaN_MASK = 8935141660703064064L;
    public static final Decimal128 NaN = fromIEEE754BIDEncoding(NaN_MASK, 0);
    public static final Decimal128 POSITIVE_ZERO = fromIEEE754BIDEncoding(3476778912330022912L, 0);
    public static final Decimal128 NEGATIVE_ZERO = fromIEEE754BIDEncoding(-5746593124524752896L, 0);

    public static Decimal128 parse(String value) {
        String lowerCasedValue = value.toLowerCase();
        if (NaN_STRINGS.contains(lowerCasedValue)) {
            return NaN;
        }
        if (NEGATIVE_NaN_STRINGS.contains(lowerCasedValue)) {
            return NEGATIVE_NaN;
        }
        if (POSITIVE_INFINITY_STRINGS.contains(lowerCasedValue)) {
            return POSITIVE_INFINITY;
        }
        if (NEGATIVE_INFINITY_STRINGS.contains(lowerCasedValue)) {
            return NEGATIVE_INFINITY;
        }
        return new Decimal128(new BigDecimal(value), value.charAt(0) == '-');
    }

    public static Decimal128 fromIEEE754BIDEncoding(long high, long low) {
        return new Decimal128(high, low);
    }

    public Decimal128(long value) {
        this(new BigDecimal(value, MathContext.DECIMAL128));
    }

    public Decimal128(BigDecimal value) {
        this(value, value.signum() == -1);
    }

    private Decimal128(long high, long low) {
        this.high = high;
        this.low = low;
    }

    private Decimal128(BigDecimal initialValue, boolean isNegative) {
        long localHigh = 0;
        long localLow = 0;
        BigDecimal value = clampAndRound(initialValue);
        long exponent = -value.scale();
        if (exponent < -6176 || exponent > 6111) {
            throw new AssertionError("Exponent is out of range for Decimal128 encoding: " + exponent);
        }
        if (value.unscaledValue().bitLength() > 113) {
            throw new AssertionError("Unscaled roundedValue is out of range for Decimal128 encoding:" + value.unscaledValue());
        }
        BigInteger significand = value.unscaledValue().abs();
        int bitLength = significand.bitLength();
        for (int i = 0; i < Math.min(64, bitLength); i++) {
            if (significand.testBit(i)) {
                localLow |= 1 << i;
            }
        }
        for (int i2 = 64; i2 < bitLength; i2++) {
            if (significand.testBit(i2)) {
                localHigh |= 1 << (i2 - 64);
            }
        }
        long biasedExponent = exponent + 6176;
        long localHigh2 = localHigh | (biasedExponent << 49);
        this.high = (value.signum() == -1 || isNegative) ? localHigh2 | Long.MIN_VALUE : localHigh2;
        this.low = localLow;
    }

    private BigDecimal clampAndRound(BigDecimal initialValue) {
        BigDecimal value;
        if ((-initialValue.scale()) > MAX_EXPONENT) {
            int diff = (-initialValue.scale()) - MAX_EXPONENT;
            if (initialValue.unscaledValue().equals(BIG_INT_ZERO)) {
                value = new BigDecimal(initialValue.unscaledValue(), -6111);
            } else {
                if (diff + initialValue.precision() > 34) {
                    throw new NumberFormatException("Exponent is out of range for Decimal128 encoding of " + initialValue);
                }
                BigInteger multiplier = BIG_INT_TEN.pow(diff);
                value = new BigDecimal(initialValue.unscaledValue().multiply(multiplier), initialValue.scale() + diff);
            }
        } else if ((-initialValue.scale()) < MIN_EXPONENT) {
            int diff2 = initialValue.scale() + MIN_EXPONENT;
            int undiscardedPrecision = ensureExactRounding(initialValue, diff2);
            BigInteger divisor = undiscardedPrecision == 0 ? BIG_INT_ONE : BIG_INT_TEN.pow(diff2);
            value = new BigDecimal(initialValue.unscaledValue().divide(divisor), initialValue.scale() - diff2);
        } else {
            value = initialValue.round(MathContext.DECIMAL128);
            int extraPrecision = initialValue.precision() - value.precision();
            if (extraPrecision > 0) {
                ensureExactRounding(initialValue, extraPrecision);
            }
        }
        return value;
    }

    private int ensureExactRounding(BigDecimal initialValue, int extraPrecision) {
        String significand = initialValue.unscaledValue().abs().toString();
        int undiscardedPrecision = Math.max(0, significand.length() - extraPrecision);
        for (int i = undiscardedPrecision; i < significand.length(); i++) {
            if (significand.charAt(i) != '0') {
                throw new NumberFormatException("Conversion to Decimal128 would require inexact rounding of " + initialValue);
            }
        }
        return undiscardedPrecision;
    }

    public long getHigh() {
        return this.high;
    }

    public long getLow() {
        return this.low;
    }

    public BigDecimal bigDecimalValue() {
        if (isNaN()) {
            throw new ArithmeticException("NaN can not be converted to a BigDecimal");
        }
        if (isInfinite()) {
            throw new ArithmeticException("Infinity can not be converted to a BigDecimal");
        }
        BigDecimal bigDecimal = bigDecimalValueNoNegativeZeroCheck();
        if (isNegative() && bigDecimal.signum() == 0) {
            throw new ArithmeticException("Negative zero can not be converted to a BigDecimal");
        }
        return bigDecimal;
    }

    private BigDecimal bigDecimalValueNoNegativeZeroCheck() {
        int scale = -getExponent();
        if (twoHighestCombinationBitsAreSet()) {
            return BigDecimal.valueOf(0L, scale);
        }
        return new BigDecimal(new BigInteger(isNegative() ? -1 : 1, getBytes()), scale);
    }

    private byte[] getBytes() {
        byte[] bytes = new byte[15];
        long mask = 255;
        for (int i = 14; i >= 7; i--) {
            bytes[i] = (byte) ((this.low & mask) >>> ((14 - i) << 3));
            mask <<= 8;
        }
        long mask2 = 255;
        for (int i2 = 6; i2 >= 1; i2--) {
            bytes[i2] = (byte) ((this.high & mask2) >>> ((6 - i2) << 3));
            mask2 <<= 8;
        }
        bytes[0] = (byte) ((this.high & 281474976710656L) >>> 48);
        return bytes;
    }

    int getExponent() {
        if (twoHighestCombinationBitsAreSet()) {
            return ((int) ((this.high & 2305807824841605120L) >>> 47)) - EXPONENT_OFFSET;
        }
        return ((int) ((this.high & 9223231299366420480L) >>> 49)) - EXPONENT_OFFSET;
    }

    private boolean twoHighestCombinationBitsAreSet() {
        return (this.high & 6917529027641081856L) == 6917529027641081856L;
    }

    public boolean isNegative() {
        return (this.high & Long.MIN_VALUE) == Long.MIN_VALUE;
    }

    public boolean isInfinite() {
        return (this.high & INFINITY_MASK) == INFINITY_MASK;
    }

    public boolean isFinite() {
        return !isInfinite();
    }

    public boolean isNaN() {
        return (this.high & NaN_MASK) == NaN_MASK;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Decimal128 that = (Decimal128) o;
        if (this.high != that.high || this.low != that.low) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = (int) (this.low ^ (this.low >>> 32));
        return (31 * result) + ((int) (this.high ^ (this.high >>> 32)));
    }

    public String toString() {
        if (isNaN()) {
            return "NaN";
        }
        if (isInfinite()) {
            if (isNegative()) {
                return "-Infinity";
            }
            return "Infinity";
        }
        return toStringWithBigDecimal();
    }

    private String toStringWithBigDecimal() {
        StringBuilder buffer = new StringBuilder();
        BigDecimal bigDecimal = bigDecimalValueNoNegativeZeroCheck();
        String significand = bigDecimal.unscaledValue().abs().toString();
        if (isNegative()) {
            buffer.append('-');
        }
        int exponent = -bigDecimal.scale();
        int adjustedExponent = exponent + (significand.length() - 1);
        if (exponent <= 0 && adjustedExponent >= -6) {
            if (exponent == 0) {
                buffer.append(significand);
            } else {
                int pad = (-exponent) - significand.length();
                if (pad >= 0) {
                    buffer.append('0');
                    buffer.append('.');
                    for (int i = 0; i < pad; i++) {
                        buffer.append('0');
                    }
                    buffer.append((CharSequence) significand, 0, significand.length());
                } else {
                    buffer.append((CharSequence) significand, 0, -pad);
                    buffer.append('.');
                    buffer.append((CharSequence) significand, -pad, (-pad) - exponent);
                }
            }
        } else {
            buffer.append(significand.charAt(0));
            if (significand.length() > 1) {
                buffer.append('.');
                buffer.append((CharSequence) significand, 1, significand.length());
            }
            buffer.append('E');
            if (adjustedExponent > 0) {
                buffer.append('+');
            }
            buffer.append(adjustedExponent);
        }
        return buffer.toString();
    }
}
