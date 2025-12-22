package io.vertx.core.net;

import io.vertx.core.Vertx;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.KeyStoreHelper;
import java.util.function.Function;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/KeyCertOptions.class */
public interface KeyCertOptions {
    @Deprecated
    /* renamed from: clone */
    KeyCertOptions m1984clone();

    default KeyCertOptions copy() {
        return m1984clone();
    }

    default KeyManagerFactory getKeyManagerFactory(Vertx vertx) throws Exception {
        return KeyStoreHelper.create((VertxInternal) vertx, this).getKeyMgrFactory();
    }

    default Function<String, X509KeyManager> keyManagerMapper(Vertx vertx) throws Exception {
        KeyStoreHelper helper = KeyStoreHelper.create((VertxInternal) vertx, this);
        helper.getClass();
        return helper::getKeyMgr;
    }
}
