package cn.hutool.crypto;

import java.security.Provider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-crypto-5.8.0.M1.jar:cn/hutool/crypto/ProviderFactory.class */
public class ProviderFactory {
    public static Provider createBouncyCastleProvider() {
        return new BouncyCastleProvider();
    }
}
