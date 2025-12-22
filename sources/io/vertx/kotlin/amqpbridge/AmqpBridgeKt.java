package io.vertx.kotlin.amqpbridge;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.amqpbridge.AmqpBridge;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.handler.FormLoginHandler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AmqpBridge.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001c\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\u0005\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a%\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a5\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"closeAwait", "", "Lio/vertx/amqpbridge/AmqpBridge;", "(Lio/vertx/amqpbridge/AmqpBridge;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startAwait", "hostname", "", RtspHeaders.Values.PORT, "", "(Lio/vertx/amqpbridge/AmqpBridge;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", FormLoginHandler.DEFAULT_USERNAME_PARAM, FormLoginHandler.DEFAULT_PASSWORD_PARAM, "(Lio/vertx/amqpbridge/AmqpBridge;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/amqpbridge/AmqpBridgeKt.class */
public final class AmqpBridgeKt {
    @Nullable
    public static final Object startAwait(@NotNull final AmqpBridge $this$startAwait, @NotNull final String hostname, final int port, @NotNull final String username, @NotNull final String password, @NotNull Continuation<? super AmqpBridge> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpBridge>>, Unit>() { // from class: io.vertx.kotlin.amqpbridge.AmqpBridgeKt.startAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpBridge>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpBridge>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$startAwait.start(hostname, port, username, password, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object startAwait(@NotNull final AmqpBridge $this$startAwait, @NotNull final String hostname, final int port, @NotNull Continuation<? super AmqpBridge> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AmqpBridge>>, Unit>() { // from class: io.vertx.kotlin.amqpbridge.AmqpBridgeKt.startAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AmqpBridge>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AmqpBridge>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$startAwait.start(hostname, port, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final AmqpBridge $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.amqpbridge.AmqpBridgeKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.amqpbridge.AmqpBridgeKt.closeAwait.2.1
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
