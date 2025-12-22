package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

/* compiled from: UNumbers.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��&\n��\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b)\u001a\u0017\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001��¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010��\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001��¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010��\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001��¢\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010��\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001��¢\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001��¢\u0006\u0004\b\u000f\u0010\u0004\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001��¢\u0006\u0004\b\u0010\u0010\u0007\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001��¢\u0006\u0004\b\u0011\u0010\n\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001��¢\u0006\u0004\b\u0012\u0010\r\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001��¢\u0006\u0004\b\u0014\u0010\u0004\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u0005H\u0087\bø\u0001��¢\u0006\u0004\b\u0015\u0010\u0007\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\bH\u0087\bø\u0001��¢\u0006\u0004\b\u0016\u0010\n\u001a\u0017\u0010\u0013\u001a\u00020\u0001*\u00020\u000bH\u0087\bø\u0001��¢\u0006\u0004\b\u0017\u0010\r\u001a\u001f\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u0018\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001f\u0010\u0018\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u0018\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b \u0010!\u001a\u001f\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b#\u0010\u001b\u001a\u001f\u0010\"\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b$\u0010\u001d\u001a\u001f\u0010\"\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b%\u0010\u001f\u001a\u001f\u0010\"\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\bø\u0001��¢\u0006\u0004\b&\u0010!\u001a\u0017\u0010'\u001a\u00020\u0002*\u00020\u0002H\u0087\bø\u0001��¢\u0006\u0004\b(\u0010)\u001a\u0017\u0010'\u001a\u00020\u0005*\u00020\u0005H\u0087\bø\u0001��¢\u0006\u0004\b*\u0010\u0007\u001a\u0017\u0010'\u001a\u00020\b*\u00020\bH\u0087\bø\u0001��¢\u0006\u0004\b+\u0010,\u001a\u0017\u0010'\u001a\u00020\u000b*\u00020\u000bH\u0087\bø\u0001��¢\u0006\u0004\b-\u0010.\u001a\u0017\u0010/\u001a\u00020\u0002*\u00020\u0002H\u0087\bø\u0001��¢\u0006\u0004\b0\u0010)\u001a\u0017\u0010/\u001a\u00020\u0005*\u00020\u0005H\u0087\bø\u0001��¢\u0006\u0004\b1\u0010\u0007\u001a\u0017\u0010/\u001a\u00020\b*\u00020\bH\u0087\bø\u0001��¢\u0006\u0004\b2\u0010,\u001a\u0017\u0010/\u001a\u00020\u000b*\u00020\u000bH\u0087\bø\u0001��¢\u0006\u0004\b3\u0010.\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"countLeadingZeroBits", "", "Lkotlin/UByte;", "countLeadingZeroBits-7apg3OU", "(B)I", "Lkotlin/UInt;", "countLeadingZeroBits-WZ4Q5Ns", "(I)I", "Lkotlin/ULong;", "countLeadingZeroBits-VKZWuLQ", "(J)I", "Lkotlin/UShort;", "countLeadingZeroBits-xj2QHRw", "(S)I", "countOneBits", "countOneBits-7apg3OU", "countOneBits-WZ4Q5Ns", "countOneBits-VKZWuLQ", "countOneBits-xj2QHRw", "countTrailingZeroBits", "countTrailingZeroBits-7apg3OU", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits-VKZWuLQ", "countTrailingZeroBits-xj2QHRw", "rotateLeft", "bitCount", "rotateLeft-LxnNnR4", "(BI)B", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft-JSWoG40", "(JI)J", "rotateLeft-olVBNx4", "(SI)S", "rotateRight", "rotateRight-LxnNnR4", "rotateRight-V7xB4Y4", "rotateRight-JSWoG40", "rotateRight-olVBNx4", "takeHighestOneBit", "takeHighestOneBit-7apg3OU", "(B)B", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit", "takeLowestOneBit-7apg3OU", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit-VKZWuLQ", "takeLowestOneBit-xj2QHRw", "kotlin-stdlib"})
@JvmName(name = "UNumbersKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/UNumbersKt.class */
public final class UNumbersKt {
    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countOneBits-WZ4Q5Ns, reason: not valid java name */
    private static final int m2350countOneBitsWZ4Q5Ns(int $this$countOneBits) {
        return Integer.bitCount($this$countOneBits);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countLeadingZeroBits-WZ4Q5Ns, reason: not valid java name */
    private static final int m2351countLeadingZeroBitsWZ4Q5Ns(int $this$countLeadingZeroBits) {
        return Integer.numberOfLeadingZeros($this$countLeadingZeroBits);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countTrailingZeroBits-WZ4Q5Ns, reason: not valid java name */
    private static final int m2352countTrailingZeroBitsWZ4Q5Ns(int $this$countTrailingZeroBits) {
        return Integer.numberOfTrailingZeros($this$countTrailingZeroBits);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeHighestOneBit-WZ4Q5Ns, reason: not valid java name */
    private static final int m2353takeHighestOneBitWZ4Q5Ns(int $this$takeHighestOneBit) {
        return UInt.m2244constructorimpl(Integer.highestOneBit($this$takeHighestOneBit));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeLowestOneBit-WZ4Q5Ns, reason: not valid java name */
    private static final int m2354takeLowestOneBitWZ4Q5Ns(int $this$takeLowestOneBit) {
        return UInt.m2244constructorimpl(Integer.lowestOneBit($this$takeLowestOneBit));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateLeft-V7xB4Y4, reason: not valid java name */
    private static final int m2355rotateLeftV7xB4Y4(int $this$rotateLeft, int bitCount) {
        return UInt.m2244constructorimpl(Integer.rotateLeft($this$rotateLeft, bitCount));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateRight-V7xB4Y4, reason: not valid java name */
    private static final int m2356rotateRightV7xB4Y4(int $this$rotateRight, int bitCount) {
        return UInt.m2244constructorimpl(Integer.rotateRight($this$rotateRight, bitCount));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countOneBits-VKZWuLQ, reason: not valid java name */
    private static final int m2357countOneBitsVKZWuLQ(long $this$countOneBits) {
        return Long.bitCount($this$countOneBits);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countLeadingZeroBits-VKZWuLQ, reason: not valid java name */
    private static final int m2358countLeadingZeroBitsVKZWuLQ(long $this$countLeadingZeroBits) {
        return Long.numberOfLeadingZeros($this$countLeadingZeroBits);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countTrailingZeroBits-VKZWuLQ, reason: not valid java name */
    private static final int m2359countTrailingZeroBitsVKZWuLQ(long $this$countTrailingZeroBits) {
        return Long.numberOfTrailingZeros($this$countTrailingZeroBits);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeHighestOneBit-VKZWuLQ, reason: not valid java name */
    private static final long m2360takeHighestOneBitVKZWuLQ(long $this$takeHighestOneBit) {
        return ULong.m2323constructorimpl(Long.highestOneBit($this$takeHighestOneBit));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeLowestOneBit-VKZWuLQ, reason: not valid java name */
    private static final long m2361takeLowestOneBitVKZWuLQ(long $this$takeLowestOneBit) {
        return ULong.m2323constructorimpl(Long.lowestOneBit($this$takeLowestOneBit));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateLeft-JSWoG40, reason: not valid java name */
    private static final long m2362rotateLeftJSWoG40(long $this$rotateLeft, int bitCount) {
        return ULong.m2323constructorimpl(Long.rotateLeft($this$rotateLeft, bitCount));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateRight-JSWoG40, reason: not valid java name */
    private static final long m2363rotateRightJSWoG40(long $this$rotateRight, int bitCount) {
        return ULong.m2323constructorimpl(Long.rotateRight($this$rotateRight, bitCount));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countOneBits-7apg3OU, reason: not valid java name */
    private static final int m2364countOneBits7apg3OU(byte $this$countOneBits) {
        return Integer.bitCount(UInt.m2244constructorimpl($this$countOneBits & 255));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countLeadingZeroBits-7apg3OU, reason: not valid java name */
    private static final int m2365countLeadingZeroBits7apg3OU(byte $this$countLeadingZeroBits) {
        return Integer.numberOfLeadingZeros($this$countLeadingZeroBits & 255) - 24;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countTrailingZeroBits-7apg3OU, reason: not valid java name */
    private static final int m2366countTrailingZeroBits7apg3OU(byte $this$countTrailingZeroBits) {
        return Integer.numberOfTrailingZeros($this$countTrailingZeroBits | 256);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeHighestOneBit-7apg3OU, reason: not valid java name */
    private static final byte m2367takeHighestOneBit7apg3OU(byte $this$takeHighestOneBit) {
        return UByte.m2165constructorimpl((byte) Integer.highestOneBit($this$takeHighestOneBit & 255));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeLowestOneBit-7apg3OU, reason: not valid java name */
    private static final byte m2368takeLowestOneBit7apg3OU(byte $this$takeLowestOneBit) {
        return UByte.m2165constructorimpl((byte) Integer.lowestOneBit($this$takeLowestOneBit & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateLeft-LxnNnR4, reason: not valid java name */
    private static final byte m2369rotateLeftLxnNnR4(byte $this$rotateLeft, int bitCount) {
        return UByte.m2165constructorimpl(NumbersKt.rotateLeft($this$rotateLeft, bitCount));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateRight-LxnNnR4, reason: not valid java name */
    private static final byte m2370rotateRightLxnNnR4(byte $this$rotateRight, int bitCount) {
        return UByte.m2165constructorimpl(NumbersKt.rotateRight($this$rotateRight, bitCount));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countOneBits-xj2QHRw, reason: not valid java name */
    private static final int m2371countOneBitsxj2QHRw(short $this$countOneBits) {
        return Integer.bitCount(UInt.m2244constructorimpl($this$countOneBits & 65535));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countLeadingZeroBits-xj2QHRw, reason: not valid java name */
    private static final int m2372countLeadingZeroBitsxj2QHRw(short $this$countLeadingZeroBits) {
        return Integer.numberOfLeadingZeros($this$countLeadingZeroBits & 65535) - 16;
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: countTrailingZeroBits-xj2QHRw, reason: not valid java name */
    private static final int m2373countTrailingZeroBitsxj2QHRw(short $this$countTrailingZeroBits) {
        return Integer.numberOfTrailingZeros($this$countTrailingZeroBits | 65536);
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeHighestOneBit-xj2QHRw, reason: not valid java name */
    private static final short m2374takeHighestOneBitxj2QHRw(short $this$takeHighestOneBit) {
        return UShort.m2429constructorimpl((short) Integer.highestOneBit($this$takeHighestOneBit & 65535));
    }

    @SinceKotlin(version = "1.5")
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class, ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: takeLowestOneBit-xj2QHRw, reason: not valid java name */
    private static final short m2375takeLowestOneBitxj2QHRw(short $this$takeLowestOneBit) {
        return UShort.m2429constructorimpl((short) Integer.lowestOneBit($this$takeLowestOneBit & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateLeft-olVBNx4, reason: not valid java name */
    private static final short m2376rotateLeftolVBNx4(short $this$rotateLeft, int bitCount) {
        return UShort.m2429constructorimpl(NumbersKt.rotateLeft($this$rotateLeft, bitCount));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
    /* renamed from: rotateRight-olVBNx4, reason: not valid java name */
    private static final short m2377rotateRightolVBNx4(short $this$rotateRight, int bitCount) {
        return UShort.m2429constructorimpl(NumbersKt.rotateRight($this$rotateRight, bitCount));
    }
}
