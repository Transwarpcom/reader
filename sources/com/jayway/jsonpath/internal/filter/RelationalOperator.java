package com.jayway.jsonpath.internal.filter;

import com.jayway.jsonpath.InvalidPathException;
import java.util.Locale;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/filter/RelationalOperator.class */
public enum RelationalOperator {
    GTE(">="),
    LTE("<="),
    EQ("=="),
    TSEQ("==="),
    NE("!="),
    TSNE("!=="),
    LT("<"),
    GT(">"),
    REGEX("=~"),
    NIN("NIN"),
    IN("IN"),
    CONTAINS("CONTAINS"),
    ALL("ALL"),
    SIZE("SIZE"),
    EXISTS("EXISTS"),
    TYPE("TYPE"),
    MATCHES("MATCHES"),
    EMPTY("EMPTY"),
    SUBSETOF("SUBSETOF"),
    ANYOF("ANYOF"),
    NONEOF("NONEOF");

    private final String operatorString;

    RelationalOperator(String operatorString) {
        this.operatorString = operatorString;
    }

    public static RelationalOperator fromString(String operatorString) {
        String upperCaseOperatorString = operatorString.toUpperCase(Locale.ROOT);
        for (RelationalOperator operator : values()) {
            if (operator.operatorString.equals(upperCaseOperatorString)) {
                return operator;
            }
        }
        throw new InvalidPathException("Filter operator " + operatorString + " is not supported!");
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.operatorString;
    }
}
