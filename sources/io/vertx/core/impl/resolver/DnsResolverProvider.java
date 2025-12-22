package io.vertx.core.impl.resolver;

import io.netty.channel.ChannelFactory;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.DatagramChannel;
import io.netty.resolver.AddressResolver;
import io.netty.resolver.AddressResolverGroup;
import io.netty.resolver.HostsFileEntries;
import io.netty.resolver.HostsFileEntriesResolver;
import io.netty.resolver.HostsFileParser;
import io.netty.resolver.NameResolver;
import io.netty.resolver.ResolvedAddressTypes;
import io.netty.resolver.dns.DefaultDnsCache;
import io.netty.resolver.dns.DefaultDnsServerAddressStreamProvider;
import io.netty.resolver.dns.DnsAddressResolverGroup;
import io.netty.resolver.dns.DnsCache;
import io.netty.resolver.dns.DnsNameResolverBuilder;
import io.netty.resolver.dns.DnsServerAddressStream;
import io.netty.resolver.dns.DnsServerAddressStreamProvider;
import io.netty.resolver.dns.DnsServerAddresses;
import io.netty.util.NetUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.ObjectUtil;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.dns.AddressResolverOptions;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.spi.resolver.ResolverProvider;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/resolver/DnsResolverProvider.class */
public class DnsResolverProvider implements ResolverProvider {
    private final Vertx vertx;
    private AddressResolverGroup<InetSocketAddress> resolverGroup;
    private final List<ResolverRegistration> resolvers = Collections.synchronizedList(new ArrayList());
    private final List<InetSocketAddress> serverList = new ArrayList();

    public List<InetSocketAddress> nameServerAddresses() {
        return this.serverList;
    }

    public DnsResolverProvider(VertxImpl vertx, AddressResolverOptions options) throws NumberFormatException, IOException {
        HostsFileEntries entries;
        String ipAddress;
        int i;
        List<String> dnsServers = options.getServers();
        if (dnsServers != null && dnsServers.size() > 0) {
            for (String dnsServer : dnsServers) {
                int sep = dnsServer.indexOf(58);
                if (sep != -1) {
                    ipAddress = dnsServer.substring(0, sep);
                    i = Integer.parseInt(dnsServer.substring(sep + 1));
                } else {
                    ipAddress = dnsServer;
                    i = 53;
                }
                try {
                    int port = i;
                    this.serverList.add(new InetSocketAddress(InetAddress.getByAddress(NetUtil.createByteArrayFromIpAddressString(ipAddress)), port));
                } catch (UnknownHostException e) {
                    throw new VertxException(e);
                }
            }
        } else {
            DnsServerAddressStream stream = DefaultDnsServerAddressStreamProvider.defaultAddresses().stream();
            Set<InetSocketAddress> all = new HashSet<>();
            while (true) {
                InetSocketAddress address = stream.next();
                if (all.contains(address)) {
                    break;
                }
                this.serverList.add(address);
                all.add(address);
            }
        }
        DnsServerAddresses nameServerAddresses = options.isRotateServers() ? DnsServerAddresses.rotational(this.serverList) : DnsServerAddresses.sequential(this.serverList);
        DnsServerAddressStreamProvider nameServerAddressProvider = hostname -> {
            return nameServerAddresses.stream();
        };
        if (options.getHostsPath() != null) {
            File file = vertx.resolveFile(options.getHostsPath()).getAbsoluteFile();
            try {
                if (!file.exists() || !file.isFile()) {
                    throw new IOException();
                }
                entries = HostsFileParser.parse(file);
            } catch (IOException e2) {
                throw new VertxException("Cannot read hosts file " + file.getAbsolutePath());
            }
        } else if (options.getHostsValue() != null) {
            try {
                entries = HostsFileParser.parse(new StringReader(options.getHostsValue().toString()));
            } catch (IOException e3) {
                throw new VertxException("Cannot read hosts config ", e3);
            }
        } else {
            entries = HostsFileParser.parseSilently();
        }
        int minTtl = ObjectUtil.intValue(Integer.valueOf(options.getCacheMinTimeToLive()), 0);
        int maxTtl = ObjectUtil.intValue(Integer.valueOf(options.getCacheMaxTimeToLive()), Integer.MAX_VALUE);
        int negativeTtl = ObjectUtil.intValue(Integer.valueOf(options.getCacheNegativeTimeToLive()), 0);
        DnsCache resolveCache = new DefaultDnsCache(minTtl, maxTtl, negativeTtl);
        DnsCache authoritativeDnsServerCache = new DefaultDnsCache(minTtl, maxTtl, negativeTtl);
        this.vertx = vertx;
        this.resolverGroup = new AnonymousClass1(vertx, nameServerAddressProvider, entries, options, resolveCache, authoritativeDnsServerCache);
    }

    /* renamed from: io.vertx.core.impl.resolver.DnsResolverProvider$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/resolver/DnsResolverProvider$1.class */
    class AnonymousClass1 extends AddressResolverGroup<InetSocketAddress> {
        final /* synthetic */ VertxImpl val$vertx;
        final /* synthetic */ DnsServerAddressStreamProvider val$nameServerAddressProvider;
        final /* synthetic */ HostsFileEntries val$entries;
        final /* synthetic */ AddressResolverOptions val$options;
        final /* synthetic */ DnsCache val$resolveCache;
        final /* synthetic */ DnsCache val$authoritativeDnsServerCache;

        AnonymousClass1(VertxImpl vertxImpl, DnsServerAddressStreamProvider dnsServerAddressStreamProvider, HostsFileEntries hostsFileEntries, AddressResolverOptions addressResolverOptions, DnsCache dnsCache, DnsCache dnsCache2) {
            this.val$vertx = vertxImpl;
            this.val$nameServerAddressProvider = dnsServerAddressStreamProvider;
            this.val$entries = hostsFileEntries;
            this.val$options = addressResolverOptions;
            this.val$resolveCache = dnsCache;
            this.val$authoritativeDnsServerCache = dnsCache2;
        }

        @Override // io.netty.resolver.AddressResolverGroup
        protected AddressResolver<InetSocketAddress> newResolver(final EventExecutor executor) throws Exception {
            VertxImpl vertxImpl = this.val$vertx;
            ChannelFactory<DatagramChannel> channelFactory = () -> {
                return vertxImpl.transport().datagramChannel();
            };
            DnsAddressResolverGroup group = new DnsAddressResolverGroup(channelFactory, this.val$nameServerAddressProvider) { // from class: io.vertx.core.impl.resolver.DnsResolverProvider.1.1
                @Override // io.netty.resolver.dns.DnsAddressResolverGroup
                protected NameResolver<InetAddress> newNameResolver(EventLoop eventLoop, ChannelFactory<? extends DatagramChannel> channelFactory2, DnsServerAddressStreamProvider nameServerProvider) throws Exception {
                    DnsNameResolverBuilder builder = new DnsNameResolverBuilder((EventLoop) executor);
                    builder.hostsFileEntriesResolver(new HostsFileEntriesResolver() { // from class: io.vertx.core.impl.resolver.DnsResolverProvider.1.1.1
                        @Override // io.netty.resolver.HostsFileEntriesResolver
                        public InetAddress address(String inetHost, ResolvedAddressTypes resolvedAddressTypes) {
                            if (inetHost.endsWith(".")) {
                                inetHost = inetHost.substring(0, inetHost.length() - 1);
                            }
                            InetAddress address = lookup(inetHost, resolvedAddressTypes);
                            if (address == null) {
                                address = lookup(inetHost.toLowerCase(Locale.ENGLISH), resolvedAddressTypes);
                            }
                            return address;
                        }

                        InetAddress lookup(String inetHost, ResolvedAddressTypes resolvedAddressTypes) {
                            switch (AnonymousClass2.$SwitchMap$io$netty$resolver$ResolvedAddressTypes[resolvedAddressTypes.ordinal()]) {
                                case 1:
                                    return AnonymousClass1.this.val$entries.inet4Entries().get(inetHost);
                                case 2:
                                    return AnonymousClass1.this.val$entries.inet6Entries().get(inetHost);
                                case 3:
                                    Inet4Address inet4Address = AnonymousClass1.this.val$entries.inet4Entries().get(inetHost);
                                    return inet4Address != null ? inet4Address : AnonymousClass1.this.val$entries.inet6Entries().get(inetHost);
                                case 4:
                                    Inet6Address inet6Address = AnonymousClass1.this.val$entries.inet6Entries().get(inetHost);
                                    return inet6Address != null ? inet6Address : AnonymousClass1.this.val$entries.inet4Entries().get(inetHost);
                                default:
                                    throw new IllegalArgumentException("Unknown ResolvedAddressTypes " + resolvedAddressTypes);
                            }
                        }
                    });
                    builder.channelFactory(channelFactory2);
                    builder.nameServerProvider(AnonymousClass1.this.val$nameServerAddressProvider);
                    builder.optResourceEnabled(AnonymousClass1.this.val$options.isOptResourceEnabled());
                    builder.resolveCache(AnonymousClass1.this.val$resolveCache);
                    builder.authoritativeDnsServerCache(AnonymousClass1.this.val$authoritativeDnsServerCache);
                    builder.queryTimeoutMillis(AnonymousClass1.this.val$options.getQueryTimeout());
                    builder.maxQueriesPerResolve(AnonymousClass1.this.val$options.getMaxQueries());
                    builder.recursionDesired(AnonymousClass1.this.val$options.getRdFlag());
                    if (AnonymousClass1.this.val$options.getSearchDomains() != null) {
                        builder.searchDomains(AnonymousClass1.this.val$options.getSearchDomains());
                        int ndots = AnonymousClass1.this.val$options.getNdots();
                        if (ndots == -1) {
                            ndots = io.vertx.core.impl.AddressResolver.DEFAULT_NDOTS_RESOLV_OPTION;
                        }
                        builder.ndots(ndots);
                    }
                    return builder.build();
                }
            };
            AddressResolver<InetSocketAddress> resolver = group.getResolver(executor);
            DnsResolverProvider.this.resolvers.add(new ResolverRegistration(resolver, (EventLoop) executor));
            return resolver;
        }
    }

    /* renamed from: io.vertx.core.impl.resolver.DnsResolverProvider$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/resolver/DnsResolverProvider$2.class */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$resolver$ResolvedAddressTypes = new int[ResolvedAddressTypes.values().length];

        static {
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_PREFERRED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_PREFERRED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/resolver/DnsResolverProvider$ResolverRegistration.class */
    private static class ResolverRegistration {
        private final AddressResolver<InetSocketAddress> resolver;
        private final EventLoop executor;

        ResolverRegistration(AddressResolver<InetSocketAddress> resolver, EventLoop executor) {
            this.resolver = resolver;
            this.executor = executor;
        }
    }

    @Override // io.vertx.core.spi.resolver.ResolverProvider
    public AddressResolverGroup<InetSocketAddress> resolver(AddressResolverOptions options) {
        return this.resolverGroup;
    }

    @Override // io.vertx.core.spi.resolver.ResolverProvider
    public void close(Handler<Void> doneHandler) {
        Context context = this.vertx.getOrCreateContext();
        ResolverRegistration[] registrations = (ResolverRegistration[]) this.resolvers.toArray(new ResolverRegistration[this.resolvers.size()]);
        if (registrations.length == 0) {
            context.runOnContext(doneHandler);
            return;
        }
        AtomicInteger count = new AtomicInteger(registrations.length);
        for (ResolverRegistration registration : registrations) {
            Runnable task = () -> {
                registration.resolver.close();
                if (count.decrementAndGet() == 0) {
                    context.runOnContext(doneHandler);
                }
            };
            if (!registration.executor.inEventLoop()) {
                registration.executor.execute(task);
            } else {
                task.run();
            }
        }
    }
}
