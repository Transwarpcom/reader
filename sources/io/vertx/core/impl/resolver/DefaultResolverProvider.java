package io.vertx.core.impl.resolver;

import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.DefaultAddressResolverGroup;
import io.vertx.core.Handler;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.spi.resolver.ResolverProvider;
import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/resolver/DefaultResolverProvider.class */
public class DefaultResolverProvider implements ResolverProvider {
    @Override // io.vertx.core.spi.resolver.ResolverProvider
    public AddressResolverGroup<InetSocketAddress> resolver(AddressResolverOptions options) {
        return DefaultAddressResolverGroup.INSTANCE;
    }

    @Override // io.vertx.core.spi.resolver.ResolverProvider
    public void close(Handler<Void> doneHandler) {
        doneHandler.handle(null);
    }
}
