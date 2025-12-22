package cn.hutool.crypto;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-crypto-5.8.0.M1.jar:cn/hutool/crypto/CryptoException.class */
public class CryptoException extends RuntimeException {
    private static final long serialVersionUID = 8068509879445395353L;

    public CryptoException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CryptoException(String message, Throwable throwable, boolean enableSuppression, boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    public CryptoException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
