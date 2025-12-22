package io.vertx.kotlin.core.net;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: NetClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a-\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"connectAwait", "Lio/vertx/core/net/NetSocket;", "Lio/vertx/core/net/NetClient;", "remoteAddress", "Lio/vertx/core/net/SocketAddress;", "(Lio/vertx/core/net/NetClient;Lio/vertx/core/net/SocketAddress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "serverName", "", "(Lio/vertx/core/net/NetClient;Lio/vertx/core/net/SocketAddress;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", RtspHeaders.Values.PORT, "", "host", "(Lio/vertx/core/net/NetClient;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/core/net/NetClient;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/net/NetClientKt.class */
public final class NetClientKt {
    @Nullable
    public static final Object connectAwait(@NotNull final NetClient $this$connectAwait, final int port, @NotNull final String host, @NotNull Continuation<? super NetSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<NetSocket>>, Unit>() { // from class: io.vertx.kotlin.core.net.NetClientKt.connectAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<NetSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<NetSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final NetClient $this$connectAwait, final int port, @NotNull final String host, @NotNull final String serverName, @NotNull Continuation<? super NetSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<NetSocket>>, Unit>() { // from class: io.vertx.kotlin.core.net.NetClientKt.connectAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<NetSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<NetSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(port, host, serverName, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final NetClient $this$connectAwait, @NotNull final SocketAddress remoteAddress, @NotNull Continuation<? super NetSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<NetSocket>>, Unit>() { // from class: io.vertx.kotlin.core.net.NetClientKt.connectAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<NetSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<NetSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(remoteAddress, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object connectAwait(@NotNull final NetClient $this$connectAwait, @NotNull final SocketAddress remoteAddress, @NotNull final String serverName, @NotNull Continuation<? super NetSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<NetSocket>>, Unit>() { // from class: io.vertx.kotlin.core.net.NetClientKt.connectAwait.8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<NetSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<NetSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$connectAwait.connect(remoteAddress, serverName, it);
            }
        }, continuation);
    }
}
