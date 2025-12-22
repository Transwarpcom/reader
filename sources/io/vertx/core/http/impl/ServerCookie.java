package io.vertx.core.http.impl;

import io.vertx.core.http.Cookie;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/ServerCookie.class */
public interface ServerCookie extends Cookie {
    boolean isChanged();

    void setChanged(boolean z);

    boolean isFromUserAgent();
}
