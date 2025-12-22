package io.vertx.ext.web.client;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.ext.web.client.impl.WebClientSessionAware;
import io.vertx.ext.web.client.spi.CookieStore;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/WebClientSession.class */
public interface WebClientSession extends WebClient {
    @Fluent
    WebClientSession addHeader(CharSequence charSequence, CharSequence charSequence2);

    @Fluent
    WebClientSession addHeader(String str, String str2);

    @Fluent
    WebClientSession addHeader(CharSequence charSequence, Iterable<CharSequence> iterable);

    @Fluent
    WebClientSession addHeader(String str, Iterable<String> iterable);

    @Fluent
    WebClientSession removeHeader(CharSequence charSequence);

    @Fluent
    WebClientSession removeHeader(String str);

    CookieStore cookieStore();

    static WebClientSession create(WebClient webClient) {
        return create(webClient, CookieStore.build());
    }

    static WebClientSession create(WebClient webClient, CookieStore cookieStore) {
        return new WebClientSessionAware(webClient, cookieStore);
    }
}
