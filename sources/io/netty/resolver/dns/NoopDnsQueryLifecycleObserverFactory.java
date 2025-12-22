package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/NoopDnsQueryLifecycleObserverFactory.class */
public final class NoopDnsQueryLifecycleObserverFactory implements DnsQueryLifecycleObserverFactory {
    public static final NoopDnsQueryLifecycleObserverFactory INSTANCE = new NoopDnsQueryLifecycleObserverFactory();

    private NoopDnsQueryLifecycleObserverFactory() {
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserverFactory
    public DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion question) {
        return NoopDnsQueryLifecycleObserver.INSTANCE;
    }
}
