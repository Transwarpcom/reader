package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WhileSelect.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010��\u001a\u00020\u00012\u001f\b\u0004\u0010\u0002\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0006H\u0087Hø\u0001��¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"whileSelect", "", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/WhileSelectKt.class */
public final class WhileSelectKt {

    /* compiled from: WhileSelect.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "WhileSelect.kt", l = {37}, i = {0}, s = {"L$0"}, n = {"builder"}, m = "whileSelect", c = "kotlinx.coroutines.selects.WhileSelectKt")
    /* renamed from: kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/WhileSelectKt$whileSelect$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
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
            return WhileSelectKt.whileSelect(null, this);
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00d3 -> B:11:0x005f). Please report as a decompilation issue!!! */
    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object whileSelect(@org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super kotlinx.coroutines.selects.SelectBuilder<? super java.lang.Boolean>, kotlin.Unit> r4, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r0 = r5
            boolean r0 = r0 instanceof kotlinx.coroutines.selects.WhileSelectKt.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r5
            kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1 r0 = (kotlinx.coroutines.selects.WhileSelectKt.AnonymousClass1) r0
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
            kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1 r0 = new kotlinx.coroutines.selects.WhileSelectKt$whileSelect$1
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
                case 1: goto Lb8;
                default: goto Ldd;
            }
        L58:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = 0
            r6 = r0
        L5f:
            r0 = 0
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r13
            r1 = r4
            r0.L$0 = r1
            r0 = r13
            r1 = 1
            r0.label = r1
            r0 = r13
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            r8 = r0
            r0 = 0
            r9 = r0
            kotlinx.coroutines.selects.SelectBuilderImpl r0 = new kotlinx.coroutines.selects.SelectBuilderImpl
            r1 = r0
            r2 = r8
            r1.<init>(r2)
            r10 = r0
            r0 = r4
            r1 = r10
            java.lang.Object r0 = r0.invoke(r1)     // Catch: java.lang.Throwable -> L92
            goto L9b
        L92:
            r11 = move-exception
            r0 = r10
            r1 = r11
            r0.handleBuilderException(r1)
        L9b:
            r0 = r10
            java.lang.Object r0 = r0.getResult()
            r1 = r0
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r2) goto Laf
            r1 = r13
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r1)
        Laf:
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Lcc
            r1 = r14
            return r1
        Lb8:
            r0 = 0
            r6 = r0
            r0 = 0
            r7 = r0
            r0 = r13
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
            r4 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Lcc:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto Ld9
            goto L5f
        Ld9:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Ldd:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.WhileSelectKt.whileSelect(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @ExperimentalCoroutinesApi
    private static final Object whileSelect$$forInline(Function1<? super SelectBuilder<? super Boolean>, Unit> function1, Continuation<? super Unit> continuation) throws Throwable {
        Object result;
        do {
            InlineMarker.mark(0);
            SelectBuilderImpl scope$iv = new SelectBuilderImpl(continuation);
            try {
                function1.invoke(scope$iv);
            } catch (Throwable e$iv) {
                scope$iv.handleBuilderException(e$iv);
            }
            result = scope$iv.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            InlineMarker.mark(1);
        } while (((Boolean) result).booleanValue());
        return Unit.INSTANCE;
    }
}
