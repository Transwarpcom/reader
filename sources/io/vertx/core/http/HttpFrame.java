package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/HttpFrame.class */
public interface HttpFrame {
    @CacheReturn
    int type();

    @CacheReturn
    int flags();

    @CacheReturn
    Buffer payload();
}
