package io.vertx.ext.web.handler.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.net.SocketAddress;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.ext.web.impl.Utils;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/LoggerHandlerImpl.class */
public class LoggerHandlerImpl implements LoggerHandler {
    private final Logger logger;
    private final boolean immediate;
    private final LoggerFormat format;

    public LoggerHandlerImpl(boolean immediate, LoggerFormat format) {
        this.logger = LoggerFactory.getLogger(getClass());
        this.immediate = immediate;
        this.format = format;
    }

    public LoggerHandlerImpl(LoggerFormat format) {
        this(false, format);
    }

    private String getClientAddress(SocketAddress inetSocketAddress) {
        if (inetSocketAddress == null) {
            return null;
        }
        return inetSocketAddress.host();
    }

    private void log(RoutingContext context, long timestamp, String remoteClient, HttpVersion version, HttpMethod method, String uri) throws NumberFormatException {
        HttpServerRequest request = context.request();
        long contentLength = 0;
        if (this.immediate) {
            Object obj = request.headers().get("content-length");
            if (obj != null) {
                try {
                    contentLength = Long.parseLong(obj.toString());
                } catch (NumberFormatException e) {
                    contentLength = 0;
                }
            }
        } else {
            contentLength = request.response().bytesWritten();
        }
        String versionFormatted = "-";
        switch (version) {
            case HTTP_1_0:
                versionFormatted = "HTTP/1.0";
                break;
            case HTTP_1_1:
                versionFormatted = "HTTP/1.1";
                break;
            case HTTP_2:
                versionFormatted = "HTTP/2.0";
                break;
        }
        MultiMap headers = request.headers();
        int status = request.response().getStatusCode();
        String message = null;
        switch (this.format) {
            case DEFAULT:
                String referrer = headers.contains("referrer") ? headers.get("referrer") : headers.get("referer");
                String userAgent = request.headers().get("user-agent");
                message = String.format("%s - - [%s] \"%s %s %s\" %d %d \"%s\" \"%s\"", remoteClient, Utils.formatRFC1123DateTime(timestamp), method, uri, versionFormatted, Integer.valueOf(status), Long.valueOf(contentLength), referrer == null ? "-" : referrer, userAgent == null ? "-" : userAgent);
                break;
            case SHORT:
                message = String.format("%s - %s %s %s %d %d - %d ms", remoteClient, method, uri, versionFormatted, Integer.valueOf(status), Long.valueOf(contentLength), Long.valueOf(System.currentTimeMillis() - timestamp));
                break;
            case TINY:
                message = String.format("%s %s %d %d - %d ms", method, uri, Integer.valueOf(status), Long.valueOf(contentLength), Long.valueOf(System.currentTimeMillis() - timestamp));
                break;
        }
        doLog(status, message);
    }

    protected void doLog(int status, String message) {
        if (status >= 500) {
            this.logger.error(message);
        } else if (status >= 400) {
            this.logger.warn(message);
        } else {
            this.logger.info(message);
        }
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) throws NumberFormatException {
        long timestamp = System.currentTimeMillis();
        String remoteClient = getClientAddress(context.request().remoteAddress());
        HttpMethod method = context.request().method();
        String uri = context.request().uri();
        HttpVersion version = context.request().version();
        if (this.immediate) {
            log(context, timestamp, remoteClient, version, method, uri);
        } else {
            context.addBodyEndHandler(v -> {
                log(context, timestamp, remoteClient, version, method, uri);
            });
        }
        context.next();
    }
}
