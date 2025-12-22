package io.vertx.kotlin.ext.unit;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.unit.Completion;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Completion.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a#\u0010��\u001a\u0004\u0018\u0001H\u0001\"\u0004\b��\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"handlerAwait", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lio/vertx/ext/unit/Completion;", "(Lio/vertx/ext/unit/Completion;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/unit/CompletionKt.class */
public final class CompletionKt {
    @Nullable
    public static final <T> Object handlerAwait(@NotNull final Completion<T> completion, @NotNull Continuation<? super T> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<T>>, Unit>() { // from class: io.vertx.kotlin.ext.unit.CompletionKt.handlerAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<T>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                completion.handler(it);
            }
        }, continuation);
    }
}
