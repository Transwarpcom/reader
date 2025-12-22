package io.netty.handler.ssl;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/OpenSslCachingX509KeyManagerFactory.class */
public final class OpenSslCachingX509KeyManagerFactory extends KeyManagerFactory {
    public OpenSslCachingX509KeyManagerFactory(final KeyManagerFactory factory) {
        super(new KeyManagerFactorySpi() { // from class: io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory.1
            @Override // javax.net.ssl.KeyManagerFactorySpi
            protected void engineInit(KeyStore keyStore, char[] chars) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
                factory.init(keyStore, chars);
            }

            @Override // javax.net.ssl.KeyManagerFactorySpi
            protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
                factory.init(managerFactoryParameters);
            }

            @Override // javax.net.ssl.KeyManagerFactorySpi
            protected KeyManager[] engineGetKeyManagers() {
                return factory.getKeyManagers();
            }
        }, factory.getProvider(), factory.getAlgorithm());
    }
}
