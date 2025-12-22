package io.vertx.core.http.impl;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.DeflateFrameServerExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateServerExtensionHandshaker;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpConnection;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.net.impl.ConnectionBase;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpHandlers.class */
public class HttpHandlers implements Handler<HttpServerConnection> {
    final HttpServerImpl server;
    final Handler<HttpServerRequest> requestHandler;
    final Handler<ServerWebSocket> wsHandler;
    final Handler<HttpConnection> connectionHandler;
    final Handler<Throwable> exceptionHandler;

    public HttpHandlers(HttpServerImpl server, Handler<HttpServerRequest> requestHandler, Handler<ServerWebSocket> wsHandler, Handler<HttpConnection> connectionHandler, Handler<Throwable> exceptionHandler) {
        this.server = server;
        this.requestHandler = requestHandler;
        this.wsHandler = wsHandler;
        this.connectionHandler = connectionHandler;
        this.exceptionHandler = exceptionHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.vertx.core.Handler
    public void handle(HttpServerConnection httpServerConnection) {
        this.server.connectionMap.put(httpServerConnection.channel(), (ConnectionBase) httpServerConnection);
        httpServerConnection.channel().closeFuture().addListener2(fut -> {
            this.server.connectionMap.remove(httpServerConnection.channel());
        });
        Handler<HttpServerRequest> requestHandler = this.requestHandler;
        if (!HttpServerImpl.DISABLE_WEBSOCKETS && (httpServerConnection instanceof Http1xServerConnection)) {
            requestHandler = new WebSocketRequestHandler(this.server.metrics, this);
            Http1xServerConnection c = (Http1xServerConnection) httpServerConnection;
            initializeWebsocketExtensions(c.channelHandlerContext().pipeline());
        }
        httpServerConnection.exceptionHandler(this.exceptionHandler);
        httpServerConnection.handler(requestHandler);
        if (this.connectionHandler != null) {
            this.connectionHandler.handle(httpServerConnection);
        }
    }

    private void initializeWebsocketExtensions(ChannelPipeline pipeline) {
        ArrayList<WebSocketServerExtensionHandshaker> extensionHandshakers = new ArrayList<>();
        if (this.server.options.getPerFrameWebsocketCompressionSupported()) {
            extensionHandshakers.add(new DeflateFrameServerExtensionHandshaker(this.server.options.getWebsocketCompressionLevel()));
        }
        if (this.server.options.getPerMessageWebsocketCompressionSupported()) {
            extensionHandshakers.add(new PerMessageDeflateServerExtensionHandshaker(this.server.options.getWebsocketCompressionLevel(), ZlibCodecFactory.isSupportingWindowSizeAndMemLevel(), 15, this.server.options.getWebsocketAllowServerNoContext(), this.server.options.getWebsocketPreferredClientNoContext()));
        }
        if (!extensionHandshakers.isEmpty()) {
            WebSocketServerExtensionHandler extensionHandler = new WebSocketServerExtensionHandler((WebSocketServerExtensionHandshaker[]) extensionHandshakers.toArray(new WebSocketServerExtensionHandshaker[extensionHandshakers.size()]));
            pipeline.addBefore("handler", "websocketExtensionHandler", extensionHandler);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HttpHandlers that = (HttpHandlers) o;
        return Objects.equals(this.requestHandler, that.requestHandler) && Objects.equals(this.wsHandler, that.wsHandler) && Objects.equals(this.connectionHandler, that.connectionHandler) && Objects.equals(this.exceptionHandler, that.exceptionHandler);
    }

    public int hashCode() {
        int result = 0;
        if (this.requestHandler != null) {
            result = (31 * 0) + this.requestHandler.hashCode();
        }
        if (this.wsHandler != null) {
            result = (31 * result) + this.wsHandler.hashCode();
        }
        if (this.connectionHandler != null) {
            result = (31 * result) + this.connectionHandler.hashCode();
        }
        if (this.exceptionHandler != null) {
            result = (31 * result) + this.exceptionHandler.hashCode();
        }
        return result;
    }
}
