package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.handler.impl.OAuth2AuthHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/OAuth2AuthHandler.class */
public interface OAuth2AuthHandler extends AuthHandler {
    @Fluent
    OAuth2AuthHandler extraParams(JsonObject jsonObject);

    @Fluent
    OAuth2AuthHandler setupCallback(Route route);

    static OAuth2AuthHandler create(OAuth2Auth authProvider, String callbackURL) {
        return new OAuth2AuthHandlerImpl(authProvider, callbackURL);
    }

    static OAuth2AuthHandler create(OAuth2Auth authProvider) {
        return new OAuth2AuthHandlerImpl(authProvider, null);
    }
}
