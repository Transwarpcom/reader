package io.vertx.ext.web;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.web.impl.CookieImpl;

@VertxGen
@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/Cookie.class */
public interface Cookie extends io.vertx.core.http.Cookie {
    @Override // io.vertx.core.http.Cookie
    @Fluent
    Cookie setValue(String str);

    @Override // io.vertx.core.http.Cookie
    @Fluent
    Cookie setDomain(String str);

    @Override // io.vertx.core.http.Cookie
    @Fluent
    Cookie setPath(String str);

    @Override // io.vertx.core.http.Cookie
    @Fluent
    Cookie setMaxAge(long j);

    @Override // io.vertx.core.http.Cookie
    @Fluent
    Cookie setSecure(boolean z);

    @Override // io.vertx.core.http.Cookie
    @Fluent
    Cookie setHttpOnly(boolean z);

    boolean isChanged();

    void setChanged(boolean z);

    boolean isFromUserAgent();

    static Cookie cookie(String name, String value) {
        return new CookieImpl(name, value);
    }

    @GenIgnore
    static Cookie cookie(io.netty.handler.codec.http.cookie.Cookie nettyCookie) {
        return new CookieImpl(nettyCookie);
    }
}
