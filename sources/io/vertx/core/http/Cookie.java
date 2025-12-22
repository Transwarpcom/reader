package io.vertx.core.http;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.impl.CookieImpl;

@VertxGen
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/Cookie.class */
public interface Cookie {
    String getName();

    String getValue();

    @Fluent
    Cookie setValue(String str);

    @Fluent
    Cookie setDomain(String str);

    String getDomain();

    @Fluent
    Cookie setPath(String str);

    String getPath();

    @Fluent
    Cookie setMaxAge(long j);

    @Fluent
    Cookie setSecure(boolean z);

    @Fluent
    Cookie setHttpOnly(boolean z);

    String encode();

    static Cookie cookie(String name, String value) {
        return new CookieImpl(name, value);
    }
}
