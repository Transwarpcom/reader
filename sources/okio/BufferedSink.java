package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;

/* compiled from: BufferedSink.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018��2\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\b\u0010\u0007\u001a\u00020��H&J\b\u0010\b\u001a\u00020��H&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020��2\u0006\u0010\u000e\u001a\u00020\u000fH&J \u0010\r\u001a\u00020��2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&J\u0010\u0010\r\u001a\u00020��2\u0006\u0010\u0013\u001a\u00020\u0014H&J \u0010\r\u001a\u00020��2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&J\u0018\u0010\r\u001a\u00020��2\u0006\u0010\u000e\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u0015H&J\u0010\u0010\u0018\u001a\u00020��2\u0006\u0010\u0019\u001a\u00020\u0011H&J\u0010\u0010\u001a\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010\u001c\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010\u001d\u001a\u00020��2\u0006\u0010\u001e\u001a\u00020\u0011H&J\u0010\u0010\u001f\u001a\u00020��2\u0006\u0010\u001e\u001a\u00020\u0011H&J\u0010\u0010 \u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010!\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010\"\u001a\u00020��2\u0006\u0010#\u001a\u00020\u0011H&J\u0010\u0010$\u001a\u00020��2\u0006\u0010#\u001a\u00020\u0011H&J\u0018\u0010%\u001a\u00020��2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H&J(\u0010%\u001a\u00020��2\u0006\u0010&\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u00112\u0006\u0010(\u001a\u00020)H&J\u0010\u0010,\u001a\u00020��2\u0006\u0010&\u001a\u00020'H&J \u0010,\u001a\u00020��2\u0006\u0010&\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0011H&J\u0010\u0010-\u001a\u00020��2\u0006\u0010.\u001a\u00020\u0011H&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006/"}, d2 = {"Lokio/BufferedSink;", "Lokio/Sink;", "Ljava/nio/channels/WritableByteChannel;", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "emit", "emitCompleteSegments", "flush", "", "outputStream", "Ljava/io/OutputStream;", "write", PackageDocumentBase.DCTags.source, "", "offset", "", "byteCount", "byteString", "Lokio/ByteString;", "Lokio/Source;", "", "writeAll", "writeByte", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "writeDecimalLong", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", OperatorName.CLOSE_AND_STROKE, "writeShortLe", "writeString", "string", "", "charset", "Ljava/nio/charset/Charset;", "beginIndex", "endIndex", "writeUtf8", "writeUtf8CodePoint", "codePoint", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/BufferedSink.class */
public interface BufferedSink extends Sink, WritableByteChannel {
    @Deprecated(message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(imports = {}, expression = "buffer"), level = DeprecationLevel.WARNING)
    @NotNull
    Buffer buffer();

    @NotNull
    Buffer getBuffer();

    @NotNull
    BufferedSink write(@NotNull ByteString byteString) throws IOException;

    @NotNull
    BufferedSink write(@NotNull ByteString byteString, int i, int i2) throws IOException;

    @NotNull
    BufferedSink write(@NotNull byte[] bArr) throws IOException;

    @NotNull
    BufferedSink write(@NotNull byte[] bArr, int i, int i2) throws IOException;

    long writeAll(@NotNull Source source) throws IOException;

    @NotNull
    BufferedSink write(@NotNull Source source, long j) throws IOException;

    @NotNull
    BufferedSink writeUtf8(@NotNull String str) throws IOException;

    @NotNull
    BufferedSink writeUtf8(@NotNull String str, int i, int i2) throws IOException;

    @NotNull
    BufferedSink writeUtf8CodePoint(int i) throws IOException;

    @NotNull
    BufferedSink writeString(@NotNull String str, @NotNull Charset charset) throws IOException;

    @NotNull
    BufferedSink writeString(@NotNull String str, int i, int i2, @NotNull Charset charset) throws IOException;

    @NotNull
    BufferedSink writeByte(int i) throws IOException;

    @NotNull
    BufferedSink writeShort(int i) throws IOException;

    @NotNull
    BufferedSink writeShortLe(int i) throws IOException;

    @NotNull
    BufferedSink writeInt(int i) throws IOException;

    @NotNull
    BufferedSink writeIntLe(int i) throws IOException;

    @NotNull
    BufferedSink writeLong(long j) throws IOException;

    @NotNull
    BufferedSink writeLongLe(long j) throws IOException;

    @NotNull
    BufferedSink writeDecimalLong(long j) throws IOException;

    @NotNull
    BufferedSink writeHexadecimalUnsignedLong(long j) throws IOException;

    @Override // okio.Sink, java.io.Flushable
    void flush() throws IOException;

    @NotNull
    BufferedSink emit() throws IOException;

    @NotNull
    BufferedSink emitCompleteSegments() throws IOException;

    @NotNull
    OutputStream outputStream();
}
