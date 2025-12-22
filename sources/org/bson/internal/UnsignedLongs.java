package org.bson.internal;

import java.math.BigInteger;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/internal/UnsignedLongs.class */
public final class UnsignedLongs {
    private static final long MAX_VALUE = -1;
    private static final long[] MAX_VALUE_DIVS = new long[37];
    private static final int[] MAX_VALUE_MODS = new int[37];
    private static final int[] MAX_SAFE_DIGITS = new int[37];

    public static int compare(long first, long second) {
        return compareLongs(first - Long.MIN_VALUE, second - Long.MIN_VALUE);
    }

    public static String toString(long value) {
        if (value >= 0) {
            return Long.toString(value);
        }
        long quotient = (value >>> 1) / 5;
        long remainder = value - (quotient * 10);
        return Long.toString(quotient) + remainder;
    }

    public static long parse(String string) {
        if (string.length() == 0) {
            throw new NumberFormatException("empty string");
        }
        int maxSafePos = MAX_SAFE_DIGITS[10] - 1;
        long value = 0;
        for (int pos = 0; pos < string.length(); pos++) {
            int digit = Character.digit(string.charAt(pos), 10);
            if (digit == -1) {
                throw new NumberFormatException(string);
            }
            if (pos <= maxSafePos || !overflowInParse(value, digit, 10)) {
                value = (value * 10) + digit;
            } else {
                throw new NumberFormatException("Too large for unsigned long: " + string);
            }
        }
        return value;
    }

    private static boolean overflowInParse(long current, int digit, int radix) {
        if (current < 0) {
            return true;
        }
        if (current < MAX_VALUE_DIVS[radix]) {
            return false;
        }
        return current > MAX_VALUE_DIVS[radix] || digit > MAX_VALUE_MODS[radix];
    }

    private static int compareLongs(long x, long y) {
        if (x < y) {
            return -1;
        }
        return x == y ? 0 : 1;
    }

    private static long divide(long dividend, long divisor) {
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return 0L;
            }
            return 1L;
        }
        if (dividend >= 0) {
            return dividend / divisor;
        }
        long quotient = ((dividend >>> 1) / divisor) << 1;
        long rem = dividend - (quotient * divisor);
        return quotient + (compare(rem, divisor) >= 0 ? 1 : 0);
    }

    private static long remainder(long dividend, long divisor) {
        if (divisor < 0) {
            if (compare(dividend, divisor) < 0) {
                return dividend;
            }
            return dividend - divisor;
        }
        if (dividend >= 0) {
            return dividend % divisor;
        }
        long quotient = ((dividend >>> 1) / divisor) << 1;
        long rem = dividend - (quotient * divisor);
        return rem - (compare(rem, divisor) >= 0 ? divisor : 0L);
    }

    static {
        BigInteger overflow = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            MAX_VALUE_DIVS[i] = divide(-1L, i);
            MAX_VALUE_MODS[i] = (int) remainder(-1L, i);
            MAX_SAFE_DIGITS[i] = overflow.toString(i).length() - 1;
        }
    }

    private UnsignedLongs() {
    }
}
