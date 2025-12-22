package com.jayway.jsonpath;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/JsonPathException.class */
public class JsonPathException extends RuntimeException {
    public JsonPathException() {
    }

    public JsonPathException(String message) {
        super(message);
    }

    public JsonPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonPathException(Throwable cause) {
        super(cause);
    }
}
