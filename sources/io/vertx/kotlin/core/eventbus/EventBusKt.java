package io.vertx.kotlin.core.eventbus;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: EventBus.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\b\u001a;\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"requestAwait", "Lio/vertx/core/eventbus/Message;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lio/vertx/core/eventbus/EventBus;", "address", "", "message", "", "(Lio/vertx/core/eventbus/EventBus;Ljava/lang/String;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "options", "Lio/vertx/core/eventbus/DeliveryOptions;", "(Lio/vertx/core/eventbus/EventBus;Ljava/lang/String;Ljava/lang/Object;Lio/vertx/core/eventbus/DeliveryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/eventbus/EventBusKt.class */
public final class EventBusKt {
    @Nullable
    public static final <T> Object requestAwait(@NotNull final EventBus $this$requestAwait, @NotNull final String address, @Nullable final Object message, @NotNull Continuation<? super Message<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Message<T>>>, Unit>() { // from class: io.vertx.kotlin.core.eventbus.EventBusKt.requestAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<Message<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$requestAwait.request(address, message, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <T> Object requestAwait(@NotNull final EventBus $this$requestAwait, @NotNull final String address, @Nullable final Object message, @NotNull final DeliveryOptions options, @NotNull Continuation<? super Message<T>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Message<T>>>, Unit>() { // from class: io.vertx.kotlin.core.eventbus.EventBusKt.requestAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<Message<T>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$requestAwait.request(address, message, options, it);
            }
        }, continuation);
    }
}
