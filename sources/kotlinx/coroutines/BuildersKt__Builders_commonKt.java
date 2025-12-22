package kotlinx.coroutines;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugCoroutineInfoImplKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Builders.common.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��J\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001aU\u0010\u0004\u001a\u0002H\u0005\"\u0004\b��\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086@ø\u0001��\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u000e\u001a[\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0010\"\u0004\b��\u0010\u0005*\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rø\u0001��¢\u0006\u0002\u0010\u0013\u001aF\u0010\u0014\u001a\u0002H\u0005\"\u0004\b��\u0010\u0005*\u00020\u00152)\b\b\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086Jø\u0001��¢\u0006\u0002\u0010\u0016\u001aO\u0010\u0017\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rø\u0001��¢\u0006\u0002\u0010\u001a\"\u000e\u0010��\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"RESUMED", "", DebugCoroutineInfoImplKt.SUSPENDED, "UNDECIDED", "withContext", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "async", "Lkotlinx/coroutines/Deferred;", "start", "Lkotlinx/coroutines/CoroutineStart;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", "invoke", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launch", "Lkotlinx/coroutines/Job;", "", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/BuildersKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/BuildersKt__Builders_commonKt.class */
public final /* synthetic */ class BuildersKt__Builders_commonKt {
    private static final int UNDECIDED = 0;
    private static final int SUSPENDED = 1;
    private static final int RESUMED = 2;

    public static /* synthetic */ Job launch$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return BuildersKt.launch(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    @NotNull
    public static final Job launch(@NotNull CoroutineScope $this$launch, @NotNull CoroutineContext context, @NotNull CoroutineStart start, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        LazyStandaloneCoroutine standaloneCoroutine;
        CoroutineContext newContext = CoroutineContextKt.newCoroutineContext($this$launch, context);
        if (start.isLazy()) {
            standaloneCoroutine = new LazyStandaloneCoroutine(newContext, function2);
        } else {
            standaloneCoroutine = new StandaloneCoroutine(newContext, true);
        }
        StandaloneCoroutine coroutine = standaloneCoroutine;
        coroutine.start(start, coroutine, function2);
        return coroutine;
    }

    public static /* synthetic */ Deferred async$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return BuildersKt.async(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    @NotNull
    public static final <T> Deferred<T> async(@NotNull CoroutineScope $this$async, @NotNull CoroutineContext context, @NotNull CoroutineStart start, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        LazyDeferredCoroutine deferredCoroutine;
        CoroutineContext newContext = CoroutineContextKt.newCoroutineContext($this$async, context);
        if (start.isLazy()) {
            deferredCoroutine = new LazyDeferredCoroutine(newContext, function2);
        } else {
            deferredCoroutine = new DeferredCoroutine(newContext, true);
        }
        DeferredCoroutine coroutine = deferredCoroutine;
        coroutine.start(start, coroutine, function2);
        return coroutine;
    }

    @Nullable
    public static final <T> Object withContext(@NotNull CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) throws Throwable {
        Object result;
        CoroutineContext oldContext = continuation.getContext();
        CoroutineContext newContext = oldContext.plus(context);
        JobKt.ensureActive(newContext);
        if (newContext == oldContext) {
            ScopeCoroutine coroutine = new ScopeCoroutine(newContext, continuation);
            result = UndispatchedKt.startUndispatchedOrReturn(coroutine, coroutine, function2);
        } else if (Intrinsics.areEqual(newContext.get(ContinuationInterceptor.Key), oldContext.get(ContinuationInterceptor.Key))) {
            UndispatchedCoroutine coroutine2 = new UndispatchedCoroutine(newContext, continuation);
            Object oldValue$iv = ThreadContextKt.updateThreadContext(newContext, null);
            try {
                Object objStartUndispatchedOrReturn = UndispatchedKt.startUndispatchedOrReturn(coroutine2, coroutine2, function2);
                ThreadContextKt.restoreThreadContext(newContext, oldValue$iv);
                result = objStartUndispatchedOrReturn;
            } catch (Throwable th) {
                ThreadContextKt.restoreThreadContext(newContext, oldValue$iv);
                throw th;
            }
        } else {
            DispatchedCoroutine coroutine3 = new DispatchedCoroutine(newContext, continuation);
            CancellableKt.startCoroutineCancellable$default(function2, coroutine3, coroutine3, null, 4, null);
            result = coroutine3.getResult();
        }
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Nullable
    public static final <T> Object invoke(@NotNull CoroutineDispatcher $this$invoke, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return BuildersKt.withContext($this$invoke, function2, continuation);
    }

    private static final <T> Object invoke$$forInline(CoroutineDispatcher $this$invoke, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) throws Throwable {
        InlineMarker.mark(0);
        Object objWithContext = BuildersKt.withContext($this$invoke, function2, continuation);
        InlineMarker.mark(1);
        return objWithContext;
    }
}
