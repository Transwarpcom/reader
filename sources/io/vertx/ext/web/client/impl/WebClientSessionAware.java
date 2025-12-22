package io.vertx.ext.web.client.impl;

import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientSession;
import io.vertx.ext.web.client.spi.CookieStore;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/WebClientSessionAware.class */
public class WebClientSessionAware extends WebClientBase implements WebClientSession {
    private final CookieStore cookieStore;
    private CaseInsensitiveHeaders headers;

    public WebClientSessionAware(WebClient webClient, CookieStore cookieStore) {
        super((WebClientBase) webClient);
        this.cookieStore = cookieStore;
        addInterceptor(new SessionAwareInterceptor());
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public CookieStore cookieStore() {
        return this.cookieStore;
    }

    protected CaseInsensitiveHeaders headers() {
        if (this.headers == null) {
            this.headers = new CaseInsensitiveHeaders();
        }
        return this.headers;
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public WebClientSession addHeader(CharSequence name, CharSequence value) {
        headers().add(name, value);
        return this;
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public WebClientSession addHeader(String name, String value) {
        headers().add(name, value);
        return this;
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public WebClientSession addHeader(CharSequence name, Iterable<CharSequence> values) {
        headers().mo1937add(name, values);
        return this;
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public WebClientSession addHeader(String name, Iterable<String> values) {
        headers().mo1938add(name, values);
        return this;
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public WebClientSession removeHeader(CharSequence name) {
        headers().mo1933remove(name);
        return this;
    }

    @Override // io.vertx.ext.web.client.WebClientSession
    public WebClientSession removeHeader(String name) {
        headers().mo1934remove(name);
        return this;
    }
}
