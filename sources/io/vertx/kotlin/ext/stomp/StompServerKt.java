package io.vertx.kotlin.ext.stomp;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.stomp.StompServer;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: StompServer.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0002*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a%\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"closeAwait", "", "Lio/vertx/ext/stomp/StompServer;", "(Lio/vertx/ext/stomp/StompServer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listenAwait", RtspHeaders.Values.PORT, "", "(Lio/vertx/ext/stomp/StompServer;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "host", "", "(Lio/vertx/ext/stomp/StompServer;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/stomp/StompServerKt.class */
public final class StompServerKt {
    @Nullable
    public static final Object listenAwait(@NotNull final StompServer $this$listenAwait, @NotNull Continuation<? super StompServer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompServer>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompServerKt.listenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompServer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompServer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listenAwait.listen(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listenAwait(@NotNull final StompServer $this$listenAwait, final int port, @NotNull Continuation<? super StompServer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompServer>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompServerKt.listenAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompServer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompServer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listenAwait.listen(port, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listenAwait(@NotNull final StompServer $this$listenAwait, final int port, @NotNull final String host, @NotNull Continuation<? super StompServer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<StompServer>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompServerKt.listenAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<StompServer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<StompServer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listenAwait.listen(port, host, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final StompServer $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.stomp.StompServerKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.stomp.StompServerKt.closeAwait.2.1
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
