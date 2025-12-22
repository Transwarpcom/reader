package io.vertx.kotlin.core.http;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.WebSocket;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.http.WebsocketVersion;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HttpClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��6\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u001a;\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a-\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0014\u001a\u001d\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0015\u001a%\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"webSocketAbsAwait", "Lio/vertx/core/http/WebSocket;", "Lio/vertx/core/http/HttpClient;", "url", "", "headers", "Lio/vertx/core/MultiMap;", "version", "Lio/vertx/core/http/WebsocketVersion;", "subProtocols", "", "(Lio/vertx/core/http/HttpClient;Ljava/lang/String;Lio/vertx/core/MultiMap;Lio/vertx/core/http/WebsocketVersion;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "webSocketAwait", "options", "Lio/vertx/core/http/WebSocketConnectOptions;", "(Lio/vertx/core/http/HttpClient;Lio/vertx/core/http/WebSocketConnectOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", RtspHeaders.Values.PORT, "", "host", "requestURI", "(Lio/vertx/core/http/HttpClient;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/core/http/HttpClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/vertx/core/http/HttpClient;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/http/HttpClientKt.class */
public final class HttpClientKt {
    @Nullable
    public static final Object webSocketAwait(@NotNull final HttpClient $this$webSocketAwait, final int port, @NotNull final String host, @NotNull final String requestURI, @NotNull Continuation<? super WebSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebSocket>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpClientKt.webSocketAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$webSocketAwait.webSocket(port, host, requestURI, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object webSocketAwait(@NotNull final HttpClient $this$webSocketAwait, @NotNull final String host, @NotNull final String requestURI, @NotNull Continuation<? super WebSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebSocket>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpClientKt.webSocketAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$webSocketAwait.webSocket(host, requestURI, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object webSocketAwait(@NotNull final HttpClient $this$webSocketAwait, @NotNull final String requestURI, @NotNull Continuation<? super WebSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebSocket>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpClientKt.webSocketAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$webSocketAwait.webSocket(requestURI, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object webSocketAwait(@NotNull final HttpClient $this$webSocketAwait, @NotNull final WebSocketConnectOptions options, @NotNull Continuation<? super WebSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebSocket>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpClientKt.webSocketAwait.8
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$webSocketAwait.webSocket(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object webSocketAbsAwait(@NotNull final HttpClient $this$webSocketAbsAwait, @NotNull final String url, @NotNull final MultiMap headers, @NotNull final WebsocketVersion version, @NotNull final List<String> list, @NotNull Continuation<? super WebSocket> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<WebSocket>>, Unit>() { // from class: io.vertx.kotlin.core.http.HttpClientKt.webSocketAbsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<WebSocket>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<WebSocket>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$webSocketAbsAwait.webSocketAbs(url, headers, version, list, it);
            }
        }, continuation);
    }
}
