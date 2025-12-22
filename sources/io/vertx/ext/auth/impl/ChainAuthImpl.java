package io.vertx.ext.auth.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.ChainAuth;
import io.vertx.ext.auth.User;
import java.util.ArrayList;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/impl/ChainAuthImpl.class */
public class ChainAuthImpl implements ChainAuth {
    private final List<AuthProvider> providers = new ArrayList();

    @Override // io.vertx.ext.auth.ChainAuth
    public ChainAuth append(AuthProvider other) {
        this.providers.add(other);
        return this;
    }

    @Override // io.vertx.ext.auth.ChainAuth
    public boolean remove(AuthProvider other) {
        return this.providers.remove(other);
    }

    @Override // io.vertx.ext.auth.ChainAuth
    public void clear() {
        this.providers.clear();
    }

    @Override // io.vertx.ext.auth.AuthProvider
    public void authenticate(JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        iterate(0, authInfo, resultHandler);
    }

    private void iterate(int idx, JsonObject authInfo, Handler<AsyncResult<User>> resultHandler) {
        if (idx >= this.providers.size()) {
            resultHandler.handle(Future.failedFuture("No more providers in the auth chain."));
        } else {
            this.providers.get(idx).authenticate(authInfo, res -> {
                if (res.succeeded()) {
                    resultHandler.handle(res);
                } else {
                    iterate(idx + 1, authInfo, resultHandler);
                }
            });
        }
    }
}
