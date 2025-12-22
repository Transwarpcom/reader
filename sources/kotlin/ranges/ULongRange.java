package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: ULongRange.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018�� \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001��¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001��¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001��ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001��ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"}, d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-s-VKNKU", "()J", "getStart-s-VKNKU", "contains", "", "value", "contains-VKZWuLQ", "(J)Z", "equals", "other", "", IdentityNamingStrategy.HASH_CODE_KEY, "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/ranges/ULongRange.class */
public final class ULongRange extends ULongProgression implements ClosedRange<ULong> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final ULongRange EMPTY = new ULongRange(-1, 0);

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getStart() {
        return ULong.m2324boximpl(m3393getStartsVKNKU());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getEndInclusive() {
        return ULong.m2324boximpl(m3394getEndInclusivesVKNKU());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return m3395containsVKZWuLQ(((ULong) comparable).m2328unboximpl());
    }

    private ULongRange(long start, long endInclusive) {
        super(start, endInclusive, 1L, null);
    }

    public /* synthetic */ ULongRange(long start, long endInclusive, DefaultConstructorMarker $constructor_marker) {
        this(start, endInclusive);
    }

    /* renamed from: getStart-s-VKNKU, reason: not valid java name */
    public long m3393getStartsVKNKU() {
        return m3389getFirstsVKNKU();
    }

    /* renamed from: getEndInclusive-s-VKNKU, reason: not valid java name */
    public long m3394getEndInclusivesVKNKU() {
        return m3390getLastsVKNKU();
    }

    /* renamed from: contains-VKZWuLQ, reason: not valid java name */
    public boolean m3395containsVKZWuLQ(long value) {
        return UnsignedKt.ulongCompare(m3389getFirstsVKNKU(), value) <= 0 && UnsignedKt.ulongCompare(value, m3390getLastsVKNKU()) <= 0;
    }

    @Override // kotlin.ranges.ULongProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return UnsignedKt.ulongCompare(m3389getFirstsVKNKU(), m3390getLastsVKNKU()) > 0;
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(@Nullable Object other) {
        return (other instanceof ULongRange) && ((isEmpty() && ((ULongRange) other).isEmpty()) || (m3389getFirstsVKNKU() == ((ULongRange) other).m3389getFirstsVKNKU() && m3390getLastsVKNKU() == ((ULongRange) other).m3390getLastsVKNKU()));
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (31 * ((int) ULong.m2323constructorimpl(m3389getFirstsVKNKU() ^ ULong.m2323constructorimpl(m3389getFirstsVKNKU() >>> 32)))) + ((int) ULong.m2323constructorimpl(m3390getLastsVKNKU() ^ ULong.m2323constructorimpl(m3390getLastsVKNKU() >>> 32)));
    }

    @Override // kotlin.ranges.ULongProgression
    @NotNull
    public String toString() {
        return ULong.m2322toStringimpl(m3389getFirstsVKNKU()) + ".." + ULong.m2322toStringimpl(m3390getLastsVKNKU());
    }

    /* compiled from: ULongRange.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/ranges/ULongRange$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final ULongRange getEMPTY() {
            return ULongRange.EMPTY;
        }
    }
}
