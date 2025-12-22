package io.vertx.core.net.impl;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.VoidChannelPromise;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GenericFutureListener;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.VertxException;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.NetworkMetrics;
import io.vertx.core.spi.metrics.TCPMetrics;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/ConnectionBase.class */
public abstract class ConnectionBase {
    public static final VertxException CLOSED_EXCEPTION = new VertxException("Connection was closed", true);
    private static final Logger log = LoggerFactory.getLogger((Class<?>) ConnectionBase.class);
    private static final int MAX_REGION_SIZE = 1048576;
    public final VoidChannelPromise voidPromise;
    protected final VertxInternal vertx;
    protected final ChannelHandlerContext chctx;
    protected final ContextInternal context;
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> closeHandler;
    private int writeInProgress;
    private Object metric;
    private SocketAddress remoteAddress;
    private SocketAddress localAddress;
    private boolean read;
    private boolean needsFlush;
    private boolean closed;

    public abstract NetworkMetrics metrics();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void handleInterestedOpsChanged();

    protected ConnectionBase(VertxInternal vertx, ChannelHandlerContext chctx, ContextInternal context) {
        this.vertx = vertx;
        this.chctx = chctx;
        this.context = context;
        this.voidPromise = new VoidChannelPromise(chctx.channel(), false);
    }

    public void fail(Throwable error) {
        handler().fail(error);
    }

    public VertxHandler handler() {
        return (VertxHandler) this.chctx.handler();
    }

    final void endReadAndFlush() {
        if (this.read) {
            this.read = false;
            if (this.needsFlush) {
                this.needsFlush = false;
                this.chctx.flush();
            }
        }
    }

    final boolean setRead() {
        this.read = true;
        return !this.closed;
    }

    private void write(Object msg, boolean flush, ChannelPromise promise) {
        this.needsFlush = !flush;
        if (flush) {
            this.chctx.writeAndFlush(msg, promise);
        } else {
            this.chctx.write(msg, promise);
        }
    }

    private void writeFlush(ChannelPromise promise) {
        if (this.needsFlush) {
            this.needsFlush = false;
            this.chctx.writeAndFlush(Unpooled.EMPTY_BUFFER, promise);
        } else {
            promise.setSuccess();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [io.netty.channel.ChannelPromise] */
    private void writeClose(Handler<AsyncResult<Void>> handler) {
        if (this.closed) {
            if (handler != null) {
                handler.handle(Future.succeededFuture());
            }
        } else {
            this.closed = true;
            writeFlush(this.chctx.newPromise().addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) f -> {
                ChannelFuture closeFut = this.chctx.channel().close();
                if (handler != null) {
                    closeFut.addListener2((GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>) new ChannelFutureListenerAdapter(this.context, null, handler));
                }
            }));
        }
    }

    public final ChannelPromise toPromise(Handler<AsyncResult<Void>> handler) {
        return handler == null ? this.voidPromise : wrap(handler);
    }

    private ChannelPromise wrap(Handler<AsyncResult<Void>> handler) {
        ChannelPromise promise = this.chctx.newPromise();
        promise.addListener2(fut -> {
            if (fut.isSuccess()) {
                handler.handle(Future.succeededFuture());
            } else {
                handler.handle(Future.failedFuture(fut.cause()));
            }
        });
        return promise;
    }

    public void writeToChannel(Object msg, ChannelPromise promise) {
        synchronized (this) {
            if (!this.chctx.executor().inEventLoop() || this.writeInProgress > 0) {
                queueForWrite(msg, promise);
            } else {
                write(msg, !this.read, promise);
            }
        }
    }

    private void queueForWrite(Object msg, ChannelPromise promise) {
        this.writeInProgress++;
        this.chctx.executor().execute(() -> {
            boolean flush;
            synchronized (this) {
                int i = this.writeInProgress - 1;
                this.writeInProgress = i;
                flush = i == 0 && !this.read;
            }
            write(msg, flush, promise);
        });
    }

    public void writeToChannel(Object obj) {
        writeToChannel(obj, this.voidPromise);
    }

    public final void flush() {
        flush(this.voidPromise);
    }

    public final void flush(ChannelPromise promise) {
        if (this.chctx.executor().inEventLoop()) {
            writeFlush(promise);
        } else {
            this.chctx.executor().execute(() -> {
                writeFlush(promise);
            });
        }
    }

    public boolean isNotWritable() {
        return !this.chctx.channel().isWritable();
    }

    public void close() {
        close(null);
    }

    public void close(Handler<AsyncResult<Void>> handler) {
        EventExecutor exec = this.chctx.executor();
        if (exec.inEventLoop()) {
            writeClose(handler);
        } else {
            exec.execute(() -> {
                writeClose(handler);
            });
        }
    }

    public synchronized ConnectionBase closeHandler(Handler<Void> handler) {
        this.closeHandler = handler;
        return this;
    }

    public synchronized ConnectionBase exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        return this;
    }

    protected synchronized Handler<Throwable> exceptionHandler() {
        return this.exceptionHandler;
    }

    public void doPause() {
        this.chctx.channel().config().setAutoRead(false);
    }

    public void doResume() {
        this.chctx.channel().config().setAutoRead(true);
    }

    public void doSetWriteQueueMaxSize(int size) {
        ChannelConfig config = this.chctx.channel().config();
        config.setWriteBufferWaterMark(new WriteBufferWaterMark(size / 2, size));
    }

    protected final void checkContext() {
        if (this.context != this.vertx.getContext()) {
            throw new IllegalStateException("Wrong context!");
        }
    }

    public final Channel channel() {
        return this.chctx.channel();
    }

    public final ChannelHandlerContext channelHandlerContext() {
        return this.chctx;
    }

    public final ContextInternal getContext() {
        return this.context;
    }

    public final synchronized void metric(Object metric) {
        this.metric = metric;
    }

    public final synchronized Object metric() {
        return this.metric;
    }

    protected synchronized void handleException(Throwable t) {
        NetworkMetrics metrics = metrics();
        if (metrics != null) {
            metrics.exceptionOccurred(this.metric, remoteAddress(), t);
        }
        if (this.exceptionHandler != null) {
            this.exceptionHandler.handle(t);
        } else if (log.isDebugEnabled()) {
            log.error(t.getMessage(), t);
        } else {
            log.error(t.getMessage());
        }
    }

    protected void handleClosed() {
        Handler<Void> handler;
        this.closed = true;
        synchronized (this) {
            NetworkMetrics metrics = metrics();
            if (metrics != null && (metrics instanceof TCPMetrics)) {
                ((TCPMetrics) metrics).disconnected(metric(), remoteAddress());
            }
            handler = this.closeHandler;
        }
        if (handler != null) {
            handler.handle(null);
        }
    }

    protected void handleIdle() {
        this.chctx.close();
    }

    protected void addFuture(Handler<AsyncResult<Void>> completionHandler, ChannelFuture future) {
        if (future != null) {
            future.addListener2(channelFuture -> {
                this.context.executeFromIO(v -> {
                    if (completionHandler != null) {
                        if (channelFuture.isSuccess()) {
                            completionHandler.handle(Future.succeededFuture());
                            return;
                        } else {
                            completionHandler.handle(Future.failedFuture(channelFuture.cause()));
                            return;
                        }
                    }
                    if (!channelFuture.isSuccess()) {
                        handleException(channelFuture.cause());
                    }
                });
            });
        }
    }

    protected boolean supportsFileRegion() {
        return !isSsl();
    }

    public void reportBytesRead(long numberOfBytes) {
        NetworkMetrics metrics = metrics();
        if (metrics != null) {
            metrics.bytesRead(metric(), remoteAddress(), numberOfBytes);
        }
    }

    public void reportBytesWritten(long numberOfBytes) {
        NetworkMetrics metrics = metrics();
        if (metrics != null) {
            metrics.bytesWritten(metric(), remoteAddress(), numberOfBytes);
        }
    }

    private void sendFileRegion(RandomAccessFile file, long offset, long length, ChannelPromise writeFuture) {
        if (length < 1048576) {
            writeToChannel(new DefaultFileRegion(file.getChannel(), offset, length), writeFuture);
            return;
        }
        ChannelPromise promise = this.chctx.newPromise();
        FileRegion region = new DefaultFileRegion(file.getChannel(), offset, 1048576L);
        region.retain();
        writeToChannel(region, promise);
        promise.addListener2(future -> {
            if (future.isSuccess()) {
                sendFileRegion(file, offset + 1048576, length - 1048576, writeFuture);
            } else {
                log.error(future.cause().getMessage(), future.cause());
                writeFuture.setFailure(future.cause());
            }
        });
    }

    protected ChannelFuture sendFile(RandomAccessFile raf, long offset, long length) throws IOException {
        ChannelPromise writeFuture = this.chctx.newPromise();
        if (!supportsFileRegion()) {
            writeToChannel(new ChunkedFile(raf, offset, length, 8192), writeFuture);
        } else {
            sendFileRegion(raf, offset, length, writeFuture);
        }
        if (writeFuture != null) {
            writeFuture.addListener2(fut -> {
                raf.close();
            });
        } else {
            raf.close();
        }
        return writeFuture;
    }

    public boolean isSsl() {
        return this.chctx.pipeline().get(SslHandler.class) != null;
    }

    public SSLSession sslSession() {
        ChannelHandlerContext sslHandlerContext = this.chctx.pipeline().context(SslHandler.class);
        if (sslHandlerContext != null) {
            SslHandler sslHandler = (SslHandler) sslHandlerContext.handler();
            return sslHandler.engine().getSession();
        }
        return null;
    }

    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        SSLSession session = sslSession();
        if (session != null) {
            return session.getPeerCertificateChain();
        }
        return null;
    }

    public String indicatedServerName() {
        if (this.chctx.channel().hasAttr(SslHandshakeCompletionHandler.SERVER_NAME_ATTR)) {
            return (String) this.chctx.channel().attr(SslHandshakeCompletionHandler.SERVER_NAME_ATTR).get();
        }
        return null;
    }

    public ChannelPromise channelFuture() {
        return this.chctx.newPromise();
    }

    public String remoteName() {
        java.net.SocketAddress addr = this.chctx.channel().remoteAddress();
        if (addr instanceof InetSocketAddress) {
            return ((InetSocketAddress) addr).getHostString();
        }
        return null;
    }

    public SocketAddress remoteAddress() {
        java.net.SocketAddress addr;
        SocketAddress address = this.remoteAddress;
        if (address == null && (addr = this.chctx.channel().remoteAddress()) != null) {
            address = this.vertx.transport().convert(addr);
            this.remoteAddress = address;
        }
        return address;
    }

    public SocketAddress localAddress() {
        java.net.SocketAddress addr;
        SocketAddress address = this.localAddress;
        if (address == null && (addr = this.chctx.channel().localAddress()) != null) {
            address = this.vertx.transport().convert(addr);
            this.localAddress = address;
        }
        return address;
    }

    public void handleMessage(Object msg) {
    }
}
