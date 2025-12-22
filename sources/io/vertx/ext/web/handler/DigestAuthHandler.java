package io.vertx.ext.web.handler;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.htdigest.HtdigestAuth;
import io.vertx.ext.web.handler.impl.DigestAuthHandlerImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/DigestAuthHandler.class */
public interface DigestAuthHandler extends AuthHandler {
    public static final long DEFAULT_NONCE_EXPIRE_TIMEOUT = 3600000;

    static DigestAuthHandler create(HtdigestAuth authProvider) {
        return new DigestAuthHandlerImpl(authProvider, 3600000L);
    }

    static DigestAuthHandler create(HtdigestAuth authProvider, long nonceExpireTimeout) {
        return new DigestAuthHandlerImpl(authProvider, nonceExpireTimeout);
    }
}
