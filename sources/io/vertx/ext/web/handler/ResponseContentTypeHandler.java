package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.ResponseContentTypeHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/ResponseContentTypeHandler.class */
public interface ResponseContentTypeHandler extends Handler<RoutingContext> {
    public static final String DEFAULT_DISABLE_FLAG = "__vertx.autoContenType.disable";

    static ResponseContentTypeHandler create() {
        return new ResponseContentTypeHandlerImpl(DEFAULT_DISABLE_FLAG);
    }

    static ResponseContentTypeHandler create(String disableFlag) {
        return new ResponseContentTypeHandlerImpl(disableFlag);
    }
}
