package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.WebSocketBase;
import io.vertx.core.http.WebSocketFrame;
import io.vertx.core.http.impl.ws.WebSocketFrameImpl;
import io.vertx.core.http.impl.ws.WebSocketFrameInternal;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.util.UUID;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/WebSocketImplBase.class */
public abstract class WebSocketImplBase<S extends WebSocketBase> implements WebSocketBase {
    private final boolean supportsContinuation;
    private final String textHandlerID = "__vertx.ws." + UUID.randomUUID().toString();
    private final String binaryHandlerID = "__vertx.ws." + UUID.randomUUID().toString();
    private final int maxWebSocketFrameSize;
    private final int maxWebSocketMessageSize;
    private final InboundBuffer<Buffer> pending;
    private MessageConsumer binaryHandlerRegistration;
    private MessageConsumer textHandlerRegistration;
    private String subProtocol;
    private Object metric;
    private Handler<WebSocketFrameInternal> frameHandler;
    private Handler<Buffer> pongHandler;
    private Handler<Void> drainHandler;
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> closeHandler;
    private Handler<Void> endHandler;
    protected final Http1xConnectionBase conn;
    protected boolean closed;
    private Short closeStatusCode;
    private String closeReason;
    private MultiMap headers;

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream drainHandler(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream write(Object obj, Handler handler) {
        return write((Buffer) obj, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    WebSocketImplBase(Http1xConnectionBase conn, boolean supportsContinuation, int maxWebSocketFrameSize, int maxWebSocketMessageSize) {
        this.supportsContinuation = supportsContinuation;
        this.conn = conn;
        this.maxWebSocketFrameSize = maxWebSocketFrameSize;
        this.maxWebSocketMessageSize = maxWebSocketMessageSize;
        this.pending = new InboundBuffer<>(conn.getContext());
        this.pending.drainHandler(v -> {
            conn.doResume();
        });
    }

    void registerHandler(EventBus eventBus) {
        Handler<Message<Buffer>> binaryHandler = msg -> {
            writeBinaryFrameInternal((Buffer) msg.body());
        };
        Handler<Message<String>> textHandler = msg2 -> {
            writeTextFrameInternal((String) msg2.body());
        };
        this.binaryHandlerRegistration = eventBus.localConsumer(this.binaryHandlerID).handler2((Handler) binaryHandler);
        this.textHandlerRegistration = eventBus.localConsumer(this.textHandlerID).handler2((Handler) textHandler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public String binaryHandlerID() {
        return this.binaryHandlerID;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public String textHandlerID() {
        return this.textHandlerID;
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        boolean zIsNotWritable;
        synchronized (this.conn) {
            checkClosed();
            zIsNotWritable = this.conn.isNotWritable();
        }
        return zIsNotWritable;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public void close() {
        close((Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public void close(Handler<AsyncResult<Void>> handler) {
        close((short) 1000, null, handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public void close(short statusCode) {
        close(statusCode, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public void close(short statusCode, Handler<AsyncResult<Void>> handler) {
        close(statusCode, null, handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public void close(short statusCode, String reason) {
        close(statusCode, reason, null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public void close(short statusCode, String reason, Handler<AsyncResult<Void>> handler) {
        synchronized (this.conn) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            unregisterHandlers();
            this.conn.closeWithPayload(statusCode, reason, handler);
        }
    }

    @Override // io.vertx.core.http.WebSocketBase
    public boolean isSsl() {
        return this.conn.isSsl();
    }

    @Override // io.vertx.core.http.WebSocketBase
    public SSLSession sslSession() {
        return this.conn.sslSession();
    }

    @Override // io.vertx.core.http.WebSocketBase
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return this.conn.peerCertificateChain();
    }

    @Override // io.vertx.core.http.WebSocketBase
    public SocketAddress localAddress() {
        return this.conn.localAddress();
    }

    @Override // io.vertx.core.http.WebSocketBase
    public SocketAddress remoteAddress() {
        return this.conn.remoteAddress();
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeFinalTextFrame(String str) {
        return (S) writeFinalTextFrame(str, null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeFinalTextFrame(String str, Handler<AsyncResult<Void>> handler) {
        return (S) writeFrame(WebSocketFrame.textFrame(str, true), handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeFinalBinaryFrame(Buffer buffer) {
        return (S) writeFinalBinaryFrame(buffer, null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeFinalBinaryFrame(Buffer buffer, Handler<AsyncResult<Void>> handler) {
        return (S) writeFrame(WebSocketFrame.binaryFrame(buffer, true), handler);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public String subProtocol() {
        String str;
        synchronized (this.conn) {
            str = this.subProtocol;
        }
        return str;
    }

    void subProtocol(String subProtocol) {
        synchronized (this.conn) {
            this.subProtocol = subProtocol;
        }
    }

    @Override // io.vertx.core.http.WebSocketBase
    public Short closeStatusCode() {
        Short sh;
        synchronized (this.conn) {
            sh = this.closeStatusCode;
        }
        return sh;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public String closeReason() {
        String str;
        synchronized (this.conn) {
            str = this.closeReason;
        }
        return str;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public MultiMap headers() {
        MultiMap multiMap;
        synchronized (this.conn) {
            multiMap = this.headers;
        }
        return multiMap;
    }

    void headers(MultiMap responseHeaders) {
        synchronized (this.conn) {
            this.headers = responseHeaders;
        }
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeBinaryMessage(Buffer buffer) {
        return (S) writeBinaryMessage(buffer, null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeBinaryMessage(Buffer data, Handler<AsyncResult<Void>> handler) {
        synchronized (this.conn) {
            checkClosed();
            writePartialMessage(FrameType.BINARY, data, 0, handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeTextMessage(String str) {
        return (S) writeTextMessage(str, null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeTextMessage(String text, Handler<AsyncResult<Void>> handler) {
        synchronized (this.conn) {
            checkClosed();
            Buffer data = Buffer.buffer(text);
            writePartialMessage(FrameType.TEXT, data, 0, handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public S write(Buffer buffer) {
        return (S) write(buffer, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S write(Buffer data, Handler<AsyncResult<Void>> handler) {
        synchronized (this.conn) {
            checkClosed();
            writeFrame(WebSocketFrame.binaryFrame(data, true), handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writePing(Buffer buffer) {
        if (buffer.length() > this.maxWebSocketFrameSize || buffer.length() > 125) {
            throw new IllegalStateException("Ping cannot exceed maxWebSocketFrameSize or 125 bytes");
        }
        return (S) writeFrame(WebSocketFrame.pingFrame(buffer));
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writePong(Buffer buffer) {
        if (buffer.length() > this.maxWebSocketFrameSize || buffer.length() > 125) {
            throw new IllegalStateException("Pong cannot exceed maxWebSocketFrameSize or 125 bytes");
        }
        return (S) writeFrame(WebSocketFrame.pongFrame(buffer));
    }

    private void writePartialMessage(FrameType frameType, Buffer data, int offset, Handler<AsyncResult<Void>> handler) {
        boolean isFinal;
        WebSocketFrame frame;
        int end = offset + this.maxWebSocketFrameSize;
        if (end >= data.length()) {
            end = data.length();
            isFinal = true;
        } else {
            isFinal = false;
        }
        Buffer slice = data.slice(offset, end);
        if (offset == 0 || !this.supportsContinuation) {
            frame = new WebSocketFrameImpl(frameType, slice.getByteBuf(), isFinal);
        } else {
            frame = WebSocketFrame.continuationFrame(slice, isFinal);
        }
        int newOffset = offset + this.maxWebSocketFrameSize;
        if (isFinal) {
            writeFrame(frame, handler);
        } else {
            writeFrame(frame);
            writePartialMessage(frameType, data, newOffset, handler);
        }
    }

    private void writeBinaryFrameInternal(Buffer data) {
        ByteBuf buf = data.getByteBuf();
        WebSocketFrame frame = new WebSocketFrameImpl(FrameType.BINARY, buf);
        writeFrame(frame);
    }

    private void writeTextFrameInternal(String str) {
        WebSocketFrame frame = new WebSocketFrameImpl(str);
        writeFrame(frame);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeFrame(WebSocketFrame webSocketFrame) {
        return (S) writeFrame(webSocketFrame, null);
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S writeFrame(WebSocketFrame frame, Handler<AsyncResult<Void>> handler) {
        synchronized (this.conn) {
            checkClosed();
            this.conn.reportBytesWritten(((WebSocketFrameInternal) frame).length());
            this.conn.writeToChannel(this.conn.encodeFrame((WebSocketFrameImpl) frame), this.conn.toPromise(handler));
        }
        return this;
    }

    void checkClosed() {
        synchronized (this.conn) {
            if (this.closed) {
                throw new IllegalStateException("WebSocket is closed");
            }
        }
    }

    @Override // io.vertx.core.http.WebSocketBase
    public boolean isClosed() {
        boolean z;
        synchronized (this.conn) {
            z = this.closed;
        }
        return z;
    }

    void handleFrame(WebSocketFrameInternal frame) {
        synchronized (this.conn) {
            if (frame.type() != FrameType.CLOSE) {
                this.conn.reportBytesRead(frame.length());
                if (!this.pending.write((InboundBuffer<Buffer>) frame.binaryData())) {
                    this.conn.doPause();
                }
            }
            switch (frame.type()) {
                case PONG:
                    if (this.pongHandler != null) {
                        this.pongHandler.handle(frame.binaryData());
                        break;
                    }
                    break;
                case CLOSE:
                    this.closeStatusCode = Short.valueOf(frame.closeStatusCode());
                    this.closeReason = frame.closeReason();
                case TEXT:
                case BINARY:
                case CONTINUATION:
                    if (this.frameHandler != null) {
                        this.frameHandler.handle(frame);
                        break;
                    }
                    break;
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/WebSocketImplBase$FrameAggregator.class */
    private class FrameAggregator implements Handler<WebSocketFrameInternal> {
        private Handler<String> textMessageHandler;
        private Handler<Buffer> binaryMessageHandler;
        private Buffer textMessageBuffer;
        private Buffer binaryMessageBuffer;

        private FrameAggregator() {
        }

        @Override // io.vertx.core.Handler
        public void handle(WebSocketFrameInternal frame) {
            switch (frame.type()) {
                case TEXT:
                    handleTextFrame(frame);
                    break;
                case BINARY:
                    handleBinaryFrame(frame);
                    break;
                case CONTINUATION:
                    if (this.textMessageBuffer != null && this.textMessageBuffer.length() > 0) {
                        handleTextFrame(frame);
                        break;
                    } else if (this.binaryMessageBuffer != null && this.binaryMessageBuffer.length() > 0) {
                        handleBinaryFrame(frame);
                        break;
                    }
                    break;
            }
        }

        private void handleTextFrame(WebSocketFrameInternal frame) {
            Buffer frameBuffer = Buffer.buffer(frame.getBinaryData());
            if (this.textMessageBuffer == null) {
                this.textMessageBuffer = frameBuffer;
            } else {
                this.textMessageBuffer.appendBuffer(frameBuffer);
            }
            if (this.textMessageBuffer.length() > WebSocketImplBase.this.maxWebSocketMessageSize) {
                int len = this.textMessageBuffer.length() - frameBuffer.length();
                this.textMessageBuffer = null;
                String msg = "Cannot process text frame of size " + frameBuffer.length() + ", it would cause message buffer (size " + len + ") to overflow max message size of " + WebSocketImplBase.this.maxWebSocketMessageSize;
                WebSocketImplBase.this.handleException(new IllegalStateException(msg));
                return;
            }
            if (frame.isFinal()) {
                String fullMessage = this.textMessageBuffer.toString();
                this.textMessageBuffer = null;
                if (this.textMessageHandler != null) {
                    this.textMessageHandler.handle(fullMessage);
                }
            }
        }

        private void handleBinaryFrame(WebSocketFrameInternal frame) {
            Buffer frameBuffer = Buffer.buffer(frame.getBinaryData());
            if (this.binaryMessageBuffer == null) {
                this.binaryMessageBuffer = frameBuffer;
            } else {
                this.binaryMessageBuffer.appendBuffer(frameBuffer);
            }
            if (this.binaryMessageBuffer.length() > WebSocketImplBase.this.maxWebSocketMessageSize) {
                int len = this.binaryMessageBuffer.length() - frameBuffer.length();
                this.binaryMessageBuffer = null;
                String msg = "Cannot process binary frame of size " + frameBuffer.length() + ", it would cause message buffer (size " + len + ") to overflow max message size of " + WebSocketImplBase.this.maxWebSocketMessageSize;
                WebSocketImplBase.this.handleException(new IllegalStateException(msg));
                return;
            }
            if (frame.isFinal()) {
                Buffer fullMessage = this.binaryMessageBuffer.copy();
                this.binaryMessageBuffer = null;
                if (this.binaryMessageHandler != null) {
                    this.binaryMessageHandler.handle(fullMessage);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.http.WebSocketBase
    public S frameHandler(Handler<WebSocketFrame> handler) {
        synchronized (this.conn) {
            checkClosed();
            this.frameHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public WebSocketBase textMessageHandler(Handler<String> handler) {
        synchronized (this.conn) {
            checkClosed();
            if (this.frameHandler == null || this.frameHandler.getClass() != FrameAggregator.class) {
                this.frameHandler = new FrameAggregator();
            }
            ((FrameAggregator) this.frameHandler).textMessageHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S binaryMessageHandler(Handler<Buffer> handler) {
        synchronized (this.conn) {
            checkClosed();
            if (this.frameHandler == null || this.frameHandler.getClass() != FrameAggregator.class) {
                this.frameHandler = new FrameAggregator();
            }
            ((FrameAggregator) this.frameHandler).binaryMessageHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public WebSocketBase pongHandler(Handler<Buffer> handler) {
        synchronized (this.conn) {
            checkClosed();
            this.pongHandler = handler;
        }
        return this;
    }

    void handleDrained() {
        if (this.drainHandler != null) {
            Handler<Void> dh = this.drainHandler;
            this.drainHandler = null;
            dh.handle(null);
        }
    }

    void handleException(Throwable t) {
        synchronized (this.conn) {
            if (this.exceptionHandler != null) {
                this.exceptionHandler.handle(t);
            }
        }
    }

    void handleClosed() {
        Handler<Void> endHandler;
        Handler<Void> closeHandler;
        unregisterHandlers();
        synchronized (this.conn) {
            endHandler = this.pending.isPaused() ? null : this.endHandler;
            closeHandler = this.closeHandler;
            this.closed = true;
            this.binaryHandlerRegistration = null;
            this.textHandlerRegistration = null;
        }
        if (closeHandler != null) {
            closeHandler.handle(null);
        }
        if (endHandler != null) {
            endHandler.handle(null);
        }
    }

    private void unregisterHandlers() {
        MessageConsumer binaryConsumer;
        MessageConsumer textConsumer;
        synchronized (this.conn) {
            binaryConsumer = this.binaryHandlerRegistration;
            textConsumer = this.textHandlerRegistration;
            this.binaryHandlerRegistration = null;
            this.textHandlerRegistration = null;
        }
        if (binaryConsumer != null) {
            binaryConsumer.unregister();
        }
        if (textConsumer != null) {
            textConsumer.unregister();
        }
    }

    synchronized void setMetric(Object metric) {
        this.metric = metric;
    }

    synchronized Object getMetric() {
        return this.metric;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkClosed();
                this.pending.handler(handler);
            } else {
                this.pending.handler(handler);
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkClosed();
                this.endHandler = handler;
            } else {
                this.endHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public S exceptionHandler(Handler<Throwable> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkClosed();
                this.exceptionHandler = handler;
            } else {
                this.exceptionHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase
    public S closeHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            checkClosed();
            this.closeHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public S drainHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            checkClosed();
            this.drainHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        if (!isClosed()) {
            this.pending.pause();
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        synchronized (this) {
            if (isClosed()) {
                Handler<Void> handler = this.endHandler;
                this.endHandler = null;
                if (handler != null) {
                    ContextInternal ctx = this.conn.getContext();
                    ctx.runOnContext(v -> {
                        handler.handle(null);
                    });
                }
            } else {
                this.pending.resume();
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        if (!isClosed()) {
            this.pending.fetch(amount);
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public S setWriteQueueMaxSize2(int maxSize) {
        synchronized (this.conn) {
            checkClosed();
            this.conn.doSetWriteQueueMaxSize(maxSize);
        }
        return this;
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public void end() {
        close();
    }

    @Override // io.vertx.core.http.WebSocketBase, io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        close(handler);
    }
}
