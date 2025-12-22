package io.netty.resolver.dns;

import io.netty.channel.EventLoop;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsCnameCache.class */
public interface DnsCnameCache {
    String get(String str);

    void cache(String str, String str2, long j, EventLoop eventLoop);

    void clear();

    boolean clear(String str);
}
