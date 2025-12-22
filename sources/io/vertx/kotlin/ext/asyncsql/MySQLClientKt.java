package io.vertx.kotlin.ext.asyncsql;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MySQLClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001f\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0005\u001a'\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"querySingleAwait", "Lio/vertx/core/json/JsonArray;", "Lio/vertx/ext/asyncsql/MySQLClient;", "sql", "", "(Lio/vertx/ext/asyncsql/MySQLClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "querySingleWithParamsAwait", "arguments", "(Lio/vertx/ext/asyncsql/MySQLClient;Ljava/lang/String;Lio/vertx/core/json/JsonArray;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/asyncsql/MySQLClientKt.class */
public final class MySQLClientKt {
    @Nullable
    public static final Object querySingleAwait(@NotNull final MySQLClient $this$querySingleAwait, @NotNull final String sql, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.asyncsql.MySQLClientKt.querySingleAwait.2
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
    public static final Object querySingleWithParamsAwait(@NotNull final MySQLClient $this$querySingleWithParamsAwait, @NotNull final String sql, @NotNull final JsonArray arguments, @NotNull Continuation<? super JsonArray> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonArray>>, Unit>() { // from class: io.vertx.kotlin.ext.asyncsql.MySQLClientKt.querySingleWithParamsAwait.2
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
}
