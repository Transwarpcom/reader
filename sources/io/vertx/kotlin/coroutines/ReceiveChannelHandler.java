package io.vertx.kotlin.coroutines;

import ch.qos.logback.core.CoreConstants;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.ObsoleteCoroutinesApi;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.selects.SelectClause1;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReceiveChannelHandler.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��`\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001f\u001a\u00020 H\u0017J\u0012\u0010\u001f\u001a\u00020\u00122\b\u0010!\u001a\u0004\u0018\u00010\"H\u0017J\u0018\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n\u0018\u00010#j\u0004\u0018\u0001`$H\u0016J\u0015\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00028��H\u0016¢\u0006\u0002\u0010'J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00028��0)H\u0096\u0002J\u000f\u0010*\u001a\u0004\u0018\u00018��H\u0016¢\u0006\u0002\u0010+J\u0011\u0010,\u001a\u00028��H\u0096@ø\u0001��¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u0004\u0018\u00018��H\u0097@ø\u0001��¢\u0006\u0002\u0010-R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0\fX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u00128VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028��0\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\u00198VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lio/vertx/kotlin/coroutines/ReceiveChannelHandler;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/vertx/core/Handler;", "Lkotlinx/coroutines/CoroutineScope;", "vertx", "Lio/vertx/core/Vertx;", "(Lio/vertx/core/Vertx;)V", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/core/Context;", "(Lio/vertx/core/Context;)V", "channel", "Lkotlinx/coroutines/channels/Channel;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "isClosedForReceive", "", "isClosedForReceive$annotations", "()V", "()Z", "isEmpty", "isEmpty$annotations", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveOrNull", "onReceiveOrNull$annotations", "getOnReceiveOrNull", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "handle", PackageDocumentBase.OPFAttributes.event, "(Ljava/lang/Object;)V", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveOrNull", "vertx-lang-kotlin-coroutines"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ReceiveChannelHandler.class */
public final class ReceiveChannelHandler<T> implements ReceiveChannel<T>, Handler<T>, CoroutineScope {

    @NotNull
    private final CoroutineContext coroutineContext;
    private final Channel<T> channel;

    @ExperimentalCoroutinesApi
    public static /* synthetic */ void isClosedForReceive$annotations() {
    }

    @ExperimentalCoroutinesApi
    public static /* synthetic */ void isEmpty$annotations() {
    }

    @ExperimentalCoroutinesApi
    public static /* synthetic */ void onReceiveOrNull$annotations() {
    }

    public ReceiveChannelHandler(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.coroutineContext = VertxCoroutineKt.dispatcher(context);
        this.channel = ChannelKt.Channel(16);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ReceiveChannelHandler(@NotNull Vertx vertx) {
        Intrinsics.checkParameterIsNotNull(vertx, "vertx");
        Context orCreateContext = vertx.getOrCreateContext();
        Intrinsics.checkExpressionValueIsNotNull(orCreateContext, "vertx.getOrCreateContext()");
        this(orCreateContext);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    @NotNull
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this.channel.isClosedForReceive();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return this.channel.isEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @NotNull
    public ChannelIterator<T> iterator() {
        return this.channel.iterator();
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

    /* compiled from: ReceiveChannelHandler.kt */
    @Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3, d1 = {"��\u0010\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u0003H\u008a@ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"})
    @DebugMetadata(f = "ReceiveChannelHandler.kt", l = {86}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.vertx.kotlin.coroutines.ReceiveChannelHandler$handle$1")
    /* renamed from: io.vertx.kotlin.coroutines.ReceiveChannelHandler$handle$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-coroutines-3.8.5.jar:io/vertx/kotlin/coroutines/ReceiveChannelHandler$handle$1.class */
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(result);
                    CoroutineScope coroutineScope = this.p$;
                    Channel channel = ReceiveChannelHandler.this.channel;
                    Object obj = this.$event;
                    this.label = 1;
                    if (channel.send(obj, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure(result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    @Override // io.vertx.core.Handler
    public void handle(T t) {
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new AnonymousClass1(t, null), 3, null);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Since 3.7.1, binary compatibility with versions <= 3.7.0")
    @ObsoleteCoroutinesApi
    public boolean cancel(@Nullable Throwable cause) {
        this.channel.cancel(ExceptionsKt.CancellationException(null, cause));
        return true;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public void cancel(@Nullable CancellationException cause) {
        this.channel.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Since 3.7.1, binary compatibility with versions <= 3.7.0")
    public void cancel() {
        ReceiveChannel.DefaultImpls.cancel$default((ReceiveChannel) this.channel, (CancellationException) null, 1, (Object) null);
    }
}
