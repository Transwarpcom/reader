package io.vertx.kotlin.core.eventbus;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
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

/* compiled from: Message.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001a\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a7\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a?\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"replyAndRequestAwait", "Lio/vertx/core/eventbus/Message;", "R", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "message", "", "(Lio/vertx/core/eventbus/Message;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "options", "Lio/vertx/core/eventbus/DeliveryOptions;", "(Lio/vertx/core/eventbus/Message;Ljava/lang/Object;Lio/vertx/core/eventbus/DeliveryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/eventbus/MessageKt.class */
public final class MessageKt {
    @Nullable
    public static final <R, T> Object replyAndRequestAwait(@NotNull final Message<T> message, @Nullable final Object message2, @NotNull Continuation<? super Message<R>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Message<R>>>, Unit>() { // from class: io.vertx.kotlin.core.eventbus.MessageKt.replyAndRequestAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<Message<R>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                message.replyAndRequest(message2, it);
            }
        }, continuation);
    }

    @Nullable
    public static final <R, T> Object replyAndRequestAwait(@NotNull final Message<T> message, @Nullable final Object message2, @NotNull final DeliveryOptions options, @NotNull Continuation<? super Message<R>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Message<R>>>, Unit>() { // from class: io.vertx.kotlin.core.eventbus.MessageKt.replyAndRequestAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                invoke((Handler) obj);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(@NotNull Handler<AsyncResult<Message<R>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                message.replyAndRequest(message2, options, it);
            }
        }, continuation);
    }
}
