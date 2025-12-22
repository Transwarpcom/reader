package cn.hutool.crypto;

import java.security.Provider;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-crypto-5.8.0.M1.jar:cn/hutool/crypto/GlobalBouncyCastleProvider.class */
public enum GlobalBouncyCastleProvider {
    INSTANCE;

    private Provider provider;
    private static boolean useBouncyCastle = true;

    GlobalBouncyCastleProvider() {
        try {
            this.provider = ProviderFactory.createBouncyCastleProvider();
        } catch (NoClassDefFoundError e) {
        }
    }

    public Provider getProvider() {
        if (useBouncyCastle) {
            return this.provider;
        }
        return null;
    }

    public static void setUseBouncyCastle(boolean isUseBouncyCastle) {
        useBouncyCastle = isUseBouncyCastle;
    }
}
