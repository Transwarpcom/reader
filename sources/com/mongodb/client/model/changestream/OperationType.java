package com.mongodb.client.model.changestream;

import ch.qos.logback.core.pattern.parser.Parser;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/changestream/OperationType.class */
public enum OperationType {
    INSERT("insert"),
    UPDATE("update"),
    REPLACE(Parser.REPLACE_CONVERTER_WORD),
    DELETE("delete"),
    INVALIDATE("invalidate"),
    DROP("drop"),
    DROP_DATABASE("dropDatabase"),
    RENAME("rename"),
    OTHER("other");

    private final String value;

    OperationType(String operationTypeName) {
        this.value = operationTypeName;
    }

    public String getValue() {
        return this.value;
    }

    public static OperationType fromString(String operationTypeName) {
        if (operationTypeName != null) {
            for (OperationType operationType : values()) {
                if (operationTypeName.equals(operationType.value)) {
                    return operationType;
                }
            }
        }
        return OTHER;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "OperationType{value='" + this.value + "'}";
    }
}
