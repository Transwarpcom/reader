package com.google.common.util.concurrent.internal;

/* loaded from: reader.jar:BOOT-INF/lib/failureaccess-1.0.1.jar:com/google/common/util/concurrent/internal/InternalFutures.class */
public final class InternalFutures {
    public static Throwable tryInternalFastPathGetFailure(InternalFutureFailureAccess future) {
        return future.tryInternalFastPathGetFailure();
    }

    private InternalFutures() {
    }
}
