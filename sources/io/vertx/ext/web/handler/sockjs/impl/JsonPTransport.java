package io.vertx.ext.web.handler.sockjs.impl;

import com.google.common.net.HttpHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.impl.URIDecoder;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.handler.sockjs.impl.BaseTransport;
import java.util.regex.Pattern;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/JsonPTransport.class */
class JsonPTransport extends BaseTransport {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) JsonPTransport.class);
    private static final Pattern CALLBACK_VALIDATION = Pattern.compile("[^a-zA-Z0-9-_.]");

    JsonPTransport(Vertx vertx, Router router, LocalMap<String, SockJSSession> sessions, SockJSHandlerOptions options, Handler<SockJSSocket> sockHandler) {
        super(vertx, sessions, options);
        router.getWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/jsonp").handler(rc -> {
            if (log.isTraceEnabled()) {
                log.trace("JsonP, get: " + rc.request().uri());
            }
            String callback = rc.request().getParam("callback");
            if (callback == null) {
                callback = rc.request().getParam(OperatorName.CURVE_TO);
                if (callback == null) {
                    rc.response().setStatusCode(500);
                    rc.response().end("\"callback\" parameter required\n");
                    return;
                }
            }
            if (callback.length() > 32 || CALLBACK_VALIDATION.matcher(callback).find()) {
                rc.response().setStatusCode(500);
                rc.response().end("invalid \"callback\" parameter\n");
            } else {
                HttpServerRequest req = rc.request();
                String sessionID = req.params().get("param0");
                SockJSSession session = getSession(rc, options.getSessionTimeout(), options.getHeartbeatInterval(), sessionID, sockHandler);
                session.register(req, new JsonPListener(rc, session, callback));
            }
        });
        router.postWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/jsonp_send").handler(rc2 -> {
            if (log.isTraceEnabled()) {
                log.trace("JsonP, post: " + rc2.request().uri());
            }
            String sessionID = rc2.request().getParam("param0");
            SockJSSession session = (SockJSSession) sessions.get(sessionID);
            if (session != null && !session.isClosed()) {
                handleSend(rc2, session);
                return;
            }
            rc2.response().setStatusCode(404);
            setJSESSIONID(options, rc2);
            rc2.response().end();
        });
    }

    private void handleSend(RoutingContext rc, SockJSSession session) {
        rc.request().bodyHandler(buff -> {
            boolean urlEncoded;
            String body = buff.toString();
            String ct = rc.request().getHeader("content-type");
            if ("application/x-www-form-urlencoded".equalsIgnoreCase(ct)) {
                urlEncoded = true;
            } else if ("text/plain".equalsIgnoreCase(ct)) {
                urlEncoded = false;
            } else {
                rc.response().setStatusCode(500);
                rc.response().end("Invalid Content-Type");
                return;
            }
            if (body.equals("") || (urlEncoded && (!body.startsWith("d=") || body.length() <= 2))) {
                rc.response().setStatusCode(500).end("Payload expected.");
                return;
            }
            if (urlEncoded) {
                body = URIDecoder.decodeURIComponent(body, true).substring(2);
            }
            if (!session.handleMessages(body)) {
                sendInvalidJSON(rc.response());
                return;
            }
            setJSESSIONID(this.options, rc);
            rc.response().putHeader("Content-Type", "text/plain; charset=UTF-8");
            setNoCacheHeaders(rc);
            rc.response().end("ok");
            if (log.isTraceEnabled()) {
                log.trace("send handled ok");
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/JsonPTransport$JsonPListener.class */
    private class JsonPListener extends BaseTransport.BaseListener {
        final String callback;
        boolean headersWritten;
        boolean closed;

        JsonPListener(RoutingContext rc, SockJSSession session, String callback) {
            super(rc, session);
            this.callback = callback;
            addCloseHandler(rc.response(), session);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sendFrame(String body, Handler<AsyncResult<Void>> handler) {
            if (JsonPTransport.log.isTraceEnabled()) {
                JsonPTransport.log.trace("JsonP, sending frame");
            }
            if (!this.headersWritten) {
                this.rc.response().setChunked(true).putHeader(HttpHeaders.X_CONTENT_TYPE_OPTIONS, "nosniff").putHeader("Content-Type", "application/javascript; charset=UTF-8");
                BaseTransport.setNoCacheHeaders(this.rc);
                BaseTransport.setJSESSIONID(JsonPTransport.this.options, this.rc);
                this.headersWritten = true;
            }
            String sb = "/**/" + this.callback + "(\"" + JsonPTransport.this.escapeForJavaScript(body) + "\");\r\n";
            this.rc.response().write(sb, handler);
            close();
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void close() {
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
}
