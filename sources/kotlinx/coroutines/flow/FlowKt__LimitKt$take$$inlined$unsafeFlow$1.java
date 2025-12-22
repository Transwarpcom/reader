package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SafeCollector.common.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0019\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005H\u0096@ø\u0001��¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006��"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__LimitKt$take$$inlined$unsafeFlow$1.class */
public final class FlowKt__LimitKt$take$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Flow $this_take$inlined;
    final /* synthetic */ int $count$inlined;

    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Limit.kt", l = {116}, i = {0}, s = {"L$0"}, n = {"$this$take_u24lambda_u2d7"}, m = "collect", c = "kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__LimitKt$take$$inlined$unsafeFlow$1$1.class */
    public static final class AnonymousClass1 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;
        Object L$0;

        public AnonymousClass1(Continuation $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__LimitKt$take$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @Override // kotlinx.coroutines.flow.Flow
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector<? super T> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            r7 = this;
            r0 = r9
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1.AnonymousClass1
            if (r0 == 0) goto L24
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1$1 r0 = (kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1.AnonymousClass1) r0
            r10 = r0
            r0 = r10
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L24
            r0 = r10
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L2e
        L24:
            kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1$1 r0 = new kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1$1
            r1 = r0
            r2 = r7
            r3 = r9
            r1.<init>(r3)
            r10 = r0
        L2e:
            r0 = r10
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r12 = r0
            r0 = r10
            int r0 = r0.label
            switch(r0) {
                case 0: goto L54;
                case 1: goto Laa;
                default: goto Ld3;
            }
        L54:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
            r1 = r10
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r13 = r1
            r14 = r0
            r0 = 0
            r15 = r0
            kotlin.jvm.internal.Ref$IntRef r0 = new kotlin.jvm.internal.Ref$IntRef
            r1 = r0
            r1.<init>()
            r16 = r0
            r0 = r7
            kotlinx.coroutines.flow.Flow r0 = r0.$this_take$inlined     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r17 = r0
            r0 = r10
            r18 = r0
            r0 = 0
            r19 = r0
            r0 = r17
            kotlinx.coroutines.flow.FlowKt__LimitKt$take$lambda-7$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__LimitKt$take$lambda-7$$inlined$collect$1     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r2 = r1
            r3 = r16
            r4 = r7
            int r4 = r4.$count$inlined     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r5 = r14
            r2.<init>()     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r2 = r18
            r3 = r10
            r4 = r14
            r3.L$0 = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r3 = r10
            r4 = 1
            r3.label = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            java.lang.Object r0 = r0.collect(r1, r2)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r1 = r0
            r2 = r12
            if (r1 != r2) goto Lc1
            r1 = r12
            return r1
        Laa:
            r0 = 0
            r15 = r0
            r0 = 0
            r19 = r0
            r0 = r10
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            r14 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc5
            r0 = r11
        Lc1:
            goto Lce
        Lc5:
            r17 = move-exception
            r0 = r17
            r1 = r14
            kotlinx.coroutines.flow.internal.FlowExceptions_commonKt.checkOwnership(r0, r1)
        Lce:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Ld3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__LimitKt$take$$inlined$unsafeFlow$1.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FlowKt__LimitKt$take$$inlined$unsafeFlow$1(Flow flow, int i) {
        this.$this_take$inlined = flow;
        this.$count$inlined = i;
    }
}
