package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/AuthoritativeDnsServerCache.class */
public interface AuthoritativeDnsServerCache {
    DnsServerAddressStream get(String str);

    void cache(String str, InetSocketAddress inetSocketAddress, long j, EventLoop eventLoop);

    void clear();

    boolean clear(String str);
}
