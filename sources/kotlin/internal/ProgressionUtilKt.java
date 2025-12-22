package kotlin.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* compiled from: progressionUtil.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u0012\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a \u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a \u0010��\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0001\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0001\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002¨\u0006\u000b"}, d2 = {"differenceModulo", "", "a", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, OperatorName.CURVE_TO, "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/internal/ProgressionUtilKt.class */
public final class ProgressionUtilKt {
    private static final int mod(int a, int b) {
        int mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static final long mod(long a, long b) {
        long mod = a % b;
        return mod >= 0 ? mod : mod + b;
    }

    private static final int differenceModulo(int a, int b, int c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    private static final long differenceModulo(long a, long b, long c) {
        return mod(mod(a, c) - mod(b, c), c);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int start, int end, int step) {
        if (step > 0) {
            return start >= end ? end : end - differenceModulo(end, start, step);
        }
        if (step < 0) {
            return start <= end ? end : end + differenceModulo(start, end, -step);
        }
        throw new IllegalArgumentException("Step is zero.");
    }

    @PublishedApi
    public static final long getProgressionLastElement(long start, long end, long step) {
        if (step > 0) {
            return start >= end ? end : end - differenceModulo(end, start, step);
        }
        if (step < 0) {
            return start <= end ? end : end + differenceModulo(start, end, -step);
        }
        throw new IllegalArgumentException("Step is zero.");
    }
}
