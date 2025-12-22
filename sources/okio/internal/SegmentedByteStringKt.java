package okio.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.Segment;
import okio.SegmentedByteString;
import okio.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SegmentedByteString.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"��R\n��\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H��\u001a\u0017\u0010\u0006\u001a\u00020\u0007*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0080\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\bH\u0080\b\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a-\u0010\u0010\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a\u001d\u0010\u0016\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001H\u0080\b\u001a\r\u0010\u0019\u001a\u00020\u0012*\u00020\bH\u0080\b\u001a%\u0010\u001a\u001a\u00020\u001b*\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0080\b\u001a]\u0010\u001e\u001a\u00020\u001b*\u00020\b2K\u0010\u001f\u001aG\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001b0 H\u0080\bø\u0001��\u001aj\u0010\u001e\u001a\u00020\u001b*\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012K\u0010\u001f\u001aG\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b!\u0012\b\b\"\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u001b0 H\u0082\b\u001a\u0014\u0010$\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0001H��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006%"}, d2 = {"binarySearch", "", "", "value", "fromIndex", "toIndex", "commonEquals", "", "Lokio/SegmentedByteString;", "other", "", "commonGetSize", "commonHashCode", "commonInternalGet", "", "pos", "commonRangeEquals", "offset", "", "otherOffset", "byteCount", "Lokio/ByteString;", "commonSubstring", "beginIndex", "endIndex", "commonToByteArray", "commonWrite", "", "buffer", "Lokio/Buffer;", "forEachSegment", "action", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "data", "segment", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/internal/SegmentedByteStringKt.class */
public final class SegmentedByteStringKt {
    public static final int binarySearch(@NotNull int[] binarySearch, int value, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        int left = fromIndex;
        int right = toIndex - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = binarySearch[mid];
            if (midVal < value) {
                left = mid + 1;
            } else {
                if (midVal <= value) {
                    return mid;
                }
                right = mid - 1;
            }
        }
        return (-left) - 1;
    }

    public static final int segment(@NotNull SegmentedByteString segment, int pos) {
        Intrinsics.checkNotNullParameter(segment, "$this$segment");
        int i = binarySearch(segment.getDirectory$okio(), pos + 1, 0, segment.getSegments$okio().length);
        return i >= 0 ? i : i ^ (-1);
    }

    public static final void forEachSegment(@NotNull SegmentedByteString forEachSegment, @NotNull Function3<? super byte[], ? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachSegment, "$this$forEachSegment");
        Intrinsics.checkNotNullParameter(action, "action");
        int segmentCount = forEachSegment.getSegments$okio().length;
        int pos = 0;
        for (int s = 0; s < segmentCount; s++) {
            int segmentPos = forEachSegment.getDirectory$okio()[segmentCount + s];
            int nextSegmentOffset = forEachSegment.getDirectory$okio()[s];
            action.invoke(forEachSegment.getSegments$okio()[s], Integer.valueOf(segmentPos), Integer.valueOf(nextSegmentOffset - pos));
            pos = nextSegmentOffset;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void forEachSegment(SegmentedByteString $this$forEachSegment, int beginIndex, int endIndex, Function3<? super byte[], ? super Integer, ? super Integer, Unit> function3) {
        int s = segment($this$forEachSegment, beginIndex);
        int pos = beginIndex;
        while (pos < endIndex) {
            int segmentOffset = s == 0 ? 0 : $this$forEachSegment.getDirectory$okio()[s - 1];
            int segmentSize = $this$forEachSegment.getDirectory$okio()[s] - segmentOffset;
            int segmentPos = $this$forEachSegment.getDirectory$okio()[$this$forEachSegment.getSegments$okio().length + s];
            int byteCount = Math.min(endIndex, segmentOffset + segmentSize) - pos;
            int offset = segmentPos + (pos - segmentOffset);
            function3.invoke($this$forEachSegment.getSegments$okio()[s], Integer.valueOf(offset), Integer.valueOf(byteCount));
            pos += byteCount;
            s++;
        }
    }

    @NotNull
    public static final ByteString commonSubstring(@NotNull SegmentedByteString commonSubstring, int beginIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(commonSubstring, "$this$commonSubstring");
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException(("beginIndex=" + beginIndex + " < 0").toString());
        }
        if (!(endIndex <= commonSubstring.size())) {
            throw new IllegalArgumentException(("endIndex=" + endIndex + " > length(" + commonSubstring.size() + ')').toString());
        }
        int subLen = endIndex - beginIndex;
        if (!(subLen >= 0)) {
            throw new IllegalArgumentException(("endIndex=" + endIndex + " < beginIndex=" + beginIndex).toString());
        }
        if (beginIndex == 0 && endIndex == commonSubstring.size()) {
            return commonSubstring;
        }
        if (beginIndex == endIndex) {
            return ByteString.EMPTY;
        }
        int beginSegment = segment(commonSubstring, beginIndex);
        int endSegment = segment(commonSubstring, endIndex - 1);
        byte[][] newSegments = (byte[][]) ArraysKt.copyOfRange(commonSubstring.getSegments$okio(), beginSegment, endSegment + 1);
        int[] newDirectory = new int[newSegments.length * 2];
        int index = 0;
        int s = beginSegment;
        if (s <= endSegment) {
            while (true) {
                newDirectory[index] = Math.min(commonSubstring.getDirectory$okio()[s] - beginIndex, subLen);
                int i = index;
                index++;
                newDirectory[i + newSegments.length] = commonSubstring.getDirectory$okio()[s + commonSubstring.getSegments$okio().length];
                if (s == endSegment) {
                    break;
                }
                s++;
            }
        }
        int segmentOffset = beginSegment == 0 ? 0 : commonSubstring.getDirectory$okio()[beginSegment - 1];
        int length = newSegments.length;
        newDirectory[length] = newDirectory[length] + (beginIndex - segmentOffset);
        return new SegmentedByteString(newSegments, newDirectory);
    }

    public static final byte commonInternalGet(@NotNull SegmentedByteString commonInternalGet, int pos) {
        Intrinsics.checkNotNullParameter(commonInternalGet, "$this$commonInternalGet");
        Util.checkOffsetAndCount(commonInternalGet.getDirectory$okio()[commonInternalGet.getSegments$okio().length - 1], pos, 1L);
        int segment = segment(commonInternalGet, pos);
        int segmentOffset = segment == 0 ? 0 : commonInternalGet.getDirectory$okio()[segment - 1];
        int segmentPos = commonInternalGet.getDirectory$okio()[segment + commonInternalGet.getSegments$okio().length];
        return commonInternalGet.getSegments$okio()[segment][(pos - segmentOffset) + segmentPos];
    }

    public static final int commonGetSize(@NotNull SegmentedByteString commonGetSize) {
        Intrinsics.checkNotNullParameter(commonGetSize, "$this$commonGetSize");
        return commonGetSize.getDirectory$okio()[commonGetSize.getSegments$okio().length - 1];
    }

    @NotNull
    public static final byte[] commonToByteArray(@NotNull SegmentedByteString commonToByteArray) {
        Intrinsics.checkNotNullParameter(commonToByteArray, "$this$commonToByteArray");
        byte[] result = new byte[commonToByteArray.size()];
        int resultPos = 0;
        int segmentCount$iv = commonToByteArray.getSegments$okio().length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = commonToByteArray.getDirectory$okio()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = commonToByteArray.getDirectory$okio()[s$iv];
            byte[] data = commonToByteArray.getSegments$okio()[s$iv];
            int byteCount = nextSegmentOffset$iv - pos$iv;
            ArraysKt.copyInto(data, result, resultPos, segmentPos$iv, segmentPos$iv + byteCount);
            resultPos += byteCount;
            pos$iv = nextSegmentOffset$iv;
        }
        return result;
    }

    public static final void commonWrite(@NotNull SegmentedByteString commonWrite, @NotNull Buffer buffer, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonWrite, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int endIndex$iv = offset + byteCount;
        int s$iv = segment(commonWrite, offset);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            int segmentOffset$iv = s$iv == 0 ? 0 : commonWrite.getDirectory$okio()[s$iv - 1];
            int segmentSize$iv = commonWrite.getDirectory$okio()[s$iv] - segmentOffset$iv;
            int segmentPos$iv = commonWrite.getDirectory$okio()[commonWrite.getSegments$okio().length + s$iv];
            int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
            int offset$iv = segmentPos$iv + (pos$iv - segmentOffset$iv);
            byte[] data = commonWrite.getSegments$okio()[s$iv];
            Segment segment = new Segment(data, offset$iv, offset$iv + byteCount$iv, true, false);
            if (buffer.head == null) {
                segment.prev = segment;
                segment.next = segment.prev;
                buffer.head = segment.next;
            } else {
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                Intrinsics.checkNotNull(segment3);
                segment3.push(segment);
            }
            pos$iv += byteCount$iv;
            s$iv++;
        }
        buffer.setSize$okio(buffer.size() + commonWrite.size());
    }

    public static final boolean commonRangeEquals(@NotNull SegmentedByteString commonRangeEquals, int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > commonRangeEquals.size() - byteCount) {
            return false;
        }
        int otherOffset2 = otherOffset;
        int endIndex$iv = offset + byteCount;
        int s$iv = segment(commonRangeEquals, offset);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            int segmentOffset$iv = s$iv == 0 ? 0 : commonRangeEquals.getDirectory$okio()[s$iv - 1];
            int segmentSize$iv = commonRangeEquals.getDirectory$okio()[s$iv] - segmentOffset$iv;
            int segmentPos$iv = commonRangeEquals.getDirectory$okio()[commonRangeEquals.getSegments$okio().length + s$iv];
            int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
            int offset$iv = segmentPos$iv + (pos$iv - segmentOffset$iv);
            byte[] data = commonRangeEquals.getSegments$okio()[s$iv];
            if (!other.rangeEquals(otherOffset2, data, offset$iv, byteCount$iv)) {
                return false;
            }
            otherOffset2 += byteCount$iv;
            pos$iv += byteCount$iv;
            s$iv++;
        }
        return true;
    }

    public static final boolean commonRangeEquals(@NotNull SegmentedByteString commonRangeEquals, int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(commonRangeEquals, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(other, "other");
        if (offset < 0 || offset > commonRangeEquals.size() - byteCount || otherOffset < 0 || otherOffset > other.length - byteCount) {
            return false;
        }
        int otherOffset2 = otherOffset;
        int endIndex$iv = offset + byteCount;
        int s$iv = segment(commonRangeEquals, offset);
        int pos$iv = offset;
        while (pos$iv < endIndex$iv) {
            int segmentOffset$iv = s$iv == 0 ? 0 : commonRangeEquals.getDirectory$okio()[s$iv - 1];
            int segmentSize$iv = commonRangeEquals.getDirectory$okio()[s$iv] - segmentOffset$iv;
            int segmentPos$iv = commonRangeEquals.getDirectory$okio()[commonRangeEquals.getSegments$okio().length + s$iv];
            int byteCount$iv = Math.min(endIndex$iv, segmentOffset$iv + segmentSize$iv) - pos$iv;
            int offset$iv = segmentPos$iv + (pos$iv - segmentOffset$iv);
            byte[] data = commonRangeEquals.getSegments$okio()[s$iv];
            if (!Util.arrayRangeEquals(data, offset$iv, other, otherOffset2, byteCount$iv)) {
                return false;
            }
            otherOffset2 += byteCount$iv;
            pos$iv += byteCount$iv;
            s$iv++;
        }
        return true;
    }

    public static final boolean commonEquals(@NotNull SegmentedByteString commonEquals, @Nullable Object other) {
        Intrinsics.checkNotNullParameter(commonEquals, "$this$commonEquals");
        if (other == commonEquals) {
            return true;
        }
        return (other instanceof ByteString) && ((ByteString) other).size() == commonEquals.size() && commonEquals.rangeEquals(0, (ByteString) other, 0, commonEquals.size());
    }

    public static final int commonHashCode(@NotNull SegmentedByteString commonHashCode) {
        Intrinsics.checkNotNullParameter(commonHashCode, "$this$commonHashCode");
        int result = commonHashCode.getHashCode$okio();
        if (result != 0) {
            return result;
        }
        int result2 = 1;
        int segmentCount$iv = commonHashCode.getSegments$okio().length;
        int pos$iv = 0;
        for (int s$iv = 0; s$iv < segmentCount$iv; s$iv++) {
            int segmentPos$iv = commonHashCode.getDirectory$okio()[segmentCount$iv + s$iv];
            int nextSegmentOffset$iv = commonHashCode.getDirectory$okio()[s$iv];
            byte[] data = commonHashCode.getSegments$okio()[s$iv];
            int byteCount = nextSegmentOffset$iv - pos$iv;
            int limit = segmentPos$iv + byteCount;
            for (int i = segmentPos$iv; i < limit; i++) {
                result2 = (31 * result2) + data[i];
            }
            pos$iv = nextSegmentOffset$iv;
        }
        commonHashCode.setHashCode$okio(result2);
        return result2;
    }
}
