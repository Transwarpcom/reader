package io.netty.resolver.dns;

import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsServerAddressStream.class */
public interface DnsServerAddressStream {
    InetSocketAddress next();

    int size();

    DnsServerAddressStream duplicate();
}
