package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/ConcurrentRuntimeException.class */
public class ConcurrentRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -6582182735562919670L;

    protected ConcurrentRuntimeException() {
    }

    public ConcurrentRuntimeException(Throwable cause) {
        super(ConcurrentUtils.checkedException(cause));
    }

    public ConcurrentRuntimeException(String msg, Throwable cause) {
        super(msg, ConcurrentUtils.checkedException(cause));
    }
}
