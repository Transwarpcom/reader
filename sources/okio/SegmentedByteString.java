package okio;

import io.netty.handler.codec.http.HttpHeaders;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okio.internal.SegmentedByteStringKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: SegmentedByteString.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n��\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\b��\u0018��2\u00020\u0001B\u001d\b��\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0015\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\r\u0010\u0019\u001a\u00020\u001aH\u0010¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0010H\u0016J\u001d\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0001H\u0010¢\u0006\u0002\b J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u001aH\u0016J\r\u0010#\u001a\u00020\u0004H\u0010¢\u0006\u0002\b$J\u0015\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001aH\u0010¢\u0006\u0002\b(J\u0018\u0010)\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u001aH\u0016J(\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001aH\u0016J(\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001aH\u0016J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u000200H\u0016J\u0018\u00101\u001a\u00020\u00012\u0006\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u001aH\u0016J\b\u00104\u001a\u00020\u0001H\u0016J\b\u00105\u001a\u00020\u0001H\u0016J\b\u00106\u001a\u00020\u0004H\u0016J\b\u00107\u001a\u00020\u0001H\u0002J\b\u00108\u001a\u00020\u0010H\u0016J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0016J%\u00109\u001a\u00020:2\u0006\u0010=\u001a\u00020>2\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001aH\u0010¢\u0006\u0002\b?J\b\u0010@\u001a\u00020AH\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006B"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", "segments", "", "", "directory", "", "([[B[I)V", "getDirectory$okio", "()[I", "getSegments$okio", "()[[B", "[[B", "asByteBuffer", "Ljava/nio/ByteBuffer;", HttpHeaders.Values.BASE64, "", "base64Url", "digest", "algorithm", "digest$okio", "equals", "", "other", "", "getSize", "", "getSize$okio", IdentityNamingStrategy.HASH_CODE_KEY, "hex", "hmac", "key", "hmac$okio", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "", "pos", "internalGet$okio", "lastIndexOf", "rangeEquals", "offset", "otherOffset", "byteCount", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toByteString", "toString", "write", "", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeReplace", "Ljava/lang/Object;", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/SegmentedByteString.class */
public final class SegmentedByteString extends ByteString {

    @NotNull
    private final transient byte[][] segments;

    @NotNull
    private final transient int[] directory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedByteString(@NotNull byte[][] segments, @NotNull int[] directory) {
        super(ByteString.EMPTY.getData$okio());
        Intrinsics.checkNotNullParameter(segments, "segments");
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.segments = segments;
        this.directory = directory;
    }

    @NotNull
    public final byte[][] getSegments$okio() {
        return this.segments;
    }

    @NotNull
    public final int[] getDirectory$okio() {
        return this.directory;
    }

    @Override // okio.ByteString
    @NotNull
    public String string(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return toByteString().string(charset);
    }

    @Override // okio.ByteString
    @NotNull
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    @NotNull
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    @NotNull
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // okio.ByteString
    @NotNull
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // okio.ByteString
    @NotNull
    public ByteString digest$okio(@NotNull String algorithm) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        int segmentCount$iv = getSegments$okio().length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory$okio()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory$okio()[s$iv];
            byte[] data = getSegments$okio()[s$iv];
            int byteCount = nextSegmentOffset$iv - pos$iv;
            digest.update(data, segmentPos$iv, byteCount);
            pos$iv = nextSegmentOffset$iv;
        }
        byte[] bArrDigest = digest.digest();
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest.digest()");
        return new ByteString(bArrDigest);
    }

    @Override // okio.ByteString
    @NotNull
    public ByteString hmac$okio(@NotNull String algorithm, @NotNull ByteString key) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            int segmentCount$iv = getSegments$okio().length;
            int pos$iv = 0;
            for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
                int segmentPos$iv = getDirectory$okio()[segmentCount$iv + s$iv];
                int nextSegmentOffset$iv = getDirectory$okio()[s$iv];
                byte[] data = getSegments$okio()[s$iv];
                int byteCount = nextSegmentOffset$iv - pos$iv;
                mac.update(data, segmentPos$iv, byteCount);
                pos$iv = nextSegmentOffset$iv;
            }
            byte[] bArrDoFinal = mac.doFinal();
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "mac.doFinal()");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // okio.ByteString
    @NotNull
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // okio.ByteString
    @NotNull
    public ByteString substring(int beginIndex, int endIndex) {
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(("beginIndex=" + beginIndex + " < 0").toString());
        }
        if (!(endIndex <= size())) {
            throw new IllegalArgumentException(("endIndex=" + endIndex + " > length(" + size() + ')').toString());
        }
        int subLen$iv = endIndex - beginIndex;
        if (!(subLen$iv >= 0)) {
            throw new IllegalArgumentException(("endIndex=" + endIndex + " < beginIndex=" + beginIndex).toString());
        }
        if (beginIndex == 0 && endIndex == size()) {
            return this;
        }
        if (beginIndex == endIndex) {
            return ByteString.EMPTY;
        }
        int beginSegment$iv = SegmentedByteStringKt.segment(this, beginIndex);
        int endSegment$iv = SegmentedByteStringKt.segment(this, endIndex - 1);
        byte[][] newSegments$iv = (byte[][]) ArraysKt.copyOfRange(getSegments$okio(), beginSegment$iv, endSegment$iv + 1);
        int[] newDirectory$iv = new int[newSegments$iv.length * 2];
        int index$iv = 0;
        int s$iv = beginSegment$iv;
        if (s$iv <= endSegment$iv) {
            while (true) {
                newDirectory$iv[index$iv] = Math.min(getDirectory$okio()[s$iv] - beginIndex, subLen$iv);
                int i = index$iv;
                index$iv++;
                newDirectory$iv[i + newSegments$iv.length] = getDirectory$okio()[s$iv + getSegments$okio().length];
                if (s$iv == endSegment$iv) {
                    break;
                }
                s$iv++;
            }
        }
        int segmentOffset$iv = beginSegment$iv == 0 ? 0 : getDirectory$okio()[beginSegment$iv - 1];
        int length = newSegments$iv.length;
        newDirectory$iv[length] = newDirectory$iv[length] + (beginIndex - segmentOffset$iv);
        return new SegmentedByteString(newSegments$iv, newDirectory$iv);
    }

    @Override // okio.ByteString
    public byte internalGet$okio(int pos) {
        Util.checkOffsetAndCount(getDirectory$okio()[getSegments$okio().length - 1], pos, 1L);
        int segment$iv = SegmentedByteStringKt.segment(this, pos);
        int segmentOffset$iv = segment$iv == 0 ? 0 : getDirectory$okio()[segment$iv - 1];
        int segmentPos$iv = getDirectory$okio()[segment$iv + getSegments$okio().length];
        return getSegments$okio()[segment$iv][(pos - segmentOffset$iv) + segmentPos$iv];
    }

    @Override // okio.ByteString
    public int getSize$okio() {
        return getDirectory$okio()[getSegments$okio().length - 1];
    }

    @Override // okio.ByteString
    @NotNull
    public byte[] toByteArray() {
        byte[] result$iv = new byte[size()];
        int resultPos$iv = 0;
        int segmentCount$iv$iv = getSegments$okio().length;
        int pos$iv$iv = 0;
        for (int s$iv$iv = 0; s$iv$iv < segmentCount$iv$iv; s$iv$iv++) {
            int segmentPos$iv$iv = getDirectory$okio()[segmentCount$iv$iv + s$iv$iv];
            int nextSegmentOffset$iv$iv = getDirectory$okio()[s$iv$iv];
            byte[] data$iv = getSegments$okio()[s$iv$iv];
            int byteCount$iv = nextSegmentOffset$iv$iv - pos$iv$iv;
            ArraysKt.copyInto(data$iv, result$iv, resultPos$iv, segmentPos$iv$iv, segmentPos$iv$iv + byteCount$iv);
            resultPos$iv += byteCount$iv;
            pos$iv$iv = nextSegmentOffset$iv$iv;
        }
        return result$iv;
    }

    @Override // okio.ByteString
    @NotNull
    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
        Intrinsics.checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer()");
        return byteBufferAsReadOnlyBuffer;
    }

    @Override // okio.ByteString
    public void write(@NotNull OutputStream out) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        int segmentCount$iv = getSegments$okio().length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = getDirectory$okio()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = getDirectory$okio()[s$iv];
            byte[] data = getSegments$okio()[s$iv];
            int byteCount = nextSegmentOffset$iv - pos$iv;
            out.write(data, segmentPos$iv, byteCount);
            pos$iv = nextSegmentOffset$iv;
        }
    }

    @Override // okio.ByteString
    public void write$okio(@NotNull Buffer buffer, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int endIndex$iv$iv = offset + byteCount;
        int s$iv$iv = SegmentedByteStringKt.segment(this, offset);
        int pos$iv$iv = offset;
        while (pos$iv$iv < endIndex$iv$iv) {
            int segmentOffset$iv$iv = s$iv$iv == 0 ? 0 : getDirectory$okio()[s$iv$iv - 1];
            int segmentSize$iv$iv = getDirectory$okio()[s$iv$iv] - segmentOffset$iv$iv;
            int segmentPos$iv$iv = getDirectory$okio()[getSegments$okio().length + s$iv$iv];
            int byteCount$iv$iv = Math.min(endIndex$iv$iv, segmentOffset$iv$iv + segmentSize$iv$iv) - pos$iv$iv;
            int offset$iv$iv = segmentPos$iv$iv + (pos$iv$iv - segmentOffset$iv$iv);
            byte[] data$iv = getSegments$okio()[s$iv$iv];
            Segment segment$iv = new Segment(data$iv, offset$iv$iv, offset$iv$iv + byteCount$iv$iv, true, false);
            if (buffer.head == null) {
                segment$iv.prev = segment$iv;
                segment$iv.next = segment$iv.prev;
                buffer.head = segment$iv.next;
            } else {
                Segment segment = buffer.head;
                Intrinsics.checkNotNull(segment);
                Segment segment2 = segment.prev;
                Intrinsics.checkNotNull(segment2);
                segment2.push(segment$iv);
            }
            pos$iv$iv += byteCount$iv$iv;
            s$iv$iv++;
        }
        buffer.setSize$okio(buffer.size() + size());
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > size() - byteCount) {
            return false;
        }
        int otherOffset$iv = otherOffset;
        int endIndex$iv$iv = offset + byteCount;
        int s$iv$iv = SegmentedByteStringKt.segment(this, offset);
        int pos$iv$iv = offset;
        while (pos$iv$iv < endIndex$iv$iv) {
            int segmentOffset$iv$iv = s$iv$iv == 0 ? 0 : getDirectory$okio()[s$iv$iv - 1];
            int segmentSize$iv$iv = getDirectory$okio()[s$iv$iv] - segmentOffset$iv$iv;
            int segmentPos$iv$iv = getDirectory$okio()[getSegments$okio().length + s$iv$iv];
            int byteCount$iv$iv = Math.min(endIndex$iv$iv, segmentOffset$iv$iv + segmentSize$iv$iv) - pos$iv$iv;
            int offset$iv$iv = segmentPos$iv$iv + (pos$iv$iv - segmentOffset$iv$iv);
            byte[] data$iv = getSegments$okio()[s$iv$iv];
            if (!other.rangeEquals(otherOffset$iv, data$iv, offset$iv$iv, byteCount$iv$iv)) {
                return false;
            }
            otherOffset$iv += byteCount$iv$iv;
            pos$iv$iv += byteCount$iv$iv;
            s$iv$iv++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int otherOffset$iv = otherOffset;
        int endIndex$iv$iv = offset + byteCount;
        int s$iv$iv = SegmentedByteStringKt.segment(this, offset);
        int pos$iv$iv = offset;
        while (pos$iv$iv < endIndex$iv$iv) {
            int segmentOffset$iv$iv = s$iv$iv == 0 ? 0 : getDirectory$okio()[s$iv$iv - 1];
            int segmentSize$iv$iv = getDirectory$okio()[s$iv$iv] - segmentOffset$iv$iv;
            int segmentPos$iv$iv = getDirectory$okio()[getSegments$okio().length + s$iv$iv];
            int byteCount$iv$iv = Math.min(endIndex$iv$iv, segmentOffset$iv$iv + segmentSize$iv$iv) - pos$iv$iv;
            int offset$iv$iv = segmentPos$iv$iv + (pos$iv$iv - segmentOffset$iv$iv);
            byte[] data$iv = getSegments$okio()[s$iv$iv];
            if (!Util.arrayRangeEquals(data$iv, offset$iv$iv, other, otherOffset$iv, byteCount$iv$iv)) {
                return false;
            }
            otherOffset$iv += byteCount$iv$iv;
            pos$iv$iv += byteCount$iv$iv;
            s$iv$iv++;
        }
        return true;
    }

    @Override // okio.ByteString
    public int indexOf(@NotNull byte[] other, int fromIndex) {
        Intrinsics.checkNotNullParameter(other, "other");
        return toByteString().indexOf(other, fromIndex);
    }

    @Override // okio.ByteString
    public int lastIndexOf(@NotNull byte[] other, int fromIndex) {
        Intrinsics.checkNotNullParameter(other, "other");
        return toByteString().lastIndexOf(other, fromIndex);
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    @NotNull
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof ByteString) && ((ByteString) other).size() == size() && rangeEquals(0, (ByteString) other, 0, size());
    }

    @Override // okio.ByteString
    public int hashCode() {
        int result$iv = getHashCode$okio();
        if (result$iv != 0) {
            return result$iv;
        }
        int result$iv2 = 1;
        int segmentCount$iv$iv = getSegments$okio().length;
        int pos$iv$iv = 0;
        for (int s$iv$iv = 0; s$iv$iv < segmentCount$iv$iv; s$iv$iv++) {
            int segmentPos$iv$iv = getDirectory$okio()[segmentCount$iv$iv + s$iv$iv];
            int nextSegmentOffset$iv$iv = getDirectory$okio()[s$iv$iv];
            byte[] data$iv = getSegments$okio()[s$iv$iv];
            int byteCount$iv = nextSegmentOffset$iv$iv - pos$iv$iv;
            int limit$iv = segmentPos$iv$iv + byteCount$iv;
            for (int i$iv = segmentPos$iv$iv; i$iv < limit$iv; i$iv++) {
                result$iv2 = (31 * result$iv2) + data$iv[i$iv];
            }
            pos$iv$iv = nextSegmentOffset$iv$iv;
        }
        setHashCode$okio(result$iv2);
        return result$iv2;
    }

    @Override // okio.ByteString
    @NotNull
    public String toString() {
        return toByteString().toString();
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        if (byteString == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
        }
        return byteString;
    }
}
