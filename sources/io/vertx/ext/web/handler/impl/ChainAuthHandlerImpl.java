package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.ChainAuthHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/ChainAuthHandlerImpl.class */
public class ChainAuthHandlerImpl extends AuthHandlerImpl implements ChainAuthHandler {
    private final List<AuthHandler> handlers;

    public ChainAuthHandlerImpl() {
        super(null);
        this.handlers = new ArrayList();
    }

    @Override // io.vertx.ext.web.handler.ChainAuthHandler
    public ChainAuthHandler append(AuthHandler other) {
        this.handlers.add(other);
        return this;
    }

    @Override // io.vertx.ext.web.handler.ChainAuthHandler
    public boolean remove(AuthHandler other) {
        return this.handlers.remove(other);
    }

    @Override // io.vertx.ext.web.handler.ChainAuthHandler
    public void clear() {
        this.handlers.clear();
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl, io.vertx.ext.web.handler.AuthHandler
    public AuthHandler addAuthority(String authority) {
        for (AuthHandler h : this.handlers) {
            h.addAuthority(authority);
        }
        return this;
    }

    @Override // io.vertx.ext.web.handler.impl.AuthHandlerImpl, io.vertx.ext.web.handler.AuthHandler
    public AuthHandler addAuthorities(Set<String> authorities) {
        for (AuthHandler h : this.handlers) {
            h.addAuthorities(authorities);
        }
        return this;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        iterate(0, context, null, handler);
    }

    private void iterate(int idx, RoutingContext ctx, HttpStatusException lastException, Handler<AsyncResult<JsonObject>> handler) {
        if (idx >= this.handlers.size()) {
            handler.handle(Future.failedFuture(lastException));
        } else {
            AuthHandler authHandler = this.handlers.get(idx);
            authHandler.parseCredentials(ctx, res -> {
                if (res.failed()) {
                    if (res.cause() instanceof HttpStatusException) {
                        HttpStatusException exception = (HttpStatusException) res.cause();
                        switch (exception.getStatusCode()) {
                            case 302:
                            case OS2WindowsMetricsTable.WEIGHT_CLASS_NORMAL /* 400 */:
                            case 401:
                            case 403:
                                iterate(idx + 1, ctx, exception, handler);
                                break;
                        }
                        return;
                    }
                    handler.handle(Future.failedFuture(res.cause()));
                    return;
                }
                if (authHandler instanceof AuthHandlerImpl) {
                    ctx.put("io.vertx.ext.web.handler.AuthHandler.provider", ((AuthHandlerImpl) authHandler).authProvider);
                }
                handler.handle(Future.succeededFuture(res.result()));
            });
        }
    }
}
