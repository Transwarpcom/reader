package com.mongodb.client.model;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/CollationCaseFirst.class */
public enum CollationCaseFirst {
    UPPER("upper"),
    LOWER("lower"),
    OFF("off");

    private final String value;

    CollationCaseFirst(String caseFirst) {
        this.value = caseFirst;
    }

    public String getValue() {
        return this.value;
    }

    public static CollationCaseFirst fromString(String collationCaseFirst) {
        if (collationCaseFirst != null) {
            for (CollationCaseFirst caseFirst : values()) {
                if (collationCaseFirst.equals(caseFirst.value)) {
                    return caseFirst;
                }
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is not a valid collationCaseFirst", collationCaseFirst));
    }
}
