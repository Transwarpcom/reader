package io.vertx.core.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.vertx.core.Handler;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.impl.ConnectionBase;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/VertxHandler.class */
public final class VertxHandler<C extends ConnectionBase> extends ChannelDuplexHandler {
    private static final Handler<Object> NULL_HANDLER = m -> {
    };
    private final Function<ChannelHandlerContext, C> connectionFactory;
    private final ContextInternal context;
    private C conn;
    private Handler<C> addHandler;
    private Handler<C> removeHandler;
    private Handler<Object> messageHandler;

    public static ByteBuf safeBuffer(ByteBufHolder holder, ByteBufAllocator allocator) {
        return safeBuffer(holder.content(), allocator);
    }

    public static ByteBuf safeBuffer(ByteBuf buf, ByteBufAllocator allocator) {
        if (buf == Unpooled.EMPTY_BUFFER) {
            return buf;
        }
        if (buf.isDirect() || (buf instanceof CompositeByteBuf)) {
            try {
                if (buf.isReadable()) {
                    ByteBuf buffer = allocator.heapBuffer(buf.readableBytes());
                    buffer.writeBytes(buf);
                    buf.release();
                    return buffer;
                }
                ByteBuf byteBuf = Unpooled.EMPTY_BUFFER;
                buf.release();
                return byteBuf;
            } catch (Throwable th) {
                buf.release();
                throw th;
            }
        }
        return buf;
    }

    public static <C extends ConnectionBase> VertxHandler<C> create(C connection) {
        return create(connection.context, ctx -> {
            return connection;
        });
    }

    public static <C extends ConnectionBase> VertxHandler<C> create(ContextInternal context, Function<ChannelHandlerContext, C> connectionFactory) {
        return new VertxHandler<>(context, connectionFactory);
    }

    private VertxHandler(ContextInternal context, Function<ChannelHandlerContext, C> connectionFactory) {
        this.context = context;
        this.connectionFactory = connectionFactory;
    }

    private void setConnection(C connection) {
        this.conn = connection;
        C c = this.conn;
        c.getClass();
        this.messageHandler = c::handleMessage;
        if (this.addHandler != null) {
            this.addHandler.handle(connection);
        }
    }

    void fail(Throwable error) {
        this.messageHandler = NULL_HANDLER;
        this.conn.chctx.pipeline().fireExceptionCaught(error);
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        setConnection(this.connectionFactory.apply(ctx));
    }

    public VertxHandler<C> addHandler(Handler<C> handler) {
        this.addHandler = handler;
        return this;
    }

    public VertxHandler<C> removeHandler(Handler<C> handler) {
        this.removeHandler = handler;
        return this;
    }

    public C getConnection() {
        return this.conn;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        ConnectionBase connection = getConnection();
        this.context.executeFromIO(v -> {
            connection.handleInterestedOpsChanged();
        });
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext chctx, Throwable t) throws Exception {
        Channel ch2 = chctx.channel();
        ConnectionBase connection = getConnection();
        if (connection != null) {
            this.context.executeFromIO(v -> {
                try {
                    if (ch2.isOpen()) {
                        ch2.close();
                    }
                } catch (Throwable th) {
                }
                connection.handleException(t);
            });
        } else {
            ch2.close();
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext chctx) throws Exception {
        if (this.removeHandler != null) {
            this.removeHandler.handle(this.conn);
        }
        this.context.executeFromIO(v -> {
            this.conn.handleClosed();
        });
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        this.conn.endReadAndFlush();
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext chctx, Object msg) throws Exception {
        if (this.conn.setRead()) {
            this.context.executeFromIO(msg, this.messageHandler);
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if ((evt instanceof IdleStateEvent) && ((IdleStateEvent) evt).state() == IdleState.ALL_IDLE) {
            this.context.executeFromIO(v -> {
                this.conn.handleIdle();
            });
        } else {
            ctx.fireUserEventTriggered(evt);
        }
    }
}
