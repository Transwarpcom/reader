package kotlinx.coroutines.flow;

import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;

/* compiled from: Share.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��j\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001c\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001a+\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\b\f\u001aM\u0010\r\u001a\u00020\u000e\"\u0004\b��\u0010\u0002*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u0002H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001aA\u0010\u0019\u001a\u00020\u001a\"\u0004\b��\u0010\u0002*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\t2\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u001cH\u0002¢\u0006\u0002\b\u001d\u001aS\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012-\u0010\u001f\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\"\u0012\u0006\u0012\u0004\u0018\u00010#0 ¢\u0006\u0002\b$ø\u0001��¢\u0006\u0002\u0010%\u001a6\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010'\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a/\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010'\u001a\u00020\u000fH\u0086@ø\u0001��¢\u0006\u0002\u0010)\u001a9\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010'\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u0002H\u0002¢\u0006\u0002\u0010*\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"asSharedFlow", "Lkotlinx/coroutines/flow/SharedFlow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/flow/MutableSharedFlow;", "asStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "configureSharing", "Lkotlinx/coroutines/flow/SharingConfig;", "Lkotlinx/coroutines/flow/Flow;", "replay", "", "configureSharing$FlowKt__ShareKt", "launchSharing", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "upstream", "shared", "started", "Lkotlinx/coroutines/flow/SharingStarted;", "initialValue", "launchSharing$FlowKt__ShareKt", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)Lkotlinx/coroutines/Job;", "launchSharingDeferred", "", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "Lkotlinx/coroutines/CompletableDeferred;", "launchSharingDeferred$FlowKt__ShareKt", "onSubscription", "action", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/SharedFlow;", "shareIn", "scope", "stateIn", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)Lkotlinx/coroutines/flow/StateFlow;", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ShareKt.class */
final /* synthetic */ class FlowKt__ShareKt {
    public static /* synthetic */ SharedFlow shareIn$default(Flow flow, CoroutineScope coroutineScope, SharingStarted sharingStarted, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return FlowKt.shareIn(flow, coroutineScope, sharingStarted, i);
    }

    @NotNull
    public static final <T> SharedFlow<T> shareIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope scope, @NotNull SharingStarted started, int replay) {
        SharingConfig config = configureSharing$FlowKt__ShareKt(flow, replay);
        MutableSharedFlow shared = SharedFlowKt.MutableSharedFlow(replay, config.extraBufferCapacity, config.onBufferOverflow);
        Job job = launchSharing$FlowKt__ShareKt(scope, config.context, config.upstream, shared, started, SharedFlowKt.NO_VALUE);
        return new ReadonlySharedFlow(shared, job);
    }

    private static final <T> SharingConfig<T> configureSharing$FlowKt__ShareKt(Flow<? extends T> flow, int replay) {
        Flow upstream;
        int i;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(replay >= 0)) {
                throw new AssertionError();
            }
        }
        int defaultExtraCapacity = RangesKt.coerceAtLeast(replay, Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core()) - replay;
        if ((flow instanceof ChannelFlow) && (upstream = ((ChannelFlow) flow).dropChannelOperators()) != null) {
            switch (((ChannelFlow) flow).capacity) {
                case -3:
                case -2:
                case 0:
                    if (((ChannelFlow) flow).onBufferOverflow == BufferOverflow.SUSPEND) {
                        if (((ChannelFlow) flow).capacity != 0) {
                            i = defaultExtraCapacity;
                            break;
                        } else {
                            i = 0;
                            break;
                        }
                    } else if (replay != 0) {
                        i = 0;
                        break;
                    } else {
                        i = 1;
                        break;
                    }
                case -1:
                default:
                    i = ((ChannelFlow) flow).capacity;
                    break;
            }
            return new SharingConfig<>(upstream, i, ((ChannelFlow) flow).onBufferOverflow, ((ChannelFlow) flow).context);
        }
        return new SharingConfig<>(flow, defaultExtraCapacity, BufferOverflow.SUSPEND, EmptyCoroutineContext.INSTANCE);
    }

    private static final <T> Job launchSharing$FlowKt__ShareKt(CoroutineScope $this$launchSharing, CoroutineContext context, Flow<? extends T> flow, MutableSharedFlow<T> mutableSharedFlow, SharingStarted started, T t) {
        return BuildersKt__Builders_commonKt.launch$default($this$launchSharing, context, null, new FlowKt__ShareKt$launchSharing$1(started, flow, mutableSharedFlow, t, null), 2, null);
    }

    @NotNull
    public static final <T> StateFlow<T> stateIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope scope, @NotNull SharingStarted started, T t) {
        SharingConfig config = configureSharing$FlowKt__ShareKt(flow, 1);
        MutableStateFlow state = StateFlowKt.MutableStateFlow(t);
        Job job = launchSharing$FlowKt__ShareKt(scope, config.context, config.upstream, state, started, t);
        return new ReadonlyStateFlow(state, job);
    }

    @Nullable
    public static final <T> Object stateIn(@NotNull Flow<? extends T> flow, @NotNull CoroutineScope scope, @NotNull Continuation<? super StateFlow<? extends T>> continuation) {
        SharingConfig config = configureSharing$FlowKt__ShareKt(flow, 1);
        CompletableDeferred result = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        launchSharingDeferred$FlowKt__ShareKt(scope, config.context, config.upstream, result);
        return result.await(continuation);
    }

    private static final <T> void launchSharingDeferred$FlowKt__ShareKt(CoroutineScope $this$launchSharingDeferred, CoroutineContext context, Flow<? extends T> flow, CompletableDeferred<StateFlow<T>> completableDeferred) {
        BuildersKt__Builders_commonKt.launch$default($this$launchSharingDeferred, context, null, new FlowKt__ShareKt$launchSharingDeferred$1(flow, completableDeferred, null), 2, null);
    }

    @NotNull
    public static final <T> SharedFlow<T> asSharedFlow(@NotNull MutableSharedFlow<T> mutableSharedFlow) {
        return new ReadonlySharedFlow(mutableSharedFlow, null);
    }

    @NotNull
    public static final <T> StateFlow<T> asStateFlow(@NotNull MutableStateFlow<T> mutableStateFlow) {
        return new ReadonlyStateFlow(mutableStateFlow, null);
    }

    @NotNull
    public static final <T> SharedFlow<T> onSubscription(@NotNull SharedFlow<? extends T> sharedFlow, @NotNull Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return new SubscribedSharedFlow(sharedFlow, function2);
    }
}
