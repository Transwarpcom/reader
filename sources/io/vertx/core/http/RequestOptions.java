package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import java.util.Objects;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/RequestOptions.class */
public class RequestOptions {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 80;
    public static final Boolean DEFAULT_SSL = null;
    public static final String DEFAULT_URI = "";
    private String host;
    private int port;
    private Boolean ssl;
    private String uri;
    private MultiMap headers;

    public RequestOptions() {
        this.host = "localhost";
        this.port = 80;
        this.ssl = DEFAULT_SSL;
        this.uri = "";
    }

    public RequestOptions(RequestOptions other) {
        setHost(other.host);
        setPort(other.port);
        setSsl(other.ssl);
        setURI(other.uri);
    }

    public RequestOptions(JsonObject json) {
        RequestOptionsConverter.fromJson(json, this);
    }

    public String getHost() {
        return this.host;
    }

    public RequestOptions setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return this.port;
    }

    public RequestOptions setPort(int port) {
        this.port = port;
        return this;
    }

    public Boolean isSsl() {
        return this.ssl;
    }

    public RequestOptions setSsl(Boolean ssl) {
        this.ssl = ssl;
        return this;
    }

    public String getURI() {
        return this.uri;
    }

    public RequestOptions setURI(String uri) {
        this.uri = uri;
        return this;
    }

    public RequestOptions addHeader(String key, String value) {
        checkHeaders();
        Objects.requireNonNull(key, "no null key accepted");
        Objects.requireNonNull(value, "no null value accepted");
        this.headers.add(key, value);
        return this;
    }

    @GenIgnore
    public RequestOptions setHeaders(MultiMap headers) {
        this.headers = headers;
        return this;
    }

    @GenIgnore
    public MultiMap getHeaders() {
        return this.headers;
    }

    private void checkHeaders() {
        if (this.headers == null) {
            this.headers = new CaseInsensitiveHeaders();
        }
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        RequestOptionsConverter.toJson(this, json);
        return json;
    }
}
