package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import java.util.Objects;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/BlockingHandlerDecorator.class */
public class BlockingHandlerDecorator implements Handler<RoutingContext> {
    private boolean ordered;
    private final Handler<RoutingContext> decoratedHandler;

    public BlockingHandlerDecorator(Handler<RoutingContext> decoratedHandler, boolean ordered) {
        Objects.requireNonNull(decoratedHandler);
        this.decoratedHandler = decoratedHandler;
        this.ordered = ordered;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        Route currentRoute = context.currentRoute();
        context.vertx().executeBlocking(fut -> {
            this.decoratedHandler.handle(new RoutingContextDecorator(currentRoute, context));
            fut.complete();
        }, this.ordered, res -> {
            if (res.failed()) {
                context.fail(res.cause());
            }
        });
    }
}
