package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpFrame;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.NetSocket;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.StreamBase;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientResponseImpl.class */
public class HttpClientResponseImpl implements HttpClientResponse {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HttpClientResponseImpl.class);
    private final HttpVersion version;
    private final int statusCode;
    private final String statusMessage;
    private final HttpClientRequestBase request;
    private final HttpConnection conn;
    private final HttpClientStream stream;
    private Handler<Buffer> dataHandler;
    private Handler<HttpFrame> customFrameHandler;
    private Handler<Void> endHandler;
    private Handler<Throwable> exceptionHandler;
    private Handler<StreamPriority> priorityHandler;
    private NetSocket netSocket;
    private MultiMap headers;
    private MultiMap trailers;
    private List<String> cookies;

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream
    /* renamed from: endHandler, reason: avoid collision after fix types in other method */
    public /* bridge */ /* synthetic */ ReadStream<Buffer> endHandler2(Handler handler) {
        return endHandler((Handler<Void>) handler);
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ ReadStream exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public /* bridge */ /* synthetic */ StreamBase exceptionHandler(Handler handler) {
        return exceptionHandler((Handler<Throwable>) handler);
    }

    HttpClientResponseImpl(HttpClientRequestBase request, HttpVersion version, HttpClientStream stream, int statusCode, String statusMessage, MultiMap headers) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.request = request;
        this.stream = stream;
        this.conn = stream.connection();
        this.headers = headers;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public HttpClientRequestBase request() {
        return this.request;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public HttpVersion version() {
        return this.version;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public int statusCode() {
        return this.statusCode;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public String statusMessage() {
        return this.statusMessage;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public MultiMap headers() {
        return this.headers;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public String getHeader(String headerName) {
        return this.headers.get(headerName);
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public String getHeader(CharSequence headerName) {
        return this.headers.get(headerName);
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public MultiMap trailers() {
        MultiMap multiMap;
        synchronized (this.conn) {
            if (this.trailers == null) {
                this.trailers = new HeadersAdaptor(new DefaultHttpHeaders());
            }
            multiMap = this.trailers;
        }
        return multiMap;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public String getTrailer(String trailerName) {
        if (this.trailers != null) {
            return this.trailers.get(trailerName);
        }
        return null;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public List<String> cookies() {
        List<String> list;
        synchronized (this.conn) {
            if (this.cookies == null) {
                this.cookies = new ArrayList();
                this.cookies.addAll(headers().getAll(HttpHeaders.SET_COOKIE));
                if (this.trailers != null) {
                    this.cookies.addAll(this.trailers.getAll(HttpHeaders.SET_COOKIE));
                }
            }
            list = this.cookies;
        }
        return list;
    }

    private void checkEnded() {
        if (this.trailers != null) {
            throw new IllegalStateException();
        }
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream
    /* renamed from: handler */
    public ReadStream<Buffer> handler2(Handler<Buffer> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.dataHandler = handler;
            } else {
                this.dataHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream
    public ReadStream<Buffer> endHandler(Handler<Void> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.endHandler = handler;
            } else {
                this.endHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream, io.vertx.core.streams.StreamBase
    public HttpClientResponse exceptionHandler(Handler<Throwable> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.exceptionHandler = handler;
            } else {
                this.exceptionHandler = handler;
            }
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream
    /* renamed from: pause */
    public ReadStream<Buffer> pause2() {
        this.stream.doPause();
        return this;
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream
    /* renamed from: resume */
    public ReadStream<Buffer> resume2() {
        return fetch2(Long.MAX_VALUE);
    }

    @Override // io.vertx.core.http.HttpClientResponse, io.vertx.core.streams.ReadStream
    /* renamed from: fetch */
    public ReadStream<Buffer> fetch2(long amount) {
        this.stream.doFetch(amount);
        return this;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public HttpClientResponse bodyHandler(Handler<Buffer> handler) {
        if (handler != null) {
            BodyHandler bodyHandler = new BodyHandler();
            handler2((Handler<Buffer>) bodyHandler);
            endHandler(v -> {
                bodyHandler.notifyHandler(handler);
            });
        } else {
            handler2((Handler<Buffer>) null);
            endHandler((Handler<Void>) null);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public HttpClientResponse customFrameHandler(Handler<HttpFrame> handler) {
        synchronized (this.conn) {
            if (this.endHandler != null) {
                checkEnded();
            }
            this.customFrameHandler = handler;
        }
        return this;
    }

    void handleUnknownFrame(HttpFrame frame) {
        synchronized (this.conn) {
            if (this.customFrameHandler != null) {
                try {
                    this.customFrameHandler.handle(frame);
                } catch (Throwable t) {
                    handleException(t);
                }
            }
        }
    }

    void handleChunk(Buffer data) {
        Handler<Buffer> handler;
        this.request.dataReceived();
        synchronized (this.conn) {
            handler = this.dataHandler;
        }
        if (handler != null) {
            try {
                handler.handle(data);
            } catch (Throwable t) {
                handleException(t);
            }
        }
    }

    void handleEnd(MultiMap trailers) {
        Handler<Void> handler;
        synchronized (this.conn) {
            this.trailers = trailers;
            handler = this.endHandler;
            this.endHandler = null;
        }
        if (handler != null) {
            try {
                handler.handle(null);
            } catch (Throwable t) {
                handleException(t);
            }
        }
    }

    void handleException(Throwable e) {
        Handler<Throwable> handler;
        synchronized (this.conn) {
            handler = this.exceptionHandler;
            if (handler == null) {
                Logger logger = log;
                logger.getClass();
                handler = (v1) -> {
                    r0.error(v1);
                };
            }
        }
        handler.handle(e);
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public NetSocket netSocket() {
        NetSocket netSocket;
        synchronized (this.conn) {
            if (this.netSocket == null) {
                this.netSocket = this.stream.createNetSocket();
            }
            netSocket = this.netSocket;
        }
        return netSocket;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpClientResponseImpl$BodyHandler.class */
    private static final class BodyHandler implements Handler<Buffer> {
        private Buffer body;

        private BodyHandler() {
        }

        @Override // io.vertx.core.Handler
        public void handle(Buffer event) {
            body().appendBuffer(event);
        }

        private Buffer body() {
            if (this.body == null) {
                this.body = Buffer.buffer();
            }
            return this.body;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void notifyHandler(Handler<Buffer> bodyHandler) {
            bodyHandler.handle(body());
            this.body = null;
        }
    }

    @Override // io.vertx.core.http.HttpClientResponse
    public HttpClientResponse streamPriorityHandler(Handler<StreamPriority> handler) {
        synchronized (this.conn) {
            if (handler != null) {
                checkEnded();
                this.priorityHandler = handler;
            } else {
                this.priorityHandler = handler;
            }
        }
        return this;
    }

    void handlePriorityChange(StreamPriority streamPriority) {
        synchronized (this.conn) {
            Handler<StreamPriority> handler = this.priorityHandler;
            if (handler == null) {
                return;
            }
            handler.handle(streamPriority);
        }
    }
}
