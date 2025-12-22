package com.jayway.jsonpath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/ValueCompareException.class */
public class ValueCompareException extends JsonPathException {
    public ValueCompareException() {
    }

    public ValueCompareException(Object left, Object right) {
        super(String.format("Can not compare a %1s with a %2s", left.getClass().getName(), right.getClass().getName()));
    }

    public ValueCompareException(String message) {
        super(message);
    }

    public ValueCompareException(String message, Throwable cause) {
        super(message, cause);
    }
}
