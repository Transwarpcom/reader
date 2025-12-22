package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.impl.ConnectionBase;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/WebSocketTransport.class */
class WebSocketTransport extends BaseTransport {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) WebSocketTransport.class);

    WebSocketTransport(Vertx vertx, Router router, LocalMap<String, SockJSSession> sessions, SockJSHandlerOptions options, Handler<SockJSSocket> sockHandler) {
        super(vertx, sessions, options);
        router.getWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/websocket").handler(rc -> {
            HttpServerRequest req = rc.request();
            String connectionHeader = req.headers().get(HttpHeaders.CONNECTION);
            if (connectionHeader == null || !connectionHeader.toLowerCase().contains("upgrade")) {
                rc.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
                rc.response().end("Can \"Upgrade\" only to \"WebSocket\".");
                return;
            }
            ServerWebSocket ws = rc.request().upgrade();
            if (log.isTraceEnabled()) {
                log.trace("WS, handler");
            }
            SockJSSession session = new SockJSSession(vertx, sessions, rc, options.getHeartbeatInterval(), sockHandler);
            session.register(req, new WebSocketListener(ws, session));
        });
        router.getWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/websocket").handler(rc2 -> {
            if (log.isTraceEnabled()) {
                log.trace("WS, get: " + rc2.request().uri());
            }
            rc2.response().setStatusCode(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
            rc2.response().end("Can \"Upgrade\" only to \"WebSocket\".");
        });
        router.routeWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/websocket").handler(rc3 -> {
            if (log.isTraceEnabled()) {
                log.trace("WS, all: " + rc3.request().uri());
            }
            rc3.response().putHeader("Allow", "GET").setStatusCode(405).end();
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/WebSocketTransport$WebSocketListener.class */
    private static class WebSocketListener implements TransportListener {
        final ServerWebSocket ws;
        final SockJSSession session;
        boolean closed;

        WebSocketListener(ServerWebSocket ws, SockJSSession session) {
            this.ws = ws;
            this.session = session;
            ws.textMessageHandler(this::handleMessages);
            ws.closeHandler(v -> {
                this.closed = true;
                session.shutdown();
            });
            ws.exceptionHandler(t -> {
                this.closed = true;
                session.shutdown();
                session.handleException(t);
            });
        }

        private void handleMessages(String msgs) {
            if (!this.session.isClosed() && !msgs.equals("") && !msgs.equals("[]")) {
                if ((msgs.startsWith("[\"") && msgs.endsWith("\"]")) || (msgs.startsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE) && msgs.endsWith(OperatorName.SHOW_TEXT_LINE_AND_SPACE))) {
                    this.session.handleMessages(msgs);
                } else {
                    close();
                }
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sendFrame(String body, Handler<AsyncResult<Void>> handler) {
            if (WebSocketTransport.log.isTraceEnabled()) {
                WebSocketTransport.log.trace("WS, sending frame");
            }
            if (!this.closed) {
                this.ws.writeTextMessage(body, handler);
            } else if (handler != null) {
                handler.handle(Future.failedFuture(ConnectionBase.CLOSED_EXCEPTION));
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void close() {
            if (!this.closed) {
                this.ws.close();
                this.session.shutdown();
                this.closed = true;
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sessionClosed() {
            this.session.writeClosed(this);
            this.closed = true;
            this.session.context().runOnContext(v -> {
                this.ws.close();
            });
        }
    }
}
