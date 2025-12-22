package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/math/LinearTransformation.class */
public abstract class LinearTransformation {
    public abstract boolean isVertical();

    public abstract boolean isHorizontal();

    public abstract double slope();

    public abstract double transform(double d);

    public abstract LinearTransformation inverse();

    public static LinearTransformationBuilder mapping(double x1, double y1) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x1) && DoubleUtils.isFinite(y1));
        return new LinearTransformationBuilder(x1, y1);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/math/LinearTransformation$LinearTransformationBuilder.class */
    public static final class LinearTransformationBuilder {
        private final double x1;
        private final double y1;

        private LinearTransformationBuilder(double x1, double y1) {
            this.x1 = x1;
            this.y1 = y1;
        }

        public LinearTransformation and(double x2, double y2) {
            Preconditions.checkArgument(DoubleUtils.isFinite(x2) && DoubleUtils.isFinite(y2));
            if (x2 == this.x1) {
                Preconditions.checkArgument(y2 != this.y1);
                return new VerticalLinearTransformation(this.x1);
            }
            return withSlope((y2 - this.y1) / (x2 - this.x1));
        }

        public LinearTransformation withSlope(double slope) {
            Preconditions.checkArgument(!Double.isNaN(slope));
            if (DoubleUtils.isFinite(slope)) {
                double yIntercept = this.y1 - (this.x1 * slope);
                return new RegularLinearTransformation(slope, yIntercept);
            }
            return new VerticalLinearTransformation(this.x1);
        }
    }

    public static LinearTransformation vertical(double x) {
        Preconditions.checkArgument(DoubleUtils.isFinite(x));
        return new VerticalLinearTransformation(x);
    }

    public static LinearTransformation horizontal(double y) {
        Preconditions.checkArgument(DoubleUtils.isFinite(y));
        return new RegularLinearTransformation(0.0d, y);
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.INSTANCE;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/math/LinearTransformation$RegularLinearTransformation.class */
    private static final class RegularLinearTransformation extends LinearTransformation {
        final double slope;
        final double yIntercept;

        @LazyInit
        LinearTransformation inverse;

        RegularLinearTransformation(double slope, double yIntercept) {
            this.slope = slope;
            this.yIntercept = yIntercept;
            this.inverse = null;
        }

        RegularLinearTransformation(double slope, double yIntercept, LinearTransformation inverse) {
            this.slope = slope;
            this.yIntercept = yIntercept;
            this.inverse = inverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.slope == 0.0d;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.slope;
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            return (x * this.slope) + this.yIntercept;
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            if (result != null) {
                return result;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.slope), Double.valueOf(this.yIntercept));
        }

        private LinearTransformation createInverse() {
            if (this.slope != 0.0d) {
                return new RegularLinearTransformation(1.0d / this.slope, ((-1.0d) * this.yIntercept) / this.slope, this);
            }
            return new VerticalLinearTransformation(this.yIntercept, this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/math/LinearTransformation$VerticalLinearTransformation.class */
    private static final class VerticalLinearTransformation extends LinearTransformation {
        final double x;

        @LazyInit
        LinearTransformation inverse;

        VerticalLinearTransformation(double x) {
            this.x = x;
            this.inverse = null;
        }

        VerticalLinearTransformation(double x, LinearTransformation inverse) {
            this.x = x;
            this.inverse = inverse;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            throw new IllegalStateException();
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation result = this.inverse;
            if (result != null) {
                return result;
            }
            LinearTransformation linearTransformationCreateInverse = createInverse();
            this.inverse = linearTransformationCreateInverse;
            return linearTransformationCreateInverse;
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.x));
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0d, this.x, this);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/math/LinearTransformation$NaNLinearTransformation.class */
    private static final class NaNLinearTransformation extends LinearTransformation {
        static final NaNLinearTransformation INSTANCE = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double x) {
            return Double.NaN;
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }

        public String toString() {
            return "NaN";
        }
    }
}
