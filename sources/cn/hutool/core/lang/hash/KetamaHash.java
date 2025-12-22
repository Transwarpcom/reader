package cn.hutool.core.lang.hash;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.StrUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/hash/KetamaHash.class */
public class KetamaHash implements Hash64<String>, Hash32<String> {
    @Override // cn.hutool.core.lang.hash.Hash64
    public long hash64(String key) throws NoSuchAlgorithmException {
        byte[] bKey = md5(key);
        return ((bKey[3] & 255) << 24) | ((bKey[2] & 255) << 16) | ((bKey[1] & 255) << 8) | (bKey[0] & 255);
    }

    @Override // cn.hutool.core.lang.hash.Hash32
    public int hash32(String key) {
        return (int) (hash64(key) & 4294967295L);
    }

    @Override // cn.hutool.core.lang.hash.Hash64, cn.hutool.core.lang.hash.Hash
    public Number hash(String key) {
        return Long.valueOf(hash64(key));
    }

    private static byte[] md5(String key) throws NoSuchAlgorithmException {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(StrUtil.utf8Bytes(key));
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException("MD5 algorithm not suooport!", e);
        }
    }
}
