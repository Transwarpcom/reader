package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.CSRFHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/CSRFHandler.class */
public interface CSRFHandler extends Handler<RoutingContext> {
    public static final String ERROR_MESSAGE = "Invalid or missing csrf token";
    public static final String DEFAULT_COOKIE_NAME = "XSRF-TOKEN";
    public static final String DEFAULT_COOKIE_PATH = "/";
    public static final String DEFAULT_HEADER_NAME = "X-XSRF-TOKEN";
    public static final String DEFAULT_RESPONSE_BODY = null;

    @Fluent
    CSRFHandler setCookieName(String str);

    @Fluent
    CSRFHandler setCookiePath(String str);

    @Fluent
    CSRFHandler setHeaderName(String str);

    @Fluent
    CSRFHandler setNagHttps(boolean z);

    @Fluent
    CSRFHandler setResponseBody(String str);

    @Fluent
    CSRFHandler setTimeout(long j);

    static CSRFHandler create(String secret) {
        return new CSRFHandlerImpl(secret);
    }
}
