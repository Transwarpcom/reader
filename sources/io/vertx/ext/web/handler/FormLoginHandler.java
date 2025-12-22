package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.FormLoginHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/FormLoginHandler.class */
public interface FormLoginHandler extends Handler<RoutingContext> {
    public static final String DEFAULT_USERNAME_PARAM = "username";
    public static final String DEFAULT_PASSWORD_PARAM = "password";
    public static final String DEFAULT_RETURN_URL_PARAM = "return_url";

    @Fluent
    FormLoginHandler setUsernameParam(String str);

    @Fluent
    FormLoginHandler setPasswordParam(String str);

    @Fluent
    FormLoginHandler setReturnURLParam(String str);

    @Fluent
    FormLoginHandler setDirectLoggedInOKURL(String str);

    static FormLoginHandler create(AuthProvider authProvider) {
        return new FormLoginHandlerImpl(authProvider, DEFAULT_USERNAME_PARAM, DEFAULT_PASSWORD_PARAM, "return_url", null);
    }

    static FormLoginHandler create(AuthProvider authProvider, String usernameParam, String passwordParam, String returnURLParam, String directLoggedInOKURL) {
        return new FormLoginHandlerImpl(authProvider, usernameParam, passwordParam, returnURLParam, directLoggedInOKURL);
    }
}
