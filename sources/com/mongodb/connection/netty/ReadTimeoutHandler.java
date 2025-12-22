package com.mongodb.connection.netty;

import com.mongodb.assertions.Assertions;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/ReadTimeoutHandler.class */
final class ReadTimeoutHandler extends ChannelInboundHandlerAdapter {
    private final long readTimeout;
    private volatile ScheduledFuture<?> timeout;

    ReadTimeoutHandler(long readTimeout) {
        Assertions.isTrueArgument("readTimeout must be greater than zero.", readTimeout > 0);
        this.readTimeout = readTimeout;
    }

    void scheduleTimeout(ChannelHandlerContext ctx) {
        Assertions.isTrue("Handler called from the eventLoop", ctx.channel().eventLoop().inEventLoop());
        if (this.timeout == null) {
            this.timeout = ctx.executor().schedule((Runnable) new ReadTimeoutTask(ctx), this.readTimeout, TimeUnit.MILLISECONDS);
        }
    }

    void removeTimeout(ChannelHandlerContext ctx) {
        Assertions.isTrue("Handler called from the eventLoop", ctx.channel().eventLoop().inEventLoop());
        if (this.timeout != null) {
            this.timeout.cancel(false);
            this.timeout = null;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/ReadTimeoutHandler$ReadTimeoutTask.class */
    private static final class ReadTimeoutTask implements Runnable {
        private final ChannelHandlerContext ctx;

        ReadTimeoutTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.ctx.channel().isOpen()) {
                try {
                    this.ctx.fireExceptionCaught((Throwable) ReadTimeoutException.INSTANCE);
                    this.ctx.close();
                } catch (Throwable t) {
                    this.ctx.fireExceptionCaught(t);
                }
            }
        }
    }
}
