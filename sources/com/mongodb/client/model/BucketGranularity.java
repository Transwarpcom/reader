package com.mongodb.client.model;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/BucketGranularity.class */
public enum BucketGranularity {
    R5,
    R10,
    R20,
    R40,
    R80,
    SERIES_125("1-2-5"),
    E6,
    E12,
    E24,
    E48,
    E96,
    E192,
    POWERSOF2;

    private final String value;

    BucketGranularity() {
        this.value = name();
    }

    BucketGranularity(String name) {
        this.value = name;
    }

    public static BucketGranularity fromString(String value) {
        for (BucketGranularity granularity : values()) {
            if (granularity.getValue().equals(value)) {
                return granularity;
            }
        }
        throw new IllegalArgumentException("No Granularity exists for the value " + value);
    }

    public String getValue() {
        return this.value;
    }
}
