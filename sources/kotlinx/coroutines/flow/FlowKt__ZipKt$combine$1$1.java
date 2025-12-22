package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: Zip.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0010��\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u008a@"}, d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", ""})
@DebugMetadata(f = "Zip.kt", l = {33, 33}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$1$1")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ZipKt$combine$1$1.class */
final class FlowKt__ZipKt$combine$1$1<R> extends SuspendLambda implements Function3<FlowCollector<? super R>, Object[], Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    final /* synthetic */ Function3<T1, T2, Continuation<? super R>, Object> $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__ZipKt$combine$1$1(Function3<? super T1, ? super T2, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super FlowKt__ZipKt$combine$1$1> continuation) {
        super(3, continuation);
        this.$transform = function3;
    }

    @Override // kotlin.jvm.functions.Function3
    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super R> flowCollector, @NotNull Object[] p2, @Nullable Continuation<? super Unit> continuation) {
        FlowKt__ZipKt$combine$1$1 flowKt__ZipKt$combine$1$1 = new FlowKt__ZipKt$combine$1$1(this.$transform, continuation);
        flowKt__ZipKt$combine$1$1.L$0 = flowCollector;
        flowKt__ZipKt$combine$1$1.L$1 = p2;
        return flowKt__ZipKt$combine$1$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r7
            int r0 = r0.label
            switch(r0) {
                case 0: goto L24;
                case 1: goto L5f;
                case 2: goto L8f;
                default: goto L99;
            }
        L24:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            r9 = r0
            r0 = r7
            java.lang.Object r0 = r0.L$1
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r10 = r0
            r0 = r9
            r11 = r0
            r0 = r7
            kotlin.jvm.functions.Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r0.$transform
            r1 = r10
            r2 = 0
            r1 = r1[r2]
            r2 = r10
            r3 = 1
            r2 = r2[r3]
            r3 = r7
            r4 = r7
            r5 = r11
            r4.L$0 = r5
            r4 = r7
            r5 = 1
            r4.label = r5
            java.lang.Object r0 = r0.invoke(r1, r2, r3)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto L6d
            r1 = r13
            return r1
        L5f:
            r0 = r7
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            r11 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L6d:
            r12 = r0
            r0 = r11
            r1 = r12
            r2 = r7
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = r7
            r4 = 0
            r3.L$0 = r4
            r3 = r7
            r4 = 2
            r3.label = r4
            java.lang.Object r0 = r0.emit(r1, r2)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto L94
            r1 = r13
            return r1
        L8f:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L94:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L99:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combine$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
