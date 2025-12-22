package io.vertx.kotlin.mysqlclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.mysqlclient.MySQLAuthOptions;
import io.vertx.mysqlclient.MySQLSetOption;
import io.vertx.sqlclient.PreparedQuery;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MySQLConnection.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��>\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\u00020\u00022\u0006\u0010\r\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a+\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\u00020\u00022\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0014\u001a#\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\u00020\u00022\u0006\u0010\r\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u001d\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\tH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"changeUserAwait", "", "Lio/vertx/mysqlclient/MySQLConnection;", "options", "Lio/vertx/mysqlclient/MySQLAuthOptions;", "(Lio/vertx/mysqlclient/MySQLConnection;Lio/vertx/mysqlclient/MySQLAuthOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "debugAwait", "(Lio/vertx/mysqlclient/MySQLConnection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInternalStatisticsAwait", "", "pingAwait", "prepareAwait", "Lio/vertx/sqlclient/PreparedQuery;", "sql", "(Lio/vertx/mysqlclient/MySQLConnection;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "preparedQueryAwait", "Lio/vertx/sqlclient/RowSet;", "Lio/vertx/sqlclient/Row;", "arguments", "Lio/vertx/sqlclient/Tuple;", "(Lio/vertx/mysqlclient/MySQLConnection;Ljava/lang/String;Lio/vertx/sqlclient/Tuple;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAwait", "resetConnectionAwait", "setOptionAwait", "option", "Lio/vertx/mysqlclient/MySQLSetOption;", "(Lio/vertx/mysqlclient/MySQLConnection;Lio/vertx/mysqlclient/MySQLSetOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "specifySchemaAwait", "schemaName", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/mysqlclient/MySQLConnectionKt.class */
public final class MySQLConnectionKt {
    @Nullable
    public static final Object prepareAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$prepareAwait, @NotNull final String sql, @NotNull Continuation<? super PreparedQuery> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<PreparedQuery>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.prepareAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<PreparedQuery>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<PreparedQuery>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$prepareAwait.prepare(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object preparedQueryAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$preparedQueryAwait, @NotNull final String sql, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.preparedQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RowSet<Row>>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RowSet<Row>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$preparedQueryAwait.preparedQuery(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queryAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$queryAwait, @NotNull final String sql, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.queryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RowSet<Row>>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RowSet<Row>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryAwait.query(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object preparedQueryAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$preparedQueryAwait, @NotNull final String sql, @NotNull final Tuple arguments, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.preparedQueryAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<RowSet<Row>>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<RowSet<Row>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$preparedQueryAwait.preparedQuery(sql, arguments, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object pingAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$pingAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.pingAwait.2
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
                $this$pingAwait.ping(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.pingAwait.2.1
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
    public static final Object specifySchemaAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$specifySchemaAwait, @NotNull final String schemaName, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.specifySchemaAwait.2
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
                $this$specifySchemaAwait.specifySchema(schemaName, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.specifySchemaAwait.2.1
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
    public static final Object getInternalStatisticsAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$getInternalStatisticsAwait, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.getInternalStatisticsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getInternalStatisticsAwait.getInternalStatistics(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object setOptionAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$setOptionAwait, @NotNull final MySQLSetOption option, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.setOptionAwait.2
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
                $this$setOptionAwait.setOption(option, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.setOptionAwait.2.1
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
    public static final Object resetConnectionAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$resetConnectionAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.resetConnectionAwait.2
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
                $this$resetConnectionAwait.resetConnection(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.resetConnectionAwait.2.1
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
    public static final Object debugAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$debugAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.debugAwait.2
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
                $this$debugAwait.debug(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.debugAwait.2.1
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
    public static final Object changeUserAwait(@NotNull final io.vertx.mysqlclient.MySQLConnection $this$changeUserAwait, @NotNull final MySQLAuthOptions options, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.changeUserAwait.2
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
                $this$changeUserAwait.changeUser(options, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.mysqlclient.MySQLConnectionKt.changeUserAwait.2.1
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
