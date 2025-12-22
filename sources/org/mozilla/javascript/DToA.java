package org.mozilla.javascript;

import java.math.BigInteger;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/DToA.class */
class DToA {
    static final int DTOSTR_STANDARD = 0;
    static final int DTOSTR_STANDARD_EXPONENTIAL = 1;
    static final int DTOSTR_FIXED = 2;
    static final int DTOSTR_EXPONENTIAL = 3;
    static final int DTOSTR_PRECISION = 4;
    private static final int Frac_mask = 1048575;
    private static final int Exp_shift = 20;
    private static final int Exp_msk1 = 1048576;
    private static final long Frac_maskL = 4503599627370495L;
    private static final int Exp_shiftL = 52;
    private static final long Exp_msk1L = 4503599627370496L;
    private static final int Bias = 1023;
    private static final int P = 53;
    private static final int Exp_shift1 = 20;
    private static final int Exp_mask = 2146435072;
    private static final int Exp_mask_shifted = 2047;
    private static final int Bndry_mask = 1048575;
    private static final int Log2P = 1;
    private static final int Sign_bit = Integer.MIN_VALUE;
    private static final int Exp_11 = 1072693248;
    private static final int Ten_pmax = 22;
    private static final int Quick_max = 14;
    private static final int Bletch = 16;
    private static final int Frac_mask1 = 1048575;
    private static final int Int_max = 14;
    private static final int n_bigtens = 5;
    private static final double[] tens = {1.0d, 10.0d, 100.0d, 1000.0d, 10000.0d, 100000.0d, 1000000.0d, 1.0E7d, 1.0E8d, 1.0E9d, 1.0E10d, 1.0E11d, 1.0E12d, 1.0E13d, 1.0E14d, 1.0E15d, 1.0E16d, 1.0E17d, 1.0E18d, 1.0E19d, 1.0E20d, 1.0E21d, 1.0E22d};
    private static final double[] bigtens = {1.0E16d, 1.0E32d, 1.0E64d, 1.0E128d, 1.0E256d};
    private static final int[] dtoaModes = {0, 0, 3, 2, 2};

    DToA() {
    }

    private static char BASEDIGIT(int digit) {
        return (char) (digit >= 10 ? 87 + digit : 48 + digit);
    }

    private static int lo0bits(int y) {
        int x = y;
        if ((x & 7) != 0) {
            if ((x & 1) != 0) {
                return 0;
            }
            if ((x & 2) != 0) {
                return 1;
            }
            return 2;
        }
        int k = 0;
        if ((x & 65535) == 0) {
            k = 16;
            x >>>= 16;
        }
        if ((x & 255) == 0) {
            k += 8;
            x >>>= 8;
        }
        if ((x & 15) == 0) {
            k += 4;
            x >>>= 4;
        }
        if ((x & 3) == 0) {
            k += 2;
            x >>>= 2;
        }
        if ((x & 1) == 0) {
            k++;
            if (((x >>> 1) & 1) == 0) {
                return 32;
            }
        }
        return k;
    }

    private static int hi0bits(int x) {
        int k = 0;
        if ((x & (-65536)) == 0) {
            k = 16;
            x <<= 16;
        }
        if ((x & (-16777216)) == 0) {
            k += 8;
            x <<= 8;
        }
        if ((x & (-268435456)) == 0) {
            k += 4;
            x <<= 4;
        }
        if ((x & (-1073741824)) == 0) {
            k += 2;
            x <<= 2;
        }
        if ((x & Integer.MIN_VALUE) == 0) {
            k++;
            if ((x & 1073741824) == 0) {
                return 32;
            }
        }
        return k;
    }

    private static void stuffBits(byte[] bits, int offset, int val) {
        bits[offset] = (byte) (val >> 24);
        bits[offset + 1] = (byte) (val >> 16);
        bits[offset + 2] = (byte) (val >> 8);
        bits[offset + 3] = (byte) val;
    }

    private static BigInteger d2b(double d, int[] e, int[] bits) {
        byte[] dbl_bits;
        int k;
        int i;
        long dBits = Double.doubleToLongBits(d);
        int d0 = (int) (dBits >>> 32);
        int d1 = (int) dBits;
        int z = d0 & 1048575;
        int de = (d0 & Integer.MAX_VALUE) >>> 20;
        if (de != 0) {
            z |= Exp_msk1;
        }
        if (d1 != 0) {
            dbl_bits = new byte[8];
            k = lo0bits(d1);
            int y = d1 >>> k;
            if (k != 0) {
                stuffBits(dbl_bits, 4, y | (z << (32 - k)));
                z >>= k;
            } else {
                stuffBits(dbl_bits, 4, y);
            }
            stuffBits(dbl_bits, 0, z);
            i = z != 0 ? 2 : 1;
        } else {
            dbl_bits = new byte[4];
            int k2 = lo0bits(z);
            z >>>= k2;
            stuffBits(dbl_bits, 0, z);
            k = k2 + 32;
            i = 1;
        }
        if (de != 0) {
            e[0] = ((de - Bias) - 52) + k;
            bits[0] = 53 - k;
        } else {
            e[0] = ((de - Bias) - 52) + 1 + k;
            bits[0] = (32 * i) - hi0bits(z);
        }
        return new BigInteger(dbl_bits);
    }

    static String JS_dtobasestr(int base, double d) {
        boolean negative;
        long mantissa;
        String intDigits;
        if (2 > base || base > 36) {
            throw new IllegalArgumentException("Bad base: " + base);
        }
        if (Double.isNaN(d)) {
            return "NaN";
        }
        if (Double.isInfinite(d)) {
            return d > 0.0d ? "Infinity" : "-Infinity";
        }
        if (d == 0.0d) {
            return "0";
        }
        if (d >= 0.0d) {
            negative = false;
        } else {
            negative = true;
            d = -d;
        }
        double dfloor = Math.floor(d);
        long lfloor = (long) dfloor;
        if (lfloor == dfloor) {
            intDigits = Long.toString(negative ? -lfloor : lfloor, base);
        } else {
            long floorBits = Double.doubleToLongBits(dfloor);
            int exp = ((int) (floorBits >> 52)) & Exp_mask_shifted;
            if (exp == 0) {
                mantissa = (floorBits & Frac_maskL) << 1;
            } else {
                mantissa = (floorBits & Frac_maskL) | Exp_msk1L;
            }
            if (negative) {
                mantissa = -mantissa;
            }
            int exp2 = exp - 1075;
            BigInteger x = BigInteger.valueOf(mantissa);
            if (exp2 > 0) {
                x = x.shiftLeft(exp2);
            } else if (exp2 < 0) {
                x = x.shiftRight(-exp2);
            }
            intDigits = x.toString(base);
        }
        if (d == dfloor) {
            return intDigits;
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(intDigits).append('.');
        double df = d - dfloor;
        long dBits = Double.doubleToLongBits(d);
        int word0 = (int) (dBits >> 32);
        int word1 = (int) dBits;
        int[] e = new int[1];
        int[] bbits = new int[1];
        BigInteger b = d2b(df, e, bbits);
        int s2 = -((word0 >>> 20) & Exp_mask_shifted);
        if (s2 == 0) {
            s2 = -1;
        }
        int s22 = s2 + 1076;
        BigInteger mlo = BigInteger.valueOf(1L);
        BigInteger mhi = mlo;
        if (word1 == 0 && (word0 & 1048575) == 0 && (word0 & 2145386496) != 0) {
            s22++;
            mhi = BigInteger.valueOf(2L);
        }
        BigInteger b2 = b.shiftLeft(e[0] + s22);
        BigInteger s = BigInteger.valueOf(1L).shiftLeft(s22);
        BigInteger bigBase = BigInteger.valueOf(base);
        boolean done = false;
        do {
            BigInteger[] divResult = b2.multiply(bigBase).divideAndRemainder(s);
            b2 = divResult[1];
            int digit = (char) divResult[0].intValue();
            if (mlo == mhi) {
                BigInteger bigIntegerMultiply = mlo.multiply(bigBase);
                mhi = bigIntegerMultiply;
                mlo = bigIntegerMultiply;
            } else {
                mlo = mlo.multiply(bigBase);
                mhi = mhi.multiply(bigBase);
            }
            int j = b2.compareTo(mlo);
            BigInteger delta = s.subtract(mhi);
            int j1 = delta.signum() <= 0 ? 1 : b2.compareTo(delta);
            if (j1 == 0 && (word1 & 1) == 0) {
                if (j > 0) {
                    digit++;
                }
                done = true;
            } else if (j < 0 || (j == 0 && (word1 & 1) == 0)) {
                if (j1 > 0) {
                    b2 = b2.shiftLeft(1);
                    if (b2.compareTo(s) > 0) {
                        digit++;
                    }
                }
                done = true;
            } else if (j1 > 0) {
                digit++;
                done = true;
            }
            buffer.append(BASEDIGIT(digit));
        } while (!done);
        return buffer.toString();
    }

    static int word0(double d) {
        long dBits = Double.doubleToLongBits(d);
        return (int) (dBits >> 32);
    }

    static double setWord0(double d, int i) {
        long dBits = Double.doubleToLongBits(d);
        return Double.longBitsToDouble((i << 32) | (dBits & 4294967295L));
    }

    static int word1(double d) {
        long dBits = Double.doubleToLongBits(d);
        return (int) dBits;
    }

    static BigInteger pow5mult(BigInteger b, int k) {
        return b.multiply(BigInteger.valueOf(5L).pow(k));
    }

    static boolean roundOff(StringBuilder buf) {
        int i = buf.length();
        while (i != 0) {
            i--;
            char c = buf.charAt(i);
            if (c != '9') {
                buf.setCharAt(i, (char) (c + 1));
                buf.setLength(i + 1);
                return false;
            }
        }
        buf.setLength(0);
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:344:0x0933, code lost:
    
        if (r22 <= 0) goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x0936, code lost:
    
        r0 = r34.shiftLeft(1).compareTo(r39);
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x0949, code lost:
    
        if (r0 > 0) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x094e, code lost:
    
        if (r0 != 0) goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0956, code lost:
    
        if ((r29 & 1) == 1) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x095a, code lost:
    
        if (r10 == false) goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x095d, code lost:
    
        r29 = (char) (r29 + 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x0968, code lost:
    
        if (r29 != '9') goto L360;
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x096b, code lost:
    
        r13.append('9');
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x0978, code lost:
    
        if (roundOff(r13) == false) goto L358;
     */
    /* JADX WARN: Code restructure failed: missing block: B:357:0x097b, code lost:
    
        r23 = r23 + 1;
        r13.append('1');
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x098a, code lost:
    
        return r23 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:360:0x098b, code lost:
    
        r13.append(r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x0997, code lost:
    
        return r23 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0223  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static int JS_dtoa(double r7, int r9, boolean r10, int r11, boolean[] r12, java.lang.StringBuilder r13) {
        /*
            Method dump skipped, instructions count: 2746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.DToA.JS_dtoa(double, int, boolean, int, boolean[], java.lang.StringBuilder):int");
    }

    private static void stripTrailingZeroes(StringBuilder buf) {
        int bl = buf.length();
        do {
            int i = bl;
            bl--;
            if (i <= 0) {
                break;
            }
        } while (buf.charAt(bl) == '0');
        buf.setLength(bl + 1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static void JS_dtostr(StringBuilder buffer, int mode, int precision, double d) {
        boolean[] sign = new boolean[1];
        if (mode == 2 && (d >= 1.0E21d || d <= -1.0E21d)) {
            mode = 0;
        }
        int decPt = JS_dtoa(d, dtoaModes[mode], mode >= 2, precision, sign, buffer);
        int nDigits = buffer.length();
        if (decPt != 9999) {
            boolean exponentialNotation = false;
            int minNDigits = 0;
            switch (mode) {
                case 0:
                    if (decPt < -5 || decPt > 21) {
                        exponentialNotation = true;
                        break;
                    } else {
                        minNDigits = decPt;
                        break;
                    }
                    break;
                case 1:
                    exponentialNotation = true;
                    break;
                case 2:
                    if (precision >= 0) {
                        minNDigits = decPt + precision;
                        break;
                    } else {
                        minNDigits = decPt;
                        break;
                    }
                case 3:
                    minNDigits = precision;
                    exponentialNotation = true;
                    break;
                case 4:
                    minNDigits = precision;
                    if (decPt < -5 || decPt > precision) {
                        exponentialNotation = true;
                        break;
                    }
                    break;
            }
            if (nDigits < minNDigits) {
                int p = minNDigits;
                nDigits = minNDigits;
                do {
                    buffer.append('0');
                } while (buffer.length() != p);
            }
            if (exponentialNotation) {
                if (nDigits != 1) {
                    buffer.insert(1, '.');
                }
                buffer.append('e');
                if (decPt - 1 >= 0) {
                    buffer.append('+');
                }
                buffer.append(decPt - 1);
            } else if (decPt != nDigits) {
                if (decPt > 0) {
                    buffer.insert(decPt, '.');
                } else {
                    for (int i = 0; i < 1 - decPt; i++) {
                        buffer.insert(0, '0');
                    }
                    buffer.insert(1, '.');
                }
            }
        }
        if (sign[0]) {
            if (word0(d) != Integer.MIN_VALUE || word1(d) != 0) {
                if ((word0(d) & Exp_mask) != Exp_mask || (word1(d) == 0 && (word0(d) & 1048575) == 0)) {
                    buffer.insert(0, '-');
                }
            }
        }
    }
}
