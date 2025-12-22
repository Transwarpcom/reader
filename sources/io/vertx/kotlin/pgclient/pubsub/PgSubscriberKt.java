package io.vertx.kotlin.pgclient.pubsub;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import io.vertx.pgclient.pubsub.PgSubscriber;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PgSubscriber.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"connectAwait", "", "Lio/vertx/pgclient/pubsub/PgSubscriber;", "(Lio/vertx/pgclient/pubsub/PgSubscriber;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/pgclient/pubsub/PgSubscriberKt.class */
public final class PgSubscriberKt {
    @Nullable
    public static final Object connectAwait(@NotNull final PgSubscriber $this$connectAwait, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.pgclient.pubsub.PgSubscriberKt.connectAwait.2
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
                $this$connectAwait.connect(new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.pgclient.pubsub.PgSubscriberKt.connectAwait.2.1
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
