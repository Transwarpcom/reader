package cn.hutool.crypto.digest;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-crypto-5.8.0.M1.jar:cn/hutool/crypto/digest/HmacAlgorithm.class */
public enum HmacAlgorithm {
    HmacMD5("HmacMD5"),
    HmacSHA1("HmacSHA1"),
    HmacSHA256("HmacSHA256"),
    HmacSHA384("HmacSHA384"),
    HmacSHA512("HmacSHA512"),
    HmacSM3("HmacSM3");

    private final String value;

    HmacAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
