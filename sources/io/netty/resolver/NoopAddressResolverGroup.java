package io.netty.resolver;

import io.netty.util.concurrent.EventExecutor;
import java.net.SocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-4.1.42.Final.jar:io/netty/resolver/NoopAddressResolverGroup.class */
public final class NoopAddressResolverGroup extends AddressResolverGroup<SocketAddress> {
    public static final NoopAddressResolverGroup INSTANCE = new NoopAddressResolverGroup();

    private NoopAddressResolverGroup() {
    }

    @Override // io.netty.resolver.AddressResolverGroup
    protected AddressResolver<SocketAddress> newResolver(EventExecutor executor) throws Exception {
        return new NoopAddressResolver(executor);
    }
}
