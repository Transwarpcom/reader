package io.vertx.kotlin.ext.stomp;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.net.NetClient;
import io.vertx.ext.stomp.StompClient;
import io.vertx.ext.stomp.StompClientConnection;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: StompClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a-\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"connectAwait", "Lio/vertx/ext/stomp/StompClientConnection;", "Lio/vertx/ext/stomp/StompClient;", "(Lio/vertx/ext/stomp/StompClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "net", "Lio/vertx/core/net/NetClient;", "(Lio/vertx/ext/stomp/StompClient;Lio/vertx/core/net/NetClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", RtspHeaders.Values.PORT, "", "host", "", "(Lio/vertx/ext/stomp/StompClient;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/ext/stomp/StompClient;ILjava/lang/String;Lio/vertx/core/net/NetClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/stomp/StompClientKt.class */
public final class StompClientKt {
    @Nullable
    public static final Object connectAwait(@NotNull final StompClient $this$connectAwait, final int port, @NotNull final String host, @NotNull Continuation<? super StompClientConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompClientConnection>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompClientKt.connectAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompClientConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompClientConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final StompClient $this$connectAwait, @NotNull final NetClient net2, @NotNull Continuation<? super StompClientConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompClientConnection>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompClientKt.connectAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompClientConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompClientConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(net2, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final StompClient $this$connectAwait, final int port, @NotNull final String host, @NotNull final NetClient net2, @NotNull Continuation<? super StompClientConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompClientConnection>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompClientKt.connectAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompClientConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompClientConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(port, host, net2, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final StompClient $this$connectAwait, @NotNull Continuation<? super StompClientConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompClientConnection>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompClientKt.connectAwait.8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompClientConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompClientConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(it);
            }
        }, continuation);
    }
}
