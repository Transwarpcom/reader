package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/CircuitBreakingException.class */
public class CircuitBreakingException extends RuntimeException {
    private static final long serialVersionUID = 1408176654686913340L;

    public CircuitBreakingException() {
    }

    public CircuitBreakingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CircuitBreakingException(String message) {
        super(message);
    }

    public CircuitBreakingException(Throwable cause) {
        super(cause);
    }
}
