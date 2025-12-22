package kotlin.ranges;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.UnsignedKt;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: UIntRange.kt */
@SinceKotlin(version = "1.5")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018�� \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001��¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001��¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001��ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001��ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018"}, d2 = {"Lkotlin/ranges/UIntRange;", "Lkotlin/ranges/UIntProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/UInt;", "start", "endInclusive", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-pVg5ArA", "()I", "getStart-pVg5ArA", "contains", "", "value", "contains-WZ4Q5Ns", "(I)Z", "equals", "other", "", IdentityNamingStrategy.HASH_CODE_KEY, "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/ranges/UIntRange.class */
public final class UIntRange extends UIntProgression implements ClosedRange<UInt> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final UIntRange EMPTY = new UIntRange(-1, 0);

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getStart() {
        return UInt.m2245boximpl(m3385getStartpVg5ArA());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ Comparable getEndInclusive() {
        return UInt.m2245boximpl(m3386getEndInclusivepVg5ArA());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return m3387containsWZ4Q5Ns(((UInt) comparable).m2249unboximpl());
    }

    private UIntRange(int start, int endInclusive) {
        super(start, endInclusive, 1, null);
    }

    public /* synthetic */ UIntRange(int start, int endInclusive, DefaultConstructorMarker $constructor_marker) {
        this(start, endInclusive);
    }

    /* renamed from: getStart-pVg5ArA, reason: not valid java name */
    public int m3385getStartpVg5ArA() {
        return m3381getFirstpVg5ArA();
    }

    /* renamed from: getEndInclusive-pVg5ArA, reason: not valid java name */
    public int m3386getEndInclusivepVg5ArA() {
        return m3382getLastpVg5ArA();
    }

    /* renamed from: contains-WZ4Q5Ns, reason: not valid java name */
    public boolean m3387containsWZ4Q5Ns(int value) {
        return UnsignedKt.uintCompare(m3381getFirstpVg5ArA(), value) <= 0 && UnsignedKt.uintCompare(value, m3382getLastpVg5ArA()) <= 0;
    }

    @Override // kotlin.ranges.UIntProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        return UnsignedKt.uintCompare(m3381getFirstpVg5ArA(), m3382getLastpVg5ArA()) > 0;
    }

    @Override // kotlin.ranges.UIntProgression
    public boolean equals(@Nullable Object other) {
        return (other instanceof UIntRange) && ((isEmpty() && ((UIntRange) other).isEmpty()) || (m3381getFirstpVg5ArA() == ((UIntRange) other).m3381getFirstpVg5ArA() && m3382getLastpVg5ArA() == ((UIntRange) other).m3382getLastpVg5ArA()));
    }

    @Override // kotlin.ranges.UIntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (31 * m3381getFirstpVg5ArA()) + m3382getLastpVg5ArA();
    }

    @Override // kotlin.ranges.UIntProgression
    @NotNull
    public String toString() {
        return UInt.m2243toStringimpl(m3381getFirstpVg5ArA()) + ".." + UInt.m2243toStringimpl(m3382getLastpVg5ArA());
    }

    /* compiled from: UIntRange.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/UIntRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "kotlin-stdlib"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/ranges/UIntRange$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final UIntRange getEMPTY() {
            return UIntRange.EMPTY;
        }
    }
}
