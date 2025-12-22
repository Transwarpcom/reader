package io.vertx.ext.web.handler.sockjs.impl;

import com.google.common.net.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.StringEscapeUtils;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;
import io.vertx.ext.web.handler.sockjs.SockJSSocket;
import io.vertx.ext.web.handler.sockjs.Transport;
import io.vertx.ext.web.impl.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/BaseTransport.class */
class BaseTransport {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) BaseTransport.class);
    protected final Vertx vertx;
    protected final LocalMap<String, SockJSSession> sessions;
    protected SockJSHandlerOptions options;
    static final String COMMON_PATH_ELEMENT_RE = "\\/[^\\/\\.]+\\/([^\\/\\.]+)\\/";
    private static final long RAND_OFFSET = 2147483648L;

    public BaseTransport(Vertx vertx, LocalMap<String, SockJSSession> sessions, SockJSHandlerOptions options) {
        this.vertx = vertx;
        this.sessions = sessions;
        this.options = options;
    }

    protected SockJSSession getSession(RoutingContext rc, long timeout, long heartbeatInterval, String sessionID, Handler<SockJSSocket> sockHandler) {
        SockJSSession session = this.sessions.computeIfAbsent(sessionID, s -> {
            return new SockJSSession(this.vertx, this.sessions, rc, s, timeout, heartbeatInterval, sockHandler);
        });
        return session;
    }

    protected void sendInvalidJSON(HttpServerResponse response) {
        if (log.isTraceEnabled()) {
            log.trace("Broken JSON");
        }
        response.setStatusCode(500);
        response.end("Broken JSON encoding.");
    }

    protected String escapeForJavaScript(String str) {
        String str2;
        try {
            str2 = StringEscapeUtils.escapeJavaScript(str);
        } catch (Exception e) {
            log.error("Failed to escape", e);
            str2 = null;
        }
        return str2;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/sockjs/impl/BaseTransport$BaseListener.class */
    protected static abstract class BaseListener implements TransportListener {
        protected final RoutingContext rc;
        protected final SockJSSession session;
        protected boolean closed;

        protected BaseListener(RoutingContext rc, SockJSSession session) {
            this.rc = rc;
            this.session = session;
        }

        protected void addCloseHandler(HttpServerResponse resp, SockJSSession session) {
            resp.closeHandler(v -> {
                if (BaseTransport.log.isTraceEnabled()) {
                    BaseTransport.log.trace("Connection closed (from client?), closing session");
                }
                session.shutdown();
                this.closed = true;
            });
        }

        @Override // io.vertx.ext.web.handler.sockjs.impl.TransportListener
        public void sessionClosed() {
            this.session.writeClosed(this);
            close();
        }
    }

    static void setJSESSIONID(SockJSHandlerOptions options, RoutingContext rc) {
        String[] parts;
        String cookies = rc.request().getHeader("cookie");
        if (options.isInsertJSESSIONID()) {
            if (cookies != null) {
                if (cookies.contains(";")) {
                    parts = cookies.split(";");
                } else {
                    parts = new String[]{cookies};
                }
                String[] strArr = parts;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String part = strArr[i];
                    if (!part.startsWith("JSESSIONID")) {
                        i++;
                    } else {
                        cookies = part + "; path=/";
                        break;
                    }
                }
            }
            if (cookies == null) {
                cookies = "JSESSIONID=dummy; path=/";
            }
            rc.response().putHeader("Set-Cookie", cookies);
        }
    }

    static void setCORS(RoutingContext rc) {
        HttpServerRequest req = rc.request();
        String origin = req.headers().get(HttpHeaders.ReferrerPolicyValues.ORIGIN);
        if (origin == null) {
            origin = "*";
        }
        Utils.addToMapIfAbsent(req.response().headers(), "Access-Control-Allow-Origin", origin);
        if ("*".equals(origin)) {
            Utils.addToMapIfAbsent(req.response().headers(), "Access-Control-Allow-Credentials", "false");
        } else {
            Utils.addToMapIfAbsent(req.response().headers(), "Access-Control-Allow-Credentials", "true");
        }
        String hdr = req.headers().get("Access-Control-Request-Headers");
        if (hdr != null) {
            Utils.addToMapIfAbsent(req.response().headers(), "Access-Control-Allow-Headers", hdr);
        }
    }

    static Handler<RoutingContext> createInfoHandler(final SockJSHandlerOptions options) {
        return new Handler<RoutingContext>() { // from class: io.vertx.ext.web.handler.sockjs.impl.BaseTransport.1
            boolean websocket;

            {
                this.websocket = !options.getDisabledTransports().contains(Transport.WEBSOCKET.toString());
            }

            @Override // io.vertx.core.Handler
            public void handle(RoutingContext rc) {
                if (BaseTransport.log.isTraceEnabled()) {
                    BaseTransport.log.trace("In Info handler");
                }
                rc.response().putHeader("Content-Type", "application/json; charset=UTF-8");
                BaseTransport.setNoCacheHeaders(rc);
                JsonObject json = new JsonObject();
                json.put("websocket", Boolean.valueOf(this.websocket));
                json.put("cookie_needed", Boolean.valueOf(options.isInsertJSESSIONID()));
                json.put("origins", new JsonArray().add("*:*"));
                json.put("entropy", Long.valueOf(BaseTransport.RAND_OFFSET + new Random().nextInt()));
                BaseTransport.setCORS(rc);
                rc.response().end(json.encode());
            }
        };
    }

    static void setNoCacheHeaders(RoutingContext rc) {
        rc.response().putHeader("Cache-Control", "no-store, no-cache, no-transform, must-revalidate, max-age=0");
    }

    static Handler<RoutingContext> createCORSOptionsHandler(SockJSHandlerOptions options, String methods) {
        return rc -> {
            if (log.isTraceEnabled()) {
                log.trace("In CORS options handler");
            }
            rc.response().putHeader("Cache-Control", "public,max-age=31536000");
            long oneYearms = 31536000 * 1000;
            String expires = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").format(new Date(System.currentTimeMillis() + oneYearms));
            rc.response().putHeader("Expires", expires).putHeader("Access-Control-Allow-Methods", methods).putHeader("Access-Control-Max-Age", String.valueOf(31536000L));
            setCORS(rc);
            setJSESSIONID(options, rc);
            rc.response().setStatusCode(204);
            rc.response().end();
        };
    }

    static MultiMap removeCookieHeaders(MultiMap headers) {
        String cookieHeader = headers.get(io.vertx.core.http.HttpHeaders.COOKIE);
        if (cookieHeader != null) {
            headers.mo1933remove(io.vertx.core.http.HttpHeaders.COOKIE);
            Set<Cookie> nettyCookies = ServerCookieDecoder.STRICT.decode(cookieHeader);
            Iterator<Cookie> it = nettyCookies.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Cookie cookie = it.next();
                if (cookie.name().equals("JSESSIONID")) {
                    headers.add(io.vertx.core.http.HttpHeaders.COOKIE, ServerCookieEncoder.STRICT.encode(cookie));
                    break;
                }
            }
        }
        return headers;
    }
}
