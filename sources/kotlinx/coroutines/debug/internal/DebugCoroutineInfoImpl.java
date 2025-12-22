package kotlinx.coroutines.debug.internal;

import ch.qos.logback.core.CoreConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DebugCoroutineInfoImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\\\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b��\u0018��2\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014J\b\u0010$\u001a\u00020\u000eH\u0016J!\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020\u000e2\n\u0010'\u001a\u0006\u0012\u0002\b\u00030(H��¢\u0006\u0002\b)J%\u0010*\u001a\u00020&*\b\u0012\u0004\u0012\u00020\u00150+2\b\u0010'\u001a\u0004\u0018\u00010\fH\u0082Pø\u0001��¢\u0006\u0002\u0010,R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n��R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R(\u0010\u0019\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\f8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001f8��@��X\u0081\u000e¢\u0006\u0002\n��R\u0010\u0010\u0006\u001a\u00020\u00078��X\u0081\u0004¢\u0006\u0002\n��R\u0011\u0010 \u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "creationStackBottom", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "sequenceNumber", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/debug/internal/StackTraceFrame;J)V", "_context", "Ljava/lang/ref/WeakReference;", "_lastObservedFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "_state", "", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getCreationStackBottom", "()Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "creationStackTrace", "", "Ljava/lang/StackTraceElement;", "getCreationStackTrace", "()Ljava/util/List;", "value", "lastObservedFrame", "getLastObservedFrame$kotlinx_coroutines_core", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "setLastObservedFrame$kotlinx_coroutines_core", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "lastObservedThread", "Ljava/lang/Thread;", "state", "getState", "()Ljava/lang/String;", "lastObservedStackTrace", "toString", "updateState", "", "frame", "Lkotlin/coroutines/Continuation;", "updateState$kotlinx_coroutines_core", "yieldFrames", "Lkotlin/sequences/SequenceScope;", "(Lkotlin/sequences/SequenceScope;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl.class */
public final class DebugCoroutineInfoImpl {

    @Nullable
    private final StackTraceFrame creationStackBottom;

    @JvmField
    public final long sequenceNumber;

    @NotNull
    private final WeakReference<CoroutineContext> _context;

    @NotNull
    private String _state = DebugCoroutineInfoImplKt.CREATED;

    @JvmField
    @Nullable
    public Thread lastObservedThread;

    @Nullable
    private WeakReference<CoroutineStackFrame> _lastObservedFrame;

    /* compiled from: DebugCoroutineInfoImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "DebugCoroutineInfoImpl.kt", l = {80}, i = {}, s = {}, n = {}, m = "yieldFrames", c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl")
    /* renamed from: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$yieldFrames$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl$yieldFrames$1.class */
    static final class C15941 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C15941(Continuation<? super C15941> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DebugCoroutineInfoImpl.this.yieldFrames(null, null, this);
        }
    }

    public DebugCoroutineInfoImpl(@Nullable CoroutineContext context, @Nullable StackTraceFrame creationStackBottom, long sequenceNumber) {
        this.creationStackBottom = creationStackBottom;
        this.sequenceNumber = sequenceNumber;
        this._context = new WeakReference<>(context);
    }

    @Nullable
    public final StackTraceFrame getCreationStackBottom() {
        return this.creationStackBottom;
    }

    @Nullable
    public final CoroutineContext getContext() {
        return this._context.get();
    }

    @NotNull
    public final List<StackTraceElement> getCreationStackTrace() {
        return creationStackTrace();
    }

    @NotNull
    public final String getState() {
        return this._state;
    }

    @Nullable
    public final CoroutineStackFrame getLastObservedFrame$kotlinx_coroutines_core() {
        WeakReference<CoroutineStackFrame> weakReference = this._lastObservedFrame;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public final void setLastObservedFrame$kotlinx_coroutines_core(@Nullable CoroutineStackFrame value) {
        WeakReference<CoroutineStackFrame> weakReference;
        DebugCoroutineInfoImpl debugCoroutineInfoImpl = this;
        if (value == null) {
            weakReference = null;
        } else {
            debugCoroutineInfoImpl = debugCoroutineInfoImpl;
            weakReference = new WeakReference<>(value);
        }
        debugCoroutineInfoImpl._lastObservedFrame = weakReference;
    }

    @NotNull
    public final List<StackTraceElement> lastObservedStackTrace() {
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = getLastObservedFrame$kotlinx_coroutines_core();
        if (lastObservedFrame$kotlinx_coroutines_core == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList result = new ArrayList();
        for (CoroutineStackFrame frame = lastObservedFrame$kotlinx_coroutines_core; frame != null; frame = frame.getCallerFrame()) {
            StackTraceElement it = frame.getStackTraceElement();
            if (it != null) {
                result.add(it);
            }
        }
        return result;
    }

    private final List<StackTraceElement> creationStackTrace() {
        StackTraceFrame bottom = this.creationStackBottom;
        return bottom == null ? CollectionsKt.emptyList() : SequencesKt.toList(SequencesKt.sequence(new AnonymousClass1(bottom, null)));
    }

    /* compiled from: DebugCoroutineInfoImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/lang/StackTraceElement;"})
    @DebugMetadata(f = "DebugCoroutineInfoImpl.kt", l = {75}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1")
    /* renamed from: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl$creationStackTrace$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl$creationStackTrace$1.class */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super StackTraceElement>, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ StackTraceFrame $bottom;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(StackTraceFrame $bottom, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$bottom = $bottom;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = DebugCoroutineInfoImpl.this.new AnonymousClass1(this.$bottom, continuation);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull SequenceScope<? super StackTraceElement> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    SequenceScope $this$sequence = (SequenceScope) this.L$0;
                    this.label = 1;
                    if (DebugCoroutineInfoImpl.this.yieldFrames($this$sequence, this.$bottom.getCallerFrame(), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0102 -> B:11:0x0066). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object yieldFrames(kotlin.sequences.SequenceScope<? super java.lang.StackTraceElement> r7, kotlin.coroutines.jvm.internal.CoroutineStackFrame r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl.yieldFrames(kotlin.sequences.SequenceScope, kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void updateState$kotlinx_coroutines_core(@NotNull String state, @NotNull Continuation<?> continuation) {
        Thread threadCurrentThread;
        if (Intrinsics.areEqual(this._state, state) && Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.SUSPENDED) && getLastObservedFrame$kotlinx_coroutines_core() != null) {
            return;
        }
        this._state = state;
        setLastObservedFrame$kotlinx_coroutines_core(continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null);
        if (Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING)) {
            threadCurrentThread = Thread.currentThread();
        } else {
            threadCurrentThread = (Thread) null;
        }
        this.lastObservedThread = threadCurrentThread;
    }

    @NotNull
    public String toString() {
        return "DebugCoroutineInfo(state=" + getState() + ",context=" + getContext() + ')';
    }
}
