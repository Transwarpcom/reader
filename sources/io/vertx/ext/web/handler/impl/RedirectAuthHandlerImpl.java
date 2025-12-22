package io.vertx.ext.web.handler.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/RedirectAuthHandlerImpl.class */
public class RedirectAuthHandlerImpl extends AuthHandlerImpl {
    private final String loginRedirectURL;
    private final String returnURLParam;

    public RedirectAuthHandlerImpl(AuthProvider authProvider, String loginRedirectURL, String returnURLParam) {
        super(authProvider);
        this.loginRedirectURL = loginRedirectURL;
        this.returnURLParam = returnURLParam;
    }

    @Override // io.vertx.ext.web.handler.AuthHandler
    public void parseCredentials(RoutingContext context, Handler<AsyncResult<JsonObject>> handler) {
        Session session = context.session();
        if (session != null) {
            session.put(this.returnURLParam, context.request().uri());
            handler.handle(Future.failedFuture(new HttpStatusException(302, this.loginRedirectURL)));
        } else {
            handler.handle(Future.failedFuture("No session - did you forget to include a SessionHandler?"));
        }
    }
}
