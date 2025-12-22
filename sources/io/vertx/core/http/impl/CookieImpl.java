package io.vertx.core.http.impl;

import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/CookieImpl.class */
public class CookieImpl implements ServerCookie {
    private final Cookie nettyCookie;
    private boolean changed;
    private boolean fromUserAgent;

    static Map<String, ServerCookie> extractCookies(CharSequence cookieHeader) {
        if (cookieHeader != null) {
            Set<Cookie> nettyCookies = ServerCookieDecoder.STRICT.decode(cookieHeader.toString());
            Map<String, ServerCookie> cookies = new HashMap<>(nettyCookies.size());
            for (Cookie cookie : nettyCookies) {
                ServerCookie ourCookie = new CookieImpl(cookie);
                cookies.put(ourCookie.getName(), ourCookie);
            }
            return cookies;
        }
        return new HashMap(4);
    }

    static io.vertx.core.http.Cookie removeCookie(Map<String, ServerCookie> cookieMap, String name, boolean invalidate) {
        ServerCookie cookie = cookieMap.get(name);
        if (cookie != null) {
            if (invalidate && cookie.isFromUserAgent()) {
                cookie.setMaxAge(0L);
            } else {
                cookieMap.remove(name);
            }
        }
        return cookie;
    }

    public CookieImpl(String name, String value) {
        this.nettyCookie = new DefaultCookie(name, value);
        this.changed = true;
    }

    public CookieImpl(Cookie nettyCookie) {
        this.nettyCookie = nettyCookie;
        this.fromUserAgent = true;
    }

    @Override // io.vertx.core.http.Cookie
    public String getValue() {
        return this.nettyCookie.value();
    }

    @Override // io.vertx.core.http.Cookie
    public io.vertx.core.http.Cookie setValue(String value) {
        this.nettyCookie.setValue(value);
        this.changed = true;
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String getName() {
        return this.nettyCookie.name();
    }

    @Override // io.vertx.core.http.Cookie
    public io.vertx.core.http.Cookie setDomain(String domain) {
        this.nettyCookie.setDomain(domain);
        this.changed = true;
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String getDomain() {
        return this.nettyCookie.domain();
    }

    @Override // io.vertx.core.http.Cookie
    public io.vertx.core.http.Cookie setPath(String path) {
        this.nettyCookie.setPath(path);
        this.changed = true;
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String getPath() {
        return this.nettyCookie.path();
    }

    @Override // io.vertx.core.http.Cookie
    public io.vertx.core.http.Cookie setMaxAge(long maxAge) {
        this.nettyCookie.setMaxAge(maxAge);
        this.changed = true;
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public io.vertx.core.http.Cookie setSecure(boolean secure) {
        this.nettyCookie.setSecure(secure);
        this.changed = true;
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public io.vertx.core.http.Cookie setHttpOnly(boolean httpOnly) {
        this.nettyCookie.setHttpOnly(httpOnly);
        this.changed = true;
        return this;
    }

    @Override // io.vertx.core.http.Cookie
    public String encode() {
        return ServerCookieEncoder.STRICT.encode(this.nettyCookie);
    }

    @Override // io.vertx.core.http.impl.ServerCookie
    public boolean isChanged() {
        return this.changed;
    }

    @Override // io.vertx.core.http.impl.ServerCookie
    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @Override // io.vertx.core.http.impl.ServerCookie
    public boolean isFromUserAgent() {
        return this.fromUserAgent;
    }
}
