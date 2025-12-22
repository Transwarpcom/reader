package io.vertx.core.net.impl;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/VertxTrustManagerFactory.class */
class VertxTrustManagerFactory extends TrustManagerFactory {
    private static final Provider PROVIDER = new Provider("", 0.0d, "") { // from class: io.vertx.core.net.impl.VertxTrustManagerFactory.1
    };

    VertxTrustManagerFactory(final TrustManager... tm) {
        super(new TrustManagerFactorySpi() { // from class: io.vertx.core.net.impl.VertxTrustManagerFactory.2
            @Override // javax.net.ssl.TrustManagerFactorySpi
            protected void engineInit(KeyStore keyStore) throws KeyStoreException {
            }

            @Override // javax.net.ssl.TrustManagerFactorySpi
            protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
            }

            @Override // javax.net.ssl.TrustManagerFactorySpi
            protected TrustManager[] engineGetTrustManagers() {
                return (TrustManager[]) tm.clone();
            }
        }, PROVIDER, "");
    }
}
