package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import java.io.RandomAccessFile;
import java.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/FileStreamChannel.class */
class FileStreamChannel extends AbstractChannel {
    private static final SocketAddress LOCAL_ADDRESS = new StreamSocketAddress();
    private static final SocketAddress REMOTE_ADDRESS = new StreamSocketAddress();
    private static final ChannelMetadata METADATA = new ChannelMetadata(true);
    private final ChannelConfig config;
    private boolean active;
    private boolean closed;
    private long bytesWritten;
    private final VertxHttp2Stream<?> stream;
    final Handler<Void> drainHandler;

    FileStreamChannel(final Promise<Long> result, VertxHttp2Stream stream, final long offset, final long length) {
        super(null, Id.INSTANCE);
        this.config = new DefaultChannelConfig(this);
        this.drainHandler = v -> {
            flush();
        };
        pipeline().addLast(new ChannelInitializer<Channel>() { // from class: io.vertx.core.http.impl.FileStreamChannel.1
            @Override // io.netty.channel.ChannelInitializer
            protected void initChannel(Channel ch2) throws Exception {
                ChannelPipeline pipeline = ch2.pipeline();
                pipeline.addLast(new ChunkedWriteHandler());
                pipeline.addLast(new ChannelInboundHandlerAdapter() { // from class: io.vertx.core.http.impl.FileStreamChannel.1.1
                    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                        if (evt instanceof RandomAccessFile) {
                            ChannelFuture fut = ctx.writeAndFlush(new ChunkedFile((RandomAccessFile) evt, offset, length, 8192));
                            Promise promise = result;
                            fut.addListener2(f -> {
                                if (f.isSuccess()) {
                                    promise.tryComplete(Long.valueOf(FileStreamChannel.this.bytesWritten));
                                } else {
                                    promise.tryFail(f.cause());
                                }
                                fut.addListener2((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
                            });
                        }
                    }

                    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        result.tryFail(cause);
                    }
                });
            }
        });
        this.stream = stream;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        this.active = true;
    }

    @Override // io.netty.channel.AbstractChannel
    protected AbstractChannel.AbstractUnsafe newUnsafe() {
        return new DefaultUnsafe();
    }

    @Override // io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop loop) {
        return true;
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return LOCAL_ADDRESS;
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return REMOTE_ADDRESS;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress localAddress) throws Exception {
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        doClose();
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        this.active = false;
        this.closed = true;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer in) {
        ByteBuf chunk;
        while (!this.stream.isNotWritable() && (chunk = (ByteBuf) in.current()) != null) {
            this.bytesWritten += chunk.readableBytes();
            this.stream.writeData(chunk.retain(), false);
            this.stream.handlerContext.flush();
            in.remove();
        }
    }

    @Override // io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // io.netty.channel.Channel
    public boolean isActive() {
        return this.active;
    }

    @Override // io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/FileStreamChannel$StreamSocketAddress.class */
    private static class StreamSocketAddress extends SocketAddress {
        private StreamSocketAddress() {
        }

        public String toString() {
            return "stream";
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/FileStreamChannel$DefaultUnsafe.class */
    private class DefaultUnsafe extends AbstractChannel.AbstractUnsafe {
        private DefaultUnsafe() {
            super();
        }

        @Override // io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
            safeSetSuccess(promise);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/FileStreamChannel$Id.class */
    static class Id implements ChannelId {
        static final ChannelId INSTANCE = new Id();

        private Id() {
        }

        @Override // io.netty.channel.ChannelId
        public String asShortText() {
            return toString();
        }

        @Override // io.netty.channel.ChannelId
        public String asLongText() {
            return toString();
        }

        @Override // java.lang.Comparable
        public int compareTo(ChannelId o) {
            if (o instanceof Id) {
                return 0;
            }
            return asLongText().compareTo(o.asLongText());
        }

        public int hashCode() {
            return 0;
        }

        public boolean equals(Object obj) {
            return obj instanceof Id;
        }

        public String toString() {
            return "stream";
        }
    }
}
