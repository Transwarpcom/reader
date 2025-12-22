package io.netty.resolver.dns;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsServerAddressStreamProviders.class */
public final class DnsServerAddressStreamProviders {
    private static final long REFRESH_INTERVAL = TimeUnit.MINUTES.toNanos(5);
    private static final DnsServerAddressStreamProvider DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER = new DnsServerAddressStreamProvider() { // from class: io.netty.resolver.dns.DnsServerAddressStreamProviders.1
        private volatile DnsServerAddressStreamProvider currentProvider = provider();
        private final AtomicLong lastRefresh = new AtomicLong(System.nanoTime());

        @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
        public DnsServerAddressStream nameServerAddressStream(String hostname) {
            long last = this.lastRefresh.get();
            DnsServerAddressStreamProvider current = this.currentProvider;
            if (System.nanoTime() - last > DnsServerAddressStreamProviders.REFRESH_INTERVAL && this.lastRefresh.compareAndSet(last, System.nanoTime())) {
                DnsServerAddressStreamProvider dnsServerAddressStreamProviderProvider = provider();
                this.currentProvider = dnsServerAddressStreamProviderProvider;
                current = dnsServerAddressStreamProviderProvider;
            }
            return current.nameServerAddressStream(hostname);
        }

        private DnsServerAddressStreamProvider provider() {
            return PlatformDependent.isWindows() ? DefaultDnsServerAddressStreamProvider.INSTANCE : UnixResolverDnsServerAddressStreamProvider.parseSilently();
        }
    };

    private DnsServerAddressStreamProviders() {
    }

    public static DnsServerAddressStreamProvider platformDefault() {
        return DEFAULT_DNS_SERVER_ADDRESS_STREAM_PROVIDER;
    }
}
