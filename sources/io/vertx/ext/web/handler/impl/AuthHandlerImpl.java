package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.AuthHandler;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/AuthHandlerImpl.class */
public abstract class AuthHandlerImpl implements AuthHandler {
    static final String AUTH_PROVIDER_CONTEXT_KEY = "io.vertx.ext.web.handler.AuthHandler.provider";
    static final HttpStatusException FORBIDDEN = new HttpStatusException(403);
    static final HttpStatusException UNAUTHORIZED = new HttpStatusException(401);
    static final HttpStatusException BAD_REQUEST = new HttpStatusException(OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL);
    protected final String realm;
    protected final AuthProvider authProvider;
    protected final Set<String> authorities;

    public AuthHandlerImpl(AuthProvider authProvider) {
        this(authProvider, "");
    }

    public AuthHandlerImpl(AuthProvider authProvider, String realm) {
        this.authorities = new HashSet();
        this.authProvider = authProvider;
        this.realm = realm;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public AuthHandler addAuthority(String authority) {
        this.authorities.add(authority);
        return this;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public AuthHandler addAuthorities(Set<String> authorities) {
        this.authorities.addAll(authorities);
        return this;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void authorize(User user, Handler<AsyncResult<Void>> handler) {
        int requiredcount = this.authorities.size();
        if (requiredcount > 0) {
            if (user == null) {
                handler.handle(Future.failedFuture(FORBIDDEN));
                return;
            }
            AtomicInteger count = new AtomicInteger();
            AtomicBoolean sentFailure = new AtomicBoolean();
            Handler<AsyncResult<Boolean>> authHandler = res -> {
                if (res.succeeded()) {
                    if (((Boolean) res.result()).booleanValue()) {
                        if (count.incrementAndGet() == requiredcount) {
                            handler.handle(Future.succeededFuture());
                            return;
                        }
                        return;
                    } else {
                        if (sentFailure.compareAndSet(false, true)) {
                            handler.handle(Future.failedFuture(FORBIDDEN));
                            return;
                        }
                        return;
                    }
                }
                handler.handle(Future.failedFuture(res.cause()));
            };
            for (String authority : this.authorities) {
                if (!sentFailure.get()) {
                    user.isAuthorized(authority, authHandler);
                }
            }
            return;
        }
        handler.handle(Future.succeededFuture());
    }

    protected String authenticateHeader(RoutingContext context) {
        return null;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        if (handlePreflight(ctx)) {
            return;
        }
        User user = ctx.user();
        if (user != null) {
            authorizeUser(ctx, user);
        } else {
            parseCredentials(ctx, res -> {
                if (res.failed()) {
                    processException(ctx, res.cause());
                    return;
                }
                User updatedUser = ctx.user();
                if (updatedUser != null) {
                    Session session = ctx.session();
                    if (session != null) {
                        session.regenerateId();
                    }
                    authorizeUser(ctx, updatedUser);
                    return;
                }
                getAuthProvider(ctx).authenticate((JsonObject) res.result(), authN -> {
                    if (authN.succeeded()) {
                        User authenticated = (User) authN.result();
                        ctx.setUser(authenticated);
                        Session session2 = ctx.session();
                        if (session2 != null) {
                            session2.regenerateId();
                        }
                        authorizeUser(ctx, authenticated);
                        return;
                    }
                    String header = authenticateHeader(ctx);
                    if (header != null) {
                        ctx.response().putHeader("WWW-Authenticate", header);
                    }
                    if (authN.cause() instanceof HttpStatusException) {
                        processException(ctx, authN.cause());
                    } else {
                        processException(ctx, new HttpStatusException(401, authN.cause()));
                    }
                });
            });
        }
    }

    protected void processException(RoutingContext ctx, Throwable exception) {
        if (exception != null && (exception instanceof HttpStatusException)) {
            int statusCode = ((HttpStatusException) exception).getStatusCode();
            String payload = ((HttpStatusException) exception).getPayload();
            switch (statusCode) {
                case 302:
                    ctx.response().putHeader(HttpHeaders.LOCATION, payload).setStatusCode(302).end("Redirecting to " + payload + ".");
                    break;
                case 401:
                    String header = authenticateHeader(ctx);
                    if (header != null) {
                        ctx.response().putHeader("WWW-Authenticate", header);
                    }
                    ctx.fail(401, exception);
                    break;
                default:
                    ctx.fail(statusCode, exception);
                    break;
            }
            return;
        }
        ctx.fail(exception);
    }

    private void authorizeUser(RoutingContext ctx, User user) {
        authorize(user, authZ -> {
            if (authZ.failed()) {
                processException(ctx, authZ.cause());
            } else {
                ctx.next();
            }
        });
    }

    private boolean handlePreflight(RoutingContext ctx) {
        String accessControlRequestHeader;
        HttpServerRequest request = ctx.request();
        if (request.method() == HttpMethod.OPTIONS && (accessControlRequestHeader = ctx.request().getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS)) != null) {
            for (String ctrlReq : accessControlRequestHeader.split(",")) {
                if (ctrlReq.equalsIgnoreCase("Authorization")) {
                    ctx.next();
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private AuthProvider getAuthProvider(RoutingContext ctx) {
        try {
            AuthProvider provider = (AuthProvider) ctx.get(AUTH_PROVIDER_CONTEXT_KEY);
            if (provider != null) {
                return provider;
            }
        } catch (RuntimeException e) {
        }
        return this.authProvider;
    }
}
