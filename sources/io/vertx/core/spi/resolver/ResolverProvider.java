package io.vertx.core.spi.resolver;

import io.netty.resolver.AddressResolverGroup;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.impl.resolver.DefaultResolverProvider;
import io.vertx.core.impl.resolver.DnsResolverProvider;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.net.InetSocketAddress;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/spi/resolver/ResolverProvider.class */
public interface ResolverProvider {
    public static final String DISABLE_DNS_RESOLVER_PROP_NAME = "vertx.disableDnsResolver";

    AddressResolverGroup<InetSocketAddress> resolver(AddressResolverOptions addressResolverOptions);

    void close(Handler<Void> handler);

    static ResolverProvider factory(Vertx vertx, AddressResolverOptions options) {
        try {
            if (!Boolean.getBoolean(DISABLE_DNS_RESOLVER_PROP_NAME)) {
                return new DnsResolverProvider((VertxImpl) vertx, options);
            }
        } catch (Throwable e) {
            if (e instanceof VertxException) {
                throw e;
            }
            Logger logger = LoggerFactory.getLogger((Class<?>) ResolverProvider.class);
            logger.info("Using the default address resolver as the dns resolver could not be loaded");
        }
        return new DefaultResolverProvider();
    }
}
