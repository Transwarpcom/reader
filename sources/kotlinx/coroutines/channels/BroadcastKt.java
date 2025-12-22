package kotlinx.coroutines.channels;

import ch.qos.logback.core.CoreConstants;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Broadcast.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��V\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u009e\u0001\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2-\b\u0002\u0010\n\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000bj\u0004\u0018\u0001`\u00112/\b\u0001\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0013¢\u0006\u0002\b\u0017H\u0007ø\u0001��¢\u0006\u0002\u0010\u0018\u001a2\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00192\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"broadcast", "Lkotlinx/coroutines/channels/BroadcastChannel;", "E", "Lkotlinx/coroutines/CoroutineScope;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/CoroutineStart;", "onCompletion", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/BroadcastChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/BroadcastKt.class */
public final class BroadcastKt {
    public static /* synthetic */ BroadcastChannel broadcast$default(ReceiveChannel receiveChannel, int i, CoroutineStart coroutineStart, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        if ((i2 & 2) != 0) {
            coroutineStart = CoroutineStart.LAZY;
        }
        return broadcast(receiveChannel, i, coroutineStart);
    }

    @ObsoleteCoroutinesApi
    @NotNull
    public static final <E> BroadcastChannel<E> broadcast(@NotNull final ReceiveChannel<? extends E> receiveChannel, int capacity, @NotNull CoroutineStart start) {
        CoroutineScope scope = CoroutineScopeKt.plus(CoroutineScopeKt.plus(GlobalScope.INSTANCE, Dispatchers.getUnconfined()), new BroadcastKt$broadcast$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key));
        return broadcast$default(scope, null, capacity, start, new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.channels.BroadcastKt.broadcast.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable it) {
                ChannelsKt.cancelConsumed(receiveChannel, it);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }
        }, new AnonymousClass2(receiveChannel, null), 1, null);
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* compiled from: Broadcast.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"})
    @DebugMetadata(f = "Broadcast.kt", l = {53, 54}, i = {0, 1}, s = {"L$0", "L$0"}, n = {"$this$broadcast", "$this$broadcast"}, m = "invokeSuspend", c = "kotlinx.coroutines.channels.BroadcastKt$broadcast$2")
    /* renamed from: kotlinx.coroutines.channels.BroadcastKt$broadcast$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/channels/BroadcastKt$broadcast$2.class */
    static final class AnonymousClass2<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ ReceiveChannel<E> $this_broadcast;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(ReceiveChannel<? extends E> receiveChannel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$this_broadcast = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$this_broadcast, continuation);
            anonymousClass2.L$0 = value;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull ProducerScope<? super E> producerScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0059  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x00a0 -> B:5:0x003a). Please report as a decompilation issue!!! */
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
                    case 0: goto L24;
                    case 1: goto L5c;
                    case 2: goto La6;
                    default: goto Lc3;
                }
            L24:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r6
                kotlinx.coroutines.channels.ReceiveChannel<E> r0 = r0.$this_broadcast
                kotlinx.coroutines.channels.ChannelIterator r0 = r0.iterator()
                r9 = r0
            L3a:
                r0 = r9
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = r6
                r3 = r8
                r2.L$0 = r3
                r2 = r6
                r3 = r9
                r2.L$1 = r3
                r2 = r6
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = r0.hasNext(r1)
                r1 = r0
                r2 = r11
                if (r1 != r2) goto L71
                r1 = r11
                return r1
            L5c:
                r0 = r6
                java.lang.Object r0 = r0.L$1
                kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
                r9 = r0
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            L71:
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto Lbf
                r0 = r9
                java.lang.Object r0 = r0.next()
                r10 = r0
                r0 = r8
                r1 = r10
                r2 = r6
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = r6
                r4 = r8
                r3.L$0 = r4
                r3 = r6
                r4 = r9
                r3.L$1 = r4
                r3 = r6
                r4 = 2
                r3.label = r4
                java.lang.Object r0 = r0.send(r1, r2)
                r1 = r0
                r2 = r11
                if (r1 != r2) goto Lbb
                r1 = r11
                return r1
            La6:
                r0 = r6
                java.lang.Object r0 = r0.L$1
                kotlinx.coroutines.channels.ChannelIterator r0 = (kotlinx.coroutines.channels.ChannelIterator) r0
                r9 = r0
                r0 = r6
                java.lang.Object r0 = r0.L$0
                kotlinx.coroutines.channels.ProducerScope r0 = (kotlinx.coroutines.channels.ProducerScope) r0
                r8 = r0
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            Lbb:
                goto L3a
            Lbf:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Lc3:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.BroadcastKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static /* synthetic */ BroadcastChannel broadcast$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, int i, CoroutineStart coroutineStart, Function1 function1, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            coroutineStart = CoroutineStart.LAZY;
        }
        if ((i2 & 8) != 0) {
            function1 = null;
        }
        return broadcast(coroutineScope, coroutineContext, i, coroutineStart, function1, function2);
    }

    @ObsoleteCoroutinesApi
    @NotNull
    public static final <E> BroadcastChannel<E> broadcast(@NotNull CoroutineScope $this$broadcast, @NotNull CoroutineContext context, int capacity, @NotNull CoroutineStart start, @Nullable Function1<? super Throwable, Unit> function1, @BuilderInference @NotNull Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        LazyBroadcastCoroutine broadcastCoroutine;
        CoroutineContext newContext = CoroutineContextKt.newCoroutineContext($this$broadcast, context);
        BroadcastChannel channel = BroadcastChannelKt.BroadcastChannel(capacity);
        if (start.isLazy()) {
            broadcastCoroutine = new LazyBroadcastCoroutine(newContext, channel, function2);
        } else {
            broadcastCoroutine = new BroadcastCoroutine(newContext, channel, true);
        }
        BroadcastCoroutine coroutine = broadcastCoroutine;
        if (function1 != null) {
            coroutine.invokeOnCompletion(function1);
        }
        coroutine.start(start, coroutine, function2);
        return coroutine;
    }
}
