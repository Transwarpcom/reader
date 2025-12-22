package io.netty.resolver.dns;

import io.netty.channel.EventLoop;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/NoopDnsCnameCache.class */
public final class NoopDnsCnameCache implements DnsCnameCache {
    public static final NoopDnsCnameCache INSTANCE = new NoopDnsCnameCache();

    private NoopDnsCnameCache() {
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public String get(String hostname) {
        return null;
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public void cache(String hostname, String cname, long originalTtl, EventLoop loop) {
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public void clear() {
    }

    @Override // io.netty.resolver.dns.DnsCnameCache
    public boolean clear(String hostname) {
        return false;
    }
}
