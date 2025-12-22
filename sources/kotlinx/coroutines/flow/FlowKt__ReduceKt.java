package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: Reduce.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u001a!\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001aE\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a#\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001aG\u0010\n\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\"\u0010\u0004\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001ay\u0010\u000b\u001a\u0002H\f\"\u0004\b��\u0010\u0001\"\u0004\b\u0001\u0010\f*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\r\u001a\u0002H\f2H\b\u0004\u0010\u000e\u001aB\b\u0001\u0012\u0013\u0012\u0011H\f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000fH\u0086Hø\u0001��¢\u0006\u0002\u0010\u0014\u001a!\u0010\u0015\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a#\u0010\u0016\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001as\u0010\u0017\u001a\u0002H\u0018\"\u0004\b��\u0010\u0018\"\b\b\u0001\u0010\u0001*\u0002H\u0018*\b\u0012\u0004\u0012\u0002H\u00010\u00022F\u0010\u000e\u001aB\b\u0001\u0012\u0013\u0012\u0011H\u0018¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u000fH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a!\u0010\u001b\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a#\u0010\u001c\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"first", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "predicate", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "firstOrNull", "fold", "R", "initial", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "acc", "value", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastOrNull", "reduce", "S", "accumulator", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "single", "singleOrNull", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt.class */
final /* synthetic */ class FlowKt__ReduceKt {

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {183}, i = {0, 0}, s = {"L$0", "L$1"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE, "collector$iv"}, m = "first", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$first$1.class */
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
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
            return FlowKt.first(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {183}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"predicate", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "collector$iv"}, m = "first", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$first$3.class */
    static final class AnonymousClass3<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.first(null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {183}, i = {0, 0}, s = {"L$0", "L$1"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE, "collector$iv"}, m = "firstOrNull", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$firstOrNull$1.class */
    static final class C16031<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C16031(Continuation<? super C16031> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.firstOrNull(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {183}, i = {0, 0}, s = {"L$0", "L$1"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE, "collector$iv"}, m = "firstOrNull", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$firstOrNull$3.class */
    static final class C16043<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C16043(Continuation<? super C16043> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.firstOrNull(null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {173}, i = {0}, s = {"L$0"}, n = {"accumulator"}, m = "fold", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$fold$1.class */
    static final class C16051<T, R> extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C16051(Continuation<? super C16051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__ReduceKt.fold(null, null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {173}, i = {0}, s = {"L$0"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE}, m = "last", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$last$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$last$1.class */
    static final class C16061<T> extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C16061(Continuation<? super C16061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.last(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {173}, i = {0}, s = {"L$0"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE}, m = "lastOrNull", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$lastOrNull$1.class */
    static final class C16071<T> extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C16071(Continuation<? super C16071> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.lastOrNull(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {173}, i = {0}, s = {"L$0"}, n = {"accumulator"}, m = "reduce", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$reduce$1.class */
    static final class C16081<S, T extends S> extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C16081(Continuation<? super C16081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.reduce(null, null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {173}, i = {0}, s = {"L$0"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE}, m = "single", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$single$1.class */
    static final class C16091<T> extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C16091(Continuation<? super C16091> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.single(null, this);
        }
    }

    /* compiled from: Reduce.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "Reduce.kt", l = {183}, i = {0, 0}, s = {"L$0", "L$1"}, n = {CacheOperationExpressionEvaluator.RESULT_VARIABLE, "collector$iv"}, m = "singleOrNull", c = "kotlinx.coroutines.flow.FlowKt__ReduceKt")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$singleOrNull$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ReduceKt$singleOrNull$1.class */
    static final class C16101<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C16101(Continuation<? super C16101> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return FlowKt.singleOrNull(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, kotlinx.coroutines.internal.Symbol] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <S, T extends S> java.lang.Object reduce(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super S, ? super T, ? super kotlin.coroutines.Continuation<? super S>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super S> r8) throws java.lang.Throwable {
        /*
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16081
            if (r0 == 0) goto L27
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16081) r0
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
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$1
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
                case 1: goto L9c;
                default: goto Lc9;
            }
        L58:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r9
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r0.element = r1
            r0 = r6
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r10
            kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$$inlined$collect$1
            r2 = r1
            r3 = r9
            r4 = r7
            r2.<init>(r3, r4)
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            r2 = r13
            r3 = r13
            r4 = r9
            r3.L$0 = r4
            r3 = r13
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.collect(r1, r2)
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Laf
            r1 = r14
            return r1
        L9c:
            r0 = 0
            r11 = r0
            r0 = r13
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r9 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Laf:
            r0 = r9
            T r0 = r0.element
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r0 != r1) goto Lc4
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r1 = r0
            java.lang.String r2 = "Empty flow can't be reduced"
            r1.<init>(r2)
            throw r0
        Lc4:
            r0 = r9
            T r0 = r0.element
            return r0
        Lc9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.reduce(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T, R> java.lang.Object fold(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, R r7, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super R, ? super T, ? super kotlin.coroutines.Continuation<? super R>, ? extends java.lang.Object> r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super R> r9) throws java.lang.Throwable {
        /*
            r0 = r9
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16051
            if (r0 == 0) goto L27
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16051) r0
            r15 = r0
            r0 = r15
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r15
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$1
            r1 = r0
            r2 = r9
            r1.<init>(r2)
            r15 = r0
        L31:
            r0 = r15
            java.lang.Object r0 = r0.result
            r14 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r16 = r0
            r0 = r15
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto La1;
                default: goto Lbf;
            }
        L58:
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = 0
            r10 = r0
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r11 = r0
            r0 = r11
            r1 = r7
            r0.element = r1
            r0 = r6
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$$inlined$collect$1
            r2 = r1
            r3 = r11
            r4 = r8
            r2.<init>(r3, r4)
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            r2 = r15
            r3 = r15
            r4 = r11
            r3.L$0 = r4
            r3 = r15
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.collect(r1, r2)
            r1 = r0
            r2 = r16
            if (r1 != r2) goto Lb8
            r1 = r16
            return r1
        La1:
            r0 = 0
            r10 = r0
            r0 = 0
            r13 = r0
            r0 = r15
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r11 = r0
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r14
        Lb8:
            r0 = r11
            T r0 = r0.element
            return r0
        Lbf:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.fold(kotlinx.coroutines.flow.Flow, java.lang.Object, kotlin.jvm.functions.Function3, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T, R> Object fold$$forInline(Flow<? extends T> flow, R r, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super R> continuation) {
        Ref.ObjectRef accumulator = new Ref.ObjectRef();
        accumulator.element = r;
        FlowKt__ReduceKt$fold$$inlined$collect$1 flowKt__ReduceKt$fold$$inlined$collect$1 = new FlowKt__ReduceKt$fold$$inlined$collect$1(accumulator, function3);
        InlineMarker.mark(0);
        flow.collect(flowKt__ReduceKt$fold$$inlined$collect$1, continuation);
        InlineMarker.mark(1);
        return accumulator.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object single(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) throws java.lang.Throwable {
        /*
            r0 = r7
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16091
            if (r0 == 0) goto L27
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16091) r0
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
            goto L31
        L27:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$single$1
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r12 = r0
        L31:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L99;
                default: goto Lc6;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r8 = r0
            r0 = r8
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r0.element = r1
            r0 = r6
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$single$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$single$$inlined$collect$1
            r2 = r1
            r3 = r8
            r2.<init>()
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            r2 = r12
            r3 = r12
            r4 = r8
            r3.L$0 = r4
            r3 = r12
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.collect(r1, r2)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto Lac
            r1 = r13
            return r1
        L99:
            r0 = 0
            r10 = r0
            r0 = r12
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r8 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        Lac:
            r0 = r8
            T r0 = r0.element
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r0 != r1) goto Lc1
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r1 = r0
            java.lang.String r2 = "Flow is empty"
            r1.<init>(r2)
            throw r0
        Lc1:
            r0 = r8
            T r0 = r0.element
            return r0
        Lc6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.single(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object singleOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.singleOrNull(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object first(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.first(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object first(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.first(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object firstOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) throws java.lang.Throwable {
        /*
            r0 = r7
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16031
            if (r0 == 0) goto L27
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16031) r0
            r14 = r0
            r0 = r14
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r14
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$1
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r14 = r0
        L31:
            r0 = r14
            java.lang.Object r0 = r0.result
            r13 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r15 = r0
            r0 = r14
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L9e;
                default: goto Ld2;
            }
        L58:
            r0 = r13
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r8 = r0
            r0 = r6
            r9 = r0
            r0 = 0
            r10 = r0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1
            r1 = r0
            r2 = r8
            r1.<init>()
            r11 = r0
            r0 = r9
            r1 = r11
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc0
            r2 = r14
            r3 = r14
            r4 = r8
            r3.L$0 = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc0
            r3 = r14
            r4 = r11
            r3.L$1 = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc0
            r3 = r14
            r4 = 1
            r3.label = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc0
            java.lang.Object r0 = r0.collect(r1, r2)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc0
            r1 = r0
            r2 = r15
            if (r1 != r2) goto Lbc
            r1 = r15
            return r1
        L9e:
            r0 = 0
            r10 = r0
            r0 = r14
            java.lang.Object r0 = r0.L$1
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$1) r0
            r11 = r0
            r0 = r14
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r8 = r0
            r0 = r13
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc0
            r0 = r13
        Lbc:
            goto Lcc
        Lc0:
            r12 = move-exception
            r0 = r12
            r1 = r11
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlinx.coroutines.flow.internal.FlowExceptions_commonKt.checkOwnership(r0, r1)
        Lcc:
            r0 = r8
            T r0 = r0.element
            return r0
        Ld2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object firstOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super T, ? super kotlin.coroutines.Continuation<? super java.lang.Boolean>, ? extends java.lang.Object> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r8) throws java.lang.Throwable {
        /*
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16043
            if (r0 == 0) goto L27
            r0 = r8
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$3 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16043) r0
            r15 = r0
            r0 = r15
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r15
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$3 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$3
            r1 = r0
            r2 = r8
            r1.<init>(r2)
            r15 = r0
        L31:
            r0 = r15
            java.lang.Object r0 = r0.result
            r14 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r16 = r0
            r0 = r15
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto La1;
                default: goto Ld5;
            }
        L58:
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r9 = r0
            r0 = r6
            r10 = r0
            r0 = 0
            r11 = r0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2
            r1 = r0
            r2 = r7
            r3 = r9
            r1.<init>(r2, r3)
            r12 = r0
            r0 = r10
            r1 = r12
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc3
            r2 = r15
            r3 = r15
            r4 = r9
            r3.L$0 = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc3
            r3 = r15
            r4 = r12
            r3.L$1 = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc3
            r3 = r15
            r4 = 1
            r3.label = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc3
            java.lang.Object r0 = r0.collect(r1, r2)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc3
            r1 = r0
            r2 = r16
            if (r1 != r2) goto Lbf
            r1 = r16
            return r1
        La1:
            r0 = 0
            r11 = r0
            r0 = r15
            java.lang.Object r0 = r0.L$1
            kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2) r0
            r12 = r0
            r0 = r15
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r9 = r0
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> Lc3
            r0 = r14
        Lbf:
            goto Lcf
        Lc3:
            r13 = move-exception
            r0 = r13
            r1 = r12
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            kotlinx.coroutines.flow.internal.FlowExceptions_commonKt.checkOwnership(r0, r1)
        Lcf:
            r0 = r9
            T r0 = r0.element
            return r0
        Ld5:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.firstOrNull(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object last(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) throws java.lang.Throwable {
        /*
            r0 = r7
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16061
            if (r0 == 0) goto L27
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$last$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16061) r0
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
            goto L31
        L27:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$last$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$last$1
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r12 = r0
        L31:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L99;
                default: goto Lc6;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r8 = r0
            r0 = r8
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r0.element = r1
            r0 = r6
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$last$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$last$$inlined$collect$1
            r2 = r1
            r3 = r8
            r2.<init>()
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            r2 = r12
            r3 = r12
            r4 = r8
            r3.L$0 = r4
            r3 = r12
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.collect(r1, r2)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto Lac
            r1 = r13
            return r1
        L99:
            r0 = 0
            r10 = r0
            r0 = r12
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r8 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        Lac:
            r0 = r8
            T r0 = r0.element
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r0 != r1) goto Lc1
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r1 = r0
            java.lang.String r2 = "Expected at least one element"
            r1.<init>(r2)
            throw r0
        Lc1:
            r0 = r8
            T r0 = r0.element
            return r0
        Lc6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.last(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object lastOrNull(@org.jetbrains.annotations.NotNull kotlinx.coroutines.flow.Flow<? extends T> r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super T> r7) throws java.lang.Throwable {
        /*
            r0 = r7
            boolean r0 = r0 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt.C16071
            if (r0 == 0) goto L27
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt.C16071) r0
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
            goto L31
        L27:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$1
            r1 = r0
            r2 = r7
            r1.<init>(r2)
            r12 = r0
        L31:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L92;
                default: goto Lab;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r1 = r0
            r1.<init>()
            r8 = r0
            r0 = r6
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$$inlined$collect$1 r1 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$lastOrNull$$inlined$collect$1
            r2 = r1
            r3 = r8
            r2.<init>()
            kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
            r2 = r12
            r3 = r12
            r4 = r8
            r3.L$0 = r4
            r3 = r12
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.collect(r1, r2)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto La5
            r1 = r13
            return r1
        L92:
            r0 = 0
            r10 = r0
            r0 = r12
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            r8 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        La5:
            r0 = r8
            T r0 = r0.element
            return r0
        Lab:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt.lastOrNull(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
