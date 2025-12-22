package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import org.apache.commons.lang3.Validate;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/concurrent/CallableBackgroundInitializer.class */
public class CallableBackgroundInitializer<T> extends BackgroundInitializer<T> {
    private final Callable<T> callable;

    public CallableBackgroundInitializer(Callable<T> call) {
        checkCallable(call);
        this.callable = call;
    }

    public CallableBackgroundInitializer(Callable<T> call, ExecutorService exec) {
        super(exec);
        checkCallable(call);
        this.callable = call;
    }

    @Override // org.apache.commons.lang3.concurrent.BackgroundInitializer
    protected T initialize() throws Exception {
        return this.callable.call();
    }

    private void checkCallable(Callable<T> call) {
        Validate.isTrue(call != null, "Callable must not be null!", new Object[0]);
    }
}
