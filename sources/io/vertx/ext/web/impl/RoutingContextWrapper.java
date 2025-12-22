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
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/RoutingContextWrapper.class */
public class RoutingContextWrapper extends RoutingContextImplBase {
    protected final RoutingContext inner;
    private final String mountPoint;

    public RoutingContextWrapper(String mountPoint, HttpServerRequest request, Set<RouteImpl> iter, RoutingContext inner) {
        super(mountPoint, request, iter);
        this.inner = inner;
        String parentMountPoint = inner.mountPoint();
        if (parentMountPoint == null) {
            this.mountPoint = mountPoint;
        } else if (parentMountPoint.charAt(parentMountPoint.length() - 1) == '/') {
            this.mountPoint = parentMountPoint.substring(0, parentMountPoint.length() - 1) + mountPoint;
        } else {
            this.mountPoint = parentMountPoint + mountPoint;
        }
    }

    @Override // io.vertx.ext.web.RoutingContext
    public HttpServerRequest request() {
        return this.inner.request();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public HttpServerResponse response() {
        return this.inner.response();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(int statusCode) {
        this.inner.fail(statusCode);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(Throwable throwable) {
        this.inner.fail(throwable);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void fail(int statusCode, Throwable throwable) {
        this.inner.fail(statusCode, throwable);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext put(String key, Object obj) {
        this.inner.put(key, obj);
        return this;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public <T> T get(String str) {
        return (T) this.inner.get(str);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public <T> T remove(String str) {
        return (T) this.inner.remove(str);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, Object> data() {
        return this.inner.data();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Vertx vertx() {
        return this.inner.vertx();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int addHeadersEndHandler(Handler<Void> handler) {
        return this.inner.addHeadersEndHandler(handler);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean removeHeadersEndHandler(int handlerID) {
        return this.inner.removeHeadersEndHandler(handlerID);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int addBodyEndHandler(Handler<Void> handler) {
        return this.inner.addBodyEndHandler(handler);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean removeBodyEndHandler(int handlerID) {
        return this.inner.removeBodyEndHandler(handlerID);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setSession(Session session) {
        this.inner.setSession(session);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Session session() {
        return this.inner.session();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setUser(User user) {
        this.inner.setUser(user);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void clearUser() {
        this.inner.clearUser();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public User user() {
        return this.inner.user();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void next() {
        if (!super.iterateNext()) {
            this.inner.next();
        }
    }

    @Override // io.vertx.ext.web.RoutingContext
    public boolean failed() {
        return this.inner.failed();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Throwable failure() {
        return this.inner.failure();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int statusCode() {
        return this.inner.statusCode();
    }

    @Override // io.vertx.ext.web.impl.RoutingContextImplBase, io.vertx.ext.web.RoutingContext
    public String mountPoint() {
        return this.mountPoint;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String normalisedPath() {
        return this.inner.normalisedPath();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Cookie getCookie(String name) {
        return this.inner.getCookie(name);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext addCookie(Cookie cookie) {
        this.inner.addCookie(cookie);
        return this;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public RoutingContext addCookie(io.vertx.core.http.Cookie cookie) {
        this.inner.addCookie(cookie);
        return this;
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Cookie removeCookie(String name, boolean invalidate) {
        return this.inner.removeCookie(name, invalidate);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public int cookieCount() {
        return this.inner.cookieCount();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Set<Cookie> cookies() {
        return this.inner.cookies();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, io.vertx.core.http.Cookie> cookieMap() {
        return this.inner.cookieMap();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getBodyAsString() {
        return this.inner.getBodyAsString();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getBodyAsString(String encoding) {
        return this.inner.getBodyAsString(encoding);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public JsonObject getBodyAsJson() {
        return this.inner.getBodyAsJson();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public JsonArray getBodyAsJsonArray() {
        return this.inner.getBodyAsJsonArray();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Buffer getBody() {
        return this.inner.getBody();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setBody(Buffer body) {
        this.inner.setBody(body);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Set<FileUpload> fileUploads() {
        return this.inner.fileUploads();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String getAcceptableContentType() {
        return this.inner.getAcceptableContentType();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public ParsedHeaderValues parsedHeaders() {
        return this.inner.parsedHeaders();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void setAcceptableContentType(String contentType) {
        this.inner.setAcceptableContentType(contentType);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public void reroute(HttpMethod method, String path) {
        this.inner.reroute(method, path);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public List<Locale> acceptableLocales() {
        return this.inner.acceptableLocales();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public Map<String, String> pathParams() {
        return this.inner.pathParams();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public String pathParam(String name) {
        return this.inner.pathParam(name);
    }

    @Override // io.vertx.ext.web.RoutingContext
    public MultiMap queryParams() {
        return this.inner.queryParams();
    }

    @Override // io.vertx.ext.web.RoutingContext
    public List<String> queryParam(String query) {
        return this.inner.queryParam(query);
    }
}
