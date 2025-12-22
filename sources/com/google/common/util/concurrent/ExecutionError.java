package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
/* loaded from: reader.jar:BOOT-INF/lib/guava-28.0-jre.jar:com/google/common/util/concurrent/ExecutionError.class */
public class ExecutionError extends Error {
    private static final long serialVersionUID = 0;

    protected ExecutionError() {
    }

    protected ExecutionError(String message) {
        super(message);
    }

    public ExecutionError(String message, Error cause) {
        super(message, cause);
    }

    public ExecutionError(Error cause) {
        super(cause);
    }
}
