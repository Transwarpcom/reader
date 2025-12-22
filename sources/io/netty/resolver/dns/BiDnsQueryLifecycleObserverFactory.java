package io.netty.resolver.dns;

import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.util.internal.ObjectUtil;
import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/BiDnsQueryLifecycleObserverFactory.class */
public final class BiDnsQueryLifecycleObserverFactory implements DnsQueryLifecycleObserverFactory {
    private final DnsQueryLifecycleObserverFactory a;
    private final DnsQueryLifecycleObserverFactory b;

    public BiDnsQueryLifecycleObserverFactory(DnsQueryLifecycleObserverFactory a, DnsQueryLifecycleObserverFactory b) {
        this.a = (DnsQueryLifecycleObserverFactory) ObjectUtil.checkNotNull(a, "a");
        this.b = (DnsQueryLifecycleObserverFactory) ObjectUtil.checkNotNull(b, OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE);
    }

    @Override // io.netty.resolver.dns.DnsQueryLifecycleObserverFactory
    public DnsQueryLifecycleObserver newDnsQueryLifecycleObserver(DnsQuestion question) {
        return new BiDnsQueryLifecycleObserver(this.a.newDnsQueryLifecycleObserver(question), this.b.newDnsQueryLifecycleObserver(question));
    }
}
