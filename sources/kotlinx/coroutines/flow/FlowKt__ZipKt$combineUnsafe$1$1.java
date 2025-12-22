package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [R, T] */
/* compiled from: Zip.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\u0010��\u001a\u00020\u0001\"\u0006\b��\u0010\u0002\u0018\u0001\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "it", ""})
@DebugMetadata(f = "Zip.kt", l = {262, 262}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineUnsafe$1$1")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ZipKt$combineUnsafe$1$1.class */
public final class FlowKt__ZipKt$combineUnsafe$1$1<R, T> extends SuspendLambda implements Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    final /* synthetic */ Function2<T[], Continuation<? super R>, Object> $transform;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__ZipKt$combineUnsafe$1$1(Function2<? super T[], ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super FlowKt__ZipKt$combineUnsafe$1$1> continuation) {
        super(3, continuation);
        this.$transform = function2;
    }

    @Override // kotlin.jvm.functions.Function3
    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super R> flowCollector, @NotNull T[] tArr, @Nullable Continuation<? super Unit> continuation) {
        FlowKt__ZipKt$combineUnsafe$1$1 flowKt__ZipKt$combineUnsafe$1$1 = new FlowKt__ZipKt$combineUnsafe$1$1(this.$transform, continuation);
        flowKt__ZipKt$combineUnsafe$1$1.L$0 = flowCollector;
        flowKt__ZipKt$combineUnsafe$1$1.L$1 = tArr;
        return flowKt__ZipKt$combineUnsafe$1$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0087  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r12 = r0
            r0 = r6
            int r0 = r0.label
            switch(r0) {
                case 0: goto L24;
                case 1: goto L5a;
                case 2: goto L8a;
                default: goto L94;
            }
        L24:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r6
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            r8 = r0
            r0 = r6
            java.lang.Object r0 = r0.L$1
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            r9 = r0
            r0 = r8
            r10 = r0
            r0 = r6
            kotlin.jvm.functions.Function2<T[], kotlin.coroutines.Continuation<? super R>, java.lang.Object> r0 = r0.$transform
            r1 = r9
            r2 = r6
            r3 = r6
            r4 = r10
            r3.L$0 = r4
            r3 = r6
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.invoke(r1, r2)
            r1 = r0
            r2 = r12
            if (r1 != r2) goto L68
            r1 = r12
            return r1
        L5a:
            r0 = r6
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            r10 = r0
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
        L68:
            r11 = r0
            r0 = r10
            r1 = r11
            r2 = r6
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = r6
            r4 = 0
            r3.L$0 = r4
            r3 = r6
            r4 = 2
            r3.label = r4
            java.lang.Object r0 = r0.emit(r1, r2)
            r1 = r0
            r2 = r12
            if (r1 != r2) goto L8f
            r1 = r12
            return r1
        L8a:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
        L8f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L94:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ZipKt$combineUnsafe$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Object invokeSuspend$$forInline(@NotNull Object obj) {
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        Object[] objArr = (Object[]) this.L$1;
        InlineMarker.mark(0);
        flowCollector.emit(this.$transform.invoke(objArr, this), this);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
