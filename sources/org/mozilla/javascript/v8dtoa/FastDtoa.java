package org.mozilla.javascript.v8dtoa;

/* loaded from: reader.jar:BOOT-INF/lib/rhino-1.7.13-1.jar:org/mozilla/javascript/v8dtoa/FastDtoa.class */
public class FastDtoa {
    static final int kFastDtoaMaximalLength = 17;
    static final int minimal_target_exponent = -60;
    static final int maximal_target_exponent = -32;
    static final int kTen4 = 10000;
    static final int kTen5 = 100000;
    static final int kTen6 = 1000000;
    static final int kTen7 = 10000000;
    static final int kTen8 = 100000000;
    static final int kTen9 = 1000000000;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !FastDtoa.class.desiredAssertionStatus();
    }

    static boolean roundWeed(FastDtoaBuilder buffer, long distance_too_high_w, long unsafe_interval, long rest, long ten_kappa, long unit) {
        long small_distance = distance_too_high_w - unit;
        long big_distance = distance_too_high_w + unit;
        while (rest < small_distance && unsafe_interval - rest >= ten_kappa && (rest + ten_kappa < small_distance || small_distance - rest >= (rest + ten_kappa) - small_distance)) {
            buffer.decreaseLast();
            rest += ten_kappa;
        }
        return (rest >= big_distance || unsafe_interval - rest < ten_kappa || (rest + ten_kappa >= big_distance && big_distance - rest <= (rest + ten_kappa) - big_distance)) && 2 * unit <= rest && rest <= unsafe_interval - (4 * unit);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static long biggestPowerTen(int r7, int r8) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.v8dtoa.FastDtoa.biggestPowerTen(int, int):long");
    }

    private static boolean uint64_lte(long a, long b) {
        if (a != b) {
            if (!(((a < b) ^ (a < 0)) ^ (b < 0))) {
                return false;
            }
        }
        return true;
    }

    static boolean digitGen(DiyFp low, DiyFp w, DiyFp high, FastDtoaBuilder buffer, int mk) {
        if (!$assertionsDisabled && (low.e() != w.e() || w.e() != high.e())) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && !uint64_lte(low.f() + 1, high.f() - 1)) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && (minimal_target_exponent > w.e() || w.e() > maximal_target_exponent)) {
            throw new AssertionError();
        }
        long unit = 1;
        DiyFp too_low = new DiyFp(low.f() - 1, low.e());
        DiyFp too_high = new DiyFp(high.f() + 1, high.e());
        DiyFp unsafe_interval = DiyFp.minus(too_high, too_low);
        DiyFp one = new DiyFp(1 << (-w.e()), w.e());
        int integrals = (int) ((too_high.f() >>> (-one.e())) & 4294967295L);
        long fractionals = too_high.f() & (one.f() - 1);
        long result = biggestPowerTen(integrals, 64 - (-one.e()));
        int divider = (int) ((result >>> 32) & 4294967295L);
        int divider_exponent = (int) (result & 4294967295L);
        int kappa = divider_exponent + 1;
        while (kappa > 0) {
            int digit = integrals / divider;
            buffer.append((char) (48 + digit));
            integrals %= divider;
            kappa--;
            long rest = (integrals << (-one.e())) + fractionals;
            if (rest < unsafe_interval.f()) {
                buffer.point = (buffer.end - mk) + kappa;
                return roundWeed(buffer, DiyFp.minus(too_high, w).f(), unsafe_interval.f(), rest, divider << (-one.e()), 1L);
            }
            divider /= 10;
        }
        do {
            long fractionals2 = fractionals * 5;
            unit *= 5;
            unsafe_interval.setF(unsafe_interval.f() * 5);
            unsafe_interval.setE(unsafe_interval.e() + 1);
            one.setF(one.f() >>> 1);
            one.setE(one.e() + 1);
            int digit2 = (int) ((fractionals2 >>> (-one.e())) & 4294967295L);
            buffer.append((char) (48 + digit2));
            fractionals = fractionals2 & (one.f() - 1);
            kappa--;
        } while (fractionals >= unsafe_interval.f());
        buffer.point = (buffer.end - mk) + kappa;
        return roundWeed(buffer, DiyFp.minus(too_high, w).f() * unit, unsafe_interval.f(), fractionals, one.f(), unit);
    }

    static boolean grisu3(double v, FastDtoaBuilder buffer) {
        long bits = Double.doubleToLongBits(v);
        DiyFp w = DoubleHelper.asNormalizedDiyFp(bits);
        DiyFp boundary_minus = new DiyFp();
        DiyFp boundary_plus = new DiyFp();
        DoubleHelper.normalizedBoundaries(bits, boundary_minus, boundary_plus);
        if (!$assertionsDisabled && boundary_plus.e() != w.e()) {
            throw new AssertionError();
        }
        DiyFp ten_mk = new DiyFp();
        int mk = CachedPowers.getCachedPower(w.e() + 64, minimal_target_exponent, maximal_target_exponent, ten_mk);
        if (!$assertionsDisabled && (minimal_target_exponent > w.e() + ten_mk.e() + 64 || maximal_target_exponent < w.e() + ten_mk.e() + 64)) {
            throw new AssertionError();
        }
        DiyFp scaled_w = DiyFp.times(w, ten_mk);
        if (!$assertionsDisabled && scaled_w.e() != boundary_plus.e() + ten_mk.e() + 64) {
            throw new AssertionError();
        }
        DiyFp scaled_boundary_minus = DiyFp.times(boundary_minus, ten_mk);
        DiyFp scaled_boundary_plus = DiyFp.times(boundary_plus, ten_mk);
        return digitGen(scaled_boundary_minus, scaled_w, scaled_boundary_plus, buffer, mk);
    }

    public static boolean dtoa(double v, FastDtoaBuilder buffer) {
        if (!$assertionsDisabled && v <= 0.0d) {
            throw new AssertionError();
        }
        if (!$assertionsDisabled && Double.isNaN(v)) {
            throw new AssertionError();
        }
        if ($assertionsDisabled || !Double.isInfinite(v)) {
            return grisu3(v, buffer);
        }
        throw new AssertionError();
    }

    public static String numberToString(double v) {
        FastDtoaBuilder buffer = new FastDtoaBuilder();
        if (numberToString(v, buffer)) {
            return buffer.format();
        }
        return null;
    }

    public static boolean numberToString(double v, FastDtoaBuilder buffer) {
        buffer.reset();
        if (v < 0.0d) {
            buffer.append('-');
            v = -v;
        }
        return dtoa(v, buffer);
    }
}
