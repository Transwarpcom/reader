package io.netty.channel;

import io.netty.util.IntSupplier;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/SelectStrategy.class */
public interface SelectStrategy {
    public static final int SELECT = -1;
    public static final int CONTINUE = -2;
    public static final int BUSY_WAIT = -3;

    int calculateStrategy(IntSupplier intSupplier, boolean z) throws Exception;
}
