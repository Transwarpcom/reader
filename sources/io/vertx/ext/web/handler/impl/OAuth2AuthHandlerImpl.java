package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.OAuth2AuthHandler;
import io.vertx.ext.web.handler.impl.AuthorizationAuthHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/OAuth2AuthHandlerImpl.class */
public class OAuth2AuthHandlerImpl extends AuthorizationAuthHandler implements OAuth2AuthHandler {
    private static final Logger log = LoggerFactory.getLogger((Class<?>) OAuth2AuthHandlerImpl.class);
    private final String host;
    private final String callbackPath;
    private final Set<String> scopes;
    private Route callback;
    private JsonObject extraParams;
    private boolean bearerOnly;

    private static AuthProvider verifyProvider(AuthProvider provider) {
        if ((provider instanceof OAuth2Auth) && ((OAuth2Auth) provider).getFlowType() != OAuth2FlowType.AUTH_CODE) {
            throw new IllegalArgumentException("OAuth2Auth + Bearer Auth requires OAuth2 AUTH_CODE flow");
        }
        return provider;
    }

    public OAuth2AuthHandlerImpl(OAuth2Auth authProvider, String callbackURL) {
        super(verifyProvider(authProvider), AuthorizationAuthHandler.Type.BEARER);
        this.scopes = new HashSet();
        this.bearerOnly = true;
        try {
            if (callbackURL != null) {
                URL url = new URL(callbackURL);
                this.host = url.getProtocol() + "://" + url.getHost() + (url.getPort() == -1 ? "" : ":" + url.getPort());
                this.callbackPath = url.getPath();
            } else {
                this.host = null;
                this.callbackPath = null;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl, io.vertx.ext.web.handler.AuthHandler
    public AuthHandler addAuthority(String authority) {
        this.scopes.add(authority);
        return this;
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl, io.vertx.ext.web.handler.AuthHandler
    public AuthHandler addAuthorities(Set<String> authorities) {
        this.scopes.addAll(authorities);
        return this;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        parseAuthorization(context, !this.bearerOnly, parseAuthorization -> {
            if (parseAuthorization.failed()) {
                handler.handle(Future.failedFuture(parseAuthorization.cause()));
                return;
            }
            String token = (String) parseAuthorization.result();
            if (token == null) {
                if (this.callback == null) {
                    handler.handle(Future.failedFuture("callback route is not configured."));
                    return;
                }
                if (context.request().method() == HttpMethod.GET && context.normalisedPath().equals(this.callback.getPath())) {
                    if (log.isWarnEnabled()) {
                        log.warn("The callback route is shaded by the OAuth2AuthHandler, ensure the callback route is added BEFORE the OAuth2AuthHandler route!");
                    }
                    handler.handle(Future.failedFuture(new HttpStatusException(500, "Infinite redirect loop [oauth2 callback]")));
                    return;
                }
                handler.handle(Future.failedFuture(new HttpStatusException(302, authURI(context.request().uri()))));
                return;
            }
            this.authProvider.decodeToken(token, decodeToken -> {
                if (decodeToken.failed()) {
                    handler.handle(Future.failedFuture(new HttpStatusException(401, decodeToken.cause().getMessage())));
                } else {
                    context.setUser((User) decodeToken.result());
                    handler.handle(Future.succeededFuture());
                }
            });
        });
    }

    private String authURI(String redirectURL) {
        JsonObject config = new JsonObject().put("state", redirectURL);
        if (this.host != null) {
            config.put("redirect_uri", this.host + this.callback.getPath());
        }
        if (this.extraParams != null) {
            config.mergeIn(this.extraParams);
        }
        if (this.scopes.size() > 0) {
            JsonArray _scopes = new JsonArray();
            for (String authority : this.scopes) {
                _scopes.add(authority);
            }
            config.put("scopes", _scopes);
        }
        return this.authProvider.authorizeURL(config);
    }

    @Override // io.vertx.ext.web.handler.OAuth2AuthHandler
    public OAuth2AuthHandler extraParams(JsonObject extraParams) {
        this.extraParams = extraParams;
        return this;
    }

    @Override // io.vertx.ext.web.handler.OAuth2AuthHandler
    public OAuth2AuthHandler setupCallback(Route route) {
        if (this.callbackPath != null && !"".equals(this.callbackPath)) {
            route.path(this.callbackPath);
        }
        route.method(HttpMethod.GET);
        route.handler(ctx -> {
            String code = ctx.request().getParam("code");
            if (code == null) {
                ctx.fail(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
                return;
            }
            String state = ctx.request().getParam("state");
            JsonObject config = new JsonObject().put("code", code);
            if (this.host != null) {
                config.put("redirect_uri", this.host + route.getPath());
            }
            if (this.extraParams != null) {
                config.mergeIn(this.extraParams);
            }
            this.authProvider.authenticate(config, res -> {
                if (res.failed()) {
                    ctx.fail(res.cause());
                    return;
                }
                ctx.setUser((User) res.result());
                Session session = ctx.session();
                if (session != null) {
                    session.regenerateId();
                    ctx.response().putHeader(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate").putHeader("Pragma", "no-cache").putHeader(HttpHeaders.EXPIRES, "0").putHeader(HttpHeaders.LOCATION, state != null ? state : "/").setStatusCode(302).end("Redirecting to " + (state != null ? state : "/") + ".");
                } else {
                    ctx.reroute(state != null ? state : "/");
                }
            });
        });
        this.bearerOnly = false;
        this.callback = route;
        return this;
    }
}
