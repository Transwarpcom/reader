package io.vertx.ext.web.handler.impl;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/ResponseContentTypeHandlerImpl.class */
public class ResponseContentTypeHandlerImpl implements ResponseContentTypeHandler {
    private final String disableFlag;

    public ResponseContentTypeHandlerImpl(String disableFlag) {
        this.disableFlag = disableFlag;
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext rc) {
        rc.addHeadersEndHandler(v -> {
            String acceptableContentType;
            if (rc.get(this.disableFlag) != null || (acceptableContentType = rc.getAcceptableContentType()) == null) {
                return;
            }
            MultiMap headers = rc.response().headers();
            if (!headers.contains(HttpHeaders.CONTENT_TYPE) && !"0".equals(headers.get(HttpHeaders.CONTENT_LENGTH))) {
                headers.add(HttpHeaders.CONTENT_TYPE, acceptableContentType);
            }
        });
        rc.next();
    }
}
