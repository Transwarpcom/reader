package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import okhttp3.internal.ws.WebSocketProtocol;
import org.jetbrains.annotations.NotNull;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: UShort.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018�� t2\b\u0012\u0004\u0012\u00020��0\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\fø\u0001��¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001��¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020��H\u0097\nø\u0001��¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020��H\u0087\nø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001��¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001��¢\u0006\u0004\b'\u0010\u0010J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001��¢\u0006\u0004\b(\u0010\u0013J\u001b\u0010&\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001��¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020��H\u0087\bø\u0001��¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020��H\u0087\nø\u0001��ø\u0001\u0001¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020��H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001��¢\u0006\u0004\b3\u0010\u0010J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\b4\u0010\u0013J\u001b\u00102\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001��¢\u0006\u0004\b8\u00109J\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001��¢\u0006\u0004\b:\u0010\u0013J\u001b\u00107\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001��¢\u0006\u0004\b;\u0010\u001fJ\u001b\u00107\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\bø\u0001��¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\fø\u0001��¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001��¢\u0006\u0004\b@\u0010\u0010J\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\bA\u0010\u0013J\u001b\u0010?\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001��¢\u0006\u0004\bI\u0010\u0010J\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\bJ\u0010\u0013J\u001b\u0010H\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001��¢\u0006\u0004\bN\u0010\u0010J\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001��¢\u0006\u0004\bO\u0010\u0013J\u001b\u0010M\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001��¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020��H\u0087\nø\u0001��¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020SH\u0087\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020WH\u0087\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020\rH\u0087\b¢\u0006\u0004\b_\u0010-J\u0010\u0010`\u001a\u00020aH\u0087\b¢\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\be\u0010\u0005J\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u000eH\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bk\u0010UJ\u0016\u0010l\u001a\u00020\u0011H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0014H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bo\u0010cJ\u0016\u0010p\u001a\u00020��H\u0087\bø\u0001��ø\u0001\u0001¢\u0006\u0004\bq\u0010\u0005J\u001b\u0010r\u001a\u00020��2\u0006\u0010\t\u001a\u00020��H\u0087\fø\u0001��¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(SLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", IdentityNamingStrategy.HASH_CODE_KEY, "hashCode-impl", "(S)I", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(SB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"})
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/UShort.class */
public final class UShort implements Comparable<UShort> {
    private final short data;
    public static final short MIN_VALUE = 0;
    public static final short MAX_VALUE = -1;
    public static final int SIZE_BYTES = 2;
    public static final int SIZE_BITS = 16;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private int m2378compareToxj2QHRw(short s) {
        return m2380compareToxj2QHRw(this.data, s);
    }

    @NotNull
    public String toString() {
        return m2428toStringimpl(this.data);
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShort m2430boximpl(short v) {
        return new UShort(v);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2431hashCodeimpl(short s) {
        return s;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2432equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).m2434unboximpl();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2433equalsimpl0(short p1, short p2) {
        return p1 == p2;
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short m2434unboximpl() {
        return this.data;
    }

    public int hashCode() {
        return m2431hashCodeimpl(this.data);
    }

    public boolean equals(Object obj) {
        return m2432equalsimpl(this.data, obj);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return m2378compareToxj2QHRw(uShort.m2434unboximpl());
    }

    @PublishedApi
    private /* synthetic */ UShort(short data) {
        this.data = data;
    }

    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static short m2429constructorimpl(short data) {
        return data;
    }

    /* compiled from: UShort.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001��ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001��ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/UShort$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m2379compareTo7apg3OU(short $this, byte other) {
        return Intrinsics.compare($this & 65535, other & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static int m2380compareToxj2QHRw(short $this, short other) {
        return Intrinsics.compare($this & 65535, other & 65535);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m2381compareToWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.uintCompare(UInt.m2244constructorimpl($this & 65535), other);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m2382compareToVKZWuLQ(short $this, long other) {
        return UnsignedKt.ulongCompare(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m2383plus7apg3OU(short $this, byte other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) + UInt.m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m2384plusxj2QHRw(short $this, short other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) + UInt.m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2385plusWZ4Q5Ns(short $this, int other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) + other);
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m2386plusVKZWuLQ(short $this, long other) {
        return ULong.m2323constructorimpl(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX) + other);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m2387minus7apg3OU(short $this, byte other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) - UInt.m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m2388minusxj2QHRw(short $this, short other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) - UInt.m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m2389minusWZ4Q5Ns(short $this, int other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) - other);
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m2390minusVKZWuLQ(short $this, long other) {
        return ULong.m2323constructorimpl(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX) - other);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m2391times7apg3OU(short $this, byte other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) * UInt.m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m2392timesxj2QHRw(short $this, short other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) * UInt.m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m2393timesWZ4Q5Ns(short $this, int other) {
        return UInt.m2244constructorimpl(UInt.m2244constructorimpl($this & 65535) * other);
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m2394timesVKZWuLQ(short $this, long other) {
        return ULong.m2323constructorimpl(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX) * other);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m2395div7apg3OU(short $this, byte other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m2396divxj2QHRw(short $this, short other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m2397divWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU(UInt.m2244constructorimpl($this & 65535), other);
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m2398divVKZWuLQ(short $this, long other) {
        return UnsignedKt.m2458ulongDivideeb3DHEI(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m2399rem7apg3OU(short $this, byte other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m2400remxj2QHRw(short $this, short other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m2401remWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU(UInt.m2244constructorimpl($this & 65535), other);
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m2402remVKZWuLQ(short $this, long other) {
        return UnsignedKt.m2459ulongRemaindereb3DHEI(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m2403floorDiv7apg3OU(short $this, byte other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m2404floorDivxj2QHRw(short $this, short other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 65535));
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m2405floorDivWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.m2456uintDivideJ1ME1BU(UInt.m2244constructorimpl($this & 65535), other);
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m2406floorDivVKZWuLQ(short $this, long other) {
        return UnsignedKt.m2458ulongDivideeb3DHEI(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m2407mod7apg3OU(short $this, byte other) {
        return UByte.m2165constructorimpl((byte) UnsignedKt.m2457uintRemainderJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 255)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m2408modxj2QHRw(short $this, short other) {
        return m2429constructorimpl((short) UnsignedKt.m2457uintRemainderJ1ME1BU(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 65535)));
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m2409modWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.m2457uintRemainderJ1ME1BU(UInt.m2244constructorimpl($this & 65535), other);
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m2410modVKZWuLQ(short $this, long other) {
        return UnsignedKt.m2459ulongRemaindereb3DHEI(ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    @InlineOnly
    /* renamed from: inc-Mh2AYeg, reason: not valid java name */
    private static final short m2411incMh2AYeg(short $this) {
        return m2429constructorimpl((short) ($this + 1));
    }

    @InlineOnly
    /* renamed from: dec-Mh2AYeg, reason: not valid java name */
    private static final short m2412decMh2AYeg(short $this) {
        return m2429constructorimpl((short) ($this - 1));
    }

    @InlineOnly
    /* renamed from: rangeTo-xj2QHRw, reason: not valid java name */
    private static final UIntRange m2413rangeToxj2QHRw(short $this, short other) {
        return new UIntRange(UInt.m2244constructorimpl($this & 65535), UInt.m2244constructorimpl(other & 65535), null);
    }

    @InlineOnly
    /* renamed from: and-xj2QHRw, reason: not valid java name */
    private static final short m2414andxj2QHRw(short $this, short other) {
        return m2429constructorimpl((short) ($this & other));
    }

    @InlineOnly
    /* renamed from: or-xj2QHRw, reason: not valid java name */
    private static final short m2415orxj2QHRw(short $this, short other) {
        return m2429constructorimpl((short) ($this | other));
    }

    @InlineOnly
    /* renamed from: xor-xj2QHRw, reason: not valid java name */
    private static final short m2416xorxj2QHRw(short $this, short other) {
        return m2429constructorimpl((short) ($this ^ other));
    }

    @InlineOnly
    /* renamed from: inv-Mh2AYeg, reason: not valid java name */
    private static final short m2417invMh2AYeg(short $this) {
        return m2429constructorimpl((short) ($this ^ (-1)));
    }

    @InlineOnly
    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m2418toByteimpl(short $this) {
        return (byte) $this;
    }

    @InlineOnly
    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m2419toShortimpl(short $this) {
        return $this;
    }

    @InlineOnly
    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m2420toIntimpl(short $this) {
        return $this & 65535;
    }

    @InlineOnly
    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m2421toLongimpl(short $this) {
        return $this & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m2422toUBytew2LRezQ(short $this) {
        return UByte.m2165constructorimpl((byte) $this);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m2423toUShortMh2AYeg(short $this) {
        return $this;
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m2424toUIntpVg5ArA(short $this) {
        return UInt.m2244constructorimpl($this & 65535);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m2425toULongsVKNKU(short $this) {
        return ULong.m2323constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    @InlineOnly
    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m2426toFloatimpl(short $this) {
        return $this & 65535;
    }

    @InlineOnly
    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m2427toDoubleimpl(short $this) {
        return $this & 65535;
    }

    @NotNull
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2428toStringimpl(short $this) {
        return String.valueOf($this & 65535);
    }
}
