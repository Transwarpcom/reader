package io.vertx.ext.auth;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/AuthProvider.class */
public interface AuthProvider {
    void authenticate(JsonObject jsonObject, Handler<AsyncResult<User>> handler);
}
