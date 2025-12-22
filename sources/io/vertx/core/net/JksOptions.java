package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/JksOptions.class */
public class JksOptions implements KeyCertOptions, TrustOptions, Cloneable {
    private String password;
    private String path;
    private Buffer value;

    public JksOptions() {
    }

    public JksOptions(JksOptions other) {
        this.password = other.getPassword();
        this.path = other.getPath();
        this.value = other.getValue();
    }

    public JksOptions(JsonObject json) {
        JksOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        JksOptionsConverter.toJson(this, json);
        return json;
    }

    public String getPassword() {
        return this.password;
    }

    public JksOptions setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPath() {
        return this.path;
    }

    public JksOptions setPath(String path) {
        this.path = path;
        return this;
    }

    public Buffer getValue() {
        return this.value;
    }

    public JksOptions setValue(Buffer value) {
        this.value = value;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JksOptions)) {
            return false;
        }
        JksOptions that = (JksOptions) o;
        if (this.password != null) {
            if (!this.password.equals(that.password)) {
                return false;
            }
        } else if (that.password != null) {
            return false;
        }
        if (this.path != null) {
            if (!this.path.equals(that.path)) {
                return false;
            }
        } else if (that.path != null) {
            return false;
        }
        if (this.value != null) {
            if (!this.value.equals(that.value)) {
                return false;
            }
            return true;
        }
        if (that.value != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = 1 + (31 * 1) + (this.password != null ? this.password.hashCode() : 0);
        int result2 = result + (31 * result) + (this.path != null ? this.path.hashCode() : 0);
        return result2 + (31 * result2) + (this.value != null ? this.value.hashCode() : 0);
    }

    @Override // io.vertx.core.net.TrustOptions
    public JksOptions copy() {
        return m1983clone();
    }

    @Override // io.vertx.core.net.TrustOptions
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public JksOptions m1983clone() {
        return new JksOptions(this);
    }
}
