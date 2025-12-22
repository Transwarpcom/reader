package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.SessionHandlerImpl;
import io.vertx.ext.web.sstore.SessionStore;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/SessionHandler.class */
public interface SessionHandler extends Handler<RoutingContext> {
    public static final String DEFAULT_SESSION_COOKIE_NAME = "vertx-web.session";
    public static final String DEFAULT_SESSION_COOKIE_PATH = "/";
    public static final long DEFAULT_SESSION_TIMEOUT = 1800000;
    public static final boolean DEFAULT_NAG_HTTPS = true;
    public static final boolean DEFAULT_COOKIE_HTTP_ONLY_FLAG = false;
    public static final boolean DEFAULT_COOKIE_SECURE_FLAG = false;
    public static final int DEFAULT_SESSIONID_MIN_LENGTH = 16;

    @Fluent
    SessionHandler setSessionTimeout(long j);

    @Fluent
    SessionHandler setNagHttps(boolean z);

    @Fluent
    SessionHandler setCookieSecureFlag(boolean z);

    @Fluent
    SessionHandler setCookieHttpOnlyFlag(boolean z);

    @Fluent
    SessionHandler setSessionCookieName(String str);

    @Fluent
    SessionHandler setSessionCookiePath(String str);

    @Fluent
    SessionHandler setMinLength(int i);

    @Fluent
    SessionHandler setAuthProvider(AuthProvider authProvider);

    static SessionHandler create(SessionStore sessionStore) {
        return new SessionHandlerImpl(DEFAULT_SESSION_COOKIE_NAME, "/", DEFAULT_SESSION_TIMEOUT, true, false, false, 16, sessionStore);
    }
}
