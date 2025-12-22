package io.vertx.core.net;

import io.vertx.core.Vertx;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.impl.KeyStoreHelper;
import java.util.function.Function;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/TrustOptions.class */
public interface TrustOptions {
    @Deprecated
    /* renamed from: clone */
    TrustOptions m1984clone();

    default TrustOptions copy() {
        return m1984clone();
    }

    default TrustManagerFactory getTrustManagerFactory(Vertx vertx) throws Exception {
        return KeyStoreHelper.create((VertxInternal) vertx, this).getTrustMgrFactory((VertxInternal) vertx);
    }

    default Function<String, TrustManager[]> trustManagerMapper(Vertx vertx) throws Exception {
        KeyStoreHelper helper = KeyStoreHelper.create((VertxInternal) vertx, this);
        if (helper == null) {
            return null;
        }
        helper.getClass();
        return helper::getTrustMgr;
    }
}
