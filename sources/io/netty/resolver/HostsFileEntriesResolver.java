package io.netty.resolver;

import java.net.InetAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-4.1.42.Final.jar:io/netty/resolver/HostsFileEntriesResolver.class */
public interface HostsFileEntriesResolver {
    public static final HostsFileEntriesResolver DEFAULT = new DefaultHostsFileEntriesResolver();

    InetAddress address(String str, ResolvedAddressTypes resolvedAddressTypes);
}
