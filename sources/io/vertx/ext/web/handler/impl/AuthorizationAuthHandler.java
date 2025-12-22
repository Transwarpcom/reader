package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/AuthorizationAuthHandler.class */
abstract class AuthorizationAuthHandler extends AuthHandlerImpl {
    protected final Type type;

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/AuthorizationAuthHandler$Type.class */
    enum Type {
        BASIC("Basic"),
        DIGEST("Digest"),
        BEARER("Bearer"),
        HOBA("HOBA"),
        MUTUAL("Mutual"),
        NEGOTIATE("Negotiate"),
        OAUTH("OAuth"),
        SCRAM_SHA_1("SCRAM-SHA-1"),
        SCRAM_SHA_256("SCRAM-SHA-256");

        private final String label;

        Type(String label) {
            this.label = label;
        }

        public boolean is(String other) {
            return this.label.equalsIgnoreCase(other);
        }
    }

    AuthorizationAuthHandler(AuthProvider authProvider, Type type) {
        super(authProvider);
        this.type = type;
    }

    AuthorizationAuthHandler(AuthProvider authProvider, String realm, Type type) {
        super(authProvider, realm);
        this.type = type;
    }

    protected final void parseAuthorization(RoutingContext ctx, boolean optional, Handler<AsyncResult<String>> handler) {
        HttpServerRequest request = ctx.request();
        String authorization = request.headers().get(HttpHeaders.AUTHORIZATION);
        if (authorization == null) {
            if (optional) {
                handler.handle(Future.succeededFuture());
                return;
            } else {
                handler.handle(Future.failedFuture(UNAUTHORIZED));
                return;
            }
        }
        try {
            int idx = authorization.indexOf(32);
            if (idx <= 0) {
                handler.handle(Future.failedFuture(BAD_REQUEST));
            } else if (!this.type.is(authorization.substring(0, idx))) {
                handler.handle(Future.failedFuture(UNAUTHORIZED));
            } else {
                handler.handle(Future.succeededFuture(authorization.substring(idx + 1)));
            }
        } catch (RuntimeException e) {
            handler.handle(Future.failedFuture(e));
        }
    }
}
