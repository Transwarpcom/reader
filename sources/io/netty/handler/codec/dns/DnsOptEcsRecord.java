package io.netty.handler.codec.dns;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-dns-4.1.42.Final.jar:io/netty/handler/codec/dns/DnsOptEcsRecord.class */
public interface DnsOptEcsRecord extends DnsOptPseudoRecord {
    int sourcePrefixLength();

    int scopePrefixLength();

    byte[] address();
}
