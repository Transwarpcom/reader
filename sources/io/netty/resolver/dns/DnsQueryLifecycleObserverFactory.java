package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsQueryLifecycleObserverFactory.class */
public interface DnsQueryLifecycleObserverFactory {
    DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion dnsQuestion);
}
