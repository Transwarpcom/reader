package io.vertx.kotlin.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.VertxOptions;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Vertx.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"Lio/vertx/kotlin/core/Vertx;", "", "()V", "clusteredVertxAwait", "Lio/vertx/core/Vertx;", "options", "Lio/vertx/core/VertxOptions;", "(Lio/vertx/core/VertxOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/Vertx.class */
public final class Vertx {
    public static final Vertx INSTANCE = new Vertx();

    private Vertx() {
    }

    @Nullable
    public final Object clusteredVertxAwait(@NotNull final VertxOptions options, @NotNull Continuation<? super io.vertx.core.Vertx> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<io.vertx.core.Vertx>>, Unit>() { // from class: io.vertx.kotlin.core.Vertx.clusteredVertxAwait.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<io.vertx.core.Vertx>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<io.vertx.core.Vertx>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                io.vertx.core.Vertx.clusteredVertx(options, it);
            }
        }, continuation);
    }
}
