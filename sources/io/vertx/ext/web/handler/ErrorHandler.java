package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.WebEnvironment;
import io.vertx.ext.web.handler.impl.ErrorHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/ErrorHandler.class */
public interface ErrorHandler extends Handler<RoutingContext> {
    public static final String DEFAULT_ERROR_HANDLER_TEMPLATE = "META-INF/vertx/web/vertx-web-error.html";

    static ErrorHandler create() {
        return create(DEFAULT_ERROR_HANDLER_TEMPLATE, WebEnvironment.development());
    }

    static ErrorHandler create(String errorTemplateName, boolean displayExceptionDetails) {
        return new ErrorHandlerImpl(errorTemplateName, displayExceptionDetails);
    }

    static ErrorHandler create(boolean displayExceptionDetails) {
        return create(DEFAULT_ERROR_HANDLER_TEMPLATE, displayExceptionDetails);
    }

    static ErrorHandler create(String errorTemplateName) {
        return create(errorTemplateName, WebEnvironment.development());
    }
}
