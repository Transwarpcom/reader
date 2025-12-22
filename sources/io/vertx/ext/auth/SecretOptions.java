package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/SecretOptions.class */
public class SecretOptions {
    private static final String TYPE = "HS256";
    private String type;
    private String secret;

    public SecretOptions() {
        init();
    }

    public SecretOptions(SecretOptions other) {
        this.type = other.getType();
        this.secret = other.getSecret();
    }

    public SecretOptions(JsonObject json) {
        init();
        SecretOptionsConverter.fromJson(json, this);
    }

    public String getType() {
        return this.type;
    }

    public SecretOptions setType(String type) {
        this.type = type;
        return this;
    }

    public String getSecret() {
        return this.secret;
    }

    public SecretOptions setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    private void init() {
        this.type = TYPE;
    }
}
