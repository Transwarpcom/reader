package cn.hutool.crypto.digest.mac;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-crypto-5.8.0.M1.jar:cn/hutool/crypto/digest/mac/MacEngineFactory.class */
public class MacEngineFactory {
    public static MacEngine createEngine(String algorithm, Key key) {
        return createEngine(algorithm, key, null);
    }

    public static MacEngine createEngine(String algorithm, Key key, AlgorithmParameterSpec spec) {
        if (algorithm.equalsIgnoreCase(HmacAlgorithm.HmacSM3.getValue())) {
            return SmUtil.createHmacSm3Engine(key.getEncoded());
        }
        return new DefaultHMacEngine(algorithm, key, spec);
    }
}
