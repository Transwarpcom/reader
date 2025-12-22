package io.vertx.kotlin.mysqlclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.mysqlclient.MySQLConnectOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MySQLConnection.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\tJ!\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lio/vertx/kotlin/mysqlclient/MySQLConnection;", "", "()V", "connectAwait", "Lio/vertx/mysqlclient/MySQLConnection;", "vertx", "Lio/vertx/core/Vertx;", "connectOptions", "Lio/vertx/mysqlclient/MySQLConnectOptions;", "(Lio/vertx/core/Vertx;Lio/vertx/mysqlclient/MySQLConnectOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connectionUri", "", "(Lio/vertx/core/Vertx;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mysqlclient/MySQLConnection.class */
public final class MySQLConnection {
    public static final MySQLConnection INSTANCE = new MySQLConnection();

    private MySQLConnection() {
    }

    @Nullable
    public final Object connectAwait(@NotNull final Vertx vertx, @NotNull final MySQLConnectOptions connectOptions, @NotNull Continuation<? super io.vertx.mysqlclient.MySQLConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.mysqlclient.MySQLConnection>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnection.connectAwait.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.mysqlclient.MySQLConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.mysqlclient.MySQLConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.mysqlclient.MySQLConnection.connect(vertx, connectOptions, it);
            }
        }, continuation);
    }

    @Nullable
    public final Object connectAwait(@NotNull final Vertx vertx, @NotNull final String connectionUri, @NotNull Continuation<? super io.vertx.mysqlclient.MySQLConnection> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.mysqlclient.MySQLConnection>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnection.connectAwait.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.mysqlclient.MySQLConnection>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.mysqlclient.MySQLConnection>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.mysqlclient.MySQLConnection.connect(vertx, connectionUri, it);
            }
        }, continuation);
    }
}
