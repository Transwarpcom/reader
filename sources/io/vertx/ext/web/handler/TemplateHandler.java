package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.impl.TemplateHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/TemplateHandler.class */
public interface TemplateHandler extends Handler<RoutingContext> {
    public static final String DEFAULT_TEMPLATE_DIRECTORY = "templates";
    public static final String DEFAULT_CONTENT_TYPE = "text/html";
    public static final String DEFAULT_INDEX_TEMPLATE = "index";

    @Fluent
    TemplateHandler setIndexTemplate(String str);

    static TemplateHandler create(TemplateEngine engine) {
        return new TemplateHandlerImpl(engine, DEFAULT_TEMPLATE_DIRECTORY, "text/html");
    }

    static TemplateHandler create(TemplateEngine engine, String templateDirectory, String contentType) {
        return new TemplateHandlerImpl(engine, templateDirectory, contentType);
    }
}
