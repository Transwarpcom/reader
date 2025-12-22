package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Delay.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��*\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0011\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010��\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a!\u0010��\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0087@ø\u0001��ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000b\u001a\u0019\u0010\u000f\u001a\u00020\n*\u00020\rH\u0001ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\"\u0018\u0010��\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"delay", "Lkotlinx/coroutines/Delay;", "Lkotlin/coroutines/CoroutineContext;", "getDelay", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Delay;", "awaitCancellation", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "timeMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "duration", "Lkotlin/time/Duration;", "delay-VtjQ1oo", "toDelayMillis", "toDelayMillis-LRDsOJo", "(J)J", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DelayKt.class */
public final class DelayKt {

    /* compiled from: Delay.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Delay.kt", l = {155}, i = {}, s = {}, n = {}, m = "awaitCancellation", c = "kotlinx.coroutines.DelayKt")
    /* renamed from: kotlinx.coroutines.DelayKt$awaitCancellation$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/DelayKt$awaitCancellation$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return DelayKt.awaitCancellation(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object awaitCancellation(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<?> r5) throws java.lang.Throwable {
        /*
            r0 = r5
            boolean r0 = r0 instanceof kotlinx.coroutines.DelayKt.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r5
            kotlinx.coroutines.DelayKt$awaitCancellation$1 r0 = (kotlinx.coroutines.DelayKt.AnonymousClass1) r0
            r13 = r0
            r0 = r13
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r13
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            kotlinx.coroutines.DelayKt$awaitCancellation$1 r0 = new kotlinx.coroutines.DelayKt$awaitCancellation$1
            r1 = r0
            r2 = r5
            r1.<init>(r2)
            r13 = r0
        L31:
            r0 = r13
            java.lang.Object r0 = r0.result
            r12 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r14 = r0
            r0 = r13
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto La8;
                default: goto Lbb;
            }
        L58:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = 0
            r6 = r0
            r0 = r13
            r1 = 1
            r0.label = r1
            r0 = r13
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            r7 = r0
            r0 = 0
            r8 = r0
            kotlinx.coroutines.CancellableContinuationImpl r0 = new kotlinx.coroutines.CancellableContinuationImpl
            r1 = r0
            r2 = r7
            kotlin.coroutines.Continuation r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r2)
            r3 = 1
            r1.<init>(r2, r3)
            r9 = r0
            r0 = r9
            r0.initCancellability()
            r0 = r9
            kotlinx.coroutines.CancellableContinuation r0 = (kotlinx.coroutines.CancellableContinuation) r0
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r9
            java.lang.Object r0 = r0.getResult()
            r1 = r0
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r2) goto L9f
            r1 = r13
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r1)
        L9f:
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Lb1
            r1 = r14
            return r1
        La8:
            r0 = 0
            r6 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Lb1:
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r1 = r0
            r1.<init>()
            throw r0
        Lbb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DelayKt.awaitCancellation(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public static final Object delay(long timeMillis, @NotNull Continuation<? super Unit> continuation) {
        if (timeMillis <= 0) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        if (timeMillis < Long.MAX_VALUE) {
            getDelay(cont.getContext()).mo4327scheduleResumeAfterDelay(timeMillis, cont);
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    @ExperimentalTime
    @Nullable
    /* renamed from: delay-VtjQ1oo, reason: not valid java name */
    public static final Object m4179delayVtjQ1oo(long duration, @NotNull Continuation<? super Unit> continuation) {
        Object objDelay = delay(m4180toDelayMillisLRDsOJo(duration), continuation);
        return objDelay == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDelay : Unit.INSTANCE;
    }

    @NotNull
    public static final Delay getDelay(@NotNull CoroutineContext $this$delay) {
        CoroutineContext.Element element = $this$delay.get(ContinuationInterceptor.Key);
        Delay delay = element instanceof Delay ? (Delay) element : null;
        return delay == null ? DefaultExecutorKt.getDefaultDelay() : delay;
    }

    @ExperimentalTime
    /* renamed from: toDelayMillis-LRDsOJo, reason: not valid java name */
    public static final long m4180toDelayMillisLRDsOJo(long $this$toDelayMillis) {
        if (Duration.m4071compareToLRDsOJo($this$toDelayMillis, Duration.Companion.m4114getZEROUwyO8pc()) > 0) {
            return RangesKt.coerceAtLeast(Duration.m4094getInWholeMillisecondsimpl($this$toDelayMillis), 1L);
        }
        return 0L;
    }
}
