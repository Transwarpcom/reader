package io.vertx.kotlin.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CompositeFuture.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0002\u001a\u0015\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"onCompleteAwait", "Lio/vertx/core/CompositeFuture;", "(Lio/vertx/core/CompositeFuture;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setHandlerAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/CompositeFutureKt.class */
public final class CompositeFutureKt {
    @Nullable
    public static final Object setHandlerAwait(@NotNull final CompositeFuture $this$setHandlerAwait, @NotNull Continuation<? super CompositeFuture> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CompositeFuture>>, Unit>() { // from class: io.vertx.kotlin.core.CompositeFutureKt.setHandlerAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CompositeFuture>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CompositeFuture>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$setHandlerAwait.setHandler2(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object onCompleteAwait(@NotNull final CompositeFuture $this$onCompleteAwait, @NotNull Continuation<? super CompositeFuture> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CompositeFuture>>, Unit>() { // from class: io.vertx.kotlin.core.CompositeFutureKt.onCompleteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CompositeFuture>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CompositeFuture>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$onCompleteAwait.onComplete2(it);
            }
        }, continuation);
    }
}
