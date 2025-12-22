package kotlin;

import ch.qos.logback.core.CoreConstants;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: DeepRecursive.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004BK\u00129\u0010\u0005\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\b\u0012\u0006\u0010\t\u001a\u00028��ø\u0001��¢\u0006\u0002\u0010\nJ\u0019\u0010\u0015\u001a\u00028\u00012\u0006\u0010\t\u001a\u00028��H\u0096@ø\u0001��¢\u0006\u0002\u0010\u0016Jc\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000429\u0010\u0018\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0002ø\u0001��¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016ø\u0001��¢\u0006\u0002\u0010\u001cJ\u000b\u0010\u001d\u001a\u00028\u0001¢\u0006\u0002\u0010\u001eJ5\u0010\u0015\u001a\u0002H\u001f\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\u001f0!2\u0006\u0010\t\u001a\u0002H H\u0096@ø\u0001��¢\u0006\u0002\u0010\"R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fRF\u0010\u0010\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\bX\u0082\u000eø\u0001��¢\u0006\u0004\n\u0002\u0010\u0011R\u001e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0013X\u0082\u000eø\u0001��ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n��\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006#"}, d2 = {"Lkotlin/DeepRecursiveScopeImpl;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "R", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function3;", "", "Lkotlin/ExtensionFunctionType;", "value", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)V", "cont", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "function", "Lkotlin/jvm/functions/Function3;", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "Lkotlin/Result;", "Ljava/lang/Object;", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "crossFunctionCompletion", "currentFunction", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resumeWith", "", "(Ljava/lang/Object;)V", "runCallLoop", "()Ljava/lang/Object;", "S", PDBorderStyleDictionary.STYLE_UNDERLINE, "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"})
@ExperimentalStdlibApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/DeepRecursiveScopeImpl.class */
final class DeepRecursiveScopeImpl<T, R> extends DeepRecursiveScope<T, R> implements Continuation<R> {
    private Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function;
    private Object value;
    private Continuation<Object> cont;
    private Object result;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DeepRecursiveScopeImpl(@NotNull Function3<? super DeepRecursiveScope<T, R>, ? super T, ? super Continuation<? super R>, ? extends Object> block, T t) {
        super(null);
        Intrinsics.checkNotNullParameter(block, "block");
        this.function = block;
        this.value = t;
        if (this == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }
        this.cont = this;
        this.result = DeepRecursiveKt.UNDEFINED_RESULT;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object result) {
        this.cont = (Continuation) null;
        this.result = result;
    }

    @Override // kotlin.DeepRecursiveScope
    @Nullable
    public Object callRecursive(T t, @NotNull Continuation<? super R> continuation) {
        if (continuation == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
        }
        this.cont = continuation;
        this.value = t;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended;
    }

    @Override // kotlin.DeepRecursiveScope
    @Nullable
    public <U, S> Object callRecursive(@NotNull DeepRecursiveFunction<U, S> deepRecursiveFunction, U u, @NotNull Continuation<? super S> continuation) {
        Function3 function = deepRecursiveFunction.getBlock$kotlin_stdlib();
        if (function == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.DeepRecursiveFunctionBlock /* = suspend kotlin.DeepRecursiveScope<*, *>.(kotlin.Any?) -> kotlin.Any? */");
        }
        DeepRecursiveScopeImpl<T, R> $this$with = this;
        Function3 currentFunction = $this$with.function;
        if (function == currentFunction) {
            if (continuation == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }
            $this$with.cont = continuation;
        } else {
            $this$with.function = function;
            if (continuation == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }
            $this$with.cont = $this$with.crossFunctionCompletion(currentFunction, continuation);
        }
        $this$with.value = u;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (coroutine_suspended == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return coroutine_suspended;
    }

    private final Continuation<Object> crossFunctionCompletion(final Function3<? super DeepRecursiveScope<?, ?>, Object, ? super Continuation<Object>, ? extends Object> function3, final Continuation<Object> continuation) {
        final EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        return new Continuation<Object>() { // from class: kotlin.DeepRecursiveScopeImpl$crossFunctionCompletion$$inlined$Continuation$1
            @Override // kotlin.coroutines.Continuation
            @NotNull
            public CoroutineContext getContext() {
                return emptyCoroutineContext;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(@NotNull Object result) {
                this.function = function3;
                this.cont = continuation;
                this.result = result;
            }
        };
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:9:0x0028
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    public final R runCallLoop() {
        /*
            r5 = this;
        L0:
            r0 = r5
            java.lang.Object r0 = r0.result
            r6 = r0
            r0 = r5
            kotlin.coroutines.Continuation<java.lang.Object> r0 = r0.cont
            r1 = r0
            if (r1 == 0) goto L11
            goto L1d
        L11:
            r0 = r6
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
            return r0
        L1d:
            r7 = r0
            java.lang.Object r0 = kotlin.DeepRecursiveKt.access$getUNDEFINED_RESULT$p()
            r1 = r6
            boolean r0 = kotlin.Result.m2109equalsimpl0(r0, r1)
            if (r0 == 0) goto Lad
        L29:
            r0 = r5
            kotlin.jvm.functions.Function3<? super kotlin.DeepRecursiveScope<?, ?>, java.lang.Object, ? super kotlin.coroutines.Continuation<java.lang.Object>, ? extends java.lang.Object> r0 = r0.function     // Catch: java.lang.Throwable -> L61
            r9 = r0
            r0 = r5
            r10 = r0
            r0 = r5
            java.lang.Object r0 = r0.value     // Catch: java.lang.Throwable -> L61
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r9
            r1 = r0
            if (r1 != 0) goto L4b
            java.lang.NullPointerException r1 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L61
            r2 = r1
            java.lang.String r3 = "null cannot be cast to non-null type (R, P, kotlin.coroutines.Continuation<T>) -> kotlin.Any?"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L61
            throw r1     // Catch: java.lang.Throwable -> L61
        L4b:
            r1 = 3
            java.lang.Object r0 = kotlin.jvm.internal.TypeIntrinsics.beforeCheckcastToFunctionOfArity(r0, r1)     // Catch: java.lang.Throwable -> L61
            kotlin.jvm.functions.Function3 r0 = (kotlin.jvm.functions.Function3) r0     // Catch: java.lang.Throwable -> L61
            r1 = r10
            r2 = r11
            r3 = r7
            java.lang.Object r0 = r0.invoke(r1, r2, r3)     // Catch: java.lang.Throwable -> L61
            r9 = r0
            goto L83
        L61:
            r10 = move-exception
            r0 = r7
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r11
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            r13 = r1
            r1 = 0
            r14 = r1
            r1 = r10
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r1)
            java.lang.Object r1 = kotlin.Result.m2105constructorimpl(r1)
            r0.resumeWith(r1)
            goto L0
        L83:
            r0 = r9
            r8 = r0
            r0 = r8
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r0 == r1) goto Lbb
            r0 = r7
            r9 = r0
            r0 = r8
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r9
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            r12 = r1
            r1 = 0
            r13 = r1
            r1 = r10
            java.lang.Object r1 = kotlin.Result.m2105constructorimpl(r1)
            r0.resumeWith(r1)
            goto Lbb
        Lad:
            r0 = r5
            java.lang.Object r1 = kotlin.DeepRecursiveKt.access$getUNDEFINED_RESULT$p()
            r0.result = r1
            r0 = r7
            r1 = r6
            r0.resumeWith(r1)
        Lbb:
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.DeepRecursiveScopeImpl.runCallLoop():java.lang.Object");
    }
}
