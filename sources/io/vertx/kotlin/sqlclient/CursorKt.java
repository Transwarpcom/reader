package io.vertx.kotlin.sqlclient;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.sqlclient.Cursor;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Cursor.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a#\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"closeAwait", "", "Lio/vertx/sqlclient/Cursor;", "(Lio/vertx/sqlclient/Cursor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readAwait", "Lio/vertx/sqlclient/RowSet;", "Lio/vertx/sqlclient/Row;", "count", "", "(Lio/vertx/sqlclient/Cursor;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/sqlclient/CursorKt.class */
public final class CursorKt {
    @Nullable
    public static final Object readAwait(@NotNull final Cursor $this$readAwait, final int count, @NotNull Continuation<? super RowSet<Row>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<RowSet<Row>>>, Unit>() { // from class: io.vertx.kotlin.sqlclient.CursorKt.readAwait.2
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
                $this$readAwait.read(count, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object closeAwait(@NotNull final Cursor $this$closeAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.sqlclient.CursorKt.closeAwait.2
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
                $this$closeAwait.close(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.sqlclient.CursorKt.closeAwait.2.1
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
