package okio;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import kotlin.Metadata;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;

/* compiled from: Sink.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\bf\u0018��2\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, d2 = {"Lokio/Sink;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "close", "", "flush", RtspHeaders.Values.TIMEOUT, "Lokio/Timeout;", "write", PackageDocumentBase.DCTags.source, "Lokio/Buffer;", "byteCount", "", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Sink.class */
public interface Sink extends Closeable, Flushable {
    void write(@NotNull Buffer buffer, long j) throws IOException;

    void flush() throws IOException;

    @NotNull
    Timeout timeout();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;
}
