package io.vertx.ext.web;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Map;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/Session.class */
public interface Session {
    Session regenerateId();

    String id();

    @Fluent
    Session put(String str, Object obj);

    <T> T get(String str);

    <T> T remove(String str);

    @GenIgnore({"permitted-type"})
    Map<String, Object> data();

    boolean isEmpty();

    long lastAccessed();

    void destroy();

    boolean isDestroyed();

    boolean isRegenerated();

    String oldId();

    long timeout();

    void setAccessed();

    default String value() {
        return id();
    }
}
