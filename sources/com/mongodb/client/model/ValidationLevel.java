package com.mongodb.client.model;

import com.mongodb.assertions.Assertions;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/ValidationLevel.class */
public enum ValidationLevel {
    OFF("off"),
    STRICT("strict"),
    MODERATE("moderate");

    private final String value;

    ValidationLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ValidationLevel fromString(String validationLevel) {
        Assertions.notNull("ValidationLevel", validationLevel);
        for (ValidationLevel action : values()) {
            if (validationLevel.equalsIgnoreCase(action.value)) {
                return action;
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is not a valid ValidationLevel", validationLevel));
    }
}
