package io.vertx.kotlin.core;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Future.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010��\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a!\u0010\u0004\u001a\u0002H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"onCompleteAwait", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lio/vertx/core/Future;", "(Lio/vertx/core/Future;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setHandlerAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/FutureKt.class */
public final class FutureKt {
    @Nullable
    public static final <T> Object setHandlerAwait(@NotNull final Future<T> future, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.core.FutureKt.setHandlerAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                future.setHandler2(it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object onCompleteAwait(@NotNull final Future<T> future, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.core.FutureKt.onCompleteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                future.onComplete2(it);
            }
        }, continuation);
    }
}
