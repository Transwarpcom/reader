package io.vertx.ext.web.handler.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.MultiTenantHandler;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/MultiTenantHandlerImpl.class */
public class MultiTenantHandlerImpl implements MultiTenantHandler {
    private final Map<String, Handler<RoutingContext>> handlerMap = new ConcurrentHashMap();
    private final Function<RoutingContext, String> tenantExtractor;
    private final String contextKey;
    private Handler<RoutingContext> defaultHandler;

    public MultiTenantHandlerImpl(Function<RoutingContext, String> tenantExtractor, String contextKey) {
        this.tenantExtractor = (Function) Objects.requireNonNull(tenantExtractor);
        this.contextKey = (String) Objects.requireNonNull(contextKey);
    }

    @Override // io.vertx.ext.web.handler.MultiTenantHandler
    public MultiTenantHandler addTenantHandler(String tenant, Handler<RoutingContext> handler) {
        Objects.requireNonNull(tenant);
        Objects.requireNonNull(handler);
        if (this.handlerMap.put(tenant, handler) != null) {
            throw new IllegalStateException("tenant '" + tenant + "' already present");
        }
        return this;
    }

    @Override // io.vertx.ext.web.handler.MultiTenantHandler
    public MultiTenantHandler removeTenant(String tenant) {
        Objects.requireNonNull(tenant);
        this.handlerMap.remove(tenant);
        return this;
    }

    @Override // io.vertx.ext.web.handler.MultiTenantHandler
    public MultiTenantHandler addDefaultHandler(Handler<RoutingContext> handler) {
        Objects.requireNonNull(handler);
        this.defaultHandler = handler;
        return this;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext ctx) {
        String tenant = this.tenantExtractor.apply(ctx);
        Handler<RoutingContext> handler = tenant == null ? this.defaultHandler : this.handlerMap.getOrDefault(tenant, this.defaultHandler);
        if (handler != null) {
            ctx.put(this.contextKey, handler == this.defaultHandler ? "default" : tenant);
            handler.handle(ctx);
        } else {
            ctx.next();
        }
    }
}
