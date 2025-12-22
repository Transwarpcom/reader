package io.vertx.ext.web.common.template;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import java.util.Map;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-common-3.8.5.jar:io/vertx/ext/web/common/template/TemplateEngine.class */
public interface TemplateEngine {
    @GenIgnore({"permitted-type"})
    void render(Map<String, Object> map, String str, Handler<AsyncResult<Buffer>> handler);

    boolean isCachingEnabled();

    default void render(JsonObject context, String templateFileName, Handler<AsyncResult<Buffer>> handler) {
        render(context.getMap(), templateFileName, handler);
    }
}
