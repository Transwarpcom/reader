package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/UncheckedTimeoutException.class */
public class UncheckedTimeoutException extends RuntimeException {
    private static final long serialVersionUID = 0;

    public UncheckedTimeoutException() {
    }

    public UncheckedTimeoutException(String message) {
        super(message);
    }

    public UncheckedTimeoutException(Throwable cause) {
        super(cause);
    }

    public UncheckedTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
