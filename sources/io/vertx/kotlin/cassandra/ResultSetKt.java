package io.vertx.kotlin.cassandra;

import com.datastax.driver.core.Row;
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

/* compiled from: ResultSet.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\"\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001b\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u0002*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0004\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"allAwait", "", "Lcom/datastax/driver/core/Row;", "Lio/vertx/cassandra/ResultSet;", "(Lio/vertx/cassandra/ResultSet;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchMoreResultsAwait", "", "oneAwait", "severalAwait", "amount", "", "(Lio/vertx/cassandra/ResultSet;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/cassandra/ResultSetKt.class */
public final class ResultSetKt {
    @Nullable
    public static final Object fetchMoreResultsAwait(@NotNull final ResultSet $this$fetchMoreResultsAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.cassandra.ResultSetKt.fetchMoreResultsAwait.2
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
                $this$fetchMoreResultsAwait.fetchMoreResults(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.cassandra.ResultSetKt.fetchMoreResultsAwait.2.1
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
    public static final Object oneAwait(@NotNull final ResultSet $this$oneAwait, @NotNull Continuation<? super Row> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Row>>, Unit>() { // from class: io.vertx.kotlin.cassandra.ResultSetKt.oneAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Row>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Row>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$oneAwait.one(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object severalAwait(@NotNull final ResultSet $this$severalAwait, final int amount, @NotNull Continuation<? super List<? extends Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Row>>>, Unit>() { // from class: io.vertx.kotlin.cassandra.ResultSetKt.severalAwait.2
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
                $this$severalAwait.several(amount, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object allAwait(@NotNull final ResultSet $this$allAwait, @NotNull Continuation<? super List<? extends Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Row>>>, Unit>() { // from class: io.vertx.kotlin.cassandra.ResultSetKt.allAwait.2
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
                $this$allAwait.all(it);
            }
        }, continuation);
    }
}
