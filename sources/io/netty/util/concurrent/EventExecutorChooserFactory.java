package io.netty.util.concurrent;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/EventExecutorChooserFactory.class */
public interface EventExecutorChooserFactory {

    /* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/EventExecutorChooserFactory$EventExecutorChooser.class */
    public interface EventExecutorChooser {
        EventExecutor next();
    }

    EventExecutorChooser newChooser(EventExecutor[] eventExecutorArr);
}
