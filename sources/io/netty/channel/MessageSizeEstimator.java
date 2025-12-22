package io.netty.channel;

/* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/MessageSizeEstimator.class */
public interface MessageSizeEstimator {

    /* loaded from: reader.jar:BOOT-INF/lib/netty-transport-4.1.42.Final.jar:io/netty/channel/MessageSizeEstimator$Handle.class */
    public interface Handle {
        int size(Object obj);
    }

    Handle newHandle();
}
