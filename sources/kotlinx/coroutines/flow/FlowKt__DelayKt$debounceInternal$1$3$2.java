package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Delay.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "value", "Lkotlinx/coroutines/channels/ChannelResult;", ""})
@DebugMetadata(f = "Delay.kt", l = {245}, i = {0}, s = {"L$0"}, n = {"$this$onFailure$iv"}, m = "invokeSuspend", c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounceInternal$1$3$2")
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/flow/FlowKt__DelayKt$debounceInternal$1$3$2.class */
final class FlowKt__DelayKt$debounceInternal$1$3$2 extends SuspendLambda implements Function2<ChannelResult<? extends Object>, Continuation<? super Unit>, Object> {
    Object L$1;
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ Ref.ObjectRef<Object> $lastValue;
    final /* synthetic */ FlowCollector<T> $downstream;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__DelayKt$debounceInternal$1$3$2(Ref.ObjectRef<Object> objectRef, FlowCollector<? super T> flowCollector, Continuation<? super FlowKt__DelayKt$debounceInternal$1$3$2> continuation) {
        super(2, continuation);
        this.$lastValue = objectRef;
        this.$downstream = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
        FlowKt__DelayKt$debounceInternal$1$3$2 flowKt__DelayKt$debounceInternal$1$3$2 = new FlowKt__DelayKt$debounceInternal$1$3$2(this.$lastValue, this.$downstream, continuation);
        flowKt__DelayKt$debounceInternal$1$3$2.L$0 = value;
        return flowKt__DelayKt$debounceInternal$1$3$2;
    }

    @Nullable
    /* renamed from: invoke-WpGqRn0, reason: not valid java name */
    public final Object m4265invokeWpGqRn0(@NotNull Object p1, @Nullable Continuation<? super Unit> continuation) {
        return ((FlowKt__DelayKt$debounceInternal$1$3$2) create(ChannelResult.m4233boximpl(p1), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(ChannelResult<? extends Object> channelResult, Continuation<? super Unit> continuation) {
        return m4265invokeWpGqRn0(channelResult.m4234unboximpl(), continuation);
    }

    /* JADX WARN: Type inference failed for: r0v19, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, kotlinx.coroutines.internal.Symbol] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
        Ref.ObjectRef<Object> objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ?? M4234unboximpl = ((ChannelResult) this.L$0).m4234unboximpl();
                Ref.ObjectRef<Object> objectRef2 = this.$lastValue;
                if (!(M4234unboximpl instanceof ChannelResult.Failed)) {
                    objectRef2.element = M4234unboximpl;
                }
                objectRef = this.$lastValue;
                FlowCollector<T> flowCollector = this.$downstream;
                if (M4234unboximpl instanceof ChannelResult.Failed) {
                    Throwable it = ChannelResult.m4228exceptionOrNullimpl(M4234unboximpl);
                    if (it != null) {
                        throw it;
                    }
                    if (objectRef.element != null) {
                        Symbol this_$iv = NullSurrogateKt.NULL;
                        Object value$iv = objectRef.element;
                        Object obj = value$iv == this_$iv ? null : value$iv;
                        this.L$0 = M4234unboximpl;
                        this.L$1 = objectRef;
                        this.label = 1;
                        if (flowCollector.emit(obj, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    objectRef.element = NullSurrogateKt.DONE;
                }
                return Unit.INSTANCE;
            case 1:
                objectRef = (Ref.ObjectRef) this.L$1;
                Object obj2 = this.L$0;
                ResultKt.throwOnFailure($result);
                objectRef.element = NullSurrogateKt.DONE;
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
