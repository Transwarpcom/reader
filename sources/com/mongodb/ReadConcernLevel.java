package com.mongodb;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ReadConcernLevel.class */
public enum ReadConcernLevel {
    LOCAL("local"),
    MAJORITY("majority"),
    LINEARIZABLE("linearizable"),
    SNAPSHOT("snapshot");

    private final String value;

    ReadConcernLevel(String readConcernLevel) {
        this.value = readConcernLevel;
    }

    public String getValue() {
        return this.value;
    }

    public static ReadConcernLevel fromString(String readConcernLevel) {
        Assertions.notNull("readConcernLevel", readConcernLevel);
        for (ReadConcernLevel level : values()) {
            if (readConcernLevel.equalsIgnoreCase(level.value)) {
                return level;
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is not a valid readConcernLevel", readConcernLevel));
    }
}
