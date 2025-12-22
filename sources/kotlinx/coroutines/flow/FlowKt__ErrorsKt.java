package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Errors.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��v\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ah\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012B\u0010\u0003\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0004¢\u0006\u0002\b\rø\u0001��¢\u0006\u0002\u0010\u000e\u001a1\u0010\u000f\u001a\u0004\u0018\u00010\u0006\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0080@ø\u0001��¢\u0006\u0002\u0010\u0011\u001a\u0019\u0010\u0012\u001a\u00020\u0013*\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¢\u0006\u0002\b\u0016\u001a\u001b\u0010\u0017\u001a\u00020\u0013*\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\b\u0019\u001aB\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001a>\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dH\u0007\u001ac\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020!23\b\u0002\u0010\u001c\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\"ø\u0001��¢\u0006\u0002\u0010#\u001a}\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012W\u0010\u001c\u001aS\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\n\u0012\u0006\u0012\u0004\u0018\u00010\f0%¢\u0006\u0002\b\rø\u0001��¢\u0006\u0002\u0010'*\\\b\u0007\u0010(\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001d2\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u001dB6\b)\u0012\n\b*\u0012\u0006\b\n0+8,\u0012\b\b-\u0012\u0004\b\b(.\u0012\u001c\b/\u0012\u0018\b\u000bB\u0014\b0\u0012\u0006\b1\u0012\u0002\b\f\u0012\b\b2\u0012\u0004\b\b(3\u0082\u0002\u0004\n\u0002\b\u0019¨\u00064"}, d2 = {"catch", "Lkotlinx/coroutines/flow/Flow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "action", "Lkotlin/Function3;", "Lkotlinx/coroutines/flow/FlowCollector;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/flow/Flow;", "catchImpl", "collector", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isCancellationCause", "", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "isCancellationCause$FlowKt__ErrorsKt", "isSameExceptionAs", "other", "isSameExceptionAs$FlowKt__ErrorsKt", "onErrorCollect", "fallback", "predicate", "Lkotlin/Function1;", "retry", "retries", "", "", "Lkotlin/Function2;", "(Lkotlinx/coroutines/flow/Flow;JLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "retryWhen", "Lkotlin/Function4;", "attempt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function4;)Lkotlinx/coroutines/flow/Flow;", "ExceptionPredicate", "Lkotlin/Deprecated;", "level", "Lkotlin/DeprecationLevel;", "ERROR", "message", "Use (Throwable) -> Boolean functional type", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "expression", "(Throwable) -> Boolean", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt.class */
final /* synthetic */ class FlowKt__ErrorsKt {

    /* compiled from: Errors.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Errors.kt", l = {227}, i = {0}, s = {"L$0"}, n = {"fromDownstream"}, m = "catchImpl", c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt$catchImpl$1.class */
    static final class AnonymousClass1<T> extends ContinuationImpl {
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
            return FlowKt.catchImpl(null, null, this);
        }
    }

    @Deprecated(message = "Use (Throwable) -> Boolean functional type", replaceWith = @ReplaceWith(expression = "(Throwable) -> Boolean", imports = {}), level = DeprecationLevel.ERROR)
    public static /* synthetic */ void ExceptionPredicate$annotations() {
    }

    @NotNull
    /* renamed from: catch, reason: not valid java name */
    public static final <T> Flow<T> m4270catch(@NotNull Flow<? extends T> flow, @NotNull Function3<? super FlowCollector<? super T>, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> function3) {
        return new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(flow, function3);
    }

    public static /* synthetic */ Flow onErrorCollect$default(Flow flow, Flow flow2, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = new Function1<Throwable, Boolean>() { // from class: kotlinx.coroutines.flow.FlowKt__ErrorsKt.onErrorCollect.1
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Boolean invoke(@NotNull Throwable it) {
                    return true;
                }
            };
        }
        return FlowKt.onErrorCollect(flow, flow2, function1);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Errors.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/FlowCollector;", "e", ""})
    @DebugMetadata(f = "Errors.kt", l = {89}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$2")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$onErrorCollect$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt$onErrorCollect$2.class */
    static final class AnonymousClass2<T> extends SuspendLambda implements Function3<FlowCollector<? super T>, Throwable, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        final /* synthetic */ Function1<Throwable, Boolean> $predicate;
        final /* synthetic */ Flow<T> $fallback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Throwable, Boolean> function1, Flow<? extends T> flow, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.$predicate = function1;
            this.$fallback = flow;
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super T> flowCollector, @NotNull Throwable p2, @Nullable Continuation<? super Unit> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$predicate, this.$fallback, continuation);
            anonymousClass2.L$0 = flowCollector;
            anonymousClass2.L$1 = p2;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    FlowCollector $this$catch = (FlowCollector) this.L$0;
                    Throwable e = (Throwable) this.L$1;
                    if (!this.$predicate.invoke(e).booleanValue()) {
                        throw e;
                    }
                    this.L$0 = null;
                    this.label = 1;
                    if (FlowKt.emitAll($this$catch, this.$fallback, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Deprecated(message = "Use catch { e -> if (predicate(e)) emitAll(fallback) else throw e }", replaceWith = @ReplaceWith(expression = "catch { e -> if (predicate(e)) emitAll(fallback) else throw e }", imports = {}), level = DeprecationLevel.ERROR)
    @NotNull
    public static final <T> Flow<T> onErrorCollect(@NotNull Flow<? extends T> flow, @NotNull Flow<? extends T> flow2, @NotNull Function1<? super Throwable, Boolean> function1) {
        return FlowKt.m4261catch(flow, new AnonymousClass2(function1, flow2, null));
    }

    public static /* synthetic */ Flow retry$default(Flow flow, long j, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = Long.MAX_VALUE;
        }
        if ((i & 2) != 0) {
            function2 = new C15981(null);
        }
        return FlowKt.retry(flow, j, (Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object>) function2);
    }

    /* compiled from: Errors.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "it", ""})
    @DebugMetadata(f = "Errors.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$1")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt$retry$1.class */
    static final class C15981 extends SuspendLambda implements Function2<Throwable, Continuation<? super Boolean>, Object> {
        int label;

        C15981(Continuation<? super C15981> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
            return new C15981(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull Throwable p1, @Nullable Continuation<? super Boolean> continuation) {
            return ((C15981) create(p1, continuation)).invokeSuspend(Unit.INSTANCE);
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

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Errors.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n��\n\u0002\u0010\t\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/FlowCollector;", "cause", "", "attempt", ""})
    @DebugMetadata(f = "Errors.kt", l = {124}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$3")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt$retry$3.class */
    static final class AnonymousClass3<T> extends SuspendLambda implements Function4<FlowCollector<? super T>, Throwable, Long, Continuation<? super Boolean>, Object> {
        int label;
        /* synthetic */ Object L$0;
        /* synthetic */ long J$0;
        final /* synthetic */ long $retries;
        final /* synthetic */ Function2<Throwable, Continuation<? super Boolean>, Object> $predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass3(long $retries, Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super AnonymousClass3> continuation) {
            super(4, continuation);
            this.$retries = $retries;
            this.$predicate = function2;
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super T> flowCollector, @NotNull Throwable p2, long p3, @Nullable Continuation<? super Boolean> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.$retries, this.$predicate, continuation);
            anonymousClass3.L$0 = p2;
            anonymousClass3.J$0 = p3;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Object invoke(Object p1, Throwable th, Long l, Continuation<? super Boolean> continuation) {
            return invoke((FlowCollector) p1, th, l.longValue(), continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0061  */
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
                r11 = r0
                r0 = r6
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L20;
                    case 1: goto L53;
                    default: goto L6a;
                }
            L20:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r6
                java.lang.Object r0 = r0.L$0
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                r8 = r0
                r0 = r6
                long r0 = r0.J$0
                r9 = r0
                r0 = r9
                r1 = r6
                long r1 = r1.$retries
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 >= 0) goto L65
                r0 = r6
                kotlin.jvm.functions.Function2<java.lang.Throwable, kotlin.coroutines.Continuation<? super java.lang.Boolean>, java.lang.Object> r0 = r0.$predicate
                r1 = r8
                r2 = r6
                r3 = r6
                r4 = 1
                r3.label = r4
                java.lang.Object r0 = r0.invoke(r1, r2)
                r1 = r0
                r2 = r11
                if (r1 != r2) goto L58
                r1 = r11
                return r1
            L53:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            L58:
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto L65
                r0 = 1
                goto L66
            L65:
                r0 = 0
            L66:
                java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
                return r0
            L6a:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @NotNull
    public static final <T> Flow<T> retry(@NotNull Flow<? extends T> flow, long retries, @NotNull Function2<? super Throwable, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        if (!(retries > 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Expected positive amount of retries, but had ", Long.valueOf(retries)).toString());
        }
        return FlowKt.retryWhen(flow, new AnonymousClass3(retries, function2, null));
    }

    public static /* synthetic */ Flow retry$default(Flow flow, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i2 & 2) != 0) {
            function1 = new Function1<Throwable, Boolean>() { // from class: kotlinx.coroutines.flow.FlowKt__ErrorsKt.retry.4
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Boolean invoke(@NotNull Throwable it) {
                    return true;
                }
            };
        }
        return retry(flow, i, function1);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Errors.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0003\n��\n\u0002\u0010\t\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/FlowCollector;", "cause", "", "attempt", ""})
    @DebugMetadata(f = "Errors.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ErrorsKt$retry$6, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ErrorsKt$retry$6.class */
    static final class AnonymousClass6<T> extends SuspendLambda implements Function4<FlowCollector<? super T>, Throwable, Long, Continuation<? super Boolean>, Object> {
        int label;
        /* synthetic */ Object L$0;
        /* synthetic */ long J$0;
        final /* synthetic */ Function1<Throwable, Boolean> $predicate;
        final /* synthetic */ int $retries;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass6(Function1<? super Throwable, Boolean> function1, int $retries, Continuation<? super AnonymousClass6> continuation) {
            super(4, continuation);
            this.$predicate = function1;
            this.$retries = $retries;
        }

        @Nullable
        public final Object invoke(@NotNull FlowCollector<? super T> flowCollector, @NotNull Throwable p2, long p3, @Nullable Continuation<? super Boolean> continuation) {
            AnonymousClass6 anonymousClass6 = new AnonymousClass6(this.$predicate, this.$retries, continuation);
            anonymousClass6.L$0 = p2;
            anonymousClass6.J$0 = p3;
            return anonymousClass6.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Object invoke(Object p1, Throwable th, Long l, Continuation<? super Boolean> continuation) {
            return invoke((FlowCollector) p1, th, l.longValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    Throwable cause = (Throwable) this.L$0;
                    long attempt = this.J$0;
                    return Boxing.boxBoolean(this.$predicate.invoke(cause).booleanValue() && attempt < ((long) this.$retries));
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Deprecated(message = "binary compatibility with retries: Int preview version", level = DeprecationLevel.HIDDEN)
    @FlowPreview
    public static final /* synthetic */ Flow retry(Flow $this$retry, int retries, Function1 predicate) {
        if (!(retries > 0)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Expected positive amount of retries, but had ", Integer.valueOf(retries)).toString());
        }
        return FlowKt.retryWhen($this$retry, new AnonymousClass6(predicate, retries, null));
    }

    @NotNull
    public static final <T> Flow<T> retryWhen(@NotNull Flow<? extends T> flow, @NotNull Function4<? super FlowCollector<? super T>, ? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function4) {
        return new FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1(flow, function4);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object catchImpl(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.FlowCollector<? super T> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Throwable> r8) throws java.lang.Throwable {
        /*
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass1
            if (r0 == 0) goto L27
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ErrorsKt.AnonymousClass1) r0
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
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$1
            r1 = r0
            r2 = r8
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
                case 1: goto L96;
                default: goto Ld6;
            }
        L58:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r6
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1     // Catch: java.lang.Throwable -> Lae
            r2 = r1
            r3 = r7
            r4 = r9
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lae
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1     // Catch: java.lang.Throwable -> Lae
            r2 = r13
            r3 = r13
            r4 = r9
            r3.L$0 = r4     // Catch: java.lang.Throwable -> Lae
            r3 = r13
            r4 = 1
            r3.label = r4     // Catch: java.lang.Throwable -> Lae
            java.lang.Object r0 = r0.collect(r1, r2)     // Catch: java.lang.Throwable -> Lae
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Laa
            r1 = r14
            return r1
        L96:
            r0 = 0
            r11 = r0
            r0 = r13
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r9 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Throwable -> Lae
            r0 = r12
        Laa:
            goto Ld4
        Lae:
            r10 = move-exception
            r0 = r10
            r1 = r9
            T r1 = r1.element
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            boolean r0 = isSameExceptionAs$FlowKt__ErrorsKt(r0, r1)
            if (r0 != 0) goto Lce
            r0 = r10
            r1 = r13
            kotlin.coroutines.CoroutineContext r1 = r1.getContext()
            boolean r0 = isCancellationCause$FlowKt__ErrorsKt(r0, r1)
            if (r0 == 0) goto Ld1
        Lce:
            r0 = r10
            throw r0
        Ld1:
            r0 = r10
            return r0
        Ld4:
            r0 = 0
            return r0
        Ld6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ErrorsKt.catchImpl(kotlinx.coroutines.flow.Flow, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean isCancellationCause$FlowKt__ErrorsKt(Throwable $this$isCancellationCause, CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key);
        if (job == null || !job.isCancelled()) {
            return false;
        }
        return isSameExceptionAs$FlowKt__ErrorsKt($this$isCancellationCause, job.getCancellationException());
    }

    private static final boolean isSameExceptionAs$FlowKt__ErrorsKt(Throwable $this$isSameExceptionAs, Throwable other) {
        if (other != null) {
            if (Intrinsics.areEqual(!DebugKt.getRECOVER_STACK_TRACES() ? other : StackTraceRecoveryKt.unwrapImpl(other), !DebugKt.getRECOVER_STACK_TRACES() ? $this$isSameExceptionAs : StackTraceRecoveryKt.unwrapImpl($this$isSameExceptionAs))) {
                return true;
            }
        }
        return false;
    }
}
