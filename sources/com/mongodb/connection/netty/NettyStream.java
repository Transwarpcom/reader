package com.mongodb.connection.netty;

import com.mongodb.MongoClientException;
import com.mongodb.MongoException;
import com.mongodb.MongoInternalException;
import com.mongodb.MongoInterruptedException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.ServerAddress;
import com.mongodb.connection.AsyncCompletionHandler;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.Stream;
import com.mongodb.internal.connection.SslHelper;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStream.class */
final class NettyStream implements Stream {
    private static final String READ_HANDLER_NAME = "ReadTimeoutHandler";
    private final ServerAddress address;
    private final SocketSettings settings;
    private final SslSettings sslSettings;
    private final EventLoopGroup workerGroup;
    private final Class<? extends SocketChannel> socketChannelClass;
    private final ByteBufAllocator allocator;
    private volatile boolean isClosed;
    private volatile Channel channel;
    private final LinkedList<ByteBuf> pendingInboundBuffers = new LinkedList<>();
    private volatile PendingReader pendingReader;
    private volatile Throwable pendingException;

    NettyStream(ServerAddress address, SocketSettings settings, SslSettings sslSettings, EventLoopGroup workerGroup, Class<? extends SocketChannel> socketChannelClass, ByteBufAllocator allocator) {
        this.address = address;
        this.settings = settings;
        this.sslSettings = sslSettings;
        this.workerGroup = workerGroup;
        this.socketChannelClass = socketChannelClass;
        this.allocator = allocator;
    }

    @Override // com.mongodb.connection.BufferProvider
    public org.bson.ByteBuf getBuffer(int size) {
        return new NettyByteBuf(this.allocator.buffer(size, size));
    }

    @Override // com.mongodb.connection.Stream
    public void open() throws InterruptedException, IOException {
        FutureAsyncCompletionHandler<Void> handler = new FutureAsyncCompletionHandler<>();
        openAsync(handler);
        handler.get();
    }

    @Override // com.mongodb.connection.Stream
    public void openAsync(final AsyncCompletionHandler<Void> handler) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(this.workerGroup);
        bootstrap.channel(this.socketChannelClass);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(this.settings.getConnectTimeout(TimeUnit.MILLISECONDS)));
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(this.settings.isKeepAlive()));
        if (this.settings.getReceiveBufferSize() > 0) {
            bootstrap.option(ChannelOption.SO_RCVBUF, Integer.valueOf(this.settings.getReceiveBufferSize()));
        }
        if (this.settings.getSendBufferSize() > 0) {
            bootstrap.option(ChannelOption.SO_SNDBUF, Integer.valueOf(this.settings.getSendBufferSize()));
        }
        bootstrap.option(ChannelOption.ALLOCATOR, this.allocator);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() { // from class: com.mongodb.connection.netty.NettyStream.1
            @Override // io.netty.channel.ChannelInitializer
            public void initChannel(SocketChannel ch2) throws Exception {
                if (NettyStream.this.sslSettings.isEnabled()) {
                    SSLEngine engine = NettyStream.this.getSslContext().createSSLEngine(NettyStream.this.address.getHost(), NettyStream.this.address.getPort());
                    engine.setUseClientMode(true);
                    SSLParameters sslParameters = engine.getSSLParameters();
                    SslHelper.enableSni(NettyStream.this.address, sslParameters);
                    if (!NettyStream.this.sslSettings.isInvalidHostNameAllowed()) {
                        SslHelper.enableHostNameVerification(sslParameters);
                    }
                    engine.setSSLParameters(sslParameters);
                    ch2.pipeline().addFirst("ssl", new SslHandler(engine, false));
                }
                int readTimeout = NettyStream.this.settings.getReadTimeout(TimeUnit.MILLISECONDS);
                if (readTimeout > 0) {
                    ch2.pipeline().addLast(NettyStream.READ_HANDLER_NAME, new ReadTimeoutHandler(readTimeout));
                }
                ch2.pipeline().addLast(new InboundBufferHandler());
            }
        });
        final ChannelFuture channelFuture = bootstrap.connect(this.address.getHost(), this.address.getPort());
        channelFuture.addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: com.mongodb.connection.netty.NettyStream.2
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    NettyStream.this.channel = channelFuture.channel();
                    NettyStream.this.channel.closeFuture().addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: com.mongodb.connection.netty.NettyStream.2.1
                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(ChannelFuture f2) throws Exception {
                            NettyStream.this.handleReadResponse(null, new IOException("The connection to the server was closed"));
                        }
                    });
                    handler.completed(null);
                    return;
                }
                handler.failed(new MongoSocketOpenException("Exception opening socket", NettyStream.this.getAddress(), future.cause()));
            }
        });
    }

    @Override // com.mongodb.connection.Stream
    public void write(List<org.bson.ByteBuf> buffers) throws InterruptedException, IOException {
        FutureAsyncCompletionHandler<Void> future = new FutureAsyncCompletionHandler<>();
        writeAsync(buffers, future);
        future.get();
    }

    @Override // com.mongodb.connection.Stream
    public org.bson.ByteBuf read(int numBytes) throws IOException {
        FutureAsyncCompletionHandler<org.bson.ByteBuf> future = new FutureAsyncCompletionHandler<>();
        readAsync(numBytes, future);
        return future.get();
    }

    @Override // com.mongodb.connection.Stream
    public void writeAsync(List<org.bson.ByteBuf> buffers, final AsyncCompletionHandler<Void> handler) {
        CompositeByteBuf composite = PooledByteBufAllocator.DEFAULT.compositeBuffer();
        for (org.bson.ByteBuf cur : buffers) {
            composite.addComponent(true, ((NettyByteBuf) cur).asByteBuf());
        }
        this.channel.writeAndFlush(composite).addListener2((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: com.mongodb.connection.netty.NettyStream.3
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    handler.failed(future.cause());
                } else {
                    handler.completed(null);
                }
            }
        });
    }

    @Override // com.mongodb.connection.Stream
    public void readAsync(int numBytes, AsyncCompletionHandler<org.bson.ByteBuf> handler) {
        Throwable exceptionResult;
        scheduleReadTimeout();
        org.bson.ByteBuf buffer = null;
        synchronized (this) {
            exceptionResult = this.pendingException;
            if (exceptionResult == null) {
                if (!hasBytesAvailable(numBytes)) {
                    this.pendingReader = new PendingReader(numBytes, handler);
                } else {
                    CompositeByteBuf composite = this.allocator.compositeBuffer(this.pendingInboundBuffers.size());
                    int bytesNeeded = numBytes;
                    Iterator<ByteBuf> iter = this.pendingInboundBuffers.iterator();
                    while (iter.hasNext()) {
                        ByteBuf next = iter.next();
                        int bytesNeededFromCurrentBuffer = Math.min(next.readableBytes(), bytesNeeded);
                        if (bytesNeededFromCurrentBuffer == next.readableBytes()) {
                            composite.addComponent(next);
                            iter.remove();
                        } else {
                            next.retain();
                            composite.addComponent(next.readSlice(bytesNeededFromCurrentBuffer));
                        }
                        composite.writerIndex(composite.writerIndex() + bytesNeededFromCurrentBuffer);
                        bytesNeeded -= bytesNeededFromCurrentBuffer;
                        if (bytesNeeded == 0) {
                            break;
                        }
                    }
                    buffer = new NettyByteBuf(composite).flip();
                }
            }
        }
        if (exceptionResult != null) {
            disableReadTimeout();
            handler.failed(exceptionResult);
        }
        if (buffer != null) {
            disableReadTimeout();
            handler.completed(buffer);
        }
    }

    private boolean hasBytesAvailable(int numBytes) {
        int bytesAvailable = 0;
        Iterator<ByteBuf> it = this.pendingInboundBuffers.iterator();
        while (it.hasNext()) {
            ByteBuf cur = it.next();
            bytesAvailable += cur.readableBytes();
            if (bytesAvailable >= numBytes) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReadResponse(ByteBuf buffer, Throwable t) {
        PendingReader localPendingReader = null;
        synchronized (this) {
            if (buffer != null) {
                this.pendingInboundBuffers.add(buffer.retain());
            } else {
                this.pendingException = t;
            }
            if (this.pendingReader != null) {
                localPendingReader = this.pendingReader;
                this.pendingReader = null;
            }
        }
        if (localPendingReader != null) {
            readAsync(localPendingReader.numBytes, localPendingReader.handler);
        }
    }

    @Override // com.mongodb.connection.Stream
    public ServerAddress getAddress() {
        return this.address;
    }

    @Override // com.mongodb.connection.Stream
    public void close() {
        this.isClosed = true;
        if (this.channel != null) {
            this.channel.close();
            this.channel = null;
        }
        Iterator<ByteBuf> iterator = this.pendingInboundBuffers.iterator();
        while (iterator.hasNext()) {
            ByteBuf nextByteBuf = iterator.next();
            iterator.remove();
            nextByteBuf.release();
        }
    }

    @Override // com.mongodb.connection.Stream
    public boolean isClosed() {
        return this.isClosed;
    }

    public SocketSettings getSettings() {
        return this.settings;
    }

    public SslSettings getSslSettings() {
        return this.sslSettings;
    }

    public EventLoopGroup getWorkerGroup() {
        return this.workerGroup;
    }

    public Class<? extends SocketChannel> getSocketChannelClass() {
        return this.socketChannelClass;
    }

    public ByteBufAllocator getAllocator() {
        return this.allocator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SSLContext getSslContext() {
        try {
            return this.sslSettings.getContext() == null ? SSLContext.getDefault() : this.sslSettings.getContext();
        } catch (NoSuchAlgorithmException e) {
            throw new MongoClientException("Unable to create default SSLContext", e);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStream$InboundBufferHandler.class */
    private class InboundBufferHandler extends SimpleChannelInboundHandler<ByteBuf> {
        private InboundBufferHandler() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.channel.SimpleChannelInboundHandler
        public void channelRead0(ChannelHandlerContext ctx, ByteBuf buffer) throws Exception {
            NettyStream.this.handleReadResponse(buffer, null);
        }

        @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) {
            if (t instanceof ReadTimeoutException) {
                NettyStream.this.handleReadResponse(null, new MongoSocketReadTimeoutException("Timeout while receiving message", NettyStream.this.address, t));
            } else {
                NettyStream.this.handleReadResponse(null, t);
            }
            ctx.close();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStream$PendingReader.class */
    private static final class PendingReader {
        private final int numBytes;
        private final AsyncCompletionHandler<org.bson.ByteBuf> handler;

        private PendingReader(int numBytes, AsyncCompletionHandler<org.bson.ByteBuf> handler) {
            this.numBytes = numBytes;
            this.handler = handler;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/netty/NettyStream$FutureAsyncCompletionHandler.class */
    private static final class FutureAsyncCompletionHandler<T> implements AsyncCompletionHandler<T> {
        private final CountDownLatch latch = new CountDownLatch(1);
        private volatile T t;
        private volatile Throwable throwable;

        FutureAsyncCompletionHandler() {
        }

        @Override // com.mongodb.connection.AsyncCompletionHandler
        public void completed(T t) {
            this.t = t;
            this.latch.countDown();
        }

        @Override // com.mongodb.connection.AsyncCompletionHandler
        public void failed(Throwable t) {
            this.throwable = t;
            this.latch.countDown();
        }

        public T get() throws InterruptedException, IOException {
            try {
                this.latch.await();
                if (this.throwable != null) {
                    if (this.throwable instanceof IOException) {
                        throw ((IOException) this.throwable);
                    }
                    if (this.throwable instanceof MongoException) {
                        throw ((MongoException) this.throwable);
                    }
                    throw new MongoInternalException("Exception thrown from Netty Stream", this.throwable);
                }
                return this.t;
            } catch (InterruptedException e) {
                throw new MongoInterruptedException("Interrupted", e);
            }
        }
    }

    private void scheduleReadTimeout() {
        adjustTimeout(false);
    }

    private void disableReadTimeout() {
        adjustTimeout(true);
    }

    private void adjustTimeout(boolean disable) {
        ChannelHandler timeoutHandler = this.channel.pipeline().get(READ_HANDLER_NAME);
        if (timeoutHandler != null) {
            final ReadTimeoutHandler readTimeoutHandler = (ReadTimeoutHandler) timeoutHandler;
            final ChannelHandlerContext handlerContext = this.channel.pipeline().context(timeoutHandler);
            EventExecutor executor = handlerContext.executor();
            if (disable) {
                if (executor.inEventLoop()) {
                    readTimeoutHandler.removeTimeout(handlerContext);
                    return;
                } else {
                    executor.submit(new Runnable() { // from class: com.mongodb.connection.netty.NettyStream.4
                        @Override // java.lang.Runnable
                        public void run() {
                            readTimeoutHandler.removeTimeout(handlerContext);
                        }
                    });
                    return;
                }
            }
            if (executor.inEventLoop()) {
                readTimeoutHandler.scheduleTimeout(handlerContext);
            } else {
                executor.submit(new Runnable() { // from class: com.mongodb.connection.netty.NettyStream.5
                    @Override // java.lang.Runnable
                    public void run() {
                        readTimeoutHandler.scheduleTimeout(handlerContext);
                    }
                });
            }
        }
    }
}
