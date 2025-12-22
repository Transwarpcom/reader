package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.web.handler.impl.RedirectAuthHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/RedirectAuthHandler.class */
public interface RedirectAuthHandler extends AuthHandler {
    public static final String DEFAULT_LOGIN_REDIRECT_URL = "/loginpage";
    public static final String DEFAULT_RETURN_URL_PARAM = "return_url";

    static AuthHandler create(AuthProvider authProvider) {
        return new RedirectAuthHandlerImpl(authProvider, DEFAULT_LOGIN_REDIRECT_URL, "return_url");
    }

    static AuthHandler create(AuthProvider authProvider, String loginRedirectURL) {
        return new RedirectAuthHandlerImpl(authProvider, loginRedirectURL, "return_url");
    }

    static AuthHandler create(AuthProvider authProvider, String loginRedirectURL, String returnURLParam) {
        return new RedirectAuthHandlerImpl(authProvider, loginRedirectURL, returnURLParam);
    }
}
