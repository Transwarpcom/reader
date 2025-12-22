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
import java.util.regex.Pattern;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/HtmlFileTransport.class */
class HtmlFileTransport extends BaseTransport {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) HtmlFileTransport.class);
    private static final Pattern CALLBACK_VALIDATION = Pattern.compile("[^a-zA-Z0-9-_.]");
    private static final String HTML_FILE_TEMPLATE;

    static {
        String str2 = "<!doctype html>\n<html><head>\n  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n</head><body><h2>Don't panic!</h2>\n  <script>\n    document.domain = document.domain;\n    var c = parent.{{ callback }};\n    c.start();\n    function p(d) {c.message(d);};\n    window.onload = function() {c.stop();};\n  </script>".replace("{{ callback }}", "");
        StringBuilder sb = new StringBuilder("<!doctype html>\n<html><head>\n  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n</head><body><h2>Don't panic!</h2>\n  <script>\n    document.domain = document.domain;\n    var c = parent.{{ callback }};\n    c.start();\n    function p(d) {c.message(d);};\n    window.onload = function() {c.stop();};\n  </script>");
        int extra = 1024 - str2.length();
        for (int i = 0; i < extra; i++) {
            sb.append(' ');
        }
        sb.append(StrPool.CRLF);
        HTML_FILE_TEMPLATE = sb.toString();
    }

    HtmlFileTransport(Vertx vertx, Router router, LocalMap<String, SockJSSession> sessions, SockJSHandlerOptions options, Handler<SockJSSocket> sockHandler) {
        super(vertx, sessions, options);
        router.getWithRegex("\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/htmlfile.*").handler(rc -> {
            if (log.isTraceEnabled()) {
                log.trace("HtmlFile, get: " + rc.request().uri());
            }
            String callback = rc.request().getParam("callback");
            if (callback == null) {
                callback = rc.request().getParam(OperatorName.CURVE_TO);
                if (callback == null) {
                    rc.response().setStatusCode(500).end("\"callback\" parameter required\n");
                    return;
                }
            }
            if (CALLBACK_VALIDATION.matcher(callback).find()) {
                rc.response().setStatusCode(500);
                rc.response().end("invalid \"callback\" parameter\n");
            } else {
                HttpServerRequest req = rc.request();
                String sessionID = req.params().get("param0");
                SockJSSession session = getSession(rc, options.getSessionTimeout(), options.getHeartbeatInterval(), sessionID, sockHandler);
                session.register(req, new HtmlFileListener(options.getMaxBytesStreaming(), rc, callback, session));
            }
        });
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/HtmlFileTransport$HtmlFileListener.class */
    private class HtmlFileListener extends BaseTransport.BaseListener {
        final int maxBytesStreaming;
        final String callback;
        boolean headersWritten;
        int bytesSent;
        boolean closed;

        HtmlFileListener(int maxBytesStreaming, RoutingContext rc, String callback, SockJSSession session) {
            super(rc, session);
            this.maxBytesStreaming = maxBytesStreaming;
            this.callback = callback;
            addCloseHandler(rc.response(), session);
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sendFrame(String body, Handler<AsyncResult<Void>> handler) {
            if (HtmlFileTransport.log.isTraceEnabled()) {
                HtmlFileTransport.log.trace("HtmlFile, sending frame");
            }
            if (!this.headersWritten) {
                String htmlFile = HtmlFileTransport.HTML_FILE_TEMPLATE.replace("{{ callback }}", this.callback);
                this.rc.response().putHeader("Content-Type", "text/html; charset=UTF-8");
                BaseTransport.setNoCacheHeaders(this.rc);
                this.rc.response().setChunked(true);
                BaseTransport.setJSESSIONID(HtmlFileTransport.this.options, this.rc);
                this.rc.response().write(htmlFile);
                this.headersWritten = true;
            }
            String sb = "<script>\np(\"" + HtmlFileTransport.this.escapeForJavaScript(body) + "\");\n</script>\r\n";
            Buffer buff = Buffer.buffer(sb);
            this.rc.response().write2(buff, handler);
            this.bytesSent += buff.length();
            if (this.bytesSent >= this.maxBytesStreaming) {
                if (HtmlFileTransport.log.isTraceEnabled()) {
                    HtmlFileTransport.log.trace("More than maxBytes sent so closing connection");
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
                    this.closed = true;
                } catch (IllegalStateException e) {
                }
            }
        }
    }
}
