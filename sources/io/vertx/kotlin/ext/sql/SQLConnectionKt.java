package io.vertx.kotlin.ext.sql;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.sql.SQLRowStream;
import io.vertx.ext.sql.TransactionIsolation;
import io.vertx.ext.sql.UpdateResult;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SQLConnection.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��P\n��\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a)\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a?\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a1\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a-\u0010\u0014\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a\u0015\u0010\u0018\u001a\u00020\u0019*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u0015\u0010\u001b\u001a\u00020\u0019*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u001d\u0010\u001c\u001a\u00020\u0019*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u001d\u0010\u001f\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a\u001f\u0010 \u001a\u0004\u0018\u00010\n*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a'\u0010!\u001a\u0004\u0018\u00010\n*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010#\u001a\u001d\u0010$\u001a\u00020%*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a%\u0010&\u001a\u00020%*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010#\u001a%\u0010'\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010#\u001a\u0015\u0010(\u001a\u00020\u0019*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001a\u001a\u001d\u0010)\u001a\u00020\u0019*\u00020\u00032\u0006\u0010*\u001a\u00020+H\u0086@ø\u0001��¢\u0006\u0002\u0010,\u001a\u001d\u0010-\u001a\u00020\u0019*\u00020\u00032\u0006\u0010.\u001a\u00020\u001eH\u0086@ø\u0001��¢\u0006\u0002\u0010/\u001a\u001d\u00100\u001a\u000201*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a%\u00102\u001a\u000201*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u00063"}, d2 = {"batchAwait", "", "", "Lio/vertx/ext/sql/SQLConnection;", "sqlStatements", "", "(Lio/vertx/ext/sql/SQLConnection;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "batchCallableWithParamsAwait", "sqlStatement", "inArgs", "Lio/vertx/core/json/JsonArray;", "outArgs", "(Lio/vertx/ext/sql/SQLConnection;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "batchWithParamsAwait", "args", "(Lio/vertx/ext/sql/SQLConnection;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callAwait", "Lio/vertx/ext/sql/ResultSet;", "sql", "(Lio/vertx/ext/sql/SQLConnection;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callWithParamsAwait", "params", "outputs", "(Lio/vertx/ext/sql/SQLConnection;Ljava/lang/String;Lio/vertx/core/json/JsonArray;Lio/vertx/core/json/JsonArray;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeAwait", "", "(Lio/vertx/ext/sql/SQLConnection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "commitAwait", "executeAwait", "getTransactionIsolationAwait", "Lio/vertx/ext/sql/TransactionIsolation;", "queryAwait", "querySingleAwait", "querySingleWithParamsAwait", "arguments", "(Lio/vertx/ext/sql/SQLConnection;Ljava/lang/String;Lio/vertx/core/json/JsonArray;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryStreamAwait", "Lio/vertx/ext/sql/SQLRowStream;", "queryStreamWithParamsAwait", "queryWithParamsAwait", "rollbackAwait", "setAutoCommitAwait", "autoCommit", "", "(Lio/vertx/ext/sql/SQLConnection;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setTransactionIsolationAwait", "isolation", "(Lio/vertx/ext/sql/SQLConnection;Lio/vertx/ext/sql/TransactionIsolation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAwait", "Lio/vertx/ext/sql/UpdateResult;", "updateWithParamsAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/sql/SQLConnectionKt.class */
public final class SQLConnectionKt {
    @Nullable
    public static final Object querySingleAwait(@NotNull final SQLConnection $this$querySingleAwait, @NotNull final String sql, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.querySingleAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$querySingleAwait.querySingle(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object querySingleWithParamsAwait(@NotNull final SQLConnection $this$querySingleWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray arguments, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.querySingleWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonArray>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonArray>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$querySingleWithParamsAwait.querySingleWithParams(sql, arguments, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object setAutoCommitAwait(@NotNull final SQLConnection $this$setAutoCommitAwait, final boolean autoCommit, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.setAutoCommitAwait.2
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
                $this$setAutoCommitAwait.setAutoCommit(autoCommit, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.setAutoCommitAwait.2.1
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
    public static final Object executeAwait(@NotNull final SQLConnection $this$executeAwait, @NotNull final String sql, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.executeAwait.2
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
                $this$executeAwait.execute(sql, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.executeAwait.2.1
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
    public static final Object queryAwait(@NotNull final SQLConnection $this$queryAwait, @NotNull final String sql, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.queryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ResultSet>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ResultSet>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryAwait.query(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queryStreamAwait(@NotNull final SQLConnection $this$queryStreamAwait, @NotNull final String sql, @NotNull Continuation<? super SQLRowStream> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SQLRowStream>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.queryStreamAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<SQLRowStream>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<SQLRowStream>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryStreamAwait.queryStream(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queryWithParamsAwait(@NotNull final SQLConnection $this$queryWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.queryWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ResultSet>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ResultSet>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryWithParamsAwait.queryWithParams(sql, params, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queryStreamWithParamsAwait(@NotNull final SQLConnection $this$queryStreamWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull Continuation<? super SQLRowStream> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SQLRowStream>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.queryStreamWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<SQLRowStream>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<SQLRowStream>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryStreamWithParamsAwait.queryStreamWithParams(sql, params, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateAwait(@NotNull final SQLConnection $this$updateAwait, @NotNull final String sql, @NotNull Continuation<? super UpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<UpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.updateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<UpdateResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<UpdateResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateAwait.update(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateWithParamsAwait(@NotNull final SQLConnection $this$updateWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull Continuation<? super UpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<UpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.updateWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<UpdateResult>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<UpdateResult>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateWithParamsAwait.updateWithParams(sql, params, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object callAwait(@NotNull final SQLConnection $this$callAwait, @NotNull final String sql, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.callAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ResultSet>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ResultSet>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$callAwait.call(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object callWithParamsAwait(@NotNull final SQLConnection $this$callWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull final JsonArray outputs, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.callWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ResultSet>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ResultSet>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$callWithParamsAwait.callWithParams(sql, params, outputs, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final SQLConnection $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.closeAwait.2.1
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
    public static final Object commitAwait(@NotNull final SQLConnection $this$commitAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.commitAwait.2
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
                $this$commitAwait.commit(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.commitAwait.2.1
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
    public static final Object rollbackAwait(@NotNull final SQLConnection $this$rollbackAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.rollbackAwait.2
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
                $this$rollbackAwait.rollback(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.rollbackAwait.2.1
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
    public static final Object batchAwait(@NotNull final SQLConnection $this$batchAwait, @NotNull final List<String> list, @NotNull Continuation<? super List<Integer>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Integer>>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.batchAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Integer>>> handler) {
                invoke2((Handler<AsyncResult<List<Integer>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Integer>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$batchAwait.batch(list, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object batchWithParamsAwait(@NotNull final SQLConnection $this$batchWithParamsAwait, @NotNull final String sqlStatement, @NotNull final List<? extends JsonArray> list, @NotNull Continuation<? super List<Integer>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Integer>>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.batchWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Integer>>> handler) {
                invoke2((Handler<AsyncResult<List<Integer>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Integer>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$batchWithParamsAwait.batchWithParams(sqlStatement, list, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object batchCallableWithParamsAwait(@NotNull final SQLConnection $this$batchCallableWithParamsAwait, @NotNull final String sqlStatement, @NotNull final List<? extends JsonArray> list, @NotNull final List<? extends JsonArray> list2, @NotNull Continuation<? super List<Integer>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Integer>>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.batchCallableWithParamsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Integer>>> handler) {
                invoke2((Handler<AsyncResult<List<Integer>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Integer>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$batchCallableWithParamsAwait.batchCallableWithParams(sqlStatement, list, list2, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object setTransactionIsolationAwait(@NotNull final SQLConnection $this$setTransactionIsolationAwait, @NotNull final TransactionIsolation isolation, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.setTransactionIsolationAwait.2
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
                $this$setTransactionIsolationAwait.setTransactionIsolation(isolation, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.setTransactionIsolationAwait.2.1
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
    public static final Object getTransactionIsolationAwait(@NotNull final SQLConnection $this$getTransactionIsolationAwait, @NotNull Continuation<? super TransactionIsolation> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<TransactionIsolation>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLConnectionKt.getTransactionIsolationAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<TransactionIsolation>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<TransactionIsolation>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getTransactionIsolationAwait.getTransactionIsolation(it);
            }
        }, continuation);
    }
}
