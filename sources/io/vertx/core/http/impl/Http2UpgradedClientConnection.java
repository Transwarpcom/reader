package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.GoAway;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.StreamPriority;
import io.vertx.core.http.impl.pool.ConnectionListener;
import io.vertx.core.impl.ContextInternal;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.security.cert.X509Certificate;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2UpgradedClientConnection.class */
public class Http2UpgradedClientConnection implements HttpClientConnection {
    private HttpClientImpl client;
    private HttpClientConnection current;
    private Handler<Void> closeHandler;
    private Handler<Void> shutdownHandler;
    private Handler<GoAway> goAwayHandler;
    private Handler<Throwable> exceptionHandler;
    private Handler<Buffer> pingHandler;
    private Handler<Http2Settings> remoteSettingsHandler;

    Http2UpgradedClientConnection(HttpClientImpl client, Http1xClientConnection connection) {
        this.client = client;
        this.current = connection;
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public ChannelHandlerContext channelHandlerContext() {
        return this.current.channelHandlerContext();
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public Channel channel() {
        return this.current.channel();
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection, io.vertx.core.http.HttpConnection
    public void close() {
        this.current.close();
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public Object metric() {
        return this.current.metric();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2UpgradedClientConnection$UpgradingStream.class */
    private class UpgradingStream implements HttpClientStream {
        private HttpClientRequestImpl request;
        private Http1xClientConnection conn;
        private HttpClientStream stream;

        UpgradingStream(HttpClientStream stream, Http1xClientConnection conn) {
            this.conn = conn;
            this.stream = stream;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public HttpClientConnection connection() {
            return Http2UpgradedClientConnection.this;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeHead(HttpMethod method, String rawMethod, String uri, MultiMap headers, String hostHeader, boolean chunked, ByteBuf buf, boolean end, StreamPriority priority, Handler<Void> continueHandler, Handler<AsyncResult<Void>> handler) {
            final ChannelPipeline pipeline = this.conn.channel().pipeline();
            HttpClientCodec httpCodec = (HttpClientCodec) pipeline.get(HttpClientCodec.class);
            VertxHttp2ClientUpgradeCodec upgradeCodec = new VertxHttp2ClientUpgradeCodec(Http2UpgradedClientConnection.this.client.getOptions().getInitialSettings()) { // from class: io.vertx.core.http.impl.Http2UpgradedClientConnection.UpgradingStream.1
                @Override // io.vertx.core.http.impl.VertxHttp2ClientUpgradeCodec, io.netty.handler.codec.http.HttpClientUpgradeHandler.UpgradeCodec
                public void upgradeTo(ChannelHandlerContext ctx, FullHttpResponse upgradeResponse) throws Exception {
                    ConnectionListener<HttpClientConnection> listener = UpgradingStream.this.conn.listener();
                    VertxHttp2ConnectionHandler<Http2ClientConnection> handler2 = Http2ClientConnection.createHttp2ConnectionHandler(Http2UpgradedClientConnection.this.client, UpgradingStream.this.conn.endpointMetric(), listener, UpgradingStream.this.conn.getContext(), Http2UpgradedClientConnection.this.current.metric(), (conn, concurrency) -> {
                        conn.upgradeStream(UpgradingStream.this.stream.metric(), ar -> {
                            UpgradingStream.this.conn.closeHandler((Handler<Void>) null);
                            UpgradingStream.this.conn.exceptionHandler((Handler<Throwable>) null);
                            if (ar.succeeded()) {
                                HttpClientStream upgradedStream = (HttpClientStream) ar.result();
                                upgradedStream.beginRequest(UpgradingStream.this.request);
                                Http2UpgradedClientConnection.this.current = conn;
                                conn.closeHandler(Http2UpgradedClientConnection.this.closeHandler);
                                conn.exceptionHandler(Http2UpgradedClientConnection.this.exceptionHandler);
                                conn.pingHandler(Http2UpgradedClientConnection.this.pingHandler);
                                conn.goAwayHandler(Http2UpgradedClientConnection.this.goAwayHandler);
                                conn.shutdownHandler(Http2UpgradedClientConnection.this.shutdownHandler);
                                conn.remoteSettingsHandler(Http2UpgradedClientConnection.this.remoteSettingsHandler);
                                listener.onConcurrencyChange(concurrency.longValue());
                                return;
                            }
                            ar.cause().printStackTrace();
                        });
                    });
                    UpgradingStream.this.conn.channel().pipeline().addLast(handler2);
                    handler2.clientUpgrade(ctx);
                }
            };
            HttpClientUpgradeHandler upgradeHandler = new HttpClientUpgradeHandler(httpCodec, upgradeCodec, 65536);
            pipeline.addAfter("codec", null, new ChannelInboundHandlerAdapter() { // from class: io.vertx.core.http.impl.Http2UpgradedClientConnection.UpgradingStream.1UpgradeRequestHandler
                @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                    super.userEventTriggered(ctx, evt);
                    ChannelPipeline pipeline2 = ctx.pipeline();
                    if (evt == HttpClientUpgradeHandler.UpgradeEvent.UPGRADE_SUCCESSFUL) {
                        pipeline2.remove(UpgradingStream.this.conn.handler());
                    }
                }

                @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    if (msg instanceof HttpResponse) {
                        pipeline.remove(this);
                        HttpResponse resp = (HttpResponse) msg;
                        if (resp.status() != HttpResponseStatus.SWITCHING_PROTOCOLS) {
                            resp.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
                        }
                    }
                    super.channelRead(ctx, msg);
                }
            });
            pipeline.addAfter("codec", null, upgradeHandler);
            this.stream.writeHead(method, rawMethod, uri, headers, hostHeader, chunked, buf, end, priority, continueHandler, handler);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public int id() {
            return 1;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public Object metric() {
            return this.stream.metric();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public HttpVersion version() {
            return HttpVersion.HTTP_2;
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public Context getContext() {
            return this.stream.getContext();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeBuffer(ByteBuf buf, boolean end, Handler<AsyncResult<Void>> handler) {
            this.stream.writeBuffer(buf, end, handler);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void writeFrame(int type, int flags, ByteBuf payload) {
            this.stream.writeFrame(type, flags, payload);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doSetWriteQueueMaxSize(int size) {
            this.stream.doSetWriteQueueMaxSize(size);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public boolean isNotWritable() {
            return this.stream.isNotWritable();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doPause() {
            this.stream.doPause();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void doFetch(long amount) {
            this.stream.doFetch(amount);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void reset(Throwable cause) {
            this.stream.reset(cause);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void beginRequest(HttpClientRequestImpl req) {
            this.request = req;
            this.stream.beginRequest(req);
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void endRequest() {
            this.stream.endRequest();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public NetSocket createNetSocket() {
            return this.stream.createNetSocket();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public StreamPriority priority() {
            return this.stream.priority();
        }

        @Override // io.vertx.core.http.impl.HttpClientStream
        public void updatePriority(StreamPriority streamPriority) {
            this.stream.updatePriority(streamPriority);
        }
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public void createStream(Handler<AsyncResult<HttpClientStream>> handler) {
        if (this.current instanceof Http1xClientConnection) {
            this.current.createStream(ar -> {
                if (ar.succeeded()) {
                    HttpClientStream stream = (HttpClientStream) ar.result();
                    UpgradingStream upgradingStream = new UpgradingStream(stream, (Http1xClientConnection) this.current);
                    handler.handle(Future.succeededFuture(upgradingStream));
                    return;
                }
                handler.handle(ar);
            });
        } else {
            this.current.createStream(handler);
        }
    }

    @Override // io.vertx.core.http.impl.HttpClientConnection
    public ContextInternal getContext() {
        return this.current.getContext();
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection closeHandler(Handler<Void> handler) {
        this.closeHandler = handler;
        this.current.closeHandler(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection exceptionHandler(Handler<Throwable> handler) {
        this.exceptionHandler = handler;
        this.current.exceptionHandler(handler);
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection remoteSettingsHandler(Handler<Http2Settings> handler) {
        if (this.current instanceof Http1xClientConnection) {
            this.remoteSettingsHandler = handler;
        } else {
            this.current.remoteSettingsHandler(handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection pingHandler(Handler<Buffer> handler) {
        if (this.current instanceof Http1xClientConnection) {
            this.pingHandler = handler;
        } else {
            this.current.pingHandler(handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection goAwayHandler(Handler<GoAway> handler) {
        if (this.current instanceof Http1xClientConnection) {
            this.goAwayHandler = handler;
        } else {
            this.current.goAwayHandler(handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdownHandler(Handler<Void> handler) {
        if (this.current instanceof Http1xClientConnection) {
            this.shutdownHandler = handler;
        } else {
            this.current.shutdownHandler(handler);
        }
        return this;
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection goAway(long errorCode, int lastStreamId, Buffer debugData) {
        return this.current.goAway(errorCode, lastStreamId, debugData);
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdown() {
        return this.current.shutdown();
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection shutdown(long timeoutMs) {
        return this.current.shutdown(timeoutMs);
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection updateSettings(Http2Settings settings) {
        return this.current.updateSettings(settings);
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection updateSettings(Http2Settings settings, Handler<AsyncResult<Void>> completionHandler) {
        return this.current.updateSettings(settings, completionHandler);
    }

    @Override // io.vertx.core.http.HttpConnection
    public Http2Settings settings() {
        return this.current.settings();
    }

    @Override // io.vertx.core.http.HttpConnection
    public Http2Settings remoteSettings() {
        return this.current.remoteSettings();
    }

    @Override // io.vertx.core.http.HttpConnection
    public HttpConnection ping(Buffer data, Handler<AsyncResult<Buffer>> pongHandler) {
        return this.current.ping(data, pongHandler);
    }

    @Override // io.vertx.core.http.HttpConnection
    public SocketAddress remoteAddress() {
        return this.current.remoteAddress();
    }

    @Override // io.vertx.core.http.HttpConnection
    public SocketAddress localAddress() {
        return this.current.localAddress();
    }

    @Override // io.vertx.core.http.HttpConnection
    public boolean isSsl() {
        return this.current.isSsl();
    }

    @Override // io.vertx.core.http.HttpConnection
    public SSLSession sslSession() {
        return this.current.sslSession();
    }

    @Override // io.vertx.core.http.HttpConnection
    public X509Certificate[] peerCertificateChain() throws SSLPeerUnverifiedException {
        return this.current.peerCertificateChain();
    }

    @Override // io.vertx.core.http.HttpConnection
    public String indicatedServerName() {
        return this.current.indicatedServerName();
    }
}
