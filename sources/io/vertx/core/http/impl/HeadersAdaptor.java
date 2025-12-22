package io.vertx.core.http.impl;

import io.netty.handler.codec.http.HttpHeaders;
import io.vertx.core.MultiMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HeadersAdaptor.class */
public class HeadersAdaptor implements MultiMap {
    private final HttpHeaders headers;

    public HeadersAdaptor(HttpHeaders headers) {
        this.headers = headers;
    }

    @Override // io.vertx.core.MultiMap
    public String get(String name) {
        return this.headers.get(name);
    }

    @Override // io.vertx.core.MultiMap
    public List<String> getAll(String name) {
        return this.headers.getAll(name);
    }

    @Override // io.vertx.core.MultiMap
    public List<Map.Entry<String, String>> entries() {
        return this.headers.entries();
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(String name) {
        return this.headers.contains(name);
    }

    @Override // io.vertx.core.MultiMap
    public boolean isEmpty() {
        return this.headers.isEmpty();
    }

    @Override // io.vertx.core.MultiMap
    public Set<String> names() {
        return this.headers.names();
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap add(String name, String value) {
        this.headers.add(name, (Object) value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add */
    public MultiMap mo1938add(String name, Iterable<String> values) {
        this.headers.add(name, (Iterable<?>) values);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap addAll(MultiMap headers) {
        for (Map.Entry<String, String> entry : headers.entries()) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap addAll(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap set(String name, String value) {
        this.headers.set(name, (Object) value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set */
    public MultiMap mo1936set(String name, Iterable<String> values) {
        this.headers.set(name, (Iterable<?>) values);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap setAll(MultiMap httpHeaders) {
        mo1932clear();
        for (Map.Entry<String, String> entry : httpHeaders) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove */
    public MultiMap mo1934remove(String name) {
        this.headers.remove(name);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: clear */
    public MultiMap mo1932clear() {
        this.headers.clear();
        return this;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<String, String>> iterator() {
        return this.headers.iteratorAsString();
    }

    @Override // io.vertx.core.MultiMap
    public int size() {
        return names().size();
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap setAll(Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public String get(CharSequence name) {
        return this.headers.get(name);
    }

    @Override // io.vertx.core.MultiMap
    public List<String> getAll(CharSequence name) {
        return this.headers.getAll(name);
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(CharSequence name) {
        return this.headers.contains(name);
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(String name, String value, boolean caseInsensitive) {
        return this.headers.contains(name, value, caseInsensitive);
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
        return this.headers.contains(name, value, caseInsensitive);
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap add(CharSequence name, CharSequence value) {
        this.headers.add(name, value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add */
    public MultiMap mo1937add(CharSequence name, Iterable<CharSequence> values) {
        this.headers.add(name, (Iterable<?>) values);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap set(CharSequence name, CharSequence value) {
        this.headers.set(name, value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set */
    public MultiMap mo1935set(CharSequence name, Iterable<CharSequence> values) {
        this.headers.set(name, (Iterable<?>) values);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove */
    public MultiMap mo1933remove(CharSequence name) {
        this.headers.remove(name);
        return this;
    }
}
