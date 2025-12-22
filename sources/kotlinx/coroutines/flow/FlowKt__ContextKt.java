package kotlinx.coroutines.flow;

import ch.qos.logback.core.CoreConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.FlowPreview;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Context.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 48, d1 = {"��0\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\u001a\u0015\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0002\b\u0004\u001a(\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a0\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006\u001a\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0006\u001a$\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b��\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u0002\u001a\u00020\u0003\u001a[\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0006\"\u0004\b��\u0010\u0007\"\u0004\b\u0001\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00070\u00062\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\t2#\u0010\u0013\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100\u00060\u0014¢\u0006\u0002\b\u0015H\u0007¨\u0006\u0016"}, d2 = {"checkFlowContext", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "checkFlowContext$FlowKt__ContextKt", "buffer", "Lkotlinx/coroutines/flow/Flow;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "cancellable", "conflate", "flowOn", "flowWith", "R", "flowContext", "bufferSize", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlinx-coroutines-core"}, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__ContextKt.class */
final /* synthetic */ class FlowKt__ContextKt {
    public static /* synthetic */ Flow buffer$default(Flow flow, int i, BufferOverflow bufferOverflow, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -2;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        return FlowKt.buffer(flow, i, bufferOverflow);
    }

    @NotNull
    public static final <T> Flow<T> buffer(@NotNull Flow<? extends T> flow, int capacity, @NotNull BufferOverflow onBufferOverflow) {
        if (!(capacity >= 0 || capacity == -2 || capacity == -1)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was ", Integer.valueOf(capacity)).toString());
        }
        if (!(capacity != -1 || onBufferOverflow == BufferOverflow.SUSPEND)) {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        }
        int capacity2 = capacity;
        BufferOverflow onBufferOverflow2 = onBufferOverflow;
        if (capacity2 == -1) {
            capacity2 = 0;
            onBufferOverflow2 = BufferOverflow.DROP_OLDEST;
        }
        return flow instanceof FusibleFlow ? FusibleFlow.DefaultImpls.fuse$default((FusibleFlow) flow, null, capacity2, onBufferOverflow2, 1, null) : new ChannelFlowOperatorImpl(flow, null, capacity2, onBufferOverflow2, 2, null);
    }

    @Deprecated(message = "Since 1.4.0, binary compatibility with earlier versions", level = DeprecationLevel.HIDDEN)
    public static final /* synthetic */ Flow buffer(Flow $this$buffer, int capacity) {
        return buffer$default($this$buffer, capacity, null, 2, null);
    }

    public static /* synthetic */ Flow buffer$default(Flow flow, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -2;
        }
        return buffer(flow, i);
    }

    @NotNull
    public static final <T> Flow<T> conflate(@NotNull Flow<? extends T> flow) {
        return buffer$default(flow, -1, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Flow<T> flowOn(@NotNull Flow<? extends T> flow, @NotNull CoroutineContext context) {
        checkFlowContext$FlowKt__ContextKt(context);
        return Intrinsics.areEqual(context, EmptyCoroutineContext.INSTANCE) ? flow : flow instanceof FusibleFlow ? FusibleFlow.DefaultImpls.fuse$default((FusibleFlow) flow, context, 0, null, 6, null) : new ChannelFlowOperatorImpl(flow, context, 0, null, 12, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> Flow<T> cancellable(@NotNull Flow<? extends T> flow) {
        return flow instanceof CancellableFlow ? flow : new CancellableFlowImpl(flow);
    }

    public static /* synthetic */ Flow flowWith$default(Flow flow, CoroutineContext coroutineContext, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -2;
        }
        return FlowKt.flowWith(flow, coroutineContext, i, function1);
    }

    @Deprecated(message = "flowWith is deprecated without replacement, please refer to its KDoc for an explanation", level = DeprecationLevel.ERROR)
    @FlowPreview
    @NotNull
    public static final <T, R> Flow<R> flowWith(@NotNull final Flow<? extends T> flow, @NotNull final CoroutineContext flowContext, final int bufferSize, @NotNull final Function1<? super Flow<? extends T>, ? extends Flow<? extends R>> function1) {
        checkFlowContext$FlowKt__ContextKt(flowContext);
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            @Nullable
            public Object collect(@NotNull final FlowCollector<? super R> flowCollector, @NotNull Continuation<? super Unit> continuation) {
                CoroutineContext originalContext = continuation.getContext().minusKey(Job.Key);
                Flow prepared = FlowKt__ContextKt.buffer$default(FlowKt.flowOn(flow, originalContext), bufferSize, null, 2, null);
                Flow $this$collect$iv = FlowKt__ContextKt.buffer$default(FlowKt.flowOn((Flow) function1.invoke(prepared), flowContext), bufferSize, null, 2, null);
                Object objCollect = $this$collect$iv.collect(new FlowCollector<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ContextKt$flowWith$lambda-3$$inlined$collect$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    @Nullable
                    public Object emit(R r, @NotNull Continuation<? super Unit> continuation2) {
                        Object objEmit = flowCollector.emit(r, continuation2);
                        return objEmit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEmit : Unit.INSTANCE;
                    }
                }, continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        };
    }

    private static final void checkFlowContext$FlowKt__ContextKt(CoroutineContext context) {
        if (!(context.get(Job.Key) == null)) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Flow context cannot contain job in it. Had ", context).toString());
        }
    }
}
