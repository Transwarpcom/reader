package io.vertx.core.eventbus;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/eventbus/DeliveryContext.class */
public interface DeliveryContext<T> {
    Message<T> message();

    void next();

    boolean send();

    Object body();
}
