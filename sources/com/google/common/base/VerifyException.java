package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/base/VerifyException.class */
public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(String message) {
        super(message);
    }

    public VerifyException(Throwable cause) {
        super(cause);
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }
}
