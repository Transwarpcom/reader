package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.FlowPreview;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Flow.kt */
@FlowPreview
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0087@ø\u0001��¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\bH¦@ø\u0001��¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/flow/AbstractFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "()V", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectSafely", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/AbstractFlow.class */
public abstract class AbstractFlow<T> implements Flow<T>, CancellableFlow<T> {

    /* compiled from: Flow.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Flow.kt", l = {212}, i = {0}, s = {"L$0"}, n = {"safeCollector"}, m = "collect", c = "kotlinx.coroutines.flow.AbstractFlow")
    /* renamed from: kotlinx.coroutines.flow.AbstractFlow$collect$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/AbstractFlow$collect$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        final /* synthetic */ AbstractFlow<T> this$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(AbstractFlow<T> abstractFlow, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = abstractFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.collect(null, this);
        }
    }

    @Nullable
    public abstract Object collectSafely(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation);

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @Override // kotlinx.coroutines.flow.Flow
    @kotlinx.coroutines.InternalCoroutinesApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object collect(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector<? super T> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.AbstractFlow.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r8
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r0 = (kotlinx.coroutines.flow.AbstractFlow.AnonymousClass1) r0
            r12 = r0
            r0 = r12
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r12
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r0 = new kotlinx.coroutines.flow.AbstractFlow$collect$1
            r1 = r0
            r2 = r6
            r3 = r8
            r1.<init>(r2, r3)
            r12 = r0
        L32:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L8d;
                default: goto Lb3;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.flow.internal.SafeCollector r0 = new kotlinx.coroutines.flow.internal.SafeCollector
            r1 = r0
            r2 = r7
            r3 = r12
            kotlin.coroutines.CoroutineContext r3 = r3.getContext()
            r1.<init>(r2, r3)
            r9 = r0
            r0 = r6
            r1 = r9
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1     // Catch: java.lang.Throwable -> La6
            r2 = r12
            r3 = r12
            r4 = r9
            r3.L$0 = r4     // Catch: java.lang.Throwable -> La6
            r3 = r12
            r4 = 1
            r3.label = r4     // Catch: java.lang.Throwable -> La6
            java.lang.Object r0 = r0.collectSafely(r1, r2)     // Catch: java.lang.Throwable -> La6
            r1 = r0
            r2 = r13
            if (r1 != r2) goto L9e
            r1 = r13
            return r1
        L8d:
            r0 = r12
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.flow.internal.SafeCollector r0 = (kotlinx.coroutines.flow.internal.SafeCollector) r0
            r9 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> La6
            r0 = r11
        L9e:
            r0 = r9
            r0.releaseIntercepted()
            goto Laf
        La6:
            r10 = move-exception
            r0 = r9
            r0.releaseIntercepted()
            r0 = r10
            throw r0
        Laf:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lb3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.AbstractFlow.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
