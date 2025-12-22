package io.netty.resolver.dns;

import java.net.InetAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsCacheEntry.class */
public interface DnsCacheEntry {
    InetAddress address();

    Throwable cause();
}
