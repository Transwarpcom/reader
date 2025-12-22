package cn.hutool.crypto.digest;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-crypto-5.8.0.M1.jar:cn/hutool/crypto/digest/DigestAlgorithm.class */
public enum DigestAlgorithm {
    MD2("MD2"),
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");

    private final String value;

    DigestAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
