package okio;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.web.handler.StaticHandler;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import me.ag2s.epublib.epub.PackageDocumentBase;
import okhttp3.internal.connection.RealConnection;
import okio.internal.BufferKt;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: Buffer.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018��2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u0090\u0001B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020��H\u0016J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020��H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016J\u0006\u0010\u0015\u001a\u00020\fJ\u0006\u0010\u0016\u001a\u00020��J$\u0010\u0017\u001a\u00020��2\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0007J\u0018\u0010\u0017\u001a\u00020��2\u0006\u0010\u0018\u001a\u00020��2\b\b\u0002\u0010\u001a\u001a\u00020\fJ \u0010\u0017\u001a\u00020��2\u0006\u0010\u0018\u001a\u00020��2\b\b\u0002\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020��H\u0016J\b\u0010!\u001a\u00020��H\u0016J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0096\u0002J\b\u0010&\u001a\u00020#H\u0016J\b\u0010'\u001a\u00020\u0012H\u0016J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\fH\u0087\u0002¢\u0006\u0002\b+J\u0015\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\fH\u0007¢\u0006\u0002\b-J\b\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001dH\u0002J\u000e\u00102\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u000e\u00103\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u000e\u00104\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001dJ\u0010\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)H\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\fH\u0016J \u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020)2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fH\u0016J\u0010\u00105\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001dH\u0016J\u0018\u00105\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\fH\u0016J\u0010\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001dH\u0016J\u0018\u0010:\u001a\u00020\f2\u0006\u0010;\u001a\u00020\u001d2\u0006\u00107\u001a\u00020\fH\u0016J\b\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020#H\u0016J\u0006\u0010?\u001a\u00020\u001dJ\b\u0010@\u001a\u00020\u0019H\u0016J\b\u0010A\u001a\u00020\u0001H\u0016J\u0018\u0010B\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001dH\u0016J(\u0010B\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u00109\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0010\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020GH\u0016J \u0010D\u001a\u00020/2\u0006\u0010E\u001a\u00020G2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010D\u001a\u00020\f2\u0006\u0010E\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010H\u001a\u00020\f2\u0006\u0010E\u001a\u00020IH\u0016J\u0012\u0010J\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020KH\u0007J\b\u0010M\u001a\u00020)H\u0016J\b\u0010N\u001a\u00020GH\u0016J\u0010\u0010N\u001a\u00020G2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010O\u001a\u00020\u001dH\u0016J\u0010\u0010O\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010P\u001a\u00020\fH\u0016J\u000e\u0010Q\u001a\u00020��2\u0006\u0010R\u001a\u00020=J\u0016\u0010Q\u001a\u00020��2\u0006\u0010R\u001a\u00020=2\u0006\u0010\u001b\u001a\u00020\fJ \u0010Q\u001a\u00020\u00122\u0006\u0010R\u001a\u00020=2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010S\u001a\u00020#H\u0002J\u0010\u0010T\u001a\u00020\u00122\u0006\u0010E\u001a\u00020GH\u0016J\u0018\u0010T\u001a\u00020\u00122\u0006\u0010E\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010U\u001a\u00020\fH\u0016J\b\u0010V\u001a\u00020/H\u0016J\b\u0010W\u001a\u00020/H\u0016J\b\u0010X\u001a\u00020\fH\u0016J\b\u0010Y\u001a\u00020\fH\u0016J\b\u0010Z\u001a\u00020[H\u0016J\b\u0010\\\u001a\u00020[H\u0016J\u0010\u0010]\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_H\u0016J\u0018\u0010]\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010^\u001a\u00020_H\u0016J\u0012\u0010`\u001a\u00020K2\b\b\u0002\u0010L\u001a\u00020KH\u0007J\b\u0010a\u001a\u00020\u001fH\u0016J\u0010\u0010a\u001a\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010b\u001a\u00020/H\u0016J\n\u0010c\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010d\u001a\u00020\u001fH\u0016J\u0010\u0010d\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\fH\u0016J\u0010\u0010f\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010g\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010h\u001a\u00020/2\u0006\u0010i\u001a\u00020jH\u0016J\u0006\u0010k\u001a\u00020\u001dJ\u0006\u0010l\u001a\u00020\u001dJ\u0006\u0010m\u001a\u00020\u001dJ\r\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0002\bnJ\u0010\u0010o\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0006\u0010p\u001a\u00020\u001dJ\u000e\u0010p\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020/J\b\u0010q\u001a\u00020rH\u0016J\b\u0010s\u001a\u00020\u001fH\u0016J\u0015\u0010t\u001a\u00020\n2\u0006\u0010u\u001a\u00020/H��¢\u0006\u0002\bvJ\u0010\u0010w\u001a\u00020/2\u0006\u0010x\u001a\u00020FH\u0016J\u0010\u0010w\u001a\u00020��2\u0006\u0010x\u001a\u00020GH\u0016J \u0010w\u001a\u00020��2\u0006\u0010x\u001a\u00020G2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010w\u001a\u00020\u00122\u0006\u0010x\u001a\u00020��2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010w\u001a\u00020��2\u0006\u0010y\u001a\u00020\u001dH\u0016J \u0010w\u001a\u00020��2\u0006\u0010y\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020/H\u0016J\u0018\u0010w\u001a\u00020��2\u0006\u0010x\u001a\u00020z2\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010{\u001a\u00020\f2\u0006\u0010x\u001a\u00020zH\u0016J\u0010\u0010|\u001a\u00020��2\u0006\u00106\u001a\u00020/H\u0016J\u0010\u0010}\u001a\u00020��2\u0006\u0010~\u001a\u00020\fH\u0016J\u0010\u0010\u007f\u001a\u00020��2\u0006\u0010~\u001a\u00020\fH\u0016J\u0012\u0010\u0080\u0001\u001a\u00020��2\u0007\u0010\u0081\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0082\u0001\u001a\u00020��2\u0007\u0010\u0081\u0001\u001a\u00020/H\u0016J\u0011\u0010\u0083\u0001\u001a\u00020��2\u0006\u0010~\u001a\u00020\fH\u0016J\u0011\u0010\u0084\u0001\u001a\u00020��2\u0006\u0010~\u001a\u00020\fH\u0016J\u0012\u0010\u0085\u0001\u001a\u00020��2\u0007\u0010\u0086\u0001\u001a\u00020/H\u0016J\u0012\u0010\u0087\u0001\u001a\u00020��2\u0007\u0010\u0086\u0001\u001a\u00020/H\u0016J\u001a\u0010\u0088\u0001\u001a\u00020��2\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0006\u0010^\u001a\u00020_H\u0016J,\u0010\u0088\u0001\u001a\u00020��2\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0007\u0010\u008a\u0001\u001a\u00020/2\u0007\u0010\u008b\u0001\u001a\u00020/2\u0006\u0010^\u001a\u00020_H\u0016J\u001b\u0010\u008c\u0001\u001a\u00020��2\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\fH\u0007J\u0012\u0010\u008d\u0001\u001a\u00020��2\u0007\u0010\u0089\u0001\u001a\u00020\u001fH\u0016J$\u0010\u008d\u0001\u001a\u00020��2\u0007\u0010\u0089\u0001\u001a\u00020\u001f2\u0007\u0010\u008a\u0001\u001a\u00020/2\u0007\u0010\u008b\u0001\u001a\u00020/H\u0016J\u0012\u0010\u008e\u0001\u001a\u00020��2\u0007\u0010\u008f\u0001\u001a\u00020/H\u0016R\u0014\u0010\u0006\u001a\u00020��8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\n8��@��X\u0081\u000e¢\u0006\u0002\n��R&\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8G@@X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0091\u0001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "()V", "buffer", "getBuffer", "()Lokio/Buffer;", "head", "Lokio/Segment;", "<set-?>", "", "size", "()J", "setSize$okio", "(J)V", "clear", "", "clone", "close", "completeSegmentByteCount", "copy", "copyTo", "out", "Ljava/io/OutputStream;", "offset", "byteCount", "digest", "Lokio/ByteString;", "algorithm", "", "emit", "emitCompleteSegments", "equals", "", "other", "", "exhausted", "flush", BeanUtil.PREFIX_GETTER_GET, "", "pos", "getByte", "index", "-deprecated_getByte", IdentityNamingStrategy.HASH_CODE_KEY, "", "hmac", "key", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "fromIndex", "toIndex", "bytes", "indexOfElement", "targetBytes", "inputStream", "Ljava/io/InputStream;", "isOpen", "md5", "outputStream", "peek", "rangeEquals", "bytesOffset", "read", "sink", "Ljava/nio/ByteBuffer;", "", "readAll", "Lokio/Sink;", "readAndWriteUnsafe", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readByte", "readByteArray", "readByteString", "readDecimalLong", "readFrom", "input", "forever", "readFully", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "readShort", "", "readShortLe", "readString", "charset", "Ljava/nio/charset/Charset;", "readUnsafe", "readUtf8", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "require", "select", "options", "Lokio/Options;", "sha1", "sha256", "sha512", "-deprecated_size", "skip", "snapshot", RtspHeaders.Values.TIMEOUT, "Lokio/Timeout;", "toString", "writableSegment", "minimumCapacity", "writableSegment$okio", "write", PackageDocumentBase.DCTags.source, "byteString", "Lokio/Source;", "writeAll", "writeByte", "writeDecimalLong", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "writeHexadecimalUnsignedLong", "writeInt", "i", "writeIntLe", "writeLong", "writeLongLe", "writeShort", OperatorName.CLOSE_AND_STROKE, "writeShortLe", "writeString", "string", "beginIndex", "endIndex", "writeTo", "writeUtf8", "writeUtf8CodePoint", "codePoint", "UnsafeCursor", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Buffer.class */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {

    @JvmField
    @Nullable
    public Segment head;
    private long size;

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out, long offset) throws IOException {
        return copyTo$default(this, out, offset, 0L, 4, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out) throws IOException {
        return copyTo$default(this, out, 0L, 0L, 6, (Object) null);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return buffer.copyTo(buffer2, j, j2);
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, Buffer buffer2, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 0;
        }
        return buffer.copyTo(buffer2, j);
    }

    @JvmOverloads
    @NotNull
    public final Buffer writeTo(@NotNull OutputStream out) throws IOException {
        return writeTo$default(this, out, 0L, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readUnsafe() {
        return readUnsafe$default(this, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readAndWriteUnsafe() {
        return readAndWriteUnsafe$default(this, null, 1, null);
    }

    @JvmName(name = "size")
    public final long size() {
        return this.size;
    }

    public final void setSize$okio(long j) {
        this.size = j;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    @NotNull
    public Buffer buffer() {
        return this;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    @NotNull
    public Buffer getBuffer() {
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.outputStream.1
            @Override // java.io.OutputStream
            public void write(int b) {
                Buffer.this.writeByte(b);
            }

            @Override // java.io.OutputStream
            public void write(@NotNull byte[] data, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(data, "data");
                Buffer.this.write(data, offset, byteCount);
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @NotNull
            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer emitCompleteSegments() {
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer emit() {
        return this;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSource
    public void require(long byteCount) throws EOFException {
        if (this.size < byteCount) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public boolean request(long byteCount) {
        return this.size >= byteCount;
    }

    @Override // okio.BufferedSource
    @NotNull
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.BufferedSource
    @NotNull
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.Buffer.inputStream.1
            @Override // java.io.InputStream
            public int read() throws EOFException {
                if (Buffer.this.size() > 0) {
                    byte $this$and$iv = Buffer.this.readByte();
                    return $this$and$iv & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(@NotNull byte[] sink, int offset, int byteCount) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                return Buffer.this.read(sink, offset, byteCount);
            }

            @Override // java.io.InputStream
            public int available() {
                long a$iv = Buffer.this.size();
                return (int) Math.min(a$iv, Integer.MAX_VALUE);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @NotNull
            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public static /* synthetic */ Buffer copyTo$default(Buffer buffer, OutputStream outputStream, long j, long j2, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = 0;
        }
        if ((i & 4) != 0) {
            j2 = buffer.size - j;
        }
        return buffer.copyTo(outputStream, j, j2);
    }

    @JvmOverloads
    @NotNull
    public final Buffer copyTo(@NotNull OutputStream out, long offset, long byteCount) throws IOException {
        Segment s;
        Intrinsics.checkNotNullParameter(out, "out");
        long offset2 = offset;
        long byteCount2 = byteCount;
        Util.checkOffsetAndCount(this.size, offset2, byteCount2);
        if (byteCount2 == 0) {
            return this;
        }
        Segment segment = this.head;
        while (true) {
            s = segment;
            long j = offset2;
            Intrinsics.checkNotNull(s);
            if (j < s.limit - s.pos) {
                break;
            }
            offset2 -= s.limit - s.pos;
            segment = s.next;
        }
        while (byteCount2 > 0) {
            Intrinsics.checkNotNull(s);
            int pos = (int) (r0.pos + offset2);
            int a$iv = s.limit - pos;
            int toCopy = (int) Math.min(a$iv, byteCount2);
            out.write(s.data, pos, toCopy);
            byteCount2 -= toCopy;
            offset2 = 0;
            s = s.next;
        }
        return this;
    }

    @NotNull
    public final Buffer copyTo(@NotNull Buffer out, long offset, long byteCount) {
        Segment s$iv;
        Intrinsics.checkNotNullParameter(out, "out");
        long offset$iv = offset;
        long byteCount$iv = byteCount;
        Util.checkOffsetAndCount(size(), offset$iv, byteCount$iv);
        if (byteCount$iv == 0) {
            return this;
        }
        out.setSize$okio(out.size() + byteCount$iv);
        Segment segment = this.head;
        while (true) {
            s$iv = segment;
            long j = offset$iv;
            Intrinsics.checkNotNull(s$iv);
            if (j < s$iv.limit - s$iv.pos) {
                break;
            }
            offset$iv -= s$iv.limit - s$iv.pos;
            segment = s$iv.next;
        }
        while (byteCount$iv > 0) {
            Segment segment2 = s$iv;
            Intrinsics.checkNotNull(segment2);
            Segment copy$iv = segment2.sharedCopy();
            copy$iv.pos += (int) offset$iv;
            copy$iv.limit = Math.min(copy$iv.pos + ((int) byteCount$iv), copy$iv.limit);
            if (out.head == null) {
                copy$iv.prev = copy$iv;
                copy$iv.next = copy$iv.prev;
                out.head = copy$iv.next;
            } else {
                Segment segment3 = out.head;
                Intrinsics.checkNotNull(segment3);
                Segment segment4 = segment3.prev;
                Intrinsics.checkNotNull(segment4);
                segment4.push(copy$iv);
            }
            byteCount$iv -= copy$iv.limit - copy$iv.pos;
            offset$iv = 0;
            s$iv = s$iv.next;
        }
        return this;
    }

    @NotNull
    public final Buffer copyTo(@NotNull Buffer out, long offset) {
        Intrinsics.checkNotNullParameter(out, "out");
        return copyTo(out, offset, this.size - offset);
    }

    public static /* synthetic */ Buffer writeTo$default(Buffer buffer, OutputStream outputStream, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = buffer.size;
        }
        return buffer.writeTo(outputStream, j);
    }

    @JvmOverloads
    @NotNull
    public final Buffer writeTo(@NotNull OutputStream out, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        long byteCount2 = byteCount;
        Util.checkOffsetAndCount(this.size, 0L, byteCount2);
        Segment s = this.head;
        while (byteCount2 > 0) {
            Segment segment = s;
            Intrinsics.checkNotNull(segment);
            int b$iv = segment.limit - s.pos;
            int toCopy = (int) Math.min(byteCount2, b$iv);
            out.write(s.data, s.pos, toCopy);
            s.pos += toCopy;
            this.size -= toCopy;
            byteCount2 -= toCopy;
            if (s.pos == s.limit) {
                Segment toRecycle = s;
                s = toRecycle.pop();
                this.head = s;
                SegmentPool.recycle(toRecycle);
            }
        }
        return this;
    }

    @NotNull
    public final Buffer readFrom(@NotNull InputStream input) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        readFrom(input, Long.MAX_VALUE, true);
        return this;
    }

    @NotNull
    public final Buffer readFrom(@NotNull InputStream input, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(input, "input");
        if (!(byteCount >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
        }
        readFrom(input, byteCount, false);
        return this;
    }

    private final void readFrom(InputStream input, long byteCount, boolean forever) throws IOException {
        long j = byteCount;
        while (true) {
            long byteCount2 = j;
            if (byteCount2 > 0 || forever) {
                Segment tail = writableSegment$okio(1);
                int b$iv = 8192 - tail.limit;
                int maxToCopy = (int) Math.min(byteCount2, b$iv);
                int bytesRead = input.read(tail.data, tail.limit, maxToCopy);
                if (bytesRead == -1) {
                    if (tail.pos == tail.limit) {
                        this.head = tail.pop();
                        SegmentPool.recycle(tail);
                    }
                    if (!forever) {
                        throw new EOFException();
                    }
                    return;
                }
                tail.limit += bytesRead;
                this.size += bytesRead;
                j = byteCount2 - bytesRead;
            } else {
                return;
            }
        }
    }

    public final long completeSegmentByteCount() {
        long result$iv = size();
        if (result$iv == 0) {
            return 0L;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment tail$iv = segment.prev;
        Intrinsics.checkNotNull(tail$iv);
        if (tail$iv.limit < 8192 && tail$iv.owner) {
            result$iv -= tail$iv.limit - tail$iv.pos;
        }
        return result$iv;
    }

    @Override // okio.BufferedSource
    public byte readByte() throws EOFException {
        if (size() == 0) {
            throw new EOFException();
        }
        Segment segment$iv = this.head;
        Intrinsics.checkNotNull(segment$iv);
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        byte[] data$iv = segment$iv.data;
        int pos$iv2 = pos$iv + 1;
        byte b$iv = data$iv[pos$iv];
        setSize$okio(size() - 1);
        if (pos$iv2 == limit$iv) {
            this.head = segment$iv.pop();
            SegmentPool.recycle(segment$iv);
        } else {
            segment$iv.pos = pos$iv2;
        }
        return b$iv;
    }

    @JvmName(name = "getByte")
    public final byte getByte(long pos) {
        Util.checkOffsetAndCount(size(), pos, 1L);
        Segment segment = this.head;
        if (segment == null) {
            Segment s$iv = (Segment) null;
            Intrinsics.checkNotNull(s$iv);
            return s$iv.data[(int) ((s$iv.pos + pos) - (-1))];
        }
        Segment s$iv$iv = segment;
        if (size() - pos < pos) {
            long size = size();
            while (true) {
                long offset$iv$iv = size;
                if (offset$iv$iv > pos) {
                    Segment segment2 = s$iv$iv.prev;
                    Intrinsics.checkNotNull(segment2);
                    s$iv$iv = segment2;
                    size = offset$iv$iv - (s$iv$iv.limit - s$iv$iv.pos);
                } else {
                    Segment s$iv2 = s$iv$iv;
                    Intrinsics.checkNotNull(s$iv2);
                    return s$iv2.data[(int) ((s$iv2.pos + pos) - offset$iv$iv)];
                }
            }
        } else {
            long j = 0;
            while (true) {
                long offset$iv$iv2 = j;
                long nextOffset$iv$iv = offset$iv$iv2 + (s$iv$iv.limit - s$iv$iv.pos);
                if (nextOffset$iv$iv <= pos) {
                    Segment segment3 = s$iv$iv.next;
                    Intrinsics.checkNotNull(segment3);
                    s$iv$iv = segment3;
                    j = nextOffset$iv$iv;
                } else {
                    Segment s$iv3 = s$iv$iv;
                    Intrinsics.checkNotNull(s$iv3);
                    return s$iv3.data[(int) ((s$iv3.pos + pos) - offset$iv$iv2)];
                }
            }
        }
    }

    @Override // okio.BufferedSource
    public short readShort() throws EOFException {
        if (size() < 2) {
            throw new EOFException();
        }
        Segment segment$iv = this.head;
        Intrinsics.checkNotNull(segment$iv);
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        if (limit$iv - pos$iv < 2) {
            byte $this$and$iv$iv = readByte();
            byte $this$and$iv$iv2 = readByte();
            int s$iv = (($this$and$iv$iv & 255) << 8) | ($this$and$iv$iv2 & 255);
            return (short) s$iv;
        }
        byte[] data$iv = segment$iv.data;
        int pos$iv2 = pos$iv + 1;
        byte $this$and$iv$iv3 = data$iv[pos$iv];
        int pos$iv3 = pos$iv2 + 1;
        byte $this$and$iv$iv4 = data$iv[pos$iv2];
        int s$iv2 = (($this$and$iv$iv3 & 255) << 8) | ($this$and$iv$iv4 & 255);
        setSize$okio(size() - 2);
        if (pos$iv3 == limit$iv) {
            this.head = segment$iv.pop();
            SegmentPool.recycle(segment$iv);
        } else {
            segment$iv.pos = pos$iv3;
        }
        return (short) s$iv2;
    }

    @Override // okio.BufferedSource
    public int readInt() throws EOFException {
        if (size() < 4) {
            throw new EOFException();
        }
        Segment segment$iv = this.head;
        Intrinsics.checkNotNull(segment$iv);
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        if (limit$iv - pos$iv < 4) {
            byte $this$and$iv$iv = readByte();
            byte $this$and$iv$iv2 = readByte();
            int i = (($this$and$iv$iv & 255) << 24) | (($this$and$iv$iv2 & 255) << 16);
            byte $this$and$iv$iv3 = readByte();
            int i2 = i | (($this$and$iv$iv3 & 255) << 8);
            byte $this$and$iv$iv4 = readByte();
            return i2 | ($this$and$iv$iv4 & 255);
        }
        byte[] data$iv = segment$iv.data;
        int pos$iv2 = pos$iv + 1;
        byte $this$and$iv$iv5 = data$iv[pos$iv];
        int pos$iv3 = pos$iv2 + 1;
        byte $this$and$iv$iv6 = data$iv[pos$iv2];
        int i3 = (($this$and$iv$iv5 & 255) << 24) | (($this$and$iv$iv6 & 255) << 16);
        int pos$iv4 = pos$iv3 + 1;
        byte $this$and$iv$iv7 = data$iv[pos$iv3];
        int i4 = i3 | (($this$and$iv$iv7 & 255) << 8);
        int pos$iv5 = pos$iv4 + 1;
        byte $this$and$iv$iv8 = data$iv[pos$iv4];
        int i$iv = i4 | ($this$and$iv$iv8 & 255);
        setSize$okio(size() - 4);
        if (pos$iv5 == limit$iv) {
            this.head = segment$iv.pop();
            SegmentPool.recycle(segment$iv);
        } else {
            segment$iv.pos = pos$iv5;
        }
        return i$iv;
    }

    @Override // okio.BufferedSource
    public long readLong() throws EOFException {
        if (size() < 8) {
            throw new EOFException();
        }
        Segment segment$iv = this.head;
        Intrinsics.checkNotNull(segment$iv);
        int pos$iv = segment$iv.pos;
        int limit$iv = segment$iv.limit;
        if (limit$iv - pos$iv < 8) {
            int $this$and$iv$iv = readInt();
            int $this$and$iv$iv2 = readInt();
            return (($this$and$iv$iv & 4294967295L) << 32) | ($this$and$iv$iv2 & 4294967295L);
        }
        byte[] data$iv = segment$iv.data;
        int pos$iv2 = pos$iv + 1;
        byte $this$and$iv$iv3 = data$iv[pos$iv];
        int pos$iv3 = pos$iv2 + 1;
        byte $this$and$iv$iv4 = data$iv[pos$iv2];
        long j = (($this$and$iv$iv3 & 255) << 56) | (($this$and$iv$iv4 & 255) << 48);
        int pos$iv4 = pos$iv3 + 1;
        byte $this$and$iv$iv5 = data$iv[pos$iv3];
        long j2 = j | (($this$and$iv$iv5 & 255) << 40);
        int pos$iv5 = pos$iv4 + 1;
        byte $this$and$iv$iv6 = data$iv[pos$iv4];
        long j3 = j2 | (($this$and$iv$iv6 & 255) << 32);
        int pos$iv6 = pos$iv5 + 1;
        byte $this$and$iv$iv7 = data$iv[pos$iv5];
        long j4 = j3 | (($this$and$iv$iv7 & 255) << 24);
        int pos$iv7 = pos$iv6 + 1;
        byte $this$and$iv$iv8 = data$iv[pos$iv6];
        long j5 = j4 | (($this$and$iv$iv8 & 255) << 16);
        int pos$iv8 = pos$iv7 + 1;
        byte $this$and$iv$iv9 = data$iv[pos$iv7];
        long j6 = j5 | (($this$and$iv$iv9 & 255) << 8);
        int pos$iv9 = pos$iv8 + 1;
        byte $this$and$iv$iv10 = data$iv[pos$iv8];
        long v$iv = j6 | ($this$and$iv$iv10 & 255);
        setSize$okio(size() - 8);
        if (pos$iv9 == limit$iv) {
            this.head = segment$iv.pop();
            SegmentPool.recycle(segment$iv);
        } else {
            segment$iv.pos = pos$iv9;
        }
        return v$iv;
    }

    @Override // okio.BufferedSource
    public short readShortLe() throws EOFException {
        return Util.reverseBytes(readShort());
    }

    @Override // okio.BufferedSource
    public int readIntLe() throws EOFException {
        return Util.reverseBytes(readInt());
    }

    @Override // okio.BufferedSource
    public long readLongLe() throws EOFException {
        return Util.reverseBytes(readLong());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0150 A[EDGE_INSN: B:53:0x0150->B:45:0x0150 BREAK  A[LOOP:0: B:7:0x0028->B:55:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readDecimalLong() throws java.io.EOFException {
        /*
            Method dump skipped, instructions count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0152 A[EDGE_INSN: B:47:0x0152->B:43:0x0152 BREAK  A[LOOP:0: B:7:0x0020->B:49:?], SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long readHexadecimalUnsignedLong() throws java.io.EOFException {
        /*
            Method dump skipped, instructions count: 352
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    @NotNull
    public ByteString readByteString() {
        return readByteString(size());
    }

    @Override // okio.BufferedSource
    @NotNull
    public ByteString readByteString(long byteCount) throws EOFException {
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (size() < byteCount) {
            throw new EOFException();
        }
        if (byteCount < 4096) {
            return new ByteString(readByteArray(byteCount));
        }
        ByteString byteStringSnapshot = snapshot((int) byteCount);
        skip(byteCount);
        return byteStringSnapshot;
    }

    @Override // okio.BufferedSource
    public int select(@NotNull Options options) throws EOFException {
        Intrinsics.checkNotNullParameter(options, "options");
        int index$iv = BufferKt.selectPrefix$default(this, options, false, 2, null);
        if (index$iv == -1) {
            return -1;
        }
        int selectedSize$iv = options.getByteStrings$okio()[index$iv].size();
        skip(selectedSize$iv);
        return index$iv;
    }

    @Override // okio.BufferedSource
    public void readFully(@NotNull Buffer sink, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (size() < byteCount) {
            sink.write(this, size());
            throw new EOFException();
        }
        sink.write(this, byteCount);
    }

    @Override // okio.BufferedSource
    public long readAll(@NotNull Sink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long byteCount$iv = size();
        if (byteCount$iv > 0) {
            sink.write(this, byteCount$iv);
        }
        return byteCount$iv;
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8(long byteCount) throws EOFException {
        return readString(byteCount, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readString(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return readString(this.size, charset);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readString(long byteCount, @NotNull Charset charset) throws EOFException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (this.size < byteCount) {
            throw new EOFException();
        }
        if (byteCount == 0) {
            return "";
        }
        Segment s = this.head;
        Intrinsics.checkNotNull(s);
        if (s.pos + byteCount > s.limit) {
            return new String(readByteArray(byteCount), charset);
        }
        String result = new String(s.data, s.pos, (int) byteCount, charset);
        s.pos += (int) byteCount;
        this.size -= byteCount;
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return result;
    }

    @Override // okio.BufferedSource
    @Nullable
    public String readUtf8Line() throws EOFException {
        long newline$iv = indexOf((byte) 10);
        if (newline$iv != -1) {
            return BufferKt.readUtf8Line(this, newline$iv);
        }
        if (size() != 0) {
            return readUtf8(size());
        }
        return null;
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    @NotNull
    public String readUtf8LineStrict(long limit) throws EOFException {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + limit).toString());
        }
        long scanLength$iv = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1;
        long newline$iv = indexOf((byte) 10, 0L, scanLength$iv);
        if (newline$iv != -1) {
            return BufferKt.readUtf8Line(this, newline$iv);
        }
        if (scanLength$iv < size() && getByte(scanLength$iv - 1) == ((byte) 13) && getByte(scanLength$iv) == ((byte) 10)) {
            return BufferKt.readUtf8Line(this, scanLength$iv);
        }
        Buffer data$iv = new Buffer();
        long b$iv$iv = size();
        copyTo(data$iv, 0L, Math.min(32, b$iv$iv));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), limit) + " content=" + data$iv.readByteString().hex() + (char) 8230);
    }

    @Override // okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        int codePoint$iv;
        int byteCount$iv;
        int min$iv;
        if (size() == 0) {
            throw new EOFException();
        }
        byte b0$iv = getByte(0L);
        if ((b0$iv & 128) == 0) {
            codePoint$iv = b0$iv & 127;
            byteCount$iv = 1;
            min$iv = 0;
        } else if ((b0$iv & 224) == 192) {
            codePoint$iv = b0$iv & 31;
            byteCount$iv = 2;
            min$iv = 128;
        } else if ((b0$iv & 240) == 224) {
            codePoint$iv = b0$iv & 15;
            byteCount$iv = 3;
            min$iv = 2048;
        } else {
            if ((b0$iv & 248) != 240) {
                skip(1L);
                return 65533;
            }
            codePoint$iv = b0$iv & 7;
            byteCount$iv = 4;
            min$iv = 65536;
        }
        if (size() < byteCount$iv) {
            throw new EOFException("size < " + byteCount$iv + ": " + size() + " (to read code point prefixed 0x" + Util.toHexString(b0$iv) + ')');
        }
        int i = byteCount$iv;
        for (int i$iv = 1; i$iv < i; i$iv++) {
            byte b$iv = getByte(i$iv);
            if ((b$iv & 192) != 128) {
                skip(i$iv);
                return 65533;
            }
            codePoint$iv = (codePoint$iv << 6) | (b$iv & 63);
        }
        skip(byteCount$iv);
        if (codePoint$iv > 1114111) {
            return 65533;
        }
        int i2 = codePoint$iv;
        if ((55296 <= i2 && 57343 >= i2) || codePoint$iv < min$iv) {
            return 65533;
        }
        return codePoint$iv;
    }

    @Override // okio.BufferedSource
    @NotNull
    public byte[] readByteArray() {
        return readByteArray(size());
    }

    @Override // okio.BufferedSource
    @NotNull
    public byte[] readByteArray(long byteCount) throws EOFException {
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (size() < byteCount) {
            throw new EOFException();
        }
        byte[] result$iv = new byte[(int) byteCount];
        readFully(result$iv);
        return result$iv;
    }

    @Override // okio.BufferedSource
    public int read(@NotNull byte[] sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return read(sink, 0, sink.length);
    }

    @Override // okio.BufferedSource
    public void readFully(@NotNull byte[] sink) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = 0;
        while (true) {
            int offset$iv = i;
            if (offset$iv >= sink.length) {
                return;
            }
            int read$iv = read(sink, offset$iv, sink.length - offset$iv);
            if (read$iv == -1) {
                throw new EOFException();
            }
            i = offset$iv + read$iv;
        }
    }

    @Override // okio.BufferedSource
    public int read(@NotNull byte[] sink, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Util.checkOffsetAndCount(sink.length, offset, byteCount);
        Segment s$iv = this.head;
        if (s$iv == null) {
            return -1;
        }
        int toCopy$iv = Math.min(byteCount, s$iv.limit - s$iv.pos);
        ArraysKt.copyInto(s$iv.data, sink, offset, s$iv.pos, s$iv.pos + toCopy$iv);
        s$iv.pos += toCopy$iv;
        setSize$okio(size() - toCopy$iv);
        if (s$iv.pos == s$iv.limit) {
            this.head = s$iv.pop();
            SegmentPool.recycle(s$iv);
        }
        return toCopy$iv;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(@NotNull ByteBuffer sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Segment s = this.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(sink.remaining(), s.limit - s.pos);
        sink.put(s.data, s.pos, toCopy);
        s.pos += toCopy;
        this.size -= toCopy;
        if (s.pos == s.limit) {
            this.head = s.pop();
            SegmentPool.recycle(s);
        }
        return toCopy;
    }

    public final void clear() throws EOFException {
        skip(size());
    }

    @Override // okio.BufferedSource
    public void skip(long byteCount) throws EOFException {
        long byteCount$iv = byteCount;
        while (byteCount$iv > 0) {
            Segment head$iv = this.head;
            if (head$iv == null) {
                throw new EOFException();
            }
            int b$iv$iv = head$iv.limit - head$iv.pos;
            int toSkip$iv = (int) Math.min(byteCount$iv, b$iv$iv);
            setSize$okio(size() - toSkip$iv);
            byteCount$iv -= toSkip$iv;
            head$iv.pos += toSkip$iv;
            if (head$iv.pos == head$iv.limit) {
                this.head = head$iv.pop();
                SegmentPool.recycle(head$iv);
            }
        }
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        int byteCount$iv = byteString.size();
        byteString.write$okio(this, 0, byteCount$iv);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull ByteString byteString, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(this, offset, byteCount);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeUtf8(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return writeUtf8(string, 0, string.length());
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeUtf8(@NotNull String string, int beginIndex, int endIndex) {
        int c$iv;
        Intrinsics.checkNotNullParameter(string, "string");
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + beginIndex).toString());
        }
        if (!(endIndex >= beginIndex)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + endIndex + " < " + beginIndex).toString());
        }
        if (!(endIndex <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + endIndex + " > " + string.length()).toString());
        }
        int i$iv = beginIndex;
        while (i$iv < endIndex) {
            int c$iv2 = string.charAt(i$iv);
            if (c$iv2 < 128) {
                Segment tail$iv = writableSegment$okio(1);
                byte[] data$iv = tail$iv.data;
                int segmentOffset$iv = tail$iv.limit - i$iv;
                int runLimit$iv = Math.min(endIndex, 8192 - segmentOffset$iv);
                int i = i$iv;
                i$iv++;
                data$iv[segmentOffset$iv + i] = (byte) c$iv2;
                while (i$iv < runLimit$iv && (c$iv = string.charAt(i$iv)) < 128) {
                    int i2 = i$iv;
                    i$iv++;
                    data$iv[segmentOffset$iv + i2] = (byte) c$iv;
                }
                int runSize$iv = (i$iv + segmentOffset$iv) - tail$iv.limit;
                tail$iv.limit += runSize$iv;
                setSize$okio(size() + runSize$iv);
            } else if (c$iv2 < 2048) {
                Segment tail$iv2 = writableSegment$okio(2);
                tail$iv2.data[tail$iv2.limit] = (byte) ((c$iv2 >> 6) | 192);
                tail$iv2.data[tail$iv2.limit + 1] = (byte) ((c$iv2 & 63) | 128);
                tail$iv2.limit += 2;
                setSize$okio(size() + 2);
                i$iv++;
            } else if (c$iv2 < 55296 || c$iv2 > 57343) {
                Segment tail$iv3 = writableSegment$okio(3);
                tail$iv3.data[tail$iv3.limit] = (byte) ((c$iv2 >> 12) | 224);
                tail$iv3.data[tail$iv3.limit + 1] = (byte) (((c$iv2 >> 6) & 63) | 128);
                tail$iv3.data[tail$iv3.limit + 2] = (byte) ((c$iv2 & 63) | 128);
                tail$iv3.limit += 3;
                setSize$okio(size() + 3);
                i$iv++;
            } else {
                int low$iv = i$iv + 1 < endIndex ? string.charAt(i$iv + 1) : 0;
                if (c$iv2 > 56319 || 56320 > low$iv || 57343 < low$iv) {
                    writeByte(63);
                    i$iv++;
                } else {
                    int codePoint$iv = 65536 + (((c$iv2 & 1023) << 10) | (low$iv & 1023));
                    Segment tail$iv4 = writableSegment$okio(4);
                    tail$iv4.data[tail$iv4.limit] = (byte) ((codePoint$iv >> 18) | 240);
                    tail$iv4.data[tail$iv4.limit + 1] = (byte) (((codePoint$iv >> 12) & 63) | 128);
                    tail$iv4.data[tail$iv4.limit + 2] = (byte) (((codePoint$iv >> 6) & 63) | 128);
                    tail$iv4.data[tail$iv4.limit + 3] = (byte) ((codePoint$iv & 63) | 128);
                    tail$iv4.limit += 4;
                    setSize$okio(size() + 4);
                    i$iv += 2;
                }
            }
        }
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeUtf8CodePoint(int codePoint) {
        if (codePoint < 128) {
            writeByte(codePoint);
        } else if (codePoint < 2048) {
            Segment tail$iv = writableSegment$okio(2);
            tail$iv.data[tail$iv.limit] = (byte) ((codePoint >> 6) | 192);
            tail$iv.data[tail$iv.limit + 1] = (byte) ((codePoint & 63) | 128);
            tail$iv.limit += 2;
            setSize$okio(size() + 2);
        } else if (55296 <= codePoint && 57343 >= codePoint) {
            writeByte(63);
        } else if (codePoint < 65536) {
            Segment tail$iv2 = writableSegment$okio(3);
            tail$iv2.data[tail$iv2.limit] = (byte) ((codePoint >> 12) | 224);
            tail$iv2.data[tail$iv2.limit + 1] = (byte) (((codePoint >> 6) & 63) | 128);
            tail$iv2.data[tail$iv2.limit + 2] = (byte) ((codePoint & 63) | 128);
            tail$iv2.limit += 3;
            setSize$okio(size() + 3);
        } else if (codePoint <= 1114111) {
            Segment tail$iv3 = writableSegment$okio(4);
            tail$iv3.data[tail$iv3.limit] = (byte) ((codePoint >> 18) | 240);
            tail$iv3.data[tail$iv3.limit + 1] = (byte) (((codePoint >> 12) & 63) | 128);
            tail$iv3.data[tail$iv3.limit + 2] = (byte) (((codePoint >> 6) & 63) | 128);
            tail$iv3.data[tail$iv3.limit + 3] = (byte) ((codePoint & 63) | 128);
            tail$iv3.limit += 4;
            setSize$okio(size() + 4);
        } else {
            throw new IllegalArgumentException("Unexpected code point: 0x" + Util.toHexString(codePoint));
        }
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeString(@NotNull String string, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return writeString(string, 0, string.length(), charset);
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeString(@NotNull String string, int beginIndex, int endIndex, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(("beginIndex < 0: " + beginIndex).toString());
        }
        if (!(endIndex >= beginIndex)) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + endIndex + " < " + beginIndex).toString());
        }
        if (!(endIndex <= string.length())) {
            throw new IllegalArgumentException(("endIndex > string.length: " + endIndex + " > " + string.length()).toString());
        }
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            return writeUtf8(string, beginIndex, endIndex);
        }
        String strSubstring = string.substring(beginIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] data = strSubstring.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(data, "(this as java.lang.String).getBytes(charset)");
        return write(data, 0, data.length);
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return write(source, 0, source.length);
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull byte[] source, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(source, "source");
        int offset$iv = offset;
        Util.checkOffsetAndCount(source.length, offset$iv, byteCount);
        int limit$iv = offset$iv + byteCount;
        while (offset$iv < limit$iv) {
            Segment tail$iv = writableSegment$okio(1);
            int toCopy$iv = Math.min(limit$iv - offset$iv, 8192 - tail$iv.limit);
            ArraysKt.copyInto(source, tail$iv.data, tail$iv.limit, offset$iv, offset$iv + toCopy$iv);
            offset$iv += toCopy$iv;
            tail$iv.limit += toCopy$iv;
        }
        setSize$okio(size() + byteCount);
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(@NotNull ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        int byteCount = source.remaining();
        int remaining = byteCount;
        while (remaining > 0) {
            Segment tail = writableSegment$okio(1);
            int toCopy = Math.min(remaining, 8192 - tail.limit);
            source.get(tail.data, tail.limit, toCopy);
            remaining -= toCopy;
            tail.limit += toCopy;
        }
        this.size += byteCount;
        return byteCount;
    }

    @Override // okio.BufferedSink
    public long writeAll(@NotNull Source source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long totalBytesRead$iv = j;
            long readCount$iv = source.read(this, 8192);
            if (readCount$iv != -1) {
                j = totalBytesRead$iv + readCount$iv;
            } else {
                return totalBytesRead$iv;
            }
        }
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer write(@NotNull Source source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j = byteCount;
        while (true) {
            long byteCount$iv = j;
            if (byteCount$iv > 0) {
                long read$iv = source.read(this, byteCount$iv);
                if (read$iv == -1) {
                    throw new EOFException();
                }
                j = byteCount$iv - read$iv;
            } else {
                return this;
            }
        }
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeByte(int b) {
        Segment tail$iv = writableSegment$okio(1);
        byte[] bArr = tail$iv.data;
        int i = tail$iv.limit;
        tail$iv.limit = i + 1;
        bArr[i] = (byte) b;
        setSize$okio(size() + 1);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeShort(int s) {
        Segment tail$iv = writableSegment$okio(2);
        byte[] data$iv = tail$iv.data;
        int limit$iv = tail$iv.limit;
        int limit$iv2 = limit$iv + 1;
        data$iv[limit$iv] = (byte) ((s >>> 8) & 255);
        data$iv[limit$iv2] = (byte) (s & 255);
        tail$iv.limit = limit$iv2 + 1;
        setSize$okio(size() + 2);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeShortLe(int s) {
        return writeShort((int) Util.reverseBytes((short) s));
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeInt(int i) {
        Segment tail$iv = writableSegment$okio(4);
        byte[] data$iv = tail$iv.data;
        int limit$iv = tail$iv.limit;
        int limit$iv2 = limit$iv + 1;
        data$iv[limit$iv] = (byte) ((i >>> 24) & 255);
        int limit$iv3 = limit$iv2 + 1;
        data$iv[limit$iv2] = (byte) ((i >>> 16) & 255);
        int limit$iv4 = limit$iv3 + 1;
        data$iv[limit$iv3] = (byte) ((i >>> 8) & 255);
        data$iv[limit$iv4] = (byte) (i & 255);
        tail$iv.limit = limit$iv4 + 1;
        setSize$okio(size() + 4);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytes(i));
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeLong(long v) {
        Segment tail$iv = writableSegment$okio(8);
        byte[] data$iv = tail$iv.data;
        int limit$iv = tail$iv.limit;
        int limit$iv2 = limit$iv + 1;
        data$iv[limit$iv] = (byte) ((v >>> 56) & 255);
        int limit$iv3 = limit$iv2 + 1;
        data$iv[limit$iv2] = (byte) ((v >>> 48) & 255);
        int limit$iv4 = limit$iv3 + 1;
        data$iv[limit$iv3] = (byte) ((v >>> 40) & 255);
        int limit$iv5 = limit$iv4 + 1;
        data$iv[limit$iv4] = (byte) ((v >>> 32) & 255);
        int limit$iv6 = limit$iv5 + 1;
        data$iv[limit$iv5] = (byte) ((v >>> 24) & 255);
        int limit$iv7 = limit$iv6 + 1;
        data$iv[limit$iv6] = (byte) ((v >>> 16) & 255);
        int limit$iv8 = limit$iv7 + 1;
        data$iv[limit$iv7] = (byte) ((v >>> 8) & 255);
        data$iv[limit$iv8] = (byte) (v & 255);
        tail$iv.limit = limit$iv8 + 1;
        setSize$okio(size() + 8);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeLongLe(long v) {
        return writeLong(Util.reverseBytes(v));
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeDecimalLong(long v) {
        int i;
        long v$iv = v;
        if (v$iv == 0) {
            return writeByte(48);
        }
        boolean negative$iv = false;
        if (v$iv < 0) {
            v$iv = -v$iv;
            if (v$iv < 0) {
                return writeUtf8("-9223372036854775808");
            }
            negative$iv = true;
        }
        if (v$iv < 100000000) {
            if (v$iv < 10000) {
                if (v$iv < 100) {
                    i = v$iv < 10 ? 1 : 2;
                } else {
                    i = v$iv < 1000 ? 3 : 4;
                }
            } else if (v$iv < StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS) {
                i = v$iv < 100000 ? 5 : 6;
            } else {
                i = v$iv < 10000000 ? 7 : 8;
            }
        } else if (v$iv < 1000000000000L) {
            if (v$iv < RealConnection.IDLE_CONNECTION_HEALTHY_NS) {
                i = v$iv < 1000000000 ? 9 : 10;
            } else {
                i = v$iv < 100000000000L ? 11 : 12;
            }
        } else if (v$iv < 1000000000000000L) {
            if (v$iv < 10000000000000L) {
                i = 13;
            } else {
                i = v$iv < 100000000000000L ? 14 : 15;
            }
        } else if (v$iv < 100000000000000000L) {
            i = v$iv < 10000000000000000L ? 16 : 17;
        } else {
            i = v$iv < 1000000000000000000L ? 18 : 19;
        }
        int width$iv = i;
        if (negative$iv) {
            width$iv++;
        }
        Segment tail$iv = writableSegment$okio(width$iv);
        byte[] data$iv = tail$iv.data;
        int pos$iv = tail$iv.limit + width$iv;
        while (v$iv != 0) {
            int digit$iv = (int) (v$iv % 10);
            pos$iv--;
            data$iv[pos$iv] = BufferKt.getHEX_DIGIT_BYTES()[digit$iv];
            v$iv /= 10;
        }
        if (negative$iv) {
            data$iv[pos$iv - 1] = (byte) 45;
        }
        tail$iv.limit += width$iv;
        setSize$okio(size() + width$iv);
        return this;
    }

    @Override // okio.BufferedSink
    @NotNull
    public Buffer writeHexadecimalUnsignedLong(long v) {
        long v$iv = v;
        if (v$iv == 0) {
            return writeByte(48);
        }
        long x$iv = v$iv | (v$iv >>> 1);
        long x$iv2 = x$iv | (x$iv >>> 2);
        long x$iv3 = x$iv2 | (x$iv2 >>> 4);
        long x$iv4 = x$iv3 | (x$iv3 >>> 8);
        long x$iv5 = x$iv4 | (x$iv4 >>> 16);
        long x$iv6 = x$iv5 | (x$iv5 >>> 32);
        long x$iv7 = x$iv6 - ((x$iv6 >>> 1) & 6148914691236517205L);
        long x$iv8 = ((x$iv7 >>> 2) & 3689348814741910323L) + (x$iv7 & 3689348814741910323L);
        long x$iv9 = ((x$iv8 >>> 4) + x$iv8) & 1085102592571150095L;
        long x$iv10 = x$iv9 + (x$iv9 >>> 8);
        long x$iv11 = x$iv10 + (x$iv10 >>> 16);
        int width$iv = (int) ((((x$iv11 & 63) + ((x$iv11 >>> 32) & 63)) + 3) / 4);
        Segment tail$iv = writableSegment$okio(width$iv);
        byte[] data$iv = tail$iv.data;
        int start$iv = tail$iv.limit;
        for (int pos$iv = (tail$iv.limit + width$iv) - 1; pos$iv >= start$iv; pos$iv--) {
            data$iv[pos$iv] = BufferKt.getHEX_DIGIT_BYTES()[(int) (v$iv & 15)];
            v$iv >>>= 4;
        }
        tail$iv.limit += width$iv;
        setSize$okio(size() + width$iv);
        return this;
    }

    @NotNull
    public final Segment writableSegment$okio(int minimumCapacity) {
        if (!(minimumCapacity >= 1 && minimumCapacity <= 8192)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        if (this.head == null) {
            Segment result$iv = SegmentPool.take();
            this.head = result$iv;
            result$iv.prev = result$iv;
            result$iv.next = result$iv;
            return result$iv;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment tail$iv = segment.prev;
        Intrinsics.checkNotNull(tail$iv);
        if (tail$iv.limit + minimumCapacity > 8192 || !tail$iv.owner) {
            tail$iv = tail$iv.push(SegmentPool.take());
        }
        return tail$iv;
    }

    @Override // okio.Sink
    public void write(@NotNull Buffer source, long byteCount) {
        Segment segment;
        Intrinsics.checkNotNullParameter(source, "source");
        long byteCount$iv = byteCount;
        if (!(source != this)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        Util.checkOffsetAndCount(source.size(), 0L, byteCount$iv);
        while (byteCount$iv > 0) {
            long j = byteCount$iv;
            Segment segment2 = source.head;
            Intrinsics.checkNotNull(segment2);
            int i = segment2.limit;
            Intrinsics.checkNotNull(source.head);
            if (j < i - r2.pos) {
                if (this.head != null) {
                    Segment segment3 = this.head;
                    Intrinsics.checkNotNull(segment3);
                    segment = segment3.prev;
                } else {
                    segment = null;
                }
                Segment tail$iv = segment;
                if (tail$iv != null && tail$iv.owner) {
                    if ((byteCount$iv + tail$iv.limit) - (tail$iv.shared ? 0 : tail$iv.pos) <= 8192) {
                        Segment segment4 = source.head;
                        Intrinsics.checkNotNull(segment4);
                        segment4.writeTo(tail$iv, (int) byteCount$iv);
                        source.setSize$okio(source.size() - byteCount$iv);
                        setSize$okio(size() + byteCount$iv);
                        return;
                    }
                }
                Segment segment5 = source.head;
                Intrinsics.checkNotNull(segment5);
                source.head = segment5.split((int) byteCount$iv);
            }
            Segment segmentToMove$iv = source.head;
            Intrinsics.checkNotNull(segmentToMove$iv);
            long movedByteCount$iv = segmentToMove$iv.limit - segmentToMove$iv.pos;
            source.head = segmentToMove$iv.pop();
            if (this.head == null) {
                this.head = segmentToMove$iv;
                segmentToMove$iv.prev = segmentToMove$iv;
                segmentToMove$iv.next = segmentToMove$iv.prev;
            } else {
                Segment segment6 = this.head;
                Intrinsics.checkNotNull(segment6);
                Segment tail$iv2 = segment6.prev;
                Intrinsics.checkNotNull(tail$iv2);
                tail$iv2.push(segmentToMove$iv).compact();
            }
            source.setSize$okio(source.size() - movedByteCount$iv);
            setSize$okio(size() + movedByteCount$iv);
            byteCount$iv -= movedByteCount$iv;
        }
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long byteCount$iv = byteCount;
        if (!(byteCount$iv >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + byteCount$iv).toString());
        }
        if (size() == 0) {
            return -1L;
        }
        if (byteCount$iv > size()) {
            byteCount$iv = size();
        }
        sink.write(this, byteCount$iv);
        return byteCount$iv;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex) {
        return indexOf(b, fromIndex, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b, long fromIndex, long toIndex) {
        long offset$iv$iv;
        long offset$iv$iv2;
        long fromIndex$iv = fromIndex;
        long toIndex$iv = toIndex;
        if (!(0 <= fromIndex$iv && toIndex$iv >= fromIndex$iv)) {
            throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + fromIndex$iv + " toIndex=" + toIndex$iv).toString());
        }
        if (toIndex$iv > size()) {
            toIndex$iv = size();
        }
        if (fromIndex$iv == toIndex$iv) {
            return -1L;
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        Segment s$iv$iv = segment;
        if (size() - fromIndex$iv < fromIndex$iv) {
            long size = size();
            while (true) {
                offset$iv$iv2 = size;
                if (offset$iv$iv2 <= fromIndex$iv) {
                    break;
                }
                Segment segment2 = s$iv$iv.prev;
                Intrinsics.checkNotNull(segment2);
                s$iv$iv = segment2;
                size = offset$iv$iv2 - (s$iv$iv.limit - s$iv$iv.pos);
            }
            Segment s$iv = s$iv$iv;
            if (s$iv == null) {
                return -1L;
            }
            Segment s$iv2 = s$iv;
            long offset$iv = offset$iv$iv2;
            while (offset$iv < toIndex$iv) {
                byte[] data$iv = s$iv2.data;
                int limit$iv = (int) Math.min(s$iv2.limit, (s$iv2.pos + toIndex$iv) - offset$iv);
                for (int pos$iv = (int) ((s$iv2.pos + fromIndex$iv) - offset$iv); pos$iv < limit$iv; pos$iv++) {
                    if (data$iv[pos$iv] == b) {
                        return (pos$iv - s$iv2.pos) + offset$iv;
                    }
                }
                offset$iv += s$iv2.limit - s$iv2.pos;
                fromIndex$iv = offset$iv;
                Segment segment3 = s$iv2.next;
                Intrinsics.checkNotNull(segment3);
                s$iv2 = segment3;
            }
            return -1L;
        }
        long j = 0;
        while (true) {
            offset$iv$iv = j;
            long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
            if (nextOffset$iv$iv > fromIndex$iv) {
                break;
            }
            Segment segment4 = s$iv$iv.next;
            Intrinsics.checkNotNull(segment4);
            s$iv$iv = segment4;
            j = nextOffset$iv$iv;
        }
        Segment s$iv3 = s$iv$iv;
        if (s$iv3 == null) {
            return -1L;
        }
        Segment s$iv4 = s$iv3;
        long offset$iv2 = offset$iv$iv;
        while (offset$iv2 < toIndex$iv) {
            byte[] data$iv2 = s$iv4.data;
            int limit$iv2 = (int) Math.min(s$iv4.limit, (s$iv4.pos + toIndex$iv) - offset$iv2);
            for (int pos$iv2 = (int) ((s$iv4.pos + fromIndex$iv) - offset$iv2); pos$iv2 < limit$iv2; pos$iv2++) {
                if (data$iv2[pos$iv2] == b) {
                    return (pos$iv2 - s$iv4.pos) + offset$iv2;
                }
            }
            offset$iv2 += s$iv4.limit - s$iv4.pos;
            fromIndex$iv = offset$iv2;
            Segment segment5 = s$iv4.next;
            Intrinsics.checkNotNull(segment5);
            s$iv4 = segment5;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public long indexOf(@NotNull ByteString bytes) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return indexOf(bytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOf(@NotNull ByteString bytes, long fromIndex) throws IOException {
        long offset$iv$iv;
        long offset$iv$iv2;
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long fromIndex$iv = fromIndex;
        if (!(bytes.size() > 0)) {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
        if (!(fromIndex$iv >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + fromIndex$iv).toString());
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        Segment s$iv$iv = segment;
        if (size() - fromIndex$iv < fromIndex$iv) {
            long size = size();
            while (true) {
                offset$iv$iv2 = size;
                if (offset$iv$iv2 <= fromIndex$iv) {
                    break;
                }
                Segment segment2 = s$iv$iv.prev;
                Intrinsics.checkNotNull(segment2);
                s$iv$iv = segment2;
                size = offset$iv$iv2 - (s$iv$iv.limit - s$iv$iv.pos);
            }
            Segment s$iv = s$iv$iv;
            if (s$iv == null) {
                return -1L;
            }
            Segment s$iv2 = s$iv;
            long offset$iv = offset$iv$iv2;
            byte[] targetByteArray$iv = bytes.internalArray$okio();
            byte b0$iv = targetByteArray$iv[0];
            int bytesSize$iv = bytes.size();
            long resultLimit$iv = (size() - bytesSize$iv) + 1;
            while (offset$iv < resultLimit$iv) {
                byte[] data$iv = s$iv2.data;
                int a$iv$iv = s$iv2.limit;
                long b$iv$iv = (s$iv2.pos + resultLimit$iv) - offset$iv;
                int segmentLimit$iv = (int) Math.min(a$iv$iv, b$iv$iv);
                for (int pos$iv = (int) ((s$iv2.pos + fromIndex$iv) - offset$iv); pos$iv < segmentLimit$iv; pos$iv++) {
                    if (data$iv[pos$iv] == b0$iv && BufferKt.rangeEquals(s$iv2, pos$iv + 1, targetByteArray$iv, 1, bytesSize$iv)) {
                        return (pos$iv - s$iv2.pos) + offset$iv;
                    }
                }
                offset$iv += s$iv2.limit - s$iv2.pos;
                fromIndex$iv = offset$iv;
                Segment segment3 = s$iv2.next;
                Intrinsics.checkNotNull(segment3);
                s$iv2 = segment3;
            }
            return -1L;
        }
        long j = 0;
        while (true) {
            offset$iv$iv = j;
            long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
            if (nextOffset$iv$iv > fromIndex$iv) {
                break;
            }
            Segment segment4 = s$iv$iv.next;
            Intrinsics.checkNotNull(segment4);
            s$iv$iv = segment4;
            j = nextOffset$iv$iv;
        }
        Segment s$iv3 = s$iv$iv;
        if (s$iv3 == null) {
            return -1L;
        }
        Segment s$iv4 = s$iv3;
        long offset$iv2 = offset$iv$iv;
        byte[] targetByteArray$iv2 = bytes.internalArray$okio();
        byte b0$iv2 = targetByteArray$iv2[0];
        int bytesSize$iv2 = bytes.size();
        long resultLimit$iv2 = (size() - bytesSize$iv2) + 1;
        while (offset$iv2 < resultLimit$iv2) {
            byte[] data$iv2 = s$iv4.data;
            int a$iv$iv2 = s$iv4.limit;
            long b$iv$iv2 = (s$iv4.pos + resultLimit$iv2) - offset$iv2;
            int segmentLimit$iv2 = (int) Math.min(a$iv$iv2, b$iv$iv2);
            for (int pos$iv2 = (int) ((s$iv4.pos + fromIndex$iv) - offset$iv2); pos$iv2 < segmentLimit$iv2; pos$iv2++) {
                if (data$iv2[pos$iv2] == b0$iv2 && BufferKt.rangeEquals(s$iv4, pos$iv2 + 1, targetByteArray$iv2, 1, bytesSize$iv2)) {
                    return (pos$iv2 - s$iv4.pos) + offset$iv2;
                }
            }
            offset$iv2 += s$iv4.limit - s$iv4.pos;
            fromIndex$iv = offset$iv2;
            Segment segment5 = s$iv4.next;
            Intrinsics.checkNotNull(segment5);
            s$iv4 = segment5;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public long indexOfElement(@NotNull ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0L);
    }

    @Override // okio.BufferedSource
    public long indexOfElement(@NotNull ByteString targetBytes, long fromIndex) {
        long offset$iv$iv;
        long offset$iv$iv2;
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long fromIndex$iv = fromIndex;
        if (!(fromIndex$iv >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + fromIndex$iv).toString());
        }
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        Segment s$iv$iv = segment;
        if (size() - fromIndex$iv < fromIndex$iv) {
            long size = size();
            while (true) {
                offset$iv$iv2 = size;
                if (offset$iv$iv2 <= fromIndex$iv) {
                    break;
                }
                Segment segment2 = s$iv$iv.prev;
                Intrinsics.checkNotNull(segment2);
                s$iv$iv = segment2;
                size = offset$iv$iv2 - (s$iv$iv.limit - s$iv$iv.pos);
            }
            Segment s$iv = s$iv$iv;
            if (s$iv == null) {
                return -1L;
            }
            Segment s$iv2 = s$iv;
            long offset$iv = offset$iv$iv2;
            if (targetBytes.size() == 2) {
                byte b0$iv = targetBytes.getByte(0);
                byte b1$iv = targetBytes.getByte(1);
                while (offset$iv < size()) {
                    byte[] data$iv = s$iv2.data;
                    int limit$iv = s$iv2.limit;
                    for (int pos$iv = (int) ((s$iv2.pos + fromIndex$iv) - offset$iv); pos$iv < limit$iv; pos$iv++) {
                        byte b = data$iv[pos$iv];
                        if (b == b0$iv || b == b1$iv) {
                            return (pos$iv - s$iv2.pos) + offset$iv;
                        }
                    }
                    offset$iv += s$iv2.limit - s$iv2.pos;
                    fromIndex$iv = offset$iv;
                    Segment segment3 = s$iv2.next;
                    Intrinsics.checkNotNull(segment3);
                    s$iv2 = segment3;
                }
            } else {
                byte[] targetByteArray$iv = targetBytes.internalArray$okio();
                while (offset$iv < size()) {
                    byte[] data$iv2 = s$iv2.data;
                    int limit$iv2 = s$iv2.limit;
                    for (int pos$iv2 = (int) ((s$iv2.pos + fromIndex$iv) - offset$iv); pos$iv2 < limit$iv2; pos$iv2++) {
                        byte b2 = data$iv2[pos$iv2];
                        for (byte t$iv : targetByteArray$iv) {
                            if (b2 == t$iv) {
                                return (pos$iv2 - s$iv2.pos) + offset$iv;
                            }
                        }
                    }
                    offset$iv += s$iv2.limit - s$iv2.pos;
                    fromIndex$iv = offset$iv;
                    Segment segment4 = s$iv2.next;
                    Intrinsics.checkNotNull(segment4);
                    s$iv2 = segment4;
                }
            }
            return -1L;
        }
        long j = 0;
        while (true) {
            offset$iv$iv = j;
            long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
            if (nextOffset$iv$iv > fromIndex$iv) {
                break;
            }
            Segment segment5 = s$iv$iv.next;
            Intrinsics.checkNotNull(segment5);
            s$iv$iv = segment5;
            j = nextOffset$iv$iv;
        }
        Segment s$iv3 = s$iv$iv;
        if (s$iv3 == null) {
            return -1L;
        }
        Segment s$iv4 = s$iv3;
        long offset$iv2 = offset$iv$iv;
        if (targetBytes.size() == 2) {
            byte b0$iv2 = targetBytes.getByte(0);
            byte b1$iv2 = targetBytes.getByte(1);
            while (offset$iv2 < size()) {
                byte[] data$iv3 = s$iv4.data;
                int limit$iv3 = s$iv4.limit;
                for (int pos$iv3 = (int) ((s$iv4.pos + fromIndex$iv) - offset$iv2); pos$iv3 < limit$iv3; pos$iv3++) {
                    byte b3 = data$iv3[pos$iv3];
                    if (b3 == b0$iv2 || b3 == b1$iv2) {
                        return (pos$iv3 - s$iv4.pos) + offset$iv2;
                    }
                }
                offset$iv2 += s$iv4.limit - s$iv4.pos;
                fromIndex$iv = offset$iv2;
                Segment segment6 = s$iv4.next;
                Intrinsics.checkNotNull(segment6);
                s$iv4 = segment6;
            }
        } else {
            byte[] targetByteArray$iv2 = targetBytes.internalArray$okio();
            while (offset$iv2 < size()) {
                byte[] data$iv4 = s$iv4.data;
                int limit$iv4 = s$iv4.limit;
                for (int pos$iv4 = (int) ((s$iv4.pos + fromIndex$iv) - offset$iv2); pos$iv4 < limit$iv4; pos$iv4++) {
                    byte b4 = data$iv4[pos$iv4];
                    for (byte t$iv2 : targetByteArray$iv2) {
                        if (b4 == t$iv2) {
                            return (pos$iv4 - s$iv4.pos) + offset$iv2;
                        }
                    }
                }
                offset$iv2 += s$iv4.limit - s$iv4.pos;
                fromIndex$iv = offset$iv2;
                Segment segment7 = s$iv4.next;
                Intrinsics.checkNotNull(segment7);
                s$iv4 = segment7;
            }
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, @NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (offset < 0 || bytesOffset < 0 || byteCount < 0 || size() - offset < byteCount || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i$iv = 0; i$iv < byteCount; i$iv++) {
            if (getByte(offset + i$iv) != bytes.getByte(bytesOffset + i$iv)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @NotNull
    public final ByteString md5() {
        return digest("MD5");
    }

    @NotNull
    public final ByteString sha1() {
        return digest("SHA-1");
    }

    @NotNull
    public final ByteString sha256() {
        return digest("SHA-256");
    }

    @NotNull
    public final ByteString sha512() {
        return digest("SHA-512");
    }

    private final ByteString digest(String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        Segment head = this.head;
        if (head != null) {
            messageDigest.update(head.data, head.pos, head.limit - head.pos);
            Segment segment = head.next;
            Intrinsics.checkNotNull(segment);
            while (true) {
                Segment s = segment;
                if (s == head) {
                    break;
                }
                messageDigest.update(s.data, s.pos, s.limit - s.pos);
                segment = s.next;
                Intrinsics.checkNotNull(segment);
            }
        }
        byte[] bArrDigest = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "messageDigest.digest()");
        return new ByteString(bArrDigest);
    }

    @NotNull
    public final ByteString hmacSha1(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac("HmacSHA1", key);
    }

    @NotNull
    public final ByteString hmacSha256(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac("HmacSHA256", key);
    }

    @NotNull
    public final ByteString hmacSha512(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac("HmacSHA512", key);
    }

    private final ByteString hmac(String algorithm, ByteString key) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.internalArray$okio(), algorithm));
            Segment head = this.head;
            if (head != null) {
                mac.update(head.data, head.pos, head.limit - head.pos);
                Segment segment = head.next;
                Intrinsics.checkNotNull(segment);
                Segment s = segment;
                while (s != head) {
                    mac.update(s.data, s.pos, s.limit - s.pos);
                    Segment segment2 = s.next;
                    Intrinsics.checkNotNull(segment2);
                    s = segment2;
                }
            }
            byte[] bArrDoFinal = mac.doFinal();
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "mac.doFinal()");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Buffer) || size() != ((Buffer) other).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment sa$iv = segment;
        Segment segment2 = ((Buffer) other).head;
        Intrinsics.checkNotNull(segment2);
        Segment sb$iv = segment2;
        int posA$iv = sa$iv.pos;
        int posB$iv = sb$iv.pos;
        long pos$iv = 0;
        while (pos$iv < size()) {
            long count$iv = Math.min(sa$iv.limit - posA$iv, sb$iv.limit - posB$iv);
            for (long i$iv = 0; i$iv < count$iv; i$iv++) {
                int i = posA$iv;
                posA$iv++;
                int i2 = posB$iv;
                posB$iv++;
                if (sa$iv.data[i] != sb$iv.data[i2]) {
                    return false;
                }
            }
            if (posA$iv == sa$iv.limit) {
                Segment segment3 = sa$iv.next;
                Intrinsics.checkNotNull(segment3);
                sa$iv = segment3;
                posA$iv = sa$iv.pos;
            }
            if (posB$iv == sb$iv.limit) {
                Segment segment4 = sb$iv.next;
                Intrinsics.checkNotNull(segment4);
                sb$iv = segment4;
                posB$iv = sb$iv.pos;
            }
            pos$iv += count$iv;
        }
        return true;
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        Segment s$iv = segment;
        int result$iv = 1;
        do {
            int limit$iv = s$iv.limit;
            for (int pos$iv = s$iv.pos; pos$iv < limit$iv; pos$iv++) {
                result$iv = (31 * result$iv) + s$iv.data[pos$iv];
            }
            Segment segment2 = s$iv.next;
            Intrinsics.checkNotNull(segment2);
            s$iv = segment2;
        } while (s$iv != this.head);
        return result$iv;
    }

    @NotNull
    public String toString() {
        return snapshot().toString();
    }

    @NotNull
    public final Buffer copy() {
        Buffer result$iv = new Buffer();
        if (size() == 0) {
            return result$iv;
        }
        Segment head$iv = this.head;
        Intrinsics.checkNotNull(head$iv);
        Segment headCopy$iv = head$iv.sharedCopy();
        result$iv.head = headCopy$iv;
        headCopy$iv.prev = result$iv.head;
        headCopy$iv.next = headCopy$iv.prev;
        Segment segment = head$iv.next;
        while (true) {
            Segment s$iv = segment;
            if (s$iv != head$iv) {
                Segment segment2 = headCopy$iv.prev;
                Intrinsics.checkNotNull(segment2);
                Intrinsics.checkNotNull(s$iv);
                segment2.push(s$iv.sharedCopy());
                segment = s$iv.next;
            } else {
                result$iv.setSize$okio(size());
                return result$iv;
            }
        }
    }

    @NotNull
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Buffer m4650clone() {
        return copy();
    }

    @NotNull
    public final ByteString snapshot() {
        if (!(size() <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + size()).toString());
        }
        return snapshot((int) size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final ByteString snapshot(int byteCount) {
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        Util.checkOffsetAndCount(size(), 0L, byteCount);
        int offset$iv = 0;
        int segmentCount$iv = 0;
        Segment segment = this.head;
        while (true) {
            Segment s$iv = segment;
            if (offset$iv < byteCount) {
                Intrinsics.checkNotNull(s$iv);
                if (s$iv.limit == s$iv.pos) {
                    throw new AssertionError("s.limit == s.pos");
                }
                offset$iv += s$iv.limit - s$iv.pos;
                segmentCount$iv++;
                segment = s$iv.next;
            } else {
                byte[] bArr = new byte[segmentCount$iv];
                int[] directory$iv = new int[segmentCount$iv * 2];
                int offset$iv2 = 0;
                int segmentCount$iv2 = 0;
                Segment segment2 = this.head;
                while (true) {
                    Segment s$iv2 = segment2;
                    if (offset$iv2 < byteCount) {
                        Intrinsics.checkNotNull(s$iv2);
                        bArr[segmentCount$iv2] = s$iv2.data;
                        offset$iv2 += s$iv2.limit - s$iv2.pos;
                        directory$iv[segmentCount$iv2] = Math.min(offset$iv2, byteCount);
                        directory$iv[segmentCount$iv2 + ((Object[]) bArr).length] = s$iv2.pos;
                        s$iv2.shared = true;
                        segmentCount$iv2++;
                        segment2 = s$iv2.next;
                    } else {
                        return new SegmentedByteString((byte[][]) bArr, directory$iv);
                    }
                }
            }
        }
    }

    public static /* synthetic */ UnsafeCursor readUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readUnsafe(unsafeCursor);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readUnsafe(@NotNull UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        if (!(unsafeCursor.buffer == null)) {
            throw new IllegalStateException("already attached to a buffer".toString());
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = false;
        return unsafeCursor;
    }

    public static /* synthetic */ UnsafeCursor readAndWriteUnsafe$default(Buffer buffer, UnsafeCursor unsafeCursor, int i, Object obj) {
        if ((i & 1) != 0) {
            unsafeCursor = new UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }

    @JvmOverloads
    @NotNull
    public final UnsafeCursor readAndWriteUnsafe(@NotNull UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        if (!(unsafeCursor.buffer == null)) {
            throw new IllegalStateException("already attached to a buffer".toString());
        }
        unsafeCursor.buffer = this;
        unsafeCursor.readWrite = true;
        return unsafeCursor;
    }

    @Deprecated(message = "moved to operator function", replaceWith = @ReplaceWith(imports = {}, expression = "this[index]"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_getByte")
    /* renamed from: -deprecated_getByte, reason: not valid java name */
    public final byte m4648deprecated_getByte(long index) {
        return getByte(index);
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "size"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_size")
    /* renamed from: -deprecated_size, reason: not valid java name */
    public final long m4649deprecated_size() {
        return this.size;
    }

    /* compiled from: Buffer.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\b\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\bJ\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nJ\u000e\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0012\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0012\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0012\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n��R\u0012\u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n��¨\u0006\u0018"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "()V", "buffer", "Lokio/Buffer;", "data", "", "end", "", "offset", "", "readWrite", "", "segment", "Lokio/Segment;", "start", "close", "", "expandBuffer", "minByteCount", "next", "resizeBuffer", "newSize", "seek", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/Buffer$UnsafeCursor.class */
    public static final class UnsafeCursor implements Closeable {

        @JvmField
        @Nullable
        public Buffer buffer;

        @JvmField
        public boolean readWrite;
        private Segment segment;

        @JvmField
        @Nullable
        public byte[] data;

        @JvmField
        public long offset = -1;

        @JvmField
        public int start = -1;

        @JvmField
        public int end = -1;

        public final int next() {
            long j = this.offset;
            Buffer buffer = this.buffer;
            Intrinsics.checkNotNull(buffer);
            if (j != buffer.size()) {
                return this.offset == -1 ? seek(0L) : seek(this.offset + (this.end - this.start));
            }
            throw new IllegalStateException("no more bytes".toString());
        }

        public final int seek(long offset) {
            Segment next;
            long nextOffset;
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            if (offset < -1 || offset > buffer.size()) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Long.valueOf(offset), Long.valueOf(buffer.size())};
                String str = String.format("offset=%s > size=%s", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                throw new ArrayIndexOutOfBoundsException(str);
            }
            if (offset == -1 || offset == buffer.size()) {
                this.segment = (Segment) null;
                this.offset = offset;
                this.data = (byte[]) null;
                this.start = -1;
                this.end = -1;
                return -1;
            }
            long min = 0;
            long max = buffer.size();
            Segment head = buffer.head;
            Segment tail = buffer.head;
            if (this.segment != null) {
                long j = this.offset;
                int i = this.start;
                Intrinsics.checkNotNull(this.segment);
                long segmentOffset = j - (i - r2.pos);
                if (segmentOffset > offset) {
                    max = segmentOffset;
                    tail = this.segment;
                } else {
                    min = segmentOffset;
                    head = this.segment;
                }
            }
            if (max - offset <= offset - min) {
                next = tail;
                long j2 = max;
                while (true) {
                    nextOffset = j2;
                    if (nextOffset <= offset) {
                        break;
                    }
                    Segment segment = next;
                    Intrinsics.checkNotNull(segment);
                    next = segment.prev;
                    Intrinsics.checkNotNull(next);
                    j2 = nextOffset - (next.limit - next.pos);
                }
            } else {
                next = head;
                nextOffset = min;
                while (true) {
                    Intrinsics.checkNotNull(next);
                    if (offset < nextOffset + (r2.limit - next.pos)) {
                        break;
                    }
                    nextOffset += next.limit - next.pos;
                    next = next.next;
                }
            }
            if (this.readWrite) {
                Segment segment2 = next;
                Intrinsics.checkNotNull(segment2);
                if (segment2.shared) {
                    Segment unsharedNext = next.unsharedCopy();
                    if (buffer.head == next) {
                        buffer.head = unsharedNext;
                    }
                    next = next.push(unsharedNext);
                    Segment segment3 = next.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.pop();
                }
            }
            this.segment = next;
            this.offset = offset;
            Segment segment4 = next;
            Intrinsics.checkNotNull(segment4);
            this.data = segment4.data;
            this.start = next.pos + ((int) (offset - nextOffset));
            this.end = next.limit;
            return this.end - this.start;
        }

        public final long resizeBuffer(long newSize) {
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers".toString());
            }
            long oldSize = buffer.size();
            if (newSize <= oldSize) {
                if (!(newSize >= 0)) {
                    throw new IllegalArgumentException(("newSize < 0: " + newSize).toString());
                }
                long j = oldSize;
                long j2 = newSize;
                while (true) {
                    long bytesToSubtract = j - j2;
                    if (bytesToSubtract <= 0) {
                        break;
                    }
                    Segment segment = buffer.head;
                    Intrinsics.checkNotNull(segment);
                    Segment tail = segment.prev;
                    Intrinsics.checkNotNull(tail);
                    int tailSize = tail.limit - tail.pos;
                    if (tailSize > bytesToSubtract) {
                        tail.limit -= (int) bytesToSubtract;
                        break;
                    }
                    buffer.head = tail.pop();
                    SegmentPool.recycle(tail);
                    j = bytesToSubtract;
                    j2 = tailSize;
                }
                this.segment = (Segment) null;
                this.offset = newSize;
                this.data = (byte[]) null;
                this.start = -1;
                this.end = -1;
            } else if (newSize > oldSize) {
                boolean needsToSeek = true;
                long bytesToAdd = newSize - oldSize;
                while (bytesToAdd > 0) {
                    Segment tail2 = buffer.writableSegment$okio(1);
                    int b$iv = 8192 - tail2.limit;
                    int segmentBytesToAdd = (int) Math.min(bytesToAdd, b$iv);
                    tail2.limit += segmentBytesToAdd;
                    bytesToAdd -= segmentBytesToAdd;
                    if (needsToSeek) {
                        this.segment = tail2;
                        this.offset = oldSize;
                        this.data = tail2.data;
                        this.start = tail2.limit - segmentBytesToAdd;
                        this.end = tail2.limit;
                        needsToSeek = false;
                    }
                }
            }
            buffer.setSize$okio(newSize);
            return oldSize;
        }

        public final long expandBuffer(int minByteCount) {
            if (!(minByteCount > 0)) {
                throw new IllegalArgumentException(("minByteCount <= 0: " + minByteCount).toString());
            }
            if (!(minByteCount <= 8192)) {
                throw new IllegalArgumentException(("minByteCount > Segment.SIZE: " + minByteCount).toString());
            }
            Buffer buffer = this.buffer;
            if (buffer == null) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            if (!this.readWrite) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers".toString());
            }
            long oldSize = buffer.size();
            Segment tail = buffer.writableSegment$okio(minByteCount);
            int result = 8192 - tail.limit;
            tail.limit = 8192;
            buffer.setSize$okio(oldSize + result);
            this.segment = tail;
            this.offset = oldSize;
            this.data = tail.data;
            this.start = 8192 - result;
            this.end = 8192;
            return result;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (!(this.buffer != null)) {
                throw new IllegalStateException("not attached to a buffer".toString());
            }
            this.buffer = (Buffer) null;
            this.segment = (Segment) null;
            this.offset = -1L;
            this.data = (byte[]) null;
            this.start = -1;
            this.end = -1;
        }
    }
}
