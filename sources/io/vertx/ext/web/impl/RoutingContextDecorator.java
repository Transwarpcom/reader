package io.vertx.ext.web.impl;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Locale;
import io.vertx.ext.web.ParsedHeaderValues;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RoutingContextDecorator.class */
public class RoutingContextDecorator implements RoutingContext {
    private final Route currentRoute;
    private final RoutingContext decoratedContext;

    public RoutingContextDecorator(Route currentRoute, RoutingContext decoratedContext) {
        Objects.requireNonNull(currentRoute);
        Objects.requireNonNull(decoratedContext);
        this.currentRoute = currentRoute;
        this.decoratedContext = decoratedContext;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int addBodyEndHandler(Handler<Void> handler) {
        return this.decoratedContext.addBodyEndHandler(handler);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext addCookie(Cookie cookie) {
        return this.decoratedContext.addCookie(cookie);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext addCookie(io.vertx.core.http.Cookie cookie) {
        return this.decoratedContext.addCookie(cookie);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int addHeadersEndHandler(Handler<Void> handler) {
        return this.decoratedContext.addHeadersEndHandler(handler);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int cookieCount() {
        return this.decoratedContext.cookieCount();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Set<Cookie> cookies() {
        return this.decoratedContext.cookies();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, io.vertx.core.http.Cookie> cookieMap() {
        return this.decoratedContext.cookieMap();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Route currentRoute() {
        return this.currentRoute;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, Object> data() {
        return this.decoratedContext.data();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(int statusCode) {
        vertx().runOnContext(future -> {
            this.decoratedContext.fail(statusCode);
        });
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(Throwable throwable) {
        vertx().runOnContext(future -> {
            this.decoratedContext.fail(throwable);
        });
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(int statusCode, Throwable throwable) {
        vertx().runOnContext(future -> {
            this.decoratedContext.fail(statusCode, throwable);
        });
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean failed() {
        return this.decoratedContext.failed();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Throwable failure() {
        return this.decoratedContext.failure();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Set<FileUpload> fileUploads() {
        return this.decoratedContext.fileUploads();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public <T> T get(String str) {
        return (T) this.decoratedContext.get(str);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public <T> T remove(String str) {
        return (T) this.decoratedContext.remove(str);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getAcceptableContentType() {
        return this.decoratedContext.getAcceptableContentType();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Buffer getBody() {
        return this.decoratedContext.getBody();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public JsonObject getBodyAsJson() {
        return this.decoratedContext.getBodyAsJson();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public JsonArray getBodyAsJsonArray() {
        return this.decoratedContext.getBodyAsJsonArray();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getBodyAsString() {
        return this.decoratedContext.getBodyAsString();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getBodyAsString(String encoding) {
        return this.decoratedContext.getBodyAsString(encoding);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Cookie getCookie(String name) {
        return this.decoratedContext.getCookie(name);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String mountPoint() {
        return this.decoratedContext.mountPoint();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void next() {
        vertx().runOnContext(future -> {
            this.decoratedContext.next();
        });
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String normalisedPath() {
        return this.decoratedContext.normalisedPath();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext put(String key, Object obj) {
        return this.decoratedContext.put(key, obj);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean removeBodyEndHandler(int handlerID) {
        return this.decoratedContext.removeBodyEndHandler(handlerID);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Cookie removeCookie(String name, boolean invalidate) {
        return this.decoratedContext.removeCookie(name, invalidate);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean removeHeadersEndHandler(int handlerID) {
        return this.decoratedContext.removeHeadersEndHandler(handlerID);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public HttpServerRequest request() {
        return this.decoratedContext.request();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public HttpServerResponse response() {
        return this.decoratedContext.response();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public User user() {
        return this.decoratedContext.user();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Session session() {
        return this.decoratedContext.session();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public ParsedHeaderValues parsedHeaders() {
        return this.decoratedContext.parsedHeaders();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setAcceptableContentType(String contentType) {
        this.decoratedContext.setAcceptableContentType(contentType);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void reroute(HttpMethod method, String path) {
        this.decoratedContext.reroute(method, path);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public List<Locale> acceptableLocales() {
        return this.decoratedContext.acceptableLocales();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, String> pathParams() {
        return this.decoratedContext.pathParams();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String pathParam(String name) {
        return this.decoratedContext.pathParam(name);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public MultiMap queryParams() {
        return this.decoratedContext.queryParams();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public List<String> queryParam(String query) {
        return this.decoratedContext.queryParam(query);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setBody(Buffer body) {
        this.decoratedContext.setBody(body);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setSession(Session session) {
        this.decoratedContext.setSession(session);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setUser(User user) {
        this.decoratedContext.setUser(user);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void clearUser() {
        this.decoratedContext.clearUser();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int statusCode() {
        return this.decoratedContext.statusCode();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Vertx vertx() {
        return this.decoratedContext.vertx();
    }
}
