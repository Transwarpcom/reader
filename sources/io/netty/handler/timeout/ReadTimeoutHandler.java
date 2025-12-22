package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.TimeUnit;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/timeout/ReadTimeoutHandler.class */
public class ReadTimeoutHandler extends IdleStateHandler {
    private boolean closed;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ReadTimeoutHandler.class.desiredAssertionStatus();
    }

    public ReadTimeoutHandler(int timeoutSeconds) {
        this(timeoutSeconds, TimeUnit.SECONDS);
    }

    public ReadTimeoutHandler(long timeout, TimeUnit unit) {
        super(timeout, 0L, 0L, unit);
    }

    @Override // io.netty.handler.timeout.IdleStateHandler
    protected final void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        if (!$assertionsDisabled && evt.state() != IdleState.READER_IDLE) {
            throw new AssertionError();
        }
        readTimedOut(ctx);
    }

    protected void readTimedOut(ChannelHandlerContext ctx) throws Exception {
        if (!this.closed) {
            ctx.fireExceptionCaught((Throwable) ReadTimeoutException.INSTANCE);
            ctx.close();
            this.closed = true;
        }
    }
}
