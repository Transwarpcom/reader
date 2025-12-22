package io.vertx.core.net;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import java.util.Objects;

@DataObject(generateConverter = true, publicConverter = false)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/ProxyOptions.class */
public class ProxyOptions {
    public static final ProxyType DEFAULT_TYPE = ProxyType.HTTP;
    public static final int DEFAULT_PORT = 3128;
    public static final String DEFAULT_HOST = "localhost";
    private String host;
    private int port;
    private String username;
    private String password;
    private ProxyType type;

    public ProxyOptions() {
        this.host = "localhost";
        this.port = DEFAULT_PORT;
        this.type = DEFAULT_TYPE;
    }

    public ProxyOptions(ProxyOptions other) {
        this.host = other.getHost();
        this.port = other.getPort();
        this.username = other.getUsername();
        this.password = other.getPassword();
        this.type = other.getType();
    }

    public ProxyOptions(JsonObject json) {
        this();
        ProxyOptionsConverter.fromJson(json, this);
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        ProxyOptionsConverter.toJson(this, json);
        return json;
    }

    public String getHost() {
        return this.host;
    }

    public ProxyOptions setHost(String host) {
        Objects.requireNonNull(host, "Proxy host may not be null");
        this.host = host;
        return this;
    }

    public int getPort() {
        return this.port;
    }

    public ProxyOptions setPort(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Invalid proxy port " + port);
        }
        this.port = port;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public ProxyOptions setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public ProxyOptions setPassword(String password) {
        this.password = password;
        return this;
    }

    public ProxyType getType() {
        return this.type;
    }

    public ProxyOptions setType(ProxyType type) {
        Objects.requireNonNull(type, "Proxy type may not be null");
        this.type = type;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientOptionsBase) || !super.equals(o)) {
            return false;
        }
        ProxyOptions that = (ProxyOptions) o;
        return this.type == that.type && !this.host.equals(that.host) && this.port == that.port && Objects.equals(this.password, that.password) && Objects.equals(this.username, that.username);
    }

    public int hashCode() {
        int result = super.hashCode();
        return (31 * ((31 * ((31 * ((31 * ((31 * result) + this.type.hashCode())) + this.host.hashCode())) + this.port)) + (this.password != null ? this.password.hashCode() : 0))) + (this.username != null ? this.username.hashCode() : 0);
    }
}
