package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import javax.net.ssl.SSLEngine;

@DataObject
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/JdkSSLEngineOptions.class */
public class JdkSSLEngineOptions extends SSLEngineOptions {
    private static Boolean jdkAlpnAvailable;

    public static synchronized boolean isAlpnAvailable() {
        if (jdkAlpnAvailable == null) {
            boolean available = false;
            try {
                try {
                    SSLEngine.class.getDeclaredMethod("getApplicationProtocol", new Class[0]);
                    available = true;
                    jdkAlpnAvailable = true;
                } catch (Throwable th) {
                    jdkAlpnAvailable = Boolean.valueOf(available);
                    throw th;
                }
            } catch (Exception e) {
                try {
                    JdkSSLEngineOptions.class.getClassLoader().loadClass("sun.security.ssl.ALPNExtension");
                    available = true;
                } catch (Exception e2) {
                }
                jdkAlpnAvailable = Boolean.valueOf(available);
            }
        }
        return jdkAlpnAvailable.booleanValue();
    }

    public JdkSSLEngineOptions() {
    }

    public JdkSSLEngineOptions(JsonObject json) {
    }

    public JdkSSLEngineOptions(JdkSSLEngineOptions that) {
    }

    public JsonObject toJson() {
        return new JsonObject();
    }

    public int hashCode() {
        return 0;
    }

    public boolean equals(Object o) {
        return this == o || (o instanceof JdkSSLEngineOptions);
    }

    @Override // io.vertx.core.net.SSLEngineOptions
    /* renamed from: clone */
    public JdkSSLEngineOptions mo1979clone() {
        return new JdkSSLEngineOptions();
    }

    @Override // io.vertx.core.net.SSLEngineOptions
    public JdkSSLEngineOptions copy() {
        return new JdkSSLEngineOptions();
    }
}
