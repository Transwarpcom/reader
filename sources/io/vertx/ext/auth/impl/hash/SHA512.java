package io.vertx.ext.auth.impl.hash;

import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/impl/hash/SHA512.class */
public class SHA512 implements HashingAlgorithm {
    private static final Base64.Encoder B64ENC = Base64.getEncoder();
    private final MessageDigest md;

    public SHA512() {
        try {
            this.md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("SHA-512 is not available", nsae);
        }
    }

    @Override // io.vertx.ext.auth.HashingAlgorithm
    public String id() {
        return "sha512";
    }

    @Override // io.vertx.ext.auth.HashingAlgorithm
    public String hash(HashString hashString, String password) {
        return B64ENC.encodeToString(this.md.digest(password.getBytes(StandardCharsets.UTF_8)));
    }
}
