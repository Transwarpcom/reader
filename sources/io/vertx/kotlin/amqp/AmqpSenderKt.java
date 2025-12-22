package io.vertx.kotlin.amqp;

import io.vertx.amqp.AmqpMessage;
import io.vertx.amqp.AmqpSender;
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

/* compiled from: AmqpSender.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"closeAwait", "", "Lio/vertx/amqp/AmqpSender;", "(Lio/vertx/amqp/AmqpSender;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "endAwait", "data", "Lio/vertx/amqp/AmqpMessage;", "(Lio/vertx/amqp/AmqpSender;Lio/vertx/amqp/AmqpMessage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendWithAckAwait", "message", "writeAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/amqp/AmqpSenderKt.class */
public final class AmqpSenderKt {
    @Nullable
    public static final Object endAwait(@NotNull final AmqpSender $this$endAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.endAwait.2
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
                $this$endAwait.end(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.endAwait.2.1
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
    public static final Object endAwait(@NotNull final AmqpSender $this$endAwait, @NotNull final AmqpMessage data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.endAwait.4
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
                $this$endAwait.end(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.endAwait.4.1
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
    public static final Object writeAwait(@NotNull final AmqpSender $this$writeAwait, @NotNull final AmqpMessage data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.writeAwait.2
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
                $this$writeAwait.write(data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.writeAwait.2.1
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
    public static final Object sendWithAckAwait(@NotNull final AmqpSender $this$sendWithAckAwait, @NotNull final AmqpMessage message, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.sendWithAckAwait.2
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
                $this$sendWithAckAwait.sendWithAck(message, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.sendWithAckAwait.2.1
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
    public static final Object closeAwait(@NotNull final AmqpSender $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqp.AmqpSenderKt.closeAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }
}
