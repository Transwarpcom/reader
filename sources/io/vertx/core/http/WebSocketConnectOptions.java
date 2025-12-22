package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

@DataObject(generateConverter = true)
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/WebSocketConnectOptions.class */
public class WebSocketConnectOptions extends RequestOptions {
    public static final WebsocketVersion DEFAULT_VERSION = WebsocketVersion.V13;
    public static final List<String> DEFAULT_SUB_PROTOCOLS = null;
    private WebsocketVersion version;
    private List<String> subProtocols;

    public WebSocketConnectOptions() {
        this.version = DEFAULT_VERSION;
        this.subProtocols = DEFAULT_SUB_PROTOCOLS;
    }

    public WebSocketConnectOptions(WebSocketConnectOptions other) {
        super(other);
        this.version = other.version;
        this.subProtocols = other.subProtocols;
    }

    public WebSocketConnectOptions(RequestOptions other) {
        super(other);
        this.version = DEFAULT_VERSION;
        this.subProtocols = DEFAULT_SUB_PROTOCOLS;
    }

    public WebSocketConnectOptions(JsonObject json) {
        super(json);
        WebSocketConnectOptionsConverter.fromJson(json, this);
    }

    public WebsocketVersion getVersion() {
        return this.version;
    }

    public WebSocketConnectOptions setVersion(WebsocketVersion version) {
        this.version = version;
        return this;
    }

    public List<String> getSubProtocols() {
        return this.subProtocols;
    }

    public WebSocketConnectOptions setSubProtocols(List<String> subProtocols) {
        this.subProtocols = subProtocols;
        return this;
    }

    public WebSocketConnectOptions addSubProtocol(String subprotocol) {
        if (this.subProtocols == null) {
            this.subProtocols = new ArrayList();
        }
        this.subProtocols.add(subprotocol);
        return this;
    }

    @Override // io.vertx.core.http.RequestOptions
    public WebSocketConnectOptions setHost(String host) {
        return (WebSocketConnectOptions) super.setHost(host);
    }

    @Override // io.vertx.core.http.RequestOptions
    public WebSocketConnectOptions setPort(int port) {
        return (WebSocketConnectOptions) super.setPort(port);
    }

    @Override // io.vertx.core.http.RequestOptions
    public WebSocketConnectOptions setSsl(Boolean ssl) {
        return (WebSocketConnectOptions) super.setSsl(ssl);
    }

    @Override // io.vertx.core.http.RequestOptions
    public WebSocketConnectOptions setURI(String uri) {
        return (WebSocketConnectOptions) super.setURI(uri);
    }

    @Override // io.vertx.core.http.RequestOptions
    public WebSocketConnectOptions addHeader(String key, String value) {
        return (WebSocketConnectOptions) super.addHeader(key, value);
    }

    @Override // io.vertx.core.http.RequestOptions
    @GenIgnore
    public WebSocketConnectOptions setHeaders(MultiMap headers) {
        return (WebSocketConnectOptions) super.setHeaders(headers);
    }

    @Override // io.vertx.core.http.RequestOptions
    public JsonObject toJson() {
        JsonObject json = super.toJson();
        WebSocketConnectOptionsConverter.toJson(this, json);
        return json;
    }
}
