package kotlinx.coroutines.flow;

import ch.qos.logback.core.CoreConstants;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.InlineMarker;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Lint.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��\u0082\u0001\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n��\n\u0002\u0010\"\n��\u001a\"\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u00022\u0010\b\u0002\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fH\u0007\u001a\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0007\u001am\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132D\b\b\u0010\u0015\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0002\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0016¢\u0006\u0002\b\u001cH\u0087\bø\u0001��¢\u0006\u0002\u0010\u001d\u001a\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u001fH\u0007\u001a!\u0010 \u001a\u00020!\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0087Hø\u0001��¢\u0006\u0002\u0010\"\u001a\u001e\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u001fH\u0007\u001a&\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010%\u001a\u00020\u0001H\u0007\u001af\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\b\b\u0002\u0010'\u001a\u00020(23\b\n\u0010)\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0*H\u0087\bø\u0001��¢\u0006\u0002\u0010+\u001a\u0082\u0001\u0010,\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132Y\b\b\u0010)\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00120\u0002\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110(¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(.\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0-¢\u0006\u0002\b\u001cH\u0087\bø\u0001��¢\u0006\u0002\u0010/\u001a'\u00100\u001a\b\u0012\u0004\u0012\u0002H\u001201\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0087Hø\u0001��¢\u0006\u0002\u0010\"\u001a'\u00102\u001a\b\u0012\u0004\u0012\u0002H\u001203\"\u0004\b��\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u0013H\u0087Hø\u0001��¢\u0006\u0002\u0010\"\"\"\u0010��\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\"\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\u0007\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/flow/FlowCollector;", "getCoroutineContext$annotations", "(Lkotlinx/coroutines/flow/FlowCollector;)V", "getCoroutineContext", "(Lkotlinx/coroutines/flow/FlowCollector;)Lkotlin/coroutines/CoroutineContext;", "isActive", "", "isActive$annotations", "(Lkotlinx/coroutines/flow/FlowCollector;)Z", "cancel", "", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancellable", "Lkotlinx/coroutines/flow/Flow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/SharedFlow;", "catch", "action", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "conflate", "Lkotlinx/coroutines/flow/StateFlow;", "count", "", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distinctUntilChanged", "flowOn", CoreConstants.CONTEXT_SCOPE_VALUE, "retry", "retries", "", "predicate", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/SharedFlow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "toList", "", "toSet", "", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/LintKt.class */
public final class LintKt {
    @Deprecated(message = "isActive is resolved into the extension of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext().isActive or cancellable() operator instead or specify the receiver of isActive explicitly. Additionally, flow {} builder emissions are cancellable by default.", replaceWith = @ReplaceWith(expression = "currentCoroutineContext().isActive", imports = {}), level = DeprecationLevel.ERROR)
    public static /* synthetic */ void isActive$annotations(FlowCollector $this$isActive_u24annotations) {
    }

    @Deprecated(message = "coroutineContext is resolved into the property of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext() instead or specify the receiver of coroutineContext explicitly", replaceWith = @ReplaceWith(expression = "currentCoroutineContext()", imports = {}), level = DeprecationLevel.ERROR)
    public static /* synthetic */ void getCoroutineContext$annotations(FlowCollector $this$getCoroutineContext_u24annotations) {
    }

    @Deprecated(message = "Applying 'cancellable' to a SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.ERROR)
    @NotNull
    public static final <T> Flow<T> cancellable(@NotNull SharedFlow<? extends T> sharedFlow) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(message = "Applying 'flowOn' to SharedFlow has no effect. See the SharedFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.ERROR)
    @NotNull
    public static final <T> Flow<T> flowOn(@NotNull SharedFlow<? extends T> sharedFlow, @NotNull CoroutineContext context) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(message = "Applying 'conflate' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.ERROR)
    @NotNull
    public static final <T> Flow<T> conflate(@NotNull StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(message = "Applying 'distinctUntilChanged' to StateFlow has no effect. See the StateFlow documentation on Operator Fusion.", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.ERROR)
    @NotNull
    public static final <T> Flow<T> distinctUntilChanged(@NotNull StateFlow<? extends T> stateFlow) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    public static final boolean isActive(@NotNull FlowCollector<?> flowCollector) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(message = "cancel() is resolved into the extension of outer CoroutineScope which is likely to be an error.Use currentCoroutineContext().cancel() instead or specify the receiver of cancel() explicitly", replaceWith = @ReplaceWith(expression = "currentCoroutineContext().cancel(cause)", imports = {}), level = DeprecationLevel.ERROR)
    public static final void cancel(@NotNull FlowCollector<?> flowCollector, @Nullable CancellationException cause) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void cancel$default(FlowCollector flowCollector, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = null;
        }
        cancel(flowCollector, cancellationException);
    }

    @NotNull
    public static final CoroutineContext getCoroutineContext(@NotNull FlowCollector<?> flowCollector) {
        FlowKt.noImpl();
        throw new KotlinNothingValueException();
    }

    @Deprecated(message = "SharedFlow never completes, so this operator typically has not effect, it can only catch exceptions from 'onSubscribe' operator", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.WARNING)
    @InlineOnly
    /* renamed from: catch, reason: not valid java name */
    private static final <T> Flow<T> m4277catch(SharedFlow<? extends T> sharedFlow, Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return FlowKt.m4261catch(sharedFlow, function3);
    }

    static /* synthetic */ Flow retry$default(SharedFlow $this$retry_u24default, long retries, Function2 predicate, int i, Object obj) {
        if ((i & 1) != 0) {
            retries = Long.MAX_VALUE;
        }
        if ((i & 2) != 0) {
            predicate = new AnonymousClass1(null);
        }
        return FlowKt.retry($this$retry_u24default, retries, (Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object>) predicate);
    }

    /* compiled from: Lint.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "it", ""})
    @DebugMetadata(f = "Lint.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.LintKt$retry$1")
    /* renamed from: kotlinx.coroutines.flow.LintKt$retry$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/LintKt$retry$1.class */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<Throwable, Continuation<? super Boolean>, Object> {
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull Throwable p1, @Nullable Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(p1, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    return Boxing.boxBoolean(true);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Deprecated(message = "SharedFlow never completes, so this operator has no effect.", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final <T> Flow<T> retry(SharedFlow<? extends T> sharedFlow, long retries, Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return FlowKt.retry(sharedFlow, retries, function2);
    }

    @Deprecated(message = "SharedFlow never completes, so this operator has no effect.", replaceWith = @ReplaceWith(expression = "this", imports = {}), level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final <T> Flow<T> retryWhen(SharedFlow<? extends T> sharedFlow, Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function4) {
        return FlowKt.retryWhen(sharedFlow, function4);
    }

    @Deprecated(message = "SharedFlow never completes, so this terminal operation never completes.", level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final <T> Object toList(SharedFlow<? extends T> sharedFlow, Continuation<? super List<? extends T>> continuation) {
        InlineMarker.mark(0);
        Object list$default = FlowKt__CollectionKt.toList$default(sharedFlow, null, continuation, 1, null);
        InlineMarker.mark(1);
        return list$default;
    }

    @Deprecated(message = "SharedFlow never completes, so this terminal operation never completes.", level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final <T> Object toSet(SharedFlow<? extends T> sharedFlow, Continuation<? super Set<? extends T>> continuation) {
        InlineMarker.mark(0);
        Object set$default = FlowKt__CollectionKt.toSet$default(sharedFlow, null, continuation, 1, null);
        InlineMarker.mark(1);
        return set$default;
    }

    @Deprecated(message = "SharedFlow never completes, so this terminal operation never completes.", level = DeprecationLevel.WARNING)
    @InlineOnly
    private static final <T> Object count(SharedFlow<? extends T> sharedFlow, Continuation<? super Integer> continuation) {
        InlineMarker.mark(0);
        Object objCount = FlowKt.count(sharedFlow, continuation);
        InlineMarker.mark(1);
        return objCount;
    }
}
