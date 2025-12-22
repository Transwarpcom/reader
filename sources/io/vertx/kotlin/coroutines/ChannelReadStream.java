package io.vertx.kotlin.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.streams.ReadStream;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReceiveChannelHandler.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��n\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0002\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010#\u001a\u00020$H\u0097\u0001J\u0013\u0010#\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010&H\u0097\u0001J\u0019\u0010#\u001a\u00020$2\u000e\u0010%\u001a\n\u0018\u00010'j\u0004\u0018\u0001`(H\u0096\u0001J\u0013\u0010)\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010&H\u0096\u0001J.\u0010*\u001a\u00020$2#\u0010+\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010&¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020$0,H\u0097\u0001J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00028��00H\u0096\u0003J\u0016\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u00028��H\u0096\u0001¢\u0006\u0002\u00103J\u0010\u00104\u001a\u0004\u0018\u00018��H\u0096\u0001¢\u0006\u0002\u00105J\u0011\u00106\u001a\u00028��H\u0096Aø\u0001��¢\u0006\u0002\u00107J\u0013\u00108\u001a\u0004\u0018\u00018��H\u0097Aø\u0001��¢\u0006\u0002\u00107J\u0019\u00109\u001a\u00020$2\u0006\u00102\u001a\u00028��H\u0096Aø\u0001��¢\u0006\u0002\u0010:J\u0006\u0010;\u001a\u00020$R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0002¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028��0\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u00178\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R$\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u001e0\u001dX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005¢\u0006\b\n��\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, d2 = {"Lio/vertx/kotlin/coroutines/ChannelReadStream;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/CoroutineScope;", "stream", "Lio/vertx/core/streams/ReadStream;", "channel", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/core/Context;", "(Lio/vertx/core/streams/ReadStream;Lkotlinx/coroutines/channels/Channel;Lio/vertx/core/Context;)V", "getChannel", "()Lkotlinx/coroutines/channels/Channel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "isClosedForReceive", "", "()Z", "isClosedForSend", "isEmpty", "isFull", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "getStream", "()Lio/vertx/core/streams/ReadStream;", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "element", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ChannelReadStream.class */
final class ChannelReadStream<T> implements Channel<T>, CoroutineScope {

    @NotNull
    private final CoroutineContext coroutineContext;

    @NotNull
    private final ReadStream<T> stream;

    @NotNull
    private final Channel<T> channel;

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this.channel.isClosedForReceive();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this.channel.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return this.channel.isEmpty();
    }

    public boolean isFull() {
        return this.channel.isFull();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<T> getOnReceive() {
        return this.channel.getOnReceive();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public SelectClause1<T> getOnReceiveOrNull() {
        return this.channel.getOnReceiveOrNull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @NotNull
    public SelectClause2<T, SendChannel<T>> getOnSend() {
        return this.channel.getOnSend();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ void cancel() {
        this.channel.cancel();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ boolean cancel(@Nullable Throwable cause) {
        return this.channel.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public void cancel(@Nullable CancellationException cause) {
        this.channel.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(@Nullable Throwable cause) {
        return this.channel.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @ExperimentalCoroutinesApi
    public void invokeOnClose(@NotNull Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        this.channel.invokeOnClose(handler);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public ChannelIterator<T> iterator() {
        return this.channel.iterator();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(T t) {
        return this.channel.offer(t);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public T poll() {
        return this.channel.poll();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Nullable
    public Object receive(@NotNull Continuation<? super T> continuation) {
        return this.channel.receive(continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @ObsoleteCoroutinesApi
    @Nullable
    public Object receiveOrNull(@NotNull Continuation<? super T> continuation) {
        return this.channel.receiveOrNull(continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Nullable
    public Object send(T t, @NotNull Continuation<? super Unit> continuation) {
        return this.channel.send(t, continuation);
    }

    @NotNull
    public final ReadStream<T> getStream() {
        return this.stream;
    }

    public ChannelReadStream(@NotNull ReadStream<T> stream, @NotNull Channel<T> channel, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(stream, "stream");
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.stream = stream;
        this.channel = channel;
        this.coroutineContext = VertxCoroutineKt.dispatcher(context);
    }

    @NotNull
    public final Channel<T> getChannel() {
        return this.channel;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final void subscribe() {
        this.stream.endHandler(new Handler<Void>() { // from class: io.vertx.kotlin.coroutines.ChannelReadStream.subscribe.1
            @Override // io.vertx.core.Handler
            public final void handle(Void it) {
                SendChannel.DefaultImpls.close$default(ChannelReadStream.this, null, 1, null);
            }
        });
        this.stream.exceptionHandler(new Handler<Throwable>() { // from class: io.vertx.kotlin.coroutines.ChannelReadStream.subscribe.2
            @Override // io.vertx.core.Handler
            public final void handle(Throwable err) {
                ChannelReadStream.this.cancel(err);
            }
        });
        this.stream.handler2(new Handler<T>() { // from class: io.vertx.kotlin.coroutines.ChannelReadStream.subscribe.3

            /* compiled from: ReceiveChannelHandler.kt */
            @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0010\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u0003H\u008a@ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
            @DebugMetadata(f = "ReceiveChannelHandler.kt", l = {154}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.vertx.kotlin.coroutines.ChannelReadStream$subscribe$3$1")
            /* renamed from: io.vertx.kotlin.coroutines.ChannelReadStream$subscribe$3$1, reason: invalid class name */
            /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ChannelReadStream$subscribe$3$1.class */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                private CoroutineScope p$;
                int label;
                final /* synthetic */ Object $event;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Object obj, Continuation continuation) {
                    super(2, continuation);
                    this.$event = obj;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$event, completion);
                    anonymousClass1.p$ = (CoroutineScope) value;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object result) throws Throwable {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(result);
                            CoroutineScope coroutineScope = this.p$;
                            ChannelReadStream channelReadStream = ChannelReadStream.this;
                            Object obj = this.$event;
                            this.label = 1;
                            if (channelReadStream.send(obj, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            break;
                        case 1:
                            ResultKt.throwOnFailure(result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ChannelReadStream.this.getStream().fetch2(1L);
                    return Unit.INSTANCE;
                }
            }

            @Override // io.vertx.core.Handler
            public final void handle(T t) {
                BuildersKt__Builders_commonKt.launch$default(ChannelReadStream.this, null, null, new AnonymousClass1(t, null), 3, null);
            }
        });
    }
}
