package io.vertx.core.dns.impl.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.dns.DnsPtrRecord;
import io.netty.handler.codec.dns.DnsRawRecord;
import io.netty.handler.codec.dns.DnsRecord;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.util.CharsetUtil;
import io.vertx.core.dns.impl.MxRecordImpl;
import io.vertx.core.dns.impl.SrvRecordImpl;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/dns/impl/decoder/RecordDecoder.class */
public class RecordDecoder {
    public static final Function<DnsRecord, MxRecordImpl> MX = record -> {
        ByteBuf packet = ((DnsRawRecord) record).content();
        int priority = packet.readShort();
        String name = readName(packet);
        return new MxRecordImpl(priority, name);
    };
    public static final Function<DnsRecord, String> DOMAIN = record -> {
        if (record instanceof DnsPtrRecord) {
            String val = ((DnsPtrRecord) record).hostname();
            if (val.endsWith(".")) {
                val = val.substring(0, val.length() - 1);
            }
            return val;
        }
        ByteBuf content = ((DnsRawRecord) record).content();
        return getName(content, content.readerIndex());
    };
    public static final Function<DnsRecord, String> A = address(4);
    public static final Function<DnsRecord, String> AAAA = address(16);
    public static final Function<DnsRecord, SrvRecordImpl> SRV = record -> {
        ByteBuf packet = ((DnsRawRecord) record).content();
        int priority = packet.readShort();
        int weight = packet.readShort();
        int port = packet.readUnsignedShort();
        String target = readName(packet);
        String[] parts = record.name().split("\\.", 3);
        String service = parts[0];
        String protocol = parts[1];
        String name = parts[2];
        return new SrvRecordImpl(priority, weight, port, name, protocol, service, target);
    };
    public static final Function<DnsRecord, StartOfAuthorityRecord> SOA = record -> {
        ByteBuf packet = ((DnsRawRecord) record).content();
        String mName = readName(packet);
        String rName = readName(packet);
        long serial = packet.readUnsignedInt();
        int refresh = packet.readInt();
        int retry = packet.readInt();
        int expire = packet.readInt();
        long minimum = packet.readUnsignedInt();
        return new StartOfAuthorityRecord(mName, rName, serial, refresh, retry, expire, minimum);
    };
    public static final Function<DnsRecord, List<String>> TXT = record -> {
        List<String> list = new ArrayList<>();
        ByteBuf data = ((DnsRawRecord) record).content();
        int i = data.readerIndex();
        while (true) {
            int index = i;
            if (index < data.writerIndex()) {
                int index2 = index + 1;
                int len = data.getUnsignedByte(index);
                list.add(data.toString(index2, len, CharsetUtil.UTF_8));
                i = index2 + len;
            } else {
                return list;
            }
        }
    };
    private static final Map<DnsRecordType, Function<DnsRecord, ?>> decoders = new HashMap();

    static {
        decoders.put(DnsRecordType.A, A);
        decoders.put(DnsRecordType.AAAA, AAAA);
        decoders.put(DnsRecordType.MX, MX);
        decoders.put(DnsRecordType.TXT, TXT);
        decoders.put(DnsRecordType.SRV, SRV);
        decoders.put(DnsRecordType.NS, DOMAIN);
        decoders.put(DnsRecordType.CNAME, DOMAIN);
        decoders.put(DnsRecordType.PTR, DOMAIN);
        decoders.put(DnsRecordType.SOA, SOA);
    }

    static Function<DnsRecord, String> address(int octets) {
        return record -> {
            ByteBuf data = ((DnsRawRecord) record).content();
            int size = data.readableBytes();
            if (size != octets) {
                throw new DecoderException("Invalid content length, or reader index when decoding address [index: " + data.readerIndex() + ", expected length: " + octets + ", actual: " + size + "].");
            }
            byte[] address = new byte[octets];
            data.getBytes(data.readerIndex(), address);
            try {
                return InetAddress.getByAddress(address).getHostAddress();
            } catch (UnknownHostException e) {
                throw new DecoderException("Could not convert address " + data.toString(data.readerIndex(), size, CharsetUtil.UTF_8) + " to InetAddress.");
            }
        };
    }

    static String readName(ByteBuf buf) {
        int position = -1;
        StringBuilder name = new StringBuilder();
        int unsignedByte = buf.readUnsignedByte();
        while (true) {
            int len = unsignedByte;
            if (!buf.isReadable() || len == 0) {
                break;
            }
            boolean pointer = (len & 192) == 192;
            if (pointer) {
                if (position == -1) {
                    position = buf.readerIndex() + 1;
                }
                buf.readerIndex(((len & 63) << 8) | buf.readUnsignedByte());
            } else {
                name.append(buf.toString(buf.readerIndex(), len, CharsetUtil.UTF_8)).append(".");
                buf.skipBytes(len);
            }
            unsignedByte = buf.readUnsignedByte();
        }
        if (position != -1) {
            buf.readerIndex(position);
        }
        if (name.length() == 0) {
            return null;
        }
        return name.substring(0, name.length() - 1);
    }

    static String getName(ByteBuf buf, int offset) {
        int unsignedByte;
        StringBuilder name = new StringBuilder();
        int offset2 = offset + 1;
        int unsignedByte2 = buf.getUnsignedByte(offset);
        while (true) {
            int len = unsignedByte2;
            if (buf.writerIndex() <= offset2 || len == 0) {
                break;
            }
            boolean pointer = (len & 192) == 192;
            if (pointer) {
                int i = offset2;
                int i2 = offset2 + 1;
                unsignedByte = ((len & 63) << 8) | buf.getUnsignedByte(i);
            } else {
                name.append(buf.toString(offset2, len, CharsetUtil.UTF_8)).append(".");
                unsignedByte = offset2 + len;
            }
            int offset3 = unsignedByte;
            offset2 = offset3 + 1;
            unsignedByte2 = buf.getUnsignedByte(offset3);
        }
        if (name.length() == 0) {
            return null;
        }
        return name.substring(0, name.length() - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T decode(DnsRecord record) {
        DnsRecordType type = record.type();
        Function<DnsRecord, ?> decoder = decoders.get(type);
        if (decoder == null) {
            throw new IllegalStateException("Unsupported resource record type [id: " + type + "].");
        }
        T result = null;
        try {
            result = decoder.apply(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
