package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/EstimatedDocumentCountOptions.class */
public class EstimatedDocumentCountOptions {
    private long maxTimeMS;

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public EstimatedDocumentCountOptions maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public String toString() {
        return "EstimatedCountOptions{, maxTimeMS=" + this.maxTimeMS + '}';
    }
}
