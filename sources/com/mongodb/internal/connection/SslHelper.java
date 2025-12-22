package com.mongodb.internal.connection;

import com.mongodb.ServerAddress;
import java.lang.reflect.InvocationTargetException;
import javax.net.ssl.SSLParameters;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/SslHelper.class */
public final class SslHelper {
    private static final SniSslHelper SNI_SSL_HELPER;

    static {
        SniSslHelper sniSslHelper;
        try {
            sniSslHelper = (SniSslHelper) Class.forName("com.mongodb.internal.connection.Java8SniSslHelper").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        } catch (IllegalAccessException e2) {
            throw new ExceptionInInitializerError(e2);
        } catch (InstantiationException e3) {
            throw new ExceptionInInitializerError(e3);
        } catch (LinkageError e4) {
            sniSslHelper = null;
        } catch (NoSuchMethodException e5) {
            throw new ExceptionInInitializerError(e5);
        } catch (InvocationTargetException e6) {
            throw new ExceptionInInitializerError(e6.getTargetException());
        }
        SNI_SSL_HELPER = sniSslHelper;
    }

    public static void enableHostNameVerification(SSLParameters sslParameters) {
        sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
    }

    public static void enableSni(ServerAddress address, SSLParameters sslParameters) {
        if (SNI_SSL_HELPER != null) {
            SNI_SSL_HELPER.enableSni(address, sslParameters);
        }
    }

    private SslHelper() {
    }
}
