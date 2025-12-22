package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpStatusClass;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Headers;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.StreamResetException;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.streams.StreamBase;
import io.vertx.core.streams.WriteStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2ServerResponseImpl.class */
public class Http2ServerResponseImpl implements HttpServerResponse {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) Http2ServerResponseImpl.class);
    private final Http2ServerStream stream;
    private final ChannelHandlerContext ctx;
    private final Http2ServerConnection conn;
    private final boolean head;
    private final boolean push;
    private final String host;
    private Http2HeadersAdaptor headersMap;
    private Http2Headers trailers;
    private Http2HeadersAdaptor trailedMap;
    private boolean chunked;
    private boolean headWritten;
    private boolean ended;
    private boolean closed;
    private Map<String, ServerCookie> cookies;
    private String statusMessage;
    private Handler<Void> drainHandler;
    private Handler<Throwable> exceptionHandler;
    private Handler<Void> headersEndHandler;
    private Handler<Void> bodyEndHandler;
    private Handler<Void> closeHandler;
    private Handler<Void> endHandler;
    private NetSocket netSocket;
    private final Http2Headers headers = new DefaultHttp2Headers();
    private HttpResponseStatus status = HttpResponseStatus.OK;

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

    public Http2ServerResponseImpl(Http2ServerConnection conn, Http2ServerStream stream, HttpMethod method, boolean push, String contentEncoding, String host) {
        this.stream = stream;
        this.ctx = conn.handlerContext;
        this.conn = conn;
        this.head = method == HttpMethod.HEAD;
        this.push = push;
        this.host = host;
        if (contentEncoding != null) {
            putHeader(HttpHeaderNames.CONTENT_ENCODING, contentEncoding);
        }
    }

    boolean isPush() {
        return this.push;
    }

    void handleReset(long code) {
        handleException(new StreamResetException(code));
    }

    void handleException(Throwable cause) {
        Handler<Throwable> handler;
        synchronized (this.conn) {
            handler = this.exceptionHandler;
        }
        if (handler != null) {
            handler.handle(cause);
        }
    }

    void handleClose() {
        Handler<Throwable> exceptionHandler;
        Handler<Void> endHandler;
        Handler<Void> closeHandler;
        synchronized (this.conn) {
            boolean failed = !this.ended;
            this.closed = true;
            exceptionHandler = failed ? this.exceptionHandler : null;
            endHandler = failed ? this.endHandler : null;
            closeHandler = this.closeHandler;
        }
        if (exceptionHandler != null) {
            exceptionHandler.handle(ConnectionBase.CLOSED_EXCEPTION);
        }
        if (endHandler != null) {
            endHandler.handle(null);
        }
        if (closeHandler != null) {
            closeHandler.handle(null);
        }
    }

    private void checkHeadWritten() {
        if (this.headWritten) {
            throw new IllegalStateException("Header already sent");
        }
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
    public int getStatusCode() {
        int iCode;
        synchronized (this.conn) {
            iCode = this.status.code();
        }
        return iCode;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse setStatusCode(int statusCode) {
        if (statusCode < 0) {
            throw new IllegalArgumentException("code: " + statusCode + " (expected: 0+)");
        }
        synchronized (this.conn) {
            checkHeadWritten();
            this.status = HttpResponseStatus.valueOf(statusCode);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public String getStatusMessage() {
        synchronized (this.conn) {
            if (this.statusMessage == null) {
                return this.status.reasonPhrase();
            }
            return this.statusMessage;
        }
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse setStatusMessage(String statusMessage) {
        synchronized (this.conn) {
            checkHeadWritten();
            this.statusMessage = statusMessage;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse setChunked(boolean chunked) {
        synchronized (this.conn) {
            checkHeadWritten();
            this.chunked = true;
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public boolean isChunked() {
        boolean z;
        synchronized (this.conn) {
            z = this.chunked;
        }
        return z;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public MultiMap headers() {
        Http2HeadersAdaptor http2HeadersAdaptor;
        synchronized (this.conn) {
            if (this.headersMap == null) {
                this.headersMap = new Http2HeadersAdaptor(this.headers);
            }
            http2HeadersAdaptor = this.headersMap;
        }
        return http2HeadersAdaptor;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putHeader(String name, String value) {
        synchronized (this.conn) {
            checkHeadWritten();
            headers().set(name, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putHeader(CharSequence name, CharSequence value) {
        synchronized (this.conn) {
            checkHeadWritten();
            headers().set(name, value);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putHeader(String name, Iterable<String> values) {
        synchronized (this.conn) {
            checkHeadWritten();
            headers().mo1936set(name, values);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putHeader(CharSequence name, Iterable<CharSequence> values) {
        synchronized (this.conn) {
            checkHeadWritten();
            headers().mo1935set(name, values);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public MultiMap trailers() {
        Http2HeadersAdaptor http2HeadersAdaptor;
        synchronized (this.conn) {
            if (this.trailedMap == null) {
                DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers();
                this.trailers = defaultHttp2Headers;
                this.trailedMap = new Http2HeadersAdaptor(defaultHttp2Headers);
            }
            http2HeadersAdaptor = this.trailedMap;
        }
        return http2HeadersAdaptor;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse putTrailer(String name, String value) {
        synchronized (this.conn) {
            checkValid();
            trailers().set(name, value);
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
    public HttpServerResponse putTrailer(String name, Iterable<String> values) {
        synchronized (this.conn) {
            checkValid();
            trailers().mo1936set(name, values);
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

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse writeContinue() {
        synchronized (this.conn) {
            checkHeadWritten();
            this.stream.writeHeaders(new DefaultHttp2Headers().status("100"), false, null);
            this.ctx.flush();
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public HttpServerResponse write(Buffer chunk) {
        ByteBuf buf = chunk.getByteBuf();
        write(buf, false, (Handler<AsyncResult<Void>>) null);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        ByteBuf buf = chunk.getByteBuf();
        write(buf, false, handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(String chunk, String enc) {
        return write(Buffer.buffer(chunk, enc).getByteBuf());
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(String chunk, String enc, Handler<AsyncResult<Void>> handler) {
        write(Buffer.buffer(chunk, enc).getByteBuf(), false, handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(String chunk) {
        return write(Buffer.buffer(chunk).getByteBuf());
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse write(String chunk, Handler<AsyncResult<Void>> handler) {
        write(Buffer.buffer(chunk).getByteBuf(), false, handler);
        return this;
    }

    private Http2ServerResponseImpl write(ByteBuf chunk) {
        write(chunk, false, (Handler<AsyncResult<Void>>) null);
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
        end(Buffer.buffer(chunk, enc));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public void end(Buffer chunk) {
        end(chunk.getByteBuf(), (Handler<AsyncResult<Void>>) null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.core.http.HttpServerResponse
    public void end(Buffer chunk, Handler<AsyncResult<Void>> handler) {
        end(chunk.getByteBuf(), handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public void end() {
        end((ByteBuf) null, (Handler<AsyncResult<Void>>) null);
    }

    @Override // io.vertx.core.streams.WriteStream
    public void end(Handler<AsyncResult<Void>> handler) {
        end((ByteBuf) null, handler);
    }

    NetSocket netSocket() {
        checkValid();
        synchronized (this.conn) {
            if (this.netSocket != null) {
                return this.netSocket;
            }
            this.status = HttpResponseStatus.OK;
            if (!checkSendHeaders(false)) {
                throw new IllegalStateException("Response for CONNECT already sent");
            }
            this.ctx.flush();
            this.netSocket = this.conn.toNetSocket(this.stream);
            return this.netSocket;
        }
    }

    private void end(ByteBuf chunk, Handler<AsyncResult<Void>> handler) {
        write(chunk, true, handler);
    }

    private void sanitizeHeaders() {
        if (this.head || this.status == HttpResponseStatus.NOT_MODIFIED) {
            this.headers.remove(HttpHeaders.TRANSFER_ENCODING);
            return;
        }
        if (this.status == HttpResponseStatus.RESET_CONTENT) {
            this.headers.remove(HttpHeaders.TRANSFER_ENCODING);
            this.headers.set((Http2Headers) HttpHeaders.CONTENT_LENGTH, (CharSequence) "0");
        } else if (this.status.codeClass() == HttpStatusClass.INFORMATIONAL || this.status == HttpResponseStatus.NO_CONTENT) {
            this.headers.remove(HttpHeaders.TRANSFER_ENCODING);
            this.headers.remove(HttpHeaders.CONTENT_LENGTH);
        }
    }

    private boolean checkSendHeaders(boolean end) {
        if (!this.headWritten) {
            if (this.headersEndHandler != null) {
                this.headersEndHandler.handle(null);
            }
            if (this.cookies != null) {
                setCookies();
            }
            sanitizeHeaders();
            this.headWritten = true;
            this.headers.status(Integer.toString(this.status.code()));
            this.stream.writeHead(this.headers, end, null);
            if (end) {
                this.ctx.flush();
                return true;
            }
            return true;
        }
        return false;
    }

    private void setCookies() {
        for (ServerCookie cookie : this.cookies.values()) {
            if (cookie.isChanged()) {
                this.headers.add((Http2Headers) HttpHeaders.SET_COOKIE, (CharSequence) cookie.encode());
            }
        }
    }

    void write(ByteBuf chunk, boolean end, Handler<AsyncResult<Void>> handler) {
        Handler<Void> bodyEndHandler;
        Handler<Void> endHandler;
        synchronized (this.conn) {
            if (this.ended) {
                throw new IllegalStateException("Response has already been written");
            }
            this.ended |= end;
            boolean hasBody = false;
            if (chunk != null) {
                hasBody = true;
            } else {
                chunk = Unpooled.EMPTY_BUFFER;
            }
            if (end && !this.headWritten && !this.head && this.status != HttpResponseStatus.NOT_MODIFIED && !this.headers.contains(HttpHeaderNames.CONTENT_LENGTH)) {
                headers().set(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(chunk.readableBytes()));
            }
            boolean sent = checkSendHeaders(end && !hasBody && this.trailers == null);
            if (hasBody || (!sent && end)) {
                this.stream.writeData(chunk, end && this.trailers == null, handler);
            }
            if (end && this.trailers != null) {
                this.stream.writeHeaders(this.trailers, true, null);
            }
            bodyEndHandler = this.bodyEndHandler;
            endHandler = this.endHandler;
        }
        if (end) {
            if (bodyEndHandler != null) {
                bodyEndHandler.handle(null);
            }
            if (endHandler != null) {
                endHandler.handle(null);
            }
        }
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse writeCustomFrame(int type, int flags, Buffer payload) {
        synchronized (this.conn) {
            checkValid();
            checkSendHeaders(false);
            this.stream.writeFrame(type, flags, payload.getByteBuf());
            this.ctx.flush();
        }
        return this;
    }

    private void checkValid() {
        if (this.ended) {
            throw new IllegalStateException("Response has already been written");
        }
    }

    void writabilityChanged() {
        if (!this.ended && !writeQueueFull() && this.drainHandler != null) {
            this.drainHandler.handle(null);
        }
    }

    @Override // io.vertx.core.streams.WriteStream
    public boolean writeQueueFull() {
        boolean zIsNotWritable;
        synchronized (this.conn) {
            checkValid();
            zIsNotWritable = this.stream.isNotWritable();
        }
        return zIsNotWritable;
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    /* renamed from: setWriteQueueMaxSize */
    public WriteStream<Buffer> setWriteQueueMaxSize2(int maxSize) {
        synchronized (this.conn) {
            checkValid();
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse, io.vertx.core.streams.WriteStream
    public WriteStream<Buffer> drainHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkValid();
                this.drainHandler = handler;
            } else {
                this.drainHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse sendFile(String filename, long offset, long length) {
        return sendFile(filename, offset, length, null);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse sendFile(String filename, long offset, long length, Handler<AsyncResult<Void>> resultHandler) {
        Handler<AsyncResult<Void>> h;
        synchronized (this.conn) {
            checkValid();
            if (resultHandler != null) {
                Context resultCtx = this.stream.vertx.getOrCreateContext();
                h = ar -> {
                    resultCtx.runOnContext(v -> {
                        resultHandler.handle(ar);
                    });
                };
            } else {
                h = ar2 -> {
                };
            }
            File file_ = this.stream.vertx.resolveFile(filename);
            if (!file_.exists()) {
                if (resultHandler != null) {
                    h.handle(Future.failedFuture(new FileNotFoundException()));
                } else {
                    log.error("File not found: " + filename);
                }
                return this;
            }
            try {
                RandomAccessFile raf = new RandomAccessFile(file_, PDPageLabelRange.STYLE_ROMAN_LOWER);
                Throwable th = null;
                if (raf != null) {
                    if (0 != 0) {
                        try {
                            raf.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        raf.close();
                    }
                }
                long contentLength = Math.min(length, file_.length() - offset);
                FileSystem fs = this.conn.vertx().fileSystem();
                Handler<AsyncResult<Void>> handler = h;
                fs.open(filename, new OpenOptions().setCreate(false).setWrite(false), ar3 -> {
                    String contentType;
                    if (ar3.succeeded()) {
                        AsyncFile file = (AsyncFile) ar3.result();
                        if (this.headers.get(HttpHeaderNames.CONTENT_LENGTH) == null) {
                            putHeader(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(contentLength));
                        }
                        if (this.headers.get(HttpHeaderNames.CONTENT_TYPE) == null && (contentType = MimeMapping.getMimeTypeForFilename(filename)) != null) {
                            putHeader(HttpHeaderNames.CONTENT_TYPE, contentType);
                        }
                        checkSendHeaders(false);
                        file.setReadPos(offset);
                        file.setReadLength(contentLength);
                        file.pipeTo(this, handler);
                        return;
                    }
                    handler.handle(ar3.mapEmpty());
                });
                return this;
            } catch (IOException e) {
                if (resultHandler != null) {
                    h.handle(Future.failedFuture(e));
                } else {
                    log.error("Failed to send file", e);
                }
                return this;
            }
        }
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void close() {
        this.conn.close();
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public boolean ended() {
        boolean z;
        synchronized (this.conn) {
            z = this.ended;
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

    @Override // io.vertx.core.http.HttpServerResponse
    public long bytesWritten() {
        return this.stream.bytesWritten();
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public int streamId() {
        return this.stream.id();
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public void reset(long code) {
        checkValid();
        this.stream.writeReset(code);
        this.ctx.flush();
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(HttpMethod method, String host, String path, Handler<AsyncResult<HttpServerResponse>> handler) {
        return push(method, host, path, null, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(HttpMethod method, String path, MultiMap headers, Handler<AsyncResult<HttpServerResponse>> handler) {
        return push(method, null, path, headers, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(HttpMethod method, String host, String path, MultiMap headers, Handler<AsyncResult<HttpServerResponse>> handler) {
        synchronized (this.conn) {
            if (this.push) {
                throw new IllegalStateException("A push response cannot promise another push");
            }
            checkValid();
            this.conn.sendPush(this.stream.id(), host, method, headers, path, this.stream.priority(), handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse push(HttpMethod method, String path, Handler<AsyncResult<HttpServerResponse>> handler) {
        return push(method, this.host, path, handler);
    }

    @Override // io.vertx.core.http.HttpServerResponse
    public HttpServerResponse setStreamPriority(StreamPriority priority) {
        this.stream.updatePriority(priority);
        return this;
    }

    Map<String, ServerCookie> cookies() {
        if (this.cookies == null) {
            this.cookies = CookieImpl.extractCookies(this.stream.headers != null ? this.stream.headers.get(HttpHeaders.COOKIE) : null);
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
