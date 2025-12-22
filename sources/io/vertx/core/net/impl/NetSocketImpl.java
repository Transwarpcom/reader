package io.vertx.core.net.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.handler.ssl.SniHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.CharsetUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.impl.NetSocketInternal;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/NetSocketImpl.class */
public class NetSocketImpl extends ConnectionBase implements NetSocketInternal {
    private static final Handler<Object> NULL_MSG_HANDLER = event -> {
        if (event instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) event;
            byteBuf.release();
        }
    };
    private static final Logger log = LoggerFactory.getLogger((Class<?>) NetSocketImpl.class);
    private final String writeHandlerID;
    private final SSLHelper helper;
    private final SocketAddress remoteAddress;
    private final TCPMetrics metrics;
    private Handler<Void> endHandler;
    private Handler<Void> drainHandler;
    private InboundBuffer<Object> pending;
    private MessageConsumer registration;
    private Handler<Object> messageHandler;

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ ConnectionBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public /* bridge */ /* synthetic */ ConnectionBase closeHandler(Handler handler) {
        return closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.net.NetSocket
    /* renamed from: closeHandler, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ NetSocket mo1996closeHandler(Handler handler) {
        return closeHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ NetSocket exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    /* renamed from: handler */
    public /* bridge */ /* synthetic */ ReadStream handler2(Handler handler) {
        return handler2((Handler<Buffer>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream write(Object obj, Handler handler) {
        return write((Buffer) obj, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    public NetSocketImpl(VertxInternal vertx, ChannelHandlerContext channel, ContextInternal context, SSLHelper helper, TCPMetrics metrics) {
        this(vertx, channel, null, context, helper, metrics);
    }

    public NetSocketImpl(VertxInternal vertx, ChannelHandlerContext channel, SocketAddress remoteAddress, ContextInternal context, SSLHelper helper, TCPMetrics metrics) {
        super(vertx, channel, context);
        this.helper = helper;
        this.writeHandlerID = "__vertx.net." + UUID.randomUUID().toString();
        this.remoteAddress = remoteAddress;
        this.metrics = metrics;
        this.messageHandler = NULL_MSG_HANDLER;
        this.pending = new InboundBuffer<>(context);
        this.pending.drainHandler(v -> {
            doResume();
        });
        this.pending.handler(obj -> {
            if (obj == InboundBuffer.END_SENTINEL) {
                Handler<Void> handler = endHandler();
                if (handler != null) {
                    handler.handle(null);
                    return;
                }
                return;
            }
            Handler<Object> handler2 = messageHandler();
            if (handler2 != null) {
                handler2.handle(obj);
            }
        });
    }

    synchronized void registerEventBusHandler() {
        Handler<Message<Buffer>> writeHandler = msg -> {
            write((Buffer) msg.body());
        };
        this.registration = this.vertx.eventBus().localConsumer(this.writeHandlerID).handler2((Handler) writeHandler);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public TCPMetrics metrics() {
        return this.metrics;
    }

    @Override // io.vertx.core.net.NetSocket
    public String writeHandlerID() {
        return this.writeHandlerID;
    }

    @Override // io.vertx.core.impl.NetSocketInternal
    public synchronized NetSocketInternal writeMessage(Object message) {
        writeToChannel(message);
        return this;
    }

    @Override // io.vertx.core.impl.NetSocketInternal
    public NetSocketInternal writeMessage(Object message, Handler<AsyncResult<Void>> handler) {
        writeToChannel(message, toPromise(handler));
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public NetSocket write(Buffer data) {
        write(data.getByteBuf(), (Handler<AsyncResult<Void>>) null);
        return this;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str) {
        return write(str, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str, Handler<AsyncResult<Void>> handler) {
        write(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8), handler);
        return this;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str, String enc) {
        return write(str, enc, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(String str, String enc, Handler<AsyncResult<Void>> handler) {
        if (enc == null) {
            write(str);
        } else {
            write(Unpooled.copiedBuffer(str, Charset.forName(enc)), handler);
        }
        return this;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket write(Buffer message, Handler<AsyncResult<Void>> handler) {
        write(message.getByteBuf(), handler);
        return this;
    }

    private void write(ByteBuf buff, Handler<AsyncResult<Void>> handler) {
        reportBytesWritten(buff.readableBytes());
        writeMessage(buff, handler);
    }

    /* renamed from: handler */
    public synchronized NetSocket handler2(Handler<Buffer> dataHandler) {
        if (dataHandler != null) {
            messageHandler(new DataMessageHandler(channelHandlerContext().alloc(), dataHandler));
        } else {
            messageHandler(null);
        }
        return this;
    }

    private synchronized Handler<Object> messageHandler() {
        return this.messageHandler;
    }

    @Override // io.vertx.core.impl.NetSocketInternal
    public synchronized NetSocketInternal messageHandler(Handler<Object> handler) {
        this.messageHandler = handler;
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public synchronized NetSocket pause2() {
        this.pending.pause();
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.pending.fetch(amount);
        return this;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.vertx.core.net.NetSocket] */
    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public synchronized NetSocket resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public NetSocket setWriteQueueMaxSize2(int maxSize) {
        doSetWriteQueueMaxSize(maxSize);
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        return isNotWritable();
    }

    private synchronized Handler<Void> endHandler() {
        return this.endHandler;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.ReadStream
    public synchronized ReadStream<Buffer> endHandler(Handler<Void> endHandler) {
        this.endHandler = endHandler;
        return this;
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public synchronized NetSocket drainHandler(Handler<Void> drainHandler) {
        this.drainHandler = drainHandler;
        this.vertx.runOnContext(v -> {
            callDrainHandler();
        });
        return this;
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket sendFile(String filename, long offset, long length) {
        return sendFile(filename, offset, length, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0090  */
    @Override // io.vertx.core.net.NetSocket
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public io.vertx.core.net.NetSocket sendFile(java.lang.String r12, long r13, long r15, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> r17) throws java.io.IOException {
        /*
            r11 = this;
            r0 = r11
            io.vertx.core.impl.VertxInternal r0 = r0.vertx
            r1 = r12
            java.io.File r0 = r0.resolveFile(r1)
            r18 = r0
            r0 = r18
            boolean r0 = r0.isDirectory()
            if (r0 == 0) goto L1e
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            java.lang.String r2 = "filename must point to a file and not to a directory"
            r1.<init>(r2)
            throw r0
        L1e:
            r0 = 0
            r19 = r0
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch: java.io.IOException -> L65
            r1 = r0
            r2 = r18
            java.lang.String r3 = "r"
            r1.<init>(r2, r3)     // Catch: java.io.IOException -> L65
            r19 = r0
            r0 = r11
            r1 = r19
            r2 = r13
            r3 = r18
            long r3 = r3.length()     // Catch: java.io.IOException -> L65
            long r2 = java.lang.Math.min(r2, r3)     // Catch: java.io.IOException -> L65
            r3 = r15
            r4 = r18
            long r4 = r4.length()     // Catch: java.io.IOException -> L65
            r5 = r13
            long r4 = r4 - r5
            long r3 = java.lang.Math.min(r3, r4)     // Catch: java.io.IOException -> L65
            io.netty.channel.ChannelFuture r0 = super.sendFile(r1, r2, r3)     // Catch: java.io.IOException -> L65
            r20 = r0
            r0 = r17
            if (r0 == 0) goto L62
            r0 = r20
            r1 = r11
            r2 = r20
            r3 = r17
            io.vertx.core.net.NetSocket r1 = (v3) -> { // io.netty.util.concurrent.GenericFutureListener.operationComplete(io.netty.util.concurrent.Future):void
                r1.lambda$sendFile$6(r2, r3, v3);
            }     // Catch: java.io.IOException -> L65
            io.netty.channel.ChannelFuture r0 = r0.addListener2(r1)     // Catch: java.io.IOException -> L65
        L62:
            goto L9a
        L65:
            r20 = move-exception
            r0 = r19
            if (r0 == 0) goto L71
            r0 = r19
            r0.close()     // Catch: java.io.IOException -> L74
        L71:
            goto L76
        L74:
            r21 = move-exception
        L76:
            r0 = r17
            if (r0 == 0) goto L90
            r0 = r11
            io.vertx.core.impl.VertxInternal r0 = r0.vertx
            r1 = r17
            r2 = r20
            io.vertx.core.net.NetSocket r1 = (v2) -> { // io.vertx.core.Handler.handle(java.lang.Object):void
                lambda$sendFile$7(r1, r2, v2);
            }
            r0.runOnContext(r1)
            goto L9a
        L90:
            io.vertx.core.logging.Logger r0 = io.vertx.core.net.impl.NetSocketImpl.log
            java.lang.String r1 = "Failed to send file"
            r2 = r20
            r0.error(r1, r2)
        L9a:
            r0 = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.net.impl.NetSocketImpl.sendFile(java.lang.String, long, long, io.vertx.core.Handler):io.vertx.core.net.NetSocket");
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public NetSocketImpl exceptionHandler(Handler<Throwable> handler) {
        return (NetSocketImpl) super.exceptionHandler(handler);
    }

    @Override // io.vertx.core.net.impl.ConnectionBase, io.vertx.core.http.HttpConnection
    public NetSocketImpl closeHandler(Handler<Void> handler) {
        return (NetSocketImpl) super.closeHandler(handler);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket upgradeToSsl(Handler<Void> handler) {
        return upgradeToSsl(null, handler);
    }

    @Override // io.vertx.core.net.NetSocket
    public NetSocket upgradeToSsl(String serverName, Handler<Void> handler) {
        ChannelOutboundHandler sslHandler;
        if (((ChannelOutboundHandler) this.chctx.pipeline().get("ssl")) == null) {
            this.chctx.pipeline().addFirst("handshaker", new SslHandshakeCompletionHandler(ar -> {
                if (ar.succeeded()) {
                    handler.handle(null);
                } else {
                    this.chctx.channel().closeFuture();
                    handleException(ar.cause());
                }
            }));
            if (this.remoteAddress != null) {
                sslHandler = new SslHandler(this.helper.createEngine(this.vertx, this.remoteAddress, serverName));
                ((SslHandler) sslHandler).setHandshakeTimeout(this.helper.getSslHandshakeTimeout(), this.helper.getSslHandshakeTimeoutUnit());
            } else if (this.helper.isSNI()) {
                sslHandler = new SniHandler(this.helper.serverNameMapper(this.vertx));
            } else {
                sslHandler = new SslHandler(this.helper.createEngine(this.vertx));
                ((SslHandler) sslHandler).setHandshakeTimeout(this.helper.getSslHandshakeTimeout(), this.helper.getSslHandshakeTimeoutUnit());
            }
            this.chctx.pipeline().addFirst("ssl", sslHandler);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.vertx.core.net.impl.ConnectionBase
    public synchronized void handleInterestedOpsChanged() {
        checkContext();
        callDrainHandler();
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        close(handler);
    }

    @Override // io.vertx.core.net.NetSocket, io.vertx.core.streams.WriteStream
    public void end() {
        close();
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    protected void handleClosed() {
        MessageConsumer consumer;
        synchronized (this) {
            consumer = this.registration;
            this.registration = null;
        }
        this.pending.write((InboundBuffer<Object>) InboundBuffer.END_SENTINEL);
        super.handleClosed();
        if (consumer != null) {
            consumer.unregister();
        }
    }

    @Override // io.vertx.core.net.impl.ConnectionBase
    public void handleMessage(Object msg) {
        if (!this.pending.write((InboundBuffer<Object>) msg)) {
            doPause();
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/NetSocketImpl$DataMessageHandler.class */
    private class DataMessageHandler implements Handler<Object> {
        private final Handler<Buffer> dataHandler;
        private final ByteBufAllocator allocator;

        DataMessageHandler(ByteBufAllocator allocator, Handler<Buffer> dataHandler) {
            this.allocator = allocator;
            this.dataHandler = dataHandler;
        }

        @Override // io.vertx.core.Handler
        public void handle(Object event) {
            if (event instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) event;
                Buffer data = Buffer.buffer(VertxHandler.safeBuffer(byteBuf, this.allocator));
                NetSocketImpl.this.reportBytesRead(data.length());
                this.dataHandler.handle(data);
            }
        }
    }

    private synchronized void callDrainHandler() {
        if (this.drainHandler != null && !writeQueueFull()) {
            this.drainHandler.handle(null);
        }
    }
}
