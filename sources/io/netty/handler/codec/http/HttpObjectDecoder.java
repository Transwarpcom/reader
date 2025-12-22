package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.PrematureChannelClosureException;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.AppendableCharSequence;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

/* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpObjectDecoder.class */
public abstract class HttpObjectDecoder extends ByteToMessageDecoder {
    private static final String EMPTY_VALUE = "";
    private final int maxChunkSize;
    private final boolean chunkedSupported;
    protected final boolean validateHeaders;
    private final HeaderParser headerParser;
    private final LineParser lineParser;
    private HttpMessage message;
    private long chunkSize;
    private long contentLength;
    private volatile boolean resetRequested;
    private CharSequence name;
    private CharSequence value;
    private LastHttpContent trailer;
    private State currentState;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpObjectDecoder$State.class */
    private enum State {
        SKIP_CONTROL_CHARS,
        READ_INITIAL,
        READ_HEADER,
        READ_VARIABLE_LENGTH_CONTENT,
        READ_FIXED_LENGTH_CONTENT,
        READ_CHUNK_SIZE,
        READ_CHUNKED_CONTENT,
        READ_CHUNK_DELIMITER,
        READ_CHUNK_FOOTER,
        BAD_MESSAGE,
        UPGRADED
    }

    protected abstract boolean isDecodingRequest();

    protected abstract HttpMessage createMessage(String[] strArr) throws Exception;

    protected abstract HttpMessage createInvalidMessage();

    static {
        $assertionsDisabled = !HttpObjectDecoder.class.desiredAssertionStatus();
    }

    protected HttpObjectDecoder() {
        this(4096, 8192, 8192, true);
    }

    protected HttpObjectDecoder(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean chunkedSupported) {
        this(maxInitialLineLength, maxHeaderSize, maxChunkSize, chunkedSupported, true);
    }

    protected HttpObjectDecoder(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean chunkedSupported, boolean validateHeaders) {
        this(maxInitialLineLength, maxHeaderSize, maxChunkSize, chunkedSupported, validateHeaders, 128);
    }

    protected HttpObjectDecoder(int maxInitialLineLength, int maxHeaderSize, int maxChunkSize, boolean chunkedSupported, boolean validateHeaders, int initialBufferSize) {
        this.contentLength = Long.MIN_VALUE;
        this.currentState = State.SKIP_CONTROL_CHARS;
        ObjectUtil.checkPositive(maxInitialLineLength, "maxInitialLineLength");
        ObjectUtil.checkPositive(maxHeaderSize, "maxHeaderSize");
        ObjectUtil.checkPositive(maxChunkSize, "maxChunkSize");
        AppendableCharSequence seq = new AppendableCharSequence(initialBufferSize);
        this.lineParser = new LineParser(seq, maxInitialLineLength);
        this.headerParser = new HeaderParser(seq, maxHeaderSize);
        this.maxChunkSize = maxChunkSize;
        this.chunkedSupported = chunkedSupported;
        this.validateHeaders = validateHeaders;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x031c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006f A[Catch: Exception -> 0x0099, TRY_ENTER, TryCatch #2 {Exception -> 0x0099, blocks: (B:11:0x005f, B:14:0x006f, B:16:0x007d, B:18:0x0085), top: B:122:0x005f }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b7 A[Catch: Exception -> 0x0186, TRY_ENTER, TryCatch #3 {Exception -> 0x0186, blocks: (B:22:0x00aa, B:25:0x00b7, B:26:0x00c6, B:27:0x00e0, B:29:0x00fa, B:31:0x0101, B:32:0x010a, B:33:0x010b, B:35:0x0117, B:39:0x012d, B:43:0x014e, B:45:0x0154, B:47:0x015c, B:49:0x0164, B:50:0x016b, B:51:0x016c, B:53:0x017f, B:41:0x0134), top: B:123:0x00aa }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02b5  */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void decode(io.netty.channel.ChannelHandlerContext r7, io.netty.buffer.ByteBuf r8, java.util.List<java.lang.Object> r9) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 888
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.http.HttpObjectDecoder.decode(io.netty.channel.ChannelHandlerContext, io.netty.buffer.ByteBuf, java.util.List):void");
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decodeLast(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        boolean prematureClosure;
        super.decodeLast(ctx, in, out);
        if (this.resetRequested) {
            resetNow();
        }
        if (this.message != null) {
            boolean chunked = HttpUtil.isTransferEncodingChunked(this.message);
            if (this.currentState == State.READ_VARIABLE_LENGTH_CONTENT && !in.isReadable() && !chunked) {
                out.add(LastHttpContent.EMPTY_LAST_CONTENT);
                resetNow();
                return;
            }
            if (this.currentState == State.READ_HEADER) {
                out.add(invalidMessage(Unpooled.EMPTY_BUFFER, new PrematureChannelClosureException("Connection closed before received headers")));
                resetNow();
                return;
            }
            if (isDecodingRequest() || chunked) {
                prematureClosure = true;
            } else {
                prematureClosure = contentLength() > 0;
            }
            if (!prematureClosure) {
                out.add(LastHttpContent.EMPTY_LAST_CONTENT);
            }
            resetNow();
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof HttpExpectationFailedEvent) {
            switch (this.currentState) {
                case READ_CHUNK_SIZE:
                case READ_VARIABLE_LENGTH_CONTENT:
                case READ_FIXED_LENGTH_CONTENT:
                    reset();
                    break;
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    protected boolean isContentAlwaysEmpty(HttpMessage msg) {
        if (msg instanceof HttpResponse) {
            HttpResponse res = (HttpResponse) msg;
            int code = res.status().code();
            if (code >= 100 && code < 200) {
                return (code == 101 && !res.headers().contains(HttpHeaderNames.SEC_WEBSOCKET_ACCEPT) && res.headers().contains((CharSequence) HttpHeaderNames.UPGRADE, (CharSequence) HttpHeaderValues.WEBSOCKET, true)) ? false : true;
            }
            switch (code) {
                case 204:
                case 304:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    protected boolean isSwitchingToNonHttp1Protocol(HttpResponse msg) {
        if (msg.status().code() != HttpResponseStatus.SWITCHING_PROTOCOLS.code()) {
            return false;
        }
        String newProtocol = msg.headers().get(HttpHeaderNames.UPGRADE);
        return newProtocol == null || !(newProtocol.contains(HttpVersion.HTTP_1_0.text()) || newProtocol.contains(HttpVersion.HTTP_1_1.text()));
    }

    public void reset() {
        this.resetRequested = true;
    }

    private void resetNow() {
        HttpResponse res;
        HttpMessage message = this.message;
        this.message = null;
        this.name = null;
        this.value = null;
        this.contentLength = Long.MIN_VALUE;
        this.lineParser.reset();
        this.headerParser.reset();
        this.trailer = null;
        if (!isDecodingRequest() && (res = (HttpResponse) message) != null && isSwitchingToNonHttp1Protocol(res)) {
            this.currentState = State.UPGRADED;
        } else {
            this.resetRequested = false;
            this.currentState = State.SKIP_CONTROL_CHARS;
        }
    }

    private HttpMessage invalidMessage(ByteBuf in, Exception cause) {
        this.currentState = State.BAD_MESSAGE;
        in.skipBytes(in.readableBytes());
        if (this.message == null) {
            this.message = createInvalidMessage();
        }
        this.message.setDecoderResult(DecoderResult.failure(cause));
        HttpMessage ret = this.message;
        this.message = null;
        return ret;
    }

    private HttpContent invalidChunk(ByteBuf in, Exception cause) {
        this.currentState = State.BAD_MESSAGE;
        in.skipBytes(in.readableBytes());
        HttpContent chunk = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
        chunk.setDecoderResult(DecoderResult.failure(cause));
        this.message = null;
        this.trailer = null;
        return chunk;
    }

    private static boolean skipControlCharacters(ByteBuf buffer) {
        boolean skiped = false;
        int wIdx = buffer.writerIndex();
        int rIdx = buffer.readerIndex();
        while (true) {
            if (wIdx <= rIdx) {
                break;
            }
            int i = rIdx;
            rIdx++;
            int c = buffer.getUnsignedByte(i);
            if (!Character.isISOControl(c) && !Character.isWhitespace(c)) {
                rIdx--;
                skiped = true;
                break;
            }
        }
        buffer.readerIndex(rIdx);
        return skiped;
    }

    private State readHeaders(ByteBuf buffer) {
        State nextState;
        HttpMessage message = this.message;
        HttpHeaders headers = message.headers();
        AppendableCharSequence line = this.headerParser.parse(buffer);
        if (line == null) {
            return null;
        }
        if (line.length() > 0) {
            do {
                char firstChar = line.charAtUnsafe(0);
                if (this.name != null && (firstChar == ' ' || firstChar == '\t')) {
                    String trimmedLine = line.toString().trim();
                    String valueStr = String.valueOf(this.value);
                    this.value = valueStr + ' ' + trimmedLine;
                } else {
                    if (this.name != null) {
                        headers.add(this.name, this.value);
                    }
                    splitHeader(line);
                }
                line = this.headerParser.parse(buffer);
                if (line == null) {
                    return null;
                }
            } while (line.length() > 0);
        }
        if (this.name != null) {
            headers.add(this.name, this.value);
        }
        this.name = null;
        this.value = null;
        if (isContentAlwaysEmpty(message)) {
            HttpUtil.setTransferEncodingChunked(message, false);
            nextState = State.SKIP_CONTROL_CHARS;
        } else if (HttpUtil.isTransferEncodingChunked(message)) {
            nextState = State.READ_CHUNK_SIZE;
        } else if (contentLength() >= 0) {
            nextState = State.READ_FIXED_LENGTH_CONTENT;
        } else {
            nextState = State.READ_VARIABLE_LENGTH_CONTENT;
        }
        return nextState;
    }

    private long contentLength() {
        if (this.contentLength == Long.MIN_VALUE) {
            this.contentLength = HttpUtil.getContentLength(this.message, -1L);
        }
        return this.contentLength;
    }

    private LastHttpContent readTrailingHeaders(ByteBuf buffer) {
        AppendableCharSequence line = this.headerParser.parse(buffer);
        if (line == null) {
            return null;
        }
        LastHttpContent trailer = this.trailer;
        if (line.length() == 0 && trailer == null) {
            return LastHttpContent.EMPTY_LAST_CONTENT;
        }
        CharSequence lastHeader = null;
        if (trailer == null) {
            DefaultLastHttpContent defaultLastHttpContent = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER, this.validateHeaders);
            this.trailer = defaultLastHttpContent;
            trailer = defaultLastHttpContent;
        }
        while (line.length() > 0) {
            char firstChar = line.charAtUnsafe(0);
            if (lastHeader != null && (firstChar == ' ' || firstChar == '\t')) {
                List<String> current = trailer.trailingHeaders().getAll(lastHeader);
                if (!current.isEmpty()) {
                    int lastPos = current.size() - 1;
                    String lineTrimmed = line.toString().trim();
                    String currentLastPos = current.get(lastPos);
                    current.set(lastPos, currentLastPos + lineTrimmed);
                }
            } else {
                splitHeader(line);
                CharSequence headerName = this.name;
                if (!HttpHeaderNames.CONTENT_LENGTH.contentEqualsIgnoreCase(headerName) && !HttpHeaderNames.TRANSFER_ENCODING.contentEqualsIgnoreCase(headerName) && !HttpHeaderNames.TRAILER.contentEqualsIgnoreCase(headerName)) {
                    trailer.trailingHeaders().add(headerName, this.value);
                }
                lastHeader = this.name;
                this.name = null;
                this.value = null;
            }
            line = this.headerParser.parse(buffer);
            if (line == null) {
                return null;
            }
        }
        this.trailer = null;
        return trailer;
    }

    private static int getChunkSize(String hex) {
        String hex2 = hex.trim();
        for (int i = 0; i < hex2.length(); i++) {
            char c = hex2.charAt(i);
            if (c == ';' || Character.isWhitespace(c) || Character.isISOControl(c)) {
                hex2 = hex2.substring(0, i);
                break;
            }
        }
        return Integer.parseInt(hex2, 16);
    }

    private static String[] splitInitialLine(AppendableCharSequence sb) {
        int aStart = findNonWhitespace(sb, 0);
        int aEnd = findWhitespace(sb, aStart);
        int bStart = findNonWhitespace(sb, aEnd);
        int bEnd = findWhitespace(sb, bStart);
        int cStart = findNonWhitespace(sb, bEnd);
        int cEnd = findEndOfString(sb);
        String[] strArr = new String[3];
        strArr[0] = sb.subStringUnsafe(aStart, aEnd);
        strArr[1] = sb.subStringUnsafe(bStart, bEnd);
        strArr[2] = cStart < cEnd ? sb.subStringUnsafe(cStart, cEnd) : "";
        return strArr;
    }

    private void splitHeader(AppendableCharSequence sb) {
        char ch2;
        int length = sb.length();
        int nameStart = findNonWhitespace(sb, 0);
        int nameEnd = nameStart;
        while (nameEnd < length && (ch2 = sb.charAtUnsafe(nameEnd)) != ':' && (isDecodingRequest() || !Character.isWhitespace(ch2))) {
            nameEnd++;
        }
        int colonEnd = nameEnd;
        while (true) {
            if (colonEnd >= length) {
                break;
            }
            if (sb.charAtUnsafe(colonEnd) != ':') {
                colonEnd++;
            } else {
                colonEnd++;
                break;
            }
        }
        this.name = sb.subStringUnsafe(nameStart, nameEnd);
        int valueStart = findNonWhitespace(sb, colonEnd);
        if (valueStart == length) {
            this.value = "";
        } else {
            int valueEnd = findEndOfString(sb);
            this.value = sb.subStringUnsafe(valueStart, valueEnd);
        }
    }

    private static int findNonWhitespace(AppendableCharSequence sb, int offset) {
        for (int result = offset; result < sb.length(); result++) {
            if (!Character.isWhitespace(sb.charAtUnsafe(result))) {
                return result;
            }
        }
        return sb.length();
    }

    private static int findWhitespace(AppendableCharSequence sb, int offset) {
        for (int result = offset; result < sb.length(); result++) {
            if (Character.isWhitespace(sb.charAtUnsafe(result))) {
                return result;
            }
        }
        return sb.length();
    }

    private static int findEndOfString(AppendableCharSequence sb) {
        for (int result = sb.length() - 1; result > 0; result--) {
            if (!Character.isWhitespace(sb.charAtUnsafe(result))) {
                return result + 1;
            }
        }
        return 0;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpObjectDecoder$HeaderParser.class */
    private static class HeaderParser implements ByteProcessor {
        private final AppendableCharSequence seq;
        private final int maxLength;
        private int size;

        HeaderParser(AppendableCharSequence seq, int maxLength) {
            this.seq = seq;
            this.maxLength = maxLength;
        }

        public AppendableCharSequence parse(ByteBuf buffer) {
            int oldSize = this.size;
            this.seq.reset();
            int i = buffer.forEachByte(this);
            if (i == -1) {
                this.size = oldSize;
                return null;
            }
            buffer.readerIndex(i + 1);
            return this.seq;
        }

        public void reset() {
            this.size = 0;
        }

        @Override // io.netty.util.ByteProcessor
        public boolean process(byte value) throws Exception {
            char nextByte = (char) (value & 255);
            if (nextByte == '\r') {
                return true;
            }
            if (nextByte == '\n') {
                return false;
            }
            int i = this.size + 1;
            this.size = i;
            if (i > this.maxLength) {
                throw newException(this.maxLength);
            }
            this.seq.append(nextByte);
            return true;
        }

        protected TooLongFrameException newException(int maxLength) {
            return new TooLongFrameException("HTTP header is larger than " + maxLength + " bytes.");
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-codec-http-4.1.42.Final.jar:io/netty/handler/codec/http/HttpObjectDecoder$LineParser.class */
    private static final class LineParser extends HeaderParser {
        LineParser(AppendableCharSequence seq, int maxLength) {
            super(seq, maxLength);
        }

        @Override // io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser
        public AppendableCharSequence parse(ByteBuf buffer) {
            reset();
            return super.parse(buffer);
        }

        @Override // io.netty.handler.codec.http.HttpObjectDecoder.HeaderParser
        protected TooLongFrameException newException(int maxLength) {
            return new TooLongFrameException("An HTTP line is larger than " + maxLength + " bytes.");
        }
    }
}
