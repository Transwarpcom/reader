package io.vertx.core.dns;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/SrvRecord.class */
public interface SrvRecord {
    int priority();

    int weight();

    int port();

    String name();

    String protocol();

    String service();

    String target();
}
