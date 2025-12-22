package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.Symbol;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Mutex.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��.\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u001aB\u0010\u0013\u001a\u0002H\u0014\"\u0004\b��\u0010\u0014*\u00020\u00102\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0018H\u0086Hø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0019\"\u0016\u0010��\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u0005\u0010\u0003\"\u0016\u0010\u0006\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\b\u0010\u0003\"\u0016\u0010\t\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\n\u0010\u0003\"\u0016\u0010\u000b\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\f\u0010\u0003\"\u0016\u0010\r\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b\u000e\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"EMPTY_LOCKED", "Lkotlinx/coroutines/sync/Empty;", "getEMPTY_LOCKED$annotations", "()V", "EMPTY_UNLOCKED", "getEMPTY_UNLOCKED$annotations", "LOCKED", "Lkotlinx/coroutines/internal/Symbol;", "getLOCKED$annotations", "LOCK_FAIL", "getLOCK_FAIL$annotations", "UNLOCKED", "getUNLOCKED$annotations", "UNLOCK_FAIL", "getUNLOCK_FAIL$annotations", "Mutex", "Lkotlinx/coroutines/sync/Mutex;", "locked", "", "withLock", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "owner", "", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/sync/MutexKt.class */
public final class MutexKt {

    @NotNull
    private static final Symbol LOCK_FAIL = new Symbol("LOCK_FAIL");

    @NotNull
    private static final Symbol UNLOCK_FAIL = new Symbol("UNLOCK_FAIL");

    @NotNull
    private static final Symbol LOCKED = new Symbol("LOCKED");

    @NotNull
    private static final Symbol UNLOCKED = new Symbol("UNLOCKED");

    @NotNull
    private static final Empty EMPTY_LOCKED = new Empty(LOCKED);

    @NotNull
    private static final Empty EMPTY_UNLOCKED = new Empty(UNLOCKED);

    /* compiled from: Mutex.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Mutex.kt", l = {113}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"$this$withLock", "owner", "action"}, m = "withLock", c = "kotlinx.coroutines.sync.MutexKt")
    /* renamed from: kotlinx.coroutines.sync.MutexKt$withLock$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/sync/MutexKt$withLock$1.class */
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return MutexKt.withLock(null, null, null, this);
        }
    }

    private static /* synthetic */ void getLOCK_FAIL$annotations() {
    }

    private static /* synthetic */ void getUNLOCK_FAIL$annotations() {
    }

    private static /* synthetic */ void getLOCKED$annotations() {
    }

    private static /* synthetic */ void getUNLOCKED$annotations() {
    }

    private static /* synthetic */ void getEMPTY_LOCKED$annotations() {
    }

    private static /* synthetic */ void getEMPTY_UNLOCKED$annotations() {
    }

    public static /* synthetic */ Mutex Mutex$default(boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return Mutex(z);
    }

    @NotNull
    public static final Mutex Mutex(boolean locked) {
        return new MutexImpl(locked);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object withLock(@org.jetbrains.annotations.NotNull kotlinx.coroutines.sync.Mutex r6, @org.jetbrains.annotations.Nullable java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends T> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexKt.withLock(kotlinx.coroutines.sync.Mutex, java.lang.Object, kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object withLock$default(Mutex $this$withLock_u24default, Object owner, Function0 action, Continuation $completion, int i, Object obj) {
        if ((i & 1) != 0) {
            owner = null;
        }
        InlineMarker.mark(0);
        $this$withLock_u24default.lock(owner, $completion);
        InlineMarker.mark(1);
        try {
            Object objInvoke = action.invoke();
            InlineMarker.finallyStart(1);
            $this$withLock_u24default.unlock(owner);
            InlineMarker.finallyEnd(1);
            return objInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            $this$withLock_u24default.unlock(owner);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    private static final <T> Object withLock$$forInline(Mutex $this$withLock, Object owner, Function0<? extends T> function0, Continuation<? super T> continuation) {
        InlineMarker.mark(0);
        $this$withLock.lock(owner, continuation);
        InlineMarker.mark(1);
        try {
            T tInvoke = function0.invoke();
            InlineMarker.finallyStart(1);
            $this$withLock.unlock(owner);
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            $this$withLock.unlock(owner);
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
}
