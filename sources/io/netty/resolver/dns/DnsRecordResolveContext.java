package io.netty.resolver.dns;

import io.netty.channel.EventLoop;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.util.ReferenceCountUtil;
import java.net.UnknownHostException;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-resolver-dns-4.1.42.Final.jar:io/netty/resolver/dns/DnsRecordResolveContext.class */
final class DnsRecordResolveContext extends DnsResolveContext<DnsRecord> {
    DnsRecordResolveContext(DnsNameResolver parent, DnsQuestion question, DnsRecord[] additionals, DnsServerAddressStream nameServerAddrs) {
        this(parent, question.name(), question.dnsClass(), new DnsRecordType[]{question.type()}, additionals, nameServerAddrs);
    }

    private DnsRecordResolveContext(DnsNameResolver parent, String hostname, int dnsClass, DnsRecordType[] expectedTypes, DnsRecord[] additionals, DnsServerAddressStream nameServerAddrs) {
        super(parent, hostname, dnsClass, expectedTypes, additionals, nameServerAddrs);
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    DnsResolveContext<DnsRecord> newResolverContext(DnsNameResolver parent, String hostname, int dnsClass, DnsRecordType[] expectedTypes, DnsRecord[] additionals, DnsServerAddressStream nameServerAddrs) {
        return new DnsRecordResolveContext(parent, hostname, dnsClass, expectedTypes, additionals, nameServerAddrs);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.resolver.dns.DnsResolveContext
    public DnsRecord convertRecord(DnsRecord record, String hostname, DnsRecord[] additionals, EventLoop eventLoop) {
        return (DnsRecord) ReferenceCountUtil.retain(record);
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    List<DnsRecord> filterResults(List<DnsRecord> unfiltered) {
        return unfiltered;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.resolver.dns.DnsResolveContext
    public boolean isCompleteEarly(DnsRecord resolved) {
        return false;
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    boolean isDuplicateAllowed() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.resolver.dns.DnsResolveContext
    public void cache(String hostname, DnsRecord[] additionals, DnsRecord result, DnsRecord convertedResult) {
    }

    @Override // io.netty.resolver.dns.DnsResolveContext
    void cache(String hostname, DnsRecord[] additionals, UnknownHostException cause) {
    }
}
