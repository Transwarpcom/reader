package com.mongodb.internal.async;

import com.mongodb.assertions.Assertions;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.diagnostics.logging.Logger;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/async/ErrorHandlingResultCallback.class */
public class ErrorHandlingResultCallback<T> implements SingleResultCallback<T> {
    private final SingleResultCallback<T> wrapped;
    private final Logger logger;

    public static <T> SingleResultCallback<T> errorHandlingCallback(SingleResultCallback<T> callback, Logger logger) {
        if (callback instanceof ErrorHandlingResultCallback) {
            return callback;
        }
        return new ErrorHandlingResultCallback(callback, logger);
    }

    ErrorHandlingResultCallback(SingleResultCallback<T> wrapped, Logger logger) {
        this.wrapped = (SingleResultCallback) Assertions.notNull("wrapped", wrapped);
        this.logger = (Logger) Assertions.notNull("logger", logger);
    }

    @Override // com.mongodb.async.SingleResultCallback
    public void onResult(T result, Throwable t) {
        try {
            this.wrapped.onResult(result, t);
        } catch (Throwable e) {
            this.logger.error("Callback onResult call produced an error", e);
        }
    }
}
