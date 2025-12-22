package io.vertx.kotlin.sqlclient;

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

/* compiled from: PreparedQuery.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u001a)\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u001a\u0015\u0010\b\u001a\u00020\t*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a#\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\f\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"batchAwait", "Lio/vertx/sqlclient/RowSet;", "Lio/vertx/sqlclient/Row;", "Lio/vertx/sqlclient/PreparedQuery;", "argsList", "", "Lio/vertx/sqlclient/Tuple;", "(Lio/vertx/sqlclient/PreparedQuery;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeAwait", "", "(Lio/vertx/sqlclient/PreparedQuery;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeAwait", "args", "(Lio/vertx/sqlclient/PreparedQuery;Lio/vertx/sqlclient/Tuple;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/sqlclient/PreparedQueryKt.class */
public final class PreparedQueryKt {
    @Nullable
    public static final Object executeAwait(@NotNull final PreparedQuery $this$executeAwait, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.sqlclient.PreparedQueryKt.executeAwait.2
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
                $this$executeAwait.execute(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object executeAwait(@NotNull final PreparedQuery $this$executeAwait, @NotNull final Tuple args, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.sqlclient.PreparedQueryKt.executeAwait.4
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
                $this$executeAwait.execute(args, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object batchAwait(@NotNull final PreparedQuery $this$batchAwait, @NotNull final List<? extends Tuple> list, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.sqlclient.PreparedQueryKt.batchAwait.2
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
                $this$batchAwait.batch(list, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final PreparedQuery $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.sqlclient.PreparedQueryKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.sqlclient.PreparedQueryKt.closeAwait.2.1
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
