package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

@Beta
@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/math/Stats.class */
public final class Stats implements Serializable {
    private final long count;
    private final double mean;
    private final double sumOfSquaresOfDeltas;
    private final double min;
    private final double max;
    static final int BYTES = 40;
    private static final long serialVersionUID = 0;

    Stats(long count, double mean, double sumOfSquaresOfDeltas, double min, double max) {
        this.count = count;
        this.mean = mean;
        this.sumOfSquaresOfDeltas = sumOfSquaresOfDeltas;
        this.min = min;
        this.max = max;
    }

    public static Stats of(Iterable<? extends Number> values) {
        StatsAccumulator accumulator = new StatsAccumulator();
        accumulator.addAll(values);
        return accumulator.snapshot();
    }

    public static Stats of(Iterator<? extends Number> values) {
        StatsAccumulator accumulator = new StatsAccumulator();
        accumulator.addAll(values);
        return accumulator.snapshot();
    }

    public static Stats of(double... values) {
        StatsAccumulator acummulator = new StatsAccumulator();
        acummulator.addAll(values);
        return acummulator.snapshot();
    }

    public static Stats of(int... values) {
        StatsAccumulator acummulator = new StatsAccumulator();
        acummulator.addAll(values);
        return acummulator.snapshot();
    }

    public static Stats of(long... values) {
        StatsAccumulator acummulator = new StatsAccumulator();
        acummulator.addAll(values);
        return acummulator.snapshot();
    }

    public long count() {
        return this.count;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public double sum() {
        return this.mean * this.count;
    }

    public double populationVariance() {
        Preconditions.checkState(this.count > 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / count();
    }

    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1);
    }

    public double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Stats other = (Stats) obj;
        return this.count == other.count && Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(other.mean) && Double.doubleToLongBits(this.sumOfSquaresOfDeltas) == Double.doubleToLongBits(other.sumOfSquaresOfDeltas) && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(other.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(other.max);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.count), Double.valueOf(this.mean), Double.valueOf(this.sumOfSquaresOfDeltas), Double.valueOf(this.min), Double.valueOf(this.max));
    }

    public String toString() {
        if (count() > 0) {
            return MoreObjects.toStringHelper(this).add("count", this.count).add("mean", this.mean).add("populationStandardDeviation", populationStandardDeviation()).add("min", this.min).add("max", this.max).toString();
        }
        return MoreObjects.toStringHelper(this).add("count", this.count).toString();
    }

    double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public static double meanOf(Iterable<? extends Number> values) {
        return meanOf(values.iterator());
    }

    public static double meanOf(Iterator<? extends Number> values) {
        Preconditions.checkArgument(values.hasNext());
        long count = 1;
        double dDoubleValue = values.next().doubleValue();
        while (true) {
            double mean = dDoubleValue;
            if (values.hasNext()) {
                double value = values.next().doubleValue();
                count++;
                if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                    dDoubleValue = mean + ((value - mean) / count);
                } else {
                    dDoubleValue = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
                }
            } else {
                return mean;
            }
        }
    }

    public static double meanOf(double... values) {
        double dCalculateNewMeanNonFinite;
        Preconditions.checkArgument(values.length > 0);
        double mean = values[0];
        for (int index = 1; index < values.length; index++) {
            double value = values[index];
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                dCalculateNewMeanNonFinite = mean + ((value - mean) / (index + 1));
            } else {
                dCalculateNewMeanNonFinite = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
            }
            mean = dCalculateNewMeanNonFinite;
        }
        return mean;
    }

    public static double meanOf(int... values) {
        double dCalculateNewMeanNonFinite;
        Preconditions.checkArgument(values.length > 0);
        double mean = values[0];
        for (int index = 1; index < values.length; index++) {
            double value = values[index];
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                dCalculateNewMeanNonFinite = mean + ((value - mean) / (index + 1));
            } else {
                dCalculateNewMeanNonFinite = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
            }
            mean = dCalculateNewMeanNonFinite;
        }
        return mean;
    }

    public static double meanOf(long... values) {
        double dCalculateNewMeanNonFinite;
        Preconditions.checkArgument(values.length > 0);
        double mean = values[0];
        for (int index = 1; index < values.length; index++) {
            double value = values[index];
            if (Doubles.isFinite(value) && Doubles.isFinite(mean)) {
                dCalculateNewMeanNonFinite = mean + ((value - mean) / (index + 1));
            } else {
                dCalculateNewMeanNonFinite = StatsAccumulator.calculateNewMeanNonFinite(mean, value);
            }
            mean = dCalculateNewMeanNonFinite;
        }
        return mean;
    }

    public byte[] toByteArray() {
        ByteBuffer buff = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
        writeTo(buff);
        return buff.array();
    }

    void writeTo(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        Preconditions.checkArgument(buffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, buffer.remaining());
        buffer.putLong(this.count).putDouble(this.mean).putDouble(this.sumOfSquaresOfDeltas).putDouble(this.min).putDouble(this.max);
    }

    public static Stats fromByteArray(byte[] byteArray) {
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkArgument(byteArray.length == 40, "Expected Stats.BYTES = %s remaining , got %s", 40, byteArray.length);
        return readFrom(ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN));
    }

    static Stats readFrom(ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer);
        Preconditions.checkArgument(buffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, buffer.remaining());
        return new Stats(buffer.getLong(), buffer.getDouble(), buffer.getDouble(), buffer.getDouble(), buffer.getDouble());
    }
}
