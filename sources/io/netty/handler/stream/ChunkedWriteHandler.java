package io.netty.handler.stream;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/stream/ChunkedWriteHandler.class */
public class ChunkedWriteHandler extends ChannelDuplexHandler {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ChunkedWriteHandler.class);
    private final Queue<PendingWrite> queue = new ArrayDeque();
    private volatile ChannelHandlerContext ctx;
    private PendingWrite currentWrite;

    public ChunkedWriteHandler() {
    }

    @Deprecated
    public ChunkedWriteHandler(int maxPendingWrites) {
        if (maxPendingWrites <= 0) {
            throw new IllegalArgumentException("maxPendingWrites: " + maxPendingWrites + " (expected: > 0)");
        }
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    public void resumeTransfer() {
        final ChannelHandlerContext ctx = this.ctx;
        if (ctx == null) {
            return;
        }
        if (ctx.executor().inEventLoop()) {
            resumeTransfer0(ctx);
        } else {
            ctx.executor().execute(new Runnable() { // from class: io.netty.handler.stream.ChunkedWriteHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    ChunkedWriteHandler.this.resumeTransfer0(ctx);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resumeTransfer0(ChannelHandlerContext ctx) {
        try {
            doFlush(ctx);
        } catch (Exception e) {
            logger.warn("Unexpected exception while sending chunks.", (Throwable) e);
        }
    }

    @Override // io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        this.queue.add(new PendingWrite(msg, promise));
    }

    @Override // io.netty.channel.ChannelDuplexHandler, io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext ctx) throws Exception {
        doFlush(ctx);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        doFlush(ctx);
        ctx.fireChannelInactive();
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel().isWritable()) {
            doFlush(ctx);
        }
        ctx.fireChannelWritabilityChanged();
    }

    private void discard(Throwable cause) {
        while (true) {
            PendingWrite currentWrite = this.currentWrite;
            if (this.currentWrite == null) {
                currentWrite = this.queue.poll();
            } else {
                this.currentWrite = null;
            }
            if (currentWrite != null) {
                Object message = currentWrite.msg;
                if (message instanceof ChunkedInput) {
                    ChunkedInput<?> in = (ChunkedInput) message;
                    try {
                        boolean endOfInput = in.isEndOfInput();
                        long inputLength = in.length();
                        closeInput(in);
                        if (!endOfInput) {
                            if (cause == null) {
                                cause = new ClosedChannelException();
                            }
                            currentWrite.fail(cause);
                        } else {
                            currentWrite.success(inputLength);
                        }
                    } catch (Exception e) {
                        closeInput(in);
                        currentWrite.fail(e);
                        if (logger.isWarnEnabled()) {
                            logger.warn(ChunkedInput.class.getSimpleName() + " failed", (Throwable) e);
                        }
                    }
                } else {
                    if (cause == null) {
                        cause = new ClosedChannelException();
                    }
                    currentWrite.fail(cause);
                }
            } else {
                return;
            }
        }
    }

    private void doFlush(ChannelHandlerContext ctx) {
        boolean suspend;
        final Channel channel = ctx.channel();
        if (!channel.isActive()) {
            discard(null);
            return;
        }
        boolean requiresFlush = true;
        ByteBufAllocator allocator = ctx.alloc();
        while (true) {
            if (!channel.isWritable()) {
                break;
            }
            if (this.currentWrite == null) {
                this.currentWrite = this.queue.poll();
            }
            if (this.currentWrite == null) {
                break;
            }
            if (this.currentWrite.promise.isDone()) {
                this.currentWrite = null;
            } else {
                final PendingWrite currentWrite = this.currentWrite;
                Object pendingMessage = currentWrite.msg;
                if (pendingMessage instanceof ChunkedInput) {
                    final ChunkedInput<?> chunks = (ChunkedInput) pendingMessage;
                    Object message = null;
                    try {
                        message = chunks.readChunk(allocator);
                        boolean endOfInput = chunks.isEndOfInput();
                        if (message == null) {
                            suspend = !endOfInput;
                        } else {
                            suspend = false;
                        }
                        if (suspend) {
                            break;
                        }
                        if (message == null) {
                            message = Unpooled.EMPTY_BUFFER;
                        }
                        ChannelFuture f = ctx.write(message);
                        if (endOfInput) {
                            this.currentWrite = null;
                            f.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.stream.ChunkedWriteHandler.2
                                @Override // io.netty.util.concurrent.GenericFutureListener
                                public void operationComplete(ChannelFuture future) throws Exception {
                                    if (!future.isSuccess()) {
                                        ChunkedWriteHandler.closeInput(chunks);
                                        currentWrite.fail(future.cause());
                                        return;
                                    }
                                    long inputProgress = chunks.progress();
                                    long inputLength = chunks.length();
                                    ChunkedWriteHandler.closeInput(chunks);
                                    currentWrite.progress(inputProgress, inputLength);
                                    currentWrite.success(inputLength);
                                }
                            });
                        } else if (channel.isWritable()) {
                            f.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.stream.ChunkedWriteHandler.3
                                @Override // io.netty.util.concurrent.GenericFutureListener
                                public void operationComplete(ChannelFuture future) throws Exception {
                                    if (!future.isSuccess()) {
                                        ChunkedWriteHandler.closeInput(chunks);
                                        currentWrite.fail(future.cause());
                                    } else {
                                        currentWrite.progress(chunks.progress(), chunks.length());
                                    }
                                }
                            });
                        } else {
                            f.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.stream.ChunkedWriteHandler.4
                                @Override // io.netty.util.concurrent.GenericFutureListener
                                public void operationComplete(ChannelFuture future) throws Exception {
                                    if (!future.isSuccess()) {
                                        ChunkedWriteHandler.closeInput(chunks);
                                        currentWrite.fail(future.cause());
                                    } else {
                                        currentWrite.progress(chunks.progress(), chunks.length());
                                        if (channel.isWritable()) {
                                            ChunkedWriteHandler.this.resumeTransfer();
                                        }
                                    }
                                }
                            });
                        }
                        ctx.flush();
                        requiresFlush = false;
                    } catch (Throwable t) {
                        this.currentWrite = null;
                        if (message != null) {
                            ReferenceCountUtil.release(message);
                        }
                        closeInput(chunks);
                        currentWrite.fail(t);
                    }
                } else {
                    this.currentWrite = null;
                    ctx.write(pendingMessage, currentWrite.promise);
                    requiresFlush = true;
                }
                if (!channel.isActive()) {
                    discard(new ClosedChannelException());
                    break;
                }
            }
        }
        if (requiresFlush) {
            ctx.flush();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void closeInput(ChunkedInput<?> chunks) {
        try {
            chunks.close();
        } catch (Throwable t) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to close a chunked input.", t);
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/stream/ChunkedWriteHandler$PendingWrite.class */
    private static final class PendingWrite {
        final Object msg;
        final ChannelPromise promise;

        PendingWrite(Object msg, ChannelPromise promise) {
            this.msg = msg;
            this.promise = promise;
        }

        void fail(Throwable cause) {
            ReferenceCountUtil.release(this.msg);
            this.promise.tryFailure(cause);
        }

        void success(long total) {
            if (this.promise.isDone()) {
                return;
            }
            progress(total, total);
            this.promise.trySuccess();
        }

        void progress(long progress, long total) {
            if (this.promise instanceof ChannelProgressivePromise) {
                ((ChannelProgressivePromise) this.promise).tryProgress(progress, total);
            }
        }
    }
}
