package io.netty.util.concurrent;

import io.netty.util.concurrent.Future;
import java.util.EventListener;

/* loaded from: reader.jar:BOOT-INF/lib/netty-common-4.1.42.Final.jar:io/netty/util/concurrent/GenericFutureListener.class */
public interface GenericFutureListener<F extends Future<?>> extends EventListener {
    void operationComplete(F f) throws Exception;
}
