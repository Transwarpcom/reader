package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/User.class */
public interface User {
    @Fluent
    @Deprecated
    User isAuthorized(String str, Handler<AsyncResult<Boolean>> handler);

    @Fluent
    @Deprecated
    User clearCache();

    JsonObject principal();

    @Deprecated
    void setAuthProvider(AuthProvider authProvider);

    @Fluent
    @Deprecated
    default User isAuthorised(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
        return isAuthorized(authority, resultHandler);
    }
}
