package io.vertx.ext.web.templ;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

@VertxGen
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/templ/TemplateEngine.class */
public interface TemplateEngine extends io.vertx.ext.web.common.template.TemplateEngine {
    @Deprecated
    default void render(RoutingContext context, String templateFileName, Handler<AsyncResult<Buffer>> handler) {
        render(new JsonObject(context.data()), templateFileName, handler);
    }

    @Deprecated
    default void render(RoutingContext context, String templateDirectory, String templateFileName, Handler<AsyncResult<Buffer>> handler) {
        render(new JsonObject(context.data()), templateDirectory + templateFileName, handler);
    }

    @Override // io.vertx.ext.web.common.template.TemplateEngine
    default boolean isCachingEnabled() {
        return false;
    }
}
