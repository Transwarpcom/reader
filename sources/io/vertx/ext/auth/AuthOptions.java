package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.Vertx;

@DataObject
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/AuthOptions.class */
public interface AuthOptions {
    AuthOptions clone();

    AuthProvider createProvider(Vertx vertx);
}
