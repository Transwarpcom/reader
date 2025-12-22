package io.vertx.ext.web.client.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/WebClientInternal.class */
public interface WebClientInternal extends WebClient {
    <T> HttpContext<T> createContext(Handler<AsyncResult<HttpResponse<T>>> handler);

    WebClientInternal addInterceptor(Handler<HttpContext<?>> handler);
}
