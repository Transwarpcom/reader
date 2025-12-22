package io.vertx.core.net.impl;

import io.vertx.core.VertxException;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.SelfSignedCertificate;
import java.security.cert.CertificateException;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/SelfSignedCertificateImpl.class */
public class SelfSignedCertificateImpl implements SelfSignedCertificate {
    private final io.netty.handler.ssl.util.SelfSignedCertificate certificate;

    public SelfSignedCertificateImpl() {
        try {
            this.certificate = new io.netty.handler.ssl.util.SelfSignedCertificate();
        } catch (CertificateException e) {
            throw new VertxException(e);
        }
    }

    public SelfSignedCertificateImpl(String fqdn) {
        try {
            this.certificate = new io.netty.handler.ssl.util.SelfSignedCertificate(fqdn);
        } catch (CertificateException e) {
            throw new VertxException(e);
        }
    }

    @Override // io.vertx.core.net.SelfSignedCertificate
    public PemKeyCertOptions keyCertOptions() {
        return new PemKeyCertOptions().setKeyPath(privateKeyPath()).setCertPath(certificatePath());
    }

    @Override // io.vertx.core.net.SelfSignedCertificate
    public PemTrustOptions trustOptions() {
        return new PemTrustOptions().addCertPath(certificatePath());
    }

    @Override // io.vertx.core.net.SelfSignedCertificate
    public String privateKeyPath() {
        return this.certificate.privateKey().getAbsolutePath();
    }

    @Override // io.vertx.core.net.SelfSignedCertificate
    public String certificatePath() {
        return this.certificate.certificate().getAbsolutePath();
    }

    @Override // io.vertx.core.net.SelfSignedCertificate
    public void delete() {
        this.certificate.delete();
    }
}
