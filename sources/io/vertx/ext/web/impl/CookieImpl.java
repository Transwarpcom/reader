package io.vertx.ext.web.impl;

import io.vertx.core.http.impl.ServerCookie;
import io.vertx.ext.web.Cookie;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/impl/CookieImpl.class */
public class CookieImpl implements Cookie, ServerCookie {
    final ServerCookie delegate;

    static Cookie wrapIfNecessary(ServerCookie cookie) {
        if (cookie == null) {
            return null;
        }
        return cookie instanceof Cookie ? (Cookie) cookie : new CookieImpl(cookie);
    }

    public CookieImpl(String name, String value) {
        this.delegate = (ServerCookie) io.vertx.core.http.Cookie.cookie(name, value);
    }

    public CookieImpl(ServerCookie delegate) {
        this.delegate = delegate;
    }

    public CookieImpl(io.netty.handler.codec.http.cookie.Cookie nettyCookie) {
        this.delegate = new io.vertx.core.http.impl.CookieImpl(nettyCookie);
    }

    @Override // io.vertx.core.http.Cookie
    public String getValue() {
        return this.delegate.getValue();
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.Cookie
    public Cookie setValue(String value) {
        this.delegate.setValue(value);
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String getName() {
        return this.delegate.getName();
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.Cookie
    public Cookie setDomain(String domain) {
        this.delegate.setDomain(domain);
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String getDomain() {
        return this.delegate.getDomain();
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.Cookie
    public Cookie setPath(String path) {
        this.delegate.setPath(path);
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String getPath() {
        return this.delegate.getPath();
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.Cookie
    public Cookie setMaxAge(long maxAge) {
        this.delegate.setMaxAge(maxAge);
        return this;
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.Cookie
    public Cookie setSecure(boolean secure) {
        this.delegate.setSecure(secure);
        return this;
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.Cookie
    public Cookie setHttpOnly(boolean httpOnly) {
        this.delegate.setHttpOnly(httpOnly);
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String encode() {
        return this.delegate.encode();
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.impl.ServerCookie
    public boolean isChanged() {
        return this.delegate.isChanged();
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.impl.ServerCookie
    public void setChanged(boolean changed) {
        this.delegate.setChanged(changed);
    }

    @Override // io.vertx.ext.web.Cookie, io.vertx.core.http.impl.ServerCookie
    public boolean isFromUserAgent() {
        return this.delegate.isFromUserAgent();
    }
}
