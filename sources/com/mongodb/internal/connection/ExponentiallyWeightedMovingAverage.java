package com.mongodb.internal.connection;

import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;

@NotThreadSafe
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ExponentiallyWeightedMovingAverage.class */
class ExponentiallyWeightedMovingAverage {
    private final double alpha;
    private long average = -1;

    ExponentiallyWeightedMovingAverage(double alpha) {
        Assertions.isTrueArgument("alpha >= 0.0 and <= 1.0", alpha >= 0.0d && alpha <= 1.0d);
        this.alpha = alpha;
    }

    void reset() {
        this.average = -1L;
    }

    long addSample(long sample) {
        if (this.average == -1) {
            this.average = sample;
        } else {
            this.average = (long) ((this.alpha * sample) + ((1.0d - this.alpha) * this.average));
        }
        return this.average;
    }

    long getAverage() {
        if (this.average == -1) {
            return 0L;
        }
        return this.average;
    }
}
