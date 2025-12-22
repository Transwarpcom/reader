package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/SequentialDnsServerAddressStreamProvider.class */
public final class SequentialDnsServerAddressStreamProvider extends UniSequentialDnsServerAddressStreamProvider {
    public SequentialDnsServerAddressStreamProvider(InetSocketAddress... addresses) {
        super(DnsServerAddresses.sequential(addresses));
    }

    public SequentialDnsServerAddressStreamProvider(Iterable<? extends InetSocketAddress> addresses) {
        super(DnsServerAddresses.sequential(addresses));
    }
}
