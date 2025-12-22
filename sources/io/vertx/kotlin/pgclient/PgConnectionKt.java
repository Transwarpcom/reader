package io.vertx.kotlin.pgclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.sqlclient.PreparedQuery;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PgConnection.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��*\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a1\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\f\u001a#\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a+\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000bH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000f\u001a#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"prepareAwait", "Lio/vertx/sqlclient/PreparedQuery;", "Lio/vertx/pgclient/PgConnection;", "sql", "", "(Lio/vertx/pgclient/PgConnection;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "preparedBatchAwait", "Lio/vertx/sqlclient/RowSet;", "Lio/vertx/sqlclient/Row;", "batch", "", "Lio/vertx/sqlclient/Tuple;", "(Lio/vertx/pgclient/PgConnection;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "preparedQueryAwait", "arguments", "(Lio/vertx/pgclient/PgConnection;Ljava/lang/String;Lio/vertx/sqlclient/Tuple;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/PgConnectionKt.class */
public final class PgConnectionKt {
    @Nullable
    public static final Object prepareAwait(@NotNull final io.vertx.pgclient.PgConnection $this$prepareAwait, @NotNull final String sql, @NotNull Continuation<? super PreparedQuery> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<PreparedQuery>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnectionKt.prepareAwait.2
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
    public static final Object preparedQueryAwait(@NotNull final io.vertx.pgclient.PgConnection $this$preparedQueryAwait, @NotNull final String sql, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnectionKt.preparedQueryAwait.2
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
    public static final Object queryAwait(@NotNull final io.vertx.pgclient.PgConnection $this$queryAwait, @NotNull final String sql, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnectionKt.queryAwait.2
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
    public static final Object preparedQueryAwait(@NotNull final io.vertx.pgclient.PgConnection $this$preparedQueryAwait, @NotNull final String sql, @NotNull final Tuple arguments, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnectionKt.preparedQueryAwait.4
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
    public static final Object preparedBatchAwait(@NotNull final io.vertx.pgclient.PgConnection $this$preparedBatchAwait, @NotNull final String sql, @NotNull final List<? extends Tuple> list, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.pgclient.PgConnectionKt.preparedBatchAwait.2
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
                $this$preparedBatchAwait.preparedBatch(sql, list, it);
            }
        }, continuation);
    }
}
