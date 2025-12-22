package io.netty.handler.codec.dns;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-dns-4.1.42.Final.jar:io/netty/handler/codec/dns/DnsOptPseudoRecord.class */
public interface DnsOptPseudoRecord extends DnsRecord {
    int extendedRcode();

    int version();

    int flags();
}
