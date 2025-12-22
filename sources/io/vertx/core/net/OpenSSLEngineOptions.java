package io.vertx.core.net;

import io.netty.handler.ssl.OpenSsl;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/OpenSSLEngineOptions.class */
public class OpenSSLEngineOptions extends SSLEngineOptions {
    public static final boolean DEFAULT_SESSION_CACHE_ENABLED = true;
    private boolean sessionCacheEnabled;

    public static boolean isAvailable() {
        return OpenSsl.isAvailable();
    }

    public static boolean isAlpnAvailable() {
        return OpenSsl.isAlpnSupported();
    }

    public OpenSSLEngineOptions() {
        this.sessionCacheEnabled = true;
    }

    public OpenSSLEngineOptions(JsonObject json) {
        OpenSSLEngineOptionsConverter.fromJson(json, this);
    }

    public OpenSSLEngineOptions(OpenSSLEngineOptions other) {
        this.sessionCacheEnabled = other.isSessionCacheEnabled();
    }

    public OpenSSLEngineOptions setSessionCacheEnabled(boolean sessionCacheEnabled) {
        this.sessionCacheEnabled = sessionCacheEnabled;
        return this;
    }

    public boolean isSessionCacheEnabled() {
        return this.sessionCacheEnabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpenSSLEngineOptions)) {
            return false;
        }
        OpenSSLEngineOptions that = (OpenSSLEngineOptions) o;
        return this.sessionCacheEnabled == that.sessionCacheEnabled;
    }

    public int hashCode() {
        return this.sessionCacheEnabled ? 1 : 0;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        OpenSSLEngineOptionsConverter.toJson(this, json);
        return json;
    }

    @Override // io.vertx.core.net.SSLEngineOptions
    /* renamed from: clone */
    public OpenSSLEngineOptions mo1979clone() {
        return new OpenSSLEngineOptions(this);
    }

    @Override // io.vertx.core.net.SSLEngineOptions
    public OpenSSLEngineOptions copy() {
        return new OpenSSLEngineOptions(this);
    }
}
