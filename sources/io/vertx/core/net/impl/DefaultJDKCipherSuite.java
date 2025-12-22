package io.vertx.core.net.impl;

import cn.hutool.core.net.SSLProtocols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/DefaultJDKCipherSuite.class */
class DefaultJDKCipherSuite {
    private static final List<String> DEFAULT_JDK_CIPHER_SUITE;

    DefaultJDKCipherSuite() {
    }

    static {
        ArrayList<String> suite = new ArrayList<>();
        try {
            SSLContext context = SSLContext.getInstance(SSLProtocols.TLS);
            context.init(null, null, null);
            SSLEngine engine = context.createSSLEngine();
            Collections.addAll(suite, engine.getEnabledCipherSuites());
        } catch (Throwable th) {
            suite = null;
        }
        DEFAULT_JDK_CIPHER_SUITE = suite != null ? Collections.unmodifiableList(suite) : null;
    }

    static List<String> get() {
        return DEFAULT_JDK_CIPHER_SUITE;
    }
}
