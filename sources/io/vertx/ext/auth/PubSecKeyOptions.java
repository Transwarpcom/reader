package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/PubSecKeyOptions.class */
public class PubSecKeyOptions {
    private String algorithm;
    private boolean certificate;
    private boolean symmetric;
    private String publicKey;
    private String secretKey;

    public PubSecKeyOptions() {
    }

    public PubSecKeyOptions(PubSecKeyOptions other) {
        this.algorithm = other.getAlgorithm();
        this.publicKey = other.getPublicKey();
        this.secretKey = other.getSecretKey();
        this.symmetric = other.isSymmetric();
        this.certificate = other.isCertificate();
    }

    public PubSecKeyOptions(JsonObject json) {
        PubSecKeyOptionsConverter.fromJson(json, this);
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public PubSecKeyOptions setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
        return this;
    }

    @Deprecated
    public String getPublicKey() {
        return this.publicKey;
    }

    @Deprecated
    public PubSecKeyOptions setPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    @Deprecated
    public String getSecretKey() {
        return this.secretKey;
    }

    @Deprecated
    public PubSecKeyOptions setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    @Deprecated
    public boolean isSymmetric() {
        return this.symmetric;
    }

    @Deprecated
    public PubSecKeyOptions setSymmetric(boolean symmetric) {
        this.symmetric = symmetric;
        return this;
    }

    @Deprecated
    public boolean isCertificate() {
        return this.certificate;
    }

    @Deprecated
    public PubSecKeyOptions setCertificate(boolean certificate) {
        this.certificate = certificate;
        return this;
    }
}
