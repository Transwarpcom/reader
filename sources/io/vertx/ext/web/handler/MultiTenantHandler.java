package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.MultiTenantHandlerImpl;
import java.util.function.Function;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/MultiTenantHandler.class */
public interface MultiTenantHandler extends Handler<RoutingContext> {
    public static final String TENANT = "tenant";

    @Fluent
    MultiTenantHandler addTenantHandler(String str, Handler<RoutingContext> handler);

    @Fluent
    MultiTenantHandler removeTenant(String str);

    @Fluent
    MultiTenantHandler addDefaultHandler(Handler<RoutingContext> handler);

    static MultiTenantHandler create(String header) {
        return create((Function<RoutingContext, String>) ctx -> {
            return ctx.request().getHeader(header);
        });
    }

    static MultiTenantHandler create(Function<RoutingContext, String> tenantExtractor) {
        return create(tenantExtractor, TENANT);
    }

    static MultiTenantHandler create(Function<RoutingContext, String> tenantExtractor, String contextKey) {
        return new MultiTenantHandlerImpl(tenantExtractor, contextKey);
    }
}
