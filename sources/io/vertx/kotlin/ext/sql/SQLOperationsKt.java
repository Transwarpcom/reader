package io.vertx.kotlin.ext.sql;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLOperations;
import io.vertx.ext.sql.SQLRowStream;
import io.vertx.ext.sql.UpdateResult;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SQLOperations.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��,\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a-\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a\u001f\u0010\f\u001a\u0004\u0018\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a'\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0012\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a%\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a\u001d\u0010\u0014\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a%\u0010\u0016\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"callAwait", "Lio/vertx/ext/sql/ResultSet;", "Lio/vertx/ext/sql/SQLOperations;", "sql", "", "(Lio/vertx/ext/sql/SQLOperations;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callWithParamsAwait", "params", "Lio/vertx/core/json/JsonArray;", "outputs", "(Lio/vertx/ext/sql/SQLOperations;Ljava/lang/String;Lio/vertx/core/json/JsonArray;Lio/vertx/core/json/JsonArray;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAwait", "querySingleAwait", "querySingleWithParamsAwait", "arguments", "(Lio/vertx/ext/sql/SQLOperations;Ljava/lang/String;Lio/vertx/core/json/JsonArray;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryStreamAwait", "Lio/vertx/ext/sql/SQLRowStream;", "queryStreamWithParamsAwait", "queryWithParamsAwait", "updateAwait", "Lio/vertx/ext/sql/UpdateResult;", "updateWithParamsAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/sql/SQLOperationsKt.class */
public final class SQLOperationsKt {
    @Nullable
    public static final Object queryAwait(@NotNull final SQLOperations $this$queryAwait, @NotNull final String sql, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.queryAwait.2
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
    public static final Object queryWithParamsAwait(@NotNull final SQLOperations $this$queryWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.queryWithParamsAwait.2
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
    public static final Object queryStreamAwait(@NotNull final SQLOperations $this$queryStreamAwait, @NotNull final String sql, @NotNull Continuation<? super SQLRowStream> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SQLRowStream>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.queryStreamAwait.2
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
    public static final Object queryStreamWithParamsAwait(@NotNull final SQLOperations $this$queryStreamWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull Continuation<? super SQLRowStream> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SQLRowStream>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.queryStreamWithParamsAwait.2
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
    public static final Object querySingleAwait(@NotNull final SQLOperations $this$querySingleAwait, @NotNull final String sql, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.querySingleAwait.2
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
    public static final Object querySingleWithParamsAwait(@NotNull final SQLOperations $this$querySingleWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray arguments, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.querySingleWithParamsAwait.2
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
    public static final Object updateAwait(@NotNull final SQLOperations $this$updateAwait, @NotNull final String sql, @NotNull Continuation<? super UpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<UpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.updateAwait.2
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
    public static final Object updateWithParamsAwait(@NotNull final SQLOperations $this$updateWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull Continuation<? super UpdateResult> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<UpdateResult>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.updateWithParamsAwait.2
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
    public static final Object callAwait(@NotNull final SQLOperations $this$callAwait, @NotNull final String sql, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.callAwait.2
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
    public static final Object callWithParamsAwait(@NotNull final SQLOperations $this$callWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray params, @NotNull final JsonArray outputs, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.ext.sql.SQLOperationsKt.callWithParamsAwait.2
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
}
