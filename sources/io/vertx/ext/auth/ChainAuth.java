package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.impl.ChainAuthImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/ChainAuth.class */
public interface ChainAuth extends AuthProvider {
    @Fluent
    ChainAuth append(AuthProvider authProvider);

    boolean remove(AuthProvider authProvider);

    void clear();

    static ChainAuth create() {
        return new ChainAuthImpl();
    }
}
