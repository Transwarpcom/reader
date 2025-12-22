package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: UInt.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018�� y2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001yB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\fø\u0001��¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001��¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020��H\u0097\nø\u0001��¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020��H\u0087\nø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001��¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001��¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\bø\u0001��¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001��¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001��¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020��H\u0087\nø\u0001��ø\u0001\u0001¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020��H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001��¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001��¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\bø\u0001��¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001��¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001��¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\fø\u0001��¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001��¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001��¢\u0006\u0004\bG\u0010\u000fJ\u001b\u0010F\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010F\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\bI\u0010\u001dJ\u001b\u0010F\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\bJ\u0010\u0016J\u001e\u0010K\u001a\u00020��2\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001��ø\u0001\u0001¢\u0006\u0004\bM\u0010\u000bJ\u001e\u0010N\u001a\u00020��2\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001��ø\u0001\u0001¢\u0006\u0004\bO\u0010\u000bJ\u001b\u0010P\u001a\u00020��2\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001��¢\u0006\u0004\bQ\u0010\u000fJ\u001b\u0010P\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bR\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\bS\u0010\u001dJ\u001b\u0010P\u001a\u00020��2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\bT\u0010\u0016J\u0010\u0010U\u001a\u00020VH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020ZH\u0087\b¢\u0006\u0004\b[\u0010\\J\u0010\u0010]\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0016\u0010o\u001a\u00020\rH\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bp\u0010XJ\u0016\u0010q\u001a\u00020��H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\br\u0010\u0005J\u0016\u0010s\u001a\u00020\u0011H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bt\u0010fJ\u0016\u0010u\u001a\u00020\u0014H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bv\u0010jJ\u001b\u0010w\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\fø\u0001��¢\u0006\u0004\bx\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006z"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", IdentityNamingStrategy.HASH_CODE_KEY, "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"})
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/UInt.class */
public final class UInt implements Comparable<UInt> {
    private final int data;
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = -1;
    public static final int SIZE_BYTES = 4;
    public static final int SIZE_BITS = 32;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private int m2191compareToWZ4Q5Ns(int i) {
        return m2194compareToWZ4Q5Ns(this.data, i);
    }

    @NotNull
    public String toString() {
        return m2243toStringimpl(this.data);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UInt m2245boximpl(int v) {
        return new UInt(v);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2246hashCodeimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2247equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).m2249unboximpl();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2248equalsimpl0(int p1, int p2) {
        return p1 == p2;
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2249unboximpl() {
        return this.data;
    }

    public int hashCode() {
        return m2246hashCodeimpl(this.data);
    }

    public boolean equals(Object obj) {
        return m2247equalsimpl(this.data, obj);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return m2191compareToWZ4Q5Ns(uInt.m2249unboximpl());
    }

    @PublishedApi
    private /* synthetic */ UInt(int data) {
        this.data = data;
    }

    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m2244constructorimpl(int data) {
        return data;
    }

    /* compiled from: UInt.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001��ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001��ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/UInt$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m2192compareTo7apg3OU(int $this, byte other) {
        return UnsignedKt.uintCompare($this, m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m2193compareToxj2QHRw(int $this, short other) {
        return UnsignedKt.uintCompare($this, m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static int m2194compareToWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.uintCompare($this, other);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m2195compareToVKZWuLQ(int $this, long other) {
        return UnsignedKt.ulongCompare(ULong.m2323constructorimpl($this & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m2196plus7apg3OU(int $this, byte other) {
        return m2244constructorimpl($this + m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m2197plusxj2QHRw(int $this, short other) {
        return m2244constructorimpl($this + m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2198plusWZ4Q5Ns(int $this, int other) {
        return m2244constructorimpl($this + other);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m2199plusVKZWuLQ(int $this, long other) {
        return ULong.m2323constructorimpl(ULong.m2323constructorimpl($this & 4294967295L) + other);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m2200minus7apg3OU(int $this, byte other) {
        return m2244constructorimpl($this - m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m2201minusxj2QHRw(int $this, short other) {
        return m2244constructorimpl($this - m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2202minusWZ4Q5Ns(int $this, int other) {
        return m2244constructorimpl($this - other);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2203minusVKZWuLQ(int $this, long other) {
        return ULong.m2323constructorimpl(ULong.m2323constructorimpl($this & 4294967295L) - other);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m2204times7apg3OU(int $this, byte other) {
        return m2244constructorimpl($this * m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m2205timesxj2QHRw(int $this, short other) {
        return m2244constructorimpl($this * m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m2206timesWZ4Q5Ns(int $this, int other) {
        return m2244constructorimpl($this * other);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m2207timesVKZWuLQ(int $this, long other) {
        return ULong.m2323constructorimpl(ULong.m2323constructorimpl($this & 4294967295L) * other);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m2208div7apg3OU(int $this, byte other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU($this, m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m2209divxj2QHRw(int $this, short other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU($this, m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m2210divWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU($this, other);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2211divVKZWuLQ(int $this, long other) {
        return UnsignedKt.m2458ulongDivideeb3DHEI(ULong.m2323constructorimpl($this & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m2212rem7apg3OU(int $this, byte other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU($this, m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m2213remxj2QHRw(int $this, short other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU($this, m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m2214remWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU($this, other);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m2215remVKZWuLQ(int $this, long other) {
        return UnsignedKt.m2459ulongRemaindereb3DHEI(ULong.m2323constructorimpl($this & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m2216floorDiv7apg3OU(int $this, byte other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU($this, m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m2217floorDivxj2QHRw(int $this, short other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU($this, m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m2218floorDivWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU($this, other);
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2219floorDivVKZWuLQ(int $this, long other) {
        return UnsignedKt.m2458ulongDivideeb3DHEI(ULong.m2323constructorimpl($this & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m2220mod7apg3OU(int $this, byte other) {
        return UByte.m2165constructorimpl((byte) UnsignedKt.m2457uintRemainderJ1ME1BU($this, m2244constructorimpl(other & 255)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m2221modxj2QHRw(int $this, short other) {
        return UShort.m2429constructorimpl((short) UnsignedKt.m2457uintRemainderJ1ME1BU($this, m2244constructorimpl(other & 65535)));
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m2222modWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU($this, other);
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m2223modVKZWuLQ(int $this, long other) {
        return UnsignedKt.m2459ulongRemaindereb3DHEI(ULong.m2323constructorimpl($this & 4294967295L), other);
    }

    @InlineOnly
    /* renamed from: inc-pVg5ArA, reason: not valid java name */
    private static final int m2224incpVg5ArA(int $this) {
        return m2244constructorimpl($this + 1);
    }

    @InlineOnly
    /* renamed from: dec-pVg5ArA, reason: not valid java name */
    private static final int m2225decpVg5ArA(int $this) {
        return m2244constructorimpl($this - 1);
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns, reason: not valid java name */
    private static final UIntRange m2226rangeToWZ4Q5Ns(int $this, int other) {
        return new UIntRange($this, other, null);
    }

    @InlineOnly
    /* renamed from: shl-pVg5ArA, reason: not valid java name */
    private static final int m2227shlpVg5ArA(int $this, int bitCount) {
        return m2244constructorimpl($this << bitCount);
    }

    @InlineOnly
    /* renamed from: shr-pVg5ArA, reason: not valid java name */
    private static final int m2228shrpVg5ArA(int $this, int bitCount) {
        return m2244constructorimpl($this >>> bitCount);
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns, reason: not valid java name */
    private static final int m2229andWZ4Q5Ns(int $this, int other) {
        return m2244constructorimpl($this & other);
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns, reason: not valid java name */
    private static final int m2230orWZ4Q5Ns(int $this, int other) {
        return m2244constructorimpl($this | other);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns, reason: not valid java name */
    private static final int m2231xorWZ4Q5Ns(int $this, int other) {
        return m2244constructorimpl($this ^ other);
    }

    @InlineOnly
    /* renamed from: inv-pVg5ArA, reason: not valid java name */
    private static final int m2232invpVg5ArA(int $this) {
        return m2244constructorimpl($this ^ (-1));
    }

    @InlineOnly
    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m2233toByteimpl(int $this) {
        return (byte) $this;
    }

    @InlineOnly
    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m2234toShortimpl(int $this) {
        return (short) $this;
    }

    @InlineOnly
    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m2235toIntimpl(int $this) {
        return $this;
    }

    @InlineOnly
    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m2236toLongimpl(int $this) {
        return $this & 4294967295L;
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m2237toUBytew2LRezQ(int $this) {
        return UByte.m2165constructorimpl((byte) $this);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m2238toUShortMh2AYeg(int $this) {
        return UShort.m2429constructorimpl((short) $this);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m2239toUIntpVg5ArA(int $this) {
        return $this;
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m2240toULongsVKNKU(int $this) {
        return ULong.m2323constructorimpl($this & 4294967295L);
    }

    @InlineOnly
    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m2241toFloatimpl(int $this) {
        return (float) UnsignedKt.uintToDouble($this);
    }

    @InlineOnly
    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m2242toDoubleimpl(int $this) {
        return UnsignedKt.uintToDouble($this);
    }

    @NotNull
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2243toStringimpl(int $this) {
        return String.valueOf($this & 4294967295L);
    }
}
