package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.impl.headers.VertxHttpHeaders;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.spi.metrics.Metrics;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpServerResponseImpl.class */
public class HttpServerResponseImpl implements HttpServerResponse {
    private static final Buffer EMPTY_BUFFER = Buffer.buffer(Unpooled.EMPTY_BUFFER);
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpServerResponseImpl.class);
    private static final String RESPONSE_WRITTEN = "Response has already been written";
    private final VertxInternal vertx;
    private final HttpRequest request;
    private final Http1xServerConnection conn;
    private final HttpVersion version;
    private final boolean keepAlive;
    private final boolean head;
    private final Object requestMetric;
    private boolean headWritten;
    private boolean written;
    private Handler<Void> drainHandler;
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> closeHandler;
    private Handler<Void> endHandler;
    private Handler<Void> headersEndHandler;
    private Handler<Void> bodyEndHandler;
    private boolean closed;
    private Map<String, ServerCookie> cookies;
    private MultiMap trailers;
    private String statusMessage;
    private long bytesWritten;
    private NetSocket netSocket;
    private HttpHeaders trailingHeaders = EmptyHttpHeaders.INSTANCE;
    private final VertxHttpHeaders headers = new VertxHttpHeaders();
    private HttpResponseStatus status = HttpResponseStatus.OK;

    @Override // io.vertx.core.http.HttpServerResponse
    public /* bridge */ /* synthetic */ HttpServerResponse putTrailer(String str, Iterable iterable) {
        return putTrailer(str, (Iterable<String>) iterable);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public /* bridge */ /* synthetic */ HttpServerResponse putHeader(String str, Iterable iterable) {
        return putHeader(str, (Iterable<String>) iterable);
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    /* renamed from: drainHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ WriteStream<Buffer> drainHandler2(Handler handler) {
        return drainHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ void end(Buffer buffer, Handler handler) {
        end(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public /* bridge */ /* synthetic */ WriteStream<Buffer> write(Buffer buffer, Handler handler) {
        return write(buffer, (Handler<AsyncResult<Void>>) handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ WriteStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    HttpServerResponseImpl(VertxInternal vertx, Http1xServerConnection conn, HttpRequest request, Object requestMetric) {
        this.vertx = vertx;
        this.conn = conn;
        this.version = request.protocolVersion();
        this.request = request;
        this.requestMetric = requestMetric;
        this.keepAlive = (this.version == HttpVersion.HTTP_1_1 && !request.headers().contains(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.CLOSE, true)) || (this.version == HttpVersion.HTTP_1_0 && request.headers().contains(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.KEEP_ALIVE, true));
        this.head = request.method() == HttpMethod.HEAD;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public MultiMap headers() {
        return this.headers;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public MultiMap trailers() {
        if (this.trailers == null) {
            VertxHttpHeaders v = new VertxHttpHeaders();
            this.trailers = v;
            this.trailingHeaders = v;
        }
        return this.trailers;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public int getStatusCode() {
        return this.status.code();
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse setStatusCode(int statusCode) {
        this.status = this.statusMessage != null ? new HttpResponseStatus(statusCode, this.statusMessage) : HttpResponseStatus.valueOf(statusCode);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public String getStatusMessage() {
        return this.status.reasonPhrase();
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse setStatusMessage(String statusMessage) {
        synchronized (this.conn) {
            this.statusMessage = statusMessage;
            this.status = new HttpResponseStatus(this.status.code(), statusMessage);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl setChunked(boolean chunked) {
        synchronized (this.conn) {
            checkValid();
            if (this.version != HttpVersion.HTTP_1_0) {
                this.headers.set(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING, (CharSequence) (chunked ? HttpHeaders.Values.CHUNKED : null));
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public boolean isChunked() {
        boolean zContains;
        synchronized (this.conn) {
            zContains = this.headers.contains(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING, io.vertx.core.http.HttpHeaders.CHUNKED, true);
        }
        return zContains;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl putHeader(String key, String value) {
        synchronized (this.conn) {
            checkValid();
            this.headers.set(key, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl putHeader(String key, Iterable<String> values) {
        synchronized (this.conn) {
            checkValid();
            this.headers.mo1936set(key, (Iterable) values);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl putTrailer(String key, String value) {
        synchronized (this.conn) {
            checkValid();
            trailers().set(key, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl putTrailer(String key, Iterable<String> values) {
        synchronized (this.conn) {
            checkValid();
            trailers().mo1936set(key, values);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putHeader(CharSequence name, CharSequence value) {
        synchronized (this.conn) {
            checkValid();
            this.headers.set(name, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putHeader(CharSequence name, Iterable<CharSequence> values) {
        synchronized (this.conn) {
            checkValid();
            this.headers.mo1935set(name, (Iterable) values);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putTrailer(CharSequence name, CharSequence value) {
        synchronized (this.conn) {
            checkValid();
            trailers().set(name, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putTrailer(CharSequence name, Iterable<CharSequence> value) {
        synchronized (this.conn) {
            checkValid();
            trailers().mo1935set(name, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public WriteStream<Buffer> setWriteQueueMaxSize2(int size) {
        synchronized (this.conn) {
            checkValid();
            this.conn.doSetWriteQueueMaxSize(size);
        }
        return this;
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        boolean zIsNotWritable;
        synchronized (this.conn) {
            checkValid();
            zIsNotWritable = this.conn.isNotWritable();
        }
        return zIsNotWritable;
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkValid();
                this.drainHandler = handler;
                this.conn.getContext().runOnContext(v -> {
                    this.conn.handleInterestedOpsChanged();
                });
            } else {
                this.drainHandler = handler;
                this.conn.getContext().runOnContext(v2 -> {
                    this.conn.handleInterestedOpsChanged();
                });
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream, io.vertx.core.streams.StreamBase
    public HttpServerResponse exceptionHandler(Handler<Throwable> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkValid();
                this.exceptionHandler = handler;
            } else {
                this.exceptionHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse closeHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkValid();
                this.closeHandler = handler;
            } else {
                this.closeHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse endHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkValid();
                this.endHandler = handler;
            } else {
                this.endHandler = handler;
            }
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public HttpServerResponseImpl write(Buffer chunk) {
        ByteBuf buf = chunk.getByteBuf();
        return write(buf, this.conn.voidPromise);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        return write(chunk.getByteBuf(), this.conn.toPromise(handler));
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl write(String chunk, String enc) {
        return write(Buffer.buffer(chunk, enc).getByteBuf(), this.conn.voidPromise);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        return write(Buffer.buffer(chunk, enc).getByteBuf(), this.conn.toPromise(handler));
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl write(String chunk) {
        return write(Buffer.buffer(chunk).getByteBuf(), this.conn.voidPromise);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(String chunk, Handler<AsyncResult<Void>> handler) {
        return write(Buffer.buffer(chunk).getByteBuf(), this.conn.toPromise(handler));
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse writeContinue() {
        this.conn.write100Continue();
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void end(String chunk) {
        end(Buffer.buffer(chunk));
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void end(String chunk, Handler<AsyncResult<Void>> handler) {
        end(Buffer.buffer(chunk), handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void end(String chunk, String enc) {
        end(Buffer.buffer(chunk, enc));
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void end(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        end(Buffer.buffer(chunk, enc), handler);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public void end(Buffer chunk) {
        end(chunk, this.conn.voidPromise);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse
    public void end(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        end(chunk, this.conn.toPromise(handler));
    }

    private void end(Buffer chunk, ChannelPromise promise) {
        HttpObject msg;
        synchronized (this.conn) {
            if (this.written) {
                throw new IllegalStateException(RESPONSE_WRITTEN);
            }
            ByteBuf data = chunk.getByteBuf();
            this.bytesWritten += data.readableBytes();
            if (!this.headWritten) {
                prepareHeaders(this.bytesWritten);
                msg = new AssembledFullHttpResponse(this.head, this.version, this.status, this.headers, data, this.trailingHeaders);
            } else {
                msg = new AssembledLastHttpContent(data, this.trailingHeaders);
            }
            this.conn.writeToChannel(msg, promise);
            this.written = true;
            this.conn.responseComplete();
            if (this.bodyEndHandler != null) {
                this.bodyEndHandler.handle(null);
            }
            if (!this.closed && this.endHandler != null) {
                this.endHandler.handle(null);
            }
            if (!this.keepAlive) {
                closeConnAfterWrite();
                this.closed = true;
            }
        }
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void close() {
        synchronized (this.conn) {
            if (!this.closed) {
                if (this.headWritten) {
                    closeConnAfterWrite();
                } else {
                    this.conn.close();
                }
                this.closed = true;
            }
        }
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public void end() {
        end(EMPTY_BUFFER);
    }

    @Override // io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        end(EMPTY_BUFFER, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponseImpl sendFile(String filename, long offset, long length) {
        doSendFile(filename, offset, length, null);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse sendFile(String filename, long start, long end, Handler<AsyncResult<Void>> resultHandler) {
        doSendFile(filename, start, end, resultHandler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public boolean ended() {
        boolean z;
        synchronized (this.conn) {
            z = this.written;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public boolean closed() {
        boolean z;
        synchronized (this.conn) {
            z = this.closed;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public boolean headWritten() {
        boolean z;
        synchronized (this.conn) {
            z = this.headWritten;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public long bytesWritten() {
        long j;
        synchronized (this.conn) {
            j = this.bytesWritten;
        }
        return j;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse headersEndHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            this.headersEndHandler = handler;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse bodyEndHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            this.bodyEndHandler = handler;
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x010d A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x0008, B:6:0x0013, B:7:0x001c, B:8:0x001d, B:12:0x0036, B:15:0x006d, B:13:0x0052, B:17:0x006f, B:19:0x0090, B:21:0x009b, B:22:0x00a8, B:23:0x00b3, B:37:0x0139, B:38:0x015d, B:27:0x00fe, B:32:0x010d, B:35:0x0137, B:33:0x012b), top: B:46:0x0008, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x012b A[Catch: all -> 0x0161, TryCatch #0 {, blocks: (B:4:0x0008, B:6:0x0013, B:7:0x001c, B:8:0x001d, B:12:0x0036, B:15:0x006d, B:13:0x0052, B:17:0x006f, B:19:0x0090, B:21:0x009b, B:22:0x00a8, B:23:0x00b3, B:37:0x0139, B:38:0x015d, B:27:0x00fe, B:32:0x010d, B:35:0x0137, B:33:0x012b), top: B:46:0x0008, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void doSendFile(java.lang.String r9, long r10, long r12, io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> r14) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.http.impl.HttpServerResponseImpl.doSendFile(java.lang.String, long, long, io.vertx.core.Handler):void");
    }

    private void closeConnAfterWrite() {
        ChannelPromise channelFuture = this.conn.channelFuture();
        this.conn.writeToChannel(Unpooled.EMPTY_BUFFER, channelFuture);
        channelFuture.addListener2(fut -> {
            this.conn.close();
        });
    }

    void handleDrained() {
        synchronized (this.conn) {
            if (this.drainHandler != null) {
                this.drainHandler.handle(null);
            }
        }
    }

    void handleException(Throwable t) {
        Handler<Throwable> handler;
        if (t == Http1xServerConnection.CLOSED_EXCEPTION) {
            handleClosed();
            return;
        }
        synchronized (this.conn) {
            handler = this.exceptionHandler;
        }
        if (handler != null) {
            handler.handle(t);
        }
    }

    private void handleClosed() {
        synchronized (this.conn) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Handler<Throwable> exceptionHandler = this.written ? null : this.exceptionHandler;
            Handler<Void> endHandler = this.written ? null : this.endHandler;
            Handler<Void> closedHandler = this.closeHandler;
            if (exceptionHandler != null) {
                exceptionHandler.handle(ConnectionBase.CLOSED_EXCEPTION);
            }
            if (endHandler != null) {
                endHandler.handle(null);
            }
            if (closedHandler != null) {
                closedHandler.handle(null);
            }
        }
    }

    private void checkValid() {
        if (this.written) {
            throw new IllegalStateException(RESPONSE_WRITTEN);
        }
    }

    private void prepareHeaders(long contentLength) {
        if (this.version == HttpVersion.HTTP_1_0 && this.keepAlive) {
            this.headers.set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.KEEP_ALIVE);
        } else if (this.version == HttpVersion.HTTP_1_1 && !this.keepAlive) {
            this.headers.set(io.vertx.core.http.HttpHeaders.CONNECTION, io.vertx.core.http.HttpHeaders.CLOSE);
        }
        if (this.head || this.status == HttpResponseStatus.NOT_MODIFIED) {
            this.headers.mo1933remove(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING);
        } else if (!this.headers.contains(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING) && !this.headers.contains(io.vertx.core.http.HttpHeaders.CONTENT_LENGTH) && contentLength >= 0) {
            String value = contentLength == 0 ? "0" : String.valueOf(contentLength);
            this.headers.set(io.vertx.core.http.HttpHeaders.CONTENT_LENGTH, (CharSequence) value);
        }
        if (this.headersEndHandler != null) {
            this.headersEndHandler.handle(null);
        }
        if (this.cookies != null) {
            setCookies();
        }
        if (Metrics.METRICS_ENABLED) {
            reportResponseBegin();
        }
        this.headWritten = true;
    }

    private void setCookies() {
        for (ServerCookie cookie : this.cookies.values()) {
            if (cookie.isChanged()) {
                this.headers.add(io.vertx.core.http.HttpHeaders.SET_COOKIE, (CharSequence) cookie.encode());
            }
        }
    }

    private void reportResponseBegin() {
        if (this.conn.metrics != null) {
            this.conn.metrics.responseBegin(this.requestMetric, this);
        }
    }

    private HttpServerResponseImpl write(ByteBuf chunk, ChannelPromise promise) {
        HttpObject msg;
        synchronized (this.conn) {
            if (this.written) {
                throw new IllegalStateException(RESPONSE_WRITTEN);
            }
            if (!this.headWritten && !this.headers.contains(io.vertx.core.http.HttpHeaders.TRANSFER_ENCODING) && !this.headers.contains(io.vertx.core.http.HttpHeaders.CONTENT_LENGTH) && this.version != HttpVersion.HTTP_1_0) {
                throw new IllegalStateException("You must set the Content-Length header to be the total size of the message body BEFORE sending any data if you are not using HTTP chunked encoding.");
            }
            this.bytesWritten += chunk.readableBytes();
            if (!this.headWritten) {
                prepareHeaders(-1L);
                msg = new AssembledHttpResponse(this.head, this.version, this.status, this.headers, chunk);
            } else {
                msg = new DefaultHttpContent(chunk);
            }
            this.conn.writeToChannel(msg, promise);
        }
        return this;
    }

    NetSocket netSocket(boolean isConnect) {
        checkValid();
        if (this.netSocket == null) {
            if (isConnect) {
                if (this.headWritten) {
                    throw new IllegalStateException("Response for CONNECT already sent");
                }
                this.status = HttpResponseStatus.OK;
                prepareHeaders(-1L);
                this.conn.writeToChannel(new AssembledHttpResponse(this.head, this.version, this.status, this.headers));
            }
            this.written = true;
            this.netSocket = this.conn.createNetSocket();
        }
        return this.netSocket;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public int streamId() {
        return -1;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void reset(long code) {
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(io.vertx.core.http.HttpMethod method, String path, MultiMap headers, Handler<AsyncResult<HttpServerResponse>> handler) {
        return push(method, null, path, headers, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(io.vertx.core.http.HttpMethod method, String host, String path, Handler<AsyncResult<HttpServerResponse>> handler) {
        return push(method, path, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(io.vertx.core.http.HttpMethod method, String path, Handler<AsyncResult<HttpServerResponse>> handler) {
        return push(method, path, null, null, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(io.vertx.core.http.HttpMethod method, String host, String path, MultiMap headers, Handler<AsyncResult<HttpServerResponse>> handler) {
        handler.handle(Future.failedFuture("Push promise is only supported with HTTP2"));
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse writeCustomFrame(int type, int flags, Buffer payload) {
        return this;
    }

    Map<String, ServerCookie> cookies() {
        if (this.cookies == null) {
            this.cookies = CookieImpl.extractCookies(this.request.headers().get(io.vertx.core.http.HttpHeaders.COOKIE));
        }
        return this.cookies;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse addCookie(Cookie cookie) {
        cookies().put(cookie.getName(), (ServerCookie) cookie);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public Cookie removeCookie(String name, boolean invalidate) {
        return CookieImpl.removeCookie(cookies(), name, invalidate);
    }
}
