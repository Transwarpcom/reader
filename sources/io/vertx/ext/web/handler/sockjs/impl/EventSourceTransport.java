package io.vertx.ext.web.handler.sockjs.impl;

import cn.hutool.core.text.StrPool;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.handler.sockjs.impl.BaseTransport;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/EventSourceTransport.class */
class EventSourceTransport extends BaseTransport {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) EventSourceTransport.class);

    EventSourceTransport(Vertx vertx, Router router, LocalMap<String, SockJSSession> sessions, SockJSHandlerOptions options, Handler<SockJSSocket> sockHandler) {
        super(vertx, sessions, options);
        router.getWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/eventsource").handler(rc -> {
            if (log.isTraceEnabled()) {
                log.trace("EventSource transport, get: " + rc.request().uri());
            }
            String sessionID = rc.request().getParam("param0");
            SockJSSession session = getSession(rc, options.getSessionTimeout(), options.getHeartbeatInterval(), sessionID, sockHandler);
            HttpServerRequest req = rc.request();
            session.register(req, new EventSourceListener(options.getMaxBytesStreaming(), rc, session));
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/EventSourceTransport$EventSourceListener.class */
    private class EventSourceListener extends BaseTransport.BaseListener {
        final int maxBytesStreaming;
        boolean headersWritten;
        int bytesSent;
        boolean closed;

        EventSourceListener(int maxBytesStreaming, RoutingContext rc, SockJSSession session) {
            super(rc, session);
            this.maxBytesStreaming = maxBytesStreaming;
            addCloseHandler(rc.response(), session);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sendFrame(String body, Handler<AsyncResult<Void>> handler) {
            if (EventSourceTransport.log.isTraceEnabled()) {
                EventSourceTransport.log.trace("EventSource, sending frame");
            }
            if (!this.headersWritten) {
                this.rc.response().putHeader("Content-Type", "text/event-stream");
                BaseTransport.setNoCacheHeaders(this.rc);
                BaseTransport.setJSESSIONID(EventSourceTransport.this.options, this.rc);
                this.rc.response().setChunked(true).write(StrPool.CRLF);
                this.headersWritten = true;
            }
            String sb = "data: " + body + "\r\n\r\n";
            Buffer buff = Buffer.buffer(sb);
            this.rc.response().write2(buff, handler);
            this.bytesSent += buff.length();
            if (this.bytesSent >= this.maxBytesStreaming) {
                if (EventSourceTransport.log.isTraceEnabled()) {
                    EventSourceTransport.log.trace("More than maxBytes sent so closing connection");
                }
                close();
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void close() {
            if (!this.closed) {
                try {
                    this.session.resetListener();
                    this.rc.response().end();
                    this.rc.response().close();
                } catch (IllegalStateException e) {
                }
                this.closed = true;
            }
        }
    }
}
