package io.vertx.ext.web.client.impl;

import io.netty.handler.codec.http.cookie.Cookie;
import io.vertx.core.http.impl.HttpUtils;
import io.vertx.ext.web.client.spi.CookieStore;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/CookieStoreImpl.class */
public class CookieStoreImpl implements CookieStore {
    private ConcurrentHashMap<Key, Cookie> noDomainCookies = new ConcurrentHashMap<>();
    private ConcurrentSkipListMap<Key, Cookie> domainCookies = new ConcurrentSkipListMap<>();
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !CookieStoreImpl.class.desiredAssertionStatus();
    }

    @Override // io.vertx.ext.web.client.spi.CookieStore
    public Iterable<Cookie> get(Boolean ssl, String domain, String path) {
        if (!$assertionsDisabled && (domain == null || domain.length() <= 0)) {
            throw new AssertionError();
        }
        String uri = HttpUtils.removeDots(path);
        int pos = uri.indexOf(63);
        if (pos > -1) {
            uri = uri.substring(0, pos);
        }
        int pos2 = uri.indexOf(35);
        if (pos2 > -1) {
            uri = uri.substring(0, pos2);
        }
        String cleanPath = uri;
        TreeMap<String, Cookie> matches = new TreeMap<>();
        Consumer consumer = c -> {
            if (ssl != Boolean.TRUE && c.isSecure()) {
                return;
            }
            if (c.path() != null && !cleanPath.equals(c.path())) {
                String cookiePath = c.path();
                if (!cookiePath.endsWith("/")) {
                    cookiePath = cookiePath + '/';
                }
                if (!cleanPath.startsWith(cookiePath)) {
                    return;
                }
            }
            matches.put(c.name(), c);
        };
        for (Cookie c2 : this.noDomainCookies.values()) {
            consumer.accept(c2);
        }
        Key key = new Key(domain, "", "");
        String prefix = key.domain.substring(0, 1);
        for (Map.Entry<Key, Cookie> entry : this.domainCookies.tailMap((ConcurrentSkipListMap<Key, Cookie>) new Key(prefix, "", ""), true).entrySet()) {
            if (entry.getKey().domain.compareTo(key.domain) > 0) {
                break;
            }
            if (key.domain.startsWith(entry.getKey().domain)) {
                consumer.accept(entry.getValue());
            }
        }
        return matches.values();
    }

    @Override // io.vertx.ext.web.client.spi.CookieStore
    public CookieStore put(Cookie cookie) {
        Key key = new Key(cookie.domain(), cookie.path(), cookie.name());
        if (key.domain.equals("")) {
            this.noDomainCookies.put(key, cookie);
            return this;
        }
        this.domainCookies.put(key, cookie);
        return this;
    }

    @Override // io.vertx.ext.web.client.spi.CookieStore
    public CookieStore remove(Cookie cookie) {
        Key key = new Key(cookie.domain(), cookie.path(), cookie.name());
        if (key.domain.equals("")) {
            this.noDomainCookies.remove(key);
        } else {
            this.domainCookies.remove(key);
        }
        return this;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-web-client-3.8.5.jar:io/vertx/ext/web/client/impl/CookieStoreImpl$Key.class */
    private static class Key implements Comparable<Key> {
        private static final String NO_DOMAIN = "";
        private final String domain;
        private final String path;
        private final String name;

        public Key(String domain, String path, String name) {
            if (domain == null || domain.length() == 0) {
                this.domain = "";
            } else {
                while (domain.charAt(0) == '.') {
                    domain = domain.substring(1);
                }
                while (domain.charAt(domain.length() - 1) == '.') {
                    domain = domain.substring(0, domain.length() - 1);
                }
                if (domain.length() == 0) {
                    this.domain = "";
                } else {
                    String[] tokens = domain.split("\\.");
                    int i = 0;
                    int j = tokens.length - 1;
                    while (i < tokens.length / 2) {
                        String tmp = tokens[j];
                        tokens[j] = tokens[i];
                        tokens[i] = tmp;
                        i++;
                        j--;
                    }
                    this.domain = String.join(".", tokens);
                }
            }
            this.path = path == null ? "" : path;
            this.name = name;
        }

        @Override // java.lang.Comparable
        public int compareTo(Key o) {
            int ret = this.domain.compareTo(o.domain);
            if (ret == 0) {
                ret = this.path.compareTo(o.path);
            }
            if (ret == 0) {
                ret = this.name.compareTo(o.name);
            }
            return ret;
        }

        public int hashCode() {
            int result = (31 * 1) + (this.domain == null ? 0 : this.domain.hashCode());
            return (31 * ((31 * result) + (this.name == null ? 0 : this.name.hashCode()))) + (this.path == null ? 0 : this.path.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Key other = (Key) obj;
            if (this.domain == null) {
                if (other.domain != null) {
                    return false;
                }
            } else if (!this.domain.equals(other.domain)) {
                return false;
            }
            if (this.name == null) {
                if (other.name != null) {
                    return false;
                }
            } else if (!this.name.equals(other.name)) {
                return false;
            }
            if (this.path == null) {
                if (other.path != null) {
                    return false;
                }
                return true;
            }
            if (!this.path.equals(other.path)) {
                return false;
            }
            return true;
        }
    }
}
