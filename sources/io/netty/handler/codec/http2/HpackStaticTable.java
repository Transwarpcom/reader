package io.netty.handler.codec.http2;

import io.netty.handler.codec.UnsupportedValueConverter;
import io.netty.util.AsciiString;
import io.vertx.core.cli.converters.FromBasedConverter;
import io.vertx.ext.web.handler.StaticHandler;
import java.util.Arrays;
import java.util.List;
import me.ag2s.epublib.epub.PackageDocumentBase;
import okhttp3.internal.http2.Header;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http2-4.1.42.Final.jar:io/netty/handler/codec/http2/HpackStaticTable.class */
final class HpackStaticTable {
    private static final List<HpackHeaderField> STATIC_TABLE = Arrays.asList(newEmptyHeaderField(Header.TARGET_AUTHORITY_UTF8), newHeaderField(Header.TARGET_METHOD_UTF8, "GET"), newHeaderField(Header.TARGET_METHOD_UTF8, "POST"), newHeaderField(Header.TARGET_PATH_UTF8, "/"), newHeaderField(Header.TARGET_PATH_UTF8, StaticHandler.DEFAULT_INDEX_PAGE), newHeaderField(Header.TARGET_SCHEME_UTF8, "http"), newHeaderField(Header.TARGET_SCHEME_UTF8, "https"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "200"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "204"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "206"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "304"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "400"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "404"), newHeaderField(Header.RESPONSE_STATUS_UTF8, "500"), newEmptyHeaderField("accept-charset"), newHeaderField("accept-encoding", "gzip, deflate"), newEmptyHeaderField("accept-language"), newEmptyHeaderField("accept-ranges"), newEmptyHeaderField("accept"), newEmptyHeaderField("access-control-allow-origin"), newEmptyHeaderField("age"), newEmptyHeaderField("allow"), newEmptyHeaderField("authorization"), newEmptyHeaderField("cache-control"), newEmptyHeaderField("content-disposition"), newEmptyHeaderField("content-encoding"), newEmptyHeaderField("content-language"), newEmptyHeaderField("content-length"), newEmptyHeaderField("content-location"), newEmptyHeaderField("content-range"), newEmptyHeaderField("content-type"), newEmptyHeaderField("cookie"), newEmptyHeaderField(PackageDocumentBase.DCTags.date), newEmptyHeaderField("etag"), newEmptyHeaderField("expect"), newEmptyHeaderField("expires"), newEmptyHeaderField(FromBasedConverter.FROM), newEmptyHeaderField("host"), newEmptyHeaderField("if-match"), newEmptyHeaderField("if-modified-since"), newEmptyHeaderField("if-none-match"), newEmptyHeaderField("if-range"), newEmptyHeaderField("if-unmodified-since"), newEmptyHeaderField("last-modified"), newEmptyHeaderField("link"), newEmptyHeaderField("location"), newEmptyHeaderField("max-forwards"), newEmptyHeaderField("proxy-authenticate"), newEmptyHeaderField("proxy-authorization"), newEmptyHeaderField("range"), newEmptyHeaderField("referer"), newEmptyHeaderField("refresh"), newEmptyHeaderField("retry-after"), newEmptyHeaderField("server"), newEmptyHeaderField("set-cookie"), newEmptyHeaderField("strict-transport-security"), newEmptyHeaderField("transfer-encoding"), newEmptyHeaderField("user-agent"), newEmptyHeaderField("vary"), newEmptyHeaderField("via"), newEmptyHeaderField("www-authenticate"));
    private static final CharSequenceMap<Integer> STATIC_INDEX_BY_NAME = createMap();
    static final int length = STATIC_TABLE.size();

    private static HpackHeaderField newEmptyHeaderField(String name) {
        return new HpackHeaderField(AsciiString.cached(name), AsciiString.EMPTY_STRING);
    }

    private static HpackHeaderField newHeaderField(String name, String value) {
        return new HpackHeaderField(AsciiString.cached(name), AsciiString.cached(value));
    }

    static HpackHeaderField getEntry(int index) {
        return STATIC_TABLE.get(index - 1);
    }

    static int getIndex(CharSequence name) {
        Integer index = STATIC_INDEX_BY_NAME.get(name);
        if (index == null) {
            return -1;
        }
        return index.intValue();
    }

    static int getIndex(CharSequence name, CharSequence value) {
        int index = getIndex(name);
        if (index == -1) {
            return -1;
        }
        while (index <= length) {
            HpackHeaderField entry = getEntry(index);
            if (HpackUtil.equalsConstantTime(name, entry.name) != 0) {
                if (HpackUtil.equalsConstantTime(value, entry.value) != 0) {
                    return index;
                }
                index++;
            } else {
                return -1;
            }
        }
        return -1;
    }

    private static CharSequenceMap<Integer> createMap() {
        int length2 = STATIC_TABLE.size();
        CharSequenceMap<Integer> ret = new CharSequenceMap<>(true, UnsupportedValueConverter.instance(), length2);
        for (int index = length2; index > 0; index--) {
            HpackHeaderField entry = getEntry(index);
            CharSequence name = entry.name;
            ret.set((CharSequenceMap<Integer>) name, (CharSequence) Integer.valueOf(index));
        }
        return ret;
    }

    private HpackStaticTable() {
    }
}
