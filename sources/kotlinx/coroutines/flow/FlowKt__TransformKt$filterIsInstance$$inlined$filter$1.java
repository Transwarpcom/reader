package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SafeCollector.common.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0019\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005H\u0096@ø\u0001��¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.class */
public final class FlowKt__TransformKt$filterIsInstance$$inlined$filter$1 implements Flow<Object> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    /* compiled from: Collect.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u0013\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00028��0\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028��H\u0096@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\b"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2.class */
    public static final class AnonymousClass2 implements FlowCollector<Object> {
        final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;

        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
        @DebugMetadata(f = "Transform.kt", l = {137}, i = {}, s = {}, n = {}, m = "emit", c = "kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2")
        /* renamed from: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1, reason: invalid class name */
        /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1.class */
        public static final class AnonymousClass1 extends ContinuationImpl {
            /* synthetic */ Object result;
            int label;
            Object L$0;
            Object L$1;

            public AnonymousClass1(Continuation $completion) {
                super($completion);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                this.result = $result;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object emit(java.lang.Object r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation r8) throws java.lang.Throwable {
            /*
                r6 = this;
                r0 = r8
                boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L24
                r0 = r8
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
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
                kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1
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
                    case 1: goto La5;
                    default: goto Lbb;
                }
            L54:
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
                r1 = r9
                r12 = r1
                r13 = r0
                r0 = 0
                r14 = r0
                r0 = r6
                kotlinx.coroutines.flow.FlowCollector r0 = r0.$this_unsafeFlow$inlined
                r1 = r13
                r2 = r9
                r15 = r2
                r16 = r1
                r17 = r0
                r0 = 0
                r18 = r0
                r0 = r16
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r19 = r1
                r20 = r0
                r0 = 0
                r21 = r0
                r0 = r20
                r1 = 3
                java.lang.String r2 = "R"
                kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r1, r2)
                boolean r0 = r0 instanceof java.lang.Object
                if (r0 == 0) goto Lb6
                r0 = r17
                r1 = r16
                r2 = r9
                r3 = r9
                r4 = 1
                r3.label = r4
                java.lang.Object r0 = r0.emit(r1, r2)
                r1 = r0
                r2 = r11
                if (r1 != r2) goto Lb2
                r1 = r11
                return r1
            La5:
                r0 = 0
                r14 = r0
                r0 = 0
                r18 = r0
                r0 = r10
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r10
            Lb2:
                goto Lb7
            Lb6:
            Lb7:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Lbb:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public AnonymousClass2(FlowCollector flowCollector) {
            this.$this_unsafeFlow$inlined = flowCollector;
        }

        @Nullable
        public Object emit$$forInline(Object value, @NotNull Continuation $completion) {
            InlineMarker.mark(4);
            new AnonymousClass1($completion);
            InlineMarker.mark(5);
            FlowCollector $this$filter_u24lambda_u2d0 = this.$this_unsafeFlow$inlined;
            Intrinsics.reifiedOperationMarker(3, "R");
            if (value instanceof Object) {
                InlineMarker.mark(0);
                $this$filter_u24lambda_u2d0.emit(value, $completion);
                InlineMarker.mark(1);
            }
            return Unit.INSTANCE;
        }
    }

    public FlowKt__TransformKt$filterIsInstance$$inlined$filter$1(Flow flow) {
        this.$this_unsafeTransform$inlined = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    public Object collect(@NotNull FlowCollector<? super Object> flowCollector, @NotNull Continuation $completion) {
        Flow $this$collect$iv = this.$this_unsafeTransform$inlined;
        Intrinsics.needClassReification();
        Object objCollect = $this$collect$iv.collect(new AnonymousClass2(flowCollector), $completion);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    @Nullable
    public Object collect$$forInline(@NotNull FlowCollector collector, @NotNull Continuation $completion) {
        InlineMarker.mark(4);
        new ContinuationImpl($completion) { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.1
            /* synthetic */ Object result;
            int label;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                this.result = $result;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__TransformKt$filterIsInstance$$inlined$filter$1.this.collect(null, this);
            }
        };
        InlineMarker.mark(5);
        Flow $this$collect$iv = this.$this_unsafeTransform$inlined;
        Intrinsics.needClassReification();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(collector);
        InlineMarker.mark(0);
        $this$collect$iv.collect(anonymousClass2, $completion);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
