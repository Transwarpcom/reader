package io.vertx.core.http.impl;

import ch.qos.logback.classic.spi.CallerData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.Http2Settings;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.StreamPriority;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpUtils.class */
public final class HttpUtils {
    static final int SC_SWITCHING_PROTOCOLS = 101;
    static final int SC_BAD_GATEWAY = 502;
    static final StreamPriority DEFAULT_STREAM_PRIORITY = new StreamPriority() { // from class: io.vertx.core.http.impl.HttpUtils.1
        @Override // io.vertx.core.http.StreamPriority
        public StreamPriority setWeight(short weight) {
            throw new UnsupportedOperationException("Unmodifiable stream priority");
        }

        @Override // io.vertx.core.http.StreamPriority
        public StreamPriority setDependency(int dependency) {
            throw new UnsupportedOperationException("Unmodifiable stream priority");
        }

        @Override // io.vertx.core.http.StreamPriority
        public StreamPriority setExclusive(boolean exclusive) {
            throw new UnsupportedOperationException("Unmodifiable stream priority");
        }
    };
    private static final CustomCompressor compressor = new CustomCompressor();
    private static final AsciiString TIMEOUT_EQ = AsciiString.of("timeout=");
    private static final Consumer<CharSequence> HEADER_VALUE_VALIDATOR = HttpUtils::validateHeaderValue;
    private static final int HIGHEST_INVALID_VALUE_CHAR_MASK = -16;

    private HttpUtils() {
    }

    private static int indexOfSlash(CharSequence str, int start) {
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == '/') {
                return i;
            }
        }
        return -1;
    }

    private static boolean matches(CharSequence path, int start, String what) {
        return matches(path, start, what, false);
    }

    private static boolean matches(CharSequence path, int start, String what, boolean exact) {
        if ((!exact || path.length() - start == what.length()) && path.length() - start >= what.length()) {
            for (int i = 0; i < what.length(); i++) {
                if (path.charAt(start + i) != what.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String normalizePath(String pathname) throws NumberFormatException {
        if (pathname == null) {
            return null;
        }
        if (pathname.length() == 0) {
            return "/";
        }
        StringBuilder ibuf = new StringBuilder(pathname.length() + 1);
        if (pathname.charAt(0) != '/') {
            ibuf.append('/');
        }
        ibuf.append(pathname);
        for (int i = 0; i < ibuf.length(); i++) {
            if (ibuf.charAt(i) == '%') {
                decodeUnreserved(ibuf, i);
            }
        }
        return removeDots(ibuf);
    }

    private static void decodeUnreserved(StringBuilder path, int start) throws NumberFormatException {
        if (start + 3 <= path.length()) {
            String escapeSequence = path.substring(start + 1, start + 3);
            try {
                int unescaped = Integer.parseInt(escapeSequence, 16);
                if (unescaped < 0) {
                    throw new IllegalArgumentException("Invalid escape sequence: %" + escapeSequence);
                }
                if ((unescaped >= 65 && unescaped <= 90) || ((unescaped >= 97 && unescaped <= 122) || ((unescaped >= 48 && unescaped <= 57) || unescaped == 45 || unescaped == 46 || unescaped == 95 || unescaped == 126))) {
                    path.setCharAt(start, (char) unescaped);
                    path.delete(start + 1, start + 3);
                    return;
                }
                return;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid escape sequence: %" + escapeSequence);
            }
        }
        throw new IllegalArgumentException("Invalid position for escape character: " + start);
    }

    public static String removeDots(CharSequence path) {
        if (path == null) {
            return null;
        }
        StringBuilder obuf = new StringBuilder(path.length());
        int i = 0;
        while (true) {
            if (i < path.length()) {
                if (matches(path, i, "./")) {
                    i += 2;
                } else if (matches(path, i, "../")) {
                    i += 3;
                } else if (matches(path, i, "/./")) {
                    i += 2;
                } else if (matches(path, i, "/.", true)) {
                    path = "/";
                    i = 0;
                } else if (!matches(path, i, "/../")) {
                    if (!matches(path, i, "/..", true)) {
                        if (!matches(path, i, ".", true) && !matches(path, i, "..", true)) {
                            if (path.charAt(i) == '/') {
                                i++;
                                if (obuf.length() == 0 || obuf.charAt(obuf.length() - 1) != '/') {
                                    obuf.append('/');
                                }
                            }
                            int pos = indexOfSlash(path, i);
                            if (pos != -1) {
                                obuf.append(path, i, pos);
                                i = pos;
                            } else {
                                obuf.append(path, i, path.length());
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        path = "/";
                        i = 0;
                        int pos2 = obuf.lastIndexOf("/");
                        if (pos2 != -1) {
                            obuf.delete(pos2, obuf.length());
                        }
                    }
                } else {
                    i += 3;
                    int pos3 = obuf.lastIndexOf("/");
                    if (pos3 != -1) {
                        obuf.delete(pos3, obuf.length());
                    }
                }
            } else {
                break;
            }
        }
        return obuf.toString();
    }

    public static URI resolveURIReference(String base, String ref) throws URISyntaxException {
        return resolveURIReference(URI.create(base), ref);
    }

    public static URI resolveURIReference(URI base, String ref) throws URISyntaxException {
        String mergedPath;
        String path;
        String query;
        String authority;
        String scheme;
        URI _ref = URI.create(ref);
        if (_ref.getScheme() != null) {
            scheme = _ref.getScheme();
            authority = _ref.getAuthority();
            path = removeDots(_ref.getPath());
            query = _ref.getRawQuery();
        } else {
            if (_ref.getAuthority() != null) {
                authority = _ref.getAuthority();
                path = _ref.getPath();
                query = _ref.getRawQuery();
            } else {
                if (_ref.getPath().length() == 0) {
                    path = base.getPath();
                    if (_ref.getRawQuery() != null) {
                        query = _ref.getRawQuery();
                    } else {
                        query = base.getRawQuery();
                    }
                } else {
                    if (_ref.getPath().startsWith("/")) {
                        path = removeDots(_ref.getPath());
                    } else {
                        String basePath = base.getPath();
                        if (base.getAuthority() != null && basePath.length() == 0) {
                            mergedPath = "/" + _ref.getPath();
                        } else {
                            int index = basePath.lastIndexOf(47);
                            if (index > -1) {
                                mergedPath = basePath.substring(0, index + 1) + _ref.getPath();
                            } else {
                                mergedPath = _ref.getPath();
                            }
                        }
                        path = removeDots(mergedPath);
                    }
                    query = _ref.getRawQuery();
                }
                authority = base.getAuthority();
            }
            scheme = base.getScheme();
        }
        return new URI(scheme, authority, path, query, _ref.getFragment());
    }

    static String parsePath(String uri) {
        int i;
        int i2;
        if (uri.charAt(0) == '/' || (i = uri.indexOf("://")) == -1) {
            i2 = 0;
        } else {
            i2 = uri.indexOf(47, i + 3);
            if (i2 == -1) {
                return "/";
            }
        }
        int queryStart = uri.indexOf(63, i2);
        if (queryStart == -1) {
            queryStart = uri.length();
        }
        return uri.substring(i2, queryStart);
    }

    static String parseQuery(String uri) {
        int i = uri.indexOf(63);
        if (i == -1) {
            return null;
        }
        return uri.substring(i + 1, uri.length());
    }

    static String absoluteURI(String serverOrigin, HttpServerRequest req) throws URISyntaxException {
        String absoluteURI;
        URI uri = new URI(req.uri());
        String scheme = uri.getScheme();
        if (scheme != null && (scheme.equals("http") || scheme.equals("https"))) {
            absoluteURI = uri.toString();
        } else {
            String host = req.host();
            if (host != null) {
                absoluteURI = req.scheme() + "://" + host + uri;
            } else {
                absoluteURI = serverOrigin + uri;
            }
        }
        return absoluteURI;
    }

    static MultiMap params(String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        Map<String, List<String>> prms = queryStringDecoder.parameters();
        MultiMap params = new CaseInsensitiveHeaders();
        if (!prms.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : prms.entrySet()) {
                params.mo1938add(entry.getKey(), entry.getValue());
            }
        }
        return params;
    }

    public static void fromVertxInitialSettings(boolean server, Http2Settings vertxSettings, io.netty.handler.codec.http2.Http2Settings nettySettings) {
        if (vertxSettings != null) {
            if (!server && !vertxSettings.isPushEnabled()) {
                nettySettings.pushEnabled(vertxSettings.isPushEnabled());
            }
            if (vertxSettings.getHeaderTableSize() != Http2Settings.DEFAULT_HEADER_TABLE_SIZE) {
                nettySettings.put((char) 1, Long.valueOf(vertxSettings.getHeaderTableSize()));
            }
            if (vertxSettings.getInitialWindowSize() != 65535) {
                nettySettings.initialWindowSize(vertxSettings.getInitialWindowSize());
            }
            if (vertxSettings.getMaxConcurrentStreams() != 4294967295L) {
                nettySettings.maxConcurrentStreams(vertxSettings.getMaxConcurrentStreams());
            }
            if (vertxSettings.getMaxFrameSize() != 16384) {
                nettySettings.maxFrameSize(vertxSettings.getMaxFrameSize());
            }
            if (vertxSettings.getMaxHeaderListSize() != 2147483647L) {
                nettySettings.maxHeaderListSize(vertxSettings.getMaxHeaderListSize());
            }
            Map<Integer, Long> extraSettings = vertxSettings.getExtraSettings();
            if (extraSettings != null) {
                extraSettings.forEach((code, setting) -> {
                    nettySettings.put((char) code.intValue(), setting);
                });
            }
        }
    }

    public static io.netty.handler.codec.http2.Http2Settings fromVertxSettings(Http2Settings settings) {
        io.netty.handler.codec.http2.Http2Settings converted = new io.netty.handler.codec.http2.Http2Settings();
        converted.pushEnabled(settings.isPushEnabled());
        converted.maxFrameSize(settings.getMaxFrameSize());
        converted.initialWindowSize(settings.getInitialWindowSize());
        converted.headerTableSize(settings.getHeaderTableSize());
        converted.maxConcurrentStreams(settings.getMaxConcurrentStreams());
        converted.maxHeaderListSize(settings.getMaxHeaderListSize());
        if (settings.getExtraSettings() != null) {
            settings.getExtraSettings().forEach((key, value) -> {
                converted.put((char) key.intValue(), value);
            });
        }
        return converted;
    }

    public static Http2Settings toVertxSettings(io.netty.handler.codec.http2.Http2Settings settings) {
        Http2Settings converted = new Http2Settings();
        Boolean pushEnabled = settings.pushEnabled();
        if (pushEnabled != null) {
            converted.setPushEnabled(pushEnabled.booleanValue());
        }
        Long maxConcurrentStreams = settings.maxConcurrentStreams();
        if (maxConcurrentStreams != null) {
            converted.setMaxConcurrentStreams(maxConcurrentStreams.longValue());
        }
        Long maxHeaderListSize = settings.maxHeaderListSize();
        if (maxHeaderListSize != null) {
            converted.setMaxHeaderListSize(maxHeaderListSize.longValue());
        }
        Integer maxFrameSize = settings.maxFrameSize();
        if (maxFrameSize != null) {
            converted.setMaxFrameSize(maxFrameSize.intValue());
        }
        Integer initialWindowSize = settings.initialWindowSize();
        if (initialWindowSize != null) {
            converted.setInitialWindowSize(initialWindowSize.intValue());
        }
        Long headerTableSize = settings.headerTableSize();
        if (headerTableSize != null) {
            converted.setHeaderTableSize(headerTableSize.longValue());
        }
        settings.forEach((key, value) -> {
            if (key.charValue() > 6) {
                converted.set(key.charValue(), value.longValue());
            }
        });
        return converted;
    }

    static io.netty.handler.codec.http2.Http2Settings decodeSettings(String base64Settings) {
        try {
            io.netty.handler.codec.http2.Http2Settings settings = new io.netty.handler.codec.http2.Http2Settings();
            Buffer buffer = Buffer.buffer(Base64.getUrlDecoder().decode(base64Settings));
            int pos = 0;
            int len = buffer.length();
            while (pos < len) {
                int i = buffer.getUnsignedShort(pos);
                int pos2 = pos + 2;
                long j = buffer.getUnsignedInt(pos2);
                pos = pos2 + 4;
                settings.put((char) i, Long.valueOf(j));
            }
            return settings;
        } catch (Exception e) {
            return null;
        }
    }

    public static String encodeSettings(Http2Settings settings) {
        Buffer buffer = Buffer.buffer();
        fromVertxSettings(settings).forEach((c, l) -> {
            buffer.appendUnsignedShort(c.charValue());
            buffer.appendUnsignedInt(l.longValue());
        });
        return Base64.getUrlEncoder().encodeToString(buffer.getBytes());
    }

    public static ByteBuf generateWSCloseFrameByteBuf(short statusCode, String reason) {
        if (reason != null) {
            return Unpooled.copiedBuffer(Unpooled.copyShort(statusCode), Unpooled.copiedBuffer(reason, Charset.forName("UTF-8")));
        }
        return Unpooled.copyShort(statusCode);
    }

    static void sendError(Channel ch2, HttpResponseStatus status) {
        sendError(ch2, status, status.reasonPhrase());
    }

    static void sendError(Channel ch2, HttpResponseStatus status, CharSequence err) {
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
        if (status.code() == HttpResponseStatus.METHOD_NOT_ALLOWED.code()) {
            resp.headers().set(HttpHeaders.ALLOW, HttpHeaders.GET);
        }
        if (err != null) {
            resp.content().writeBytes(err.toString().getBytes(CharsetUtil.UTF_8));
            HttpUtil.setContentLength(resp, err.length());
        } else {
            HttpUtil.setContentLength(resp, 0L);
        }
        ch2.writeAndFlush(resp);
    }

    static String getWebSocketLocation(HttpServerRequest req, boolean ssl) throws Exception {
        String prefix;
        if (ssl) {
            prefix = "ws://";
        } else {
            prefix = "wss://";
        }
        URI uri = new URI(req.uri());
        String path = uri.getRawPath();
        String loc = prefix + req.headers().get(HttpHeaderNames.HOST) + path;
        String query = uri.getRawQuery();
        if (query != null) {
            loc = loc + CallerData.NA + query;
        }
        return loc;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/http/impl/HttpUtils$CustomCompressor.class */
    private static class CustomCompressor extends HttpContentCompressor {
        private CustomCompressor() {
        }

        @Override // io.netty.handler.codec.http.HttpContentCompressor
        public ZlibWrapper determineWrapper(String acceptEncoding) {
            return super.determineWrapper(acceptEncoding);
        }
    }

    static String determineContentEncoding(Http2Headers headers) {
        ZlibWrapper wrapper;
        String acceptEncoding = headers.get(HttpHeaderNames.ACCEPT_ENCODING) != null ? headers.get(HttpHeaderNames.ACCEPT_ENCODING).toString() : null;
        if (acceptEncoding != null && (wrapper = compressor.determineWrapper(acceptEncoding)) != null) {
            switch (wrapper) {
                case GZIP:
                    return "gzip";
                case ZLIB:
                    return "deflate";
                default:
                    return null;
            }
        }
        return null;
    }

    static HttpMethod toNettyHttpMethod(io.vertx.core.http.HttpMethod method, String rawMethod) {
        switch (method) {
            case CONNECT:
                return HttpMethod.CONNECT;
            case GET:
                return HttpMethod.GET;
            case PUT:
                return HttpMethod.PUT;
            case POST:
                return HttpMethod.POST;
            case DELETE:
                return HttpMethod.DELETE;
            case HEAD:
                return HttpMethod.HEAD;
            case OPTIONS:
                return HttpMethod.OPTIONS;
            case TRACE:
                return HttpMethod.TRACE;
            case PATCH:
                return HttpMethod.PATCH;
            default:
                return HttpMethod.valueOf(rawMethod);
        }
    }

    static HttpVersion toNettyHttpVersion(io.vertx.core.http.HttpVersion version) {
        switch (version) {
            case HTTP_1_0:
                return HttpVersion.HTTP_1_0;
            case HTTP_1_1:
                return HttpVersion.HTTP_1_1;
            default:
                throw new IllegalArgumentException("Unsupported HTTP version: " + version);
        }
    }

    static io.vertx.core.http.HttpMethod toVertxMethod(String method) {
        try {
            return io.vertx.core.http.HttpMethod.valueOf(method);
        } catch (IllegalArgumentException e) {
            return io.vertx.core.http.HttpMethod.OTHER;
        }
    }

    public static int parseKeepAliveHeaderTimeout(CharSequence value) {
        int next;
        int ret;
        int len = value.length();
        int i = 0;
        while (true) {
            int pos = i;
            if (pos < len) {
                int idx = AsciiString.indexOf(value, ',', pos);
                if (idx == -1) {
                    next = len;
                    idx = len;
                } else {
                    next = idx + 1;
                }
                while (pos < idx && value.charAt(pos) == ' ') {
                    pos++;
                }
                int to = idx;
                while (to > pos && value.charAt(to - 1) == ' ') {
                    to--;
                }
                if (AsciiString.regionMatches(value, true, pos, TIMEOUT_EQ, 0, TIMEOUT_EQ.length())) {
                    int pos2 = pos + TIMEOUT_EQ.length();
                    if (pos2 < to) {
                        int i2 = 0;
                        while (true) {
                            ret = i2;
                            if (pos2 >= to) {
                                break;
                            }
                            int i3 = pos2;
                            pos2++;
                            int ch2 = value.charAt(i3);
                            if (ch2 < 48 || ch2 >= 57) {
                                break;
                            }
                            i2 = (ret * 10) + (ch2 - 48);
                        }
                        ret = -1;
                        if (ret > -1) {
                            return ret;
                        }
                    } else {
                        continue;
                    }
                }
                i = next;
            } else {
                return -1;
            }
        }
    }

    public static void validateHeader(CharSequence name, CharSequence value) {
        validateHeaderName(name);
        validateHeaderValue(value);
    }

    public static void validateHeader(CharSequence name, Iterable<? extends CharSequence> values) {
        validateHeaderName(name);
        values.forEach(HEADER_VALUE_VALIDATOR);
    }

    public static void validateHeaderValue(CharSequence seq) {
        int state = 0;
        for (int index = 0; index < seq.length(); index++) {
            state = validateValueChar(seq, state, seq.charAt(index));
        }
        if (state != 0) {
            throw new IllegalArgumentException("a header value must not end with '\\r' or '\\n':" + ((Object) seq));
        }
    }

    private static int validateValueChar(CharSequence seq, int state, char character) {
        if ((character & HIGHEST_INVALID_VALUE_CHAR_MASK) == 0) {
            switch (character) {
                case 0:
                    throw new IllegalArgumentException("a header value contains a prohibited character '��': " + ((Object) seq));
                case 11:
                    throw new IllegalArgumentException("a header value contains a prohibited character '\\v': " + ((Object) seq));
                case '\f':
                    throw new IllegalArgumentException("a header value contains a prohibited character '\\f': " + ((Object) seq));
            }
        }
        switch (state) {
            case 0:
                switch (character) {
                    case '\n':
                        return 2;
                    case '\r':
                        return 1;
                }
            case 1:
                switch (character) {
                    case '\n':
                        return 2;
                    default:
                        throw new IllegalArgumentException("only '\\n' is allowed after '\\r': " + ((Object) seq));
                }
            case 2:
                switch (character) {
                    case '\t':
                    case ' ':
                        return 0;
                    default:
                        throw new IllegalArgumentException("only ' ' and '\\t' are allowed after '\\n': " + ((Object) seq));
                }
        }
        return state;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void validateHeaderName(java.lang.CharSequence r5) {
        /*
            r0 = 0
            r6 = r0
        L2:
            r0 = r6
            r1 = r5
            int r1 = r1.length()
            if (r0 >= r1) goto Lba
            r0 = r5
            r1 = r6
            char r0 = r0.charAt(r1)
            r7 = r0
            r0 = r7
            switch(r0) {
                case 0: goto L78;
                case 9: goto L78;
                case 10: goto L78;
                case 11: goto L78;
                case 12: goto L78;
                case 13: goto L78;
                case 32: goto L78;
                case 44: goto L78;
                case 58: goto L78;
                case 59: goto L78;
                case 61: goto L78;
                default: goto L93;
            }
        L78:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = r2
            r3.<init>()
            java.lang.String r3 = "a header name cannot contain the following prohibited characters: =,;: \\t\\r\\n\\v\\f: "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r5
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L93:
            r0 = r7
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto Lb4
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = r2
            r3.<init>()
            java.lang.String r3 = "a header name cannot contain non-ASCII character: "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r5
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        Lb4:
            int r6 = r6 + 1
            goto L2
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.http.impl.HttpUtils.validateHeaderName(java.lang.CharSequence):void");
    }
}
