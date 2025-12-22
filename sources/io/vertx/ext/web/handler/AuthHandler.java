package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import java.util.Set;

@VertxGen(concrete = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/AuthHandler.class */
public interface AuthHandler extends Handler<RoutingContext> {
    @Fluent
    AuthHandler addAuthority(String str);

    @Fluent
    AuthHandler addAuthorities(Set<String> set);

    void parseCredentials(RoutingContext routingContext, Handler<AsyncResult<JsonObject>> handler);

    void authorize(User user, Handler<AsyncResult<Void>> handler);
}
