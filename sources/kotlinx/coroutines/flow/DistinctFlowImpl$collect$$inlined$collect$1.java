package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: Collect.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0013\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028��H\u0096@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006��"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/DistinctFlowImpl$collect$$inlined$collect$1.class */
public final class DistinctFlowImpl$collect$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ DistinctFlowImpl this$0;
    final /* synthetic */ Ref.ObjectRef $previousKey$inlined;
    final /* synthetic */ FlowCollector $collector$inlined;

    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Distinct.kt", l = {139}, i = {}, s = {}, n = {}, m = "emit", c = "kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1")
    /* renamed from: kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/DistinctFlowImpl$collect$$inlined$collect$1$1.class */
    public static final class AnonymousClass1 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;

        public AnonymousClass1(Continuation $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return DistinctFlowImpl$collect$$inlined$collect$1.this.emit(null, this);
        }
    }

    public DistinctFlowImpl$collect$$inlined$collect$1(DistinctFlowImpl distinctFlowImpl, Ref.ObjectRef objectRef, FlowCollector flowCollector) {
        this.this$0 = distinctFlowImpl;
        this.$previousKey$inlined = objectRef;
        this.$collector$inlined = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object emit(T r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.AnonymousClass1
            if (r0 == 0) goto L24
            r0 = r8
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1 r0 = (kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.AnonymousClass1) r0
            r9 = r0
            r0 = r9
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L24
            r0 = r9
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L2e
        L24:
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1 r0 = new kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1$1
            r1 = r0
            r2 = r6
            r3 = r8
            r1.<init>(r3)
            r9 = r0
        L2e:
            r0 = r9
            java.lang.Object r0 = r0.result
            r10 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r11 = r0
            r0 = r9
            int r0 = r0.label
            switch(r0) {
                case 0: goto L54;
                case 1: goto Lc3;
                default: goto Ld2;
            }
        L54:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            r1 = r9
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r12 = r1
            r13 = r0
            r0 = 0
            r14 = r0
            r0 = r6
            kotlinx.coroutines.flow.DistinctFlowImpl r0 = r0.this$0
            kotlin.jvm.functions.Function1<T, java.lang.Object> r0 = r0.keySelector
            r1 = r13
            java.lang.Object r0 = r0.invoke(r1)
            r15 = r0
            r0 = r6
            kotlin.jvm.internal.Ref$ObjectRef r0 = r0.$previousKey$inlined
            T r0 = r0.element
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r0 == r1) goto La0
            r0 = r6
            kotlinx.coroutines.flow.DistinctFlowImpl r0 = r0.this$0
            kotlin.jvm.functions.Function2<java.lang.Object, java.lang.Object, java.lang.Boolean> r0 = r0.areEquivalent
            r1 = r6
            kotlin.jvm.internal.Ref$ObjectRef r1 = r1.$previousKey$inlined
            T r1 = r1.element
            r2 = r15
            java.lang.Object r0 = r0.invoke(r1, r2)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto Lce
        La0:
            r0 = r6
            kotlin.jvm.internal.Ref$ObjectRef r0 = r0.$previousKey$inlined
            r1 = r15
            r0.element = r1
            r0 = r6
            kotlinx.coroutines.flow.FlowCollector r0 = r0.$collector$inlined
            r1 = r13
            r2 = r9
            r3 = r9
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.emit(r1, r2)
            r1 = r0
            r2 = r11
            if (r1 != r2) goto Lcd
            r1 = r11
            return r1
        Lc3:
            r0 = 0
            r14 = r0
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r10
        Lcd:
        Lce:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Ld2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.DistinctFlowImpl$collect$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
