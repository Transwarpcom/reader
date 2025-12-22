package io.vertx.kotlin.amqp;

import io.vertx.amqp.AmqpConnection;
import io.vertx.amqp.AmqpReceiver;
import io.vertx.amqp.AmqpReceiverOptions;
import io.vertx.amqp.AmqpSender;
import io.vertx.amqp.AmqpSenderOptions;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AmqpConnection.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��4\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\b\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a%\u0010\b\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010\u000f\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a%\u0010\u000f\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"closeAwait", "", "Lio/vertx/amqp/AmqpConnection;", "(Lio/vertx/amqp/AmqpConnection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createAnonymousSenderAwait", "Lio/vertx/amqp/AmqpSender;", "createDynamicReceiverAwait", "Lio/vertx/amqp/AmqpReceiver;", "createReceiverAwait", "address", "", "(Lio/vertx/amqp/AmqpConnection;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiverOptions", "Lio/vertx/amqp/AmqpReceiverOptions;", "(Lio/vertx/amqp/AmqpConnection;Ljava/lang/String;Lio/vertx/amqp/AmqpReceiverOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSenderAwait", "options", "Lio/vertx/amqp/AmqpSenderOptions;", "(Lio/vertx/amqp/AmqpConnection;Ljava/lang/String;Lio/vertx/amqp/AmqpSenderOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/amqp/AmqpConnectionKt.class */
public final class AmqpConnectionKt {
    @Nullable
    public static final Object closeAwait(@NotNull final AmqpConnection $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.closeAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.closeAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object createReceiverAwait(@NotNull final AmqpConnection $this$createReceiverAwait, @NotNull final String address, @NotNull Continuation<? super AmqpReceiver> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpReceiver>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.createReceiverAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpReceiver>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpReceiver>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createReceiverAwait.createReceiver(address, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createReceiverAwait(@NotNull final AmqpConnection $this$createReceiverAwait, @NotNull final String address, @NotNull final AmqpReceiverOptions receiverOptions, @NotNull Continuation<? super AmqpReceiver> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpReceiver>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.createReceiverAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpReceiver>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpReceiver>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createReceiverAwait.createReceiver(address, receiverOptions, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createDynamicReceiverAwait(@NotNull final AmqpConnection $this$createDynamicReceiverAwait, @NotNull Continuation<? super AmqpReceiver> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpReceiver>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.createDynamicReceiverAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpReceiver>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpReceiver>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createDynamicReceiverAwait.createDynamicReceiver(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createSenderAwait(@NotNull final AmqpConnection $this$createSenderAwait, @NotNull final String address, @NotNull Continuation<? super AmqpSender> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpSender>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.createSenderAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpSender>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpSender>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createSenderAwait.createSender(address, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createSenderAwait(@NotNull final AmqpConnection $this$createSenderAwait, @NotNull final String address, @NotNull final AmqpSenderOptions options, @NotNull Continuation<? super AmqpSender> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpSender>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.createSenderAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpSender>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpSender>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createSenderAwait.createSender(address, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createAnonymousSenderAwait(@NotNull final AmqpConnection $this$createAnonymousSenderAwait, @NotNull Continuation<? super AmqpSender> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpSender>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpConnectionKt.createAnonymousSenderAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpSender>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpSender>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createAnonymousSenderAwait.createAnonymousSender(it);
            }
        }, continuation);
    }
}
