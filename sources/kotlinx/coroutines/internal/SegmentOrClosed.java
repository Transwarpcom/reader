package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Segment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: ConcurrentLinkedList.kt */
@JvmInline
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0081@\u0018��*\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0014\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003ø\u0001��¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\u00028��8F¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��\u0088\u0001\u0004\u0092\u0001\u0004\u0018\u00010\u0003ø\u0001��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", "", "value", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "segment", "getSegment$annotations", "()V", "getSegment-impl", "(Ljava/lang/Object;)Lkotlinx/coroutines/internal/Segment;", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", IdentityNamingStrategy.HASH_CODE_KEY, "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/SegmentOrClosed.class */
public final class SegmentOrClosed<S extends Segment<S>> {

    @Nullable
    private final Object value;

    public static /* synthetic */ void getSegment$annotations() {
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m4332toStringimpl(Object arg0) {
        return "SegmentOrClosed(value=" + arg0 + ')';
    }

    public String toString() {
        return m4332toStringimpl(this.value);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m4333hashCodeimpl(Object arg0) {
        if (arg0 == null) {
            return 0;
        }
        return arg0.hashCode();
    }

    public int hashCode() {
        return m4333hashCodeimpl(this.value);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m4334equalsimpl(Object arg0, Object other) {
        return (other instanceof SegmentOrClosed) && Intrinsics.areEqual(arg0, ((SegmentOrClosed) other).m4337unboximpl());
    }

    public boolean equals(Object other) {
        return m4334equalsimpl(this.value, other);
    }

    @NotNull
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <S extends Segment<S>> Object m4335constructorimpl(@Nullable Object value) {
        return value;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SegmentOrClosed m4336boximpl(Object v) {
        return new SegmentOrClosed(v);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m4337unboximpl() {
        return this.value;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4338equalsimpl0(Object p1, Object p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    private /* synthetic */ SegmentOrClosed(Object value) {
        this.value = value;
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m4330isClosedimpl(Object arg0) {
        return arg0 == ConcurrentLinkedListKt.CLOSED;
    }

    @NotNull
    /* renamed from: getSegment-impl, reason: not valid java name */
    public static final S m4331getSegmentimpl(Object arg0) {
        if (arg0 == ConcurrentLinkedListKt.CLOSED) {
            throw new IllegalStateException("Does not contain segment".toString());
        }
        if (arg0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        }
        return (S) arg0;
    }
}
