package io.vertx.kotlin.cassandra;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import io.vertx.cassandra.CassandraClient;
import io.vertx.cassandra.CassandraRowStream;
import io.vertx.cassandra.ResultSet;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CassandraClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��<\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a\u001d\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010\u000f\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u001a\u001d\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a\u001d\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"closeAwait", "", "Lio/vertx/cassandra/CassandraClient;", "(Lio/vertx/cassandra/CassandraClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeAwait", "Lio/vertx/cassandra/ResultSet;", "statement", "Lcom/datastax/driver/core/Statement;", "(Lio/vertx/cassandra/CassandraClient;Lcom/datastax/driver/core/Statement;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "query", "", "(Lio/vertx/cassandra/CassandraClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeWithFullFetchAwait", "", "Lcom/datastax/driver/core/Row;", "prepareAwait", "Lcom/datastax/driver/core/PreparedStatement;", "queryStreamAwait", "Lio/vertx/cassandra/CassandraRowStream;", "sql", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/cassandra/CassandraClientKt.class */
public final class CassandraClientKt {
    @Nullable
    public static final Object executeAwait(@NotNull final CassandraClient $this$executeAwait, @NotNull final String query, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.executeAwait.2
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
                $this$executeAwait.execute(query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queryStreamAwait(@NotNull final CassandraClient $this$queryStreamAwait, @NotNull final String sql, @NotNull Continuation<? super CassandraRowStream> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CassandraRowStream>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.queryStreamAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CassandraRowStream>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CassandraRowStream>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryStreamAwait.queryStream(sql, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final CassandraClient $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.closeAwait.2.1
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
    public static final Object executeWithFullFetchAwait(@NotNull final CassandraClient $this$executeWithFullFetchAwait, @NotNull final String query, @NotNull Continuation<? super List<? extends Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Row>>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.executeWithFullFetchAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Row>>> handler) {
                invoke2((Handler<AsyncResult<List<Row>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Row>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$executeWithFullFetchAwait.executeWithFullFetch(query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object executeWithFullFetchAwait(@NotNull final CassandraClient $this$executeWithFullFetchAwait, @NotNull final Statement statement, @NotNull Continuation<? super List<? extends Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Row>>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.executeWithFullFetchAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Row>>> handler) {
                invoke2((Handler<AsyncResult<List<Row>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Row>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$executeWithFullFetchAwait.executeWithFullFetch(statement, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object executeAwait(@NotNull final CassandraClient $this$executeAwait, @NotNull final Statement statement, @NotNull Continuation<? super ResultSet> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ResultSet>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.executeAwait.4
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
                $this$executeAwait.execute(statement, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object prepareAwait(@NotNull final CassandraClient $this$prepareAwait, @NotNull final String query, @NotNull Continuation<? super PreparedStatement> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<PreparedStatement>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.prepareAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<PreparedStatement>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<PreparedStatement>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$prepareAwait.prepare(query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object queryStreamAwait(@NotNull final CassandraClient $this$queryStreamAwait, @NotNull final Statement statement, @NotNull Continuation<? super CassandraRowStream> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CassandraRowStream>>, Unit>() { // from class: io.vertx.kotlin.cassandra.CassandraClientKt.queryStreamAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CassandraRowStream>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CassandraRowStream>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$queryStreamAwait.queryStream(statement, it);
            }
        }, continuation);
    }
}
