package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/UncheckedExecutionException.class */
public class UncheckedExecutionException extends RuntimeException {
    private static final long serialVersionUID = 0;

    protected UncheckedExecutionException() {
    }

    protected UncheckedExecutionException(String message) {
        super(message);
    }

    public UncheckedExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncheckedExecutionException(Throwable cause) {
        super(cause);
    }
}
