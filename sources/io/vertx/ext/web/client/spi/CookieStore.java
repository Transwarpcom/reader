package io.vertx.ext.web.client.spi;

import io.netty.handler.codec.http.cookie.Cookie;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.ext.web.client.impl.CookieStoreImpl;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/spi/CookieStore.class */
public interface CookieStore {
    Iterable<Cookie> get(Boolean bool, String str, String str2);

    @Fluent
    CookieStore put(Cookie cookie);

    @Fluent
    CookieStore remove(Cookie cookie);

    static CookieStore build() {
        return new CookieStoreImpl();
    }
}
