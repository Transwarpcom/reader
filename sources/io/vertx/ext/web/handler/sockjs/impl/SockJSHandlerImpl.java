package io.vertx.ext.web.handler.sockjs.impl;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.BridgeEvent;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.handler.sockjs.Transport;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/SockJSHandlerImpl.class */
public class SockJSHandlerImpl implements SockJSHandler {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) SockJSHandlerImpl.class);
    private Vertx vertx;
    private Router router;
    private LocalMap<String, SockJSSession> sessions;
    private SockJSHandlerOptions options;
    private static final String IFRAME_TEMPLATE = "<!DOCTYPE html>\n<html>\n<head>\n  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n  <script src=\"{{ sockjs_url }}\"></script>\n  <script>\n    document.domain = document.domain;\n    SockJS.bootstrap_iframe();\n  </script>\n</head>\n<body>\n  <h2>Don't panic!</h2>\n  <p>This is a SockJS hidden iframe. It's used for cross domain magic.</p>\n</body>\n</html>";

    public SockJSHandlerImpl(Vertx vertx, SockJSHandlerOptions options) {
        this.vertx = vertx;
        this.sessions = vertx.sharedData().getLocalMap("_vertx.sockjssessions");
        this.router = Router.router(vertx);
        this.options = options;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.vertx.ext.web.handler.sockjs.SockJSHandler, io.vertx.core.Handler
    @Deprecated
    public void handle(RoutingContext context) {
        if (log.isTraceEnabled()) {
            log.trace("Got request in sockjs server: " + context.request().uri());
        }
        this.router.handleContext(context);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSHandler
    public Router bridge(BridgeOptions bridgeOptions) {
        return bridge(bridgeOptions, null);
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSHandler
    public Router bridge(BridgeOptions bridgeOptions, Handler<BridgeEvent> bridgeEventHandler) {
        return socketHandler(new EventBusBridgeImpl(this.vertx, bridgeOptions, bridgeEventHandler));
    }

    @Override // io.vertx.ext.web.handler.sockjs.SockJSHandler
    public Router socketHandler(Handler<SockJSSocket> sockHandler) throws NoSuchAlgorithmException {
        this.router.route("/").useNormalisedPath(false).handler(rc -> {
            if (log.isTraceEnabled()) {
                log.trace("Returning welcome response");
            }
            rc.response().putHeader("Content-Type", "text/plain; charset=UTF-8").end("Welcome to SockJS!\n");
        });
        String iframeHTML = IFRAME_TEMPLATE.replace("{{ sockjs_url }}", this.options.getLibraryURL());
        Handler<RoutingContext> iframeHandler = createIFrameHandler(iframeHTML);
        this.router.get("/iframe.html").handler(iframeHandler);
        this.router.getWithRegex("\\/iframe-[^\\/]*\\.html").handler(iframeHandler);
        this.router.post("/chunking_test").handler(createChunkingTestHandler());
        this.router.options("/chunking_test").handler(BaseTransport.createCORSOptionsHandler(this.options, "OPTIONS, POST"));
        this.router.get("/info").handler(BaseTransport.createInfoHandler(this.options));
        this.router.options("/info").handler(BaseTransport.createCORSOptionsHandler(this.options, "OPTIONS, GET"));
        Set<String> enabledTransports = new HashSet<>();
        enabledTransports.add(Transport.EVENT_SOURCE.toString());
        enabledTransports.add(Transport.HTML_FILE.toString());
        enabledTransports.add(Transport.JSON_P.toString());
        enabledTransports.add(Transport.WEBSOCKET.toString());
        enabledTransports.add(Transport.XHR.toString());
        Set<String> disabledTransports = this.options.getDisabledTransports();
        if (disabledTransports == null) {
            disabledTransports = new HashSet<>();
        }
        enabledTransports.removeAll(disabledTransports);
        if (enabledTransports.contains(Transport.XHR.toString())) {
            new XhrTransport(this.vertx, this.router, this.sessions, this.options, sockHandler);
        }
        if (enabledTransports.contains(Transport.EVENT_SOURCE.toString())) {
            new EventSourceTransport(this.vertx, this.router, this.sessions, this.options, sockHandler);
        }
        if (enabledTransports.contains(Transport.HTML_FILE.toString())) {
            new HtmlFileTransport(this.vertx, this.router, this.sessions, this.options, sockHandler);
        }
        if (enabledTransports.contains(Transport.JSON_P.toString())) {
            new JsonPTransport(this.vertx, this.router, this.sessions, this.options, sockHandler);
        }
        if (enabledTransports.contains(Transport.WEBSOCKET.toString())) {
            new WebSocketTransport(this.vertx, this.router, this.sessions, this.options, sockHandler);
            new RawWebSocketTransport(this.vertx, this.router, sockHandler);
        }
        return this.router;
    }

    private Handler<RoutingContext> createChunkingTestHandler() {
        return new Handler<RoutingContext>() { // from class: io.vertx.ext.web.handler.sockjs.impl.SockJSHandlerImpl.1

            /* renamed from: io.vertx.ext.web.handler.sockjs.impl.SockJSHandlerImpl$1$TimeoutInfo */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/SockJSHandlerImpl$1$TimeoutInfo.class */
            class TimeoutInfo {
                long timeout;
                Buffer buff;

                TimeoutInfo(long timeout, Buffer buff) {
                    this.timeout = timeout;
                    this.buff = buff;
                }
            }

            private void setTimeout(List<TimeoutInfo> timeouts, long delay, Buffer buff) {
                timeouts.add(new TimeoutInfo(delay, buff));
            }

            private void runTimeouts(List<TimeoutInfo> timeouts, HttpServerResponse response) {
                Iterator<TimeoutInfo> iter = timeouts.iterator();
                nextTimeout(timeouts, iter, response);
            }

            private void nextTimeout(List<TimeoutInfo> timeouts, Iterator<TimeoutInfo> iter, HttpServerResponse response) {
                if (iter.hasNext()) {
                    TimeoutInfo timeout = iter.next();
                    SockJSHandlerImpl.this.vertx.setTimer(timeout.timeout, id -> {
                        response.write(timeout.buff);
                        nextTimeout(timeouts, iter, response);
                    });
                } else {
                    timeouts.clear();
                }
            }

            @Override // io.vertx.core.Handler
            public void handle(RoutingContext rc) {
                rc.response().headers().set("Content-Type", "application/javascript; charset=UTF-8");
                BaseTransport.setCORS(rc);
                rc.response().setChunked(true);
                Buffer h = Buffer.buffer(2);
                h.appendString("h\n");
                Buffer hs = Buffer.buffer(2050);
                for (int i = 0; i < 2048; i++) {
                    hs.appendByte((byte) 32);
                }
                hs.appendString("h\n");
                List<TimeoutInfo> timeouts = new ArrayList<>();
                setTimeout(timeouts, 0L, h);
                setTimeout(timeouts, 1L, hs);
                setTimeout(timeouts, 5L, h);
                setTimeout(timeouts, 25L, h);
                setTimeout(timeouts, 125L, h);
                setTimeout(timeouts, 625L, h);
                setTimeout(timeouts, 3125L, h);
                runTimeouts(timeouts, rc.response());
            }
        };
    }

    private Handler<RoutingContext> createIFrameHandler(String iframeHTML) throws NoSuchAlgorithmException {
        String etag = getMD5String(iframeHTML);
        return rc -> {
            try {
                if (log.isTraceEnabled()) {
                    log.trace("In Iframe handler");
                }
                if (etag != null && etag.equals(rc.request().getHeader("if-none-match"))) {
                    rc.response().setStatusCode(304);
                    rc.response().end();
                } else {
                    String expires = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(System.currentTimeMillis() + 31536000000L));
                    rc.response().putHeader("Content-Type", "text/html; charset=UTF-8").putHeader("Cache-Control", "public,max-age=31536000").putHeader("Expires", expires).putHeader("ETag", etag).end(iframeHTML);
                }
            } catch (Exception e) {
                log.error("Failed to server iframe", e);
            }
        };
    }

    private static String getMD5String(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toHexString(b + 127));
            }
            return sb.toString();
        } catch (Exception e) {
            log.error("Failed to generate MD5 for iframe, If-None-Match headers will be ignored");
            return null;
        }
    }
}
