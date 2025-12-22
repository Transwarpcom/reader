package io.netty.handler.ssl;

import io.netty.util.internal.PlatformDependent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.net.ssl.SSLEngine;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/Conscrypt.class */
final class Conscrypt {
    private static final Method IS_CONSCRYPT_SSLENGINE = loadIsConscryptEngine();
    private static final boolean CAN_INSTANCE_PROVIDER = canInstanceProvider();

    private static Method loadIsConscryptEngine() {
        try {
            Class<?> conscryptClass = Class.forName("org.conscrypt.Conscrypt", true, ConscryptAlpnSslEngine.class.getClassLoader());
            return conscryptClass.getMethod("isConscrypt", SSLEngine.class);
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean canInstanceProvider() {
        try {
            Class<?> providerClass = Class.forName("org.conscrypt.OpenSSLProvider", true, ConscryptAlpnSslEngine.class.getClassLoader());
            providerClass.newInstance();
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static boolean isAvailable() {
        return CAN_INSTANCE_PROVIDER && IS_CONSCRYPT_SSLENGINE != null && PlatformDependent.javaVersion() >= 8;
    }

    static boolean isEngineSupported(SSLEngine engine) {
        return isAvailable() && isConscryptEngine(engine);
    }

    private static boolean isConscryptEngine(SSLEngine engine) {
        try {
            return ((Boolean) IS_CONSCRYPT_SSLENGINE.invoke(null, engine)).booleanValue();
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Conscrypt() {
    }
}
