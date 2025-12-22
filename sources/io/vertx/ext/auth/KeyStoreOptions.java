package io.vertx.ext.auth;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/KeyStoreOptions.class */
public class KeyStoreOptions {
    private static final String TYPE = "jceks";
    private String type;
    private String path;
    private String password;

    public KeyStoreOptions() {
        init();
    }

    public KeyStoreOptions(KeyStoreOptions other) {
        this.type = other.getType();
        this.path = other.getPath();
        this.password = other.getPassword();
    }

    private void init() {
        this.type = "jceks";
    }

    public KeyStoreOptions(JsonObject json) {
        init();
        KeyStoreOptionsConverter.fromJson(json, this);
    }

    public String getType() {
        return this.type;
    }

    public KeyStoreOptions setType(String type) {
        this.type = type;
        return this;
    }

    public String getPath() {
        return this.path;
    }

    public KeyStoreOptions setPath(String path) {
        this.path = path;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public KeyStoreOptions setPassword(String password) {
        this.password = password;
        return this;
    }
}
