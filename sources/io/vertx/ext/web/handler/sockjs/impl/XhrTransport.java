package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.handler.sockjs.impl.BaseTransport;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/XhrTransport.class */
class XhrTransport extends BaseTransport {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) XhrTransport.class);
    private static final Buffer H_BLOCK;

    static {
        byte[] bytes = new byte[2049];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = 104;
        }
        bytes[bytes.length - 1] = 10;
        H_BLOCK = Buffer.buffer(bytes);
    }

    XhrTransport(Vertx vertx, Router router, LocalMap<String, SockJSSession> sessions, SockJSHandlerOptions options, Handler<SockJSSocket> sockHandler) {
        super(vertx, sessions, options);
        String xhrRE = "\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/xhr";
        String xhrStreamRE = "\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/xhr_streaming";
        Handler<RoutingContext> xhrOptionsHandler = createCORSOptionsHandler(options, "OPTIONS, POST");
        router.optionsWithRegex(xhrRE).handler(xhrOptionsHandler);
        router.optionsWithRegex(xhrStreamRE).handler(xhrOptionsHandler);
        registerHandler(router, sockHandler, xhrRE, false, options);
        registerHandler(router, sockHandler, xhrStreamRE, true, options);
        router.optionsWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/xhr_send").handler(xhrOptionsHandler);
        router.postWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/xhr_send").handler(rc -> {
            if (log.isTraceEnabled()) {
                log.trace("XHR send, post, " + rc.request().uri());
            }
            String sessionID = rc.request().getParam("param0");
            SockJSSession session = (SockJSSession) sessions.get(sessionID);
            if (session != null && !session.isClosed()) {
                handleSend(rc, session);
                return;
            }
            rc.response().setStatusCode(404);
            setJSESSIONID(options, rc);
            rc.response().end();
        });
    }

    private void registerHandler(Router router, Handler<SockJSSocket> sockHandler, String re, boolean streaming, SockJSHandlerOptions options) {
        router.postWithRegex(re).handler(rc -> {
            if (log.isTraceEnabled()) {
                log.trace("XHR, post, " + rc.request().uri());
            }
            setNoCacheHeaders(rc);
            String sessionID = rc.request().getParam("param0");
            SockJSSession session = getSession(rc, options.getSessionTimeout(), options.getHeartbeatInterval(), sessionID, sockHandler);
            HttpServerRequest req = rc.request();
            session.register(req, streaming ? new XhrStreamingListener(options.getMaxBytesStreaming(), rc, session) : new XhrPollingListener(rc, session));
        });
    }

    private void handleSend(RoutingContext rc, SockJSSession session) {
        Buffer body = rc.getBody();
        if (body != null) {
            handleSendMessage(rc, session, body);
        } else if (rc.request().isEnded()) {
            log.error("Request ended before SockJS handler could read the body. Do you have an asynchronous request handler before the SockJS handler? If so, add a BodyHandler before the SockJS handler (see the docs).");
            rc.fail(500);
        } else {
            rc.request().bodyHandler(buff -> {
                handleSendMessage(rc, session, buff);
            });
        }
    }

    private void handleSendMessage(RoutingContext rc, SockJSSession session, Buffer body) {
        String msgs = body.toString();
        if (msgs.equals("")) {
            rc.response().setStatusCode(500);
            rc.response().end("Payload expected.");
            return;
        }
        if (!session.handleMessages(msgs)) {
            sendInvalidJSON(rc.response());
        } else {
            rc.response().putHeader("Content-Type", "text/plain; charset=UTF-8");
            setNoCacheHeaders(rc);
            setJSESSIONID(this.options, rc);
            setCORS(rc);
            rc.response().setStatusCode(204);
            rc.response().end();
        }
        if (log.isTraceEnabled()) {
            log.trace("XHR send processed ok");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/XhrTransport$BaseXhrListener.class */
    private abstract class BaseXhrListener extends BaseTransport.BaseListener {
        boolean headersWritten;

        BaseXhrListener(RoutingContext rc, SockJSSession session) {
            super(rc, session);
        }

        final void beforeSend() {
            if (XhrTransport.log.isTraceEnabled()) {
                XhrTransport.log.trace("XHR sending frame");
            }
            if (!this.headersWritten) {
                HttpServerResponse resp = this.rc.response();
                resp.putHeader("Content-Type", "application/javascript; charset=UTF-8");
                BaseTransport.setJSESSIONID(XhrTransport.this.options, this.rc);
                BaseTransport.setCORS(this.rc);
                if (this.rc.request().version() != HttpVersion.HTTP_1_0) {
                    resp.setChunked(true);
                }
                this.headersWritten = true;
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void close() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/XhrTransport$XhrPollingListener.class */
    private class XhrPollingListener extends BaseXhrListener {
        XhrPollingListener(RoutingContext rc, SockJSSession session) {
            super(rc, session);
            addCloseHandler(rc.response(), session);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sendFrame(String body, Handler<AsyncResult<Void>> handler) {
            super.beforeSend();
            this.rc.response().write(body + "\n", handler);
            close();
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.XhrTransport.BaseXhrListener, io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void close() {
            if (XhrTransport.log.isTraceEnabled()) {
                XhrTransport.log.trace("XHR poll closing listener");
            }
            if (!this.closed) {
                try {
                    this.session.resetListener();
                    this.rc.response().end();
                    this.rc.response().close();
                    this.closed = true;
                } catch (IllegalStateException e) {
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/XhrTransport$XhrStreamingListener.class */
    private class XhrStreamingListener extends BaseXhrListener {
        int bytesSent;
        int maxBytesStreaming;

        XhrStreamingListener(int maxBytesStreaming, RoutingContext rc, SockJSSession session) {
            super(rc, session);
            this.maxBytesStreaming = maxBytesStreaming;
            addCloseHandler(rc.response(), session);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sendFrame(String body, Handler<AsyncResult<Void>> handler) {
            boolean hr = this.headersWritten;
            super.beforeSend();
            if (!hr) {
                this.rc.response().write(XhrTransport.H_BLOCK);
            }
            String sbody = body + "\n";
            Buffer buff = Buffer.buffer(sbody);
            this.rc.response().write2(buff, handler);
            this.bytesSent += buff.length();
            if (this.bytesSent >= this.maxBytesStreaming) {
                close();
            }
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.XhrTransport.BaseXhrListener, io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void close() {
            if (XhrTransport.log.isTraceEnabled()) {
                XhrTransport.log.trace("XHR stream closing listener");
            }
            if (!this.closed) {
                this.session.resetListener();
                try {
                    this.rc.response().end();
                    this.rc.response().close();
                    this.closed = true;
                } catch (IllegalStateException e) {
                }
            }
        }
    }
}
