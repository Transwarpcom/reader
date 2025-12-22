package io.vertx.kotlin.pgclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.pgclient.PgConnectOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PgConnection.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\nJ!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/vertx/kotlin/pgclient/PgConnection;", "", "()V", "connectAwait", "Lio/vertx/pgclient/PgConnection;", "vertx", "Lio/vertx/core/Vertx;", "(Lio/vertx/core/Vertx;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "options", "Lio/vertx/pgclient/PgConnectOptions;", "(Lio/vertx/core/Vertx;Lio/vertx/pgclient/PgConnectOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectionUri", "", "(Lio/vertx/core/Vertx;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/PgConnection.class */
public final class PgConnection {
    public static final PgConnection INSTANCE = new PgConnection();

    private PgConnection() {
    }

    @Nullable
    public final Object connectAwait(@NotNull final Vertx vertx, @NotNull final PgConnectOptions options, @NotNull Continuation<? super io.vertx.pgclient.PgConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.pgclient.PgConnection>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnection.connectAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.pgclient.PgConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.pgclient.PgConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.pgclient.PgConnection.connect(vertx, options, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object connectAwait(@NotNull final Vertx vertx, @NotNull Continuation<? super io.vertx.pgclient.PgConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.pgclient.PgConnection>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnection.connectAwait.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.pgclient.PgConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.pgclient.PgConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.pgclient.PgConnection.connect(vertx, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object connectAwait(@NotNull final Vertx vertx, @NotNull final String connectionUri, @NotNull Continuation<? super io.vertx.pgclient.PgConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.pgclient.PgConnection>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnection.connectAwait.6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.pgclient.PgConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.pgclient.PgConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.pgclient.PgConnection.connect(vertx, connectionUri, it);
            }
        }, continuation);
    }
}
