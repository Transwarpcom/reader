package io.netty.handler.codec.dns;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-dns-4.1.42.Final.jar:io/netty/handler/codec/dns/DnsQuery.class */
public interface DnsQuery extends DnsMessage {
    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery setId(int i);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery setOpCode(DnsOpCode dnsOpCode);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery setRecursionDesired(boolean z);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery setZ(int i);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery setRecord(DnsSection dnsSection, DnsRecord dnsRecord);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery addRecord(DnsSection dnsSection, DnsRecord dnsRecord);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery addRecord(DnsSection dnsSection, int i, DnsRecord dnsRecord);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery clear(DnsSection dnsSection);

    @Override // io.netty.handler.codec.dns.DnsMessage
    DnsQuery clear();

    @Override // io.netty.handler.codec.dns.DnsMessage, io.netty.util.ReferenceCounted
    DnsQuery touch();

    @Override // io.netty.handler.codec.dns.DnsMessage, io.netty.util.ReferenceCounted
    DnsQuery touch(Object obj);

    @Override // io.netty.handler.codec.dns.DnsMessage, io.netty.util.ReferenceCounted
    DnsQuery retain();

    @Override // io.netty.handler.codec.dns.DnsMessage, io.netty.util.ReferenceCounted
    DnsQuery retain(int i);
}
