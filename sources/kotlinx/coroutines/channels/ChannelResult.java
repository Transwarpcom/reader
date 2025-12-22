package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: Channel.kt */
@JvmInline
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087@\u0018�� %*\u0006\b��\u0010\u0001 \u00012\u00020\u0002:\u0003$%&B\u0016\b\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u0004\u0018\u00018��¢\u0006\u0004\b\u0019\u0010\u0005J\r\u0010\u001a\u001a\u00028��¢\u0006\u0004\b\u001b\u0010\u0005J\u0010\u0010\u001c\u001a\u00020\u001dHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028��X\u0081\u0004¢\u0006\b\n��\u0012\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002ø\u0001��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "holder", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "getHolder$annotations", "()V", "isClosed", "", "isClosed-impl", "(Ljava/lang/Object;)Z", "isFailure", "isFailure-impl", "isSuccess", "isSuccess-impl", "equals", "other", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "exceptionOrNull", "", "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getOrNull", "getOrNull-impl", "getOrThrow", "getOrThrow-impl", IdentityNamingStrategy.HASH_CODE_KEY, "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "Closed", "Companion", "Failed", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelResult.class */
public final class ChannelResult<T> {

    @Nullable
    private final Object holder;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Failed failed = new Failed();

    @PublishedApi
    public static /* synthetic */ void getHolder$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m4230hashCodeimpl(Object arg0) {
        if (arg0 == null) {
            return 0;
        }
        return arg0.hashCode();
    }

    public int hashCode() {
        return m4230hashCodeimpl(this.holder);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m4231equalsimpl(Object arg0, Object other) {
        return (other instanceof ChannelResult) && Intrinsics.areEqual(arg0, ((ChannelResult) other).m4234unboximpl());
    }

    public boolean equals(Object other) {
        return m4231equalsimpl(this.holder, other);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m4232constructorimpl(@Nullable Object holder) {
        return holder;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ChannelResult m4233boximpl(Object v) {
        return new ChannelResult(v);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m4234unboximpl() {
        return this.holder;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4235equalsimpl0(Object p1, Object p2) {
        return Intrinsics.areEqual(p1, p2);
    }

    @PublishedApi
    private /* synthetic */ ChannelResult(Object holder) {
        this.holder = holder;
    }

    /* renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m4223isSuccessimpl(Object arg0) {
        return !(arg0 instanceof Failed);
    }

    /* renamed from: isFailure-impl, reason: not valid java name */
    public static final boolean m4224isFailureimpl(Object arg0) {
        return arg0 instanceof Failed;
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m4225isClosedimpl(Object arg0) {
        return arg0 instanceof Closed;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    /* renamed from: getOrNull-impl, reason: not valid java name */
    public static final T m4226getOrNullimpl(Object obj) {
        if (obj instanceof Failed) {
            return null;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrThrow-impl, reason: not valid java name */
    public static final T m4227getOrThrowimpl(Object obj) throws Throwable {
        if (!(obj instanceof Failed)) {
            return obj;
        }
        if (!(obj instanceof Closed) || ((Closed) obj).cause == null) {
            throw new IllegalStateException(Intrinsics.stringPlus("Trying to call 'getOrThrow' on a failed channel result: ", obj).toString());
        }
        throw ((Closed) obj).cause;
    }

    @Nullable
    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m4228exceptionOrNullimpl(Object arg0) {
        Closed closed = arg0 instanceof Closed ? (Closed) arg0 : null;
        if (closed == null) {
            return null;
        }
        return closed.cause;
    }

    /* compiled from: Channel.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0010\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Failed;", "", "()V", "toString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelResult$Failed.class */
    public static class Failed {
        @NotNull
        public String toString() {
            return "Failed";
        }
    }

    /* compiled from: Channel.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b��\u0018��2\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Closed;", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "cause", "", "(Ljava/lang/Throwable;)V", "equals", "", "other", "", IdentityNamingStrategy.HASH_CODE_KEY, "", "toString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelResult$Closed.class */
    public static final class Closed extends Failed {

        @JvmField
        @Nullable
        public final Throwable cause;

        public Closed(@Nullable Throwable cause) {
            this.cause = cause;
        }

        public boolean equals(@Nullable Object other) {
            return (other instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) other).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        @NotNull
        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    /* compiled from: Channel.kt */
    @InternalCoroutinesApi
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\n\b\u0087\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u0007H\u0007ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\r\u0010\u000eJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\u0006\u0010\u0010\u001a\u0002H\u0007H\u0007ø\u0001��ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/channels/ChannelResult$Companion;", "", "()V", "failed", "Lkotlinx/coroutines/channels/ChannelResult$Failed;", "closed", "Lkotlinx/coroutines/channels/ChannelResult;", "E", "cause", "", "closed-JP2dKIU", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "failure", "failure-PtdJZtk", "()Ljava/lang/Object;", "success", "value", "success-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/ChannelResult$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: success-JP2dKIU, reason: not valid java name */
        public final <E> Object m4237successJP2dKIU(E e) {
            return ChannelResult.m4232constructorimpl(e);
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: failure-PtdJZtk, reason: not valid java name */
        public final <E> Object m4238failurePtdJZtk() {
            return ChannelResult.m4232constructorimpl(ChannelResult.failed);
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: closed-JP2dKIU, reason: not valid java name */
        public final <E> Object m4239closedJP2dKIU(@Nullable Throwable cause) {
            return ChannelResult.m4232constructorimpl(new Closed(cause));
        }
    }

    @NotNull
    public String toString() {
        return m4229toStringimpl(this.holder);
    }

    @NotNull
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m4229toStringimpl(Object arg0) {
        return arg0 instanceof Closed ? arg0.toString() : "Value(" + arg0 + ')';
    }
}
