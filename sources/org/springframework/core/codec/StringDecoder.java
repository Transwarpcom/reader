package org.springframework.core.codec;

import cn.hutool.core.text.StrPool;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.core.io.buffer.PooledDataBuffer;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/codec/StringDecoder.class */
public final class StringDecoder extends AbstractDataBufferDecoder<String> {
    private static final DataBuffer END_FRAME = new DefaultDataBufferFactory().wrap(new byte[0]);
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    public static final List<String> DEFAULT_DELIMITERS = Arrays.asList(StrPool.CRLF, "\n");
    private final List<String> delimiters;
    private final boolean stripDelimiter;
    private final ConcurrentMap<Charset, List<byte[]>> delimitersCache;

    @Override // org.springframework.core.codec.AbstractDataBufferDecoder
    protected /* bridge */ /* synthetic */ String decodeDataBuffer(DataBuffer dataBuffer, ResolvableType resolvableType, @Nullable MimeType mimeType, @Nullable Map map) {
        return decodeDataBuffer(dataBuffer, resolvableType, mimeType, (Map<String, Object>) map);
    }

    private StringDecoder(List<String> delimiters, boolean stripDelimiter, MimeType... mimeTypes) {
        super(mimeTypes);
        this.delimitersCache = new ConcurrentHashMap();
        Assert.notEmpty(delimiters, "'delimiters' must not be empty");
        this.delimiters = new ArrayList(delimiters);
        this.stripDelimiter = stripDelimiter;
    }

    @Override // org.springframework.core.codec.AbstractDecoder, org.springframework.core.codec.Decoder
    public boolean canDecode(ResolvableType elementType, @Nullable MimeType mimeType) {
        return elementType.resolve() == String.class && super.canDecode(elementType, mimeType);
    }

    @Override // org.springframework.core.codec.AbstractDataBufferDecoder, org.springframework.core.codec.Decoder
    public Flux<String> decode(Publisher<DataBuffer> input, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        List<byte[]> delimiterBytes = getDelimiterBytes(mimeType);
        Flux<DataBuffer> inputFlux = Flux.from(input).flatMapIterable(buffer -> {
            return splitOnDelimiter(buffer, delimiterBytes);
        }).bufferUntil(buffer2 -> {
            return buffer2 == END_FRAME;
        }).map(StringDecoder::joinUntilEndFrame).doOnDiscard(PooledDataBuffer.class, (v0) -> {
            DataBufferUtils.release(v0);
        });
        return super.decode(inputFlux, elementType, mimeType, hints);
    }

    private List<byte[]> getDelimiterBytes(@Nullable MimeType mimeType) {
        return this.delimitersCache.computeIfAbsent(getCharset(mimeType), charset -> {
            List<byte[]> list = new ArrayList<>();
            for (String delimiter : this.delimiters) {
                byte[] bytes = delimiter.getBytes(charset);
                list.add(bytes);
            }
            return list;
        });
    }

    private List<DataBuffer> splitOnDelimiter(DataBuffer buffer, List<byte[]> delimiterBytes) {
        DataBuffer dataBufferSlice;
        List<DataBuffer> frames = new ArrayList<>();
        do {
            try {
                try {
                    int length = Integer.MAX_VALUE;
                    byte[] matchingDelimiter = null;
                    for (byte[] delimiter : delimiterBytes) {
                        int index = indexOf(buffer, delimiter);
                        if (index >= 0 && index < length) {
                            length = index;
                            matchingDelimiter = delimiter;
                        }
                    }
                    int readPosition = buffer.readPosition();
                    if (matchingDelimiter != null) {
                        if (this.stripDelimiter) {
                            dataBufferSlice = buffer.slice(readPosition, length);
                        } else {
                            dataBufferSlice = buffer.slice(readPosition, length + matchingDelimiter.length);
                        }
                        DataBuffer frame = dataBufferSlice;
                        buffer.readPosition(readPosition + length + matchingDelimiter.length);
                        frames.add(DataBufferUtils.retain(frame));
                        frames.add(END_FRAME);
                    } else {
                        DataBuffer frame2 = buffer.slice(readPosition, buffer.readableByteCount());
                        buffer.readPosition(readPosition + buffer.readableByteCount());
                        frames.add(DataBufferUtils.retain(frame2));
                    }
                } finally {
                }
            } finally {
                DataBufferUtils.release(buffer);
            }
        } while (buffer.readableByteCount() > 0);
        return frames;
    }

    private static int indexOf(DataBuffer buffer, byte[] delimiter) {
        for (int i = buffer.readPosition(); i < buffer.writePosition(); i++) {
            int bufferPos = i;
            int delimiterPos = 0;
            while (delimiterPos < delimiter.length && buffer.getByte(bufferPos) == delimiter[delimiterPos]) {
                bufferPos++;
                boolean endOfBuffer = bufferPos == buffer.writePosition();
                boolean endOfDelimiter = delimiterPos == delimiter.length - 1;
                if (endOfBuffer && !endOfDelimiter) {
                    return -1;
                }
                delimiterPos++;
            }
            if (delimiterPos == delimiter.length) {
                return i - buffer.readPosition();
            }
        }
        return -1;
    }

    private static DataBuffer joinUntilEndFrame(List<DataBuffer> dataBuffers) {
        if (!dataBuffers.isEmpty()) {
            int lastIdx = dataBuffers.size() - 1;
            if (dataBuffers.get(lastIdx) == END_FRAME) {
                dataBuffers.remove(lastIdx);
            }
        }
        return dataBuffers.get(0).factory().join(dataBuffers);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.springframework.core.codec.AbstractDataBufferDecoder
    protected String decodeDataBuffer(DataBuffer dataBuffer, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        Charset charset = getCharset(mimeType);
        CharBuffer charBuffer = charset.decode(dataBuffer.asByteBuffer());
        DataBufferUtils.release(dataBuffer);
        String value = charBuffer.toString();
        LogFormatUtils.traceDebug(this.logger, traceOn -> {
            String formatted = LogFormatUtils.formatValue(value, !traceOn.booleanValue());
            return Hints.getLogPrefix(hints) + "Decoded " + formatted;
        });
        return value;
    }

    private static Charset getCharset(@Nullable MimeType mimeType) {
        if (mimeType != null && mimeType.getCharset() != null) {
            return mimeType.getCharset();
        }
        return DEFAULT_CHARSET;
    }

    @Deprecated
    public static StringDecoder textPlainOnly(boolean ignored) {
        return textPlainOnly();
    }

    public static StringDecoder textPlainOnly() {
        return textPlainOnly(DEFAULT_DELIMITERS, true);
    }

    public static StringDecoder textPlainOnly(List<String> delimiters, boolean stripDelimiter) {
        return new StringDecoder(delimiters, stripDelimiter, new MimeType(NCXDocumentV2.NCXTags.text, "plain", DEFAULT_CHARSET));
    }

    @Deprecated
    public static StringDecoder allMimeTypes(boolean ignored) {
        return allMimeTypes();
    }

    public static StringDecoder allMimeTypes() {
        return allMimeTypes(DEFAULT_DELIMITERS, true);
    }

    public static StringDecoder allMimeTypes(List<String> delimiters, boolean stripDelimiter) {
        return new StringDecoder(delimiters, stripDelimiter, new MimeType(NCXDocumentV2.NCXTags.text, "plain", DEFAULT_CHARSET), MimeTypeUtils.ALL);
    }
}
