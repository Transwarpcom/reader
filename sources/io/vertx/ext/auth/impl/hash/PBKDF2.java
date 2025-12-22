package io.vertx.ext.auth.impl.hash;

import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Collections;
import java.util.Set;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/impl/hash/PBKDF2.class */
public class PBKDF2 implements HashingAlgorithm {
    private static final int DEFAULT_ITERATIONS = 10000;
    private static final Base64.Decoder B64DEC = Base64.getDecoder();
    private static final Base64.Encoder B64ENC = Base64.getEncoder();
    private static final Set<String> DEFAULT_CONFIG = Collections.singleton("it");
    private final SecretKeyFactory skf;

    public PBKDF2() {
        try {
            this.skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("PBKDF2 is not available", nsae);
        }
    }

    @Override // io.vertx.ext.auth.HashingAlgorithm
    public String id() {
        return "pbkdf2";
    }

    @Override // io.vertx.ext.auth.HashingAlgorithm
    public Set<String> params() {
        return DEFAULT_CONFIG;
    }

    @Override // io.vertx.ext.auth.HashingAlgorithm
    public String hash(HashString hashString, String password) {
        int iterations;
        try {
            if (hashString.params() != null) {
                iterations = Integer.getInteger(hashString.params().get("it")).intValue();
            } else {
                iterations = 10000;
            }
        } catch (RuntimeException e) {
            iterations = 10000;
        }
        if (hashString.salt() == null) {
            throw new RuntimeException("hashString salt is null");
        }
        byte[] salt = B64DEC.decode(hashString.salt());
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 512);
        try {
            return B64ENC.encodeToString(this.skf.generateSecret(spec).getEncoded());
        } catch (InvalidKeySpecException ikse) {
            throw new RuntimeException(ikse);
        }
    }
}
