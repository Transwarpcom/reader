package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.InvalidPathException;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/LogicalOperator.class */
public enum LogicalOperator {
    AND("&&"),
    NOT("!"),
    OR("||");

    private final String operatorString;

    LogicalOperator(String operatorString) {
        this.operatorString = operatorString;
    }

    public String getOperatorString() {
        return this.operatorString;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.operatorString;
    }

    public static LogicalOperator fromString(String operatorString) {
        if (AND.operatorString.equals(operatorString)) {
            return AND;
        }
        if (NOT.operatorString.equals(operatorString)) {
            return NOT;
        }
        if (OR.operatorString.equals(operatorString)) {
            return OR;
        }
        throw new InvalidPathException("Failed to parse operator " + operatorString);
    }
}
