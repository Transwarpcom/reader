package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.dns.DnsRecord;
import java.net.InetAddress;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsCache.class */
public interface DnsCache {
    void clear();

    boolean clear(String str);

    List<? extends DnsCacheEntry> get(String str, DnsRecord[] dnsRecordArr);

    DnsCacheEntry cache(String str, DnsRecord[] dnsRecordArr, InetAddress inetAddress, long j, EventLoop eventLoop);

    DnsCacheEntry cache(String str, DnsRecord[] dnsRecordArr, Throwable th, EventLoop eventLoop);
}
