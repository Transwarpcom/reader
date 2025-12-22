package io.netty.util.concurrent;

import java.util.concurrent.Executor;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/ImmediateExecutor.class */
public final class ImmediateExecutor implements Executor {
    public static final ImmediateExecutor INSTANCE = new ImmediateExecutor();

    private ImmediateExecutor() {
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException("command");
        }
        command.run();
    }
}
