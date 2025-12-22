package io.vertx.core.http.impl;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpHeaders;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/Http2HeadersAdaptor.class */
public class Http2HeadersAdaptor implements MultiMap {
    private final Http2Headers headers;
    private Set<String> names;

    static CharSequence toLowerCase(CharSequence s) {
        StringBuilder buffer = null;
        int len = s.length();
        for (int index = 0; index < len; index++) {
            char c = s.charAt(index);
            if (c >= 'A' && c <= 'Z') {
                if (buffer == null) {
                    buffer = new StringBuilder(s);
                }
                buffer.setCharAt(index, (char) (c + ' '));
            }
        }
        if (buffer != null) {
            return buffer.toString();
        }
        return s;
    }

    public Http2HeadersAdaptor(Http2Headers headers) {
        List<CharSequence> cookies = headers.getAll(HttpHeaderNames.COOKIE);
        if (cookies != null && cookies.size() > 1) {
            String value = (String) cookies.stream().collect(Collectors.joining("; "));
            headers.set((Http2Headers) HttpHeaderNames.COOKIE, (AsciiString) value);
        }
        this.headers = headers;
    }

    @Override // io.vertx.core.MultiMap
    public String get(String name) {
        CharSequence val = this.headers.get(toLowerCase(name));
        if (val != null) {
            return val.toString();
        }
        return null;
    }

    @Override // io.vertx.core.MultiMap
    public List<String> getAll(String name) {
        final List<CharSequence> all = this.headers.getAll(toLowerCase(name));
        if (all != null) {
            return new AbstractList<String>() { // from class: io.vertx.core.http.impl.Http2HeadersAdaptor.1
                @Override // java.util.AbstractList, java.util.List
                public String get(int index) {
                    return ((CharSequence) all.get(index)).toString();
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return all.size();
                }
            };
        }
        return null;
    }

    @Override // io.vertx.core.MultiMap
    public List<Map.Entry<String, String>> entries() {
        return (List) this.headers.names().stream().map(name -> {
            return new AbstractMap.SimpleEntry(name.toString(), this.headers.get(name).toString());
        }).collect(Collectors.toList());
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(String name) {
        return this.headers.contains(toLowerCase(name));
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(String name, String value, boolean caseInsensitive) {
        return this.headers.contains(toLowerCase(name), value, caseInsensitive);
    }

    @Override // io.vertx.core.MultiMap
    public boolean isEmpty() {
        return this.headers.isEmpty();
    }

    @Override // io.vertx.core.MultiMap
    public Set<String> names() {
        if (this.names == null) {
            this.names = new AbstractSet<String>() { // from class: io.vertx.core.http.impl.Http2HeadersAdaptor.2
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<String> iterator() {
                    final Iterator<CharSequence> it = Http2HeadersAdaptor.this.headers.names().iterator();
                    return new Iterator<String>() { // from class: io.vertx.core.http.impl.Http2HeadersAdaptor.2.1
                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            return it.hasNext();
                        }

                        /* JADX WARN: Can't rename method to resolve collision */
                        @Override // java.util.Iterator
                        public String next() {
                            return ((CharSequence) it.next()).toString();
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return Http2HeadersAdaptor.this.headers.size();
                }
            };
        }
        return this.names;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap add(String name, String value) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, value);
        }
        this.headers.add((Http2Headers) toLowerCase(name), (CharSequence) value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add */
    public MultiMap mo1938add(String name, Iterable<String> values) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, values);
        }
        this.headers.add((Http2Headers) toLowerCase(name), (Iterable) values);
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
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, value);
        }
        this.headers.set((Http2Headers) toLowerCase(name), (CharSequence) value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set */
    public MultiMap mo1936set(String name, Iterable<String> values) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, values);
        }
        this.headers.set((Http2Headers) toLowerCase(name), (Iterable) values);
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
        this.headers.remove(toLowerCase(name));
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
        return entries().iterator();
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
        CharSequence val = this.headers.get(toLowerCase(name));
        if (val != null) {
            return val.toString();
        }
        return null;
    }

    @Override // io.vertx.core.MultiMap
    public List<String> getAll(CharSequence name) {
        List<CharSequence> all = this.headers.getAll(toLowerCase(name));
        if (all != null) {
            return (List) all.stream().map((v0) -> {
                return v0.toString();
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(CharSequence name) {
        return this.headers.contains(toLowerCase(name));
    }

    @Override // io.vertx.core.MultiMap
    public boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
        return this.headers.contains(toLowerCase(name), value, caseInsensitive);
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap add(CharSequence name, CharSequence value) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, value);
        }
        this.headers.add((Http2Headers) toLowerCase(name), value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: add */
    public MultiMap mo1937add(CharSequence name, Iterable<CharSequence> values) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, values);
        }
        this.headers.add((Http2Headers) toLowerCase(name), (Iterable) values);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    public MultiMap set(CharSequence name, CharSequence value) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, value);
        }
        this.headers.set((Http2Headers) toLowerCase(name), value);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: set */
    public MultiMap mo1935set(CharSequence name, Iterable<CharSequence> values) {
        if (!HttpHeaders.DISABLE_HTTP_HEADERS_VALIDATION) {
            HttpUtils.validateHeader(name, values);
        }
        this.headers.set((Http2Headers) toLowerCase(name), (Iterable) values);
        return this;
    }

    @Override // io.vertx.core.MultiMap
    /* renamed from: remove */
    public MultiMap mo1933remove(CharSequence name) {
        this.headers.remove(toLowerCase(name));
        return this;
    }
}
