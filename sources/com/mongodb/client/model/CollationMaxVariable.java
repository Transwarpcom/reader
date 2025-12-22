package com.mongodb.client.model;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/CollationMaxVariable.class */
public enum CollationMaxVariable {
    PUNCT("punct"),
    SPACE("space");

    private final String value;

    CollationMaxVariable(String caseFirst) {
        this.value = caseFirst;
    }

    public String getValue() {
        return this.value;
    }

    public static CollationMaxVariable fromString(String collationMaxVariable) {
        if (collationMaxVariable != null) {
            for (CollationMaxVariable maxVariable : values()) {
                if (collationMaxVariable.equals(maxVariable.value)) {
                    return maxVariable;
                }
            }
        }
        throw new IllegalArgumentException(String.format("'%s' is not a valid collationMaxVariable", collationMaxVariable));
    }
}
