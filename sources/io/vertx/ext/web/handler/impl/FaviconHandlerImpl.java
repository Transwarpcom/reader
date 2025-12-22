package io.vertx.ext.web.handler.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.FaviconHandler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/FaviconHandlerImpl.class */
public class FaviconHandlerImpl implements FaviconHandler {
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) FaviconHandler.class);
    private static final String DEFAULT_VERTX_ICON = "META-INF/vertx/web/favicon.ico";
    private final Icon NULL_ICON;
    private Icon icon;
    private final String path;
    private final long maxAgeSeconds;

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/FaviconHandlerImpl$Icon.class */
    private class Icon {
        private final MultiMap headers;
        private final Buffer body;

        private Icon(Buffer buffer) {
            this.headers = new CaseInsensitiveHeaders();
            this.body = buffer;
            this.headers.add(HttpHeaders.CONTENT_TYPE, "image/x-icon");
            this.headers.add(HttpHeaders.CONTENT_LENGTH, Integer.toString(buffer.length()));
            this.headers.add(HttpHeaders.CACHE_CONTROL, "public, max-age=" + FaviconHandlerImpl.this.maxAgeSeconds);
        }

        private Icon() {
            this.headers = null;
            this.body = null;
        }
    }

    public FaviconHandlerImpl(String path, long maxAgeSeconds) {
        this.NULL_ICON = new Icon();
        this.path = path;
        this.maxAgeSeconds = maxAgeSeconds;
        if (maxAgeSeconds < 0) {
            throw new IllegalArgumentException("maxAgeSeconds must be > 0");
        }
    }

    public FaviconHandlerImpl(long maxAgeSeconds) {
        this(null, maxAgeSeconds);
    }

    public FaviconHandlerImpl(String path) {
        this(path, 86400L);
    }

    public FaviconHandlerImpl() {
        this((String) null);
    }

    private Buffer readFile(FileSystem fs, String path) {
        if (fs.existsBlocking(path)) {
            return fs.readFileBlocking(path);
        }
        throw new RuntimeException(path + " not found!");
    }

    private void init(Vertx vertx) {
        FileSystem fs = vertx.fileSystem();
        Buffer buffer = null;
        if (this.path == null) {
            try {
                buffer = readFile(fs, "favicon.ico");
            } catch (RuntimeException e) {
                try {
                    buffer = fs.readFileBlocking(DEFAULT_VERTX_ICON);
                } catch (RuntimeException e2) {
                }
            }
        } else {
            try {
                buffer = readFile(fs, this.path);
            } catch (RuntimeException e3) {
                logger.error("Could not load favicon " + this.path);
            }
        }
        if (buffer != null) {
            this.icon = new Icon(buffer);
        } else {
            this.icon = this.NULL_ICON;
        }
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        if (this.icon == null) {
            init(ctx.vertx());
        }
        if ("/favicon.ico".equals(ctx.request().path())) {
            HttpServerResponse resp = ctx.response();
            if (this.icon != this.NULL_ICON) {
                resp.headers().addAll(this.icon.headers);
                resp.end(this.icon.body);
                return;
            } else {
                resp.setStatusCode(404).end();
                return;
            }
        }
        ctx.next();
    }
}
