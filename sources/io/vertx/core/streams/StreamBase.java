package io.vertx.core.streams;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/streams/StreamBase.class */
public interface StreamBase {
    @Fluent
    StreamBase exceptionHandler(Handler<Throwable> handler);
}
