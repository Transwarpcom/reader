package okio;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;

/* compiled from: GzipSource.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0002\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n��¨\u0006!"}, d2 = {"Lokio/GzipSource;", "Lokio/Source;", PackageDocumentBase.DCTags.source, "(Lokio/Source;)V", "crc", "Ljava/util/zip/CRC32;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "section", "", "Lokio/RealBufferedSource;", "checkEqual", "", "name", "", "expected", "", "actual", "close", "consumeHeader", "consumeTrailer", "read", "", "sink", "Lokio/Buffer;", "byteCount", RtspHeaders.Values.TIMEOUT, "Lokio/Timeout;", "updateCrc", "buffer", "offset", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/GzipSource.class */
public final class GzipSource implements Source {
    private byte section;
    private final RealBufferedSource source;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private final CRC32 crc;

    public GzipSource(@NotNull Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = new RealBufferedSource(source);
        this.inflater = new Inflater(true);
        this.inflaterSource = new InflaterSource((BufferedSource) this.source, this.inflater);
        this.crc = new CRC32();
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long byteCount) throws DataFormatException, IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (!(byteCount >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
        }
        if (byteCount == 0) {
            return 0L;
        }
        if (this.section == 0) {
            consumeHeader();
            this.section = (byte) 1;
        }
        if (this.section == 1) {
            long offset = sink.size();
            long result = this.inflaterSource.read(sink, byteCount);
            if (result != -1) {
                updateCrc(sink, offset, result);
                return result;
            }
            this.section = (byte) 2;
        }
        if (this.section == 2) {
            consumeTrailer();
            this.section = (byte) 3;
            if (!this.source.exhausted()) {
                throw new IOException("gzip finished without exhausting source");
            }
            return -1L;
        }
        return -1L;
    }

    private final void consumeHeader() throws IOException {
        this.source.require(10L);
        RealBufferedSource this_$iv = this.source;
        int flags = this_$iv.bufferField.getByte(3L);
        boolean fhcrc = ((flags >> 1) & 1) == 1;
        if (fhcrc) {
            RealBufferedSource this_$iv2 = this.source;
            updateCrc(this_$iv2.bufferField, 0L, 10L);
        }
        short id1id2 = this.source.readShort();
        checkEqual("ID1ID2", 8075, id1id2);
        this.source.skip(8L);
        if (((flags >> 2) & 1) == 1) {
            this.source.require(2L);
            if (fhcrc) {
                RealBufferedSource this_$iv3 = this.source;
                updateCrc(this_$iv3.bufferField, 0L, 2L);
            }
            RealBufferedSource this_$iv4 = this.source;
            long xlen = this_$iv4.bufferField.readShortLe();
            this.source.require(xlen);
            if (fhcrc) {
                RealBufferedSource this_$iv5 = this.source;
                updateCrc(this_$iv5.bufferField, 0L, xlen);
            }
            this.source.skip(xlen);
        }
        if (((flags >> 3) & 1) == 1) {
            long index = this.source.indexOf((byte) 0);
            if (index == -1) {
                throw new EOFException();
            }
            if (fhcrc) {
                RealBufferedSource this_$iv6 = this.source;
                updateCrc(this_$iv6.bufferField, 0L, index + 1);
            }
            this.source.skip(index + 1);
        }
        if (((flags >> 4) & 1) == 1) {
            long index2 = this.source.indexOf((byte) 0);
            if (index2 == -1) {
                throw new EOFException();
            }
            if (fhcrc) {
                RealBufferedSource this_$iv7 = this.source;
                updateCrc(this_$iv7.bufferField, 0L, index2 + 1);
            }
            this.source.skip(index2 + 1);
        }
        if (fhcrc) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) this.crc.getValue());
            this.crc.reset();
        }
    }

    private final void consumeTrailer() throws IOException {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private final void updateCrc(Buffer buffer, long offset, long byteCount) {
        Segment s;
        long offset2 = offset;
        long byteCount2 = byteCount;
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        while (true) {
            s = segment;
            if (offset2 < s.limit - s.pos) {
                break;
            }
            offset2 -= s.limit - s.pos;
            segment = s.next;
            Intrinsics.checkNotNull(segment);
        }
        while (byteCount2 > 0) {
            int pos = (int) (s.pos + offset2);
            int a$iv = s.limit - pos;
            int toUpdate = (int) Math.min(a$iv, byteCount2);
            this.crc.update(s.data, pos, toUpdate);
            byteCount2 -= toUpdate;
            offset2 = 0;
            Segment segment2 = s.next;
            Intrinsics.checkNotNull(segment2);
            s = segment2;
        }
    }

    private final void checkEqual(String name, int expected, int actual) throws IOException {
        if (actual != expected) {
            Object[] objArr = {name, Integer.valueOf(actual), Integer.valueOf(expected)};
            String str = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            throw new IOException(str);
        }
    }
}
