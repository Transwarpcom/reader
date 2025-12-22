package okio;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.IOException;
import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;

/* compiled from: DeflaterSink.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\u0018��2\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0006\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0003J\r\u0010\u000e\u001a\u00020\u000bH��¢\u0006\u0002\b\u000fJ\b\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0002\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001a"}, d2 = {"Lokio/DeflaterSink;", "Lokio/Sink;", "sink", "deflater", "Ljava/util/zip/Deflater;", "(Lokio/Sink;Ljava/util/zip/Deflater;)V", "Lokio/BufferedSink;", "(Lokio/BufferedSink;Ljava/util/zip/Deflater;)V", "closed", "", "close", "", "deflate", "syncFlush", "finishDeflate", "finishDeflate$okio", "flush", RtspHeaders.Values.TIMEOUT, "Lokio/Timeout;", "toString", "", "write", PackageDocumentBase.DCTags.source, "Lokio/Buffer;", "byteCount", "", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/DeflaterSink.class */
public final class DeflaterSink implements Sink {
    private boolean closed;
    private final BufferedSink sink;
    private final Deflater deflater;

    public DeflaterSink(@NotNull BufferedSink sink, @NotNull Deflater deflater) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        this.sink = sink;
        this.deflater = deflater;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DeflaterSink(@NotNull Sink sink, @NotNull Deflater deflater) {
        this(Okio.buffer(sink), deflater);
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Util.checkOffsetAndCount(source.size(), 0L, byteCount);
        long j = byteCount;
        while (true) {
            long remaining = j;
            if (remaining > 0) {
                Segment head = source.head;
                Intrinsics.checkNotNull(head);
                int b$iv = head.limit - head.pos;
                int toDeflate = (int) Math.min(remaining, b$iv);
                this.deflater.setInput(head.data, head.pos, toDeflate);
                deflate(false);
                source.setSize$okio(source.size() - toDeflate);
                head.pos += toDeflate;
                if (head.pos == head.limit) {
                    source.head = head.pop();
                    SegmentPool.recycle(head);
                }
                j = remaining - toDeflate;
            } else {
                return;
            }
        }
    }

    @IgnoreJRERequirement
    private final void deflate(boolean syncFlush) throws IOException {
        Segment s;
        int iDeflate;
        Buffer buffer = this.sink.getBuffer();
        while (true) {
            s = buffer.writableSegment$okio(1);
            if (syncFlush) {
                iDeflate = this.deflater.deflate(s.data, s.limit, 8192 - s.limit, 2);
            } else {
                iDeflate = this.deflater.deflate(s.data, s.limit, 8192 - s.limit);
            }
            int deflated = iDeflate;
            if (deflated > 0) {
                s.limit += deflated;
                buffer.setSize$okio(buffer.size() + deflated);
                this.sink.emitCompleteSegments();
            } else if (this.deflater.needsInput()) {
                break;
            }
        }
        if (s.pos == s.limit) {
            buffer.head = s.pop();
            SegmentPool.recycle(s);
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        deflate(true);
        this.sink.flush();
    }

    public final void finishDeflate$okio() throws IOException {
        this.deflater.finish();
        deflate(false);
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.closed) {
            return;
        }
        Throwable thrown = (Throwable) null;
        try {
            finishDeflate$okio();
        } catch (Throwable e) {
            thrown = e;
        }
        try {
            this.deflater.end();
        } catch (Throwable e2) {
            if (thrown == null) {
                thrown = e2;
            }
        }
        try {
            this.sink.close();
        } catch (Throwable e3) {
            if (thrown == null) {
                thrown = e3;
            }
        }
        this.closed = true;
        if (thrown != null) {
            throw thrown;
        }
    }

    @Override // okio.Sink
    @NotNull
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @NotNull
    public String toString() {
        return "DeflaterSink(" + this.sink + ')';
    }
}
