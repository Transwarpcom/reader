package io.vertx.kotlin.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.streams.WriteStream;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
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
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��n\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010#\u001a\u00020$H\u0097\u0001J\u0013\u0010#\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010&H\u0097\u0001J\u0019\u0010#\u001a\u00020$2\u000e\u0010%\u001a\n\u0018\u00010'j\u0004\u0018\u0001`(H\u0096\u0001J\u0012\u0010)\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0015\u0010*\u001a\u00020\u00112\b\u0010+\u001a\u0004\u0018\u00018��¢\u0006\u0002\u0010,J.\u0010-\u001a\u00020$2#\u0010.\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010&¢\u0006\f\b0\u0012\b\b1\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020$0/H\u0097\u0001J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00028��03H\u0096\u0003J\u0016\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00028��H\u0096\u0001¢\u0006\u0002\u0010,J\u0010\u00106\u001a\u0004\u0018\u00018��H\u0096\u0001¢\u0006\u0002\u00107J\u0011\u00108\u001a\u00028��H\u0096Aø\u0001��¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u0004\u0018\u00018��H\u0097Aø\u0001��¢\u0006\u0002\u00109J\u0019\u0010;\u001a\u00020$2\u0006\u00105\u001a\u00028��H\u0096Aø\u0001��¢\u0006\u0002\u0010<J\b\u0010=\u001a\u00020$H\u0007R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0002¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00118\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028��0\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u00178\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R$\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u001e0\u001dX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005¢\u0006\b\n��\u001a\u0004\b!\u0010\"\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006>"}, d2 = {"Lio/vertx/kotlin/coroutines/ChannelWriteStream;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/channels/Channel;", "Lkotlinx/coroutines/CoroutineScope;", "stream", "Lio/vertx/core/streams/WriteStream;", "channel", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/core/Context;", "(Lio/vertx/core/streams/WriteStream;Lkotlinx/coroutines/channels/Channel;Lio/vertx/core/Context;)V", "getChannel", "()Lkotlinx/coroutines/channels/Channel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "isClosedForReceive", "", "()Z", "isClosedForSend", "isEmpty", "isFull", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "getStream", "()Lio/vertx/core/streams/WriteStream;", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "close", "dispatch", "elt", "(Ljava/lang/Object;)Z", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "element", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "subscribe", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ChannelWriteStream.class */
final class ChannelWriteStream<T> implements Channel<T>, CoroutineScope {

    @NotNull
    private final CoroutineContext coroutineContext;

    @NotNull
    private final WriteStream<T> stream;

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
    public final WriteStream<T> getStream() {
        return this.stream;
    }

    public ChannelWriteStream(@NotNull WriteStream<T> stream, @NotNull Channel<T> channel, @NotNull Context context) {
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

    @ExperimentalCoroutinesApi
    public final void subscribe() {
        this.stream.exceptionHandler(new Handler<Throwable>() { // from class: io.vertx.kotlin.coroutines.ChannelWriteStream.subscribe.1
            @Override // io.vertx.core.Handler
            public final void handle(Throwable it) {
                ChannelWriteStream.this.getChannel().cancel(it);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new AnonymousClass2(null), 3, null);
    }

    /* compiled from: ReceiveChannelHandler.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0010\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u0003H\u008a@ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    @DebugMetadata(f = "ReceiveChannelHandler.kt", l = {209}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.vertx.kotlin.coroutines.ChannelWriteStream$subscribe$2")
    /* renamed from: io.vertx.kotlin.coroutines.ChannelWriteStream$subscribe$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ChannelWriteStream$subscribe$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private CoroutineScope p$;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(completion);
            anonymousClass2.p$ = (CoroutineScope) value;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x007d, code lost:
        
            if (r6.this$0.dispatch(r9) == false) goto L17;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0059  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x0075  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x007d -> B:5:0x0029). Please report as a decompilation issue!!! */
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
                r10 = r0
                r0 = r6
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L20;
                    case 1: goto L44;
                    default: goto L8a;
                }
            L20:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r6
                kotlinx.coroutines.CoroutineScope r0 = r0.p$
                r8 = r0
            L29:
                r0 = r6
                io.vertx.kotlin.coroutines.ChannelWriteStream r0 = io.vertx.kotlin.coroutines.ChannelWriteStream.this
                kotlinx.coroutines.channels.Channel r0 = r0.getChannel()
                r1 = r6
                r2 = r6
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = r0.receiveOrNull(r1)
                r1 = r0
                r2 = r10
                if (r1 != r2) goto L49
                r1 = r10
                return r1
            L44:
                r0 = r7
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r7
            L49:
                r9 = r0
                r0 = r6
                io.vertx.kotlin.coroutines.ChannelWriteStream r0 = io.vertx.kotlin.coroutines.ChannelWriteStream.this
                io.vertx.core.streams.WriteStream r0 = r0.getStream()
                boolean r0 = r0.writeQueueFull()
                if (r0 == 0) goto L75
                r0 = r6
                io.vertx.kotlin.coroutines.ChannelWriteStream r0 = io.vertx.kotlin.coroutines.ChannelWriteStream.this
                io.vertx.core.streams.WriteStream r0 = r0.getStream()
                io.vertx.kotlin.coroutines.ChannelWriteStream$subscribe$2$1 r1 = new io.vertx.kotlin.coroutines.ChannelWriteStream$subscribe$2$1
                r2 = r1
                r3 = r6
                r4 = r9
                r2.<init>()
                io.vertx.core.Handler r1 = (io.vertx.core.Handler) r1
                io.vertx.core.streams.WriteStream r0 = r0.drainHandler(r1)
                goto L86
            L75:
                r0 = r6
                io.vertx.kotlin.coroutines.ChannelWriteStream r0 = io.vertx.kotlin.coroutines.ChannelWriteStream.this
                r1 = r9
                boolean r0 = r0.dispatch(r1)
                if (r0 != 0) goto L83
                goto L86
            L83:
                goto L29
            L86:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L8a:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.vertx.kotlin.coroutines.ChannelWriteStream.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final boolean dispatch(@Nullable T t) {
        if (t != null) {
            this.stream.write(t);
            return true;
        }
        SendChannel.DefaultImpls.close$default(this, null, 1, null);
        return false;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: close */
    public boolean cancel(@Nullable Throwable cause) {
        boolean ret = this.channel.cancel(cause);
        if (ret) {
            this.stream.end();
        }
        return ret;
    }
}
