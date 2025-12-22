package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/SingletonDnsServerAddressStreamProvider.class */
public final class SingletonDnsServerAddressStreamProvider extends UniSequentialDnsServerAddressStreamProvider {
    public SingletonDnsServerAddressStreamProvider(InetSocketAddress address) {
        super(DnsServerAddresses.singleton(address));
    }
}
