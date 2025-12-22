package io.vertx.core.http.impl;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.spi.metrics.HttpServerMetrics;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/WebSocketRequestHandler.class */
public class WebSocketRequestHandler implements Handler<HttpServerRequest> {
    private final HttpServerMetrics metrics;
    private final HttpHandlers handlers;

    WebSocketRequestHandler(HttpServerMetrics metrics, HttpHandlers handlers) {
        this.metrics = metrics;
        this.handlers = handlers;
    }

    @Override // io.vertx.core.Handler
    public void handle(HttpServerRequest req) {
        if (req.headers().contains(HttpHeaders.UPGRADE, HttpHeaders.WEBSOCKET, true) || this.handlers.requestHandler == null) {
            handle((HttpServerRequestImpl) req);
        } else {
            this.handlers.requestHandler.handle(req);
        }
    }

    private void handle(HttpServerRequestImpl req) {
        Buffer body = Buffer.buffer();
        boolean[] failed = new boolean[1];
        req.handler2(buff -> {
            if (!failed[0]) {
                body.appendBuffer(buff);
                if (body.length() > 8192) {
                    failed[0] = true;
                    HttpServerResponseImpl resp = req.response();
                    resp.setStatusCode(413).end();
                    resp.close();
                }
            }
        });
        req.endHandler(v -> {
            if (!failed[0]) {
                handle(req, body);
            }
        });
    }

    private void handle(HttpServerRequestImpl req, Buffer body) {
        HttpRequest nettyReq = req.nettyRequest();
        req.setRequest(new DefaultFullHttpRequest(nettyReq.protocolVersion(), nettyReq.method(), nettyReq.uri(), body.getByteBuf(), nettyReq.headers(), EmptyHttpHeaders.INSTANCE));
        if (this.handlers.wsHandler != null) {
            ServerWebSocketImpl ws = ((Http1xServerConnection) req.connection()).createWebSocket(req);
            if (ws == null) {
                return;
            }
            this.handlers.wsHandler.handle(ws);
            ws.tryHandshake(101);
            return;
        }
        this.handlers.requestHandler.handle(req);
    }
}
