package kotlinx.coroutines.flow;

import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlin.time.Duration;
import kotlin.time.ExperimentalTime;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.FlowCoroutineKt;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Delay.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��,\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a2\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0007\u001a:\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\u0004H\u0007ø\u0001��¢\u0006\u0002\b\b\u001a&\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0005H\u0007\u001a3\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a7\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0002\b\r\u001a$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u0005H��\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0005H\u0007\u001a3\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0007H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"debounce", "Lkotlinx/coroutines/flow/Flow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "timeoutMillis", "Lkotlin/Function1;", "", RtspHeaders.Values.TIMEOUT, "Lkotlin/time/Duration;", "debounceDuration", "debounce-HG0u8IE", "(Lkotlinx/coroutines/flow/Flow;J)Lkotlinx/coroutines/flow/Flow;", "debounceInternal", "timeoutMillisSelector", "debounceInternal$FlowKt__DelayKt", "fixedPeriodTicker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "Lkotlinx/coroutines/CoroutineScope;", "delayMillis", "initialDelayMillis", "sample", "periodMillis", "period", "sample-HG0u8IE", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__DelayKt.class */
final /* synthetic */ class FlowKt__DelayKt {
    /* JADX WARN: Multi-variable type inference failed */
    @FlowPreview
    @NotNull
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, final long timeoutMillis) {
        if (timeoutMillis >= 0) {
            return timeoutMillis == 0 ? flow : debounceInternal$FlowKt__DelayKt(flow, new Function1<T, Long>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt.debounce.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Long invoke(T t) {
                    return Long.valueOf(timeoutMillis);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Long invoke(Object obj) {
                    return invoke((AnonymousClass2<T>) obj);
                }
            });
        }
        throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
    }

    @FlowPreview
    @OverloadResolutionByLambdaReturnType
    @NotNull
    public static final <T> Flow<T> debounce(@NotNull Flow<? extends T> flow, @NotNull Function1<? super T, Long> function1) {
        return debounceInternal$FlowKt__DelayKt(flow, function1);
    }

    @FlowPreview
    @ExperimentalTime
    @NotNull
    /* renamed from: debounce-HG0u8IE, reason: not valid java name */
    public static final <T> Flow<T> m4263debounceHG0u8IE(@NotNull Flow<? extends T> flow, long timeout) {
        return FlowKt.debounce(flow, DelayKt.m4180toDelayMillisLRDsOJo(timeout));
    }

    @FlowPreview
    @JvmName(name = "debounceDuration")
    @NotNull
    @ExperimentalTime
    @OverloadResolutionByLambdaReturnType
    public static final <T> Flow<T> debounceDuration(@NotNull Flow<? extends T> flow, @NotNull final Function1<? super T, Duration> function1) {
        return debounceInternal$FlowKt__DelayKt(flow, new Function1<T, Long>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt.debounce.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Long invoke(Object obj) {
                return invoke((AnonymousClass3<T>) obj);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Long invoke(T t) {
                return Long.valueOf(DelayKt.m4180toDelayMillisLRDsOJo(function1.invoke(t).m4109unboximpl()));
            }
        });
    }

    private static final <T> Flow<T> debounceInternal$FlowKt__DelayKt(Flow<? extends T> flow, Function1<? super T, Long> function1) {
        return FlowCoroutineKt.scopedFlow(new FlowKt__DelayKt$debounceInternal$1(function1, flow, null));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* compiled from: Delay.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/CoroutineScope;", "downstream", "Lkotlinx/coroutines/flow/FlowCollector;"})
    @DebugMetadata(f = "Delay.kt", l = {355}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"downstream", "values", "lastValue", "ticker"}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__DelayKt$sample$2.class */
    static final class C15962<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        Object L$2;
        Object L$3;
        int label;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        final /* synthetic */ long $periodMillis;
        final /* synthetic */ Flow<T> $this_sample;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C15962(long $periodMillis, Flow<? extends T> flow, Continuation<? super C15962> continuation) {
            super(3, continuation);
            this.$periodMillis = $periodMillis;
            this.$this_sample = flow;
        }

        @Override // kotlin.jvm.functions.Function3
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @NotNull FlowCollector<? super T> flowCollector, @Nullable Continuation<? super Unit> continuation) {
            C15962 c15962 = new C15962(this.$periodMillis, this.$this_sample, continuation);
            c15962.L$0 = p1;
            c15962.L$1 = flowCollector;
            return c15962.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            ReceiveChannel ticker;
            Ref.ObjectRef lastValue;
            ReceiveChannel values;
            FlowCollector downstream;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$scopedFlow = (CoroutineScope) this.L$0;
                    downstream = (FlowCollector) this.L$1;
                    values = ProduceKt.produce$default($this$scopedFlow, null, -1, new FlowKt__DelayKt$sample$2$values$1(this.$this_sample, null), 1, null);
                    lastValue = new Ref.ObjectRef();
                    ticker = FlowKt__DelayKt.fixedPeriodTicker$default($this$scopedFlow, this.$periodMillis, 0L, 2, null);
                    break;
                case 1:
                    ticker = (ReceiveChannel) this.L$3;
                    lastValue = (Ref.ObjectRef) this.L$2;
                    values = (ReceiveChannel) this.L$1;
                    downstream = (FlowCollector) this.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            while (lastValue.element != NullSurrogateKt.DONE) {
                this.L$0 = downstream;
                this.L$1 = values;
                this.L$2 = lastValue;
                this.L$3 = ticker;
                this.label = 1;
                C15962<T> uCont$iv = this;
                SelectBuilderImpl scope$iv = new SelectBuilderImpl(uCont$iv);
                try {
                    SelectBuilderImpl $this$invokeSuspend_u24lambda_u2d0 = scope$iv;
                    $this$invokeSuspend_u24lambda_u2d0.invoke(values.getOnReceiveCatching(), new FlowKt__DelayKt$sample$2$1$1(lastValue, ticker, null));
                    $this$invokeSuspend_u24lambda_u2d0.invoke(ticker.getOnReceive(), new FlowKt__DelayKt$sample$2$1$2(lastValue, downstream, null));
                } catch (Throwable e$iv) {
                    scope$iv.handleBuilderException(e$iv);
                }
                Object result = scope$iv.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(this);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    @FlowPreview
    @NotNull
    public static final <T> Flow<T> sample(@NotNull Flow<? extends T> flow, long periodMillis) {
        if (!(periodMillis > 0)) {
            throw new IllegalArgumentException("Sample period should be positive".toString());
        }
        return FlowCoroutineKt.scopedFlow(new C15962(periodMillis, flow, null));
    }

    public static /* synthetic */ ReceiveChannel fixedPeriodTicker$default(CoroutineScope coroutineScope, long j, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        return FlowKt.fixedPeriodTicker(coroutineScope, j, j2);
    }

    @NotNull
    public static final ReceiveChannel<Unit> fixedPeriodTicker(@NotNull CoroutineScope $this$fixedPeriodTicker, long delayMillis, long initialDelayMillis) {
        if (!(delayMillis >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + delayMillis + " ms").toString());
        }
        if (!(initialDelayMillis >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + initialDelayMillis + " ms").toString());
        }
        return ProduceKt.produce$default($this$fixedPeriodTicker, null, 0, new C15953(initialDelayMillis, delayMillis, null), 1, null);
    }

    /* compiled from: Delay.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;"})
    @DebugMetadata(f = "Delay.kt", l = {316, 318, 319}, i = {0, 1, 2}, s = {"L$0", "L$0", "L$0"}, n = {"$this$produce", "$this$produce", "$this$produce"}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__DelayKt$fixedPeriodTicker$3.class */
    static final class C15953 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ long $initialDelayMillis;
        final /* synthetic */ long $delayMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C15953(long $initialDelayMillis, long $delayMillis, Continuation<? super C15953> continuation) {
            super(2, continuation);
            this.$initialDelayMillis = $initialDelayMillis;
            this.$delayMillis = $delayMillis;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
            C15953 c15953 = new C15953(this.$initialDelayMillis, this.$delayMillis, continuation);
            c15953.L$0 = value;
            return c15953;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super Unit> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((C15953) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:12:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x00a9  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00a6 -> B:10:0x005e). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r9 = r0
                r0 = r6
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L28;
                    case 1: goto L50;
                    case 2: goto L81;
                    case 3: goto Lab;
                    default: goto Lbc;
                }
            L28:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r6
                long r0 = r0.$initialDelayMillis
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = r6
                r3 = r8
                r2.L$0 = r3
                r2 = r6
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = kotlinx.coroutines.DelayKt.delay(r0, r1)
                r1 = r0
                r2 = r9
                if (r1 != r2) goto L5d
                r1 = r9
                return r1
            L50:
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            L5d:
            L5e:
                r0 = r8
                kotlinx.coroutines.channels.SendChannel r0 = r0.getChannel()
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = r6
                r4 = r8
                r3.L$0 = r4
                r3 = r6
                r4 = 2
                r3.label = r4
                java.lang.Object r0 = r0.send(r1, r2)
                r1 = r0
                r2 = r9
                if (r1 != r2) goto L8e
                r1 = r9
                return r1
            L81:
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            L8e:
                r0 = r6
                long r0 = r0.$delayMillis
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = r6
                r3 = r8
                r2.L$0 = r3
                r2 = r6
                r3 = 3
                r2.label = r3
                java.lang.Object r0 = kotlinx.coroutines.DelayKt.delay(r0, r1)
                r1 = r0
                r2 = r9
                if (r1 != r2) goto Lb8
                r1 = r9
                return r1
            Lab:
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            Lb8:
                goto L5e
            Lbc:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt.C15953.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @FlowPreview
    @ExperimentalTime
    @NotNull
    /* renamed from: sample-HG0u8IE, reason: not valid java name */
    public static final <T> Flow<T> m4264sampleHG0u8IE(@NotNull Flow<? extends T> flow, long period) {
        return FlowKt.sample(flow, DelayKt.m4180toDelayMillisLRDsOJo(period));
    }
}
