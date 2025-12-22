package okio.internal;

import io.vertx.ext.web.handler.StaticHandler;
import java.io.EOFException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import okhttp3.internal.connection.RealConnection;
import okio.Buffer;
import okio.ByteString;
import okio.Options;
import okio.Platform;
import okio.Segment;
import okio.SegmentPool;
import okio.SegmentedByteString;
import okio.Sink;
import okio.Source;
import okio.Util;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Buffer.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"��v\n��\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010��\n��\n\u0002\u0010\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\n\n��\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH��\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0080\b\u001a\r\u0010\u0014\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u0010\u0015\u001a\u00020\u0013*\u00020\u0013H\u0080\b\u001a%\u0010\u0016\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0017\u0010\u001a\u001a\u00020\n*\u00020\u00132\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0080\b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u0005H\u0080\b\u001a\r\u0010 \u001a\u00020\b*\u00020\u0013H\u0080\b\u001a%\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010!\u001a\u00020\u0005*\u00020\u00132\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a\u001d\u0010&\u001a\u00020\u0005*\u00020\u00132\u0006\u0010'\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0005H\u0080\b\u001a-\u0010(\u001a\u00020\n*\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a%\u0010)\u001a\u00020\b*\u00020\u00132\u0006\u0010*\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010)\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010+\u001a\u00020\u0005*\u00020\u00132\u0006\u0010*\u001a\u00020,H\u0080\b\u001a\r\u0010-\u001a\u00020\u001e*\u00020\u0013H\u0080\b\u001a\r\u0010.\u001a\u00020\u0001*\u00020\u0013H\u0080\b\u001a\u0015\u0010.\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010/\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010/\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00100\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\u0015\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u0001H\u0080\b\u001a\u001d\u00101\u001a\u00020\u0012*\u00020\u00132\u0006\u0010*\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00102\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00103\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\r\u00104\u001a\u00020\u0005*\u00020\u0013H\u0080\b\u001a\r\u00105\u001a\u000206*\u00020\u0013H\u0080\b\u001a\u0015\u00107\u001a\u000208*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u00109\u001a\u00020\b*\u00020\u0013H\u0080\b\u001a\u000f\u0010:\u001a\u0004\u0018\u000108*\u00020\u0013H\u0080\b\u001a\u0015\u0010;\u001a\u000208*\u00020\u00132\u0006\u0010<\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010=\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?H\u0080\b\u001a\u0015\u0010@\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\r\u0010A\u001a\u00020%*\u00020\u0013H\u0080\b\u001a\u0015\u0010A\u001a\u00020%*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u0015\u0010B\u001a\u00020\f*\u00020\u00132\u0006\u0010C\u001a\u00020\bH\u0080\b\u001a\u0015\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u0001H\u0080\b\u001a%\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0012*\u00020\u00132\u0006\u0010E\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a)\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010F\u001a\u00020%2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\bH\u0080\b\u001a\u001d\u0010D\u001a\u00020\u0013*\u00020\u00132\u0006\u0010E\u001a\u00020G2\u0006\u0010\u0019\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010H\u001a\u00020\u0005*\u00020\u00132\u0006\u0010E\u001a\u00020GH\u0080\b\u001a\u0015\u0010I\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\"\u001a\u00020\bH\u0080\b\u001a\u0015\u0010J\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010L\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010M\u001a\u00020\u0013*\u00020\u00132\u0006\u0010N\u001a\u00020\bH\u0080\b\u001a\u0015\u0010O\u001a\u00020\u0013*\u00020\u00132\u0006\u0010K\u001a\u00020\u0005H\u0080\b\u001a\u0015\u0010P\u001a\u00020\u0013*\u00020\u00132\u0006\u0010Q\u001a\u00020\bH\u0080\b\u001a%\u0010R\u001a\u00020\u0013*\u00020\u00132\u0006\u0010S\u001a\u0002082\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\bH\u0080\b\u001a\u0015\u0010V\u001a\u00020\u0013*\u00020\u00132\u0006\u0010W\u001a\u00020\bH\u0080\b\u001a\u0014\u0010X\u001a\u000208*\u00020\u00132\u0006\u0010Y\u001a\u00020\u0005H��\u001a?\u0010Z\u001a\u0002H[\"\u0004\b��\u0010[*\u00020\u00132\u0006\u0010#\u001a\u00020\u00052\u001a\u0010\\\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H[0]H\u0080\bø\u0001��¢\u0006\u0002\u0010^\u001a\u001e\u0010_\u001a\u00020\b*\u00020\u00132\u0006\u0010>\u001a\u00020?2\b\b\u0002\u0010`\u001a\u00020\nH��\"\u0014\u0010��\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n��\"\u000e\u0010\u0007\u001a\u00020\bX\u0080T¢\u0006\u0002\n��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006a"}, d2 = {"HEX_DIGIT_BYTES", "", "getHEX_DIGIT_BYTES", "()[B", "OVERFLOW_DIGIT_START", "", "OVERFLOW_ZONE", "SEGMENTING_THRESHOLD", "", "rangeEquals", "", "segment", "Lokio/Segment;", "segmentPos", "bytes", "bytesOffset", "bytesLimit", "commonClear", "", "Lokio/Buffer;", "commonCompleteSegmentByteCount", "commonCopy", "commonCopyTo", "out", "offset", "byteCount", "commonEquals", "other", "", "commonGet", "", "pos", "commonHashCode", "commonIndexOf", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "fromIndex", "toIndex", "Lokio/ByteString;", "commonIndexOfElement", "targetBytes", "commonRangeEquals", "commonRead", "sink", "commonReadAll", "Lokio/Sink;", "commonReadByte", "commonReadByteArray", "commonReadByteString", "commonReadDecimalLong", "commonReadFully", "commonReadHexadecimalUnsignedLong", "commonReadInt", "commonReadLong", "commonReadShort", "", "commonReadUtf8", "", "commonReadUtf8CodePoint", "commonReadUtf8Line", "commonReadUtf8LineStrict", "limit", "commonSelect", "options", "Lokio/Options;", "commonSkip", "commonSnapshot", "commonWritableSegment", "minimumCapacity", "commonWrite", PackageDocumentBase.DCTags.source, "byteString", "Lokio/Source;", "commonWriteAll", "commonWriteByte", "commonWriteDecimalLong", OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT, "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteLong", "commonWriteShort", OperatorName.CLOSE_AND_STROKE, "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "readUtf8Line", "newline", "seek", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "lambda", "Lkotlin/Function2;", "(Lokio/Buffer;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "selectPrefix", "selectTruncated", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/internal/BufferKt.class */
public final class BufferKt {

    @NotNull
    private static final byte[] HEX_DIGIT_BYTES = Platform.asUtf8ToByteArray("0123456789abcdef");
    public static final int SEGMENTING_THRESHOLD = 4096;
    public static final long OVERFLOW_ZONE = -922337203685477580L;
    public static final long OVERFLOW_DIGIT_START = -7;

    @NotNull
    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(@NotNull Segment segment, int segmentPos, @NotNull byte[] bytes, int bytesOffset, int bytesLimit) {
        Intrinsics.checkNotNullParameter(segment, "segment");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Segment segment2 = segment;
        int segmentPos2 = segmentPos;
        int segmentLimit = segment2.limit;
        byte[] data = segment2.data;
        for (int i = bytesOffset; i < bytesLimit; i++) {
            if (segmentPos2 == segmentLimit) {
                Segment segment3 = segment2.next;
                Intrinsics.checkNotNull(segment3);
                segment2 = segment3;
                data = segment2.data;
                segmentPos2 = segment2.pos;
                segmentLimit = segment2.limit;
            }
            if (data[segmentPos2] != bytes[i]) {
                return false;
            }
            segmentPos2++;
        }
        return true;
    }

    @NotNull
    public static final String readUtf8Line(@NotNull Buffer readUtf8Line, long newline) throws EOFException {
        Intrinsics.checkNotNullParameter(readUtf8Line, "$this$readUtf8Line");
        if (newline > 0 && readUtf8Line.getByte(newline - 1) == ((byte) 13)) {
            String result = readUtf8Line.readUtf8(newline - 1);
            readUtf8Line.skip(2L);
            return result;
        }
        String result2 = readUtf8Line.readUtf8(newline);
        readUtf8Line.skip(1L);
        return result2;
    }

    public static final <T> T seek(@NotNull Buffer seek, long fromIndex, @NotNull Function2<? super Segment, ? super Long, ? extends T> lambda) {
        Intrinsics.checkNotNullParameter(seek, "$this$seek");
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        Segment segment = seek.head;
        if (segment == null) {
            return lambda.invoke(null, -1L);
        }
        Segment s = segment;
        if (seek.size() - fromIndex < fromIndex) {
            long size = seek.size();
            while (true) {
                long offset = size;
                if (offset > fromIndex) {
                    Segment segment2 = s.prev;
                    Intrinsics.checkNotNull(segment2);
                    s = segment2;
                    size = offset - (s.limit - s.pos);
                } else {
                    return lambda.invoke(s, Long.valueOf(offset));
                }
            }
        } else {
            long j = 0;
            while (true) {
                long offset2 = j;
                long nextOffset = offset2 + (s.limit - s.pos);
                if (nextOffset <= fromIndex) {
                    Segment segment3 = s.next;
                    Intrinsics.checkNotNull(segment3);
                    s = segment3;
                    j = nextOffset;
                } else {
                    return lambda.invoke(s, Long.valueOf(offset2));
                }
            }
        }
    }

    public static /* synthetic */ int selectPrefix$default(Buffer buffer, Options options, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return selectPrefix(buffer, options, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x019a, code lost:
    
        if (r6 == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x019d, code lost:
    
        return -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01a2, code lost:
    
        return r14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int selectPrefix(@org.jetbrains.annotations.NotNull okio.Buffer r4, @org.jetbrains.annotations.NotNull okio.Options r5, boolean r6) {
        /*
            Method dump skipped, instructions count: 419
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.BufferKt.selectPrefix(okio.Buffer, okio.Options, boolean):int");
    }

    @NotNull
    public static final Buffer commonCopyTo(@NotNull Buffer commonCopyTo, @NotNull Buffer out, long offset, long byteCount) {
        Segment s;
        Intrinsics.checkNotNullParameter(commonCopyTo, "$this$commonCopyTo");
        Intrinsics.checkNotNullParameter(out, "out");
        long offset2 = offset;
        long byteCount2 = byteCount;
        Util.checkOffsetAndCount(commonCopyTo.size(), offset2, byteCount2);
        if (byteCount2 == 0) {
            return commonCopyTo;
        }
        out.setSize$okio(out.size() + byteCount2);
        Segment segment = commonCopyTo.head;
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
            Segment segment2 = s;
            Intrinsics.checkNotNull(segment2);
            Segment copy = segment2.sharedCopy();
            copy.pos += (int) offset2;
            copy.limit = Math.min(copy.pos + ((int) byteCount2), copy.limit);
            if (out.head == null) {
                copy.prev = copy;
                copy.next = copy.prev;
                out.head = copy.next;
            } else {
                Segment segment3 = out.head;
                Intrinsics.checkNotNull(segment3);
                Segment segment4 = segment3.prev;
                Intrinsics.checkNotNull(segment4);
                segment4.push(copy);
            }
            byteCount2 -= copy.limit - copy.pos;
            offset2 = 0;
            s = s.next;
        }
        return commonCopyTo;
    }

    public static final long commonCompleteSegmentByteCount(@NotNull Buffer commonCompleteSegmentByteCount) {
        Intrinsics.checkNotNullParameter(commonCompleteSegmentByteCount, "$this$commonCompleteSegmentByteCount");
        long result = commonCompleteSegmentByteCount.size();
        if (result == 0) {
            return 0L;
        }
        Segment segment = commonCompleteSegmentByteCount.head;
        Intrinsics.checkNotNull(segment);
        Segment tail = segment.prev;
        Intrinsics.checkNotNull(tail);
        if (tail.limit < 8192 && tail.owner) {
            result -= tail.limit - tail.pos;
        }
        return result;
    }

    public static final byte commonReadByte(@NotNull Buffer commonReadByte) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadByte, "$this$commonReadByte");
        if (commonReadByte.size() == 0) {
            throw new EOFException();
        }
        Segment segment = commonReadByte.head;
        Intrinsics.checkNotNull(segment);
        int pos = segment.pos;
        int limit = segment.limit;
        byte[] data = segment.data;
        int pos2 = pos + 1;
        byte b = data[pos];
        commonReadByte.setSize$okio(commonReadByte.size() - 1);
        if (pos2 == limit) {
            commonReadByte.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos2;
        }
        return b;
    }

    public static final short commonReadShort(@NotNull Buffer commonReadShort) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadShort, "$this$commonReadShort");
        if (commonReadShort.size() < 2) {
            throw new EOFException();
        }
        Segment segment = commonReadShort.head;
        Intrinsics.checkNotNull(segment);
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 2) {
            byte $this$and$iv = commonReadShort.readByte();
            byte $this$and$iv2 = commonReadShort.readByte();
            int s = (($this$and$iv & 255) << 8) | ($this$and$iv2 & 255);
            return (short) s;
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        byte $this$and$iv3 = data[pos];
        int pos3 = pos2 + 1;
        byte $this$and$iv4 = data[pos2];
        int s2 = (($this$and$iv3 & 255) << 8) | ($this$and$iv4 & 255);
        commonReadShort.setSize$okio(commonReadShort.size() - 2);
        if (pos3 == limit) {
            commonReadShort.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos3;
        }
        return (short) s2;
    }

    public static final int commonReadInt(@NotNull Buffer commonReadInt) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadInt, "$this$commonReadInt");
        if (commonReadInt.size() < 4) {
            throw new EOFException();
        }
        Segment segment = commonReadInt.head;
        Intrinsics.checkNotNull(segment);
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 4) {
            byte $this$and$iv = commonReadInt.readByte();
            byte $this$and$iv2 = commonReadInt.readByte();
            int i = (($this$and$iv & 255) << 24) | (($this$and$iv2 & 255) << 16);
            byte $this$and$iv3 = commonReadInt.readByte();
            int i2 = i | (($this$and$iv3 & 255) << 8);
            byte $this$and$iv4 = commonReadInt.readByte();
            return i2 | ($this$and$iv4 & 255);
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        byte $this$and$iv5 = data[pos];
        int pos3 = pos2 + 1;
        byte $this$and$iv6 = data[pos2];
        int i3 = (($this$and$iv5 & 255) << 24) | (($this$and$iv6 & 255) << 16);
        int pos4 = pos3 + 1;
        byte $this$and$iv7 = data[pos3];
        int i4 = i3 | (($this$and$iv7 & 255) << 8);
        int pos5 = pos4 + 1;
        byte $this$and$iv8 = data[pos4];
        int i5 = i4 | ($this$and$iv8 & 255);
        commonReadInt.setSize$okio(commonReadInt.size() - 4);
        if (pos5 == limit) {
            commonReadInt.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos5;
        }
        return i5;
    }

    public static final long commonReadLong(@NotNull Buffer commonReadLong) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadLong, "$this$commonReadLong");
        if (commonReadLong.size() < 8) {
            throw new EOFException();
        }
        Segment segment = commonReadLong.head;
        Intrinsics.checkNotNull(segment);
        int pos = segment.pos;
        int limit = segment.limit;
        if (limit - pos < 8) {
            int $this$and$iv = commonReadLong.readInt();
            int $this$and$iv2 = commonReadLong.readInt();
            return (($this$and$iv & 4294967295L) << 32) | ($this$and$iv2 & 4294967295L);
        }
        byte[] data = segment.data;
        int pos2 = pos + 1;
        byte $this$and$iv3 = data[pos];
        int pos3 = pos2 + 1;
        byte $this$and$iv4 = data[pos2];
        long j = (($this$and$iv3 & 255) << 56) | (($this$and$iv4 & 255) << 48);
        int pos4 = pos3 + 1;
        byte $this$and$iv5 = data[pos3];
        long j2 = j | (($this$and$iv5 & 255) << 40);
        int pos5 = pos4 + 1;
        byte $this$and$iv6 = data[pos4];
        long j3 = j2 | (($this$and$iv6 & 255) << 32);
        int pos6 = pos5 + 1;
        byte $this$and$iv7 = data[pos5];
        long j4 = j3 | (($this$and$iv7 & 255) << 24);
        int pos7 = pos6 + 1;
        byte $this$and$iv8 = data[pos6];
        long j5 = j4 | (($this$and$iv8 & 255) << 16);
        int pos8 = pos7 + 1;
        byte $this$and$iv9 = data[pos7];
        long j6 = j5 | (($this$and$iv9 & 255) << 8);
        int pos9 = pos8 + 1;
        byte $this$and$iv10 = data[pos8];
        long v = j6 | ($this$and$iv10 & 255);
        commonReadLong.setSize$okio(commonReadLong.size() - 8);
        if (pos9 == limit) {
            commonReadLong.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = pos9;
        }
        return v;
    }

    public static final byte commonGet(@NotNull Buffer commonGet, long pos) {
        Intrinsics.checkNotNullParameter(commonGet, "$this$commonGet");
        Util.checkOffsetAndCount(commonGet.size(), pos, 1L);
        Segment segment = commonGet.head;
        if (segment == null) {
            Segment s = (Segment) null;
            Intrinsics.checkNotNull(s);
            return s.data[(int) ((s.pos + pos) - (-1))];
        }
        Segment s$iv = segment;
        if (commonGet.size() - pos < pos) {
            long size = commonGet.size();
            while (true) {
                long offset$iv = size;
                if (offset$iv > pos) {
                    Segment segment2 = s$iv.prev;
                    Intrinsics.checkNotNull(segment2);
                    s$iv = segment2;
                    size = offset$iv - (s$iv.limit - s$iv.pos);
                } else {
                    Segment s2 = s$iv;
                    Intrinsics.checkNotNull(s2);
                    return s2.data[(int) ((s2.pos + pos) - offset$iv)];
                }
            }
        } else {
            long j = 0;
            while (true) {
                long offset$iv2 = j;
                long nextOffset$iv = offset$iv2 + (s$iv.limit - s$iv.pos);
                if (nextOffset$iv <= pos) {
                    Segment segment3 = s$iv.next;
                    Intrinsics.checkNotNull(segment3);
                    s$iv = segment3;
                    j = nextOffset$iv;
                } else {
                    Segment s3 = s$iv;
                    Intrinsics.checkNotNull(s3);
                    return s3.data[(int) ((s3.pos + pos) - offset$iv2)];
                }
            }
        }
    }

    public static final void commonClear(@NotNull Buffer commonClear) throws EOFException {
        Intrinsics.checkNotNullParameter(commonClear, "$this$commonClear");
        commonClear.skip(commonClear.size());
    }

    public static final void commonSkip(@NotNull Buffer commonSkip, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(commonSkip, "$this$commonSkip");
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            Segment head = commonSkip.head;
            if (head == null) {
                throw new EOFException();
            }
            int b$iv = head.limit - head.pos;
            int toSkip = (int) Math.min(byteCount2, b$iv);
            commonSkip.setSize$okio(commonSkip.size() - toSkip);
            byteCount2 -= toSkip;
            head.pos += toSkip;
            if (head.pos == head.limit) {
                commonSkip.head = head.pop();
                SegmentPool.recycle(head);
            }
        }
    }

    public static /* synthetic */ Buffer commonWrite$default(Buffer commonWrite, ByteString byteString, int offset, int byteCount, int i, Object obj) {
        if ((i & 2) != 0) {
            offset = 0;
        }
        if ((i & 4) != 0) {
            byteCount = byteString.size();
        }
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(commonWrite, offset, byteCount);
        return commonWrite;
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull ByteString byteString, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.write$okio(commonWrite, offset, byteCount);
        return commonWrite;
    }

    @NotNull
    public static final Buffer commonWriteDecimalLong(@NotNull Buffer commonWriteDecimalLong, long v) {
        int i;
        Intrinsics.checkNotNullParameter(commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        long v2 = v;
        if (v2 == 0) {
            return commonWriteDecimalLong.writeByte(48);
        }
        boolean negative = false;
        if (v2 < 0) {
            v2 = -v2;
            if (v2 < 0) {
                return commonWriteDecimalLong.writeUtf8("-9223372036854775808");
            }
            negative = true;
        }
        if (v2 < 100000000) {
            if (v2 < 10000) {
                if (v2 < 100) {
                    i = v2 < 10 ? 1 : 2;
                } else {
                    i = v2 < 1000 ? 3 : 4;
                }
            } else if (v2 < StaticHandler.DEFAULT_MAX_AVG_SERVE_TIME_NS) {
                i = v2 < 100000 ? 5 : 6;
            } else {
                i = v2 < 10000000 ? 7 : 8;
            }
        } else if (v2 < 1000000000000L) {
            if (v2 < RealConnection.IDLE_CONNECTION_HEALTHY_NS) {
                i = v2 < 1000000000 ? 9 : 10;
            } else {
                i = v2 < 100000000000L ? 11 : 12;
            }
        } else if (v2 < 1000000000000000L) {
            if (v2 < 10000000000000L) {
                i = 13;
            } else {
                i = v2 < 100000000000000L ? 14 : 15;
            }
        } else if (v2 < 100000000000000000L) {
            i = v2 < 10000000000000000L ? 16 : 17;
        } else {
            i = v2 < 1000000000000000000L ? 18 : 19;
        }
        int width = i;
        if (negative) {
            width++;
        }
        Segment tail = commonWriteDecimalLong.writableSegment$okio(width);
        byte[] data = tail.data;
        int pos = tail.limit + width;
        while (v2 != 0) {
            int digit = (int) (v2 % 10);
            pos--;
            data[pos] = getHEX_DIGIT_BYTES()[digit];
            v2 /= 10;
        }
        if (negative) {
            data[pos - 1] = (byte) 45;
        }
        tail.limit += width;
        commonWriteDecimalLong.setSize$okio(commonWriteDecimalLong.size() + width);
        return commonWriteDecimalLong;
    }

    @NotNull
    public static final Buffer commonWriteHexadecimalUnsignedLong(@NotNull Buffer commonWriteHexadecimalUnsignedLong, long v) {
        Intrinsics.checkNotNullParameter(commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        long v2 = v;
        if (v2 == 0) {
            return commonWriteHexadecimalUnsignedLong.writeByte(48);
        }
        long x = v2 | (v2 >>> 1);
        long x2 = x | (x >>> 2);
        long x3 = x2 | (x2 >>> 4);
        long x4 = x3 | (x3 >>> 8);
        long x5 = x4 | (x4 >>> 16);
        long x6 = x5 | (x5 >>> 32);
        long x7 = x6 - ((x6 >>> 1) & 6148914691236517205L);
        long x8 = ((x7 >>> 2) & 3689348814741910323L) + (x7 & 3689348814741910323L);
        long x9 = ((x8 >>> 4) + x8) & 1085102592571150095L;
        long x10 = x9 + (x9 >>> 8);
        long x11 = x10 + (x10 >>> 16);
        int width = (int) ((((x11 & 63) + ((x11 >>> 32) & 63)) + 3) / 4);
        Segment tail = commonWriteHexadecimalUnsignedLong.writableSegment$okio(width);
        byte[] data = tail.data;
        int start = tail.limit;
        for (int pos = (tail.limit + width) - 1; pos >= start; pos--) {
            data[pos] = getHEX_DIGIT_BYTES()[(int) (v2 & 15)];
            v2 >>>= 4;
        }
        tail.limit += width;
        commonWriteHexadecimalUnsignedLong.setSize$okio(commonWriteHexadecimalUnsignedLong.size() + width);
        return commonWriteHexadecimalUnsignedLong;
    }

    @NotNull
    public static final Segment commonWritableSegment(@NotNull Buffer commonWritableSegment, int minimumCapacity) {
        Intrinsics.checkNotNullParameter(commonWritableSegment, "$this$commonWritableSegment");
        if (!(minimumCapacity >= 1 && minimumCapacity <= 8192)) {
            throw new IllegalArgumentException("unexpected capacity".toString());
        }
        if (commonWritableSegment.head == null) {
            Segment result = SegmentPool.take();
            commonWritableSegment.head = result;
            result.prev = result;
            result.next = result;
            return result;
        }
        Segment segment = commonWritableSegment.head;
        Intrinsics.checkNotNull(segment);
        Segment tail = segment.prev;
        Intrinsics.checkNotNull(tail);
        if (tail.limit + minimumCapacity > 8192 || !tail.owner) {
            tail = tail.push(SegmentPool.take());
        }
        return tail;
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull byte[] source) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        return commonWrite.write(source, 0, source.length);
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull byte[] source, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        int offset2 = offset;
        Util.checkOffsetAndCount(source.length, offset2, byteCount);
        int limit = offset2 + byteCount;
        while (offset2 < limit) {
            Segment tail = commonWrite.writableSegment$okio(1);
            int toCopy = Math.min(limit - offset2, 8192 - tail.limit);
            ArraysKt.copyInto(source, tail.data, tail.limit, offset2, offset2 + toCopy);
            offset2 += toCopy;
            tail.limit += toCopy;
        }
        commonWrite.setSize$okio(commonWrite.size() + byteCount);
        return commonWrite;
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer commonReadByteArray) {
        Intrinsics.checkNotNullParameter(commonReadByteArray, "$this$commonReadByteArray");
        return commonReadByteArray.readByteArray(commonReadByteArray.size());
    }

    @NotNull
    public static final byte[] commonReadByteArray(@NotNull Buffer commonReadByteArray, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadByteArray, "$this$commonReadByteArray");
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (commonReadByteArray.size() < byteCount) {
            throw new EOFException();
        }
        byte[] result = new byte[(int) byteCount];
        commonReadByteArray.readFully(result);
        return result;
    }

    public static final int commonRead(@NotNull Buffer commonRead, @NotNull byte[] sink) {
        Intrinsics.checkNotNullParameter(commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink, "sink");
        return commonRead.read(sink, 0, sink.length);
    }

    public static final void commonReadFully(@NotNull Buffer commonReadFully, @NotNull byte[] sink) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i = 0;
        while (true) {
            int offset = i;
            if (offset < sink.length) {
                int read = commonReadFully.read(sink, offset, sink.length - offset);
                if (read == -1) {
                    throw new EOFException();
                }
                i = offset + read;
            } else {
                return;
            }
        }
    }

    public static final int commonRead(@NotNull Buffer commonRead, @NotNull byte[] sink, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink, "sink");
        Util.checkOffsetAndCount(sink.length, offset, byteCount);
        Segment s = commonRead.head;
        if (s == null) {
            return -1;
        }
        int toCopy = Math.min(byteCount, s.limit - s.pos);
        ArraysKt.copyInto(s.data, sink, offset, s.pos, s.pos + toCopy);
        s.pos += toCopy;
        commonRead.setSize$okio(commonRead.size() - toCopy);
        if (s.pos == s.limit) {
            commonRead.head = s.pop();
            SegmentPool.recycle(s);
        }
        return toCopy;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0156 A[EDGE_INSN: B:53:0x0156->B:45:0x0156 BREAK  A[LOOP:0: B:7:0x002e->B:55:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long commonReadDecimalLong(@org.jetbrains.annotations.NotNull okio.Buffer r6) throws java.io.EOFException {
        /*
            Method dump skipped, instructions count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.BufferKt.commonReadDecimalLong(okio.Buffer):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0158 A[EDGE_INSN: B:47:0x0158->B:43:0x0158 BREAK  A[LOOP:0: B:7:0x0026->B:49:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final long commonReadHexadecimalUnsignedLong(@org.jetbrains.annotations.NotNull okio.Buffer r6) throws java.io.EOFException {
        /*
            Method dump skipped, instructions count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.BufferKt.commonReadHexadecimalUnsignedLong(okio.Buffer):long");
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer commonReadByteString) {
        Intrinsics.checkNotNullParameter(commonReadByteString, "$this$commonReadByteString");
        return commonReadByteString.readByteString(commonReadByteString.size());
    }

    @NotNull
    public static final ByteString commonReadByteString(@NotNull Buffer commonReadByteString, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadByteString, "$this$commonReadByteString");
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (commonReadByteString.size() < byteCount) {
            throw new EOFException();
        }
        if (byteCount < 4096) {
            return new ByteString(commonReadByteString.readByteArray(byteCount));
        }
        ByteString byteStringSnapshot = commonReadByteString.snapshot((int) byteCount);
        commonReadByteString.skip(byteCount);
        return byteStringSnapshot;
    }

    public static final int commonSelect(@NotNull Buffer commonSelect, @NotNull Options options) throws EOFException {
        Intrinsics.checkNotNullParameter(commonSelect, "$this$commonSelect");
        Intrinsics.checkNotNullParameter(options, "options");
        int index = selectPrefix$default(commonSelect, options, false, 2, null);
        if (index == -1) {
            return -1;
        }
        int selectedSize = options.getByteStrings$okio()[index].size();
        commonSelect.skip(selectedSize);
        return index;
    }

    public static final void commonReadFully(@NotNull Buffer commonReadFully, @NotNull Buffer sink, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadFully, "$this$commonReadFully");
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (commonReadFully.size() < byteCount) {
            sink.write(commonReadFully, commonReadFully.size());
            throw new EOFException();
        }
        sink.write(commonReadFully, byteCount);
    }

    public static final long commonReadAll(@NotNull Buffer commonReadAll, @NotNull Sink sink) throws IOException {
        Intrinsics.checkNotNullParameter(commonReadAll, "$this$commonReadAll");
        Intrinsics.checkNotNullParameter(sink, "sink");
        long byteCount = commonReadAll.size();
        if (byteCount > 0) {
            sink.write(commonReadAll, byteCount);
        }
        return byteCount;
    }

    @NotNull
    public static final String commonReadUtf8(@NotNull Buffer commonReadUtf8, long byteCount) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadUtf8, "$this$commonReadUtf8");
        if (!(byteCount >= 0 && byteCount <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalArgumentException(("byteCount: " + byteCount).toString());
        }
        if (commonReadUtf8.size() < byteCount) {
            throw new EOFException();
        }
        if (byteCount == 0) {
            return "";
        }
        Segment s = commonReadUtf8.head;
        Intrinsics.checkNotNull(s);
        if (s.pos + byteCount > s.limit) {
            return _Utf8Kt.commonToUtf8String$default(commonReadUtf8.readByteArray(byteCount), 0, 0, 3, null);
        }
        String result = _Utf8Kt.commonToUtf8String(s.data, s.pos, s.pos + ((int) byteCount));
        s.pos += (int) byteCount;
        commonReadUtf8.setSize$okio(commonReadUtf8.size() - byteCount);
        if (s.pos == s.limit) {
            commonReadUtf8.head = s.pop();
            SegmentPool.recycle(s);
        }
        return result;
    }

    @Nullable
    public static final String commonReadUtf8Line(@NotNull Buffer commonReadUtf8Line) {
        Intrinsics.checkNotNullParameter(commonReadUtf8Line, "$this$commonReadUtf8Line");
        long newline = commonReadUtf8Line.indexOf((byte) 10);
        if (newline != -1) {
            return readUtf8Line(commonReadUtf8Line, newline);
        }
        if (commonReadUtf8Line.size() != 0) {
            return commonReadUtf8Line.readUtf8(commonReadUtf8Line.size());
        }
        return null;
    }

    @NotNull
    public static final String commonReadUtf8LineStrict(@NotNull Buffer commonReadUtf8LineStrict, long limit) throws EOFException {
        Intrinsics.checkNotNullParameter(commonReadUtf8LineStrict, "$this$commonReadUtf8LineStrict");
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("limit < 0: " + limit).toString());
        }
        long scanLength = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1;
        long newline = commonReadUtf8LineStrict.indexOf((byte) 10, 0L, scanLength);
        if (newline != -1) {
            return readUtf8Line(commonReadUtf8LineStrict, newline);
        }
        if (scanLength < commonReadUtf8LineStrict.size() && commonReadUtf8LineStrict.getByte(scanLength - 1) == ((byte) 13) && commonReadUtf8LineStrict.getByte(scanLength) == ((byte) 10)) {
            return readUtf8Line(commonReadUtf8LineStrict, scanLength);
        }
        Buffer data = new Buffer();
        long b$iv = commonReadUtf8LineStrict.size();
        commonReadUtf8LineStrict.copyTo(data, 0L, Math.min(32, b$iv));
        throw new EOFException("\\n not found: limit=" + Math.min(commonReadUtf8LineStrict.size(), limit) + " content=" + data.readByteString().hex() + (char) 8230);
    }

    public static final int commonReadUtf8CodePoint(@NotNull Buffer commonReadUtf8CodePoint) throws EOFException {
        int codePoint;
        int byteCount;
        int min;
        Intrinsics.checkNotNullParameter(commonReadUtf8CodePoint, "$this$commonReadUtf8CodePoint");
        if (commonReadUtf8CodePoint.size() == 0) {
            throw new EOFException();
        }
        byte b0 = commonReadUtf8CodePoint.getByte(0L);
        if ((b0 & 128) == 0) {
            codePoint = b0 & 127;
            byteCount = 1;
            min = 0;
        } else if ((b0 & 224) == 192) {
            codePoint = b0 & 31;
            byteCount = 2;
            min = 128;
        } else if ((b0 & 240) == 224) {
            codePoint = b0 & 15;
            byteCount = 3;
            min = 2048;
        } else {
            if ((b0 & 248) != 240) {
                commonReadUtf8CodePoint.skip(1L);
                return 65533;
            }
            codePoint = b0 & 7;
            byteCount = 4;
            min = 65536;
        }
        if (commonReadUtf8CodePoint.size() < byteCount) {
            throw new EOFException("size < " + byteCount + ": " + commonReadUtf8CodePoint.size() + " (to read code point prefixed 0x" + Util.toHexString(b0) + ')');
        }
        int i = byteCount;
        for (int i2 = 1; i2 < i; i2++) {
            byte b = commonReadUtf8CodePoint.getByte(i2);
            if ((b & 192) == 128) {
                codePoint = (codePoint << 6) | (b & 63);
            } else {
                commonReadUtf8CodePoint.skip(i2);
                return 65533;
            }
        }
        commonReadUtf8CodePoint.skip(byteCount);
        if (codePoint > 1114111) {
            return 65533;
        }
        int i3 = codePoint;
        if ((55296 <= i3 && 57343 >= i3) || codePoint < min) {
            return 65533;
        }
        return codePoint;
    }

    @NotNull
    public static final Buffer commonWriteUtf8(@NotNull Buffer commonWriteUtf8, @NotNull String string, int beginIndex, int endIndex) {
        int c;
        Intrinsics.checkNotNullParameter(commonWriteUtf8, "$this$commonWriteUtf8");
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
        int i = beginIndex;
        while (i < endIndex) {
            int c2 = string.charAt(i);
            if (c2 < 128) {
                Segment tail = commonWriteUtf8.writableSegment$okio(1);
                byte[] data = tail.data;
                int segmentOffset = tail.limit - i;
                int runLimit = Math.min(endIndex, 8192 - segmentOffset);
                int i2 = i;
                i++;
                data[segmentOffset + i2] = (byte) c2;
                while (i < runLimit && (c = string.charAt(i)) < 128) {
                    int i3 = i;
                    i++;
                    data[segmentOffset + i3] = (byte) c;
                }
                int runSize = (i + segmentOffset) - tail.limit;
                tail.limit += runSize;
                commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + runSize);
            } else if (c2 < 2048) {
                Segment tail2 = commonWriteUtf8.writableSegment$okio(2);
                tail2.data[tail2.limit] = (byte) ((c2 >> 6) | 192);
                tail2.data[tail2.limit + 1] = (byte) ((c2 & 63) | 128);
                tail2.limit += 2;
                commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 2);
                i++;
            } else if (c2 < 55296 || c2 > 57343) {
                Segment tail3 = commonWriteUtf8.writableSegment$okio(3);
                tail3.data[tail3.limit] = (byte) ((c2 >> 12) | 224);
                tail3.data[tail3.limit + 1] = (byte) (((c2 >> 6) & 63) | 128);
                tail3.data[tail3.limit + 2] = (byte) ((c2 & 63) | 128);
                tail3.limit += 3;
                commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 3);
                i++;
            } else {
                int low = i + 1 < endIndex ? string.charAt(i + 1) : 0;
                if (c2 > 56319 || 56320 > low || 57343 < low) {
                    commonWriteUtf8.writeByte(63);
                    i++;
                } else {
                    int codePoint = 65536 + (((c2 & 1023) << 10) | (low & 1023));
                    Segment tail4 = commonWriteUtf8.writableSegment$okio(4);
                    tail4.data[tail4.limit] = (byte) ((codePoint >> 18) | 240);
                    tail4.data[tail4.limit + 1] = (byte) (((codePoint >> 12) & 63) | 128);
                    tail4.data[tail4.limit + 2] = (byte) (((codePoint >> 6) & 63) | 128);
                    tail4.data[tail4.limit + 3] = (byte) ((codePoint & 63) | 128);
                    tail4.limit += 4;
                    commonWriteUtf8.setSize$okio(commonWriteUtf8.size() + 4);
                    i += 2;
                }
            }
        }
        return commonWriteUtf8;
    }

    @NotNull
    public static final Buffer commonWriteUtf8CodePoint(@NotNull Buffer commonWriteUtf8CodePoint, int codePoint) {
        Intrinsics.checkNotNullParameter(commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        if (codePoint < 128) {
            commonWriteUtf8CodePoint.writeByte(codePoint);
        } else if (codePoint < 2048) {
            Segment tail = commonWriteUtf8CodePoint.writableSegment$okio(2);
            tail.data[tail.limit] = (byte) ((codePoint >> 6) | 192);
            tail.data[tail.limit + 1] = (byte) ((codePoint & 63) | 128);
            tail.limit += 2;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 2);
        } else if (55296 <= codePoint && 57343 >= codePoint) {
            commonWriteUtf8CodePoint.writeByte(63);
        } else if (codePoint < 65536) {
            Segment tail2 = commonWriteUtf8CodePoint.writableSegment$okio(3);
            tail2.data[tail2.limit] = (byte) ((codePoint >> 12) | 224);
            tail2.data[tail2.limit + 1] = (byte) (((codePoint >> 6) & 63) | 128);
            tail2.data[tail2.limit + 2] = (byte) ((codePoint & 63) | 128);
            tail2.limit += 3;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 3);
        } else if (codePoint <= 1114111) {
            Segment tail3 = commonWriteUtf8CodePoint.writableSegment$okio(4);
            tail3.data[tail3.limit] = (byte) ((codePoint >> 18) | 240);
            tail3.data[tail3.limit + 1] = (byte) (((codePoint >> 12) & 63) | 128);
            tail3.data[tail3.limit + 2] = (byte) (((codePoint >> 6) & 63) | 128);
            tail3.data[tail3.limit + 3] = (byte) ((codePoint & 63) | 128);
            tail3.limit += 4;
            commonWriteUtf8CodePoint.setSize$okio(commonWriteUtf8CodePoint.size() + 4);
        } else {
            throw new IllegalArgumentException("Unexpected code point: 0x" + Util.toHexString(codePoint));
        }
        return commonWriteUtf8CodePoint;
    }

    public static final long commonWriteAll(@NotNull Buffer commonWriteAll, @NotNull Source source) throws IOException {
        Intrinsics.checkNotNullParameter(commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkNotNullParameter(source, "source");
        long j = 0;
        while (true) {
            long totalBytesRead = j;
            long readCount = source.read(commonWriteAll, 8192);
            if (readCount != -1) {
                j = totalBytesRead + readCount;
            } else {
                return totalBytesRead;
            }
        }
    }

    @NotNull
    public static final Buffer commonWrite(@NotNull Buffer commonWrite, @NotNull Source source, long byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        long j = byteCount;
        while (true) {
            long byteCount2 = j;
            if (byteCount2 > 0) {
                long read = source.read(commonWrite, byteCount2);
                if (read == -1) {
                    throw new EOFException();
                }
                j = byteCount2 - read;
            } else {
                return commonWrite;
            }
        }
    }

    @NotNull
    public static final Buffer commonWriteByte(@NotNull Buffer commonWriteByte, int b) {
        Intrinsics.checkNotNullParameter(commonWriteByte, "$this$commonWriteByte");
        Segment tail = commonWriteByte.writableSegment$okio(1);
        byte[] bArr = tail.data;
        int i = tail.limit;
        tail.limit = i + 1;
        bArr[i] = (byte) b;
        commonWriteByte.setSize$okio(commonWriteByte.size() + 1);
        return commonWriteByte;
    }

    @NotNull
    public static final Buffer commonWriteShort(@NotNull Buffer commonWriteShort, int s) {
        Intrinsics.checkNotNullParameter(commonWriteShort, "$this$commonWriteShort");
        Segment tail = commonWriteShort.writableSegment$okio(2);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((s >>> 8) & 255);
        data[limit2] = (byte) (s & 255);
        tail.limit = limit2 + 1;
        commonWriteShort.setSize$okio(commonWriteShort.size() + 2);
        return commonWriteShort;
    }

    @NotNull
    public static final Buffer commonWriteInt(@NotNull Buffer commonWriteInt, int i) {
        Intrinsics.checkNotNullParameter(commonWriteInt, "$this$commonWriteInt");
        Segment tail = commonWriteInt.writableSegment$okio(4);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((i >>> 24) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((i >>> 16) & 255);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((i >>> 8) & 255);
        data[limit4] = (byte) (i & 255);
        tail.limit = limit4 + 1;
        commonWriteInt.setSize$okio(commonWriteInt.size() + 4);
        return commonWriteInt;
    }

    @NotNull
    public static final Buffer commonWriteLong(@NotNull Buffer commonWriteLong, long v) {
        Intrinsics.checkNotNullParameter(commonWriteLong, "$this$commonWriteLong");
        Segment tail = commonWriteLong.writableSegment$okio(8);
        byte[] data = tail.data;
        int limit = tail.limit;
        int limit2 = limit + 1;
        data[limit] = (byte) ((v >>> 56) & 255);
        int limit3 = limit2 + 1;
        data[limit2] = (byte) ((v >>> 48) & 255);
        int limit4 = limit3 + 1;
        data[limit3] = (byte) ((v >>> 40) & 255);
        int limit5 = limit4 + 1;
        data[limit4] = (byte) ((v >>> 32) & 255);
        int limit6 = limit5 + 1;
        data[limit5] = (byte) ((v >>> 24) & 255);
        int limit7 = limit6 + 1;
        data[limit6] = (byte) ((v >>> 16) & 255);
        int limit8 = limit7 + 1;
        data[limit7] = (byte) ((v >>> 8) & 255);
        data[limit8] = (byte) (v & 255);
        tail.limit = limit8 + 1;
        commonWriteLong.setSize$okio(commonWriteLong.size() + 8);
        return commonWriteLong;
    }

    public static final void commonWrite(@NotNull Buffer commonWrite, @NotNull Buffer source, long byteCount) {
        Segment segment;
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(source, "source");
        long byteCount2 = byteCount;
        if (!(source != commonWrite)) {
            throw new IllegalArgumentException("source == this".toString());
        }
        Util.checkOffsetAndCount(source.size(), 0L, byteCount2);
        while (byteCount2 > 0) {
            long j = byteCount2;
            Segment segment2 = source.head;
            Intrinsics.checkNotNull(segment2);
            int i = segment2.limit;
            Intrinsics.checkNotNull(source.head);
            if (j < i - r2.pos) {
                if (commonWrite.head != null) {
                    Segment segment3 = commonWrite.head;
                    Intrinsics.checkNotNull(segment3);
                    segment = segment3.prev;
                } else {
                    segment = null;
                }
                Segment tail = segment;
                if (tail != null && tail.owner) {
                    if ((byteCount2 + tail.limit) - (tail.shared ? 0 : tail.pos) <= 8192) {
                        Segment segment4 = source.head;
                        Intrinsics.checkNotNull(segment4);
                        segment4.writeTo(tail, (int) byteCount2);
                        source.setSize$okio(source.size() - byteCount2);
                        commonWrite.setSize$okio(commonWrite.size() + byteCount2);
                        return;
                    }
                }
                Segment segment5 = source.head;
                Intrinsics.checkNotNull(segment5);
                source.head = segment5.split((int) byteCount2);
            }
            Segment segmentToMove = source.head;
            Intrinsics.checkNotNull(segmentToMove);
            long movedByteCount = segmentToMove.limit - segmentToMove.pos;
            source.head = segmentToMove.pop();
            if (commonWrite.head == null) {
                commonWrite.head = segmentToMove;
                segmentToMove.prev = segmentToMove;
                segmentToMove.next = segmentToMove.prev;
            } else {
                Segment segment6 = commonWrite.head;
                Intrinsics.checkNotNull(segment6);
                Segment tail2 = segment6.prev;
                Intrinsics.checkNotNull(tail2);
                tail2.push(segmentToMove).compact();
            }
            source.setSize$okio(source.size() - movedByteCount);
            commonWrite.setSize$okio(commonWrite.size() + movedByteCount);
            byteCount2 -= movedByteCount;
        }
    }

    public static final long commonRead(@NotNull Buffer commonRead, @NotNull Buffer sink, long byteCount) {
        Intrinsics.checkNotNullParameter(commonRead, "$this$commonRead");
        Intrinsics.checkNotNullParameter(sink, "sink");
        long byteCount2 = byteCount;
        if (!(byteCount2 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + byteCount2).toString());
        }
        if (commonRead.size() == 0) {
            return -1L;
        }
        if (byteCount2 > commonRead.size()) {
            byteCount2 = commonRead.size();
        }
        sink.write(commonRead, byteCount2);
        return byteCount2;
    }

    public static final long commonIndexOf(@NotNull Buffer commonIndexOf, byte b, long fromIndex, long toIndex) {
        long offset$iv;
        long offset$iv2;
        Intrinsics.checkNotNullParameter(commonIndexOf, "$this$commonIndexOf");
        long fromIndex2 = fromIndex;
        long toIndex2 = toIndex;
        if (!(0 <= fromIndex2 && toIndex2 >= fromIndex2)) {
            throw new IllegalArgumentException(("size=" + commonIndexOf.size() + " fromIndex=" + fromIndex2 + " toIndex=" + toIndex2).toString());
        }
        if (toIndex2 > commonIndexOf.size()) {
            toIndex2 = commonIndexOf.size();
        }
        if (fromIndex2 == toIndex2) {
            return -1L;
        }
        Segment segment = commonIndexOf.head;
        if (segment == null) {
            return -1L;
        }
        Segment s$iv = segment;
        if (commonIndexOf.size() - fromIndex2 < fromIndex2) {
            long size = commonIndexOf.size();
            while (true) {
                offset$iv2 = size;
                if (offset$iv2 <= fromIndex2) {
                    break;
                }
                Segment segment2 = s$iv.prev;
                Intrinsics.checkNotNull(segment2);
                s$iv = segment2;
                size = offset$iv2 - (s$iv.limit - s$iv.pos);
            }
            Segment s = s$iv;
            if (s == null) {
                return -1L;
            }
            Segment s2 = s;
            long offset = offset$iv2;
            while (offset < toIndex2) {
                byte[] data = s2.data;
                int limit = (int) Math.min(s2.limit, (s2.pos + toIndex2) - offset);
                for (int pos = (int) ((s2.pos + fromIndex2) - offset); pos < limit; pos++) {
                    if (data[pos] == b) {
                        return (pos - s2.pos) + offset;
                    }
                }
                offset += s2.limit - s2.pos;
                fromIndex2 = offset;
                Segment segment3 = s2.next;
                Intrinsics.checkNotNull(segment3);
                s2 = segment3;
            }
            return -1L;
        }
        long j = 0;
        while (true) {
            offset$iv = j;
            long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
            if (nextOffset$iv > fromIndex2) {
                break;
            }
            Segment segment4 = s$iv.next;
            Intrinsics.checkNotNull(segment4);
            s$iv = segment4;
            j = nextOffset$iv;
        }
        Segment s3 = s$iv;
        if (s3 == null) {
            return -1L;
        }
        Segment s4 = s3;
        long offset2 = offset$iv;
        while (offset2 < toIndex2) {
            byte[] data2 = s4.data;
            int limit2 = (int) Math.min(s4.limit, (s4.pos + toIndex2) - offset2);
            for (int pos2 = (int) ((s4.pos + fromIndex2) - offset2); pos2 < limit2; pos2++) {
                if (data2[pos2] == b) {
                    return (pos2 - s4.pos) + offset2;
                }
            }
            offset2 += s4.limit - s4.pos;
            fromIndex2 = offset2;
            Segment segment5 = s4.next;
            Intrinsics.checkNotNull(segment5);
            s4 = segment5;
        }
        return -1L;
    }

    public static final long commonIndexOf(@NotNull Buffer commonIndexOf, @NotNull ByteString bytes, long fromIndex) {
        long offset$iv;
        long offset$iv2;
        Intrinsics.checkNotNullParameter(commonIndexOf, "$this$commonIndexOf");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        long fromIndex2 = fromIndex;
        if (!(bytes.size() > 0)) {
            throw new IllegalArgumentException("bytes is empty".toString());
        }
        if (!(fromIndex2 >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + fromIndex2).toString());
        }
        Segment segment = commonIndexOf.head;
        if (segment == null) {
            return -1L;
        }
        Segment s$iv = segment;
        if (commonIndexOf.size() - fromIndex2 < fromIndex2) {
            long size = commonIndexOf.size();
            while (true) {
                offset$iv2 = size;
                if (offset$iv2 <= fromIndex2) {
                    break;
                }
                Segment segment2 = s$iv.prev;
                Intrinsics.checkNotNull(segment2);
                s$iv = segment2;
                size = offset$iv2 - (s$iv.limit - s$iv.pos);
            }
            Segment s = s$iv;
            if (s == null) {
                return -1L;
            }
            Segment s2 = s;
            long offset = offset$iv2;
            byte[] targetByteArray = bytes.internalArray$okio();
            byte b0 = targetByteArray[0];
            int bytesSize = bytes.size();
            long resultLimit = (commonIndexOf.size() - bytesSize) + 1;
            while (offset < resultLimit) {
                byte[] data = s2.data;
                int a$iv = s2.limit;
                long b$iv = (s2.pos + resultLimit) - offset;
                int segmentLimit = (int) Math.min(a$iv, b$iv);
                for (int pos = (int) ((s2.pos + fromIndex2) - offset); pos < segmentLimit; pos++) {
                    if (data[pos] == b0 && rangeEquals(s2, pos + 1, targetByteArray, 1, bytesSize)) {
                        return (pos - s2.pos) + offset;
                    }
                }
                offset += s2.limit - s2.pos;
                fromIndex2 = offset;
                Segment segment3 = s2.next;
                Intrinsics.checkNotNull(segment3);
                s2 = segment3;
            }
            return -1L;
        }
        long j = 0;
        while (true) {
            offset$iv = j;
            long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
            if (nextOffset$iv > fromIndex2) {
                break;
            }
            Segment segment4 = s$iv.next;
            Intrinsics.checkNotNull(segment4);
            s$iv = segment4;
            j = nextOffset$iv;
        }
        Segment s3 = s$iv;
        if (s3 == null) {
            return -1L;
        }
        Segment s4 = s3;
        long offset2 = offset$iv;
        byte[] targetByteArray2 = bytes.internalArray$okio();
        byte b02 = targetByteArray2[0];
        int bytesSize2 = bytes.size();
        long resultLimit2 = (commonIndexOf.size() - bytesSize2) + 1;
        while (offset2 < resultLimit2) {
            byte[] data2 = s4.data;
            int a$iv2 = s4.limit;
            long b$iv2 = (s4.pos + resultLimit2) - offset2;
            int segmentLimit2 = (int) Math.min(a$iv2, b$iv2);
            for (int pos2 = (int) ((s4.pos + fromIndex2) - offset2); pos2 < segmentLimit2; pos2++) {
                if (data2[pos2] == b02 && rangeEquals(s4, pos2 + 1, targetByteArray2, 1, bytesSize2)) {
                    return (pos2 - s4.pos) + offset2;
                }
            }
            offset2 += s4.limit - s4.pos;
            fromIndex2 = offset2;
            Segment segment5 = s4.next;
            Intrinsics.checkNotNull(segment5);
            s4 = segment5;
        }
        return -1L;
    }

    public static final long commonIndexOfElement(@NotNull Buffer commonIndexOfElement, @NotNull ByteString targetBytes, long fromIndex) {
        long offset$iv;
        long offset$iv2;
        Intrinsics.checkNotNullParameter(commonIndexOfElement, "$this$commonIndexOfElement");
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long fromIndex2 = fromIndex;
        if (!(fromIndex2 >= 0)) {
            throw new IllegalArgumentException(("fromIndex < 0: " + fromIndex2).toString());
        }
        Segment segment = commonIndexOfElement.head;
        if (segment == null) {
            return -1L;
        }
        Segment s$iv = segment;
        if (commonIndexOfElement.size() - fromIndex2 < fromIndex2) {
            long size = commonIndexOfElement.size();
            while (true) {
                offset$iv2 = size;
                if (offset$iv2 <= fromIndex2) {
                    break;
                }
                Segment segment2 = s$iv.prev;
                Intrinsics.checkNotNull(segment2);
                s$iv = segment2;
                size = offset$iv2 - (s$iv.limit - s$iv.pos);
            }
            Segment s = s$iv;
            if (s == null) {
                return -1L;
            }
            Segment s2 = s;
            long offset = offset$iv2;
            if (targetBytes.size() == 2) {
                byte b0 = targetBytes.getByte(0);
                byte b1 = targetBytes.getByte(1);
                while (offset < commonIndexOfElement.size()) {
                    byte[] data = s2.data;
                    int limit = s2.limit;
                    for (int pos = (int) ((s2.pos + fromIndex2) - offset); pos < limit; pos++) {
                        byte b = data[pos];
                        if (b == b0 || b == b1) {
                            return (pos - s2.pos) + offset;
                        }
                    }
                    offset += s2.limit - s2.pos;
                    fromIndex2 = offset;
                    Segment segment3 = s2.next;
                    Intrinsics.checkNotNull(segment3);
                    s2 = segment3;
                }
                return -1L;
            }
            byte[] targetByteArray = targetBytes.internalArray$okio();
            while (offset < commonIndexOfElement.size()) {
                byte[] data2 = s2.data;
                int limit2 = s2.limit;
                for (int pos2 = (int) ((s2.pos + fromIndex2) - offset); pos2 < limit2; pos2++) {
                    byte b2 = data2[pos2];
                    for (byte t : targetByteArray) {
                        if (b2 == t) {
                            return (pos2 - s2.pos) + offset;
                        }
                    }
                }
                offset += s2.limit - s2.pos;
                fromIndex2 = offset;
                Segment segment4 = s2.next;
                Intrinsics.checkNotNull(segment4);
                s2 = segment4;
            }
            return -1L;
        }
        long j = 0;
        while (true) {
            offset$iv = j;
            long nextOffset$iv = offset$iv + (s$iv.limit - s$iv.pos);
            if (nextOffset$iv > fromIndex2) {
                break;
            }
            Segment segment5 = s$iv.next;
            Intrinsics.checkNotNull(segment5);
            s$iv = segment5;
            j = nextOffset$iv;
        }
        Segment s3 = s$iv;
        if (s3 == null) {
            return -1L;
        }
        Segment s4 = s3;
        long offset2 = offset$iv;
        if (targetBytes.size() == 2) {
            byte b02 = targetBytes.getByte(0);
            byte b12 = targetBytes.getByte(1);
            while (offset2 < commonIndexOfElement.size()) {
                byte[] data3 = s4.data;
                int limit3 = s4.limit;
                for (int pos3 = (int) ((s4.pos + fromIndex2) - offset2); pos3 < limit3; pos3++) {
                    byte b3 = data3[pos3];
                    if (b3 == b02 || b3 == b12) {
                        return (pos3 - s4.pos) + offset2;
                    }
                }
                offset2 += s4.limit - s4.pos;
                fromIndex2 = offset2;
                Segment segment6 = s4.next;
                Intrinsics.checkNotNull(segment6);
                s4 = segment6;
            }
            return -1L;
        }
        byte[] targetByteArray2 = targetBytes.internalArray$okio();
        while (offset2 < commonIndexOfElement.size()) {
            byte[] data4 = s4.data;
            int limit4 = s4.limit;
            for (int pos4 = (int) ((s4.pos + fromIndex2) - offset2); pos4 < limit4; pos4++) {
                byte b4 = data4[pos4];
                for (byte t2 : targetByteArray2) {
                    if (b4 == t2) {
                        return (pos4 - s4.pos) + offset2;
                    }
                }
            }
            offset2 += s4.limit - s4.pos;
            fromIndex2 = offset2;
            Segment segment7 = s4.next;
            Intrinsics.checkNotNull(segment7);
            s4 = segment7;
        }
        return -1L;
    }

    public static final boolean commonRangeEquals(@NotNull Buffer commonRangeEquals, long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (offset < 0 || bytesOffset < 0 || byteCount < 0 || commonRangeEquals.size() - offset < byteCount || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i = 0; i < byteCount; i++) {
            if (commonRangeEquals.getByte(offset + i) != bytes.getByte(bytesOffset + i)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean commonEquals(@NotNull Buffer commonEquals, @Nullable Object other) {
        Intrinsics.checkNotNullParameter(commonEquals, "$this$commonEquals");
        if (commonEquals == other) {
            return true;
        }
        if (!(other instanceof Buffer) || commonEquals.size() != ((Buffer) other).size()) {
            return false;
        }
        if (commonEquals.size() == 0) {
            return true;
        }
        Segment segment = commonEquals.head;
        Intrinsics.checkNotNull(segment);
        Segment sa = segment;
        Segment segment2 = ((Buffer) other).head;
        Intrinsics.checkNotNull(segment2);
        Segment sb = segment2;
        int posA = sa.pos;
        int posB = sb.pos;
        long pos = 0;
        while (pos < commonEquals.size()) {
            long count = Math.min(sa.limit - posA, sb.limit - posB);
            for (long i = 0; i < count; i++) {
                int i2 = posA;
                posA++;
                int i3 = posB;
                posB++;
                if (sa.data[i2] != sb.data[i3]) {
                    return false;
                }
            }
            if (posA == sa.limit) {
                Segment segment3 = sa.next;
                Intrinsics.checkNotNull(segment3);
                sa = segment3;
                posA = sa.pos;
            }
            if (posB == sb.limit) {
                Segment segment4 = sb.next;
                Intrinsics.checkNotNull(segment4);
                sb = segment4;
                posB = sb.pos;
            }
            pos += count;
        }
        return true;
    }

    public static final int commonHashCode(@NotNull Buffer commonHashCode) {
        Intrinsics.checkNotNullParameter(commonHashCode, "$this$commonHashCode");
        Segment segment = commonHashCode.head;
        if (segment == null) {
            return 0;
        }
        Segment s = segment;
        int result = 1;
        do {
            int limit = s.limit;
            for (int pos = s.pos; pos < limit; pos++) {
                result = (31 * result) + s.data[pos];
            }
            Segment segment2 = s.next;
            Intrinsics.checkNotNull(segment2);
            s = segment2;
        } while (s != commonHashCode.head);
        return result;
    }

    @NotNull
    public static final Buffer commonCopy(@NotNull Buffer commonCopy) {
        Intrinsics.checkNotNullParameter(commonCopy, "$this$commonCopy");
        Buffer result = new Buffer();
        if (commonCopy.size() == 0) {
            return result;
        }
        Segment head = commonCopy.head;
        Intrinsics.checkNotNull(head);
        Segment headCopy = head.sharedCopy();
        result.head = headCopy;
        headCopy.prev = result.head;
        headCopy.next = headCopy.prev;
        Segment segment = head.next;
        while (true) {
            Segment s = segment;
            if (s != head) {
                Segment segment2 = headCopy.prev;
                Intrinsics.checkNotNull(segment2);
                Intrinsics.checkNotNull(s);
                segment2.push(s.sharedCopy());
                segment = s.next;
            } else {
                result.setSize$okio(commonCopy.size());
                return result;
            }
        }
    }

    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer commonSnapshot) {
        Intrinsics.checkNotNullParameter(commonSnapshot, "$this$commonSnapshot");
        if (!(commonSnapshot.size() <= ((long) Integer.MAX_VALUE))) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + commonSnapshot.size()).toString());
        }
        return commonSnapshot.snapshot((int) commonSnapshot.size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final ByteString commonSnapshot(@NotNull Buffer commonSnapshot, int byteCount) {
        Intrinsics.checkNotNullParameter(commonSnapshot, "$this$commonSnapshot");
        if (byteCount == 0) {
            return ByteString.EMPTY;
        }
        Util.checkOffsetAndCount(commonSnapshot.size(), 0L, byteCount);
        int offset = 0;
        int segmentCount = 0;
        Segment segment = commonSnapshot.head;
        while (true) {
            Segment s = segment;
            if (offset < byteCount) {
                Intrinsics.checkNotNull(s);
                if (s.limit == s.pos) {
                    throw new AssertionError("s.limit == s.pos");
                }
                offset += s.limit - s.pos;
                segmentCount++;
                segment = s.next;
            } else {
                byte[] bArr = new byte[segmentCount];
                int[] directory = new int[segmentCount * 2];
                int offset2 = 0;
                int segmentCount2 = 0;
                Segment segment2 = commonSnapshot.head;
                while (true) {
                    Segment s2 = segment2;
                    if (offset2 < byteCount) {
                        Intrinsics.checkNotNull(s2);
                        bArr[segmentCount2] = s2.data;
                        offset2 += s2.limit - s2.pos;
                        directory[segmentCount2] = Math.min(offset2, byteCount);
                        directory[segmentCount2 + ((Object[]) bArr).length] = s2.pos;
                        s2.shared = true;
                        segmentCount2++;
                        segment2 = s2.next;
                    } else {
                        return new SegmentedByteString((byte[][]) bArr, directory);
                    }
                }
            }
        }
    }
}
