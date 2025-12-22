package com.google.common.util.concurrent.internal;

/* loaded from: reader.jar:BOOT-INF/lib/failureaccess-1.0.1.jar:com/google/common/util/concurrent/internal/InternalFutureFailureAccess.class */
public abstract class InternalFutureFailureAccess {
    protected abstract Throwable tryInternalFastPathGetFailure();

    protected InternalFutureFailureAccess() {
    }
}
