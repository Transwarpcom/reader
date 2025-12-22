package org.apache.commons.lang3.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/ConcurrentException.class */
public class ConcurrentException extends Exception {
    private static final long serialVersionUID = 6622707671812226130L;

    protected ConcurrentException() {
    }

    public ConcurrentException(Throwable cause) {
        super(ConcurrentUtils.checkedException(cause));
    }

    public ConcurrentException(String msg, Throwable cause) {
        super(msg, ConcurrentUtils.checkedException(cause));
    }
}
