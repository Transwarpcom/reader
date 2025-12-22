package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.impl.AuthorizationAuthHandler;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/JWTAuthHandlerImpl.class */
public class JWTAuthHandlerImpl extends AuthorizationAuthHandler implements JWTAuthHandler {
    private final String skip;
    private final JsonObject options;

    public JWTAuthHandlerImpl(JWTAuth authProvider, String skip) {
        super(authProvider, AuthorizationAuthHandler.Type.BEARER);
        this.skip = skip;
        this.options = new JsonObject();
    }

    @Override // io.vertx.ext.web.handler.JWTAuthHandler
    public JWTAuthHandler setAudience(List<String> audience) {
        this.options.put("audience", new JsonArray(audience));
        return this;
    }

    @Override // io.vertx.ext.web.handler.JWTAuthHandler
    public JWTAuthHandler setIssuer(String issuer) {
        this.options.put("issuer", issuer);
        return this;
    }

    @Override // io.vertx.ext.web.handler.JWTAuthHandler
    public JWTAuthHandler setIgnoreExpiration(boolean ignoreExpiration) {
        this.options.put("ignoreExpiration", Boolean.valueOf(ignoreExpiration));
        return this;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        if (this.skip != null && context.normalisedPath().startsWith(this.skip)) {
            context.next();
        } else {
            parseAuthorization(context, false, parseAuthorization -> {
                if (parseAuthorization.failed()) {
                    handler.handle(Future.failedFuture(parseAuthorization.cause()));
                } else {
                    handler.handle(Future.succeededFuture(new JsonObject().put("jwt", (String) parseAuthorization.result()).put("options", this.options)));
                }
            });
        }
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl
    protected String authenticateHeader(RoutingContext context) {
        return "Bearer";
    }
}
