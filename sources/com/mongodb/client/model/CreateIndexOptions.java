package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/CreateIndexOptions.class */
public class CreateIndexOptions {
    private long maxTimeMS;

    public long getMaxTime(TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        return timeUnit.convert(this.maxTimeMS, TimeUnit.MILLISECONDS);
    }

    public CreateIndexOptions maxTime(long maxTime, TimeUnit timeUnit) {
        Assertions.notNull("timeUnit", timeUnit);
        this.maxTimeMS = TimeUnit.MILLISECONDS.convert(maxTime, timeUnit);
        return this;
    }

    public String toString() {
        return "CreateIndexOptions{maxTimeMS=" + this.maxTimeMS + '}';
    }
}
