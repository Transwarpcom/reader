package com.jayway.jsonpath.internal;

/* loaded from: reader.jar:BOOT-INF/lib/json-path-2.6.0.jar:com/jayway/jsonpath/internal/EvaluationAbortException.class */
public class EvaluationAbortException extends RuntimeException {
    private static final long serialVersionUID = 4419305302960432348L;

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        return this;
    }
}
