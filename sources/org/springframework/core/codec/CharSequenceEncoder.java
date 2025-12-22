package org.springframework.core.codec;

import java.nio.charset.Charset;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/core/codec/CharSequenceEncoder.class */
public final class CharSequenceEncoder extends AbstractEncoder<CharSequence> {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final ConcurrentMap<Charset, Float> charsetToMaxBytesPerChar;

    private CharSequenceEncoder(MimeType... mimeTypes) {
        super(mimeTypes);
        this.charsetToMaxBytesPerChar = new ConcurrentHashMap(3);
    }

    @Override // org.springframework.core.codec.AbstractEncoder, org.springframework.core.codec.Encoder
    public boolean canEncode(ResolvableType elementType, @Nullable MimeType mimeType) {
        Class<?> clazz = elementType.toClass();
        return super.canEncode(elementType, mimeType) && CharSequence.class.isAssignableFrom(clazz);
    }

    @Override // org.springframework.core.codec.Encoder
    public Flux<DataBuffer> encode(Publisher<? extends CharSequence> inputStream, DataBufferFactory bufferFactory, ResolvableType elementType, @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {
        Charset charset = getCharset(mimeType);
        return Flux.from(inputStream).map(charSequence -> {
            if (!Hints.isLoggingSuppressed(hints)) {
                LogFormatUtils.traceDebug(this.logger, traceOn -> {
                    String formatted = LogFormatUtils.formatValue(charSequence, !traceOn.booleanValue());
                    return Hints.getLogPrefix(hints) + "Writing " + formatted;
                });
            }
            boolean release = true;
            int capacity = calculateCapacity(charSequence, charset);
            DataBuffer dataBuffer = bufferFactory.allocateBuffer(capacity);
            try {
                try {
                    dataBuffer.write(charSequence, charset);
                    release = false;
                    if (0 != 0) {
                        DataBufferUtils.release(dataBuffer);
                    }
                    return dataBuffer;
                } catch (CoderMalfunctionError ex) {
                    throw new EncodingException("String encoding error: " + ex.getMessage(), ex);
                }
            } catch (Throwable th) {
                if (release) {
                    DataBufferUtils.release(dataBuffer);
                }
                throw th;
            }
        });
    }

    int calculateCapacity(CharSequence sequence, Charset charset) {
        float maxBytesPerChar = this.charsetToMaxBytesPerChar.computeIfAbsent(charset, cs -> {
            return Float.valueOf(cs.newEncoder().maxBytesPerChar());
        }).floatValue();
        float maxBytesForSequence = sequence.length() * maxBytesPerChar;
        return (int) Math.ceil(maxBytesForSequence);
    }

    private Charset getCharset(@Nullable MimeType mimeType) {
        if (mimeType != null && mimeType.getCharset() != null) {
            return mimeType.getCharset();
        }
        return DEFAULT_CHARSET;
    }

    public static CharSequenceEncoder textPlainOnly() {
        return new CharSequenceEncoder(new MimeType(NCXDocumentV2.NCXTags.text, "plain", DEFAULT_CHARSET));
    }

    public static CharSequenceEncoder allMimeTypes() {
        return new CharSequenceEncoder(new MimeType(NCXDocumentV2.NCXTags.text, "plain", DEFAULT_CHARSET), MimeTypeUtils.ALL);
    }
}
